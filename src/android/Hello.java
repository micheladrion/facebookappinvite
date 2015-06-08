package com.example.plugin;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

import com.facebook.FacebookSdk;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.widget.AppInviteDialog;

import android.content.Context;

public class Hello extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("greet")) {
			
			Context context=this.cordova.getActivity().getApplicationContext();
        	
        	FacebookSdk.setApplicationId("732960346813030");
            FacebookSdk.sdkInitialize(context);
			
			String appLinkUrl, previewImageUrl;

			appLinkUrl = "https://www.mydomain.com/myapplink";
			previewImageUrl = "https://www.mydomain.com/my_invite_image.jpg";

			if (AppInviteDialog.canShow()) {
				
				AppInviteContent content = new AppInviteContent.Builder()
                .setApplinkUrl(appLinkUrl)
                .setPreviewImageUrl(previewImageUrl)
                .build();
				
			    AppInviteDialog.show( this.cordova.getActivity(), content);
			    //AppInviteDialog.show( this.cordova.getActivity(), content );
			}

            String name = data.getString(0);
            String message = "Hello, " + name;
            callbackContext.success(message);

            return true;

        } else {
            
            return false;

        }
    }
}
