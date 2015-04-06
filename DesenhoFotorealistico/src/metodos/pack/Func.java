package metodos.pack;
import fotorealistico.pack.Photo;
import android.graphics.Color;

public class Func extends Photo {
	
	//static int index=0;
	static int[][] img1;
	
	public static int[] pretoBranco(int vet[]) {

		int r = 0, g = 0, b = 0, res = 0;

		for (int i = 0; i < max; i++) {
		r = (vet[i] >> 16) & 0xff;
		g = (vet[i] >> 8) & 0xff;
		b = vet[i] & 0xff;

		// transformar a img em preto e branco
		res = (r + g + b) / 3;

		r = res;
		g = res;
		b = res;

		vet[i] = Color.rgb(r, g, b);
		}

		return vet;
	}
	
	public static int[] negativo(int vet[]){
		int r = 0, g = 0, b = 0;

		for (int i = 0; i < max; i++) {
		r = (vet[i] >> 16) & 0xff;
		g = (vet[i] >> 8) & 0xff;
		b = vet[i] & 0xff;

		r = 255-r;
		g = 255-g;
		b = 255-b;

		vet[i] = Color.rgb(r, g, b);
		}

		return vet;
	}
	
	//demorado + ou -
	public static int[] blur(int vet[]){
		
		img1 = new int[altura][largura];
		
		int l_cont = 0, r= 0, g= 0, b= 0, cont = 0;
		
		int index=0;
		// separando vetor em 2
		for (int i = 0; i < altura; i++) {
			for (int j = 0; j < largura; j++) {
				img1[i][j] = pixel[index];
				index++;
			}
		}
					
		// Realiza o img1 na imagem
		for (int x = 0; x < altura; x++) {
			for (int y = 0; y < largura; y++) {

				// inicializa variáveis de controle.
				r = 0;
				g = 0;
				b = 0;
				l_cont = 0;

				// obtem as 9 posições

				// img1[x-1][y-1]
				if (x > 0 && y > 0) {

					l_cont++;

					r += (img1[x - 1][y - 1] >> 16) & 0xff;
					g += (img1[x - 1][y - 1] >> 8) & 0xff;
					b += img1[x - 1][y - 1] & 0xff;
				}

				// img1[x-1][y];
				if (x > 0 && y >= 0) {

					l_cont++;

					r += (img1[x - 1][y] >> 16) & 0xff;
					g += (img1[x - 1][y] >> 8) & 0xff;
					b += img1[x - 1][y] & 0xff;
				}

				// img1[x-1][y+1]
				if (x > 0 && y + 1 < largura) {

					l_cont++;

					r += (img1[x - 1][y + 1] >> 16) & 0xff;
					g += (img1[x - 1][y + 1] >> 8) & 0xff;
					b += img1[x - 1][y + 1] & 0xff;
				}

				// img1[x][y-1]
				if (x >= 0 && y > 0) {

					l_cont++;

					r += (img1[x][y - 1] >> 16) & 0xff;
					g += (img1[x][y - 1] >> 8) & 0xff;
					b += img1[x][y - 1] & 0xff;
				}

				// img1[x][y];
				if (x >= 0 && y >= 0) {

					l_cont++;

					r += (img1[x][y] >> 16) & 0xff;
					g += (img1[x][y] >> 8) & 0xff;
					b += img1[x][y] & 0xff;
				}

				// img1[x][y+1]
				if (x >= 0 && y + 1 < largura) {

					l_cont++;

					r += (img1[x][y + 1] >> 16) & 0xff;
					g += (img1[x][y + 1] >> 8) & 0xff;
					b += img1[x][y + 1] & 0xff;
				}

				// img1[x+1][y-1]
				if (x + 1 < altura && y > 0) {

					l_cont++;

					r += (img1[x + 1][y - 1] >> 16) & 0xff;
					g += (img1[x + 1][y - 1] >> 8) & 0xff;
					b += img1[x + 1][y - 1] & 0xff;
				}

				// img1[x+1][y]
				if (x + 1 < altura && y >= 0) {

					l_cont++;

					r += (img1[x + 1][y] >> 16) & 0xff;
					g += (img1[x + 1][y] >> 8) & 0xff;
					b += img1[x + 1][y] & 0xff;
				}

				// img1[x+1][y+1]
				if (x + 1 < altura && y + 1 < largura) {

					l_cont++;

					r += (img1[x + 1][y + 1] >> 16) & 0xff;
					g += (img1[x + 1][y + 1] >> 8) & 0xff;
					b += img1[x + 1][y + 1] & 0xff;
				}

				// Media das cores proximas
				r = r / l_cont;
				g = g / l_cont;
				b = b / l_cont;

				vet[cont] = Color.rgb(r, g, b);

				cont++;
			}
		}
		return vet;
	}

	public static int[] saturacao(int[] vet) {
		
		int a=0, r = 0, g = 0, b = 0;
		
		// life saturation
		for (int i = 0; i < max; i++) {
			a = (vet[i]>>>24);
			r = (vet[i] >> 16) & 0xff;
			g = (vet[i] >> 8) & 0xff;
			b = vet[i] & 0xff;

			r = r+(r/16);
			g = g+(g/16);
			b = b+(b/16);
			
			if(r>=255 || g>=255 || b >=255){
				r=255;
				g=255;
				b=255;
			}
			
			vet[i] = Color.rgb(r, g, b);
			}
		
		/*
		//demorado
		float[] hsv = new float[3];
		
		for (int i = 0; i < max; i++) {
		Color.colorToHSV(vet[i], hsv);
		hsv[1] = hsv[1] / 2;
		vet[i] = Color.HSVToColor(255, hsv);
		}
		*/
		return vet;
	}

	//demorado
	public static int[] Toon(int[] vet) {
		
		float[] hsv = new float[3];
		
		for (int i = 0; i < max; i++) {
		Color.colorToHSV(vet[i], hsv);

		// intensidade das cores

		// Preto
		if (hsv[2] > 0 && hsv[2] <= 0.2) {
			hsv[2] = 0;
		}

		// cores tons escuros
		else if (hsv[2] > 0.2 && hsv[2] <= 0.4) {
			hsv[2] = 0.3f;
		}

		// cores tons medios
		else if (hsv[2] > 0.4 && hsv[2] <= 0.6) {
			hsv[2] = 0.5f;
		}

		// cores tons claros
		else if (hsv[2] > 0.6 && hsv[2] <= 0.8) {
			hsv[2] = 0.7f;
		}

		// branco 1
		else if (hsv[2] > 0.8 && hsv[2] <= 0.95) {
			hsv[2] = 0.9f;
		}

		// branco 2
		else if (hsv[2] > 0.95 && hsv[2] <= 1) {
			hsv[2] = 1.0f;
			hsv[1] = 0;
		}

		// cinza
		else if (hsv[1] >= 0 && hsv[1] <= 0.3f) {
			hsv[1] = 0;
		}

		// Magenta
		if (hsv[0] > 340 && hsv[0] <= 360) {
			hsv[0] = 350;
		}

		// Rosa
		else if (hsv[0] > 280 && hsv[0] <= 340) {
			hsv[0] = 320;
		}

		// Roxo
		else if (hsv[0] > 260 && hsv[0] <= 280) {
			hsv[0] = 270;
		}

		// Azul escuro
		else if (hsv[0] > 220 && hsv[0] <= 260) {
			hsv[0] = 240;
		}

		// Azul claro
		else if (hsv[0] > 190 && hsv[0] <= 220) {
			hsv[0] = 205;
		}

		// Ciano
		else if (hsv[0] > 170 && hsv[0] <= 220) {
			hsv[0] = 180;
		}

		// Verde
		else if (hsv[0] > 70 && hsv[0] <= 160) {
			hsv[0] = 120;
		}

		// Amarelo
		else if (hsv[0] > 50 && hsv[0] <= 70) {
			hsv[0] = 60;
		}

		// Laranja
		else if (hsv[0] > 20 && hsv[0] <= 50) {
			hsv[0] = 25;
		}

		// Vermelho
		else if (hsv[0] > 0 && hsv[0] <= 20) {
			hsv[0] = 0;
		}
		
		vet[i] = Color.HSVToColor(255, hsv);
		}
		return vet;
	}

	public static int[] threshold(int[] vet){
		
		int r = 0, g = 0, b = 0;

		for (int i = 0; i < max; i++) {
		r = (vet[i] >> 16) & 0xff;
		g = (vet[i] >> 8) & 0xff;
		b = vet[i] & 0xff;
		
		if(r<110 && g <110 && b <110){

		r = 0;
		g = 0;
		b = 0;
		}
		else{
		r=255;
		g=255;
		b=255;
		}

		vet[i] = Color.rgb(r, g, b);
		}
		
		return vet;
	}
	
	public static int[] contorno(int[] vet) {

		int vet2[] = new int[max];
		int[][] img2 = new int[altura][largura];

		int index=0;
		// separando vetor em 2
		for (int i = 0; i < altura; i++) {
			for (int j = 0; j < largura; j++) {
				img2[i][j] = vet[index];
				index++;
			}
		}
		
		int x1 = 0, y1 = 0;
		int index2 = 0;
		int r = 0, g = 0, b = 0;
		int r1 = 0;

		//vet3 = vet;
		threshold(vet);

		index=0;
		//for (int i = 0; i < max; i++) {
		//	vet2[i] = vet[i];
		//}
		
		// Translação da imagem + 2
		for (int x = 0; x < altura; x++) {
			for (int y = 0; y < largura; y++) {

				if (x > 1 && y > 1) {
					x1 = (x - 2);
					y1 = (y - 2);

					img2[x1][y1] = vet[index];

					index2 = x1 * largura + y1;
				}

				index++;

				r = (img2[x1][y1] >> 16) & 0xff;
				g = (img2[x1][y1] >> 8) & 0xff;
				b = img2[x1][y1] & 0xff;
				
				vet2[index2] = 0xff000000 | (r << 16) | (g << 8) | b;
			}
		}
		
		// Contorno
		for (int i = 0; i < max; i++) {

			// pegando rgb da imagem 1
			r = (vet[i] >> 16) & 0xff;

			// pegando rgb da imagem 2
			r1 = (vet2[i] >> 16) & 0xff;

			if (r != r1) {
				// se diferente contorna preto
				vet[i] = 0xff000000;
			}

			else {
				// deixar branco
				vet[i] = 0xffffffff;
				//vet3[i] = 0xff000000 | (r << 16) | (g << 8) | b;
			}
			index++;
		}
		
		return vet;
	}

	public static int[] all(int[] vet) {

		int vet2[] = new int[max];
		
		int[][] img1 = new int[altura][largura];
		
		int index=0;
		// separando vetor em 2
		for (int i = 0; i < altura; i++) {
			for (int j = 0; j < largura; j++) {
				img1[i][j] = vet[index];
				index++;
			}
		}
		
		int x1 = 0, y1 = 0;
		int index2 = 0;
		int r = 0, g = 0, b = 0;
		int r1 = 0, g1 = 0, b1 = 0;

		threshold(vet);

		index=0;
		
		// Translação da imagem + 2
		for (int x = 0; x < altura; x++) {
			for (int y = 0; y < largura; y++) {

				if (x > 1 && y > 1) {
					x1 = (x - 2);
					y1 = (y - 2);

					img1[x1][y1] = vet[index];

					index2 = x1 * largura + y1;
				}

				index++;

				r = (img1[x1][y1] >> 16) & 0xff;
				g = (img1[x1][y1] >> 8) & 0xff;
				b = img1[x1][y1] & 0xff;
				
				vet2[index2] = 0xff000000 | (r << 16) | (g << 8) | b;
			}
		}
		
		// Contorno
		for (int i = 0; i < max; i++) {

			// pegando rgb da imagem 1
			r = (vet[i] >> 16) & 0xff;
			g = (vet[i] >> 8) & 0xff;
			b = vet[i] & 0xff;

			// pegando rgb da imagem 2
			r1 = (vet2[i] >> 16) & 0xff;
			g1 = (vet2[i] >> 8) & 0xff;
			b1 = vet2[i] & 0xff;

			if (r != r1) {
				// se diferente contorna preto
				vet[i] = 0xff000000;
			}

			else {
				// deixar branco
				vet[i] = 0xffffffff;
				//vet3[i] = 0xff000000 | (r << 16) | (g << 8) | b;
			}
			index++;
		}
		
		return vet;
	}

}