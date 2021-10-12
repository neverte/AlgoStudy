package baekjoon_02001_03000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//[브론즈1] 2차원 배열의 합
//https://www.acmicpc.net/problem/2167
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_2167_2차원배열의합_최민수 {
	static int n, m;
	static int[][][] arr; //[x][y][0]: x, y에 있는 값, [x][y][1] x행에서 1~y번째 까지의 누적합

	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_2167"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n][m][2];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				arr[i][j][0] = Integer.parseInt(st.nextToken());
				if (j == 0) {
					arr[i][j][1] = arr[i][j][0];
				} else {
					arr[i][j][1] = arr[i][j - 1][1] + arr[i][j][0];
				}
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken())-1;
			int y1 = Integer.parseInt(st.nextToken())-1;
			int x2 = Integer.parseInt(st.nextToken())-1;
			int y2 = Integer.parseInt(st.nextToken())-1;
			int temp = 0;
			for (int i = x1; i <= x2; i++) {
				if (y1 == 0) {
					temp += arr[i][y2][1];
				} else {
					temp += arr[i][y2][1] - arr[i][y1 - 1][1];
				}
			}
			bw.write(temp+"\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}
}
