package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_모의_5656_벽돌깨기_최민수 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_모의_5656.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//테케, T
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			//전역변수 초기화
			min = Integer.MAX_VALUE;
			
			//가능한 모든 경우를 해본다 12^4
			
			//구슬을 쏘는 회수 N: 1~4
			//배열 너비 W: 2~12
			//배열 높이 H: 2~15
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			
			//0: original, 1: 0번 구슬 떨어뜨린 후, 2: 1번구슬, 3:2, 4:3
			int[][][] map = new int[h][w][n+1]; 
			
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < w; j++) {
					map[i][j][0] = Integer.parseInt(st.nextToken());
				}
			}
			
			//N 개의 벽돌을 떨어트려 최대한 많은 벽돌을 제거
			//method(몇번째 구슬 떨어진다, map, 구슬이 떨어지는 위치
			drop(0, map, n, w, h);
			
			System.out.println("#"+(tc+1)+" "+min);
		}
		br.close();
	}

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int min = 0;
	
	private static void drop(int marvel, int[][][] map, int end, int width, int height) {
		//마지막 구슬 연산까지 마쳤으면 끝
		if(marvel == end) { //marvel(4) == 
			int sub = 0;
			for (int k = 0; k < height; k++) {
				for (int j = 0; j < width; j++) {
					if(map[k][j][marvel] != 0) sub++;
				}
			}
			//min값 갱신
			min = Math.min(min, sub);
			return;
		}
		
		
		//구슬 떨어뜨려서 발생하는 일
		//i번째에 떨어뜨려보고 재귀 돌리고, i+1번쨰에 떨어뜨려보고
		for (int i = 0; i < width; i++) {
			//1) 배열 복사
			//marvel번째 구슬이 떨어지니 marvel+1번 배열을 복사해야 한다.
			for (int k = 0; k < width; k++) {
				for (int j = 0; j < height; j++) {
					map[j][k][marvel+1] = map[j][k][marvel];
				}
			}
			
			//2) i번쨰 구슬 떨어뜨림
			//구슬이 닿는 부분을 큐에 넣고, 큐에 넣은걸 꺼내면서 걔네가 닫는걸 계속 큐에 넣음
			ArrayDeque<Integer> q = new ArrayDeque<>();
			//2-1) 구슬이 닿는 부분 찾기
			for (int j = 0; j < height; j++) {
				if(map[j][i][marvel+1] != 0) {
					q.offer(j);
					q.offer(i);
					break;
				}
			}
			//2-2) 큐에 있는걸 꺼내면서 연쇄 폭파
			while(!q.isEmpty()) {
				int x = q.poll();
				int y = q.poll();
				//방문했으면
				if(map[x][y][marvel+1] == 0) continue;
				
				//폭발 범위
				int area = map[x][y][marvel+1];
				//자기 자신 폭발
				map[x][y][marvel+1] = 0;
				if(area == 1) continue;
				else { //4방향 탐색
					for (int j = 0; j < 4; j++) {
						for (int k = 1; k < area; k++) {
							int nextx = x + dx[j] * k;
							int nexty = y + dy[j] * k;
							//범위 안인지 체크
							if(nextx < 0 || nextx >= height || nexty < 0 || nexty >= width) break;
							//0이면 패스
							if(map[nextx][nexty][marvel+1] == 0) continue;
							//이 외엔 큐에 넣기
							q.offer(nextx);
							q.offer(nexty);
						}
					}
				}
			}
			
			//3) 공중에 떠 있는 벽돌 정리
			for (int j = 0; j < width; j++) {
				int[] temp = new int[height];
				int count = 0;
				for (int k = height-1; k >= 0; k--) {
					//바닥에서부터 올라가면서 height 길이의 배열에 0이 아닌값을 저장
					if(map[k][j][marvel+1] != 0) temp[count++] = map[k][j][marvel+1];
				}
				
				int count2 = 0;
				for (int k = height-1; k >= 0; k--) {
					//그리고 바닥에서부터 해당 배열 값으로 채우고
					if(count > count2) { //3 0
						map[k][j][marvel+1] = temp[count2++];
					}else {
						//나머지는 0으로 바꿈
						map[k][j][marvel+1] = 0;
					}
				}
			}
			
			
			//4) 재귀 돌림
			//다음 구슬 떨어뜨려야 함.
			drop(marvel+1, map, end, width, height);
		}
	}
}
//폐기 아이디어 1
//w만큼 다 떨어뜨려보고 한번에 가장 많은 벽돌을 제거하는 것을 선택(Greedy?) 반례있음
//01 01 02
//10 01 01
//01 01 01