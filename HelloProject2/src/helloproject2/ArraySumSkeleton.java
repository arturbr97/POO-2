package helloproject2;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ArraySumSkeleton {
	
	int [] A;
	int [] B;
	int C[];
	

	public ArraySumSkeleton(int[] a, int[] b) {
		this.A = a;
		this.B = b;
	}
	
	public int[] arraySumSeq() {
		C = new int [A.length];
        for(int i = 0; i < A.length; i++) {
            C[i] = A[i] + B[i];
        }
		return C;
	}
	
	public int[] arraySumParallel() {
		
		IntStream s = IntStream.rangeClosed(0, A.length - 1);
		C = s.parallel().filter(a-> A.length == B.length).map(i -> A[i] + B[i]).toArray();
		return C;
		
	}
	
	// TODO criar o método arraySumPar() para realizar o somatório dos vetores A e B em paralelo
	// utilizando IntStream


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Quantidade de elementos no vetor A: ");
		int n = entrada.nextInt();
		System.out.println("Quantidade de elementos no vetor B: ");
		int q = entrada.nextInt();
		
		if(n!=q) {
			System.out.println("Os vetores devem ter o mesmo tamanho.");
			System.exit(0);
		}
		int[] A = new int[n];
		int[] B = new int[q];
		
		for(int i=0;i<n;i++) {
			A[i] = (int)(Math.random() *100);
		}
		
		System.out.println("\n");
		
		for(int i=0;i<n;i++) {
			System.out.println("Elementos de A " + A[i]);
		}
		
		System.out.println("\n");
		
		for(int i=0;i<q;i++) {
			B[i] = (int)(Math.random() * 100);
		}
		
		for(int i=0;i<q;i++) {
			System.out.println("Elementos de B " + B[i]);
		}
		
		System.out.println("\n");
		ArraySumSkeleton L = new ArraySumSkeleton(A, B);
		System.out.println(Arrays.toString(L.arraySumSeq()));
		System.out.println(Arrays.toString(L.arraySumParallel()));

	}

}
