package Acore;
import java.util.Arrays;
import java.util.Scanner;

public class C2_NextPermutationTest2 {
	
	static int N, R;
	static int[] input;
	static int[] P;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		
		input = new int[N];
		P = new int[N]; //N 크기의 flag 배열
		
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
			
		}
		//약간의 전처리
		int cnt = 0;
		//R개만큼 배열에 1을 넣음. 0011같이.
		while(++cnt <= R) P[N-cnt] = 1;
		System.out.println(Arrays.toString(P));
		
		do {
			for (int i = 0; i < N; i++) {
				if(P[i] == 1) {
					System.out.print(input[i]+" ");
				}
			}
			System.out.println();
		} while (np());
		
		sc.close();
	}

	public static boolean np() {
		int i = N-1;
		while(i > 0 && P[i-1] >= P[i]) i--;
		if(i==0) return false;
		

		int j = N-1;
		while(P[i-1] >= P[j]) j--;
		
		swap(i-1, j);
		
		int k = N-1;
		while(i < k) {
			swap(i++, k--);
		}
		
		return true;
	}
	
	private static void swap(int i, int j) {
		int temp = P[i];
		P[i] = P[j];
		P[j] = temp;
	}

}
