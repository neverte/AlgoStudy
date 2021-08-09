package baekjoon_02001_03000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

//[골드 5] 치즈
//https://www.acmicpc.net/problem/2636
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_2636_치즈_최민수 {
	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_2636"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 치즈에는 하나 이상의 구멍이 있을 수 있다.
		// 공기와 접촉된 칸은 한 시간이 지나면 녹아 없어진다

		// 아이디어1. 절대 치즈 못놓는 공간에서 BFS를 시작해서 치즈 만날떄마다 마킹하고
		// BFS끝나면 마킹한 치즈 제거한 후 다시 반복

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		int[][] map = new int[height][width];

		for (int i = 0; i < height; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < width; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean go = true;
		int cnt = 0;
		int lastCheese = 0;
		int nowCheese = 0;
		while (go) {
			nowCheese = 0;
			boolean v[][] = new boolean[height][width];
			// 노출 치즈 선별 작업
			bfs(map, go, lastCheese, v);

			// 치즈 제거작업
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					if (map[i][j] == 2) {
						nowCheese++;
						map[i][j] = 0;
					}
				}
			}
			if (nowCheese == 0)
				go = false;
			if (go) {
				lastCheese = nowCheese;
				cnt++;
			}
			// for(int[] ii : map)System.out.println(Arrays.toString(ii));

		}
		System.out.println(cnt);
		System.out.println(lastCheese);

		// 걸리는 시간
		// 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수

		br.close();
	}

	// 상우하좌
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	private static void bfs(int[][] map, boolean go, int lastCheese, boolean[][] visited) {
		ArrayDeque<int[]> q = new ArrayDeque<int[]>();

		int[] temp = new int[2];
		temp[0] = 0;
		temp[1] = 0;
		visited[0][0] = true;
		q.offer(temp);

		while (!q.isEmpty()) {
			int[] current = q.poll();

			for (int i = 0; i < 4; i++) {
				int cx = current[0] + dx[i];
				int cy = current[1] + dy[i];

				// 배열 밖인지 체크
				if (cx < 0 || cx >= map.length || cy < 0 || cy >= map[0].length)
					continue;

				if (visited[cx][cy])
					continue;

				// 치즈이면
				if (map[cx][cy] == 1) {
					map[cx][cy] = 2;
					visited[cx][cy] = true;
					continue;
				} else if (map[cx][cy] == 2)
					continue;

				// 방문한 곳인지

				// 배열 안이고, 치즈도 아니면 이동해야 함.
				int[] next = new int[2];
				next[0] = cx;
				next[1] = cy;
				q.offer(next);
				visited[cx][cy] = true;
			}

		}

	}
}
// 폐기 아이디어. 상하좌우에서 처음만나는 치즈 제거