package Etc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//0323 Workshop 2번
public class DP_Ex2 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
//		System.setIn(new FileInputStream("res/baekjoon/bj_input_0000"));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//f(n) = f(n-1) * 2 + f(n-2) * 1;
		int arr[] = new int[6];
		arr[0] = 2;
		arr[1] = 5;
		for (int i = 2; i < 6; i++) {
			arr[i] = arr[i-1] * 2 + arr[i-2] * 1;
		}
		
		for (int i = 0; i < 6; i++) {
			System.out.println((i+1) + "cm: "+arr[i]);
		}
		
		
//		br.close();
	}
}
