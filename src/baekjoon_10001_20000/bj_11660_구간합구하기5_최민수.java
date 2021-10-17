package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//[실버 1] 구간 합 구하기 5
//https://www.acmicpc.net/problem/11660
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_11660_구간합구하기5_최민수 {

	static int n, T;
	static long[][][] arr;
	//[x][y][0]: x, y에 있는 값
	//[x][y][1] 0,0 ~ x, y까지의 누적합

	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_11660"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		arr = new long[n][n][2];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				arr[i][j][0] = Integer.parseInt(st.nextToken());
				if (i == 0 && j == 0) {
					arr[i][j][1] = arr[i][j][0];
				}else if (j == 0) {
					arr[i][j][1] = arr[i-1][j][1] + arr[i][j][0];
				}else if (i == 0) {
					arr[i][j][1] = arr[i][j-1][1] + arr[i][j][0];
				} else if(i > 0 && j > 0) {
					arr[i][j][1] = arr[i][j - 1][1] + arr[i-1][j][1] - arr[i-1][j-1][1] + arr[i][j][0];
				}
			}
		}
		
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken())-1;
			int y1 = Integer.parseInt(st.nextToken())-1;
			int x2 = Integer.parseInt(st.nextToken())-1;
			int y2 = Integer.parseInt(st.nextToken())-1;
			long temp = 0;
			if (x1 == 0 && y1 == 0) {
				temp = arr[x2][y2][1];
			} else if (x1 == 0) {
				temp = arr[x2][y2][1] - arr[x2][y1-1][1];
			} else if (y1 == 0) {
				temp = arr[x2][y2][1] - arr[x1-1][y2][1];
			} else {
				temp = arr[x2][y2][1] - arr[x2][y1-1][1] - arr[x1-1][y2][1] + arr[x1-1][y1-1][1];
			}
			bw.write(temp+"\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}
}
