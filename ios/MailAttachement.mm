#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(MailAttachement, NSObject)

RCT_EXTERN_METHOD(sendEmailWithAttachment:(NSString *)recipient
                        subject:(NSString *)subject
                           body:(NSString *)body
                 attachmentUri:(NSString *)attachmentUri
                      resolver:(RCTPromiseResolveBlock)resolve
                      rejecter:(RCTPromiseRejectBlock)reject)


+ (BOOL)requiresMainQueueSetup
{
  return NO;
}

@end



