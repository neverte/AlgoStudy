package baekjoon_01001_02000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//[골드 5] 최단경로
//https://www.acmicpc.net/problem/1753
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_1753_최단경로_최민수 {

	// static int adj[][];

	static class Node implements Comparable<Node> {
		int vertex;
		int weight;

		public Node(int vertex, int weight) {
			super();
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {

			return Integer.compare(this.weight, o.weight);
		}

	}

	static List<Node>[] adjList;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_1753"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 정점의 개수 V 1~2만, 간선의 개수 E 1~30만
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		// 시작 정점의 번호 K: 1~V
		int k = Integer.parseInt(br.readLine()) - 1;

		// adj = new int[v][v];
		adjList = new ArrayList[v];
		for (int i = 0; i < v; i++) {
			adjList[i] = new ArrayList<>();
		}

		// 간선을 나타내는 u->v, 가중치 w, w: 1~10, u, v는 다름
		// 다른 두 정점 사이에 여러 간선이 존재할 수 있음.
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			// if(adj[start][end] == 0) adj[start][end] = weight;
			// else adj[start][end] = adj[start][end] < weight ? adj[start][end] : weight;
			// 이미 값이 있는지 확인
			// boolean isExist = false;
			// for (Node temp = adjList[start]; temp != null; temp = temp.next) {
			// if (temp.vertex == end) { //도착지점 노드 찾음.
			// isExist = true;
			// if (temp.weight > weight) { // 기존 가중치보다 더 작다.
			// temp.weight = weight; // 값 갱신
			// }
			// break;
			// }
			// }
			// if(!isExist) { //기존에 없던 노드면
			// adjList[start] = new Node(end, weight, adjList[start]);
			// }
			// adjList[start] = new Node(end, weight);
			adjList[start].add(new Node(end, weight));

		}

		// 출력 v줄에 걸쳐 출발->i로의 최단 경로값 출력
		// 경로 없으면 INF 출력
		dijkstra(k, v);

		for (int i = 0; i < v; i++) {
			if (distance[i] == INFINITY)
				sb.append("INF\n");
			else
				sb.append(distance[i] + "\n"); // 지금까지 더한 누적값 출력
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	static final int INFINITY = Integer.MAX_VALUE;
	static int distance[];

	private static void dijkstra(int start, int nodeNum) {
		// int nodeNum = adj.length;
		distance = new int[nodeNum];
		boolean visited[] = new boolean[nodeNum];
		PriorityQueue<Node> pq = new PriorityQueue<Node>();

		Arrays.fill(distance, INFINITY);
		distance[start] = 0;
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			int current = now.vertex;

			if (!visited[current]) {
				visited[current] = true;

				for (Node temp : adjList[current]) {
					if (!visited[temp.vertex] && distance[temp.vertex] > distance[current] + temp.weight) {
						distance[temp.vertex] = distance[current] + temp.weight;
						pq.offer(new Node(temp.vertex, distance[temp.vertex]));
					}
				}
			}
		}

	}
}

// 1. 메모리 초과: 아마 인접 행렬 사용 -> 인접 리스트로 교체해볼 예정
// 2. 시간 초과: syso 에서 sb + bw 사용
// 3. 시간 초과: => priority queue사용?
// 4. 시간 초과 해결: dijkstra를 한번만 돌리면 되는데, 각 정점마다 다 돌렸다.
