package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//[브론즈 3] 그대로 출력하기
//https://www.acmicpc.net/problem/11718
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_11718_그대로출력하기_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_11718"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		for (int i = 0; i < 100; i++) {
			String temp = br.readLine();
			if(temp == null) break;
			System.out.println(temp);
		}
		
		br.close();
	}
}
