package baekjoon_02001_03000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[브론즈2] 오르막길
//https://www.acmicpc.net/problem/2846
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_2846_오르막길_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_2846"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int min = 0;
		int max = Integer.MIN_VALUE;
		int answer = 0;
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			int temp = Integer.parseInt(st.nextToken());
			if(i==0) min = temp;
			if(temp > max) { //오르막길이 이어진다.
				max = temp;
			}else { //오르막길 끝났다.
				min = temp;
				max = min;
			}
			answer = Integer.max(answer, max-min);
		}
		System.out.println(answer);
	
		br.close();
	}
}
