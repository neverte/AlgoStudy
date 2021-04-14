package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[골드 5] 미세먼지 안녕!
//https://www.acmicpc.net/problem/17144
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_17144_미세먼지안녕_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_17144"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//넓이 R, C 6~50
		//T 1~1000
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		
		int[][][] map = new int[r][c][2]; //[0]에는 현재 상태, [1]에는 확산과정
		
		int clean = 0;
		int[][] cleanLoc = new int[2][2];
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < c; j++) {
				map[i][j][0] = Integer.parseInt(st.nextToken());
				if(clean < 2 && map[i][j][0] == -1) { //공기청정기 위치 저장
					cleanLoc[clean][0] = i;
					cleanLoc[clean][1] = j;
					clean++;
				}
			}
		}
		
		//항상 1번 열에 설치되어 있고, 크기는 두 행을 차지
		
		//1초동안 아래의 일이 순서대로 일어남
		//1) 미세먼지 확산, 인접한 4방향
		//   인접한 방향에 공기청정기가 있으면 확산 X, 배열범위 밖X
		//   확산되는 양은 미세먼지값/5, 소수점 버림
		//   확산되고 남은 양 원래값 - 확산되는양*확산된방향의개수
		//2) 공기청정기 작동
		//   위쪽은 반시계, 아래쪽은 시계방향으로 바람이 순환
		//   바람이 불면 미세먼지가 방향대로 모두 1칸씩 이동
		//   공기청정기로 들어간 미세먼지는 정화됨
	
		//공기 청정기는 -1
		
		//구현해야되는게
		//1) 미세먼지 확산
		//2) 공기청정기 상단부(반시계)
		//3) 공기청정기 하단부(시계)

		int count = 0;
		while(count++ < t) {
			//1) 미세먼지 확산
			spread(map, r, c);
			//2) 공기청정기 상단부 가동(반시계)
			cleanTop(map, r, c, cleanLoc[0][0], cleanLoc[0][1]);
			//3) 공기청정기 하단부 가동(시계)
			cleanBottom(map, r, c, cleanLoc[1][0], cleanLoc[1][1]);
		}
		
		//출력
		//첫째 줄에 T초가 지난 후 구사과 방에 남아있는 미세먼지의 양을 출력
		int answer = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if(map[i][j][0] > 0) answer += map[i][j][0];
			}
		}
		System.out.println(answer);
		
		br.close();
	}

	private static void cleanBottom(int[][][] map, int r, int c, int ax, int ay) {
		//처음 0을 내뿜으면서 시작된다.
		int movedVal = 0;
		//공기 청정기 위치(ax, ay)에서 오른쪽으로 직진 (왼 -> 오)
		int temp = 0; //옮기기전 임시로 담을 값
		for (int i = 1; i < c; i++) { //0은 공기청정기 자리
			temp = map[ax][i][0]; //옮길 자리에 있는 값 임시 저장
			map[ax][i][0] = movedVal; //옮길 자리에 원래 넣을 값 저장
			movedVal = temp; //다음 자리에 넣을 값 업데이트
		}
		//위 -> 아래
		for (int i = ax+1; i < r; i++) { //얘도 첫번째 칸은 이미 이전 for문이 옮겼기때문에 +1
			temp = map[i][c-1][0];
			map[i][c-1][0] = movedVal;
			movedVal = temp;
		}
		//오 -> 왼
		for (int i = c-2; i >= 0; i--) { //얘도 첫번째 칸은 이미 이전 for문이 옮겼기때문에 -1
			temp = map[r-1][i][0];
			map[r-1][i][0] = movedVal;
			movedVal = temp;
		}
		//아래 -> 위
		for (int i = r-2; i >= ax ; i--) {
			temp = map[i][0][0];
			map[i][0][0] = movedVal;
			movedVal = temp;
		}
		//공기청정기 자리 업데이트
		map[ax][ay][0] = -1;
		
	}

	private static void cleanTop(int[][][] map, int r, int c, int ax, int ay) {
		//처음 0을 내뿜으면서 시작된다.
		int movedVal = 0;
		//공기 청정기 위치(ax, ay)에서 오른쪽으로 직진 (왼 -> 오)
		int temp = 0; //옮기기전 임시로 담을 값
		for (int i = 1; i < c; i++) { //0은 공기청정기 자리
			temp = map[ax][i][0]; //옮길 자리에 있는 값 임시 저장
			map[ax][i][0] = movedVal; //옮길 자리에 원래 넣을 값 저장
			movedVal = temp; //다음 자리에 넣을 값 업데이트
		}
		//아래 -> 위
		for (int i = ax-1; i >= 0; i--) {
			temp = map[i][c-1][0];
			map[i][c-1][0] = movedVal;
			movedVal = temp;
		}
		//오 -> 왼
		for (int i = c-2; i >= 0; i--) {
			temp = map[0][i][0];
			map[0][i][0] = movedVal;
			movedVal = temp;
		}
		//위 -> 아래
		for (int i = 1; i <= ax ; i++) {
			temp = map[i][0][0];
			map[i][0][0] = movedVal;
			movedVal = temp;
		}
		//공기청정기 자리 업데이트
		map[ax][ay][0] = -1;
		
	}

	private static void spread(int[][][] map, int r, int c) {
		//각각의 미세먼지는 확산된 값 + 잔류 값으로 구성된다.
		// 따라서 [0]에 잔류값을 남기고, [1]에 확산값을 저장한다음
		// 모든 미세먼지 확산끝났으면 [0] + [1]을 하고
		//다음 연산을 위해 [1]을 초기화한다.
		
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		
		//1) 확산
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				//먼저 퍼트릴 미세먼지 찾기
				if(map[i][j][0] > 0) {
					int way = 0;
					int val = map[i][j][0] / 5;
					for (int k = 0; k < 4; k++) {
						int x = i + dx[k];
						int y = j + dy[k];
						
						//배열 범위 밖인지
						if(x < 0 || x >= r || y < 0 || y >= c) continue;
						//공기청정기위치인지
						if(map[x][y][0] == -1) continue;
						
						map[x][y][1] += val;
						way++;
					} //4방향 확산 끝
					//잔류값 계산
					map[i][j][0] -= val * way;
				}
			}
		}
		
		//2) 합산
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				map[i][j][0] += map[i][j][1];
				//3) 초기화
				map[i][j][1] = 0;
			}
		}
		
	}
}
