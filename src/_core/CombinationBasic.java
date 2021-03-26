package _core;

import java.util.Arrays;
import java.util.Scanner;

public class C1_CombinationBasicTest {
	
	static int N, R;
	static int[] input;
	static int[] numbers;
//	4 4
//	1 2 3 4
//	[1, 2, 3, 4]

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		
		input = new int[N];
		numbers = new int[R];
		
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
			
		}
		combination(0, 0);
		
		sc.close();
	}
	public static void combination(int cnt, int start) {
		if(cnt == R) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for (int i = start; i < N; i++) {
			//[i]를 써야지 [start]쓰면 틀린다
			numbers[cnt] = input[i];
			combination(cnt+1, i+1);
			
		}
	}
	

}
