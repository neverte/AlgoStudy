package Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test03_0608 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String st = "ABCDEFG";
		
		//재귀로 찍어라
		print(st, 0);
		
	}

	private static void print(String st, int i) {
		if(st.length() == i) {
			System.out.println();
			return;
		}
		System.out.print(st.charAt(i));
		print(st, i+1);
		System.out.print(st.charAt(i));
		
	}
}
/*
3
ABCAAA

답: C (동률 처리 안함)
*/
