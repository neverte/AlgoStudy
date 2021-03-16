package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//[골드 5] 감시
//https://www.acmicpc.net/problem/15683
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_15683_감시_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_15683"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//아이디어
		//1. 각 cctv별 모든 방향 다 돌려서 최소값 찾기
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		//세로 n 1~8, 가로 m 1~8
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		height = n;
		width = m;
		int[][] map = new int[n][m];
		int[][] cctv = new int[8][3];//[0] cctv번호, [1] x좌표, [2]y좌표
		int cctvCnt = 0;
		//0은 빈칸, 6은 벽 1~5는 CCTV
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				//cctv 타입과 위치 따로 저장
				if(map[i][j] > 0 && map[i][j] < 6) {
					cctv[cctvCnt][0] = map[i][j];
					cctv[cctvCnt][1] = i;
					cctv[cctvCnt][2] = j;
					cctvCnt++;
				}
			}
		}
		
		Arrays.sort(cctv, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// 내림차순의 이유, 0번은 cctv가 아니기 때문
				return -(o1[0] - o2[0]);
			}
		});

		//몇번째 CCTV인지
		cctvCheck(0, map, cctv);

		System.out.println(answer);
		br.close();
	}

	static int answer = Integer.MAX_VALUE;
	static int height, width;
	static int mapcl[][] = new int[height][width];
	
	private static void cctvCheck(int cctvNum, int[][] map, int[][] cctv) {
		int cctvType = 0;
		if(cctvNum < 8) cctvType = cctv[cctvNum][0];
		//마지막 cctv이면 끝
		//처음에 배열 크기를 8로 잡아서, 8이 됐거나, type이 0번인 cctv이면 끝낸다.
		if(cctvNum == 8 || cctv[cctvNum][0] == 0) { 
			//사각지대 넓이 갱신
			int temp = 0;
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					if(map[i][j] == 0) temp++;
				}	
			}
			answer = Math.min(answer, temp);
			//넓이가 0되면 바로 끝(가지치기)
			//이 코드 추가하니까 실행 시간 388ms -> 404ms로 증가 ㅋㅋ
			if(answer == 0) {
				System.out.println(answer);
				System.exit(0);
			}
			
			return; 
		}
		
		int cx = 0, cy = 0;
		cx = cctv[cctvNum][1];
		cy = cctv[cctvNum][2];
		mapcl = new int[height][width];
		
		//괜찮은 아이디어가 안생각나서 하드코딩
		if(cctvType == 5) {
			watchUp(cx, cy, map);
			watchRight(cx, cy, map);
			watchLeft(cx, cy, map);
			watchDown(cx, cy, map);
			cctvCheck(cctvNum+1, map, cctv);
		}else if(cctvType == 4) {
			//위 X
			copy(map, mapcl);
			watchRight(cx, cy, mapcl);
			watchLeft(cx, cy, mapcl);
			watchDown(cx, cy, mapcl);
			cctvCheck(cctvNum+1, mapcl, cctv);
			//오른 X
			copy(map, mapcl);
			watchUp(cx, cy, mapcl);
			watchLeft(cx, cy, mapcl);
			watchDown(cx, cy, mapcl);
			cctvCheck(cctvNum+1, mapcl, cctv);
			//아래 X
			copy(map, mapcl);
			watchUp(cx, cy, mapcl);
			watchRight(cx, cy, mapcl);
			watchLeft(cx, cy, mapcl);
			cctvCheck(cctvNum+1, mapcl, cctv);
			//왼 X
			copy(map, mapcl);
			watchUp(cx, cy, mapcl);
			watchRight(cx, cy, mapcl);
			watchDown(cx, cy, mapcl);
			cctvCheck(cctvNum+1, mapcl, cctv);
		}else if(cctvType == 3) {
			//위, 오른 X
			copy(map, mapcl);
			watchLeft(cx, cy, mapcl);
			watchDown(cx, cy, mapcl);
			cctvCheck(cctvNum+1, mapcl, cctv);
			//오른, 아래 X
			copy(map, mapcl);
			watchUp(cx, cy, mapcl);
			watchLeft(cx, cy, mapcl);
			cctvCheck(cctvNum+1, mapcl, cctv);
			//아래, 왼 X
			copy(map, mapcl);
			watchUp(cx, cy, mapcl);
			watchRight(cx, cy, mapcl);
			cctvCheck(cctvNum+1, mapcl, cctv);
			//왼, 위 X
			copy(map, mapcl);
			watchRight(cx, cy, mapcl);
			watchDown(cx, cy, mapcl);
			cctvCheck(cctvNum+1, mapcl, cctv);
		}else if(cctvType == 2) {
			//위, 아래 O
			copy(map, mapcl);
			watchUp(cx, cy, mapcl);
			watchDown(cx, cy, mapcl);
			cctvCheck(cctvNum+1, mapcl, cctv);
			//오른, 왼 O
			copy(map, mapcl);
			watchRight(cx, cy, mapcl);
			watchLeft(cx, cy, mapcl);
			cctvCheck(cctvNum+1, mapcl, cctv);
		}else if(cctvType == 1) {
			//위 O
			copy(map, mapcl);
			watchUp(cx, cy, mapcl);
			cctvCheck(cctvNum+1, mapcl, cctv);
			//오른 O
			copy(map, mapcl);
			watchRight(cx, cy, mapcl);
			cctvCheck(cctvNum+1, mapcl, cctv);
			//아래 O
			copy(map, mapcl);
			watchDown(cx, cy, mapcl);
			cctvCheck(cctvNum+1, mapcl, cctv);
			//왼 O
			copy(map, mapcl);
			watchLeft(cx, cy, mapcl);
			cctvCheck(cctvNum+1, mapcl, cctv);		
		}
	}


	private static void copy(int[][] map, int[][] mapcl) {
		int height = map.length;
		int width = map[0].length;
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				mapcl[i][j] = map[i][j];
			}
		}
		
	}


	private static void watchUp(int cx, int cy, int[][] map) {
		for (int i = cx; i >= 0; i--) {
			if(map[i][cy] == 6) return;
			else if(map[i][cy] == 0) map[i][cy] = 9;
			else continue; //다른 cctv, 이미 감시한 구역인 경우		
		}
	}


	private static void watchRight(int cx, int cy, int[][] map) {
		for (int i = cy; i < map[0].length; i++) {
			if(map[cx][i] == 6) return;
			else if(map[cx][i] == 0) map[cx][i] = 9;
			else continue; //다른 cctv, 이미 감시한 구역인 경우		
		}
		
	}
	private static void watchLeft(int cx, int cy, int[][] map) {
		for (int i = cy; i >= 0; i--) {
			if(map[cx][i] == 6) return;
			else if(map[cx][i] == 0) map[cx][i] = 9;
			else continue; //다른 cctv, 이미 감시한 구역인 경우			
		}
		
	}
	private static void watchDown(int cx, int cy, int[][] map) {
		for (int i = cx; i < map.length; i++) {
			if(map[i][cy] == 6) return;
			else if(map[i][cy] == 0) map[i][cy] = 9;
			else continue; //다른 cctv, 이미 감시한 구역인 경우			
		}
		
	}
}
