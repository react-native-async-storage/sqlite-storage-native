#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(AsyncStorageSqlite, NSObject)

RCT_EXTERN_METHOD(getRandomNumber:(RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject);

@end
