package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//[실버 3] 2xn 타일링
//https://www.acmicpc.net/problem/11727
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_11727_2xn타일링2_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_11727"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int MOD = 10007;
	
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n+1];
		dp[1] = 1; //|
		if(n>1) dp[2] = 3; //=, ||, ㅁ
		//점화식
		//마지막에 |가 오는 경우 1, -가 오는 경우 1개, ㅁ가 오는 경우
		//dp[n] = dp[n-1] + d[n-2] + d[n-2]
		for (int i = 3; i < n+1; i++) {
			dp[i] = ((dp[i-1] + dp[i-2]) % MOD + dp[i-2]) % MOD;
		}
		System.out.println(dp[n]);
		
		br.close();
	}
}
