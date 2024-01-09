import type {AsyncStorageSqlite} from '@react-native-async-storage/sqlite-storage';
import React, {useState} from 'react';
import {Button, StyleSheet, Text, View} from 'react-native';

type Props = {db: AsyncStorageSqlite};

const BasicCrud: React.FC<Props> = ({db}) => {
  const [value, setValue] = useState<null | string>(null);

  async function readValue() {
    const stored = await db.read('random');
    setValue(JSON.stringify(stored, null, 2));
  }

  async function createAndSaveRandomValue() {
    const randomValue = (Math.random() % 100) * 100;
    await db.save('random', String(randomValue));
    await readValue();
  }

  async function deleteValue() {
    await db.delete('random');
    await readValue();
  }

  return (
    <View style={styles.container}>
      <Text style={styles.header}>Basic crud example</Text>
      <View style={styles.row}>
        <Button title="read random" onPress={readValue} />
        <Button title="Save random" onPress={createAndSaveRandomValue} />
        <Button title="delete random" onPress={deleteValue} />
      </View>

      <Text>{value ?? 'null'}</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    padding: 8,
  },
  header: {
    fontSize: 18,
  },
  row: {
    paddingVertical: 18,
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
});

export default BasicCrud;
