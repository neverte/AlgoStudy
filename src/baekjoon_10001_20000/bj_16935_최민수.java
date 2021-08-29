package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//참고1. https://codingdog.tistory.com/entry/%EB%B0%B0%EC%97%B4-%ED%9A%8C%EC%A0%84-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EC%9D%BD%EB%8A%94-%EB%B0%A9%EB%B2%95%EB%A7%8C-%EC%83%9D%EA%B0%81%ED%95%98%EB%A9%B4-%EC%96%B4%EB%A0%B5%EC%A7%80-%EC%95%8A%EC%95%84%EC%9A%94

//[실버 3] 배열 돌리기 3
//https://www.acmicpc.net/problem/16935
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_16935_최민수 {
	static int N, M, rotateCount;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_16935"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 입력
		// 배열의 크기 N, M, 수행 연산 수 R
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		int R = Integer.parseInt(st.nextToken());

		// 배열 입력받기
		for (int i = 0; i < N; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st2.nextToken());
			}
		}

		int temp[][] = new int[N][M];
		// 2차원 배열 deep copy
		for (int i = 0; i < temp.length; i++) {
			System.arraycopy(map[i], 0, temp[i], 0, map[0].length);
		}

		// 사용할 연산 받아놓기
		String[] oper = br.readLine().split(" ");

		for (int i = 0; i < R; i++) {
			if (oper[i].equals("1"))
				map = one();
			else if (oper[i].equals("2"))
				two();
			else if (oper[i].equals("3"))
				three();
			else if (oper[i].equals("4"))
				four();
			else if (oper[i].equals("5"))
				five();
			else if (oper[i].equals("6"))
				six();
		}

		// for (int[] t : map) System.out.println(Arrays.toString(t));
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.setLength(sb.length() - 1);
			sb.append("\n");
		}
		System.out.println(sb);

		br.close();
	}

	// 상하 반전
	static int[][] one() {
		int temp[][] = new int[map.length][map[0].length];
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				int row = map.length / 2;
				int x = (i < row) ? i + 2 * (row - i) - 1 : i - 2 * (i - row) - 1;
				temp[i][j] = map[x][j];
			}
		}
		map = temp;
		return map;
	}

	// 좌우 반전
	static int[][] two() {
		int temp[][] = new int[map.length][map[0].length];

		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				int col = map[0].length / 2;
				int y = (j < col) ? j + 2 * (col - j) - 1 : j - 2 * (j - col) - 1;
				temp[i][j] = map[i][y];
			}
		}
		map = temp;
		return map;
	}

	// 오른쪽 90도
	static int[][] three() { // 1, 7
		int temp[][] = new int[map[0].length][map.length];

		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				int x = map.length - 1 - j;
				int y = i;
				temp[i][j] = map[x][y];
			}
		}
		map = temp;
		return map;
	}

	// 왼쪽 90도
	static void four() {
		three();
		three();
		three();
	}

	// 나눠서 시계방향
	static int[][] five() {
		int temp[][] = new int[map.length][map[0].length];
		int row = map.length;
		int col = map[0].length;
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				if (i < row / 2 && j < col / 2) {
					temp[i][j] = map[i + row / 2][j];
				} else if (i < row / 2 && j >= col / 2) {
					temp[i][j] = map[i][j - col / 2];
				} else if (i >= row / 2 && j >= col / 2) {
					temp[i][j] = map[i - row / 2][j];
				} else {
					temp[i][j] = map[i][j + col / 2];
				}
			}
		}
		map = temp;
		return map;
	}

	static int[][] six() {
		int temp[][] = new int[map.length][map[0].length];
		int row = map.length;
		int col = map[0].length;
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				if (i < row / 2 && j < col / 2) {
					temp[i][j] = map[i][j + col / 2];
				} else if (i < row / 2 && j >= col / 2) {
					temp[i][j] = map[i + row / 2][j];
				} else if (i >= row / 2 && j >= col / 2) {
					temp[i][j] = map[i][j - col / 2];
				} else {
					temp[i][j] = map[i - row / 2][j];
				}

			}
		}
		map = temp;
		return map;
	}
}
