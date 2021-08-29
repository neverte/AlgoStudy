package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

//[실버 1] 결혼식
//https://www.acmicpc.net/problem/5567
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_5567_결혼식_최민수 {
	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_5567"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 학생들의 학번 1~N, 상근이 1
		// 상근이의 동기의 수 n 2~500
		int n = Integer.parseInt(br.readLine());
		// 리스트의 길이 m 1~1만
		int m = Integer.parseInt(br.readLine());
		// 친구관계 a, b
		int[][] adjMatrix = new int[n][n];
		int count = 1; // 자기자신 1회 + 1과 인접한 애들의 수
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			adjMatrix[a][b] = 1;
			adjMatrix[b][a] = 1;
		}
		for (int i = 0; i < n; i++) {
			if (adjMatrix[0][i] == 1)
				count++;
		}

		boolean visited[] = new boolean[n];
		answer = -1; // 자기자신 제외
		bfs(adjMatrix, visited, count);

		System.out.println(answer);

		br.close();
	}

	static int answer;

	private static void bfs(int[][] adjMatrix, boolean[] visited, int count) {

		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		q.offer(0);
		while (!q.isEmpty()) {
			int now = q.poll();
			if (visited[now])
				continue;
			visited[now] = true;
			answer++;

			if (count-- > 0) { // 예제기준 3>0 자기자신 2>0 친구1 1>0 친구2
				for (int i = 0; i < visited.length; i++) {
					if (adjMatrix[now][i] == 1 && !visited[i]) {
						q.offer(i);
					}
				}

			}
		}
	}
}
