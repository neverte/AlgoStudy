package baekjoon_01001_02000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//[실버 1] 동물원
//https://www.acmicpc.net/problem/1309
//참고: https://sundries-in-myidea.tistory.com/78
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_1309_동물원_최민수 {

	static int dp[][];
	static int N;
	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_1309"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		//[높이][사자 넣는 경우의 수]: 에서 배치할 수 있는 경우의 수
		//0: 사자 X, 사자 왼, 사자 오 (왼오 값이 같으니까 하나로 합침)
		dp = new int[N + 1][2];
		
		dp[1][0] = 1;
		dp[1][1] = 1;

		for (int i = 2; i <= N; i++) {
			dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i-1][1]) % 9901;
			dp[i][1] = (dp[i - 1][0] + dp[i - 1][1]) % 9901;
		}

		bw.write((dp[N][0]+dp[N][1]+dp[N][1]) % 9901+"");
		

		bw.flush();
		bw.close();
		br.close();
	}
}
