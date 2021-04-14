package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;

//[브론즈 1] 제곱근
//https://www.acmicpc.net/problem/13706
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_13706_제곱근_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_13706"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//N의 길이는 최대 800자리
		BigInteger n = new BigInteger(br.readLine());
		
		//변수 x를 선언하여 이분탐색으로 x^2 == n이 되는지 확인하자
		BigInteger low = new BigInteger("1");
		BigInteger high = n;
		BigInteger mid = new BigInteger("1");
		BigInteger one = new BigInteger("1");
		BigInteger two = new BigInteger("2");
		
		while(true) {
			mid = (low.add(high)).divide(two);
			
			BigInteger square = mid.multiply(mid);
			
			if(square.compareTo(n) == 0) { //일치하면 0, 
				System.out.println(mid);
				break;				
			}else if(square.compareTo(n) == -1) { //오른쪽이 크면 -1
				// = 제곱한 수가 작다 = 더 늘려야 한다.
				low = mid.add(one);
			}else { //왼쪽이 크면 1, 
				// = 제곱한 수가 더 크다 = 더 줄여야 한다.
				high = mid;
			}
		}
		
		br.close();
	}
}
