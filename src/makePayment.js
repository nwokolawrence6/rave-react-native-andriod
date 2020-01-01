import __ from 'arguejs';
import { NativeModules } from 'react-native';

const { RavePay } = NativeModules;
const makePayment = ( publicKey , encryptionKey , amount , email , firstName , lastName , narration , country , currency, meta ) => {
  return new Promise( ( resolve , reject ) => {
    try {
      __( {
        publicKey : String ,
        encryptionKey : String ,
        amount : Number ,
        email : String ,
        firstName : String ,
        lastName : String ,
        narration : String ,
        country : String ,
        currency : String,
        meta: [String]
      } ,  [publicKey , encryptionKey , amount , email , firstName , lastName , narration , country , currency, meta ]);
      RavePay.startPaymentProcess( publicKey , encryptionKey , amount , email , firstName , lastName , narration , country , currency , ( requestCode , resultCode , result , type ) => {
        if ( type === 'ERROR' ) {
          return reject( result );
        }
        return resolve( JSON.parse( result ) );
      } );
    } catch ( e ) {
      return reject( e.message );
    }

  } );
};
export default makePayment
