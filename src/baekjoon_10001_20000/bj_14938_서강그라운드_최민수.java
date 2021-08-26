package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//[골드 4] 서강그라운드
//https://www.acmicpc.net/problem/14938
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_14938_서강그라운드_최민수 {
	static int areaNum, searchRange, roadNum;
	static int INF = Integer.MAX_VALUE;
	static int[][] distance;
	static int[] item;

	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_14938"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		areaNum = Integer.parseInt(st.nextToken());
		searchRange = Integer.parseInt(st.nextToken());
		roadNum = Integer.parseInt(st.nextToken());

		distance = new int[areaNum + 1][areaNum + 1];
		item = new int[areaNum + 1];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= areaNum; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}

		fill(distance, INF);

		for (int i = 0; i < roadNum; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			distance[a][b] = c;
			distance[b][a] = c;
		}

		// 모든 지점에서 다익스트라 or
		// 그냥 플로이드 워샬
		floydWarshall();

		int answer = 0;
		for (int start = 1; start <= areaNum; start++) {
			int temp = 0;
			for (int movePoint = 1; movePoint <= areaNum; movePoint++) {
				if (distance[start][movePoint] <= searchRange) {
					temp += item[movePoint];
				}
			}
			answer = Integer.max(answer, temp);
		}

		bw.write(answer + "");

		bw.flush();
		bw.close();
		br.close();
	}

	private static void fill(int[][] arr, int val) {
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j < arr[0].length; j++) {
				if (i == j) {
					arr[i][j] = 0;
				} else {
					arr[i][j] = val;
				}
			}
		}
	}

	private static void floydWarshall() {

		for (int k = 1; k <= areaNum; k++) {
			for (int i = 1; i <= areaNum; i++) {
				if (i == k) {
					continue;
				}
				for (int j = 1; j <= areaNum; j++) {
					if (i == j || j == k) {
						continue;
					}
					if (distance[i][k] != INF && distance[k][j] != INF && distance[i][j] > distance[i][k] + distance[k][j]) {
						distance[i][j] = Integer.min(distance[i][j], distance[i][k] + distance[k][j]);
					}
				}
			}
		}
	}
}
