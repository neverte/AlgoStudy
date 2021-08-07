package baekjoon_01001_02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//[실버 5] 숫자놀이
//https://www.acmicpc.net/problem/1755
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_1755_숫자놀이_최민수 {

	public static void main(String[] args) throws IOException {
		// 테스트를 위한 입력
		// System.setIn(new FileInputStream("res/input01"));
		// 빠른 출력을 위한 BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 읽었을 때 기준으로 사전 순으로 정렬하여 출력한다.

		// M, N이 주어진다. M~N 한줄에 10개씩
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// m, n을 각각 Integer로 변환
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		// 정답을 저장할 배열
		String[] answer = new String[n - m + 1];
		// n~m에 해당하는 i를 한개씩 처리할 것임
		for (int i = m; i <= n; i++) {
			// 십의 자리
			int first = i / 10;
			// 일의 차리
			int second = i % 10;

			// 십의 자리 수와, 일의 자리 수를 String으로 변환할 것이다
			String tempFirst = null;
			String tempSecond = null;
			// intToString method를 통해 String으로 바꾸어 준 값을 저장한다.
			tempFirst = intToString(first);
			tempSecond = intToString(second);

			// 한자리 수는 바로 읽음. 한 자리 수 == 10의 자리가 0임
			if (first == 0) {
				// String으로 변환한 값을 Answer에 저장한다.
				answer[i - m] = tempSecond;
			} else { // 두 자리 수
				// 십의 자리 " " 일의 자리를 answer에 저장한다.
				answer[i - m] = tempFirst + " " + tempSecond;
			}
		}

		// 알파벳 순으로 정렬한다.
		Arrays.sort(answer);

		// 정렬한 결과 값을 이제 다시 int로 바꾸어 출력해야 한다.
		for (int i = 0; i < answer.length; i++) {
			// 한 줄에 10개만 출력해야 하기 때문에 10개 마다 한 줄을 넘긴다
			if (i > 0 && i % 10 == 0)
				System.out.println();
			// 이전에 String으로 answer에 저장되어 있기 때문에 int로 변환하여 준다.
			int number = stringToInt(answer[i]);
			// int와 공백을 신경 써서 출력한다.
			System.out.print(number + " ");
		}

		br.close();
	}

	// 입력으로 들어온 num은 문자열, 공백으로 이루어져 있다.
	private static int stringToInt(String num) {
		// 따라서 공백을 기준으로 나눈 다음
		StringTokenizer st = new StringTokenizer(num, " ");
		// 앞 자리를 first에
		String first = st.nextToken();
		// 뒷 자지를 second에 저장할 것이다.
		String second = null;
		// 그런데 second는 한자리 숫자면 없을 수 있기 때문에 flag를 사용하여 판단한다.
		boolean haveSecond = false;
		// 앞자리를 받았는데 st에 토큰이 남아있다는 것은 두자리 숫자라는 뜻이기 때문에
		if (st.hasMoreTokens()) {
			// flag를 true로 만들고
			haveSecond = true;
			// second에 값을 넣어 준다.
			second = st.nextToken();
		}

		// first를 다시 숫자로 바꿀 것이다.
		int one = oneStringToInt(first);
		if (haveSecond) { // second가 있다면 숫자로 바꿀 것이다.
			int two = oneStringToInt(second);
			// 십의 자리 수 * 10 + 일의 자리수를 하면 원래 숫자로 변환할 수 있다.
			return 10 * one + two;
		}

		// 한 자리 숫자는 앞 자리 수만 반환하면 된다.
		return one;
	}

	// 문자열을 switch를 활용하여 int로 바꿔줄 것이다.
	private static int oneStringToInt(String first) {

		// 모든 경우마다 return을 사용하였기 때문에 break는 사용하지 않는다.
		switch (first) {
			case "zero":
				return 0;
			case "one":
				return 1;
			case "two":
				return 2;
			case "three":
				return 3;
			case "four":
				return 4;
			case "five":
				return 5;
			case "six":
				return 6;
			case "seven":
				return 7;
			case "eight":
				return 8;
			case "nine":
				return 9;
			default:
				return 0;
		}
	}

	// 위의 method와 반대로 int를 String으로 변환하여 줄 것이다.
	private static String intToString(int first) {
		switch (first) {
			case 0:
				return "zero";
			case 1:
				return "one";
			case 2:
				return "two";
			case 3:
				return "three";
			case 4:
				return "four";
			case 5:
				return "five";
			case 6:
				return "six";
			case 7:
				return "seven";
			case 8:
				return "eight";
			case 9:
				return "nine";
			default:
				return null;
		}

	}

}
