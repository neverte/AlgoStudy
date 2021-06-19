package Etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test02_0608 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//가장 쉬운 방법: 구간 별로 짜른다음에 다 세면 된다.
		//그다음 생각나는 방법: sliding window
		
		int alpha[] = new int[26];
		
		int N = Integer.parseInt(br.readLine());
		char word[] = br.readLine().toCharArray();
		for (int i = 0; i < N-1; i++) { //0번째 부터라 N-2까지 받는다.
			alpha[word[i]-65]++;
		}
		
		//이제부터 슬라이딩 윈도우
		int answer[] = new int[26];
		for (int i = N-1; i < word.length; i++) {
			//넣고
			alpha[word[i]-65]++;
			//최대값 확인하고
			int maxVal = 0;
			int maxPoint = 0;
			for (int j = 0; j < alpha.length; j++) {
				if(alpha[j] > maxVal) maxPoint = j;
			}
			
			answer[maxPoint]++;
			//맨 앞에꺼 빼고
			alpha[word[i - (N-1)]-65]--;
		}
		
		//출력
		int max = 0;
		int where = 0;
		for (int i = 0; i < answer.length; i++) {
			if(answer[i] > max) where = i;
		}
		System.out.println((char)(where+65));
		System.out.println(answer[where]);

	}
}
/*
3
ABCAAA

답: C (동률 처리 안함)
*/
