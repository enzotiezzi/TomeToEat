package estou.com.fome;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.ClipData.Item;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TimePicker;

public class MainActivity extends Activity {

	private TimePicker tpTimePicker;
	public static Activity lastActivity;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tpTimePicker = (TimePicker)findViewById	(R.id.tpTime);
		lastActivity = this;
	}
	
	public void getTime(View v)
	{
		int hora = tpTimePicker.getCurrentHour();
		int minuto = tpTimePicker.getCurrentMinute();
        Grade grade = Grade.initialize(this);
        grade.registrarGrade(hora, minuto);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_inicio, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		
		switch (item.getItemId()) {
		case R.id.mnListaAlarmes:
			gotoListaAlarmes();
			break;

		default:
			break;
		}
		
		return true;
	}
	
	public void gotoListaAlarmes()
	{
		Intent intent = new Intent(getApplicationContext(), ListaAlarme.class);
		startActivity(intent);
	}
}
