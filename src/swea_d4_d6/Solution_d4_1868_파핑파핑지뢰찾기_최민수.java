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
public class Solution_d4_1868_파핑파핑지뢰찾기_최민수 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d4_1868.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {

			//8방향 탐색
			//전략: 눌렀을 때 0인 곳 위주로 최대한 누른다.
			//1. 배열을 조회하면서 3*3 모두 지뢰인 중심을 q에 저장
			//2. q에서 한개씩 꺼내면서 탐색 시작
			//3. q가 다 비었으면 이제 미발견 갯수 세서 2횟수+미발견하면 됨
			
			//배열의 크기 N 1~300
			int n = Integer.parseInt(br.readLine());
			char[][] map = new char[n][n];
			
			for (int i = 0; i < n; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			//1. 3*3이 모두 지뢰가 아닌것('.') 찾기
			ArrayDeque<Integer> q = new ArrayDeque<>();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					//찾으면 q에 넣기
					if(isEmptyNine(i, j, map, n) == 0) {
						q.offer(i);
						q.offer(j);
					}
					
				}
			}
			
			int click = 0;
			//2. q가 다 빌때까지 bfs탐색			
			ArrayDeque<Integer> bfsq = new ArrayDeque<>();
			while(!q.isEmpty()) {
				int nowx = q.poll();
				int nowy = q.poll();
				
				//이미 다른애가 탐색했다.
				if(map[nowx][nowy] != '.') continue;
				
				//이 점을 시작점으로 하는 bfs탐색
				bfsq.offer(nowx);
				bfsq.offer(nowy);
				
				map[nowx][nowy] = '0';
				
				click++;
				
				while(!bfsq.isEmpty()) {
					int xx = bfsq.poll();
					int yy = bfsq.poll();
					
					//8방향 탐색
					for (int i = 0; i < 8; i++) {
						int nextx = xx + dx[i];
						int nexty = yy + dy[i];
						
						//범위밖이면 무시
						if(nextx < 0 || nextx >= n || nexty < 0 || nexty >= n) continue;
						
						//이미 방문한 곳 or 지뢰이면 무시
						if(map[nextx][nexty] != '.') continue;
						
						//방문처리
						//근데 0이상을 만나는 순간 q에 넣으면 안된다.
						map[nextx][nexty] = Character.forDigit(isEmptyNine(nextx, nexty, map, n), 10);

						//q에 넣기
						if(map[nextx][nexty] == '0') {
							bfsq.offer(nextx);
							bfsq.offer(nexty);
							
						}
					}
				}
			}
			
			//3. 남아있는 애들 확인
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(map[i][j] == '.') click++;
				}
			}
			
			System.out.println("#"+(tc+1)+" "+click);
		}//tc끝
		
		br.close();
	}
	
	private static int isEmptyNine(int x, int y, char[][] map, int len) {
		int nearMines = 0;
		
		for (int i = 0; i < 8; i++) {
			int nextx = x + dx[i];
			int nexty = y + dy[i];
			
			//범위밖이면 무시
			if(nextx < 0 || nextx >= len || nexty < 0 || nexty >= len) continue;
			//지뢰만나면 컷
			if(map[nextx][nexty] == '*') {
				nearMines++;
			}
		}
		
		return nearMines;
	}

	//상방향부터 시계방향
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
}

