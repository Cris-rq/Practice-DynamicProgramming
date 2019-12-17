import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.io.PrintWriter;

/**
 * @author Pepe Gallardo
 *
 */
abstract public class EvaluacionExperimental {

	String nombreEval; // Nombre para la prueba

	public EvaluacionExperimental(String nombre) {
		nombreEval = nombre;
	}
	
	

	/**
	 * @param scmc : una instancia del problema
	 * @param sols : la lista con todas sus soluciones
	 * @return     : true si la implementación de SCMC pasa la comprobación
	 * 				 para la instancia @scmc
	 */
	abstract public boolean comprobacion(SCMC scmc, ListOfStrings sols);
	
	
	/**
	 * @param fichero : el nombre de un fichero de texto (generado con 
	 *                  el método @vuelcaEjemplos) con instancias resueltas.
	 *                  
	 * Realiza una evaluación experimental del problema, realizando
	 * la comprobación especificada con el método @comprobación
	 * para todas las instancias en el fichero.                 
	 */
	public void realizarCon(String fichero){
		BufferedReader br;
		String sigma, r, t;
		boolean ok = true;
		int numSols;
		
		try {
			br = new BufferedReader(new FileReader(fichero));
			
			while((sigma=br.readLine()) != null) {
				r = br.readLine();
				t = br.readLine();
				// Leer soluciones
				numSols = Integer.parseInt(br.readLine());
				ListOfStrings sols = new ListOfStrings();
				for (int i=0; i<numSols; i++) 
			         sols.add(br.readLine());
				br.readLine();
	
				// Solucionar la instancia
				if(!comprobacion(new SCMC(sigma,r,t), sols))
					ok = false;
			}
			br.close();
			System.out.println(nombreEval+" "+ (ok ? "correcto" : "INCORRECTO!!"));
			
		} catch (IOException e) {
			throw new RuntimeException("Fallo de E/S");
		}	
	}
}
