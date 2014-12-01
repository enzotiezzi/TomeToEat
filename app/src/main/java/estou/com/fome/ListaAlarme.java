package estou.com.fome;

import java.net.URISyntaxException;

import android.R.anim;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListaAlarme extends Activity {

	private ListView lvAlarmes;
	private ArrayAdapter<String> listaAdapter;
	private LogClass logClass;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.lista_alarmes);

	    try {
	    	lvAlarmes = (ListView)findViewById(R.id.lvAlarmes);
		    logClass = LogClass.initialize(getApplicationContext());
		    
		    String[] lista = logClass.readFile();
		    
		    if (lista != null) {
		    	listaAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
			    lvAlarmes.setAdapter(listaAdapter);
			}else {
				Toast.makeText(getApplicationContext(), "Lista vazia", Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Log.e("Error(onCreate)", e.toString());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_principal, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		
		switch (item.getItemId()) {
		case R.id.mnBack:
			MenuClass.finishActivity(this);
			break;

		default:
			break;
		}
		
		return true;
	}	
}
