package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//[실버 3] 1로 만들기
//https://www.acmicpc.net/problem/1463
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_1463_1로만들기_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_1463"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//1~10^6
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		dp[1] = 0;
		int target = 2;
		if(N == 1) {
			System.out.println(0);
			return;
		}
		while(true) {
			dp[target] = dp[target - 1] +  1;
			if(target % 3 == 0) {
				dp[target] = Math.min(dp[target], dp[target/3] + 1);
			}
			if(target % 2 == 0) {
				dp[target] = Math.min(dp[target], dp[target/2] + 1);
			}
			if(target == N) break;
			target++;
		}
		System.out.println(dp[target]);
		br.close();
	}

}
