package estou.com.fome;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;

import android.R.bool;
import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.os.FileObserver;
import android.util.Log;
import android.widget.Toast;

public class LogClass extends Activity{
	
	private  Calendar nowCalendar;
	private Calendar timeToEatCalendar;
	private static LogClass logClass = null;
	private static int logCount = 0;
	private final static File PATH_STRING = Environment.getExternalStorageDirectory();
	private static File folder;
	private static File file;
	private static String logName = "log("+logCount+").txt";
	private Context context;
	
	public void setTimeToEatCalendar(Calendar timeToEatCalendar) {
		this.timeToEatCalendar = timeToEatCalendar;
	}
	
	public void setContext(Context context) {
		this.context = context;
	}
	
	public LogClass(Context context) {
		this.context = context;
	}
		
	public static LogClass initialize(Context context)
	{
		if (logClass != null) {
			return logClass;
		}
		
		logClass = new LogClass(context);
		
		createFolder();
		createFile();
		
		return logClass;
	}
	
	private static void createFolder()
	{
		try {
			folder = new File(PATH_STRING, "Log");
			if (!folder.exists()) {
				folder.mkdirs();
			}
		} catch (Exception e) {
			Log.e("Error(createFolderFile())", e.toString());
		}
	}
	
	public static void createFile()
	{
		file = new File(folder, logName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				Log.e("Error(createFile())", e.toString());
			}
		}
	}
	
	public void writeLog(String writeIt)
	{
		try {
			FileWriter fWriter = new FileWriter(file, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fWriter);
			bufferedWriter.append(writeIt);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			bufferedWriter.close();
			msgSaved();
		} catch (Exception e) {
			Log.e("Error(Log)", e.toString());
		}
	}
	
	public void writeLog()
	{
		try { 
			FileWriter fWriter = new FileWriter(file, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fWriter);
			String timeToEat = timeToEatCalendar.get(Calendar.DATE)
					+"/"+timeToEatCalendar.get(Calendar.MONTH)
					+"/"+timeToEatCalendar.get(Calendar.YEAR)
					+"/"+timeToEatCalendar.get(Calendar.HOUR_OF_DAY)
					+":"+timeToEatCalendar.get(Calendar.MINUTE);
			bufferedWriter.append(timeToEat);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			bufferedWriter.close();
			msgSaved();
		} catch (Exception e) {
			Log.e("Error(Log)", e.toString());
		}
	}
	
	public String[] readFile()
	{
		ArrayList<String> lista = new ArrayList<String>();
		String[] vStrings = null;
		if (file.exists()) {
			FileReader fileReader;
			BufferedReader bufferedReader;
			String lineString;
			int count = 0;
			try {
				fileReader = new FileReader(file);
				bufferedReader = new BufferedReader(fileReader);
				while ((lineString = bufferedReader.readLine()) != null) {
					lista.add(lineString);
				}
				fileReader.close();
				bufferedReader.close();
				vStrings = new String[lista.size()];
				vStrings = lista.toArray(vStrings);
			} catch (Exception e) {
				Log.e("Error(readFile())", e.toString());
			}
		}
		return vStrings;
	}
	
	public void msgSaved()
	{
		Toast.makeText(context, "Salvo com sucesso!!", Toast.LENGTH_SHORT).show();
	}
}
