import React, {useState} from 'react';
import {SafeAreaView, ScrollView, StatusBar, View} from 'react-native';
import useDatabase from './hooks/useDatabase';
import DatabaseSelector from './components/DatabaseSelector';
import ExampleSelector, {Examples} from './components/ExampleSelector';
import BasicCrudExample from './components/examples/BasicCrud';
import UtilsExample from './components/examples/Utils';
import type {AsyncStorageSqlite} from '@react-native-async-storage/sqlite-storage';

const ExamplesToComponents: {
  [key in Examples]: React.FC<{db: AsyncStorageSqlite}>;
} = {
  [Examples.BasicCrud]: BasicCrudExample,
  [Examples.Utils]: UtilsExample,
};

function App(): JSX.Element {
  const [dbName, setDbName] = useState('example_db.db');
  const [example, setExample] = useState(Examples.BasicCrud);
  const database = useDatabase(dbName);
  const Example = ExamplesToComponents[example];
  return (
    <SafeAreaView>
      <StatusBar barStyle={'dark-content'} />
      <ScrollView contentContainerStyle={{width: '100%', height: '100%'}}>
        <View style={{flex: 1}}>
          <DatabaseSelector
            initialName={dbName}
            onCreateDatabase={newName => setDbName(newName)}
          />
          <ExampleSelector selected={example} onSelect={setExample} />
          <Example db={database} key={database.name} />
        </View>
      </ScrollView>
    </SafeAreaView>
  );
}

export default App;
