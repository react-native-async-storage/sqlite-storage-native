package org.asyncstorage.sqlitestorage

import app.cash.turbine.test
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.TestScope
import org.asyncstorage.sqlitestorage.models.Entry
import org.asyncstorage.sqlitestorage.utils.JunitRunner
import org.asyncstorage.sqlitestorage.utils.RunWith
import org.asyncstorage.sqlitestorage.utils.createTestDatabase
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlinx.coroutines.test.runTest as realRunTest

@RunWith(JunitRunner::class)
class StorageAccessTest {
    private val testDispatcher = StandardTestDispatcher()

    private fun createDb() =
        createTestDatabase(
            "Test.db",
            dispatcher = testDispatcher,
        )

    private fun runTest(testBody: suspend TestScope.(db: SqliteStorage) -> Unit): TestResult {
        return realRunTest(context = testDispatcher, testBody = {
            val db = createDb()
            testBody(db)
            db.dropDatabase()
        })
    }

    @Test
    fun writes_and_reads_single_entry() =
        runTest { db ->
            val data = Entry("key", "my_value")
            var current = db.read("key")
            assertNull(current.value, "entry should be null")
            db.write(data)
            current = db.read("key")
            assertNotNull(current.value, "entry should NOT be null")
        }

    @Test
    fun overrides_existing_entries() =
        runTest { db ->
            val entry = Entry("basic", "crud")
            var current = db.read("basic")
            assertNull(current.value, "initial entry should be null")
            db.write(entry)
            current = db.read("basic")
            assertNotNull(current.value, "entry should not be null after saving")
            val overrideEntry = Entry("basic", "new_value_here")
            db.write(overrideEntry)
            current = db.read("basic")
            assertEquals(overrideEntry, current, "Overridden entry is not the same")
        }

    @Test
    fun inserts_and_reads_multiple_entries() =
        runTest { db ->
            val entries =
                listOf(
                    Entry("key1", "my_value1"),
                    Entry("key2", "my_value2"),
                    Entry("key3", "my_value3"),
                )
            var current = db.readMany(entries.map { e -> e.key })
            current.forEach {
                assertNull(it.value, "entry should be null")
            }
            db.writeMany(entries)
            current = db.readMany(entries.map { e -> e.key })
            current.forEach {
                assertNotNull(it.value, "Value should NOT be null")
            }
        }

    @Test
    fun removes_only_selected_entries() =
        runTest { db ->
            val entries =
                listOf(
                    Entry("k1", "delete_me_1"),
                    Entry("k2", "delete_me_2"),
                    Entry("k3", "delete_me_3"),
                )

            db.writeMany(entries)
            db.readMany(entries.map { it.key })
                .forEach { e -> assertNotNull(e.value, "saved entry is null") }
            db.remove("k1")
            val current = db.readMany(entries.map { it.key })
            val k1 = current.find { it.key == "k1" }
            assertNotNull(k1, "entry should be returned with null value")
            assertNull(k1.value, "k1 value should be null")
            val rest = current.filter { it != k1 }
            rest.forEach { e -> assertNotNull(e.value, "${e.key}'s value should not be null") }
        }

    @Test
    fun clears_all_entries() =
        runTest { db ->
            val entries =
                listOf(
                    Entry("t1", "d1"),
                    Entry("t2", "d2"),
                    Entry("t3", "d3"),
                    Entry("t4", "d4"),
                    Entry("t5", "d5"),
                    Entry("t6", "d6"),
                )

            db.writeMany(entries)
            var savedEntries = db.readMany(entries.map { it.key })
            assertEquals(6, savedEntries.size, "Saved entries not matching required length")
            savedEntries.forEach { assertNotNull(it.value, "Entry ${it.key} should not be null") }

            db.clear()
            savedEntries = db.readMany(entries.map { it.key })
            // keeps the same number of requested keys
            assertEquals(
                6,
                savedEntries.size,
                "Saved entries after clear not matching required length",
            )
            savedEntries.forEach { assertNull(it.value, "Entry ${it.key} should cleared") }
        }

    @Test
    fun reads_entry_on_change_as_flow() =
        runTest { db ->
            val entry = Entry("key1", "value123")

            db.readAsFlow(entry.key).test {
                skipItems(1) // skip initial emit, where value is null
                db.write(entry)
                assertEquals(
                    entry,
                    awaitItem(),
                    "entry item is different then emitted one",
                )
                val copy = entry.copy(value = "987entry")
                db.write(copy)
                assertEquals(
                    copy,
                    awaitItem(),
                    "copied item is different than emitted",
                )
                db.write(Entry("different"))
                assertEquals(
                    copy,
                    awaitItem(),
                    "copy value is changed after writing different value",
                )
            }
        }

    @Test
    fun reads_many_entries_on_change_as_flow() =
        runTest { db ->
            val entries =
                listOf(
                    Entry("1", "value1"),
                    Entry("2", "value2"),
                )

            db.readManyAsFlow(entries.map { it.key }).test {
                skipItems(1) // skip first where items are not available
                db.writeMany(entries)
                assertEquals(
                    entries,
                    awaitItem(),
                    "initial current entries not matching with emitted",
                )
                // override one entry
                db.write(Entry("1", null))
                assertNull(
                    awaitItem().find { it.key == "1" }!!.value,
                    "Entry 1 should have null value",
                )
                // bring back to original value
                db.write(Entry("1", "value1"))
                assertEquals(entries, awaitItem(), "entries not matching expected original value")
            }
        }

    @Test
    fun reads_keys_on_change_as_flow() =
        runTest { db ->
            val entryList =
                listOf(
                    Entry("1", "entry_value_1"),
                    Entry("2", "entry_value_2"),
                    Entry("3", "entry_value_3"),
                    Entry("4", "entry_value_4"),
                )
            db.getKeysAsFlow().test {
                skipItems(1) // skip first emit where default null values are emitted
                db.writeMany(entryList)
                assertEquals(
                    listOf("1", "2", "3", "4"),
                    awaitItem(),
                    "initial values do not match",
                )

                db.removeMany(listOf("2", "4"))
                assertEquals(
                    listOf("1", "3"),
                    awaitItem(),
                    "values after remove do not match",
                )

                db.write(Entry("9"))
                assertEquals(
                    listOf("1", "3", "9"),
                    awaitItem(),
                    "values after addition do not match",
                )
            }
        }

    @Test
    fun merges_entry_with_existing_one() =
        runTest { db ->
            var entry = Entry("merge_test", "[1,2,3,4]")
            db.write(entry)
            entry = Entry("merge_test", "[5,6,7,8]")
            val result = db.merge(entry)
            assertEquals("[1,2,3,4,5,6,7,8]", result.value)
            assertEquals("merge_test", result.key)
            val read = db.read("merge_test")
            assertEquals(result, read)
        }

    @Test
    fun merges_multiple_entries() =
        runTest { db ->
            var entry1 = Entry("merge_test_1", "[1,2,3]")
            var entry2 = Entry("merge_test_2", null)
            var entry3 = Entry("merge_test_3", """{"a":1,"b":[true],"c":null,"d":{"e":true}}""")
            db.writeMany(listOf(entry1, entry2, entry3))
            entry1 = Entry("merge_test_1", "[4,5,6]")
            entry2 = Entry("merge_test_2", "value")
            entry3 = Entry("merge_test_3", """{"a":null,"b":999,"c":"value","d":{"e":false}}""")
            val result = db.mergeMany(listOf(entry1, entry2, entry3))
            entry1 = result.find { e -> e.key == "merge_test_1" }!!
            entry2 = result.find { e -> e.key == "merge_test_2" }!!
            entry3 = result.find { e -> e.key == "merge_test_3" }!!
            assertEquals("[1,2,3,4,5,6]", entry1.value)
            assertEquals("value", entry2.value)
            assertEquals("""{"a":null,"b":999,"c":"value","d":{"e":false}}""", entry3.value)
            assertEquals(db.read("merge_test_1"), entry1)
            assertEquals(db.read("merge_test_2"), entry2)
            assertEquals(db.read("merge_test_3"), entry3)
        }
}
