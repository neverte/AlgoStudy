package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

//[골드 5] 연구소
//https://www.acmicpc.net/problem/14502
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_14502_연구소_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_14502"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		// 벽의 개수는 3개이며, 꼭 3개를 세워야 한다.
		//바이러스가 퍼질 수 없는 곳을 안전 영역 (벽, 바이러스 제외, 0인 공간)
		
		//아이디어1. 바이러스는 dfs, bfs로 퍼뜨리면 됨
		//벽 설치를 어떻게 할 것이냐 -> 조합
		//최대공간보다 남은 공간이 더 줄어들면 바로 가지치기
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		//3~8
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		int[][] mapClone = new int[n][m];
		blankSpace = -3; //0의 갯수, 벽 3개만큼 미리 빼 둠.
		ArrayDeque<virus> q = new ArrayDeque<virus>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				mapClone[i][j] = map[i][j];
				if(map[i][j] == 0) blankSpace++;
				else if(map[i][j] == 2) q.offer(new virus(i, j));
			}
		}
		
		blankSpaceClone = blankSpace;
		
		//벽을 설치하고
		buildWall(mapClone, 0, q, 0, 0);

		System.out.println(answer);
		
		br.close();
	}
	
	static int blankSpaceClone;
	static int answer = Integer.MIN_VALUE;
	
	private static void buildWall(int[][] mapClone, int cnt, ArrayDeque<virus> q, int startx, int starty) {
		int n = mapClone.length;
		int m = mapClone[0].length;
		
		if(cnt == 3) {
			for(int[] ii : mapClone)System.out.println(Arrays.toString(ii));
			
			blankSpace = blankSpaceClone;
			
			//세균 번식 시작
			boolean visited[][] = new boolean[n][m];
			ArrayDeque<virus> qClone = q.clone();
			while(!qClone.isEmpty()) {
				dfs(mapClone, visited, qClone.poll());
			}
			
			System.out.println(blankSpace);
//			for(boolean[] ii : visited)System.out.println(Arrays.toString(ii));
			System.out.println();
			
			answer = Math.max(answer, blankSpace);
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if(i < startx) continue;
			for (int j = 0; j < m; j++) {
				if(i <= startx && j < starty) continue;
				if(mapClone[i][j] == 0) {
					mapClone[i][j] = 1;
					if(starty+1 < m) buildWall(mapClone, cnt+1, q, startx, starty+1);
					else buildWall(mapClone, cnt+1, q, startx+1, 0);
					mapClone[i][j] = 0;
				}
			}
		}
		
		
		
	}

	static int blankSpace;
	//상우하좌
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	private static void dfs(int[][] map, boolean[][] v, virus start) {
		if(blankSpace < answer) return;
		if(blankSpace < 0) return;
		
		for (int i = 0; i < 4; i++) {
			int cx = start.x + dx[i];
			int cy = start.y + dy[i];
			
			//배열 범위 밖
			if(cx < 0 || cx >= map.length || cy < 0 || cy >= map[0].length) continue;
			//방문했던 곳
			if(v[cx][cy]) continue;
			//벽이거나, 다른 바이러스인 경우
			if(map[cx][cy] != 0) continue;
			
			v[cx][cy] = true;
//			map[cx][cy] = 2;
			blankSpace--; //0이 제거되었으니
			dfs(map, v, new virus(cx, cy));
			v[cx][cy] = false;
		}
		
	}

	static class virus{
		int x, y;

		public virus(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
}
