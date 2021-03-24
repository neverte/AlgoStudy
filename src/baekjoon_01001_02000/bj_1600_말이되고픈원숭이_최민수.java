package baekjoon_01001_02000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

//[골드 5] 말이 되고픈 원숭이
//https://www.acmicpc.net/problem/1600
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_1600_말이되고픈원숭이_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_1600"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//12방향 DFS하기
		//말 점프 회수 k: 0~30
		int k = Integer.parseInt(br.readLine());
		
		//가로길이 w, 가로길이 h
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int[][] map = new int[h][w];
		
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean visited[][][] = new boolean[h][w][k+1];
		int count = 0;
		answer = Integer.MAX_VALUE;
		bfs(map, visited, 0, 0, count, k);
		
		
		
		br.close();
	}

	static int answer;
	
	//상우하좌, 말점프 우상, 우하, 좌하, 좌상
	static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2, -1, 0, 1, 0};
	static int[] dy = {1, 2, 2, 1, -1, -2, -2, -1, 0, 1, 0, -1};
	
	private static void bfs(int[][] map, boolean[][][] v, int x, int y, int count, int leftJump) {

		ArrayDeque<int[]> q = new ArrayDeque<int[]>();
		int start[] = new int[4];
		start[0] = x;
		start[1] = y;
		start[2] = count;
		start[3] = leftJump;
		q.offer(start);
		v[0][0][leftJump] = true;
		
		int targetx = map.length;
		int targety = map[0].length;
		
		while(!q.isEmpty()) {
			int now[] = q.poll();
//			System.out.println(now[0] + ", " + now[1]);
			
			if(now[0] == targetx - 1 && now[1] == targety - 1) {
				System.out.println(now[2]);
				return;
			}
			
			for (int i = 0; i < 12; i++) {
				if(i < 8 && now[3] <= 0) continue;
				int cx = now[0] + dx[i];
				int cy = now[1] + dy[i];
				
				//배열 범위 밖
				if(cx < 0 || cx >= targetx || cy < 0 || cy >= targety) continue;
				//장애물
				if(map[cx][cy] == 1) continue;
				//방문한 곳
				if(i < 8) {
					if(v[cx][cy][now[3]-1]) continue;
					v[cx][cy][now[3]-1] = true;
				}else {
//					System.out.println(cx);
//					System.out.println(cy);
//					System.out.println(now[3]);
//					System.out.println("--------------");
					if(v[cx][cy][now[3]]) continue;
					v[cx][cy][now[3]] = true;
				}
				
				
				int next[] = new int[4];
				next[0] = cx;
				next[1] = cy;
				next[2] = now[2]+1;
				if(i < 8) next[3] = now[3]-1;
				else next[3] = now[3];				
				q.offer(next);
			}
			
		}
		//시작점에서 도착점까지 갈 수 없는 경우엔 -1을 출력한다.
		System.out.println(-1);
	}

}
//시간초과: DFS => BFS
