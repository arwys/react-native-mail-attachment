# react-native-mail-attachment

send email with attachment

## Installation

```sh
npm install react-native-mail-attachment
yarn add react-native-mail-attachment
cd ios && pod install
```

## Usage

```js
//CLASS 
import MailAttachment from 'react-native-mail-attachment';

const App = () => {
  const recipient = 'recipient@example.com';
  const subject = 'Subject';
  const body = 'Email body';
  const attachmentUri = '/path/to/attachment/file';

  return (
    <>
      <View style={{height: 70}} />
      <TouchableOpacity onPress={
        async () => { 
         MailAttachment.sendEmailWithAttachment(recepient,subject, body,attachmentUri)}}>
        <Text> Send email</Text>
      </TouchableOpacity>
    </>
  );
};


//FUNCTION 
import { sendEmailWithAttachment} from 'react-native-mail-attachment';

const App = () => {
  const recipient = 'recipient@example.com';
  const subject = 'Subject';
  const body = 'Email body';
  const attachmentUri = '/path/to/attachment/file';

  return (
    <>
      <View style={{height: 70}} />
      <TouchableOpacity onPress={
        async () => { 
      sendEmailWithAttachment(recepient,subject, body,attachmentUri)}}>
        <Text> Send email</Text>
      </TouchableOpacity>
    </>
  );
};

// ...



```


## Current API(Property)

| Property    | Type                     | Description                 |       
| ----------- | ------------------------ | --------------------------|
| sendEmailWithAttachment  | void            |   android & ios
| pickFile  | function            | android  |
| recipient  | string  (optional)              | render component                 |
|            |
|subject        | string (optional) |email subject
|body        | string (optional) | email body
|URI        | string (required) |PDF or TXT or jpg
               


## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

