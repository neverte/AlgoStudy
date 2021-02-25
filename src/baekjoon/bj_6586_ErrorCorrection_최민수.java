package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[골드 1] Error Correction
//https://www.acmicpc.net/problem/6586
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_6586_ErrorCorrection_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_6586"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		while (true) {
			//행렬의 크기 N ~99
			int N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			int rowCount, rowParitCheck = 0, colParitCheck = 0;
			boolean isCorrupt = false;
			int[][] map = new int[N][N];
			int checkPointX = -1;
			Loop1: for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				rowCount = 0;
				for (int j = 0; j < map.length; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) rowCount++;
				}
				if(rowCount % 2 == 1) rowParitCheck++;
				if(!isCorrupt && rowParitCheck==1 && checkPointX == -1) checkPointX = i;
				//무조건 Corrupt
				if(rowParitCheck >= 2) {
					isCorrupt = true;
					while(++i<N) br.readLine(); //입력 마저 읽어서 다음 입력에 방해되지 않게
					break Loop1;
				}
			}
			int colCount, checkPointY = -1;
			Loop2: for (int i = 0; i < N; i++) {
				colCount = 0;
				for (int j = 0; j < N; j++) {
					if(map[j][i] == 1) colCount++;
				}
				if(colCount % 2 == 1)colParitCheck++;
				if(colParitCheck >= 2) {
					isCorrupt = true;
					break Loop2;
				}
				if(!isCorrupt && rowParitCheck==1 && colParitCheck == 1 && checkPointY == -1) checkPointY = i;
				if(isCorrupt || colParitCheck >= 2) {
					isCorrupt = true;
					break Loop2;
				}
			}
			if(!isCorrupt) {
				if(rowParitCheck + colParitCheck == 0) System.out.println("OK");
				else {
					System.out.println("Change bit ("+(checkPointX + 1)+","+(checkPointY+1)+")");
					
				}
			}else {
				System.out.println("Corrupt");
			}
			
			
		}
		
		br.close();

	}
}
