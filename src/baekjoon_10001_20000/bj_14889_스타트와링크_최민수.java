package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[실버 3] 스타트와 링크
//https://www.acmicpc.net/problem/14889
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_14889_스타트와링크_최민수 {
	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_14889"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// N명은 짝수
		// 각팀은 N/2
		// 능력치 Sij는 i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치
		// 팀의 능력치는 팀에 속한 모든 쌍의 능력치 Sij의 합
		// i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치는 Sij와 Sji이다.

		// 스타트 팀의 능력치와 링크 팀의 능력치의 차이를 최소로

		// N 4~20
		int n = Integer.parseInt(br.readLine());
		int[][] stat = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				// 배열 우상단에 값을 합침
				if (i > j)
					stat[j][i] += Integer.parseInt(st.nextToken());
				else
					stat[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// n개의 원소 중 n/2를 뽑는 조합 만들기
		int[] number = new int[n];
		for (int i = 0; i < number.length; i++) {
			number[i] = i;
		}
		// 모든 조합을 뽑아서 각 경우의 최소값을 구해볼 것이다.
		int count = 0;
		int[] comb = new int[n / 2];
		makeCombination(stat, number, count, comb, 0);

		System.out.println(answer);

		br.close();
	}

	static int answer = Integer.MAX_VALUE;

	private static void makeCombination(int[][] arr, int[] num, int cnt, int[] com, int start) {

		// 종료 조건
		// 이 시점에서의 cnt개 만큼 뽑았다.
		if (cnt == arr.length / 2) {
			// 오른쪽 팀원 구하기
			int[] rTeam = new int[com.length];
			int count = 0;
			int rCnt = 0;
			for (int i = 0; i < arr.length; i++) {
				if (count < arr.length / 2 && com[count] == i) {
					count++;
				} else {
					rTeam[rCnt++] = i;
				}
			}

			int leftSum = 0;
			int rightSum = 0;
			// 왼쪽팀 합계 구하기
			for (int i = 0; i < com.length - 1; i++) {
				// i번째 원소와 i+1, i+2, ... 원소들의 합 더하기
				for (int j = i + 1; j < com.length; j++) {
					leftSum += arr[com[i]][com[j]];
				}
			}
			// 오른쪽팀 합계 구하기
			for (int i = 0; i < com.length - 1; i++) {
				// i번째 원소와 i+1, i+2, ... 원소들의 합 더하기
				for (int j = i + 1; j < com.length; j++) {
					rightSum += arr[rTeam[i]][rTeam[j]];
				}
			}
			// 전체 답 계산
			answer = Math.min(answer, Math.abs(leftSum - rightSum));
			return;
		}

		// 조합 뽑기
		for (int i = start; i < arr.length; i++) {
			com[cnt] = num[i];
			makeCombination(arr, num, cnt + 1, com, i + 1);
		}

	}

}
