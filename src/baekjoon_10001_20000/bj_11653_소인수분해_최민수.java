package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//[실버 4] 소인수분해
//https://www.acmicpc.net/problem/11653
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_11653_소인수분해_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_11653"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//아이디어
		//에라토스테네스의 체로 소수를 찾은 다음
		//2~마지막 소수까지 직접 나눠보기
		
		int n = Integer.parseInt(br.readLine());
		
		int[] prime = new int[n+1];
		
		findPrime(prime);
		
		int primeNow = 1;
		//소수로 나누기
		while(n > 1) {
			//이번에 사용할 소수 찾기
			for (int i = primeNow+1; i < prime.length; i++) {
				if(prime[i] == 1) {
					primeNow = i;
					prime[i] = -1;
					break;
				}
			}
			//안나눠질때까지 나눠보기
			while( n % primeNow == 0) {
				n /= primeNow;
				System.out.println(primeNow);				
			}
		}
		
		br.close();
	}

	private static void findPrime(int[] prime) {
		prime[0] = -1;
		prime[1] = -1;
		
		for (int i = 2; i < prime.length; i++) {
			//이전에 방문한 적 있는지
			if(prime[i] != 0) continue;
			
			prime[i] = 1;
			for (int j = 2; i*j < prime.length; j++) {
				prime[i*j] = -1;				
			}
			
		}
		
	}
}
