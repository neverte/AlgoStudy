package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//[골드 4] 알파벳
//https://www.acmicpc.net/problem/1987
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_1987_최민수 {
	
	static int R, C;
	//방문여부는 chkAlphabet으로 확인
	//A에 방문했다 치면 어떻게 체크?
	//A는 아스키 65
	static int chkAlphabet[] = new int[26];
	
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_1987"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//[R][C]
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		//R, C 1~20
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		//아이디어 DFS로 돌면서 최대치 기록
		DFS(map, 0, 0, 1);
		
		System.out.println(answer);
		
		br.close();
	}
	
	//우, 하, 좌, 상
	public static int[] deltaX = {0, 1, 0, -1};
	public static int[] deltaY = {1, 0,-1,  0};
	static int answer = 0;
	
	public static void DFS(char[][] arr, int x, int y, int count) {
		//방문여부
		chkAlphabet[arr[x][y] - 65] = 1;
		answer = Math.max(answer, count);
		
		for (int i = 0; i < 4; i++) {
			int movX = x + deltaX[i];
			int movY = y + deltaY[i];
			
			//범위 체크
			if(movX < 0|| movX >= R || movY < 0 || movY >= C || chkAlphabet[arr[movX][movY] - 65] == 1) continue;
			DFS(arr, movX, movY, count+1);
			chkAlphabet[arr[movX][movY] - 65] = 0;
		}
	}
}
