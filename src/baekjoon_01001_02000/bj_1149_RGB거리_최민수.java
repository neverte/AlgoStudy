package baekjoon_01001_02000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[실버1] RGB 거리
//https://www.acmicpc.net/problem/1149
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_1149_RGB거리_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_1149"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//집의 수 n: 2~1000
		int n = Integer.parseInt(br.readLine());
		
		//dp를 할껀데
		//[i][0] 현재까지의 최소값, [i][1] i번쨰 집의 색
		//탐욕이 안되는 예시
		//1 2 2
		//1 99 99		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[][] dp = new int[n][3]; //[i]번째 집에 [0~2]를 칠했을 때의 최소값
		dp[0][0] = Integer.parseInt(st.nextToken());
		dp[0][1] = Integer.parseInt(st.nextToken());
		dp[0][2] = Integer.parseInt(st.nextToken());
		
		int[] rgb = new int[3];
		for (int i = 1; i < n; i++) { //1개는 이미 받았다.
			st = new StringTokenizer(br.readLine(), " ");
			rgb[0] = Integer.parseInt(st.nextToken());
			rgb[1] = Integer.parseInt(st.nextToken());
			rgb[2] = Integer.parseInt(st.nextToken());
			
			//i번째가 빨간색이면 i-1번째는 빨간색이 아님
			dp[i][0] = (dp[i-1][1] < dp[i-1][2] ? dp[i-1][1] : dp[i-1][2]) + rgb[0];
			dp[i][1] = (dp[i-1][0] < dp[i-1][2] ? dp[i-1][0] : dp[i-1][2]) + rgb[1];
			dp[i][2] = (dp[i-1][0] < dp[i-1][1] ? dp[i-1][0] : dp[i-1][1]) + rgb[2];

		}
		int answer = Math.min(dp[n-1][0], dp[n-1][1]);
		answer = Math.min(answer, dp[n-1][2]);
		System.out.println(answer);
	
		br.close();
	}
}
