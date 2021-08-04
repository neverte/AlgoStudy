package _core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//편향트리가 발생할 수 있기 때문에 RANK 관리를 하면 (완벽하지는 않아도) 보완할 수 있다.

//최소 신장 트리(MST, Minimum Spanning Tree)
public class MST_Kruskal {

	// 간선 정보 표현할 class
	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// 가중치가 작은 기준으로 정렬할 것이다.
			// return this.weight - o.weight;
			return Integer.compare(this.weight, o.weight);
		}

	}

	static Edge[] edgeList; // 간선을 담을 배열

	static int V, E;
	static int parents[]; // 자신의 부모를 가리킬 배열

	// 크기가 1인 단위 집합을 만든다.
	static void make() {
		for (int i = 0; i < V; i++) {
			parents[i] = i; // 자기 자신을 부모로
		}
	}

	// 집합의 대표자를 찾아줌
	static int findSet(int a) {
		// 부모를 봤는데 나랑 같으면 내가 대표자다
		if (parents[a] == a)
			return a;
		// return findSet(parents[a]); //path compression 전
		return parents[a] = findSet(parents[a]); // pathcompression 후. 내 부모를 no1으로 함
	}

	// void로 해도 상관없다.
	static boolean union(int a, int b) {
		// 같은 조직인지 검사
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot)
			return false; // 같은 부모를 둠

		// 부모가 다름. rank 관리 안 하는 코드
		parents[bRoot] = aRoot; // b의 no1의 부모를 a의 no1으로 해 줌
		return true;
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/0318_kruskal_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		parents = new int[V];
		edgeList = new Edge[E];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		} // 간선 리스트

		// 1. 간선리스트 가중치 기준 오름차순 정렬
		Arrays.sort(edgeList);

		make();
		int result = 0; // 가중치의 합
		int count = 0; // 선택한 간선 수

		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) { // 사이클이 발생하지 않았다면
				result += edge.weight;
				if (++count == V - 1)
					break;
			}
		}
		System.out.println(result);

	}

}
