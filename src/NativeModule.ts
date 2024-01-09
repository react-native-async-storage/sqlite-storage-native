import { NativeModules } from "react-native";
import type { Entries, Key } from "./types";

interface AsyncStorageSqliteNativeModule {
  readMany: (dbName: string, keys: Key[]) => Promise<Entries>;
  saveMany: (dbName: string, entries: Entries) => Promise<void>;
  deleteMany: (dbName: string, keys: Key[]) => Promise<void>;
  merge: (dbName: string, entries: Entries) => Promise<Entries>;
  getKeys: (dbName: string) => Promise<Key[]>;
  dropStorage: (dbName: string) => Promise<void>;
  getDatabasePath: (dbName: string) => Promise<string>;
  getDatabaseSize: (dbName: string) => Promise<number>;
}

export default NativeModules[
  "AsyncStorageSqlite"
] as AsyncStorageSqliteNativeModule;
