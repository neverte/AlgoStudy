package baekjoon_01001_02000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//[골드 5] 게임
//https://www.acmicpc.net/problem/1584
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_1584_게임_최민수 {
	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_1584"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		map = new int[501][501]; // 안전 구역0, 위험 구역 1, 죽음 구역 2
		cost = new int[501][501];
		visit = new boolean[501][501];
		fill(cost, INFINITY);

		int dangerPoints = Integer.parseInt(br.readLine());
		for (int i = 0; i < dangerPoints; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			markDangerPoint(x1, y1, x2, y2, 1);
		}
		int diePoints = Integer.parseInt(br.readLine());
		for (int i = 0; i < diePoints; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			markDangerPoint(x1, y1, x2, y2, 2);
		}

		dijkstra();

		if (cost[500][500] == INFINITY) {
			bw.write(-1 + "");
		} else {
			bw.write(cost[500][500] + "");
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static void fill(int[][] cost2, int i) {
		for (int j = 0; j < cost2.length; j++) {
			for (int j2 = 0; j2 < cost2[0].length; j2++) {
				cost2[j][j2] = i;
			}
		}
	}

	private static void dijkstra() {
		cost[0][0] = 0;

		// 큐에 시작원소 넣고
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(new Point(0, 0, cost[0][0]));

		// 큐에서 값을 꺼낸 곳에서 이동할 수 있는 곳을 다 방문하는데
		while (!pq.isEmpty()) {
			Point now = pq.poll();
			// 이미 방문 했다.
			if (visit[now.x][now.y]) {
				continue;
			}
			visit[now.x][now.y] = true;

			if (now.x == 500 && now.y == 500) {
				break;
			}
			for (int way = 0; way < 4; way++) {
				int nx = now.x + dx[way];
				int ny = now.y + dy[way];

				if (nx < 0 || nx >= 501 || ny < 0 || ny >= 501 || visit[nx][ny]) {
					continue;
				}
				// 추가 비용
				int addCost = 0;
				// 사망지역
				if (map[nx][ny] == 2) {
					continue;
				} else if (map[nx][ny] == 1) {
					addCost = 1;
				}
				// 더 짧은 비용을 찾았다.
				if (cost[nx][ny] > now.needCost + addCost) {
					cost[nx][ny] = now.needCost + addCost;
					pq.add(new Point(nx, ny, cost[nx][ny]));
				}
			}
		}
	}

	private static void markDangerPoint(int x1, int y1, int x2, int y2, int pointValue) {
		if (x1 > x2) { // x1이 x2보다 위쪽에 있게.
			int tempx = x2;
			x2 = x1;
			x1 = tempx;
			int tempy = y2;
			y2 = y1;
			y1 = tempy;
		}
		if (y1 < y2) { // 1이 왼쪽 위, 2가 오른쪽 아래
		} else {
			// x1은 그대로
			// y1, y2은 값이 바뀌어야 함
			int tempy = y1;
			y1 = y2;
			y2 = tempy;
			// x2는 그대로
		}
		for (int i = x1; i <= x2; i++) {
			for (int j = y1; j <= y2; j++) {
				map[i][j] = pointValue;
			}
		}
	}

	static public class Point implements Comparable<Point> {
		int x;
		int y;
		int needCost;

		public Point(int x, int y, int needCost) {
			this.x = x;
			this.y = y;
			this.needCost = needCost;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(needCost, o.needCost);
		}
	}

	static int[][] map;
	static int[][] cost;
	static boolean[][] visit;
	static int INFINITY = Integer.MAX_VALUE;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
}
