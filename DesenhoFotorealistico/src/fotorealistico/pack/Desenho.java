package fotorealistico.pack;

import metodos.pack.Func;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Desenho extends Photo {
	
	// imagem, galeria, botao, bitmap
	TextView txt;
	ImageView imagem;
	
	//funcionalidades 7
	Button blur, cinza, negativo, sat, toon, nanquim, contorno, all;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desenho);
       
        txt = (TextView)findViewById(R.desenho.txt);
        
        imagem = (ImageView)findViewById(R.desenho.img1);
        imagem.setImageBitmap(bit);
        
        //botao voltar
        negativo = (Button) findViewById(R.desenho.negativo);
        negativo.setOnClickListener(negativoListener);
        
        blur = (Button) findViewById(R.desenho.blur);
        cinza = (Button) findViewById(R.desenho.cinza);
        sat = (Button) findViewById(R.desenho.sat);
        toon = (Button) findViewById(R.desenho.toon);
        nanquim = (Button) findViewById(R.desenho.nanquim);
        contorno = (Button) findViewById(R.desenho.contorno);
        all = (Button) findViewById(R.desenho.all);
        
        blur.setOnClickListener(blurListener);
        cinza.setOnClickListener(cinzaListener);
        sat.setOnClickListener(satListener);
        toon.setOnClickListener(toonListener);
        nanquim.setOnClickListener(nanquimListener);
        contorno.setOnClickListener(contornoListener);
        all.setOnClickListener(allListener);
   }
    
	// TODO Auto-generated method stub
    
    //Evento Blur
    OnClickListener blurListener = new OnClickListener(){
		@Override
		public void onClick(View arg0) {
			
		Func.blur(pixel);
		
		bit.setPixels(pixel, 0, largura, 0, 0, largura, altura);
		imagem.setImageBitmap(bit);
		
		System.out.println("blur");
		}
    };
    
    //Preto e Branco
    OnClickListener cinzaListener = new OnClickListener(){
		@Override
		public void onClick(View arg0) {
			
		Func.pretoBranco(pixel);
		
		bit.setPixels(pixel, 0, largura, 0, 0, largura, altura);
		imagem.setImageBitmap(bit);
		
		System.out.println("cinza");
		
		}
    };
    
    //Saturacao
    OnClickListener satListener = new OnClickListener(){
		@Override
		public void onClick(View arg0) {
			
		Func.saturacao(pixel);
		
		bit.setPixels(pixel, 0, largura, 0, 0, largura, altura);
		imagem.setImageBitmap(bit);
		
		System.out.println("saturation");
		
		}
    };
    
    //Desenho
    OnClickListener toonListener = new OnClickListener(){
		@Override
		public void onClick(View arg0) {
			
		Func.Toon(pixel);
		
		bit.setPixels(pixel, 0, largura, 0, 0, largura, altura);
		imagem.setImageBitmap(bit);
		
		System.out.println("toon");
		
		}
    };
    
    //Nanquim (Threshold)
    OnClickListener nanquimListener = new OnClickListener(){
		@Override
		public void onClick(View arg0) {
		
		Func.pretoBranco(pixel);
		Func.threshold(pixel);
		
		bit.setPixels(pixel, 0, largura, 0, 0, largura, altura);
		imagem.setImageBitmap(bit);
		
		System.out.println("nanquim");
		
		}
    };
    
    //contorno
    OnClickListener contornoListener = new OnClickListener(){
		@Override
		public void onClick(View arg0) {
		
	
		Func.contorno(pixel);
		
		bit.setPixels(pixel, 0, largura, 0, 0, largura, altura);
		imagem.setImageBitmap(bit);
		
		System.out.println("contorno");
		
		}
    };
    
    //All
    OnClickListener allListener = new OnClickListener(){
		@Override
		public void onClick(View arg0) {
			
			int vet3[] = new int[max];
			
			for(int i=0; i<max; i++){
			vet3[i] = pixel[i];
			}
			
			Func.blur(vet3);
			Func.Toon(vet3);
			
			Func.pretoBranco(pixel);
			Func.contorno(pixel);
			
			for(int i=0; i<max; i++){
				if(pixel[i]==0xff000000){
					//pixel[i]=vet3[i];
					vet3[i]=pixel[i];
				}
			}
			
			
			bit.setPixels(vet3, 0, largura, 0, 0, largura, altura);
			imagem.setImageBitmap(bit);
			
			System.out.println("all");
		}
    };
    
    //Evento Botao Volta
    OnClickListener negativoListener = new OnClickListener(){
		@Override
		public void onClick(View arg0) {
		Func.negativo(pixel);
		
		bit.setPixels(pixel, 0, largura, 0, 0, largura, altura);
		imagem.setImageBitmap(bit);
		
		System.out.println("negativo");
		}
    };
}