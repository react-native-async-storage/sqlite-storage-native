package org.reactnative.asyncstorage.sqlite

import com.facebook.react.TurboReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.model.ReactModuleInfo
import com.facebook.react.module.model.ReactModuleInfoProvider

class AsyncStorageSqlitePackage : TurboReactPackage() {
    override fun getModule(name: String?, ctx: ReactApplicationContext): NativeModule? {
        if (name == AsyncStorageSqlite.NAME) {
            return AsyncStorageSqliteModule(ctx)
        }
        return null
    }


    override fun getReactModuleInfoProvider(): ReactModuleInfoProvider {
        return ReactModuleInfoProvider {
            val moduleInfo = ReactModuleInfo(
                AsyncStorageSqlite.NAME, /* name */
                AsyncStorageSqlite::class.java.name, /* className */
                false, /* canOverrideExistingModule */
                false, /* needsEagerInit */
                false, /* hasConstants */
                false, /* isCxxModule */
                BuildConfig.IS_NEW_ARCHITECTURE_ENABLED, /* isTurboModule */
            )
            mutableMapOf<String, ReactModuleInfo>().apply {
                put(AsyncStorageSqlite.NAME, moduleInfo)
            }
        }
    }
}
