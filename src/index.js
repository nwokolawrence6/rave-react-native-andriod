import React from 'react';
import { View, Text , Button, NativeModules} from 'react-native';
const { RavePay } = NativeModules;
import makePayment from './makePayment'
const Index = () => {
  const handle = () => {
    makePayment("FLWPUBK_TEST-917e60385a1b5ec2da7f93794574f7f1-X", "FLWSECK_TESTf8bae715af2c", 10, "nwokolawrence6@gmail.com", "fName", "lName", "narration", "NG", "NGN", "meta").then((data)=> {
      console.log(data)
    }).catch((e)=> console.warn(e))
  };
  return (
    <View style={{justifyContent: "center", flex: 1, alignContent: "center"}}>
      <Button onPress={()=> handle()} title="sd"/>
    </View>
  );
};

export default Index;
