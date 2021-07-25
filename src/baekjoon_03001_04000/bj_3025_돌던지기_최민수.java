package baekjoon_03001_04000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

//[플래티넘 5] 돌 던지기
//https://www.acmicpc.net/problem/3025
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_3025_돌던지기_최민수 {
	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_3025"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		// memo = new int[r * c]; // [x*c + y] = x, y에 돌을 떨어뜨리면 어디서 시작해야하는가
		start = new int[c];

		for (int i = 0; i < r; i++) {
			map[i] = br.readLine().toCharArray();
		}

		int stones = Integer.parseInt(br.readLine());
		for (int i = 0; i < stones; i++) {
			int point = Integer.parseInt(br.readLine());
			dropStone(point - 1);

			// System.out.println((i + 1) + "회차==================" + (point - 1));
			// for (char[] cc : map) {
			// System.out.println(Arrays.toString(cc));

			// }
		}
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				bw.write(map[i][j]);
			}
			bw.write("\n");

		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void dropStone(int y) {
		// 처음 돌의 좌표는 0, point

		// 민혁이는 항상 제일 윗 칸이 비어있는 칸에만 돌을 던진다.
		if (map[0][y] != '.') {
			return;
		}
		int x = start[y];

		boolean linear = true; // 한번도 안꺾었는가

		while (true) {
			// 돌의 아랫칸이 벽으로 막혀있거나 가장 아랫줄이라면, 돌은 그 자리에 그대로 멈춰 있는다.
			if (x == r - 1 || map[x + 1][y] == 'X') {
				if (linear) {
					start[y] = x - 1;
				}
				map[x][y] = 'O';
				return;
			}
			// 돌의 아랫칸이 비어있다면, 돌은 아랫칸으로 이동한다.
			else if (map[x + 1][y] == '.') {
				x += 1;
			}
			// 돌의 아랫칸에 돌이 있다면, 돌은 다음과 같이 미끄러진다.
			else if (map[x + 1][y] == 'O') {
				// 만약 돌의 왼쪽 칸과 왼쪽-아래 칸이 비어있다면, 돌은 왼쪽으로 미끄러진다.
				if (y - 1 >= 0 && map[x][y - 1] == '.' && map[x + 1][y - 1] == '.') {
					linear = false;
					map[x][y] = '.';
					++x;
					--y;
				}
				// 만약 돌이 왼쪽으로 미끄러지지 않았고, 오른쪽 칸과 오른쪽-아래 칸이 비어있다면, 돌은 오른쪽으로 미끄러진다.
				else if (y + 1 < c && map[x][y + 1] == '.' && map[x + 1][y + 1] == '.') {
					linear = false;
					map[x][y] = '.';
					++x;
					++y;

				}
				// 위의 두 경우가 아니라면 돌은 그 자리에 멈추고, 다시는 움직이지 않는다.
				else {
					if (linear) {
						start[y] = x - 1;
					}
					map[x][y] = 'O';
					return;
				}
			}
		}

	}

	static char[][] map;
	static int r, c;
	// static int[] memo; // 시작 - 끝을 갱신, 메모하려 했으나 변수가 너무 많음. 값이 계속 바뀜.
	static int[] start; // 시작 -> 수직으로 도달하는 곳 체크
}
