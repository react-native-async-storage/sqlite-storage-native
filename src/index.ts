import {NativeModules} from 'react-native'

const module = NativeModules['AsyncStorageSqlite']

export async function sayHi() {
    const result = await module.getRandomNumber()
    console.log("hello!", result)
    return result
}
