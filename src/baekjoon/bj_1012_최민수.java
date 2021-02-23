package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[실버 2] 유기농 배추
//https://www.acmicpc.net/problem/1012
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_1012_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_1012"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//아이디어
		//isVisited를 활용한 BFS/DFS
		
		//테케 T
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			//밭의 가로M(1~50), 세로N(1~50), 배추 개수K(1~2500)
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			//배추 밭
			int[][] farm = new int[M][N];
			//배추 심기
			for (int j = 0; j < K; j++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st2.nextToken());
				int y = Integer.parseInt(st2.nextToken());
				farm[x][y] = 1;				
			}
			
			int count = 0;
			//배열의 모든 원소 탐색
			//0이면 넘어가고, 1이면 탐색
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < N; k++) {
					if(farm[j][k] == 1) { //배추 뭉텅이? 발견
						DFS(farm, j, k);
						count++; // 지렁이 추가
					}
				}
			}
			System.out.println(count);
		}
		br.close();
	}
	
	//상, 우, 하, 좌
	static int[] deltaX = {-1, 0, 1, 0 };
	static int[] deltaY = { 0, 1, 0,-1 };
	
	public static void DFS(int[][] arr, int x, int y) {
		arr[x][y] = 0;
		for (int i = 0; i < 4; i++) {
			int movX = x + deltaX[i];
			int movY = y + deltaY[i];
			//배열 범위 밖이면 continue;
			if(movX < 0 || movX >= arr.length || movY <0 || movY >= arr[0].length) continue;
			if(arr[movX][movY] == 1) {
				DFS(arr, movX, movY);
			}
		}
	}
}


