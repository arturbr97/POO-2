package helloproject2;


import java.util.Arrays;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class MatrixMultiply {
	
    public static int getNCores() {

         return Runtime.getRuntime().availableProcessors();

    }

  /*  public static double[][] matrixMultiplySeq(double[][] A, double[][] B, double[][] C, int linha,int coluna) {
    	for (int i = 0; i < linha; i++) {	
    	 	for (int j = 0; j < linha; j++) {
                		//C[i][j] = 0.0;
                		for (int k = 0; k < coluna; k++) {
                    		C[i][j] += A[i][k] * B[k][j];
                		}
            	}
    	}
    	
    	return C;
    }*/

    public static int[][] matrixMultiplyPar(int [][] A, int [][] B, int [][] C, int linha,int coluna) {
    	for (int i = 0; i < linha; i++) {
    	 	for (int j = 0; j < linha; j++) {
    	 		int a = i,b = j;
                	IntStream.range(0,coluna).forEachOrdered(k-> C[a][b] += A[a][k] * B[k][b]);
            	}
    	}
    	//IntStream.range(0,N).forEachOrdered(a->IntStream.range(0,N).forEachOrdered(j-> IntStream.range(0,N).forEachOrdered(k-> C[a][j] += A[a][k] * B[k][j])));
    	return C;
    }
    
    private static int[][] createMatrix(int linha,int coluna) {
        int [][] input = new int [linha][coluna];

        for (int i=0; i<input.length; i++) {
            for (int j=0; j<input[i].length; j++) {
                input[i][j] = (int) (Math.random()*10);
            }           
        }

        return input;
    }

    public static void printResults(int[][] array) {
    	for (int[] row : array){
    	    System.out.println(Arrays.toString(row));
    	}
    }
    
    
	public static void main(String[] args) {
                 int ncores = getNCores();
		 int N=100;
		 int L1=2,C1=3,L2=3,C2=2;
             int [][] A = createMatrix(L1,C1);
	     int [][] B = createMatrix(L2,C2);
	     int [][] C = new int [L1][C2];
	     int [][] refC = new int [L1][C2];
	     
	     if(L1 == C2 && C1 == L2) {
		     long startTime = System.nanoTime();
		//     C = matrixMultiplySeq(A, B, refC, L1,C1);
		     long timeInNanos = System.nanoTime() - startTime;
		     System.out.println("SEQUENCIAL TERMINEM EM: " + timeInNanos);
		     long startTimeb = System.nanoTime();
		     C = matrixMultiplyPar(A, B, refC, L1,C1);
		     long timeInNanosb = System.nanoTime() - startTimeb;
		     System.out.println("PARALELO TERMINOU EM: " + timeInNanosb);
	     }else {
	    	 System.out.println("Impossível realizar a mutiplicação dessas matrizes!");
	     }
	     System.out.println("A=");
	     printResults(A);
	     	
	     System.out.println("B=");
	     printResults(B);
	     
	     
	     System.out.println("C=");
	     printResults(C);
             
             System.out.println("NUMEROS DE CORES: " + ncores);
	     
	}

}