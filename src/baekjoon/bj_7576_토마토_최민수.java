package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

//[실버 1] 토마토
//https://www.acmicpc.net/problem/7576
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_7576_토마토_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_7576"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//토마토 N * M 상자에 넣어 보관한다.
		//하루 지나면 익은 토마토 상하좌우가 익는다.
		//토마토들이 며칠이 지나면 다 익게 되는지
		//BFS
		
		//M, N 2~1000
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][m];
		
		ArrayDeque<Integer> q = new ArrayDeque<>();
		
		//정수 1은 익은 토마토, 정수 0은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					q.offer(i);
					q.offer(j);
				}
			}
		}
		
		// 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력
		if(q.size() == m*n*2) {
			System.out.println(0);
			System.exit(0);
		}
		
		bfs(map, q);
		
		int count = 0;
		//bfs다 돌고 점검을 한다.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// 토마토가 모두 익지는 못하는 상황이면 -1을 출력해야 한다.
				if(map[i][j] == 0) {
					System.out.println(-1);
					System.exit(0);
				}
				count = Math.max(count, map[i][j]);
			}
		}

		System.out.println(count - 1); //처음에 1부터 시작했음
	
		br.close();
	}

	private static void bfs(int[][] map, ArrayDeque<Integer> q) {
		int n = map.length;
		int m = map[0].length;
		
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		while(!q.isEmpty()) {
			int x = q.poll();
			int y = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nextx = x + dx[i];
				int nexty = y + dy[i];
				
				//범위 체크
				if(nextx < 0 || nextx >= n || nexty < 0 || nexty >= m) continue;
				
				//갈 수 없는 곳(-1) && 이미 방문한 곳(1 이상)
				if(map[nextx][nexty] != 0) continue;
				
				q.offer(nextx);
				q.offer(nexty);
				map[nextx][nexty] = map[x][y] + 1;
				
			}
		}
		
	}
}
