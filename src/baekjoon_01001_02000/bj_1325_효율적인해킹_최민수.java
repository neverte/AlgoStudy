package baekjoon_01001_02000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//[실버 2] 효율적인 해킹
//https://www.acmicpc.net/problem/1325
//참고: https://wonit.tistory.com/556
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_1325_효율적인해킹_최민수 {

	//[i]로 해킹할 수 있는 것 저장
	static ArrayList<ArrayList<Integer>> connection = new ArrayList<>();
	static int N, M;
	static boolean[] visited;
	static int[] result;
	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_1325"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++) {
			connection.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int victim = Integer.parseInt(st.nextToken());
			int hacker = Integer.parseInt(st.nextToken());
			connection.get(victim).add(hacker);
		}

		result = new int[N + 1];
		int maxAnswer = 0;

		for (int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
			bfs(i, visited);
		}

		for (int i = 1; i <= N; i++) {
			maxAnswer = Math.max(maxAnswer, result[i]);
	}

	for (int i = 1; i <= N; i++) {
			if(result[i] == maxAnswer) {
					bw.write(i + " ");
			}
	}


		bw.flush();
		bw.close();
		br.close();
	}

	private static void bfs(int now, boolean[] visited) {

		Queue<Integer> q = new LinkedList<>();
		q.add(now);
		visited[now] = true;

		while(!q.isEmpty()){
			int front = q.poll();
			for (int i : connection.get(front)) {
				if (!visited[i]) {
					visited[i] = true;
					q.add(i);
					result[i]++;
				}
			}
		}

	}
}
