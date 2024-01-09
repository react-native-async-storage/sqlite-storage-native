import React, {useState} from 'react';
import {SafeAreaView, ScrollView, StatusBar, View} from 'react-native';
import useDatabase from './hooks/useDatabase';
import DatabaseSelector from './components/DatabaseSelector';
import BasicCrud from './components/BasicCrud';

function App(): JSX.Element {
  const [dbName, setDbName] = useState('example_db.db');
  const database = useDatabase(dbName);
  return (
    <SafeAreaView>
      <StatusBar barStyle={'dark-content'} />
      <ScrollView contentContainerStyle={{width: '100%', height: '100%'}}>
        <View style={{flex: 1}}>
          <DatabaseSelector
            initialName={dbName}
            onCreateDatabase={newName => setDbName(newName)}
          />
          <BasicCrud key={dbName} db={database} />
        </View>
      </ScrollView>
    </SafeAreaView>
  );
}

export default App;
