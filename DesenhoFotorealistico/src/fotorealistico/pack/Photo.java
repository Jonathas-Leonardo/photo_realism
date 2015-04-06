package fotorealistico.pack;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class Photo extends Activity {

	// Variaveis globais
	protected static int largura;
	protected static int altura;
	protected static int max;
	protected static int[] pixel;
	static Bitmap bit;

	// Imagens
	Integer imagens[] = { R.drawable.yui, R.drawable.will, R.drawable.beyonce,
			R.drawable.jim, R.drawable.epica, R.drawable.place2,
			R.drawable.place3, R.drawable.place4, R.drawable.espiral,
			R.drawable.donald, R.drawable.beatles, R.drawable.beatles2,
			R.drawable.hp };

	// imagem, galeria, botao, bitmap
	ImageSwitcher imagemSwitcher;
	ImageView imagem;
	Gallery galeria;
	Button go, back;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);

		// imagemSwitcher
		imagemSwitcher = (ImageSwitcher) findViewById(R.photo.switcher);
		imagemSwitcher.setFactory(imagemfactory);
		imagemSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
		imagemSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));

		// galeria
		galeria = (Gallery) findViewById(R.photo.gallery);
		galeria.setAdapter(new ImageAdapter(this, imagens));
		galeria.setOnItemSelectedListener(galeriaListener);

		// botao voltar
		back = (Button) findViewById(R.photo.but1);
		back.setOnClickListener(voltaListener);

		// botao ir
		go = (Button) findViewById(R.photo.but2);
		go.setOnClickListener(transformarListener);
	}

	// ImagemSwitch
	ViewFactory imagemfactory = new ViewFactory() {
		@Override
		public View makeView() {
			// TODO Auto-generated method stub
			ImageView i = new ImageView(getBaseContext());
			i.setScaleType(ImageView.ScaleType.FIT_CENTER);
			i.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			return i;
		}
	};

	// Galeria
	OnItemSelectedListener galeriaListener = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
			
			// inserir no imagemSwitcher a imagem clicada
			imagemSwitcher.setImageResource(imagens[position]);
			// pegar informações da imagem clicada
			bit = BitmapFactory.decodeResource(getResources(),imagens[position]);	
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
		}
	};

	// Evento Botao Volta
	OnClickListener voltaListener = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			finish();
		}
	};

	// Evento Botao Transforma
	OnClickListener transformarListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			// pegar todos os pixels da imagem
			altura = bit.getHeight();
			largura = bit.getWidth();
			max = largura * altura;
			// Log.e("Largura e Altura", "" + largura + " X " + altura);
						
			// vetor de pixel e dados dos pixels
			pixel = new int[max];
			bit.getPixels(pixel, 0, largura, 0, 0, largura, altura);
			
			// Cria uma nova Imagem
			bit = Bitmap.createBitmap(largura, altura, Bitmap.Config.ARGB_8888);
			bit.setPixels(pixel, 0, largura, 0, 0, largura, altura);

			Intent it = new Intent(v.getContext(), Desenho.class);
			startActivity(it);
		}
	};
}