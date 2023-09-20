package com.mailattachment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = MailAttachmentModule.NAME)
public class MailAttachmentModule extends ReactContextBaseJavaModule {
  public static final String NAME = "MailAttachment";

 private static final int PICK_FILE_REQUEST_CODE = 1;
    private Promise mPickerPromise;

    private final ActivityEventListener mActivityEventListener = new BaseActivityEventListener() {
        @Override
        public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
            if (requestCode == PICK_FILE_REQUEST_CODE) {
                if (resultCode == Activity.RESULT_OK) {
                    if (mPickerPromise != null) {
                        String uri = data.getData().toString();
                        mPickerPromise.resolve(uri);
                    }
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    if (mPickerPromise != null) {
                        mPickerPromise.reject("USER_CANCELLED", "User cancelled file picker");
                    }
                }
            }
        }
    };


  public MailAttachmentModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }


  // Example method
  // See https://reactnative.dev/docs/native-modules-android

  @ReactMethod
  public void sendEmailWithAttachment(String recipient, String subject, String body, String attachmentUri, Promise promise) {
    try {
      Intent emailIntent = new Intent(Intent.ACTION_SEND);
      emailIntent.setType("text/plain");
      emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
      emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
      emailIntent.putExtra(Intent.EXTRA_TEXT, body);

      if (attachmentUri != null && !attachmentUri.isEmpty()) {
        Uri attachment = Uri.parse(attachmentUri);
        emailIntent.putExtra(Intent.EXTRA_STREAM, attachment);
      }

      emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

      getReactApplicationContext().startActivity(emailIntent);
      promise.resolve("Email sent successfully");
    } catch (Exception e) {
      promise.reject("EMAIL_SEND_ERROR", "Failed to send email: " + e.getMessage());
    }
  }

   @ReactMethod
    public void pickFile(Promise promise) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            promise.reject("ACTIVITY_NOT_FOUND", "Activity not found");
            return;
        }
        mPickerPromise = promise;
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");

        currentActivity.startActivityForResult(intent, PICK_FILE_REQUEST_CODE, null);
    }
}
