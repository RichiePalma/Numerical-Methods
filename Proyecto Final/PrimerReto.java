import java.lang.Math;

//Metodos numericos
public class PrimerReto{
	
	
	public double[] NewtonRaphson(double volume){		//Escenario 1
		double yi = (double)Math.round(Math.pow(volume, 1/3)*1000d)/1000d; //Asuming the box is a cube so V = xyz equals V = y^3 so ... y = v^(1/3)
		
		double function =  0;		// f(x)
		double primeFunction = 0;			// f'(x)
	
		int maxIterations = 100;
		int i = 0;
		final double tol = 0.005;
		double nextY = 0;
		double err = 1;
		
		
		
		while(err > tol && i < maxIterations){
			//(double)Math.round(Math.abs(trueVal - aproxVal)*1000d)/1000d to round value up to 3 decimals
			System.out.println("iteration " + i + " value "+ yi + " f(x): " + function + " f'(x): " + primeFunction + " err: " + err);
			
			function =  (double)Math.round(((3*Math.pow(yi, 5)) - (2*yi) - 4) * 1000d) / 1000d;		// f(x)
			primeFunction = (double)Math.round(((15*Math.pow(yi,4) )- 2)*1000d)/1000d;			// f'(x)
			
			nextY = yi -  (function / primeFunction);
			err = errorRelativoNormalizado(nextY,yi);
			
			yi =  (double)Math.round(nextY* 1000d)/1000d;		
			i++;
		}
		
		double x = (double)Math.round(Math.pow(yi,2)*1000d) / 1000d;			// Requirement: x = y^2 
		double z = (double)Math.round(volume / Math.pow(yi,3)*1000d) / 1000d; 	// Volume = xyz, since x = y^2 then Volume = (y^3)*z ; therefore z = volume / (y^3)
		double aproxVolume = (double)Math.round((x*yi*z)*1000d)/1000d;
		System.out.println("Value: x " + x + " value y " + yi + " value z " + z + " equals volume " + aproxVolume);
		double[] dimensions = {x,yi,z};
		return dimensions;
		
	}
	
	public double quadraticFormula(double a, double b){	
		double primeA = 12.0; 		// 4 squares removed = 4x^3 , derivative equals 12x^2 . Fixed amount in this case.
		double primeB = (double)Math.round(4*(-a-b)*1000d)/1000d;
		double primeC = (double)Math.round((a*b)*1000d)/1000d;
		
		//~ double tol = 0.005
		//~ double  
		//Quatratic formula has two cases: 1) When you substract the squareroot part of the equation to minus b 2) when you add it instead
		
		double x1 = (double)Math.round((-primeB - Math.sqrt(Math.pow(primeB, 2) - (4*primeA*primeC)))/(2*primeA)*1000d)/1000d;	// case 1
		double x2 = (double)Math.round((-primeB + Math.sqrt(Math.pow(primeB, 2) - (4*primeA*primeC)))/(2*primeA)*1000d)/1000d; //case 2
		
		double  x1_secondOrderVolume = (double)Math.round((primeB + (24*x1))*1000d)/1000d;
		double  x2_secondOrderVolume = (double)Math.round((primeB + (24*x2))*1000d)/1000d;
		
		System.out.println("a: " + primeA + " b: " + primeB + " c: " + primeC + " x1: " + x1 + " x2: " + x2); //for testing
		//System.out.println( x1_secondOrderVolume  +" " + x2_secondOrderVolume );
		
		if(x1_secondOrderVolume < 0 ){
					System.out.println("x1: " + x1 + " is Max");
					return x1;
		}
		else if (x2_secondOrderVolume < 0 ){
				System.out.println("x2: " + x2 + "is Max");
				return x2;
		}
		else{
			return 0; //none
		}

	}
	
	public double NewtonRaphson(double lado, double radio){ //Escenario 3
		//(double)Math.round(()*1000d)/1000d;  rounds up to 3 decimals
		double diametro = (double)Math.round((2 * radio)*1000d)/1000d; 
		double xi = (double)Math.round((lado / 2)*1000d)/1000d;	// X0, first value of x assuming the best case would be drilling up to the half of the cube
		double zg1 =  xi;
		double zg2 = (double)Math.round((lado - (xi/2))*1000d)/1000d;
		double a1 = (double)Math.round((lado * lado )*1000d)/1000d;
		double a2 = (double)Math.round((diametro*xi)*1000d)/1000d;
		
		int maxIterations = 100;
		int i = 0;
		final double tol = 0.005;
		double nextX = 0;
		double err = 1;
		
		while(err > tol && i < maxIterations){
			double a =(-lado* diametro*a1) + (zg1 * a1 * diametro);
			double b  = (a1 * diametro * xi );
			double c = (radio*diametro*Math.pow(xi,2));
			double function = (double)Math.round(((a) + (b) - (c))*1000d)/1000d;
			double d = function / Math.pow((a1-a2),2);			// Before dividing by g(x)^2 
			function = (double)Math.round((d)*1000d)/1000d;		// Divided by g(x)^2
			
			double primeFunction = (double)Math.round(( ((b/xi) - (2*c/xi)) * Math.pow(a1-a2,2) - (( a + b - c ) * ( 2 * -a2/xi )* ( a1 - a2 ) ) )*1000d)/1000d; //Before dividing by g(x)^2 
			primeFunction = (double)Math.round((primeFunction / Math.pow(a1-a2,4))*1000d)/1000d;
			
			nextX = xi -  (function / primeFunction);
			err = errorRelativoNormalizado(nextX,xi);
			
			// Testing prints
			System.out.println("diametro: " + diametro + " xi: " + xi + " zg1: " + zg1 + " zg2: " + zg2 + " a1: " + a1 + " a2 " + a2 + " function: " + function + " f'(x):  " + primeFunction); 
			System.out.println(a + " " + b + " " + c + " "+ d);
			System.out.println("iteration: " + i + " x(i+1): " + nextX + " xi: " + xi + " f(x): "+ function + " f'(x): " + primeFunction + " err: " + err );
		
			
			
			xi =  (double)Math.round(nextX* 1000d)/1000d;		
			i++;
		}
		
		System.out.println("Final value of x: " + xi);
		return xi;
	}
	
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

	public static void main(String[] args){
		PrimerReto test = new PrimerReto();
		System.out.println("---------------------------------- Escenario 1 ----------------------------------");
		test.NewtonRaphson(1);	// V = 1
		System.out.println("---------------------------------- Escenario 2 ----------------------------------");
		test.quadraticFormula(2,1); // largo (a) = 1.5mts, ancho (b) = 0.75mts
		System.out.println("---------------------------------- Escenario 3 ----------------------------------");
		test.NewtonRaphson(50,5); //lado del cubo = 50 cm, radio de la perforación: 5 cms
	}
}


