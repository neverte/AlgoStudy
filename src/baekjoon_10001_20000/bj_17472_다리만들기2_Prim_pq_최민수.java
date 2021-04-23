package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//[골드 2] 다리 만들기 2
//https://www.acmicpc.net/problem/17472
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_17472_다리만들기2_Prim_pq_최민수 {
	
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_17472"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//아이디어. MST
		//0. 섬을 목록으로 만들어야 한다. 섬의 개수는 2~6
		//섬 판정은 DFS로 한다? 아니면 union find?
		//1. 먼저 각 섬에서 4방향으로 다리를 다 놔보고, 연결 가능하면 후보에 넣고, 아니면 제거한다
		//2. 모든 다리 후보군을 구했으면 prim을 돌려본다
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		longest = Math.max(n, m);
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//입력 끝
		
		//0. 섬 판정
		int islandNum = 2;
		boolean visited[][] = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j] == 1) {
					//자기 자신도 해당 섬이여야 함
					map[i][j] = islandNum;
					dfs(i, j, map, visited, islandNum++);
				}
			}
		}
//		for(int[] ii: map)System.out.println(Arrays.toString(ii));
		
		//1. 각 섬마다 다리 놔보자.
		//for문으로 돌다가 섬 만나면
		//해당 좌표에서 상우하좌로 다리 일직선 놔보고
		//놓을 수 있으면 Edge 객체 만들어서
		//Edge List에 저장하기
		
		ArrayList<Edge> al = new ArrayList<Edge>(); //edge를 담아둘 것
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(map[i][j] >= 2) {
					makeBridge(i, j, map, al);
				}
			}
		}
		
		//2. Prim
		//필요한 가중치를 우선순위대로 정렬할 것임.
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>(); 
		//정점 방문 스케줄 처리를 위한 q
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		boolean[] visitPrim = new boolean[islandNum];
		
		int sum = 0;

		q.offer(2); //2번 섬을 시작점으로 한다.
		
		while(!q.isEmpty()) {
			int start = q.poll();
			visitPrim[start] = true;
			
			//start -> end
			for (int i = 0; i < al.size(); i++) {
				//1. 시작점이 start 인 녀석 중
				//2. 목적지를 방문하지 않은 간선이라면
				if(al.get(i).startNum == start && !visitPrim[al.get(i).endNum]) {
					//pq에 추가한다
					pq.offer(al.get(i));
				}
			}
			
			
			while(!pq.isEmpty()) {
				Edge edge = pq.poll(); //pq이기 때문에 가장 작은 간선을 찾음.
				if(!visitPrim[edge.endNum]) { //이미 방문한 섬에 도착하는 간선은 스킵
					q.offer(edge.endNum);
					visitPrim[edge.endNum] = true; //방문처리
					sum += edge.distance;
					break; //간선개수 확인해서 나갈 수 있도록 바꾸기.
					
					
					//86~94 이동.
					
				}
				
				
				
			}
		}
		
//		for(int[] ii: map)System.out.println(Arrays.toString(ii));
		
		//visitPrim을 돌면서 하나라도 false이면 해당 섬을 방문하지 않은 것
		boolean canBuild = true;
		for (int i = 2; i < visitPrim.length; i++) {
			if(!visitPrim[i]) canBuild = false;
		}
		
		//모든 섬을 연결하는 것이 불가능하면 -1을 출력한다.
		if(canBuild) System.out.println(sum);
		else System.out.println(-1);

		br.close();
	}
	

	static int dx[] = {-1, 0, 1, 0}; //상우하좌
	static int dy[] = {0, 1, 0, -1};
	static int longest;

	private static void dfs(int startx, int starty, int[][] map, boolean[][] v, int num) {
		int n = map.length;
		int m = map[0].length;
		
		for (int i = 0; i < 4; i++) {
			int cx = startx + dx[i];
			int cy = starty + dy[i];
			
			//배열 범위 밖
			if(cx < 0 || cx >= n || cy < 0 || cy >= m) continue;
			//방문 했으면
			if(v[cx][cy]) continue;
			//땅이 아닌 바다면
			if(map[cx][cy] == 0) continue;
			
			v[cx][cy] = true;
			map[cx][cy] = num;
			dfs(cx, cy, map, v, num);
		}
		
	}
	
	static class Edge implements Comparable<Edge>{
		int startX, startY, startNum, endX, endY, endNum, distance;

		public Edge(int startX, int startY, int startNum, int endX, int endY, int endNum, int distance) {
			super();
			this.startX = startX;
			this.startY = startY;
			this.startNum = startNum;
			this.endX = endX;
			this.endY = endY;
			this.endNum = endNum;
			this.distance = distance;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.distance, o.distance);
		}
		
	}
	
	private static void makeBridge(int startX, int startY, int[][] map, ArrayList<Edge> al) {
		int n = map.length;
		int m = map[0].length;
		int islandNum = map[startX][startY];
		
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j < longest; j++) {
				int cx = startX + dx[i] * j;
				int cy = startY + dy[i] * j;
				
				//돌다가 배열 밖을 만난다 = 섬을 못만났다 = 다리 개설 불가
				if(cx < 0 || cx >= n || cy < 0 || cy >= m) break;
				//자기 자신 섬을 만났다.
				//ㄷ자 형태의 섬이면 자기 자신이면 안된다.
				if(islandNum == map[cx][cy]) break;
				
				//돌다가 섬을 만났다 = 다리 개설 가능
				int distance = Math.abs(startX - cx) + Math.abs(startY - cy) - 1; //시작점, 끝점이 섬에 포함되어있어서 1 빼줘야 함.
				
				//섬 A와 B를 연결하는 다리가 중간에 섬 C와 인접한 바다를 지나가는 경우에 섬 C는 A, B와 연결되어있는 것이 아니다
				//길이가 1이면 안된다. "2" 0 "4"
				if(map[cx][cy] >= 2 && distance >= 2) {
					al.add(new Edge(startX, startY, islandNum, cx, cy, map[cx][cy], distance));
					break; //1 0 1 0 0 1를 걸러내기 위함
				}
				else if(map[cx][cy] == 0) continue; //바다를 만나면?
				else break; //섬을 만났는데 거리가 1이하임
			}

		}
		
	}

}
