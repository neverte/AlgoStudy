package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//[실버 5] 무한 문자열
//https://www.acmicpc.net/problem/12871
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_12871_무한문자열_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_12871"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//두 문자열 중 작거나 같은것을 가지고 나머지가 그 작거나 같은것의 복사본인지 확인한다.
		String s1 = br.readLine();
		String s2 = br.readLine();
		
		//그냥 문자열을 서로의 길이만큼 곱해서 판단한다.
		String t1 = "";
		String t2 = "";
		
		for (int i = 0; i < s2.length(); i++) {
			t1 += s1;
		}
		for (int i = 0; i < s1.length(); i++) {
			t2 += s2;
		}
		
		for (int i = 0; i < t1.length(); i++) {
			if(t1.charAt(i) != t2.charAt(i)) {
				System.out.println(0);
				return;
			}
		}
		System.out.println(1);
		
		br.close();
	}
}
