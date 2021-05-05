package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

//[실버 3] 로마 숫자 만들기
//https://www.acmicpc.net/problem/16922
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_16922_로마숫자만들기_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_16922"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int n = Integer.parseInt(br.readLine());
		
		//중복을 허용하는 조합.
		//만든 결과를 중복을 허용안하는 set에 넣는다.
		//단순한 경우의 수, 4^20 = 2^40 = 10^12 = 천억, long은 담을 수 있다.
		Set<Long> set = new HashSet<>();
		selected = new int[n];
		
		func(set, n, 0);
		
		System.out.println(set.size());
		
		br.close();
	}

	static int[] roman = {50, 10, 5, 1};
	static int[] selected;
	private static void func(Set<Long> set, int n, int now) {
		if(now == n) {
			long sum = 0;
			for (int i = 0; i < n; i++) {
				sum += roman[selected[i]];
			}
			set.add(sum);
			return;
		}
		if(now == 0) {
			for (int i = 0; i < 4; i++) {
				selected[now] = i;
				func(set, n, now+1);
				
			}
			
		}else { //앞에서 선택한 친구보다 같을 순 있어도 큰건 선택못하게 하자.
			for (int i = selected[now-1]; i < 4; i++) {
				selected[now] = i;
				func(set, n, now+1);
				
			}
			
		}
			
	}
}

//1. 단순 중복조합은 시간초과 -> 순서가 의미가 없다. XXXI 나 XXIX나 똑같다.