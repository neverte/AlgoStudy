package baekjoon_01001_02000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;


//[실버 2] DFS와 BFS
//https://www.acmicpc.net/problem/1260
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_1260_DFS와BFS_최민수 {
	
	static class Node {
		int vertex;
		Node next;
		public Node(int vertex, Node next) {
			super();
			this.vertex = vertex;
			this.next = next;
		}
		
		public Node(int vertex) {
			super();
			this.vertex = vertex;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Node [vertex=").append(vertex).append(", next=").append(next).append("]");
			return builder.toString();
		}
		
		
	}
	
	static Node[] adjList;	//연결리스트의 첫번째 노드, head만 쥐고있을 것이다.
	static boolean[] visited; //dfs용
	
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_1260"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//정점의 개수 n 1~1000, 간선의 개수 m 1~1만, 탐색을 시작할 정점의 번호 v
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		
		
		adjList = new Node[n+1]; //각 노드별로 이어지는 애들 달꺼다. 1번부터 시작해서 +1
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			if(adjList[from] == null) {
				adjList[from] = new Node(to, adjList[from]);
			}else {
				for(Node temp = adjList[from]; temp != null; temp = temp.next) {
					if(temp.vertex > to) {
						adjList[from] = new Node(to, adjList[from]);
						break;
					}
					if(temp.next == null){
						temp.next = new Node(to, null);
						break;
					}
					if(temp.next.vertex > to) {
						temp.next = new Node(to, temp.next);
						break;
					}
				}
			}
			if(adjList[to] == null) {
				adjList[to] = new Node(from, adjList[to]);
			}else {
				for(Node temp = adjList[to]; temp != null; temp = temp.next) {
					if(temp.vertex > from) {
						adjList[to] = new Node(from, adjList[to]);
						break;
					}
					if(temp.next == null){
						temp.next = new Node(from, null);
						break;
					}
					if(temp.next.vertex > from) {
						temp.next = new Node(from, temp.next);
						break;
					}
				}
			}
		}
				
		visited = new boolean[n+1]; //1번부터 시작해서 +1
		dfs(v);
		System.out.println();
		bfs(n, v);
		
		
		br.close();
	}


	//방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문
	private static void dfs(int current) {
		visited[current] = true;
		System.out.print(current+" ");
		
		for(Node temp = adjList[current]; temp != null; temp = temp.next) {
			if(visited[temp.vertex]) continue;
			dfs(temp.vertex);
		}
		
	}
	

	private static void bfs(int n, int start) {
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		boolean[] v = new boolean[1001];
		
		q.offer(start);
		v[start] = true;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			System.out.print(current+" ");
			v[current] = true;
			for(Node temp = adjList[current]; temp != null; temp = temp.next) {
				if(!v[temp.vertex]) {
					q.offer(temp.vertex);
					v[temp.vertex] = true;
				}
			}
		}
		
		
	}
}
