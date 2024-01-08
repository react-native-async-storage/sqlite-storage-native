import type {Key, Value, Entries} from "./types";
import module from './NativeModule'

export class AsyncStorageSqlite {
    constructor(private dbName: string) {
    }

    read = async (key: Key): Promise<Entries> => {
        return this.readMany([key])
    }

    readMany = async (keys: Key[]): Promise<Entries> => {
        return module.readMany(this.dbName, keys)
    }

    save = async (key: Key, value: Value): Promise<void> => {
        return this.saveMany({[key]: value})
    }

    saveMany = async (entries: Entries): Promise<void> => {
        return module.saveMany(this.dbName, entries)
    }

    delete = async (key: Key): Promise<void> => {
        return this.deleteMany([key])
    }

    deleteMany = async (keys: Key[]): Promise<void> => {
        return module.deleteMany(this.dbName, keys)
    }

    merge = async (entry: Entries): Promise<Entries> => {
        return module.merge(this.dbName, entry)
    }

    getKeys = async (): Promise<Key[]> => {
        return module.getKeys(this.dbName)
    }


    getDatabaseFilePath = async() => {
        return module.getDatabasePath(this.dbName)
    }

    getDatabaseFileSize = async() => {
        return module.getDatabaseSize(this.dbName)
    }
}
