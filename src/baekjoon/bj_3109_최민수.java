package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//참고: https://yabmoons.tistory.com/124

//[골드 2] 빵집
//https://www.acmicpc.net/problem/3109
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_3109_최민수 {
	
	static int R, C;
	static int[] where;
	static String[][] map;
	static boolean[][] vis;
	
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_3109"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		// [R][C]
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new String[R][C];
		vis = new boolean[R][C];

		//한줄씩 통채로 입력받기
		for (int i = 0; i < R; i++) {
			String temp[] = br.readLine().split("");
			map[i] = temp;
		}
		
		//한줄마다 답 찾기
		for (int i = 0; i < R; i++) {
			findAns = false;
			DFS(i, 0);
		}
		
		System.out.println(answer);

		br.close();
	}
	
	static int answer = 0;
	static boolean findAns = false;
	static int dx[] = {-1, 0, 1}; //우상, 우, 우하
	static int dy[] = { 1, 1, 1};
	
	public static void DFS(int x, int y) {
		//따로 초기화해주지 않는 이유는
		//해당 지점을 방문해서 성공했든 실패했든
		//언젠가 다시 여길 도달하면 동일한 결과가 발생할 것이기 때문에
		//언젠가 다시 여길 도달하지 못하도록 방문했다고 처리.
		vis[x][y] = true;

		//맨 오른쪽 도달
		if(y == C-1) {
			findAns = true;
			answer++;
			return;
		}

		//자식노드에 가지 파생
		//여기선 3방향, 우상, 우, 우하
		for (int i = 0; i < 3; i++) {
			int movX = x+dx[i];
			int movY = y+dy[i];
			//배열 범위 안인지
			if(movX >= 0 && movX < R && movY >= 0 && movY < C) {
				//건물이 아니고, 방문하지 않았는지
				if(map[movX][movY].equals(".") && vis[movX][movY] == false) {
					//이 탐색에서 답을 찾았으면 return
					DFS(movX, movY);
					if(findAns) return;
				}
			}
		}
	}
}

//메모리 초과: ArrayList -> Array
//시간초과 1: String -> int 변환 삭제
//해결1: vis 배열 만들기(원래는 1차원배열로 위치 추적함)