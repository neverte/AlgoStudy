package swea_d4_d6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d4_4006_고속도로건설2_최민수 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d4_4006.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//아무리 봐도 MST 문제: Prim이나 kruskal
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			//도시의 수 N: 2~5만
			int n = Integer.parseInt(br.readLine());
			//도로 후보의 수
			int m = Integer.parseInt(br.readLine());
			
			PriorityQueue<road> pq = new PriorityQueue<>();
			for (int i = 0; i < m; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int from = Integer.parseInt(st.nextToken())-1; //0번 도시부터
				int to = Integer.parseInt(st.nextToken())-1;
				int cost = Integer.parseInt(st.nextToken());
				pq.offer(new road(from, to, cost));
			}
			
			//K-1) 각자 자신의 부모를 만듦
			int[] parent = new int[n];
			for (int i = 0; i < parent.length; i++) {
				parent[i] = i;
			}
			
			
			long answer = 0;
			while(!pq.isEmpty()) {
				road now = pq.poll();
				
				if(union(now.from, now.to, parent)) {
					answer += now.cost;
//					System.out.println("test");
				}
			}

			System.out.println("#"+(tc+1)+" "+answer);
		}//tc끝
		
		br.close();
	}
	
	static int findParent(int a, int[] parent) {
		if(parent[a] == a) return a;
		
		return findParent(parent[a], parent);
	}
	
	static boolean union(int a, int b, int[] parent) {
		int rootA = findParent(a, parent);
		int rootB = findParent(b, parent);
		
		if(rootA == rootB) return false;
		
		parent[rootB] = rootA;
		
		return true;
	}

	static class road implements Comparable<road> {
		int from, to, cost;

		public road(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(road o) {
			return Integer.compare(this.cost, o.cost);
		}
		
		
	}
}

//pq 이용한 kruskal: 시간초과
