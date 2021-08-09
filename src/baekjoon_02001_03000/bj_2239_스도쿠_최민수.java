package baekjoon_02001_03000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

//[골드 4] 스도쿠
//https://www.acmicpc.net/problem/2239
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_2239_스도쿠_최민수 {
	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_2239"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 0 위치를 q에 넣은 다음 DFS
		ArrayDeque<Integer> zeroq = new ArrayDeque<>();

		int[][] map = new int[9][9];
		for (int i = 0; i < 9; i++) {
			String[] temp = br.readLine().split("");
			for (int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
				if (map[i][j] == 0) {
					zeroq.offer(i);
					zeroq.offer(j);
				}
			}
		}

		dfs(map, zeroq);

		br.close();
	}

	private static void dfs(int[][] map, ArrayDeque<Integer> zeroq) {
		if (zeroq.isEmpty()) {
			// 큐에 있는거 다 뽑았다
			// 출력하고 종료
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			System.exit(0);
		}

		int nowx = zeroq.poll();
		int nowy = zeroq.poll();

		for (int i = 1; i <= 9; i++) {
			// 이 위치에 i 넣어도 되는지 검사
			if (isAvailable(nowx, nowy, i, map)) { // 넣어도 된다.
				map[nowx][nowy] = i;
				dfs(map, zeroq);
				// 만약에 9개 다 안되면 돌아가서 다음 숫자를 넣어봐야된다.
				map[nowx][nowy] = 0; // 이게 없으면 타고올라가면서 값이 입력된게 타고 올라간다.
			}
		}
		// 다시 타고 올라갈 때
		zeroq.offerFirst(nowy);
		zeroq.offerFirst(nowx);
		return;
	}

	private static boolean isAvailable(int nowx, int nowy, int value, int[][] map) {
		// 행, 열 검사
		for (int j = 0; j < 9; j++) {
			if (map[j][nowy] == value)
				return false; // 열 검사
			if (map[nowx][j] == value)
				return false; // 열 검사
		}

		// 박스 검사
		int startx = (nowx / 3) * 3; // 0, 3, 6
		int starty = (nowy / 3) * 3;
		for (int i = startx; i < startx + 3; i++) {
			for (int j = starty; j < starty + 3; j++) {
				if (map[i][j] == value)
					return false;
			}
		}

		return true;
	}
}
