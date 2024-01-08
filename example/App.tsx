import React, {useState} from 'react';
import {Button, SafeAreaView, ScrollView, StatusBar, Text} from 'react-native';

import {AsyncStorageSqlite} from '@react-native-async-storage/sqlite-storage';

const storage = new AsyncStorageSqlite('example_db.db');

function App(): JSX.Element {
  const [en, setEnt] = useState('');
  return (
    <SafeAreaView>
      <StatusBar barStyle={'dark-content'} />
      <ScrollView contentInsetAdjustmentBehavior="automatic">
        <Button
          title="Save random"
          onPress={() => {
            const random = (Math.random() * 100) % 100;
            storage.save('random', String(random));
          }}
        />
        <Button
          title="read random"
          onPress={() => {
            storage.read('random').then(res => {
              setEnt(JSON.stringify(res, null, 2));
            });
          }}
        />
        <Button
          title="delete random"
          onPress={() => {
            storage.delete('random').then(res => {
              storage.read('random').then(res => {
                setEnt(JSON.stringify(res, null, 2));
              });
            });
          }}
        />

        <Button
          title="log db path"
          onPress={() => {
            storage.getDatabaseFilePath().then(console.log);
          }}
        />
        <Button
          title="log db size"
          onPress={() => {
            storage.getDatabaseFileSize().then(console.log);
          }}
        />
        <Text>Entries:</Text>
        <Text>{en}</Text>
      </ScrollView>
    </SafeAreaView>
  );
}

export default App;
