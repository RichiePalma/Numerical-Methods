import java.lang.Math;
//Ricardo Palma - A01226922
public class MinimosCuadrados{

	public int[] x,y;
	public double[][] matrix;
    
    public void printMatrix(){
		for(int i = 0; i < this.matrix.length; i++){
			for(int j = 0; j < this.matrix[i].length;j++){
				System.out.print(this.matrix[i][j] + " ");
			}
			System.out.println(" ");
		}
	}
	
	public void setXY(int[] x, int[] y){
		this.x = x;
		this.y = y;
	}
	
	public void getSistemaEcuaciones(int n, int m){  //n is number of points, m is order of function to solve
		int[][] ecuaciones = new int[m+1][m+1];
		int[] resultados = new int[m+1];
		this.matrix = new double[m+1][m + 2];	//Extra space in m+2 in order to include the result of each equation
		
		for(int i = 0; i < m+1; i++){
			for(int j = 0; j < m+1;j++){
				for(int k = 0; k < n; k++){
					ecuaciones[i][j] += Math.pow(this.x[k], i+j);
				}
				this.matrix[i][j] = ecuaciones[i][j];
				System.out.print(ecuaciones[i][j]+"a("+j+") + ");
			}
			for(int k = 0; k < n; k++){
					resultados[i] += Math.pow(this.x[k],i) * this.y[k]; 
			}
			
			this.matrix[i][m+1] = resultados[i];
			System.out.println(" 0 = " + resultados[i] );
		}
	}
 
	public static void main(String[] args){
		int[] x = {1,2,4,5};			//Test case seen in class
		int[] y ={-3,-11,-15,1}; //Test case seen in class
		int n = 4, m = 2, piv = 0;	//n is number of points, m is order of function to solve, piv is the pivot for GaussJordan
		
		MinimosCuadrados test = new MinimosCuadrados();
		GaussJordan testGJ = new GaussJordan();
		test.setXY(x,y);
		test.getSistemaEcuaciones(n,m);
		System.out.println("\n Matriz Original \n");
		test.printMatrix();
		System.out.println("");
		

		
		for (int a = 0; a < m+1; a++) {
            testGJ.pivote(test.matrix, piv, m+1);

            System.out.println("\tRenglon " + (a + 1) + " entre el pivote");
             testGJ.muestramatriz(test.matrix, m+1);

            System.out.println("");

            System.out.println("\tHaciendo ceros");
             testGJ.hacerceros(test.matrix, piv, m+1);

            testGJ.muestramatriz(test.matrix, m+1);
            System.out.println("");
            piv++;
        }
        for (int i = 0; i < m+1; i++) {
            System.out.println("La variable a(" + (i ) + ") es: " + test.matrix[i][m+1]);
        }
        
        System.out.println("\nTal que la funcion de orden " + m + " mejor ajustable es: ");
         for (int i = 0; i < m+1; i++) {
            System.out.print(test.matrix[i][m+1]+"a(" + (i ) + ") + " );
        }
        System.out.print("0  = y" );
	}
}

class GaussJordan{
   public void muestramatriz(double matriz[][], int var) {
        for (int x = 0; x < var; x++) {
            for (int y = 0; y < (var + 1); y++) {
                System.out.print(" " + matriz[x][y] + " |");
            }
            System.out.println("");
        }

    }

   public void pivote(double matriz[][], int piv, int var) {
        double temp = 0;
        temp = matriz[piv][piv];
        for (int y = 0; y < (var + 1); y++) {

            matriz[piv][y] = matriz[piv][y] / temp;
        }
    }

    public void hacerceros(double matriz[][], int piv, int var) {
        for (int x = 0; x < var; x++) {
            if (x != piv) {
                double c = matriz[x][piv];
                for (int z = 0; z < (var + 1); z++) {
                    matriz[x][z] = ((-1 * c) * matriz[piv][z]) + matriz[x][z];
                }
            }
        }
    }
}
