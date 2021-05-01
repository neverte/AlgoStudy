package baekjoon_02001_03000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[실버5] 최대공약수와 최소공배수
//https://www.acmicpc.net/problem/2609
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_2609_최대공약수와최소공배수_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_2609"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		int gcd = gcd(a, b);
		System.out.println(gcd);
		System.out.println(a*b/gcd);
		
		br.close();
	}

	private static int gcd(int a, int b) {
		while(true) {
			if(b > a) {
				int temp = a;
				a = b;
				b = temp;
			}
			if (b == 0) break;
			int temp = b;
			b = a%b;
			a = temp;
		}
		
		return a;
	}
}
