package com.example.smsproject;



import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
        Button c = (Button) findViewById(R.id.button1);
        c.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
	        	Intent intentI = new Intent(MainActivity.this, smsActivity.class);
	        	startActivity(intentI);
				
			}
		});
        
        Button send = (Button) findViewById(R.id.button2);
        send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String messageToSend = "this is a test message from Janitha";
				 String number = "0713485096";

				 SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null,null);
				
			}
		});
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
       // return true;
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.main, menu);
        //Log.d("Main", "Menu has bee created");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_about){
        	Intent intentI = new Intent(MainActivity.this, smsActivity.class);
        	startActivity(intentI);
        }
        else if (id == R.id.item1) {
			
		}
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
