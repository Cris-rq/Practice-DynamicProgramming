
/**
 * @author Pepe Gallardo
 * 
 * La implementación del alumno debe pasar este test experimental
 */
public class TestsCorreccion {

	// Comprueba experimentalmente el método unaSolución
	public static void testUnaSolucion() {
		EvaluacionExperimental eval = 
			new EvaluacionExperimental ("Test de unaSolucion") {
				public boolean comprobacion(SCMC scmc, ListOfStrings sols) {
					scmc.solucionaPD();
					String sol = scmc.unaSolucionPD();
					if(!sols.contains(sol)) {
						System.out.println("Fallo en la evaluacion experimental:");
						System.out.println("La cadena \""+sol+ "\" no es una SCMC de la instancia "+scmc+".");
						System.out.println("Las soluciones validas son: "+sols);
						return false;
					} else 
						return true;
				}
			};
		eval.realizarCon("tests.txt");
		
	}
	
	// Comprueba experimentalmente el método longitudDeSoluciónPD
	public static void testLongSolucion() {
		EvaluacionExperimental eval = 
			new EvaluacionExperimental ("Test de longitudDeSolucionPD") {
				public boolean comprobacion(SCMC scmc, ListOfStrings sols) {
					scmc.solucionaPD();
					int solLen = scmc.longitudDeSolucionPD();
					java.util.Iterator<String> it = sols.iterator();
					int solOptLen = it.next().length(); 
					
					if(solOptLen!=solLen) {
						System.out.println("Fallo en la evaluacion experimental:");
						System.out.println("La longitud "+solLen+ " no es la de la solucion optima para la instancia "+scmc+".");
						System.out.println("Las longitud optima es: "+solOptLen);
						return false;
					} else 
						return true;
				}
			};
		eval.realizarCon("tests.txt");
		
	}
	
	public static void main(String[] args) {
		testUnaSolucion();
		testLongSolucion();
	}	

}
