/**
 * @author Pepe Gallardo
 * @modifiedby Ricardo Conejo
 * 
 * Muestra graficas con el comportamiento experimental de la implementacion
 */

public class TestsTiempos {

	public static void pruebaFBvsPD() {
		Temporizador tPD = new Temporizador(), tFB = new Temporizador();
		
		int tamSigma = 3; // tamaño de alfabeto
		int numTests = 5; // pruebas por tamaño de cadena
		int maxLong  = 10;
		double mediaTiempos;

		Grafica gr  = new Grafica( "Prog Din\u00e1mica vs Fuerza Bruta ("+tamSigma+") s\u00edmbolos"
				                 , "longitud cadenas" 
				                 , "segundos"
				                 , "%.0f"
				                 , "%.2f"
				                 );
		Grafica.Linea pd = gr.new Linea("Programaci\u00f3n Din\u00e1mica");
		Grafica.Linea fb = gr.new Linea("Fuerza Bruta");
		

		for(int l=2;l<=maxLong;l++){
			tPD.resetear();
			tFB.resetear();
			
			for (int i=0; i<numTests; i++) {
				String sigma = Utils.alfabeto(tamSigma);
				String r = Utils.cadenaAleatoria(l, sigma);
				String s = Utils.cadenaAleatoria(l, sigma);
				SCMC inst = new SCMC(sigma,r,s);
				
				tPD.iniciar();
				inst.solucionaPD();
				String sPD = inst.unaSolucionPD();
				tPD.parar();
			
				tFB.iniciar();
				String sFB = inst.unaSolucionFB();
				tFB.parar();
				
				System.out.println("Pasado tests n\u00FAmero "+i);
			}
			
			// Anotar medias de tiempos en segundos
			mediaTiempos = (double)tPD.tiempoPasado()/ (double)numTests;
			pd.addData((double)l, mediaTiempos*1e-9);
			
			mediaTiempos = (double)tFB.tiempoPasado()/ (double)numTests;
			fb.addData((double)l, mediaTiempos*1e-9);
		
			System.out.println("Pasada prueba de longitud "+l);
		}
	}
	


	public static void pruebaFBvsSigma() {
		Temporizador tFB = new Temporizador();
		
		int numTests = 5; // pruebas por tamaño de cadena
		int maxLong  = 6;
		double mediaTiempos;

		Grafica gr  = new Grafica( "Fuerza Bruta"
				                 , "longitud cadenas" 
				                 , "segundos"
				                 , "%.0f"
				                 , "%.2f"
				                 );

		for(int tamSigma=2;tamSigma<7;tamSigma++) {
			Grafica.Linea ln = gr.new Linea("F Bruta (tama\u00F1o sigma="+tamSigma+")");
			for(int l=2;l<=maxLong;l++){
				tFB.resetear();
				
				for (int i=0; i<numTests; i++) {
					String sigma = Utils.alfabeto(tamSigma);
					String r = Utils.cadenaAleatoria(l, sigma);
					String s = Utils.cadenaAleatoria(l, sigma);
					SCMC inst = new SCMC(sigma,r,s);
				
					tFB.iniciar();
					String sFB = inst.unaSolucionFB();
					tFB.parar();
					
					System.out.println("Pasado tests n\u00FAmero "+i);
				}
				
				// Anotar medias de tiempos en segundos
				mediaTiempos = (double)tFB.tiempoPasado()/ (double)numTests;
				ln.addData((double)l, mediaTiempos*1e-9);
				
				System.out.println("Pasada prueba de longitud "+l);
			}
		}
	}
	
	public static void pruebaPDvsLong() {
		Temporizador tPD = new Temporizador();
		
		int tamSigma = 10;    //tamaño del alfabeto
		int numTests = 5;    // tests por tamaño
		int maxLong  = 3500; // máxima longitud de cadenas
		double mediaTiempos;

		Grafica gr  = new Grafica( "Prog Din\u00e1mica ("+tamSigma+") s\u00edmbolos"
				                 , "longitud cadenas" 
				                 , "segundos"
				                 , "%.0f"
				                 , "%.2f"
				                 );
		
		Grafica.Linea pd = gr.new Linea("Programaci\u00f3n Din\u00e1mica");

		for(int l=3;l<=maxLong;l+=50){
			tPD.resetear();
			
			for (int i=0; i<numTests; i++) {
				String sigma = Utils.alfabeto(tamSigma);
				String r = Utils.cadenaAleatoria(l, sigma);
				String s = Utils.cadenaAleatoria(l, sigma);
				SCMC inst = new SCMC(sigma,r,s);

				// medir tiempo por Prog Dinámica
				tPD.iniciar();
				inst.solucionaPD();
				String sPD = inst.unaSolucionPD();
				tPD.parar();
		
				System.out.println("Pasado tests n\u00FAmero "+i);
			}
			
			// Anotar tiempo medio (en segundos) para la longirud l
			mediaTiempos = (double)tPD.tiempoPasado() / (double)numTests;
			pd.addData((double)l, mediaTiempos*1e-9);
			
			System.out.println("Pasada prueba de longitud "+l);
		}
	}
	

	public static void main(String args[]) {
		pruebaPDvsLong();
		pruebaFBvsSigma();
		pruebaFBvsPD();
	}

	
}
