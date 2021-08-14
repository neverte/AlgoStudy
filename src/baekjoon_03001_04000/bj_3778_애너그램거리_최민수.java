package baekjoon_03001_04000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//[브론즈 2] 애너그램 거리
//https://www.acmicpc.net/problem/3778
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_3778_애너그램거리_최민수 {
	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_3778"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			String s1 = br.readLine();
			String s2 = br.readLine();

			// 알파벳 개수를 세아릴 배열
			int[][] alpha = new int[26][2]; // [0]에는 s1, [1]에는 s2

			// s1의 알파벳 구성을 분석해야 한다.
			char[] split1 = s1.toCharArray();
			int s1len = split1.length;
			for (int i = 0; i < s1len; i++) {
				// 97이 소문자 a
				alpha[(int) split1[i] - 97][0]++;
			}

			// s2를 이제 접근할 것이다.
			char[] split2 = s2.toCharArray();
			for (int i = 0; i < split2.length; i++) {
				alpha[(int) split2[i] - 97][1]++;
			}
			System.out.println("Case #" + (tc + 1) + ": " + isAnagram(alpha));

		} // tc끝

		br.close();
	}

	private static int isAnagram(int[][] alpha) {
		int answer = 0;
		for (int i = 0; i < alpha.length; i++) {
			answer += Math.abs(alpha[i][0] - alpha[i][1]);
		}

		return answer;
	}
}