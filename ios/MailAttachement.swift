
@objc(MailAttachement)
class MailAttachement: NSObject {

  @objc(sendEmailWithAttachment:subject:body:attachmentUri:resolver:rejecter:)
    func sendEmailWithAttachment(_ recipient: String, subject: String, body: String, attachmentUri: String, resolver: @escaping RCTPromiseResolveBlock, rejecter: @escaping RCTPromiseRejectBlock)  {
      if let recipientEncoded = recipient.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed),
    let subjectEncoded = subject.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed),
    let bodyEncoded = body.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed),
    let emailURL = URL(string: "mailto:\(recipientEncoded)?subject=\(subjectEncoded)&body=\(bodyEncoded)") {

    if UIApplication.shared.canOpenURL(emailURL) {
        if #available(iOS 10.0, *) {
            UIApplication.shared.open(emailURL, options: [:], completionHandler: { (success) in
                if success {
                    resolver("Email app opened successfully.")
                } else {
                    rejecter("EMAIL_OPEN_ERROR", "Failed to open email app.", nil)
                }
            })
        } else {
            UIApplication.shared.openURL(emailURL)
            resolver("Email app opened successfully.")
        }
    } else {
        rejecter("EMAIL_APP_NOT_INSTALLED", "No email app is installed to handle this request.", nil)
    }
} else {
    rejecter("EMAIL_URL_ERROR", "Failed to create a valid email URL.", nil)
}
    }
}


