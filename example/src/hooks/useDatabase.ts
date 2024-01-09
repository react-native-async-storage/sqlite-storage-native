import React, {useEffect} from 'react';
import {AsyncStorageSqlite} from '@react-native-async-storage/sqlite-storage';

function useDatabase(dbName: string) {
  const [database, setDatabase] = React.useState<AsyncStorageSqlite>(() => {
    return new AsyncStorageSqlite(dbName);
  });

  useEffect(() => {
    if (dbName && database?.name !== dbName) {
      setDatabase(new AsyncStorageSqlite(dbName));
    }
  }, [dbName]);

  return database;
}

export default useDatabase;
