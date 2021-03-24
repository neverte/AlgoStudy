package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d4_1251_하나로_최민수 {
	
	static class Edge implements Comparable<Edge>{
		int start, end;
		double distance;
		
		public Edge(int start, int end, double distance) {
			super();
			this.start = start;
			this.end = end;
			this.distance = distance;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.distance, o.distance);
		}
	}
	
	static int findSet(int element, int[] parents) {
		if(parents[element] == element) return element;
		
		return parents[element] = findSet(parents[element], parents);
	}
	
	static boolean union(int e1, int e2, int[] parents) {
		int root1 = findSet(e1, parents);
		int root2 = findSet(e2, parents);
		if(root1 == root2) return false;
		
		parents[root1] = root2;
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d4_1251.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			
			//섬의 개수 n: 1~1000
			int n = Integer.parseInt(br.readLine());
			
			//각 섬의 좌표
			int[][] island = new int[n][2];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < n; i++) {
				island[i][0] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < n; i++) {
				island[i][1] = Integer.parseInt(st.nextToken());
			}
			
			//환경부담세율실수 E 0~1
			double e = Double.parseDouble(br.readLine());
			
			//모든 섬을 해저터널로 연결하는 것을 목표
			//아이디어 1. 자신과 가장 가까운 섬과 연결. 최적의 해가 안나오는 반례 존재함.
			//아이디어 2. 최소신장트리 알고리즘 - kruskal
			//1000*1000개의 간선 = 10만개
			PriorityQueue<Edge> elist = new PriorityQueue<Edge>();
			for (int i = 0; i < n; i++) {
				for (int j = i+1; j < n; j++) {
					//환경 부담 세율(E)과 각 해저터널 길이(L)의 제곱의 곱(E * L^2)만큼 지불
					int x = Math.abs(island[i][0] - island[j][0]);
					int y = Math.abs(island[i][1] - island[j][1]);
					double dist = Math.sqrt(((double)x*x) + ((double)y*y));
//					double dist = Math.sqrt((double)(x*x) + (double)(y*y));
//					System.out.println(x+","+y+","+dist);
					double val = e * dist * dist;
					elist.offer(new Edge(i, j, val));
				}
			}
			
			//kruskal 부분
			
			//선언
			int[] parents = new int[n];
			
			//make
			for (int i = 0; i < n; i++) {
				parents[i] = i;
			}
			
			double result = 0;
			int count = 0;
			
			//pq에서 edge하나 꺼낸다음, 사이클이 아니면 정답에 추가
			while(!elist.isEmpty()) {
				Edge now = elist.poll();
				if(union(now.start, now.end, parents)) { //true라는 것은 서로 root가 달랐다.
					result += now.distance;
					if(++count == n-1) break;
				}
			}
			
			// 소수 첫째 자리에서 반올림하여 정수 형태로 출력하라
			System.out.println("#" + (tc+1) + " " + Math.round(result));

		}//tc끝

		br.close();
	}

}
