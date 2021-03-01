package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//[골드 4] 배열돌리기 4
//https://www.acmicpc.net/problem/17406
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_17406_최민수 {
	static int[] r ;
	static int[] c;
	static int[] s;
	static int N, M, K;
	static int[][] map, copyMap;
	
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_17406"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//배열의 크기 [N][M], 회전 연산의 개수 K
		// N, M: 3~60, K: 1~6
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		//배열
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st2.nextToken());
			}
		}

		//연산 K
		r = new int[K];
		c = new int[K];
		s = new int[K];
		for (int i = 0; i < K; i++) {
			//r, c, s
			StringTokenizer st3 = new StringTokenizer(br.readLine(), " ");
			//전체 배열이 [1][1]부터 시작하는게 불편하니까 1씩 빼준다.
			r[i] = Integer.parseInt(st3.nextToken())-1;
			c[i] = Integer.parseInt(st3.nextToken())-1;
			s[i] = Integer.parseInt(st3.nextToken());
		}
		
		//출력
		//각 행에 있는 모든 수의 합 중 최솟값

		vis = new boolean[K];
		permList = new int[K];
		copyMap = new int[N][M];
		answer = Integer.MAX_VALUE;

		copyMap = copy(map, copyMap);
		
		perm(0, copyMap);
		

		System.out.println(answer);
		
		br.close();
	}
	
	public static int[][] rotate(int Kth, int[][] copyM) {
		// K번째 돌려라
		// s가 2면 중심제외 2회전해야됨.
		for (int j = 1; j <= s[Kth]; j++) {
			// 시작 지점(중심
			int startX = r[Kth] - j;
			int startY = c[Kth] - j;
			int temp = copyM[startX][startY];
			// 우, 하, 좌, 상 방향으로 i*2개를 옮기면 1회전
			// 하( [3][3]값을 [2][3]에 넣기 ) 상<-하
			for (int l = 0; l < j * 2; l++) {
				copyM[startX][startY] = copyM[startX + 1][startY];
				startX = startX + 1;
			}
			for (int l = 0; l < j * 2; l++) { // 좌<-우
				copyM[startX][startY] = copyM[startX][startY + 1];
				startY = startY + 1;
			}
			for (int l = 0; l < j * 2; l++) {//하<-상
				copyM[startX][startY] = copyM[startX - 1][startY];
				startX = startX - 1;
			}
			for (int l = 0; l < j * 2; l++) { //우<-좌
				if (l == j * 2 - 1)
					copyM[startX][startY] = temp;
				else
					copyM[startX][startY] = copyM[startX][startY - 1];
				startY = startY - 1;
			}
		}
		return copyM;
	}
	
	//입력 K개에 대한 순열
	static boolean vis[];
	static int[] permList;
	public static void perm(int count, int[][] copyMap) {
		if(count == K) {
			//배열초기화, 원본을 복사
			copyMap = copy(map, copyMap);
			for (int i = 0; i < K; i++) {
				copyMap = rotate(permList[i], copyMap);
			}
			calcAnswer(copyMap);
			return;
		}
		for (int i = 0; i < K; i++) {
			if(vis[i]) continue;
			vis[i] = true;
			permList[count] = i;
			perm(count+1, copyMap);
			vis[i] = false;
		}
	}
	
	public static int[][] copy(int[][] map, int[][] copy) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				copy[i][j] = map[i][j];
			}
		}
		return copy;
	}
	
	static int answer;
	
	public static void calcAnswer(int[][] copyMap) {
		for (int i = 0; i < N; i++) {
			int temp = 0;
			for (int j = 0; j < M; j++) {
				temp+=copyMap[i][j];
			}
			answer = Math.min(answer, temp);
		}
	}
	
}
