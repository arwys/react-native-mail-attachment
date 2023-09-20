import { NativeModules, Platform } from 'react-native';
import { errorResponse } from './error';

const isIPhone: boolean = Platform.OS === 'ios';

const moduleName = isIPhone
  ? NativeModules.MailAttachement
  : NativeModules.MailAttachment;

const MailAttachment = moduleName ? moduleName : errorResponse;

export const pickFile = async (): Promise<string | undefined> => {
  if (!isIPhone) {
    try {
      const uri: string = await MailAttachment.pickFile();
      return uri;
    } catch (error) {
      console.error('File picking error:', error);
      return;
    }
  } else {
    console.error('Unfortunetely IOS has not supported yetx');
    return;
  }
};

export const sendEmailWithAttachment = (
  URI: string,
  recipient: string = '',
  subject: string = '',
  body: string = ''
) => {
  const pdfUri = URI;

  if (!URI) throw new Error('Please Provide the URI ');
  else MailAttachment.sendEmailWithAttachment(recipient, subject, body, pdfUri);
};

class MailAttachmentClass {
  static async pickFile(): Promise<string | undefined> {
    return await pickFile();
  }

  static async sendEmailWithAttachment(
    URI: string,
    recipient: string = '',
    subject: string = '',
    body: string = ''
  ) {
    await sendEmailWithAttachment(recipient, subject, body, URI);
  }
}

export {
  MailAttachmentClass as default,
  MailAttachmentClass as MailAttachment,
};
