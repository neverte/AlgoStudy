package baekjoon_01001_02000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[실버 2] 최대공약수
//https://www.acmicpc.net/problem/1850
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_1850_최대공약수_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_1850"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//큰수 A, 작은 수 B. A, B의 최대공약수 = B, A%B이다.
		
		//입력 받기
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		//정답은 A-B를 한다음에 나온 숫자만큼 일을 찍는다.
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		euclidean(a, b);
		StringBuilder sb = new StringBuilder();
		for (long i = 0; i < answer; i++) {
			sb.append(1);
		}
		System.out.print(sb);
		
		br.close();
	}

	static long answer = 0;
	private static void euclidean(long a, long b) {
		if(b > a) swap(a, b);
		if(b == 0) {
			answer = a;
			return;
		}
		euclidean(b, a%b);
	}

	private static void swap(long a, long b) {
		long temp = b;
		b = a;
		a = temp;
	}
}

//참고: https://wonit.tistory.com/413