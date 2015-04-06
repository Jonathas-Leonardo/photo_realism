package fotorealistico.pack;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


public class Inicio extends Activity {
	
	ListView lista;
	GradientDrawable grad;
	
	String contatos[] = new String[]{"Iniciar","Sair"};
	ArrayAdapter<String> adapter;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
       
        		//array
        		adapter = new  ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contatos);
                
        		//lista
        		lista = (ListView) findViewById(R.inicio.lista);
                lista.setAdapter(adapter);

                //degrade\\
                grad = new GradientDrawable(Orientation.TOP_BOTTOM,new int[] {Color.BLACK, Color.GRAY});
                getWindow().setBackgroundDrawable(grad);
                
                lista.setOnItemClickListener(listaListener);
    }
    
    
    OnItemClickListener listaListener = new OnItemClickListener(){
		@Override
		public void onItemClick(AdapterView<?> arg0, View v, int position,
				long arg3) {
			// TODO Auto-generated method stub
			
			switch(position){
			case 0:
				Intent it = new Intent(v.getContext(), Photo.class);
				startActivity(it);
				break;
			case 1:
				System.exit(0);
				break;
			default:
				break;
			}
		}
    };
}