package swea_d4_d6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 패키지 날려야되나
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d4_1861_정사각형방_구미4반_최민수 {
	//상 우 하 좌
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1}; 
	
	static int[][] room;
	static int N;
	static int count=0;
	
	static int search(int x, int y, int cnt) {
		//좌표가 들어오면 상하좌우에서 +1인지 찾기 비교 BFS느낌으로
		int temp = room[x][y];
//		System.out.println(temp+" "+cnt);
		for (int j = 0; j < dx.length; j++) {
			//범위 밖이면
			if(x+dx[j] >= N || x+dx[j] < 0 || y+dy[j] >= N || y+dy[j] < 0) continue;
			if(room[x+dx[j]][y+dy[j]] == temp + 1) {
				search(x+dx[j],y+dy[j], cnt+1);
			}
		}
		if(cnt > count) count = cnt;
		return cnt;
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d4_1861.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//방번호가 다 다르다.
		//상하좌우 이동가능. (방이 존재해야함 && 현재방에 적힌 숫자보다 정확히 1 더커야 함
		//BFS?
		
		//테스트 케이트
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			//방의 크기 N
			N = Integer.parseInt(br.readLine());
			room = new int[N][N];
			

			for (int j = 0; j < N; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int k = 0; k < N; k++) {
					room[j][k] = Integer.parseInt(st.nextToken());
				}
				
			}
			int max = 0;
			int start = Integer.MAX_VALUE;
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					count = 0;
					search(j, k, 0);
					int temp = count;
					if(temp > max) {
						start = room[j][k];
						max = temp;
					}else if(temp == max) {
						start = Math.min(room[j][k], start);
						max = temp;
						
					}
				}
				
			}
			
			//출력
			//1. 처음에 출발해야 하는 방 번호
			//2. 최대 몇 개의 방을 이동할 수 있는지
			System.out.println("#"+(i+1)+" "+start+" "+(max+1));
			
		}
		
		br.close();
	}

}
