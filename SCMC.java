// ALUMNO: Rafael Moret Galán
//         Cristina Espejo Roque
// GRUPO y TITULACION: Ingeniería informática grupo C

/**
 *
 * Clase para resolucion del problema de la
 * Supersecuencia Comun mas Corta para dos cadenas
 * 
 */

import java.util.Random;

public class SCMC {
	
	private String r, t; // Las dos cadenas de la instancia
	private String sigma; // El alfabeto de la instancia
	private int m[][]; // la matriz para resolucion por Prog. Dinamica
	private static Random rnd = new Random(); // generador de aleatorio
	
	/**
	 * Crea una instancia del problema
	 * @param sigma : alfabeto para la instancia
	 * @param r     : primera cadena
	 * @param t     : segunda cadena
	 */
	public SCMC(String sigma, String r, String t) {
		this.r = r;
		this.t = t;
		this.sigma = sigma;
		m = new int[1+r.length()][1+t.length()];

	}
		
	/**
	 * Crea una instancia aleatoria del problema
	 * @param longMax  : longitud maxima de las cadenas
	 * @param tamSigma : tama�o del alfabeto
	 */
	public SCMC(int longMax, int tamSigma) {		
		this.sigma = Utils.alfabeto(tamSigma);
		r = Utils.cadenaAleatoria(rnd.nextInt(1+longMax),sigma);
		t = Utils.cadenaAleatoria(rnd.nextInt(1+longMax),sigma);
		m = new int[1+r.length()][1+t.length()];

	}

	public String r(){
		return r;
	}

	public String t(){
		return t;
	} 
	
	public String sigma(){
		return sigma;
	}

	public int m(int i, int j){
		if (i<m.length && j<m[0].length) {
			return m[i][j];
		} else {
			return -1;
		}
	}

		
	/**
	 * Soluciona la instancia por Prog Dinamica, es decir, rellena
	 * la tabla @m
	 */
	public void solucionaPD(){
		for(int i = 0; i <= r.length(); i++){
			for(int j = 0; j <= t.length(); j++){
				if(i == 0 && j >= 0){
					m[i][j] = j;
				} else if(j == 0 && i >0){
					m[i][j] = i;
				} else if (r.charAt(i-1) == t.charAt(j-1)){
					m[i][j] = 1 + m[i-1][j-1];
				} else if (r.charAt(i-1) != t.charAt(j-1)){
					m[i][j] = 1 + Math.min(m[i-1][j], m[i][j-1]);
				}
			}
		}
	}



	/**
	 * @return : devuelve la longitud de la solucion
	 *           a la instancia, es decir, la longitud 
	 *           de la supersecuencia comun m�s corta de @r y @t
	 *           a partir de la tabla obtenida por Prog Dinamica
	 */
	public int longitudDeSolucionPD(){
		return m(r.length(), t.length());
	}

	/**
	 * @return Devuelve una solucion optima de la instancia, es decir
	 *         una supersecuencia comun mas corta de @r y @t
	 */
	public String unaSolucionPD(){
		int i = r.length();
		int j = t.length();

		String res = "";
		while(i > 0 && j > 0){
			if(r.charAt(i-1) == t.charAt(j-1)){
				res = r.charAt(i-1) + res;
				i--;
				j--;
			} else {
				if(m[i][j] == 1 + m[i-1][j]){
					res = r.charAt(i-1) + res;
					i--;
				} else {
					res = t.charAt(j-1) + res;
					j--;
				}
			}
		}
		if(i>0){
			res = r.substring(0, i) + res;
		} else {
			res = t.substring(0, j) + res;
		}
		
		return res;
	}
	

		
	// representacion como String de la instancia
	public String toString(){
		return "Sigma="+Utils.entreComillas(sigma)
		        +", r="+Utils.entreComillas(r)
		        +", s="+Utils.entreComillas(t);
	}



	// Obtiene una solucion al problema por "fuerza bruta"
	public String unaSolucionFB() {
		int l = Math.max(r.length(),t.length());
		String res = null;
		for(l=Math.max(r.length(),t.length()); res==null; l++)
		  res = unaSolucionFB("",l);
		return res;
	}

	// m�todo auxiliar recursivo
	private String unaSolucionFB(String s, int l) {
		String str = null;
		if(l==0) {
			if(Utils.esSupersecuencia(s,r) && Utils.esSupersecuencia(s,t))
				str = s;
		}	
		else
			for(int i=0; i<sigma.length(); i++) {
				str = unaSolucionFB(s+sigma.charAt(i),l-1);
				if(str!=null) break;
			}
		return str;
	}
	
	
}
