package baekjoon_01001_02000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[실버 5] 최소공배수
//https://www.acmicpc.net/problem/1934
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_1934_최소공배수_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_1934"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			
			//출력
			System.out.println(lcm(a, b));
			
		}
	
		br.close();
	}

	private static int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}

	private static int gcd(int a, int b) {
		
		
		while(true) {
			if(b>a) {
				int temp = a;
				a = b;
				b = temp;
			}
			
			if(b == 0) break;
			
			int c = b;
			a = a%b;
			b = c;
		}
		
		return a;
	}
}
