package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

//[골드 4] 타임머신
//https://www.acmicpc.net/problem/11657
//참고: https://ratsgo.github.io/data%20structure&algorithm/2017/11/27/bellmanford/
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_11657_타임머신_최민수 {
	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_11657"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		cityNum = Integer.parseInt(st.nextToken());
		busNum = Integer.parseInt(st.nextToken());
		cityCost = new long[cityNum + 1]; // 이 도시에 도달하는데 소요되는 비용
		fill(cityCost, INF);

		busList = new ArrayList<>();
		for (int i = 0; i < busNum; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			busList.add(new Bus(a, b, c));
		}

		// 무한 음수 사이클이 있으면 true
		boolean isNegative = false;
		isNegative = bellmandFord();

		if (isNegative) {
			bw.write(-1 + "");
		} else {
			for (int i = 2; i <= cityNum; i++) {
				if (i >= 3) {
					bw.write("\n");
				}
				if (cityCost[i] == INF) {
					bw.write(-1 + "");
				} else {
					bw.write(cityCost[i] + "");
				}
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static boolean bellmandFord() {
		boolean returnVal = false;
		// 시작도시는 1
		cityCost[1] = 0;

		// 노드 개수만큼 반복할 것이다.
		for (int i = 0; i <= cityNum; i++) {
			// 버스 노선 처음부터 끝까지 순회하면서 cityCost 갱신
			for (int j = 0; j < busNum; j++) {
				Bus now = busList.get(j);
				// 시작 INF면 스킵 어떤 짓을 해도 의미가 없음.
				if (cityCost[now.start] == INF) {
					continue;
				} else if (cityCost[now.end] > cityCost[now.start] + now.cost) {
					cityCost[now.end] = cityCost[now.start] + now.cost;
					// 노드 개수만큼 반복한 다음 사이클에서도 값이 줄어든다 = 음수 사이클이 있다.
					if (i == cityNum) {
						returnVal = true;
					}
				}
			}
		}
		return returnVal;
	}

	static public class Bus {
		int start;
		int end;
		int cost;

		public Bus(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
	}

	private static void fill(long[] cityCost2, long val) {
		for (int i = 0; i < cityCost2.length; i++) {
			cityCost2[i] = val;
		}
	}

	static int cityNum, busNum;
	static long[] cityCost;
	static long INF = Long.MAX_VALUE;
	static ArrayList<Bus> busList;
}
