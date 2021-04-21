package swea_etc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_모의_1953_탈주범검거_최민수 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_모의_1953.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//테케, T
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			//세로 N, 가로 M 5~50
			//멘홀 뚜껑 세로 R, 가로 C 배열 범위 안
			//탈출 후 소요된 시간 L 1~20
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			
			int[][][] map = new int[n][m][2]; //0은 터널모양, 1은 방문 여부
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < m; j++) {
					map[i][j][0] = Integer.parseInt(st.nextToken());
				}
			}
			
			//탈주범은 시간당 1의 거리를 움직일 수 있다.
			ArrayDeque<Integer> q = new ArrayDeque<>();
			//첫 맨홀 방문 처리
			map[r][c][1] = 1;
			q.offer(r);
			q.offer(c);
			bfs(map, n, m, q, l);
			
			//이동할 수 있는 장소 확인 = 방문했던 장소
			int count = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if(map[i][j][1] != 0) count++;
				}
			}
			//출력: 탈주범이 위치할 수 있는 장소의 개수
			System.out.println("#"+(tc+1)+" "+count);
		}
		br.close();
	}
	
	private static void bfs(int[][][] map, int n, int m, ArrayDeque<Integer> q, int l) {
		//상우하좌
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		
		while(!q.isEmpty()) {
			//q가 다 비거나 or L시간 후에 끝나야 함
			int x = q.poll();
			int y = q.poll();

			//시간 종료조건
			if(map[x][y][1] == l) break;
			
			//방문처리
			//상우하좌로 하는데, map[][][0]값보고 예외처리
			for (int i = 0; i < 4; i++) {
				//1) 내 터널로 갈 수 없는 방향 걸러내기
				boolean canMove = true;
				
				switch (map[x][y][0]) { //현재 내 위치
				case 0: //터널 자체가 없음
					canMove = false;
					break;
				case 1: //상우하좌 다 가능
					break;
				case 2: //상, 하 가능
					if(i == 1 || i == 3) canMove = false;
					break;
				case 3: //우, 좌 가능
					if(i == 0 || i == 2) canMove = false;
					break;
				case 4: //상, 우 가능
					if(i == 2 || i == 3) canMove = false;
					break;
				case 5: //우, 하 가능
					if(i == 0 || i == 3) canMove = false;
					break;
				case 6: //하, 좌 가능
					if(i == 0 || i == 1) canMove = false;
					break;
				case 7: //상, 좌 가능
					if(i == 1 || i == 2) canMove = false;
					break;
				}
				if(!canMove) continue;
				
				int nextx = x + dx[i];
				int nexty = y + dy[i];
				
				//배열 범위 밖
				if(nextx < 0 || nextx >= n || nexty < 0 || nexty >= m) continue;
				//이미 방문한 곳
				if(map[nextx][nexty][1] != 0) continue;
				
				//2) 상대편 터널이 나하고 이어져있는지 확인
				//내가 상일 때 상대 하가 이어져 있는지 확인 (상-하), (우-좌) (하-상) (좌-우)
				canMove = false;
				if(i == 0) { //내가 상
					//상대 모양이 1, 2, 5, 6
					if(map[nextx][nexty][0] == 1 || map[nextx][nexty][0] == 2 || map[nextx][nexty][0] == 5 || map[nextx][nexty][0] == 6) canMove = true;
				}else if(i ==1) { //우
					//상대 모양이 1, 3, 6, 7
					if(map[nextx][nexty][0] == 1 || map[nextx][nexty][0] == 3 || map[nextx][nexty][0] == 6 || map[nextx][nexty][0] == 7) canMove = true;
				}else if(i ==2) { //하
					//상대 모양이 1, 2, 4, 7
					if(map[nextx][nexty][0] == 1 || map[nextx][nexty][0] == 2 || map[nextx][nexty][0] == 4 || map[nextx][nexty][0] == 7) canMove = true;
				}else if(i ==3) { //좌
					//상대 모양이 1, 3, 4, 5
					if(map[nextx][nexty][0] == 1 || map[nextx][nexty][0] == 3 || map[nextx][nexty][0] == 4 || map[nextx][nexty][0] == 5) canMove = true;
				}
				if(!canMove) continue;
				
				q.offer(nextx);
				q.offer(nexty);
				//방문처리
				map[nextx][nexty][1] = map[x][y][1] + 1;
				
			}
		}
		
	}

}
