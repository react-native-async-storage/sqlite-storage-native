import React from 'react';
import {Button, StyleSheet, View} from 'react-native';

type Props = {
  selected: Examples;
  onSelect: (selected: Examples) => void;
};

export enum Examples {
  BasicCrud = 'Basic Crud',
  Utils = 'Utils',
}

const ActiveColor = '#7DCE82';
const DisabledColor = '#B5E3B8';

const ExampleSelector: React.FC<Props> = ({selected, onSelect}) => {
  return (
    <View style={styles.row}>
      <View style={styles.button}>
        <Button
          color={selected === Examples.BasicCrud ? ActiveColor : DisabledColor}
          title="Curd"
          onPress={() => onSelect(Examples.BasicCrud)}
        />
      </View>
      <View style={styles.button}>
        <Button
          color={selected === Examples.Utils ? ActiveColor : DisabledColor}
          title="Utils"
          onPress={() => onSelect(Examples.Utils)}
        />
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  row: {
    padding: 8,
    flexDirection: 'row',
    justifyContent: 'flex-start',
    alignItems: 'center',
  },
  button: {
    paddingHorizontal: 8,
  },
});

export default ExampleSelector;
