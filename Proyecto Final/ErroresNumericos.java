import java.lang.Math;

public class ErroresNumericos{
	public static double errorAbsoluto(double trueVal, double aproxVal){		//Err(abs) = | Valor(verdader) - Valor (Aproximado) |
		return (double)Math.round(Math.abs(trueVal - aproxVal)*1000d)/1000d;   //Round value up to 3 decimals
	}
	
	public static double errorRelativo(double trueVal, double aproxVal){	//Err(rel) = | (Val(true) - Val(aprox)) / Val(true) | 
		double errAbs = errorAbsoluto(trueVal,aproxVal);
		return (double)Math.round(Math.abs(errAbs / trueVal)*1000d)/1000d ;
	}
	
	public static double errorRelativoNormalizado(double aproxActual, double aproxAnterior){  //Err(Norm) = | (Val(actual) - Val(ant)) / Val(actual)|
		return errorRelativo(aproxActual,aproxAnterior);
	}
	
	public static double aproxEuler(long n){
		return Math.pow((1 + (1d /n)), n); // Could give Underflow 
		
	}
	
	public  static double aproxEulerTol(int n) { 
		int i = 1;								// Iterator, i != 0 because you can't divide by it
		double a,b;
		a = b = 0;
		double tol = 100; 
		double minTol = (0.5) * Math.pow(2, -127);  // This is the minimum Expressible Positive Number
		while( i < n && Math.abs(tol) > minTol){
			a = aproxEuler(i+1);
			b = aproxEuler(i);
			tol = errorRelativoNormalizado(a,b);
			i++;
		}
		return a; 
	}
	
	public static void main(String[] args){
		//~ ErroresNumericos test = new ErroresNumericos();
		//~ System.out.println("Error absoluto = " + test.errorAbsoluto(3.1416,2.964));
		//~ System.out.println("Error relativo = " + test.errorRelativo(3.1416,2.964));
		//~ System.out.println("Error relativo normalizado = " + test.errorRelativoNormalizado(2.739, Math.sqrt(6))*100 +"%") ;
		//~ System.out.println("Aproximacion de Euler sin tolerancia de error: " + test.aproxEuler(1000000000)); // Esto daría 1/100000 = 0.0 debido a su aproximación a 0
		//~ System.out.println("Aproximacion de Euler con tolerancia de error: " + test.aproxEulerTol(1000000000));
	}							
}
