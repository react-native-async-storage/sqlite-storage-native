import Foundation

@objc(AsyncStorageSqlite)
class AsyncStorageSqliteModule : NSObject {
    @objc(getRandomNumber:reject:)
    func random(resolve: RCTPromiseResolveBlock, reject: RCTPromiseRejectBlock) {
        resolve(Int.random(in: 1...100))
    }
}
