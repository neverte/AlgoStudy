package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//[실버 2] 골드바흐 파티션
//https://www.acmicpc.net/problem/17103
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_17103_골드바흐파티션_최민수 {
	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_17103"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력 받은 수중에 가장 큰 수보다 작은 소수를 먼저 구한다.
		int T = Integer.parseInt(br.readLine());
		int[] input = new int[T];
		int max = 0;
		for (int i = 0; i < T; i++) {
			input[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, input[i]);
		}

		// 소수를 구할 것이다.
		// 에라토스테네스의 체를 활용할 것
		boolean[] numbers = new boolean[max + 1];
		// false: 소수, true면 소수가 아님으로 하자
		for (int i = 2; i <= max; i++) {
			// true = 소수 X = PASS
			if (numbers[i])
				continue;
			// 자기 배수 다 true 처리
			int temp = i;
			int j = 1;
			if (i * i <= max) {
				while (true) {
					j++;
					if (temp * j > max)
						break;
					numbers[temp * j] = true;
				}
			}
		}

		// 골드바흐 파티션 계산하기.
		for (int k = 0; k < input.length; k++) {
			int answer = 0;
			for (int i = 2; i <= input[k] / 2; i++) {
				if (!numbers[i] && !numbers[input[k] - i])
					answer++;
			}
			System.out.println(answer);
		}
		br.close();
	}
}
