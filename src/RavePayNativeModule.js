//  Created by react-native-create-bridge

import { NativeModules } from 'react-native'

const { RavePay } = NativeModules;

export default {
  exampleMethod () {
    return RavePay.exampleMethod()
  },

  EXAMPLE_CONSTANT: RavePay.EXAMPLE_CONSTANT
}
