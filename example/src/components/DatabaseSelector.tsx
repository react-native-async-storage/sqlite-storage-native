import React from 'react';
import {useState} from 'react';
import {Button, StyleSheet, TextInput, View} from 'react-native';

type Props = {
  initialName: string;
  children?: React.ReactElement;
  onCreateDatabase: (dbName: string) => void;
};

const DatabaseSelector: React.FC<Props> = ({
  children,
  initialName,
  onCreateDatabase,
}) => {
  const [input, setInput] = useState<string>(initialName);

  function createDatabase() {
    if (!input) {
      return;
    }
    onCreateDatabase(input);
  }

  return (
    <View>
      <View style={styles.row}>
        <TextInput style={styles.input} onChangeText={setInput} value={input} />
        <View style={styles.buttons}>
          <Button title="Select database" onPress={createDatabase} />
        </View>
      </View>
      {children}
    </View>
  );
};

const styles = StyleSheet.create({
  row: {
    padding: 8,
    flexDirection: 'row',
    justifyContent: 'space-around',
    alignItems: 'center',
  },
  input: {
    paddingHorizontal: 8,
    borderStyle: 'solid',
    borderWidth: 1,
    flex: 1,
    borderColor: 'gray',
  },
  buttons: {alignItems: 'center', justifyContent: 'center', padding: 8},
});

export default DatabaseSelector;
