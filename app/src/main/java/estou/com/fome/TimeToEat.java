package estou.com.fome;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.widget.TextView;

public class TimeToEat extends Activity {

	/** Called when the activity is first created. */
	CountDownTimer cdt;
	TextView tvcdtTime;
	Vibrator vibrate;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.layout_time_to_eat);
	 
	    tvcdtTime = (TextView)findViewById(R.id.tvcdtTime);
	    vibrate = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
	    vibrate.vibrate(5000);
	    cdt = new CountDownTimer(5000, 1000) {
			
			@Override
			public void onTick(long millisUntilFinished) {
				tvcdtTime.setText(String.valueOf(millisUntilFinished/1000));
			}
			
			@Override
			public void onFinish() {
				finish();
			}
		};
		
		cdt.start();
	}

}
