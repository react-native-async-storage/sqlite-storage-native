#import <Foundation/NSArray.h>
#import <Foundation/NSDictionary.h>
#import <Foundation/NSError.h>
#import <Foundation/NSObject.h>
#import <Foundation/NSSet.h>
#import <Foundation/NSString.h>
#import <Foundation/NSValue.h>

@class SQLSKMPKotlinEnumCompanion, SQLSKMPKotlinEnum<E>, SQLSKMPDatabaseFileType, SQLSKMPKotlinArray<T>, SQLSKMPAsync_storage_entriesQueries, SQLSKMPAsyncStorageDBCompanion, SQLSKMPAsync_storage_entries, SQLSKMPKotlinUnit, SQLSKMPRuntimeTransacterTransaction, SQLSKMPKotlinThrowable, SQLSKMPRuntimeBaseTransacterImpl, SQLSKMPRuntimeTransacterImpl, SQLSKMPRuntimeQuery<__covariant RowType>, SQLSKMPEntryKMP, SQLSKMPKotlinx_serialization_jsonJsonElementCompanion, SQLSKMPKotlinx_serialization_jsonJsonElement, SQLSKMPKotlinx_coroutines_coreCoroutineDispatcher, NSError, SQLSKMPRuntimeAfterVersion, SQLSKMPRuntimeExecutableQuery<__covariant RowType>, SQLSKMPKotlinAbstractCoroutineContextElement, SQLSKMPKotlinx_coroutines_coreCoroutineDispatcherKey, SQLSKMPKotlinByteArray, SQLSKMPKotlinAbstractCoroutineContextKey<B, E>, SQLSKMPKotlinException, SQLSKMPKotlinRuntimeException, SQLSKMPKotlinIllegalStateException, SQLSKMPKotlinByteIterator, SQLSKMPKotlinx_serialization_coreSerializersModule, SQLSKMPKotlinx_serialization_coreSerialKind, SQLSKMPKotlinNothing;

@protocol SQLSKMPKotlinComparable, SQLSKMPDatabaseFiles, SQLSKMPSQLiteStorageKMP, SQLSKMPRuntimeTransactionWithoutReturn, SQLSKMPRuntimeTransactionWithReturn, SQLSKMPRuntimeTransacterBase, SQLSKMPRuntimeTransacter, SQLSKMPAsyncStorageDB, SQLSKMPRuntimeSqlDriver, SQLSKMPRuntimeSqlSchema, SQLSKMPKotlinIterator, SQLSKMPRuntimeTransactionCallbacks, SQLSKMPRuntimeQueryListener, SQLSKMPRuntimeQueryResult, SQLSKMPRuntimeSqlPreparedStatement, SQLSKMPRuntimeSqlCursor, SQLSKMPRuntimeCloseable, SQLSKMPKotlinx_serialization_coreKSerializer, SQLSKMPKotlinCoroutineContextKey, SQLSKMPKotlinCoroutineContextElement, SQLSKMPKotlinCoroutineContext, SQLSKMPKotlinContinuation, SQLSKMPKotlinContinuationInterceptor, SQLSKMPKotlinx_coroutines_coreRunnable, SQLSKMPKotlinx_serialization_coreEncoder, SQLSKMPKotlinx_serialization_coreSerialDescriptor, SQLSKMPKotlinx_serialization_coreSerializationStrategy, SQLSKMPKotlinx_serialization_coreDecoder, SQLSKMPKotlinx_serialization_coreDeserializationStrategy, SQLSKMPKotlinx_serialization_coreCompositeEncoder, SQLSKMPKotlinAnnotation, SQLSKMPKotlinx_serialization_coreCompositeDecoder, SQLSKMPKotlinx_serialization_coreSerializersModuleCollector, SQLSKMPKotlinKClass, SQLSKMPKotlinKDeclarationContainer, SQLSKMPKotlinKAnnotatedElement, SQLSKMPKotlinKClassifier;

NS_ASSUME_NONNULL_BEGIN
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wunknown-warning-option"
#pragma clang diagnostic ignored "-Wincompatible-property-type"
#pragma clang diagnostic ignored "-Wnullability"

#pragma push_macro("_Nullable_result")
#if !__has_feature(nullability_nullable_result)
#undef _Nullable_result
#define _Nullable_result _Nullable
#endif

__attribute__((swift_name("KotlinBase")))
@interface SQLSKMPBase : NSObject
- (instancetype)init __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (void)initialize __attribute__((objc_requires_super));
@end

@interface SQLSKMPBase (SQLSKMPBaseCopying) <NSCopying>
@end

__attribute__((swift_name("KotlinMutableSet")))
@interface SQLSKMPMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end

__attribute__((swift_name("KotlinMutableDictionary")))
@interface SQLSKMPMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end

@interface NSError (NSErrorSQLSKMPKotlinException)
@property (readonly) id _Nullable kotlinException;
@end

__attribute__((swift_name("KotlinNumber")))
@interface SQLSKMPNumber : NSNumber
- (instancetype)initWithChar:(char)value __attribute__((unavailable));
- (instancetype)initWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
- (instancetype)initWithShort:(short)value __attribute__((unavailable));
- (instancetype)initWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
- (instancetype)initWithInt:(int)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
- (instancetype)initWithLong:(long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
- (instancetype)initWithLongLong:(long long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
- (instancetype)initWithFloat:(float)value __attribute__((unavailable));
- (instancetype)initWithDouble:(double)value __attribute__((unavailable));
- (instancetype)initWithBool:(BOOL)value __attribute__((unavailable));
- (instancetype)initWithInteger:(NSInteger)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
+ (instancetype)numberWithChar:(char)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
+ (instancetype)numberWithShort:(short)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
+ (instancetype)numberWithInt:(int)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
+ (instancetype)numberWithLong:(long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
+ (instancetype)numberWithLongLong:(long long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
+ (instancetype)numberWithFloat:(float)value __attribute__((unavailable));
+ (instancetype)numberWithDouble:(double)value __attribute__((unavailable));
+ (instancetype)numberWithBool:(BOOL)value __attribute__((unavailable));
+ (instancetype)numberWithInteger:(NSInteger)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
@end

__attribute__((swift_name("KotlinByte")))
@interface SQLSKMPByte : SQLSKMPNumber
- (instancetype)initWithChar:(char)value;
+ (instancetype)numberWithChar:(char)value;
@end

__attribute__((swift_name("KotlinUByte")))
@interface SQLSKMPUByte : SQLSKMPNumber
- (instancetype)initWithUnsignedChar:(unsigned char)value;
+ (instancetype)numberWithUnsignedChar:(unsigned char)value;
@end

__attribute__((swift_name("KotlinShort")))
@interface SQLSKMPShort : SQLSKMPNumber
- (instancetype)initWithShort:(short)value;
+ (instancetype)numberWithShort:(short)value;
@end

__attribute__((swift_name("KotlinUShort")))
@interface SQLSKMPUShort : SQLSKMPNumber
- (instancetype)initWithUnsignedShort:(unsigned short)value;
+ (instancetype)numberWithUnsignedShort:(unsigned short)value;
@end

__attribute__((swift_name("KotlinInt")))
@interface SQLSKMPInt : SQLSKMPNumber
- (instancetype)initWithInt:(int)value;
+ (instancetype)numberWithInt:(int)value;
@end

__attribute__((swift_name("KotlinUInt")))
@interface SQLSKMPUInt : SQLSKMPNumber
- (instancetype)initWithUnsignedInt:(unsigned int)value;
+ (instancetype)numberWithUnsignedInt:(unsigned int)value;
@end

__attribute__((swift_name("KotlinLong")))
@interface SQLSKMPLong : SQLSKMPNumber
- (instancetype)initWithLongLong:(long long)value;
+ (instancetype)numberWithLongLong:(long long)value;
@end

__attribute__((swift_name("KotlinULong")))
@interface SQLSKMPULong : SQLSKMPNumber
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value;
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value;
@end

__attribute__((swift_name("KotlinFloat")))
@interface SQLSKMPFloat : SQLSKMPNumber
- (instancetype)initWithFloat:(float)value;
+ (instancetype)numberWithFloat:(float)value;
@end

__attribute__((swift_name("KotlinDouble")))
@interface SQLSKMPDouble : SQLSKMPNumber
- (instancetype)initWithDouble:(double)value;
+ (instancetype)numberWithDouble:(double)value;
@end

__attribute__((swift_name("KotlinBoolean")))
@interface SQLSKMPBoolean : SQLSKMPNumber
- (instancetype)initWithBool:(BOOL)value;
+ (instancetype)numberWithBool:(BOOL)value;
@end

__attribute__((swift_name("KotlinComparable")))
@protocol SQLSKMPKotlinComparable
@required
- (int32_t)compareToOther:(id _Nullable)other __attribute__((swift_name("compareTo(other:)")));
@end

__attribute__((swift_name("KotlinEnum")))
@interface SQLSKMPKotlinEnum<E> : SQLSKMPBase <SQLSKMPKotlinComparable>
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) SQLSKMPKotlinEnumCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)compareToOther:(E)other __attribute__((swift_name("compareTo(other:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) int32_t ordinal __attribute__((swift_name("ordinal")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DatabaseFileType")))
@interface SQLSKMPDatabaseFileType : SQLSKMPKotlinEnum<SQLSKMPDatabaseFileType *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) SQLSKMPDatabaseFileType *main __attribute__((swift_name("main")));
@property (class, readonly) SQLSKMPDatabaseFileType *wal __attribute__((swift_name("wal")));
@property (class, readonly) SQLSKMPDatabaseFileType *index __attribute__((swift_name("index")));
+ (SQLSKMPKotlinArray<SQLSKMPDatabaseFileType *> *)values __attribute__((swift_name("values()")));
@property (class, readonly) NSArray<SQLSKMPDatabaseFileType *> *entries __attribute__((swift_name("entries")));
@end

__attribute__((swift_name("DatabaseFiles")))
@protocol SQLSKMPDatabaseFiles
@required
- (BOOL)delete __attribute__((swift_name("delete()")));
- (NSString *)dirPath __attribute__((swift_name("dirPath()")));
- (NSString *)pathType:(SQLSKMPDatabaseFileType *)type __attribute__((swift_name("path(type:)")));
- (SQLSKMPLong * _Nullable)sizeType:(SQLSKMPDatabaseFileType *)type __attribute__((swift_name("size(type:)")));
@end

__attribute__((swift_name("SQLiteStorageKMP")))
@protocol SQLSKMPSQLiteStorageKMP
@required
- (void)closeConnection __attribute__((swift_name("closeConnection()")));
@property (readonly) id<SQLSKMPDatabaseFiles> files __attribute__((swift_name("files")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SQLiteStorageFactoryKMP")))
@interface SQLSKMPSQLiteStorageFactoryKMP : SQLSKMPBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (id<SQLSKMPSQLiteStorageKMP>)createDbName:(NSString *)dbName __attribute__((swift_name("create(dbName:)")));
@end

__attribute__((swift_name("RuntimeTransacterBase")))
@protocol SQLSKMPRuntimeTransacterBase
@required
@end

__attribute__((swift_name("RuntimeTransacter")))
@protocol SQLSKMPRuntimeTransacter <SQLSKMPRuntimeTransacterBase>
@required
- (void)transactionNoEnclosing:(BOOL)noEnclosing body:(void (^)(id<SQLSKMPRuntimeTransactionWithoutReturn>))body __attribute__((swift_name("transaction(noEnclosing:body:)")));
- (id _Nullable)transactionWithResultNoEnclosing:(BOOL)noEnclosing bodyWithReturn:(id _Nullable (^)(id<SQLSKMPRuntimeTransactionWithReturn>))bodyWithReturn __attribute__((swift_name("transactionWithResult(noEnclosing:bodyWithReturn:)")));
@end

__attribute__((swift_name("AsyncStorageDB")))
@protocol SQLSKMPAsyncStorageDB <SQLSKMPRuntimeTransacter>
@required
@property (readonly) SQLSKMPAsync_storage_entriesQueries *async_storage_entriesQueries __attribute__((swift_name("async_storage_entriesQueries")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AsyncStorageDBCompanion")))
@interface SQLSKMPAsyncStorageDBCompanion : SQLSKMPBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SQLSKMPAsyncStorageDBCompanion *shared __attribute__((swift_name("shared")));
- (id<SQLSKMPAsyncStorageDB>)invokeDriver:(id<SQLSKMPRuntimeSqlDriver>)driver __attribute__((swift_name("invoke(driver:)")));
@property (readonly) id<SQLSKMPRuntimeSqlSchema> Schema __attribute__((swift_name("Schema")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Async_storage_entries")))
@interface SQLSKMPAsync_storage_entries : SQLSKMPBase
- (instancetype)initWithKey:(NSString *)key value_:(NSString * _Nullable)value_ __attribute__((swift_name("init(key:value_:)"))) __attribute__((objc_designated_initializer));
- (SQLSKMPAsync_storage_entries *)doCopyKey:(NSString *)key value_:(NSString * _Nullable)value_ __attribute__((swift_name("doCopy(key:value_:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *key __attribute__((swift_name("key")));
@property (readonly) NSString * _Nullable value_ __attribute__((swift_name("value_")));
@end

__attribute__((swift_name("RuntimeBaseTransacterImpl")))
@interface SQLSKMPRuntimeBaseTransacterImpl : SQLSKMPBase
- (instancetype)initWithDriver:(id<SQLSKMPRuntimeSqlDriver>)driver __attribute__((swift_name("init(driver:)"))) __attribute__((objc_designated_initializer));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (NSString *)createArgumentsCount:(int32_t)count __attribute__((swift_name("createArguments(count:)")));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (void)notifyQueriesIdentifier:(int32_t)identifier tableProvider:(void (^)(SQLSKMPKotlinUnit *(^)(NSString *)))tableProvider __attribute__((swift_name("notifyQueries(identifier:tableProvider:)")));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (id _Nullable)postTransactionCleanupTransaction:(SQLSKMPRuntimeTransacterTransaction *)transaction enclosing:(SQLSKMPRuntimeTransacterTransaction * _Nullable)enclosing thrownException:(SQLSKMPKotlinThrowable * _Nullable)thrownException returnValue:(id _Nullable)returnValue __attribute__((swift_name("postTransactionCleanup(transaction:enclosing:thrownException:returnValue:)")));

/**
 * @note This property has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
@property (readonly) id<SQLSKMPRuntimeSqlDriver> driver __attribute__((swift_name("driver")));
@end

__attribute__((swift_name("RuntimeTransacterImpl")))
@interface SQLSKMPRuntimeTransacterImpl : SQLSKMPRuntimeBaseTransacterImpl <SQLSKMPRuntimeTransacter>
- (instancetype)initWithDriver:(id<SQLSKMPRuntimeSqlDriver>)driver __attribute__((swift_name("init(driver:)"))) __attribute__((objc_designated_initializer));
- (void)transactionNoEnclosing:(BOOL)noEnclosing body:(void (^)(id<SQLSKMPRuntimeTransactionWithoutReturn>))body __attribute__((swift_name("transaction(noEnclosing:body:)")));
- (id _Nullable)transactionWithResultNoEnclosing:(BOOL)noEnclosing bodyWithReturn:(id _Nullable (^)(id<SQLSKMPRuntimeTransactionWithReturn>))bodyWithReturn __attribute__((swift_name("transactionWithResult(noEnclosing:bodyWithReturn:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Async_storage_entriesQueries")))
@interface SQLSKMPAsync_storage_entriesQueries : SQLSKMPRuntimeTransacterImpl
- (instancetype)initWithDriver:(id<SQLSKMPRuntimeSqlDriver>)driver __attribute__((swift_name("init(driver:)"))) __attribute__((objc_designated_initializer));
- (void)deleteAll __attribute__((swift_name("deleteAll()")));
- (void)deleteManyKeys:(id)keys __attribute__((swift_name("deleteMany(keys:)")));
- (void)deleteOneKey:(NSString *)key __attribute__((swift_name("deleteOne(key:)")));
- (SQLSKMPRuntimeQuery<NSString *> *)getAllKeys __attribute__((swift_name("getAllKeys()")));
- (SQLSKMPRuntimeQuery<SQLSKMPAsync_storage_entries *> *)getManyKey:(id)key __attribute__((swift_name("getMany(key:)")));
- (SQLSKMPRuntimeQuery<id> *)getManyKey:(id)key mapper:(id (^)(NSString *, NSString * _Nullable))mapper __attribute__((swift_name("getMany(key:mapper:)")));
- (SQLSKMPRuntimeQuery<SQLSKMPAsync_storage_entries *> *)getOneKey:(NSString *)key __attribute__((swift_name("getOne(key:)")));
- (SQLSKMPRuntimeQuery<id> *)getOneKey:(NSString *)key mapper:(id (^)(NSString *, NSString * _Nullable))mapper __attribute__((swift_name("getOne(key:mapper:)")));
- (void)insertOneKey:(NSString *)key value:(NSString * _Nullable)value __attribute__((swift_name("insertOne(key:value:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("EntryKMP")))
@interface SQLSKMPEntryKMP : SQLSKMPBase
- (instancetype)initWithKey:(NSString *)key value:(NSString * _Nullable)value __attribute__((swift_name("init(key:value:)"))) __attribute__((objc_designated_initializer));
- (SQLSKMPEntryKMP *)doCopyKey:(NSString *)key value:(NSString * _Nullable)value __attribute__((swift_name("doCopy(key:value:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *key __attribute__((swift_name("key")));
@property (readonly) NSString * _Nullable value __attribute__((swift_name("value")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable(with=NormalClass(value=kotlinx/serialization/json/JsonElementSerializer))
*/
__attribute__((swift_name("Kotlinx_serialization_jsonJsonElement")))
@interface SQLSKMPKotlinx_serialization_jsonJsonElement : SQLSKMPBase
@property (class, readonly, getter=companion) SQLSKMPKotlinx_serialization_jsonJsonElementCompanion *companion __attribute__((swift_name("companion")));
@end

@interface SQLSKMPKotlinx_serialization_jsonJsonElement (Extensions)
- (BOOL)isArray __attribute__((swift_name("isArray()")));
- (BOOL)isObject __attribute__((swift_name("isObject()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DispatcherIOKt")))
@interface SQLSKMPDispatcherIOKt : SQLSKMPBase
@property (class, readonly) SQLSKMPKotlinx_coroutines_coreCoroutineDispatcher *DispatcherIO __attribute__((swift_name("DispatcherIO")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("JsonStringKt")))
@interface SQLSKMPJsonStringKt : SQLSKMPBase
+ (BOOL)isValidJson:(NSString *)receiver __attribute__((swift_name("isValidJson(_:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SQLiteStorageKt")))
@interface SQLSKMPSQLiteStorageKt : SQLSKMPBase
+ (id<SQLSKMPSQLiteStorageKMP>)SQLiteStorageDriver:(id<SQLSKMPRuntimeSqlDriver>)driver dbFile:(id<SQLSKMPDatabaseFiles>)dbFile __attribute__((swift_name("SQLiteStorage(driver:dbFile:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SQLiteStorageNativeKt")))
@interface SQLSKMPSQLiteStorageNativeKt : SQLSKMPBase
+ (SQLSKMPKotlinUnit *(^(^)(SQLSKMPKotlinUnit *(^)(SQLSKMPKotlinUnit *, SQLSKMPKotlinUnit *), SQLSKMPKotlinUnit *(^)(NSError *, SQLSKMPKotlinUnit *), SQLSKMPKotlinUnit *(^)(NSError *, SQLSKMPKotlinUnit *)))(void))clear:(id<SQLSKMPSQLiteStorageKMP>)receiver __attribute__((swift_name("clear(_:)")));
+ (SQLSKMPKotlinUnit *(^(^)(SQLSKMPKotlinUnit *(^)(NSArray<SQLSKMPEntryKMP *> *, SQLSKMPKotlinUnit *), SQLSKMPKotlinUnit *(^)(NSError *, SQLSKMPKotlinUnit *), SQLSKMPKotlinUnit *(^)(NSError *, SQLSKMPKotlinUnit *)))(void))mergeMany:(id<SQLSKMPSQLiteStorageKMP>)receiver entries:(NSArray<SQLSKMPEntryKMP *> *)entries __attribute__((swift_name("mergeMany(_:entries:)")));
+ (SQLSKMPKotlinUnit *(^(^)(SQLSKMPKotlinUnit *(^)(SQLSKMPEntryKMP *, SQLSKMPKotlinUnit *), SQLSKMPKotlinUnit *(^)(NSError *, SQLSKMPKotlinUnit *), SQLSKMPKotlinUnit *(^)(NSError *, SQLSKMPKotlinUnit *)))(void))merge:(id<SQLSKMPSQLiteStorageKMP>)receiver entry:(SQLSKMPEntryKMP *)entry __attribute__((swift_name("merge(_:entry:)")));
+ (SQLSKMPKotlinUnit *(^(^)(SQLSKMPKotlinUnit *(^)(NSArray<SQLSKMPEntryKMP *> *, SQLSKMPKotlinUnit *(^)(void), SQLSKMPKotlinUnit *), SQLSKMPKotlinUnit *(^)(NSError * _Nullable, SQLSKMPKotlinUnit *), SQLSKMPKotlinUnit *(^)(NSError *, SQLSKMPKotlinUnit *)))(void))readAsFlow:(id<SQLSKMPSQLiteStorageKMP>)receiver keys:(NSArray<NSString *> *)keys __attribute__((swift_name("readAsFlow(_:keys:)")));
+ (SQLSKMPKotlinUnit *(^(^)(SQLSKMPKotlinUnit *(^)(NSArray<NSString *> *, SQLSKMPKotlinUnit *(^)(void), SQLSKMPKotlinUnit *), SQLSKMPKotlinUnit *(^)(NSError * _Nullable, SQLSKMPKotlinUnit *), SQLSKMPKotlinUnit *(^)(NSError *, SQLSKMPKotlinUnit *)))(void))readKeysAsFlow:(id<SQLSKMPSQLiteStorageKMP>)receiver __attribute__((swift_name("readKeysAsFlow(_:)")));
+ (SQLSKMPKotlinUnit *(^(^)(SQLSKMPKotlinUnit *(^)(NSArray<NSString *> *, SQLSKMPKotlinUnit *), SQLSKMPKotlinUnit *(^)(NSError *, SQLSKMPKotlinUnit *), SQLSKMPKotlinUnit *(^)(NSError *, SQLSKMPKotlinUnit *)))(void))readKeys:(id<SQLSKMPSQLiteStorageKMP>)receiver __attribute__((swift_name("readKeys(_:)")));
+ (SQLSKMPKotlinUnit *(^(^)(SQLSKMPKotlinUnit *(^)(NSArray<SQLSKMPEntryKMP *> *, SQLSKMPKotlinUnit *), SQLSKMPKotlinUnit *(^)(NSError *, SQLSKMPKotlinUnit *), SQLSKMPKotlinUnit *(^)(NSError *, SQLSKMPKotlinUnit *)))(void))readMany:(id<SQLSKMPSQLiteStorageKMP>)receiver keys:(NSArray<NSString *> *)keys __attribute__((swift_name("readMany(_:keys:)")));
+ (SQLSKMPKotlinUnit *(^(^)(SQLSKMPKotlinUnit *(^)(SQLSKMPEntryKMP *, SQLSKMPKotlinUnit *), SQLSKMPKotlinUnit *(^)(NSError *, SQLSKMPKotlinUnit *), SQLSKMPKotlinUnit *(^)(NSError *, SQLSKMPKotlinUnit *)))(void))read:(id<SQLSKMPSQLiteStorageKMP>)receiver key:(NSString *)key __attribute__((swift_name("read(_:key:)")));
+ (SQLSKMPKotlinUnit *(^(^)(SQLSKMPKotlinUnit *(^)(SQLSKMPKotlinUnit *, SQLSKMPKotlinUnit *), SQLSKMPKotlinUnit *(^)(NSError *, SQLSKMPKotlinUnit *), SQLSKMPKotlinUnit *(^)(NSError *, SQLSKMPKotlinUnit *)))(void))removeMany:(id<SQLSKMPSQLiteStorageKMP>)receiver keys:(NSArray<NSString *> *)keys __attribute__((swift_name("removeMany(_:keys:)")));
+ (SQLSKMPKotlinUnit *(^(^)(SQLSKMPKotlinUnit *(^)(SQLSKMPKotlinUnit *, SQLSKMPKotlinUnit *), SQLSKMPKotlinUnit *(^)(NSError *, SQLSKMPKotlinUnit *), SQLSKMPKotlinUnit *(^)(NSError *, SQLSKMPKotlinUnit *)))(void))remove:(id<SQLSKMPSQLiteStorageKMP>)receiver key:(NSString *)key __attribute__((swift_name("remove(_:key:)")));
+ (SQLSKMPKotlinUnit *(^(^)(SQLSKMPKotlinUnit *(^)(SQLSKMPKotlinUnit *, SQLSKMPKotlinUnit *), SQLSKMPKotlinUnit *(^)(NSError *, SQLSKMPKotlinUnit *), SQLSKMPKotlinUnit *(^)(NSError *, SQLSKMPKotlinUnit *)))(void))writeMany:(id<SQLSKMPSQLiteStorageKMP>)receiver entries:(NSArray<SQLSKMPEntryKMP *> *)entries __attribute__((swift_name("writeMany(_:entries:)")));
+ (SQLSKMPKotlinUnit *(^(^)(SQLSKMPKotlinUnit *(^)(SQLSKMPKotlinUnit *, SQLSKMPKotlinUnit *), SQLSKMPKotlinUnit *(^)(NSError *, SQLSKMPKotlinUnit *), SQLSKMPKotlinUnit *(^)(NSError *, SQLSKMPKotlinUnit *)))(void))write:(id<SQLSKMPSQLiteStorageKMP>)receiver entry:(SQLSKMPEntryKMP *)entry __attribute__((swift_name("write(_:entry:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SqlDriverKt")))
@interface SQLSKMPSqlDriverKt : SQLSKMPBase
+ (void)executePragmaOptimize:(id<SQLSKMPRuntimeSqlDriver>)receiver __attribute__((swift_name("executePragmaOptimize(_:)")));
+ (BOOL)executePragmaWalCheckpoint:(id<SQLSKMPRuntimeSqlDriver>)receiver __attribute__((swift_name("executePragmaWalCheckpoint(_:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinEnumCompanion")))
@interface SQLSKMPKotlinEnumCompanion : SQLSKMPBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SQLSKMPKotlinEnumCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinArray")))
@interface SQLSKMPKotlinArray<T> : SQLSKMPBase
+ (instancetype)arrayWithSize:(int32_t)size init:(T _Nullable (^)(SQLSKMPInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (T _Nullable)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (id<SQLSKMPKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(T _Nullable)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end

__attribute__((swift_name("RuntimeTransactionCallbacks")))
@protocol SQLSKMPRuntimeTransactionCallbacks
@required
- (void)afterCommitFunction:(void (^)(void))function __attribute__((swift_name("afterCommit(function:)")));
- (void)afterRollbackFunction:(void (^)(void))function __attribute__((swift_name("afterRollback(function:)")));
@end

__attribute__((swift_name("RuntimeTransactionWithoutReturn")))
@protocol SQLSKMPRuntimeTransactionWithoutReturn <SQLSKMPRuntimeTransactionCallbacks>
@required
- (void)rollback __attribute__((swift_name("rollback()")));
- (void)transactionBody:(void (^)(id<SQLSKMPRuntimeTransactionWithoutReturn>))body __attribute__((swift_name("transaction(body:)")));
@end

__attribute__((swift_name("RuntimeTransactionWithReturn")))
@protocol SQLSKMPRuntimeTransactionWithReturn <SQLSKMPRuntimeTransactionCallbacks>
@required
- (void)rollbackReturnValue:(id _Nullable)returnValue __attribute__((swift_name("rollback(returnValue:)")));
- (id _Nullable)transactionBody_:(id _Nullable (^)(id<SQLSKMPRuntimeTransactionWithReturn>))body __attribute__((swift_name("transaction(body_:)")));
@end

__attribute__((swift_name("RuntimeCloseable")))
@protocol SQLSKMPRuntimeCloseable
@required
- (void)close __attribute__((swift_name("close()")));
@end

__attribute__((swift_name("RuntimeSqlDriver")))
@protocol SQLSKMPRuntimeSqlDriver <SQLSKMPRuntimeCloseable>
@required
- (void)addListenerQueryKeys:(SQLSKMPKotlinArray<NSString *> *)queryKeys listener:(id<SQLSKMPRuntimeQueryListener>)listener __attribute__((swift_name("addListener(queryKeys:listener:)")));
- (SQLSKMPRuntimeTransacterTransaction * _Nullable)currentTransaction __attribute__((swift_name("currentTransaction()")));
- (id<SQLSKMPRuntimeQueryResult>)executeIdentifier:(SQLSKMPInt * _Nullable)identifier sql:(NSString *)sql parameters:(int32_t)parameters binders:(void (^ _Nullable)(id<SQLSKMPRuntimeSqlPreparedStatement>))binders __attribute__((swift_name("execute(identifier:sql:parameters:binders:)")));
- (id<SQLSKMPRuntimeQueryResult>)executeQueryIdentifier:(SQLSKMPInt * _Nullable)identifier sql:(NSString *)sql mapper:(id<SQLSKMPRuntimeQueryResult> (^)(id<SQLSKMPRuntimeSqlCursor>))mapper parameters:(int32_t)parameters binders:(void (^ _Nullable)(id<SQLSKMPRuntimeSqlPreparedStatement>))binders __attribute__((swift_name("executeQuery(identifier:sql:mapper:parameters:binders:)")));
- (id<SQLSKMPRuntimeQueryResult>)doNewTransaction __attribute__((swift_name("doNewTransaction()")));
- (void)notifyListenersQueryKeys:(SQLSKMPKotlinArray<NSString *> *)queryKeys __attribute__((swift_name("notifyListeners(queryKeys:)")));
- (void)removeListenerQueryKeys:(SQLSKMPKotlinArray<NSString *> *)queryKeys listener:(id<SQLSKMPRuntimeQueryListener>)listener __attribute__((swift_name("removeListener(queryKeys:listener:)")));
@end

__attribute__((swift_name("RuntimeSqlSchema")))
@protocol SQLSKMPRuntimeSqlSchema
@required
- (id<SQLSKMPRuntimeQueryResult>)createDriver:(id<SQLSKMPRuntimeSqlDriver>)driver __attribute__((swift_name("create(driver:)")));
- (id<SQLSKMPRuntimeQueryResult>)migrateDriver:(id<SQLSKMPRuntimeSqlDriver>)driver oldVersion:(int64_t)oldVersion newVersion:(int64_t)newVersion callbacks:(SQLSKMPKotlinArray<SQLSKMPRuntimeAfterVersion *> *)callbacks __attribute__((swift_name("migrate(driver:oldVersion:newVersion:callbacks:)")));
@property (readonly) int64_t version __attribute__((swift_name("version")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinUnit")))
@interface SQLSKMPKotlinUnit : SQLSKMPBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)unit __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SQLSKMPKotlinUnit *shared __attribute__((swift_name("shared")));
- (NSString *)description __attribute__((swift_name("description()")));
@end

__attribute__((swift_name("RuntimeTransacterTransaction")))
@interface SQLSKMPRuntimeTransacterTransaction : SQLSKMPBase <SQLSKMPRuntimeTransactionCallbacks>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)afterCommitFunction:(void (^)(void))function __attribute__((swift_name("afterCommit(function:)")));
- (void)afterRollbackFunction:(void (^)(void))function __attribute__((swift_name("afterRollback(function:)")));

/**
 * @note This method has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
- (id<SQLSKMPRuntimeQueryResult>)endTransactionSuccessful:(BOOL)successful __attribute__((swift_name("endTransaction(successful:)")));

/**
 * @note This property has protected visibility in Kotlin source and is intended only for use by subclasses.
*/
@property (readonly) SQLSKMPRuntimeTransacterTransaction * _Nullable enclosingTransaction __attribute__((swift_name("enclosingTransaction")));
@end

__attribute__((swift_name("KotlinThrowable")))
@interface SQLSKMPKotlinThrowable : SQLSKMPBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(SQLSKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(SQLSKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));

/**
 * @note annotations
 *   kotlin.experimental.ExperimentalNativeApi
*/
- (SQLSKMPKotlinArray<NSString *> *)getStackTrace __attribute__((swift_name("getStackTrace()")));
- (void)printStackTrace __attribute__((swift_name("printStackTrace()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) SQLSKMPKotlinThrowable * _Nullable cause __attribute__((swift_name("cause")));
@property (readonly) NSString * _Nullable message __attribute__((swift_name("message")));
- (NSError *)asError __attribute__((swift_name("asError()")));
@end

__attribute__((swift_name("RuntimeExecutableQuery")))
@interface SQLSKMPRuntimeExecutableQuery<__covariant RowType> : SQLSKMPBase
- (instancetype)initWithMapper:(RowType (^)(id<SQLSKMPRuntimeSqlCursor>))mapper __attribute__((swift_name("init(mapper:)"))) __attribute__((objc_designated_initializer));
- (id<SQLSKMPRuntimeQueryResult>)executeMapper:(id<SQLSKMPRuntimeQueryResult> (^)(id<SQLSKMPRuntimeSqlCursor>))mapper __attribute__((swift_name("execute(mapper:)")));
- (NSArray<RowType> *)executeAsList __attribute__((swift_name("executeAsList()")));
- (RowType)executeAsOne __attribute__((swift_name("executeAsOne()")));
- (RowType _Nullable)executeAsOneOrNull __attribute__((swift_name("executeAsOneOrNull()")));
@property (readonly) RowType (^mapper)(id<SQLSKMPRuntimeSqlCursor>) __attribute__((swift_name("mapper")));
@end

__attribute__((swift_name("RuntimeQuery")))
@interface SQLSKMPRuntimeQuery<__covariant RowType> : SQLSKMPRuntimeExecutableQuery<RowType>
- (instancetype)initWithMapper:(RowType (^)(id<SQLSKMPRuntimeSqlCursor>))mapper __attribute__((swift_name("init(mapper:)"))) __attribute__((objc_designated_initializer));
- (void)addListenerListener:(id<SQLSKMPRuntimeQueryListener>)listener __attribute__((swift_name("addListener(listener:)")));
- (void)removeListenerListener:(id<SQLSKMPRuntimeQueryListener>)listener __attribute__((swift_name("removeListener(listener:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_serialization_jsonJsonElement.Companion")))
@interface SQLSKMPKotlinx_serialization_jsonJsonElementCompanion : SQLSKMPBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SQLSKMPKotlinx_serialization_jsonJsonElementCompanion *shared __attribute__((swift_name("shared")));
- (id<SQLSKMPKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.3")
*/
__attribute__((swift_name("KotlinCoroutineContext")))
@protocol SQLSKMPKotlinCoroutineContext
@required
- (id _Nullable)foldInitial:(id _Nullable)initial operation:(id _Nullable (^)(id _Nullable, id<SQLSKMPKotlinCoroutineContextElement>))operation __attribute__((swift_name("fold(initial:operation:)")));
- (id<SQLSKMPKotlinCoroutineContextElement> _Nullable)getKey:(id<SQLSKMPKotlinCoroutineContextKey>)key __attribute__((swift_name("get(key:)")));
- (id<SQLSKMPKotlinCoroutineContext>)minusKeyKey:(id<SQLSKMPKotlinCoroutineContextKey>)key __attribute__((swift_name("minusKey(key:)")));
- (id<SQLSKMPKotlinCoroutineContext>)plusContext:(id<SQLSKMPKotlinCoroutineContext>)context __attribute__((swift_name("plus(context:)")));
@end

__attribute__((swift_name("KotlinCoroutineContextElement")))
@protocol SQLSKMPKotlinCoroutineContextElement <SQLSKMPKotlinCoroutineContext>
@required
@property (readonly) id<SQLSKMPKotlinCoroutineContextKey> key __attribute__((swift_name("key")));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.3")
*/
__attribute__((swift_name("KotlinAbstractCoroutineContextElement")))
@interface SQLSKMPKotlinAbstractCoroutineContextElement : SQLSKMPBase <SQLSKMPKotlinCoroutineContextElement>
- (instancetype)initWithKey:(id<SQLSKMPKotlinCoroutineContextKey>)key __attribute__((swift_name("init(key:)"))) __attribute__((objc_designated_initializer));
@property (readonly) id<SQLSKMPKotlinCoroutineContextKey> key __attribute__((swift_name("key")));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.3")
*/
__attribute__((swift_name("KotlinContinuationInterceptor")))
@protocol SQLSKMPKotlinContinuationInterceptor <SQLSKMPKotlinCoroutineContextElement>
@required
- (id<SQLSKMPKotlinContinuation>)interceptContinuationContinuation:(id<SQLSKMPKotlinContinuation>)continuation __attribute__((swift_name("interceptContinuation(continuation:)")));
- (void)releaseInterceptedContinuationContinuation:(id<SQLSKMPKotlinContinuation>)continuation __attribute__((swift_name("releaseInterceptedContinuation(continuation:)")));
@end

__attribute__((swift_name("Kotlinx_coroutines_coreCoroutineDispatcher")))
@interface SQLSKMPKotlinx_coroutines_coreCoroutineDispatcher : SQLSKMPKotlinAbstractCoroutineContextElement <SQLSKMPKotlinContinuationInterceptor>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithKey:(id<SQLSKMPKotlinCoroutineContextKey>)key __attribute__((swift_name("init(key:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) SQLSKMPKotlinx_coroutines_coreCoroutineDispatcherKey *companion __attribute__((swift_name("companion")));
- (void)dispatchContext:(id<SQLSKMPKotlinCoroutineContext>)context block:(id<SQLSKMPKotlinx_coroutines_coreRunnable>)block __attribute__((swift_name("dispatch(context:block:)")));
- (void)dispatchYieldContext:(id<SQLSKMPKotlinCoroutineContext>)context block:(id<SQLSKMPKotlinx_coroutines_coreRunnable>)block __attribute__((swift_name("dispatchYield(context:block:)")));
- (id<SQLSKMPKotlinContinuation>)interceptContinuationContinuation:(id<SQLSKMPKotlinContinuation>)continuation __attribute__((swift_name("interceptContinuation(continuation:)")));
- (BOOL)isDispatchNeededContext:(id<SQLSKMPKotlinCoroutineContext>)context __attribute__((swift_name("isDispatchNeeded(context:)")));

/**
 * @note annotations
 *   kotlinx.coroutines.ExperimentalCoroutinesApi
*/
- (SQLSKMPKotlinx_coroutines_coreCoroutineDispatcher *)limitedParallelismParallelism:(int32_t)parallelism __attribute__((swift_name("limitedParallelism(parallelism:)")));
- (SQLSKMPKotlinx_coroutines_coreCoroutineDispatcher *)plusOther:(SQLSKMPKotlinx_coroutines_coreCoroutineDispatcher *)other __attribute__((swift_name("plus(other:)"))) __attribute__((unavailable("Operator '+' on two CoroutineDispatcher objects is meaningless. CoroutineDispatcher is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The dispatcher to the right of `+` just replaces the dispatcher to the left.")));
- (void)releaseInterceptedContinuationContinuation:(id<SQLSKMPKotlinContinuation>)continuation __attribute__((swift_name("releaseInterceptedContinuation(continuation:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@end

__attribute__((swift_name("KotlinIterator")))
@protocol SQLSKMPKotlinIterator
@required
- (BOOL)hasNext __attribute__((swift_name("hasNext()")));
- (id _Nullable)next __attribute__((swift_name("next()")));
@end

__attribute__((swift_name("RuntimeQueryListener")))
@protocol SQLSKMPRuntimeQueryListener
@required
- (void)queryResultsChanged __attribute__((swift_name("queryResultsChanged()")));
@end

__attribute__((swift_name("RuntimeQueryResult")))
@protocol SQLSKMPRuntimeQueryResult
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)awaitWithCompletionHandler:(void (^)(id _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("await(completionHandler:)")));
@property (readonly) id _Nullable value __attribute__((swift_name("value")));
@end

__attribute__((swift_name("RuntimeSqlPreparedStatement")))
@protocol SQLSKMPRuntimeSqlPreparedStatement
@required
- (void)bindBooleanIndex:(int32_t)index boolean:(SQLSKMPBoolean * _Nullable)boolean __attribute__((swift_name("bindBoolean(index:boolean:)")));
- (void)bindBytesIndex:(int32_t)index bytes:(SQLSKMPKotlinByteArray * _Nullable)bytes __attribute__((swift_name("bindBytes(index:bytes:)")));
- (void)bindDoubleIndex:(int32_t)index double:(SQLSKMPDouble * _Nullable)double_ __attribute__((swift_name("bindDouble(index:double:)")));
- (void)bindLongIndex:(int32_t)index long:(SQLSKMPLong * _Nullable)long_ __attribute__((swift_name("bindLong(index:long:)")));
- (void)bindStringIndex:(int32_t)index string:(NSString * _Nullable)string __attribute__((swift_name("bindString(index:string:)")));
@end

__attribute__((swift_name("RuntimeSqlCursor")))
@protocol SQLSKMPRuntimeSqlCursor
@required
- (SQLSKMPBoolean * _Nullable)getBooleanIndex:(int32_t)index __attribute__((swift_name("getBoolean(index:)")));
- (SQLSKMPKotlinByteArray * _Nullable)getBytesIndex:(int32_t)index __attribute__((swift_name("getBytes(index:)")));
- (SQLSKMPDouble * _Nullable)getDoubleIndex:(int32_t)index __attribute__((swift_name("getDouble(index:)")));
- (SQLSKMPLong * _Nullable)getLongIndex:(int32_t)index __attribute__((swift_name("getLong(index:)")));
- (NSString * _Nullable)getStringIndex:(int32_t)index __attribute__((swift_name("getString(index:)")));
- (id<SQLSKMPRuntimeQueryResult>)next __attribute__((swift_name("next()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RuntimeAfterVersion")))
@interface SQLSKMPRuntimeAfterVersion : SQLSKMPBase
- (instancetype)initWithAfterVersion:(int64_t)afterVersion block:(void (^)(id<SQLSKMPRuntimeSqlDriver>))block __attribute__((swift_name("init(afterVersion:block:)"))) __attribute__((objc_designated_initializer));
@property (readonly) int64_t afterVersion __attribute__((swift_name("afterVersion")));
@property (readonly) void (^block)(id<SQLSKMPRuntimeSqlDriver>) __attribute__((swift_name("block")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerializationStrategy")))
@protocol SQLSKMPKotlinx_serialization_coreSerializationStrategy
@required
- (void)serializeEncoder:(id<SQLSKMPKotlinx_serialization_coreEncoder>)encoder value:(id _Nullable)value __attribute__((swift_name("serialize(encoder:value:)")));
@property (readonly) id<SQLSKMPKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreDeserializationStrategy")))
@protocol SQLSKMPKotlinx_serialization_coreDeserializationStrategy
@required
- (id _Nullable)deserializeDecoder:(id<SQLSKMPKotlinx_serialization_coreDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
@property (readonly) id<SQLSKMPKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreKSerializer")))
@protocol SQLSKMPKotlinx_serialization_coreKSerializer <SQLSKMPKotlinx_serialization_coreSerializationStrategy, SQLSKMPKotlinx_serialization_coreDeserializationStrategy>
@required
@end

__attribute__((swift_name("KotlinCoroutineContextKey")))
@protocol SQLSKMPKotlinCoroutineContextKey
@required
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.3")
*/
__attribute__((swift_name("KotlinContinuation")))
@protocol SQLSKMPKotlinContinuation
@required
- (void)resumeWithResult:(id _Nullable)result __attribute__((swift_name("resumeWith(result:)")));
@property (readonly) id<SQLSKMPKotlinCoroutineContext> context __attribute__((swift_name("context")));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.3")
 *   kotlin.ExperimentalStdlibApi
*/
__attribute__((swift_name("KotlinAbstractCoroutineContextKey")))
@interface SQLSKMPKotlinAbstractCoroutineContextKey<B, E> : SQLSKMPBase <SQLSKMPKotlinCoroutineContextKey>
- (instancetype)initWithBaseKey:(id<SQLSKMPKotlinCoroutineContextKey>)baseKey safeCast:(E _Nullable (^)(id<SQLSKMPKotlinCoroutineContextElement>))safeCast __attribute__((swift_name("init(baseKey:safeCast:)"))) __attribute__((objc_designated_initializer));
@end


/**
 * @note annotations
 *   kotlin.ExperimentalStdlibApi
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_coroutines_coreCoroutineDispatcher.Key")))
@interface SQLSKMPKotlinx_coroutines_coreCoroutineDispatcherKey : SQLSKMPKotlinAbstractCoroutineContextKey<id<SQLSKMPKotlinContinuationInterceptor>, SQLSKMPKotlinx_coroutines_coreCoroutineDispatcher *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithBaseKey:(id<SQLSKMPKotlinCoroutineContextKey>)baseKey safeCast:(id<SQLSKMPKotlinCoroutineContextElement> _Nullable (^)(id<SQLSKMPKotlinCoroutineContextElement>))safeCast __attribute__((swift_name("init(baseKey:safeCast:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)key __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) SQLSKMPKotlinx_coroutines_coreCoroutineDispatcherKey *shared __attribute__((swift_name("shared")));
@end

__attribute__((swift_name("Kotlinx_coroutines_coreRunnable")))
@protocol SQLSKMPKotlinx_coroutines_coreRunnable
@required
- (void)run __attribute__((swift_name("run()")));
@end

__attribute__((swift_name("KotlinException")))
@interface SQLSKMPKotlinException : SQLSKMPKotlinThrowable
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(SQLSKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(SQLSKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("KotlinRuntimeException")))
@interface SQLSKMPKotlinRuntimeException : SQLSKMPKotlinException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(SQLSKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(SQLSKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("KotlinIllegalStateException")))
@interface SQLSKMPKotlinIllegalStateException : SQLSKMPKotlinRuntimeException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(SQLSKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(SQLSKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.4")
*/
__attribute__((swift_name("KotlinCancellationException")))
@interface SQLSKMPKotlinCancellationException : SQLSKMPKotlinIllegalStateException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(SQLSKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(SQLSKMPKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinByteArray")))
@interface SQLSKMPKotlinByteArray : SQLSKMPBase
+ (instancetype)arrayWithSize:(int32_t)size __attribute__((swift_name("init(size:)")));
+ (instancetype)arrayWithSize:(int32_t)size init:(SQLSKMPByte *(^)(SQLSKMPInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (int8_t)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (SQLSKMPKotlinByteIterator *)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(int8_t)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreEncoder")))
@protocol SQLSKMPKotlinx_serialization_coreEncoder
@required
- (id<SQLSKMPKotlinx_serialization_coreCompositeEncoder>)beginCollectionDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor collectionSize:(int32_t)collectionSize __attribute__((swift_name("beginCollection(descriptor:collectionSize:)")));
- (id<SQLSKMPKotlinx_serialization_coreCompositeEncoder>)beginStructureDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("beginStructure(descriptor:)")));
- (void)encodeBooleanValue:(BOOL)value __attribute__((swift_name("encodeBoolean(value:)")));
- (void)encodeByteValue:(int8_t)value __attribute__((swift_name("encodeByte(value:)")));
- (void)encodeCharValue:(unichar)value __attribute__((swift_name("encodeChar(value:)")));
- (void)encodeDoubleValue:(double)value __attribute__((swift_name("encodeDouble(value:)")));
- (void)encodeEnumEnumDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)enumDescriptor index:(int32_t)index __attribute__((swift_name("encodeEnum(enumDescriptor:index:)")));
- (void)encodeFloatValue:(float)value __attribute__((swift_name("encodeFloat(value:)")));
- (id<SQLSKMPKotlinx_serialization_coreEncoder>)encodeInlineDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("encodeInline(descriptor:)")));
- (void)encodeIntValue:(int32_t)value __attribute__((swift_name("encodeInt(value:)")));
- (void)encodeLongValue:(int64_t)value __attribute__((swift_name("encodeLong(value:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNotNullMark __attribute__((swift_name("encodeNotNullMark()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNull __attribute__((swift_name("encodeNull()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNullableSerializableValueSerializer:(id<SQLSKMPKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeNullableSerializableValue(serializer:value:)")));
- (void)encodeSerializableValueSerializer:(id<SQLSKMPKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeSerializableValue(serializer:value:)")));
- (void)encodeShortValue:(int16_t)value __attribute__((swift_name("encodeShort(value:)")));
- (void)encodeStringValue:(NSString *)value __attribute__((swift_name("encodeString(value:)")));
@property (readonly) SQLSKMPKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerialDescriptor")))
@protocol SQLSKMPKotlinx_serialization_coreSerialDescriptor
@required

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (NSArray<id<SQLSKMPKotlinAnnotation>> *)getElementAnnotationsIndex:(int32_t)index __attribute__((swift_name("getElementAnnotations(index:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)getElementDescriptorIndex:(int32_t)index __attribute__((swift_name("getElementDescriptor(index:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (int32_t)getElementIndexName:(NSString *)name __attribute__((swift_name("getElementIndex(name:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (NSString *)getElementNameIndex:(int32_t)index __attribute__((swift_name("getElementName(index:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (BOOL)isElementOptionalIndex:(int32_t)index __attribute__((swift_name("isElementOptional(index:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property (readonly) NSArray<id<SQLSKMPKotlinAnnotation>> *annotations __attribute__((swift_name("annotations")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property (readonly) int32_t elementsCount __attribute__((swift_name("elementsCount")));
@property (readonly) BOOL isInline __attribute__((swift_name("isInline")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property (readonly) BOOL isNullable __attribute__((swift_name("isNullable")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property (readonly) SQLSKMPKotlinx_serialization_coreSerialKind *kind __attribute__((swift_name("kind")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property (readonly) NSString *serialName __attribute__((swift_name("serialName")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreDecoder")))
@protocol SQLSKMPKotlinx_serialization_coreDecoder
@required
- (id<SQLSKMPKotlinx_serialization_coreCompositeDecoder>)beginStructureDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("beginStructure(descriptor:)")));
- (BOOL)decodeBoolean __attribute__((swift_name("decodeBoolean()")));
- (int8_t)decodeByte __attribute__((swift_name("decodeByte()")));
- (unichar)decodeChar __attribute__((swift_name("decodeChar()")));
- (double)decodeDouble __attribute__((swift_name("decodeDouble()")));
- (int32_t)decodeEnumEnumDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)enumDescriptor __attribute__((swift_name("decodeEnum(enumDescriptor:)")));
- (float)decodeFloat __attribute__((swift_name("decodeFloat()")));
- (id<SQLSKMPKotlinx_serialization_coreDecoder>)decodeInlineDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeInline(descriptor:)")));
- (int32_t)decodeInt __attribute__((swift_name("decodeInt()")));
- (int64_t)decodeLong __attribute__((swift_name("decodeLong()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (BOOL)decodeNotNullMark __attribute__((swift_name("decodeNotNullMark()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (SQLSKMPKotlinNothing * _Nullable)decodeNull __attribute__((swift_name("decodeNull()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id _Nullable)decodeNullableSerializableValueDeserializer:(id<SQLSKMPKotlinx_serialization_coreDeserializationStrategy>)deserializer __attribute__((swift_name("decodeNullableSerializableValue(deserializer:)")));
- (id _Nullable)decodeSerializableValueDeserializer:(id<SQLSKMPKotlinx_serialization_coreDeserializationStrategy>)deserializer __attribute__((swift_name("decodeSerializableValue(deserializer:)")));
- (int16_t)decodeShort __attribute__((swift_name("decodeShort()")));
- (NSString *)decodeString __attribute__((swift_name("decodeString()")));
@property (readonly) SQLSKMPKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((swift_name("KotlinByteIterator")))
@interface SQLSKMPKotlinByteIterator : SQLSKMPBase <SQLSKMPKotlinIterator>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (SQLSKMPByte *)next __attribute__((swift_name("next()")));
- (int8_t)nextByte __attribute__((swift_name("nextByte()")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreCompositeEncoder")))
@protocol SQLSKMPKotlinx_serialization_coreCompositeEncoder
@required
- (void)encodeBooleanElementDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(BOOL)value __attribute__((swift_name("encodeBooleanElement(descriptor:index:value:)")));
- (void)encodeByteElementDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int8_t)value __attribute__((swift_name("encodeByteElement(descriptor:index:value:)")));
- (void)encodeCharElementDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(unichar)value __attribute__((swift_name("encodeCharElement(descriptor:index:value:)")));
- (void)encodeDoubleElementDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(double)value __attribute__((swift_name("encodeDoubleElement(descriptor:index:value:)")));
- (void)encodeFloatElementDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(float)value __attribute__((swift_name("encodeFloatElement(descriptor:index:value:)")));
- (id<SQLSKMPKotlinx_serialization_coreEncoder>)encodeInlineElementDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("encodeInlineElement(descriptor:index:)")));
- (void)encodeIntElementDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int32_t)value __attribute__((swift_name("encodeIntElement(descriptor:index:value:)")));
- (void)encodeLongElementDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int64_t)value __attribute__((swift_name("encodeLongElement(descriptor:index:value:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNullableSerializableElementDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index serializer:(id<SQLSKMPKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeNullableSerializableElement(descriptor:index:serializer:value:)")));
- (void)encodeSerializableElementDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index serializer:(id<SQLSKMPKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeSerializableElement(descriptor:index:serializer:value:)")));
- (void)encodeShortElementDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int16_t)value __attribute__((swift_name("encodeShortElement(descriptor:index:value:)")));
- (void)encodeStringElementDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(NSString *)value __attribute__((swift_name("encodeStringElement(descriptor:index:value:)")));
- (void)endStructureDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("endStructure(descriptor:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (BOOL)shouldEncodeElementDefaultDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("shouldEncodeElementDefault(descriptor:index:)")));
@property (readonly) SQLSKMPKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerializersModule")))
@interface SQLSKMPKotlinx_serialization_coreSerializersModule : SQLSKMPBase

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)dumpToCollector:(id<SQLSKMPKotlinx_serialization_coreSerializersModuleCollector>)collector __attribute__((swift_name("dumpTo(collector:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id<SQLSKMPKotlinx_serialization_coreKSerializer> _Nullable)getContextualKClass:(id<SQLSKMPKotlinKClass>)kClass typeArgumentsSerializers:(NSArray<id<SQLSKMPKotlinx_serialization_coreKSerializer>> *)typeArgumentsSerializers __attribute__((swift_name("getContextual(kClass:typeArgumentsSerializers:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id<SQLSKMPKotlinx_serialization_coreSerializationStrategy> _Nullable)getPolymorphicBaseClass:(id<SQLSKMPKotlinKClass>)baseClass value:(id)value __attribute__((swift_name("getPolymorphic(baseClass:value:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id<SQLSKMPKotlinx_serialization_coreDeserializationStrategy> _Nullable)getPolymorphicBaseClass:(id<SQLSKMPKotlinKClass>)baseClass serializedClassName:(NSString * _Nullable)serializedClassName __attribute__((swift_name("getPolymorphic(baseClass:serializedClassName:)")));
@end

__attribute__((swift_name("KotlinAnnotation")))
@protocol SQLSKMPKotlinAnnotation
@required
@end


/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
__attribute__((swift_name("Kotlinx_serialization_coreSerialKind")))
@interface SQLSKMPKotlinx_serialization_coreSerialKind : SQLSKMPBase
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreCompositeDecoder")))
@protocol SQLSKMPKotlinx_serialization_coreCompositeDecoder
@required
- (BOOL)decodeBooleanElementDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeBooleanElement(descriptor:index:)")));
- (int8_t)decodeByteElementDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeByteElement(descriptor:index:)")));
- (unichar)decodeCharElementDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeCharElement(descriptor:index:)")));
- (int32_t)decodeCollectionSizeDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeCollectionSize(descriptor:)")));
- (double)decodeDoubleElementDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeDoubleElement(descriptor:index:)")));
- (int32_t)decodeElementIndexDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeElementIndex(descriptor:)")));
- (float)decodeFloatElementDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeFloatElement(descriptor:index:)")));
- (id<SQLSKMPKotlinx_serialization_coreDecoder>)decodeInlineElementDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeInlineElement(descriptor:index:)")));
- (int32_t)decodeIntElementDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeIntElement(descriptor:index:)")));
- (int64_t)decodeLongElementDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeLongElement(descriptor:index:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id _Nullable)decodeNullableSerializableElementDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index deserializer:(id<SQLSKMPKotlinx_serialization_coreDeserializationStrategy>)deserializer previousValue:(id _Nullable)previousValue __attribute__((swift_name("decodeNullableSerializableElement(descriptor:index:deserializer:previousValue:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (BOOL)decodeSequentially __attribute__((swift_name("decodeSequentially()")));
- (id _Nullable)decodeSerializableElementDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index deserializer:(id<SQLSKMPKotlinx_serialization_coreDeserializationStrategy>)deserializer previousValue:(id _Nullable)previousValue __attribute__((swift_name("decodeSerializableElement(descriptor:index:deserializer:previousValue:)")));
- (int16_t)decodeShortElementDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeShortElement(descriptor:index:)")));
- (NSString *)decodeStringElementDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeStringElement(descriptor:index:)")));
- (void)endStructureDescriptor:(id<SQLSKMPKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("endStructure(descriptor:)")));
@property (readonly) SQLSKMPKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinNothing")))
@interface SQLSKMPKotlinNothing : SQLSKMPBase
@end


/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
__attribute__((swift_name("Kotlinx_serialization_coreSerializersModuleCollector")))
@protocol SQLSKMPKotlinx_serialization_coreSerializersModuleCollector
@required
- (void)contextualKClass:(id<SQLSKMPKotlinKClass>)kClass provider:(id<SQLSKMPKotlinx_serialization_coreKSerializer> (^)(NSArray<id<SQLSKMPKotlinx_serialization_coreKSerializer>> *))provider __attribute__((swift_name("contextual(kClass:provider:)")));
- (void)contextualKClass:(id<SQLSKMPKotlinKClass>)kClass serializer:(id<SQLSKMPKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("contextual(kClass:serializer:)")));
- (void)polymorphicBaseClass:(id<SQLSKMPKotlinKClass>)baseClass actualClass:(id<SQLSKMPKotlinKClass>)actualClass actualSerializer:(id<SQLSKMPKotlinx_serialization_coreKSerializer>)actualSerializer __attribute__((swift_name("polymorphic(baseClass:actualClass:actualSerializer:)")));
- (void)polymorphicDefaultBaseClass:(id<SQLSKMPKotlinKClass>)baseClass defaultDeserializerProvider:(id<SQLSKMPKotlinx_serialization_coreDeserializationStrategy> _Nullable (^)(NSString * _Nullable))defaultDeserializerProvider __attribute__((swift_name("polymorphicDefault(baseClass:defaultDeserializerProvider:)"))) __attribute__((deprecated("Deprecated in favor of function with more precise name: polymorphicDefaultDeserializer")));
- (void)polymorphicDefaultDeserializerBaseClass:(id<SQLSKMPKotlinKClass>)baseClass defaultDeserializerProvider:(id<SQLSKMPKotlinx_serialization_coreDeserializationStrategy> _Nullable (^)(NSString * _Nullable))defaultDeserializerProvider __attribute__((swift_name("polymorphicDefaultDeserializer(baseClass:defaultDeserializerProvider:)")));
- (void)polymorphicDefaultSerializerBaseClass:(id<SQLSKMPKotlinKClass>)baseClass defaultSerializerProvider:(id<SQLSKMPKotlinx_serialization_coreSerializationStrategy> _Nullable (^)(id))defaultSerializerProvider __attribute__((swift_name("polymorphicDefaultSerializer(baseClass:defaultSerializerProvider:)")));
@end

__attribute__((swift_name("KotlinKDeclarationContainer")))
@protocol SQLSKMPKotlinKDeclarationContainer
@required
@end

__attribute__((swift_name("KotlinKAnnotatedElement")))
@protocol SQLSKMPKotlinKAnnotatedElement
@required
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.1")
*/
__attribute__((swift_name("KotlinKClassifier")))
@protocol SQLSKMPKotlinKClassifier
@required
@end

__attribute__((swift_name("KotlinKClass")))
@protocol SQLSKMPKotlinKClass <SQLSKMPKotlinKDeclarationContainer, SQLSKMPKotlinKAnnotatedElement, SQLSKMPKotlinKClassifier>
@required

/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.1")
*/
- (BOOL)isInstanceValue:(id _Nullable)value __attribute__((swift_name("isInstance(value:)")));
@property (readonly) NSString * _Nullable qualifiedName __attribute__((swift_name("qualifiedName")));
@property (readonly) NSString * _Nullable simpleName __attribute__((swift_name("simpleName")));
@end

#pragma pop_macro("_Nullable_result")
#pragma clang diagnostic pop
NS_ASSUME_NONNULL_END
