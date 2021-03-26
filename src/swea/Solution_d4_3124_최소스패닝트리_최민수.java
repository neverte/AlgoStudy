package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d4_3124_최소스패닝트리_최민수 {
	
	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
		
		
	}
	
	static void make(int v) {
		for (int i = 0; i < v; i++) {
			parent[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(parent[a] == a) return a;
		return parent[a] = findSet(parent[a]);
	}
	
	static boolean union(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		if(rootA == rootB) return false;
		
		parent[rootA] = rootB;
		return true;
	}
	
	static Edge[] el; //간선을 담을 배열
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d4_3124.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			//초기화 해야하는 것
			
			//정점 V: 1~10만, 간선 E: 1~20만
			StringTokenizer st =new StringTokenizer(br.readLine(), " ");
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			parent = new int[v];
			make(v);
			el = new Edge[e];
			
			for (int i = 0; i < e; i++) {
				st =new StringTokenizer(br.readLine(), " ");
				//a, b정점이 가중치 c로 연결되어 있다. c는 음수일 수 있다.
				int a = Integer.parseInt(st.nextToken())-1;
				int b = Integer.parseInt(st.nextToken())-1;
				int c = Integer.parseInt(st.nextToken());
				el[i] = new Edge(a, b, c);
			}
			
			Arrays.sort(el);
			
			int result = 0;
			int count = 0;
			
			for (Edge ed : el) {
				if(union(ed.from, ed.to)) {
					result += ed.weight;
					if(++count == v-1) break;
				}
			}
			System.out.println("#"+(tc+1)+" "+result);
			
		}//tc끝

		br.close();
	}

}
