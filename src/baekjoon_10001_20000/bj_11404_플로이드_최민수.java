package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//[골드 4] 플로이드
//https://www.acmicpc.net/problem/11404
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_11404_플로이드_최민수 {

	static int cityNum, busNum;
	static int INF = Integer.MAX_VALUE;
	static int[][] distance;

	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_11404"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		cityNum = Integer.parseInt(br.readLine());
		busNum = Integer.parseInt(br.readLine());
		distance = new int[cityNum + 1][cityNum + 1];
		fill(distance, INF);

		for (int i = 0; i < busNum; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (distance[a][b] > c) {
				distance[a][b] = c;
			}
		}

		floydWarshall();

		for (int i = 1; i <= cityNum; i++) {
			for (int j = 1; j <= cityNum; j++) {
				if (distance[i][j] == INF) {
					bw.write(0 + " ");
				} else {
					bw.write(distance[i][j] + " ");
				}
			}
			bw.write("\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void floydWarshall() {
		// 경유지 - 출발지 - 도착지
		for (int k = 1; k <= cityNum; k++) {
			for (int i = 1; i <= cityNum; i++) {
				// 경유지 == 출발지면 스킵
				if (k == i) {
					continue;
				}
				for (int j = 1; j <= cityNum; j++) {
					if (i == j || j == k) {
						continue;
					}
					if (distance[i][k] != INF && distance[k][j] != INF && distance[i][j] > distance[i][k] + distance[k][j]) {
						distance[i][j] = distance[i][k] + distance[k][j];
					}
				}
			}
		}

	}

	private static void fill(int[][] arr, int val) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (i == j) {
					arr[i][j] = 0;
				} else {
					arr[i][j] = val;
				}
			}
		}
	}
}
