package _core;

import java.util.Arrays;
import java.util.Scanner;

public class SubsetTest {
	static int N, totalCnt;
	static int[] input;
	static boolean[] isSelected;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		input = new int[N];
		isSelected = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		
		generateSubset(0);
		System.out.println("total: "+totalCnt);
		
		sc.close();
		
	}
	
	//현 원소를 부분집합의 구성에 반영
	private static void generateSubset(int cnt) {
		if(cnt == N) {
			++totalCnt;
			for (int i = 0; i < N; i++) {
				//선택된 애만 찍을껀데,
				//안됐으면 X찍어볼꺼다.
				System.out.print((isSelected[i]?input[i]:"X")+"\t");
			}
			System.out.println(Arrays.toString(input));
			return;
		}
		
		
		//선택
		isSelected[cnt] = true;
		generateSubset(cnt+1);
		//비선택
		isSelected[cnt] = false;
		generateSubset(cnt+1);
	}
}
