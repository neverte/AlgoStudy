package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//[골드 2] 구슬 탈출 4
//https://www.acmicpc.net/problem/15653
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_15653_구슬탈출4_최민수 {
	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_15653"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 상, 하, 좌, 우의 탐색 시작점
		int[] startX = { 0, N - 1, 0, 0 };
		int[] startY = { 0, 0, M - 1, 0 };
		// 빨간 구슬 좌표, 파란 구슬 좌표 저장
		visit = new boolean[N][M][N][M];

		int answer = 0;

		Queue<Marvel> bfsq = new LinkedList<>();
		char[][] start = new char[N][M];
		redX = 0;
		redY = 0;
		blueX = 0;
		blueY = 0;

		for (int i = 0; i < N; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				start[i][j] = temp[j];
				if (temp[j] == 'R') {
					redX = i;
					redY = j;
				} else if (temp[j] == 'B') {
					blueX = i;
					blueY = j;
				}
			}
		}

		bfsq.add(new Marvel(start, redX, redY, blueX, blueY, 0, -1));

		while (!bfsq.isEmpty()) {
			Marvel temp = bfsq.poll();

			// 만약 남은 빨간 구슬이 0개(redX값이 -1)라면 break
			if (temp.redX == -1) {
				answer = temp.turn;
				break;
			}

			// 방문한 적이 있다
			if (visit[temp.redX][temp.redY][temp.blueX][temp.blueY]) {
				continue;
			}
			// 방문처리
			visit[temp.redX][temp.redY][temp.blueX][temp.blueY] = true;

			// bfs: 현재 배열 상태, 현재 R, B구슬 위치, 현재 턴 수
			// 상하좌우 순서대로 4방향으로 기울인 것을 다 q에 넣는다.
			// 기울이는 중에 B가 빠지면 그대로 끝
			blueExit: for (int i = 0; i < 4; i++) {
				// 지금 방향이랑 같으면 스킵
				if (temp.lastState == i) {
					continue;
				}

				// 맵 복사
				copyMap = new char[N][M];
				copy(temp.map);

				int bound = M;
				if (i >= 2) {
					bound = N;
				}

				for (int j = 0; j < bound; j++) {
					int start_x = startX[i] + j * dx[i];
					int start_y = startY[i] + j * dy[i];

					// 배열 범위 초과 시
					if (start_x >= N || start_y >= M) {
						continue;
					}

					// 한 줄씩 이동
					if (i == 0 && !moveUp(start_x, start_y)) {
						// B가 빠졌으면 이번 방향으로 기울이는 것은 스킵하고 다음 방향으로
						continue blueExit;
					} else if (i == 1 && !moveDown(start_x, start_y)) {
						continue blueExit;
					} else if (i == 2 && !moveLeft(start_x, start_y)) {
						continue blueExit;
					} else if (i == 3 && !moveRight(start_x, start_y)) {
						continue blueExit;
					}
				}
				// 한 방향 이동이 끝났다.
				// 변화가 없으면 스킵
				if (isSame(temp.map)) {
					continue;
				}

				bfsq.add(new Marvel(copyMap, redX, redY, blueX, blueY, temp.turn + 1, i));
			}
		}

		if (answer == 0) {
			answer = -1;
		}
		bw.write(answer + "");

		bw.flush();
		bw.close();
		br.close();
	}

	private static boolean isSame(char[][] original) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copyMap[i][j] != original[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean moveRight(int start_x, int start_y) {
		// 가장 가까이에 있는 벽 위치
		int wally = start_y;
		// 움직이기 시작하면 벽 대신 구멍으로 빠지는지 체크
		boolean isHole = false;
		for (int i = 0; i < M; i++) {
			if (copyMap[start_x][i] == '.') {
				continue;
			} else if (copyMap[start_x][i] == 'O') {
				isHole = true;
			} else if (copyMap[start_x][i] == 'R') {
				// 위치 제거
				copyMap[start_x][i] = '.';
				if (isHole) {
					// 제거
					redX = -1;
				} else {
					// 벽 앞으로 이동
					copyMap[start_x][wally + 1] = 'R';
					redX = start_x;
					redY = wally + 1;
					// wally 갱신
					wally++;
				}
			} else if (copyMap[start_x][i] == 'B') {
				// 위치 제거
				copyMap[start_x][i] = '.';
				if (isHole) {
					// 파란색이 제거되었음
					return false;
				} else {
					// 벽 앞으로 이동
					copyMap[start_x][wally + 1] = 'B';
					blueX = start_x;
					blueY = wally + 1;
					// wally 갱신
					wally++;
				}
			} else if (copyMap[start_x][i] == '#') {
				// 구멍 지나옴
				isHole = false;
				// 벽 위치 갱신
				wally = i;
			}
		}
		return true;
	}

	private static boolean moveLeft(int start_x, int start_y) {
		// 가장 가까이에 있는 벽 위치
		int wally = start_y;
		// 움직이기 시작하면 벽 대신 구멍으로 빠지는지 체크
		boolean isHole = false;
		for (int i = M - 1; i >= 0; i--) {
			if (copyMap[start_x][i] == '.') {
				continue;
			} else if (copyMap[start_x][i] == 'O') {
				isHole = true;
			} else if (copyMap[start_x][i] == 'R') {
				// 위치 제거
				copyMap[start_x][i] = '.';
				if (isHole) {
					// 제거
					redX = -1;
				} else {
					// 벽 앞으로 이동
					copyMap[start_x][wally - 1] = 'R';
					redX = start_x;
					redY = wally - 1;
					// wally 갱신
					wally--;
				}
			} else if (copyMap[start_x][i] == 'B') {
				// 위치 제거
				copyMap[start_x][i] = '.';
				if (isHole) {
					// 파란색이 제거되었음
					return false;
				} else {
					// 벽 앞으로 이동
					copyMap[start_x][wally - 1] = 'B';
					blueX = start_x;
					blueY = wally - 1;
					// wallx 갱신
					wally--;
				}
			} else if (copyMap[start_x][i] == '#') {
				// 구멍 지나옴
				isHole = false;
				// 벽 위치 갱신
				wally = i;
			}
		}
		return true;
	}

	private static boolean moveDown(int start_x, int start_y) {
		// 가장 가까이에 있는 벽 위치
		int wallx = start_x;
		// 움직이기 시작하면 벽 대신 구멍으로 빠지는지 체크
		boolean isHole = false;
		for (int i = N - 1; i >= 0; i--) {
			if (copyMap[i][start_y] == '.') {
				continue;
			} else if (copyMap[i][start_y] == 'O') {
				isHole = true;
			} else if (copyMap[i][start_y] == 'R') {
				// 위치 제거
				copyMap[i][start_y] = '.';
				if (isHole) {
					// 제거
					redX = -1;
				} else {
					// 벽 앞으로 이동
					copyMap[wallx - 1][start_y] = 'R';
					redX = wallx - 1;
					redY = start_y;
					// wallx 갱신
					wallx--;
				}
			} else if (copyMap[i][start_y] == 'B') {
				// 위치 제거
				copyMap[i][start_y] = '.';
				if (isHole) {
					// 파란색이 제거되었음
					return false;
				} else {
					// 벽 앞으로 이동
					copyMap[wallx - 1][start_y] = 'B';
					blueX = wallx - 1;
					blueY = start_y;
					// wallx 갱신
					wallx--;
				}
			} else if (copyMap[i][start_y] == '#') {
				// 구멍 지나옴
				isHole = false;
				// 벽 위치 갱신
				wallx = i;
			}
		}
		return true;
	}

	// 세로로 움직임
	private static boolean moveUp(int start_x, int start_y) {
		// 일단 위로 움직인다고 생각
		// start_x, start_y는 벽이고 여기서부터 밑으로 가면서 당긴다

		// 가장 가까이에 있는 벽 위치
		int wallx = start_x;
		// 움직이기 시작하면 벽 대신 구멍으로 빠지는지 체크
		boolean isHole = false;
		for (int i = 0; i < N; i++) {
			if (copyMap[i][start_y] == '.') {
				continue;
			} else if (copyMap[i][start_y] == 'O') {
				isHole = true;
			} else if (copyMap[i][start_y] == 'R') {
				// 위치 제거
				copyMap[i][start_y] = '.';
				if (isHole) {
					// 제거
					redX = -1;
				} else {
					// 벽 앞으로 이동
					copyMap[wallx + 1][start_y] = 'R';
					redX = wallx + 1;
					redY = start_y;
					// wallx 갱신
					wallx++;
				}
			} else if (copyMap[i][start_y] == 'B') {
				// 위치 제거
				copyMap[i][start_y] = '.';
				if (isHole) {
					// 파란색이 제거되었음
					return false;
				} else {
					// 벽 앞으로 이동
					copyMap[wallx + 1][start_y] = 'B';
					blueX = wallx + 1;
					blueY = start_y;
					// wallx 갱신
					wallx++;
				}
			} else if (copyMap[i][start_y] == '#') {
				// 구멍 지나옴
				isHole = false;
				// 벽 위치 갱신
				wallx = i;
			}
		}
		return true;
	}

	private static void copy(char[][] original) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyMap[i][j] = original[i][j];
			}
		}
	}

	static int N, M, redX, redY, blueX, blueY;
	// 상하좌우로 기울일 때 시작점 위치.
	static int dx[] = { 0, 0, 1, 1 };
	static int dy[] = { 1, 1, 0, 0 };
	static char[][] copyMap;
	static boolean[][][][] visit;

	static class Marvel {
		char[][] map;
		int redX;
		int redY;
		int blueX;
		int blueY;
		int turn;
		int lastState; // 마지막으로 움직인 방향 0상 1하 2좌 3우

		public Marvel(char[][] map, int redX, int redY, int blueX, int blueY, int turn, int lastState) {
			this.map = map;
			this.redX = redX;
			this.redY = redY;
			this.blueX = blueX;
			this.blueY = blueY;
			this.turn = turn;
			this.lastState = lastState;
		}

	}
}
