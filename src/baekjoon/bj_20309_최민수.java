package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;

//[실버 4] 트리플 소트
//https://www.acmicpc.net/problem/20309
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기

public class bj_20309_최민수 {
	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_20309_input"));
		Scanner scan = new Scanner(System.in);
		String state = "YES";

		int N = scan.nextInt();
		// 5라고 생각했을 때 안되는 경우는
		// 홀수가 홀수자리에 안오고, 짝수가 짝수자리에 안오면 안된다.
		// 왜냐하면 양끝 원소가 트리플소트는 2칸씩 움직이기 때문.
		// 6이나 4일때도 마찬가지.
		// 즉 배열을 받을 때 홀수자리에 홀수가 오는지 확인하면 될 듯.
		for (int i = 0; i < N; i++) {
			int num = scan.nextInt();
			/*
			 * 김정수님 코드: 홀수 짝수 한번에 처리 if arr[i-1]%2 != i%2:
			 */

			// 짝수 자리일 때(i는 0부터 시작하니까, 0, 2, 4가 홀수번째자리
			if (i % 2 == 1 && num % 2 == 1) {
				// 홀수가 오면 NO
				state = "NO";
				break; // break 유무 시간 1128ms <-> 1140ms
			} else if (i % 2 == 0 && num % 2 == 0) {
				// 홀수 자리일 때
				// 짝수가 오면 NO
				state = "NO";
				break;
			}
		}
		System.out.println(state);
		scan.close();

	}
}
