package Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test01_0608 {
	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(System.in);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//가장 많이 등장하는 알파벳과 개수 출력
		int alpha[] = new int[26];
		char[] word = br.readLine().toCharArray();
		for (int i = 0; i < word.length; i++) {
			alpha[word[i] - 65]++;
		}
		
		int max = 0;
		for (int i = 0; i < alpha.length; i++) {
			max = Math.max(max, alpha[i]);
		}
		System.out.println(max);
	}
}
