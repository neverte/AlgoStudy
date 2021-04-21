package swea_d1_d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 패키지 날려야되나
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d3_1873_상호의배틀필드_구미4반_최민수 {
	static int[] dirX = {-1,1,0,0}; //위, 아래, 좌, 우
	static int[] dirY = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d3_1873.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//Testcase
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			
			//높이, 너비
			StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
			int height = Integer.parseInt(st1.nextToken());
			int width = Integer.parseInt(st1.nextToken());
			
			//map을 넣을 배열
			String[][] map = new String[height][width];
			//전차 위치
			int tankX=0, tankY=0;
			
			for (int j = 0; j < height; j++) {
				//width길이의 문자열 입력
				map[j] = br.readLine().split("");
				for (int k = 0; k < width; k++) {
					//전차위치 찾으면
					if(map[j][k].equals("^") || map[j][k].equals("v") || map[j][k].equals("<") || map[j][k].equals(">")) {
						tankX = j;
						tankY = k;
					}
				}
			}
		
			int userInput = Integer.parseInt(br.readLine());
			//유저 조작
			//을 넣을 배열
			String[] move = br.readLine().split("");
		
			for (int j = 0; j < userInput; j++) {
				switch (move[j]) {
				case "U":
					//전차 모양 바꾸고
					map[tankX][tankY] = "^";
					//위쪽칸이 map 범위 안인지 체크
					if(tankX-1 < 0) break;
					//위쪽 칸이 평지인지 체크
					if(map[tankX-1][tankY].equals(".")) {
						//위쪽칸으로 전차 위치 이동
						map[tankX-1][tankY] = "^";
						//원래 위치 평지로 바꾸기
						map[tankX][tankY] = ".";
						//tank 좌표 수정
						tankX--;
					}
					break;
				case "D":
					//전차 모양 바꾸고
					map[tankX][tankY] = "v";
					//아래칸이 map 범위 안인지 체크
					if(tankX+1 >= height) break;
					//아래 칸이 평지인지 체크
					if(map[tankX+1][tankY].equals(".")) {
						//아래칸으로 전차 위치 이동
						map[tankX+1][tankY] = "v";
						//원래 위치 평지로 바꾸기
						map[tankX][tankY] = ".";
						//tank 좌표 수정
						tankX++;
					}
					break;
				case "L":
					//전차 모양 바꾸고
					map[tankX][tankY] = "<";
					//왼쪽 칸이 map 범위 안인지 체크
					if(tankY-1 < 0) break;
					//왼쪽 칸이 평지인지 체크
					if(map[tankX][tankY-1].equals(".")) {
						//왼쪽 칸으로 전차 위치 이동
						map[tankX][tankY-1] = "<";
						//원래 위치 평지로 바꾸기
						map[tankX][tankY] = ".";
						//tank 좌표 수정
						tankY--;
					}
					break;
				case "R":
					//전차 모양 바꾸고
					map[tankX][tankY] = ">";
					//오른쪽 칸이 map 범위 안인지 체크
					if(tankY+1 >= width) break;
					//오른쪽 칸이 평지인지 체크
					if(map[tankX][tankY+1].equals(".")) {
						//오른쪽 칸으로 전차 위치 이동
						map[tankX][tankY+1] = ">";
						//원래 위치 평지로 바꾸기
						map[tankX][tankY] = ".";
						//tank 좌표 수정
						tankY++;
					}
					break;
				case "S":
					int deltaX = 9, deltaY = 9;
					//방향 체크
					if(map[tankX][tankY].equals("^")) {
						deltaX = dirX[0];
						deltaY = dirY[0];
					}else if(map[tankX][tankY].equals("v")) {
						deltaX = dirX[1];
						deltaY = dirY[1];
					}else if(map[tankX][tankY].equals("<")) {
						deltaX = dirX[2];
						deltaY = dirY[2];
					}else if(map[tankX][tankY].equals(">")) {
						deltaX = dirX[3];
						deltaY = dirY[3];
					}
					
					for (int k = 1; k < Math.max(height, width); k++) {
						//맵 범위 밖이면 break;
						if(tankX+deltaX*k < 0 || tankX+deltaX*k >= height || tankY+deltaY*k < 0 || tankY+deltaY*k >= width) break;
						//벽돌이면 평지로 만들기
						if(map[tankX+deltaX*k][tankY+deltaY*k].equals("*")) {
							map[tankX+deltaX*k][tankY+deltaY*k] = ".";
							break;
							//강철을 만나면 끝
						}else if(map[tankX+deltaX*k][tankY+deltaY*k].equals("#")) break;
						//물, 평지이면 아무일 없다.
					}
					break;
				}
			} //유저조작 끝
			
			//결과 출력
			System.out.print("#"+(i+1)+" ");
			for (int j = 0; j < height; j++) {
				for (int k = 0; k < width; k++) {
					System.out.print(map[j][k]);
				}
				System.out.println();
			}
			
		}
		
		br.close();
	}

}
