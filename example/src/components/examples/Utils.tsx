import type {AsyncStorageSqlite} from '@react-native-async-storage/sqlite-storage';
import React, {useEffect, useState} from 'react';
import {Button, StyleSheet, Text, View} from 'react-native';

type Props = {db: AsyncStorageSqlite};

const Utils: React.FC<Props> = ({db}) => {
  const [dbPath, setDbPath] = useState('');
  const [dbSize, setDbSize] = useState('-');

  async function readPath() {
    const path = await db.getDatabaseFilePath();
    setDbPath(path);
  }

  async function readSize() {
    const size = await db.getDatabaseFileSize();
    setDbSize(`${size / 1000}MB`);
  }

  useEffect(() => {
    console.log('refreshing!');
    readPath();
    readSize();
  }, []);

  return (
    <View style={styles.container}>
      <Text style={styles.header}>Database utils</Text>
      <View style={styles.row}>
        <View style={styles.button}>
          <Button title="read size" onPress={readSize} />
        </View>
        <View style={styles.button}>
          <Button title="read path" onPress={readPath} />
        </View>
      </View>

      <Text>{`Database size: ${dbSize}`}</Text>
      <Text>{`Database path: ${dbPath}`}</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    padding: 8,
  },
  button: {
    paddingHorizontal: 8,
  },
  header: {
    fontSize: 18,
  },
  row: {
    paddingVertical: 18,
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
  },
});

export default Utils;
