package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//[실버2] 스티커
//https://www.acmicpc.net/problem/9465
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_9465_스티커_최민수 {
	static int T, n;
	static int[][] value, dp;

	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_9465"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			n = Integer.parseInt(br.readLine());
			value = new int[2][n];
			dp = new int[n][3]; //0 위, 1 아래, 2 선택X

			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < n; j++) {
					value[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			dp[0][0] = value[0][0];
			dp[0][1] = value[1][0];
			dp[0][2] = 0;

			for (int i = 1; i < n; i++) {
				dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][2]) + value[0][i];
				dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][2]) + value[1][i];
				dp[i][2] = Math.max(Math.max(dp[i - 1][1], dp[i - 1][2]), dp[i - 1][0]);
			}

			bw.write(Math.max(Math.max(dp[n - 1][1], dp[n - 1][2]), dp[n - 1][0]) + "\n");

		}

		bw.flush();
		bw.close();
		br.close();
	}
	
	
}
