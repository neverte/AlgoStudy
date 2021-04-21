package swea_d4_d6;

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
public class Solution_d4_3820_롤러코스터_최민수 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d4_3820.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		final int primeNumber = 1000000007;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			// 레일 n개 2~20만
			//N개의 레일 모두 사용
			int n = Integer.parseInt(br.readLine());
			
			//각 레일에는 a, b가 주어진다. 1~10억
			//속도 v가 av+b가 된다.
			PriorityQueue<rail> pq = new PriorityQueue<>(); 
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				pq.offer(new rail(a, b));
			}
			
			long v = 1;
			while(!pq.isEmpty()) {
				rail now = pq.poll();
				v = ((v * now.a)%primeNumber + now.b) % primeNumber;
			}

			System.out.println("#"+(tc+1)+" "+v);
		}//tc끝
		
		br.close();
	}

	static class rail implements Comparable<rail>{
		long a, b;

		public rail(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}
		
		//(a - 1)/b 값이 큰 순으로 내림차순하면 된다.
		// (i+1)번째 < i번째로
		@Override
		public int compareTo(rail o) {
			return -Long.compare((this.a - 1)*o.b, (o.a - 1)*b);
		}

	}
}
