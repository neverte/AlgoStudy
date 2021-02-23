package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[실버 4] 배열 돌리기 1
//https://www.acmicpc.net/problem/16926
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_16926_최민수 {
//		1하 2우 3상 4좌
	static int[] delX = {0, 1, 0, -1, 0};
	static int[] delY = {0, 0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_16926"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		//[][][0]에는 값, [][][1]에는 방문여부
		int[][][] map = new int[N][M][2];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j][0] = Integer.parseInt(st2.nextToken());
			}
		}
	
		//R번 돌린다.
		for (int i = 0; i < R; i++) {
			int state = 0; //0초기 1하 2우 3상 4좌
			int posX = 0;
			int posY = 0;
			int prevVal =0;
			int nowVal = 0;
			while(true) {
				posX += delX[state];
				posY += delY[state];
				if(state == 0) {
					//방문 확인
					map[posX][posY][1] = 1;
					//상태변경
					state = 1; //0 -> 1
					nowVal =  map[posX][posY][0];
					map[posX][posY][0] = 0;
					prevVal = nowVal;
				}else if(state == 1 || state == 2  || state == 3) {
					//1. 범위 밖이거나 방문한적 있으면 다음 상태로
					if(posX < 0 || posX >=N || posY <0 || posY >= M || map[posX][posY][1] == 1) {
						posX -= delX[state];
						posY -= delY[state];
						state += 1;
						continue;
					}
					//2. 방문 도장 찍고
					map[posX][posY][1] = 1;
					//3. 값 변경
					nowVal =  map[posX][posY][0];
					map[posX][posY][0] = prevVal;
					prevVal = nowVal;
				}else if(state == 4) {
					if(posX == posY) {
					nowVal =  map[posX][posY][0];
					map[posX][posY][0] = prevVal;
					prevVal = nowVal;
					}
					//1. 범위 밖이거나 방문한적 있으면 안쪽 사각형으로
					if(posX < 0 || posX >=N || posY <0 || posY >= M || map[posX][posY][1] == 1) {
						posX += 1;
						posY += 1;

						//내부 사각형도 방문했으면 break;
						if( map[posX][posY][1] == 1) {
							state = 0;
							break;
						}
						state = 0;
						continue;
					}
					//2. 방문 도장 찍고
					map[posX][posY][1] = 1;
					nowVal =  map[posX][posY][0];
					map[posX][posY][0] = prevVal;
					prevVal = nowVal;
				}
			}
			
			//방문 기록 초기화
			for (int k = 0; k < N; k++) {
				for (int j = 0; j < M; j++) {
					map[k][j][1] = 0;
				}
			}
		}
		//배열 출력
		for (int k = 0; k < N; k++) {
			for (int j = 0; j < M; j++) {
				sb.append(map[k][j][0]).append(" ");
			}
			sb.setLength(sb.length());
			sb.append("\n");
		}
		sb.append("\n");
		System.out.println(sb);
		
		br.close();
	}
}
