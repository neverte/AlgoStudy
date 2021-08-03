package Etc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//0323 Workshop 1번
public class DP_Ex1 {
	public static void main(String[] args) throws Exception {
		// 테스트 입력
		// System.setIn(new FileInputStream("res/baekjoon/bj_input_0000"));
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// n층 아파트마다 꼭대기 층이 노랑인 수, 파랑인 수를 기록한 다음
		// 노랑인 수 *2 + 파랑인수 * 1을 n+1층으로 한다.
		// 노랑 갯수 = 전층의 노란색 * 1 + 전층의 파란색 *1
		// 파랑 갯수 = 전층의 노란색 * 1

		// [0]이 노랑, [1]이 파랑
		int arr[][] = new int[8][2];
		arr[0][0] = 1;
		arr[0][1] = 1;

		for (int i = 1; i < 8; i++) {
			arr[i][0] = arr[i - 1][0] + arr[i - 1][1];
			arr[i][1] = arr[i - 1][0];
		}

		for (int i = 0; i < 8; i++) {
			System.out.println((i + 1) + "층 :" + (arr[i][0] + arr[i][1]));

		}

		// br.close();
	}
}
