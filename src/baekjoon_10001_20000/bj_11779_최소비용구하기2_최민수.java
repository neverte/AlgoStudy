package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//[골드 3] 최소비용 구하기 2
//https://www.acmicpc.net/problem/11779
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_11779_최소비용구하기2_최민수 {

	static int cityNum, busNum;
	static int[][] mapCost;
	static boolean[] visited;
	static int INF = 1500000000; // 10억이 최대

	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_11779"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		cityNum = Integer.parseInt(br.readLine());
		busNum = Integer.parseInt(br.readLine());
		mapCost = new int[cityNum + 1][cityNum + 1];
		visited = new boolean[cityNum + 1];

		// 초기화
		initialize();

		// 버스 입력
		for (int i = 0; i < busNum; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			if (mapCost[start][end] > cost) {
				mapCost[start][end] = cost;
			}
		}

		StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
		int startCity = Integer.parseInt(st2.nextToken());
		int endCity = Integer.parseInt(st2.nextToken());

		// 다익스트라를 돌릴껀데 그 경로도 저장할꺼다
		// 각 점마다 해당 위치에 도달하는 최소 경로 추가.
		Node answer = dijkstra(startCity, endCity);
		bw.write(answer.distance + "\n");
		bw.write(answer.history.size() + "\n");
		for (int i = 0; i < answer.history.size(); i++) {
			bw.write(answer.history.get(i) + " ");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	static class Node implements Comparable<Node> {
		int index; // 몇번 노드인가
		ArrayList<Integer> history; // 경로를 저장할 것임.
		int distance; // 시작점 -> 이 노드까지 도달하는데 필요한 비용

		public Node(int index, ArrayList<Integer> history, int distance) {
			this.index = index;
			this.history = history;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.distance, o.distance);
		}

	}

	private static Node dijkstra(int startCity, int endCity) {

		PriorityQueue<Node> pq = new PriorityQueue<>();
		ArrayList<Integer> al = new ArrayList<>();
		al.add(startCity);
		pq.add(new Node(startCity, al, 0));

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			if (now.index == endCity) {
				return now;
			}

			// 이번 노드 이미 방문한 적이 있으면 스킵
			if (visited[now.index]) {
				continue;
			}
			visited[now.index] = true;

			// now점을 경유지로 해서 새롭게 갈 수 있는 지역 추가
			// start -> now -> 새로운 지점
			for (int i = 1; i <= cityNum; i++) {
				if (!visited[i] && mapCost[now.index][i] != INF
						&& mapCost[startCity][i] >= now.distance + mapCost[now.index][i]) {
					mapCost[startCity][i] = now.distance + mapCost[now.index][i];
					ArrayList<Integer> tempHistory = new ArrayList<>();
					for (int j = 0; j < now.history.size(); j++) {
						tempHistory.add(now.history.get(j));
					}
					tempHistory.add(i);
					pq.add(new Node(i, tempHistory, mapCost[startCity][i]));
				}
			}

		}
		return new Node(startCity, al, 0);
	}

	private static void initialize() {
		for (int i = 1; i < mapCost.length; i++) {
			for (int j = 1; j < mapCost[0].length; j++) {
				if (i == j) {
					continue;
				} else {
					mapCost[i][j] = INF;
				}
			}
		}
	}

}
