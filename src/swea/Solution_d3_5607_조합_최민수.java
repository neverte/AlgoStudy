package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d3_5607_조합_최민수 {
	static final int MOD = 1234567891;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d3_5607.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			n_r = 0;
			rrr = 0;
			
			//페르마의 소정리 없이 풀기
			//파일명을 under_fermat해서 페르마해서 제출하면 좋고.
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			//n 100만, r <= n
			int n = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			//n C r = n! / ((n-r)! * r!) mod 1234567891
			//= n! * ((n-r)!)^1234567889 * ((r)!)^1234567889

			//factorial 계산 여러번 하기 싫으니까 계산해둔다.
			long[] fact = new long[n+1]; //1~n번까지
			fact[0] = 1;
			//이 과정에서 n!, n-r!, r! 다 구한다.
			for (int i = 1; i <= n; i++) {
				fact[i] = fact[i-1] * i % MOD ;
			}
			n_r = fermat(fact[n-r], MOD-2);
			rrr = fermat(fact[r], MOD-2);
			long temp = (fact[n] * n_r) % MOD;
			
//			System.out.println(fact[n]);
//			System.out.println(n_r);
//			System.out.println(rrr);
//			System.out.println(temp);
//			
			System.out.println("#"+(tc+1)+" "+(temp * rrr % MOD));
		}//tc끝
		
		br.close();
	}

	//너무 지저분해서 사용.
	static long n_r;
	static long rrr;
	
	private static long fermat(long a, int count) {
		//1/a값을 페르마 소정리로 바꿔서 반환해주면 된다.
		//a^(MOD-2) = 1/a
//		System.out.println(count);
		
        if (count == 0) return 1;
        long temp = fermat(a, count / 2);
        long ret = (temp * temp) % MOD;
        
        //2로 나누어 떨어진다는 것
        //즉 짝수는 자기자신 2번 곱한것이지만
        //홀수는 자기자신 2번 곱한것에 +1번 더 곱해줘야함.
        if (count % 2 == 0) return ret;
        else return (ret * a) % MOD;
	}
}

//참고: https://m.blog.naver.com/1ilsang/221461026692
//오답원인: fact 배열에 int를 초과하는게 들어갔음.