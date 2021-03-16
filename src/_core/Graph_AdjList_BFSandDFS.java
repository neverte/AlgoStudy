package _core;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
7
8
0 1
0 2
1 3
1 4
2 4
3 5
4 5
5 6
 */

//인접 리스트  //리스트를 직접 구현할 것임.
public class Graph_AdjList_BFSandDFS {
	
	static class Node{
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

	static int N;
	static Node[] adjList;	//연결리스트의 첫번째 노드, head만 쥐고있을 것이다.
	static boolean[] visited; //dfs용
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		int C = Integer.parseInt(in.readLine());
		adjList = new Node[N];
		
		StringTokenizer st = null;
		for (int i = 0; i < C; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			//뒤로 넣으려고 애쓸필요없이 앞으로 넣어도 상관없음.
			adjList[from] = new Node(to, adjList[from]); //새 노드 [to | null]을 만들어서 넣음.
			adjList[to] = new Node(from, adjList[to]);
		}
		
		System.out.println("-------BFS----------");
		bfs();
		
		System.out.println("-------DFS----------");
		visited = new boolean[N];
		dfs(0);

	}

	private static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[N];
		
		int start = 0;
		q.offer(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			System.out.println((char)(current+65));
			
			for (Node temp = adjList[current]; temp != null; temp = temp.next) {
				if(!visited[temp.vertex]) {
					q.offer(temp.vertex);
					visited[temp.vertex] = true;
				}
				
			}
		}
		
	}

	private static void dfs(int current) {
		visited[current] = true;
		System.out.println((char)(current+65));
		
		for (Node temp = adjList[current]; temp != null; temp = temp.next) {
			if(!visited[temp.vertex]) { //방문하지 않은 정점이면 탐색하러 가야한다.
				dfs(temp.vertex);
			}
		}
	}
}
