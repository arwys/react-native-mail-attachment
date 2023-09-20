#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(MailAttachement, NSObject)

RCT_EXTERN_METHOD(sendEmailWithAttachment:
          attachmentUri:(NSString *)attachmentUri
          recipient:(NSString *)recipient
                        subject:(NSString *)subject
                           body:(NSString *)body
                      resolver:(RCTPromiseResolveBlock)resolve
                      rejecter:(RCTPromiseRejectBlock)reject)


+ (BOOL)requiresMainQueueSetup
{
  return NO;
}

@end



