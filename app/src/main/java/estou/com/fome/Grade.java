package estou.com.fome;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by enzo on 20/11/2014.
 */
public class Grade extends Activity{

    private LogClass logClass;
    private int multipleAlarms = 0;
    private static Grade grade;

    //Atributos
    private Context context;
    private int hora;
    private int minuto;

    public Grade(Context context)
    {
        this.context = context;
    }

    public static Grade initialize(Context context)
    {
        if (grade != null)
            return grade;
        grade = new Grade(context);
        return grade;
    }

    public void trocaContext(Context context)
    {
        this.context = context;
    }

    public boolean registrarGrade(int hora, int minuto)
    {
        try
        {
            Calendar calendar = Calendar.getInstance();
            Calendar now = (Calendar)calendar.clone();

            int ano = calendar.get(Calendar.YEAR);
            int mes = calendar.get(Calendar.MONTH);
            int dia = calendar.get(Calendar.DATE);

            calendar.set(Calendar.YEAR, ano);
            calendar.set(Calendar.MONTH, mes);
            calendar.set(Calendar.DATE, dia);
            calendar.set(Calendar.HOUR_OF_DAY, hora);
            calendar.set(Calendar.MINUTE, minuto);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            Intent alarm = new Intent(context , Alarme.class);
            PendingIntent pIntent = PendingIntent.getBroadcast(context, multipleAlarms, alarm, PendingIntent.FLAG_ONE_SHOT);
            this.multipleAlarms++;

            AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);

            AlertDialog.Builder alert = new AlertDialog.Builder(context);

            String data = dia+"/"+(mes+1)+"/"+ano+" "+hora+":"+minuto;
            String mensagem = "Lanche marcado para: "+ data;

            String alarmesString = "Lanche tomado: "+data;

            logClass = LogClass.initialize(context);
            logClass.setTimeToEatCalendar(calendar);
            logClass.writeLog(alarmesString);

            alert.setTitle("Alarme registrado!!");
            alert.setMessage(mensagem);
            alert.setNeutralButton("Ok", null);
            alert.show();
        }
        catch (Exception es)
        {
            return false;
        }

        return true;
    }
}
