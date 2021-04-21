package swea_d4_d6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

//패키지 날리기
//class Solution으로 바꾸기
//내부 입력 주석처리하기
public class Solution_d4_1249_보급로_구미4반_최민수 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d4_1249.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//참고: https://hoho325.tistory.com/111
		//BFS로 값을 갱신하면서 탐색.
		
		final int INF = Integer.MAX_VALUE;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			
			//지도의 크기 N ~100
			int n = Integer.parseInt(br.readLine());
			int[][][] map = new int[n][n][2]; //[0]에는 공사비용, [1]에는 해당 지점에 도달할 수 있는 최소값
			
			for (int i = 0; i < n; i++) {
				String[] temp = br.readLine().split("");
				for (int j = 0; j < n; j++) {
					map[i][j][0] = Integer.parseInt(temp[j]);
					map[i][j][1] = INF;
				}
			}
			
			ArrayDeque<Integer> q = new ArrayDeque<>();
			q.offer(0);
			q.offer(0);
			map[0][0][1] = 0;
			
			int[] dx = {0, -1, 0, 1}; //좌, 상, 우, 하
			int[] dy = {-1, 0, 1, 0};
			
			int min = Integer.MAX_VALUE;
			
			while(!q.isEmpty()) {
				int x = q.poll();
				int y = q.poll();
				
				//도착지에 도착한 경우
				if(x == n-1 && y == n-1) min = Math.min(min, map[x][y][1]);
				//가지치기
				if(min <= map[x][y][1]) continue;
				
				for (int i = 0; i < 4; i++) {
					int nextx = x + dx[i];
					int nexty = y + dy[i];
					
					//배열 범위 초과
					if(nextx < 0 || nextx >= n || nexty < 0 || nexty >= n) continue;
					
					//방문한 적이 없거나(값이 INF)
					//갱신할 값이 더 적으면 간다
					if(map[nextx][nexty][1] == INF || map[nextx][nexty][1] > map[nextx][nexty][0] + map[x][y][1]) {
						map[nextx][nexty][1] = map[nextx][nexty][0] + map[x][y][1];
						q.offer(nextx);
						q.offer(nexty);
					}
				}
			}
			
//			System.out.println("#"+(tc+1)+" "+map[n-1][n-1][1]);
			System.out.println("#"+(tc+1)+" "+min);
	
		} //태케 끝
		
		br.close();
	}
}

//폐기 아이디어1.
//각 칸에 도달할 수 있는 최소값을 갱신하면서 간다.
//자신의 상하좌우에서 자신에게 도달할 수 있는 값이 가장 적은것 + 자신값을 갱신한다
//도착점에서 거꾸로 간다.