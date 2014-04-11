package com.example.smsproject;

import java.util.ArrayList;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

public class smsActivity extends Activity {
	
	private static final String SMS_SENT = "SENT";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		registerReceiver(receiver, new IntentFilter(SMS_SENT));
		sendSms("0771052121", "this is a text message by Janitha", false);
	}

	private void sendSms(String phonenumber,String message, boolean isBinary)
	{
	    SmsManager manager = SmsManager.getDefault();

	    Intent SMS_SENT = null;
		PendingIntent piSend = PendingIntent.getBroadcast(this, 0, new Intent(SMS_SENT), 0);
	    Intent SMS_DELIVERED = null;
		PendingIntent piDelivered = PendingIntent.getBroadcast(this, 0, new Intent(SMS_DELIVERED), 0);

	    int MAX_SMS_MESSAGE_LENGTH = 160;
		if(isBinary)
	    {
	            byte[] data = new byte[message.length()];

	            for(int index=0; index<message.length() && index < MAX_SMS_MESSAGE_LENGTH; ++index)
	            {
	                    data[index] = (byte)message.charAt(index);
	            }

	            short SMS_PORT = 0;
				manager.sendDataMessage(phonenumber, null, (short) SMS_PORT, data,piSend, piDelivered);
	    }
	    else
	    {
	            int length = message.length();

	            if(length > MAX_SMS_MESSAGE_LENGTH)
	            {
	                    ArrayList<String> messagelist = manager.divideMessage(message);

	                    manager.sendMultipartTextMessage(phonenumber, null, messagelist, null, null);
	            }
	            else
	            {
	                    manager.sendTextMessage(phonenumber, null, message, piSend, piDelivered);
	            }
	    }
	}
	
	private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String message = null;

            switch (getResultCode()) {
            case Activity.RESULT_OK:
                message = "Message sent!";
                break;
            case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                message = "Error. Message not sent.";
                break;
            case SmsManager.RESULT_ERROR_NO_SERVICE:
                message = "Error: No service.";
                break;
            case SmsManager.RESULT_ERROR_NULL_PDU:
                message = "Error: Null PDU.";
                break;
            case SmsManager.RESULT_ERROR_RADIO_OFF:
                message = "Error: Radio off.";
                break;
            }

            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            
      }
  };
  
  @Override
  protected void onDestroy() {
      unregisterReceiver(receiver);
      super.onDestroy();
  }
}
