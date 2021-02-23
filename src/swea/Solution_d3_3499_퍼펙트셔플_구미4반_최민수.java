package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// 패키지 날려야되나
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d3_3499_퍼펙트셔플_구미4반_최민수 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d3_3499.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		//큐 2개 만들기
		ArrayDeque<String> queue1 = new ArrayDeque<String>();
		ArrayDeque<String> queue2 = new ArrayDeque<String>();
		
		//테케
		int T = Integer.parseInt(br.readLine());
//		System.out.println(T);
		for (int i = 0; i < T; i++) {
//			System.out.println(i);
			//출력 준비
			//초기화
			sb.setLength(0);
			sb.append("#").append((i+1));
			
			//카드 N개 1~1000
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				if(j <= (N-1)/2) queue1.add(st.nextToken());
				else queue2.add(st.nextToken());
			}
			for (int j = 0; j < N/2; j++) {
				sb.append(" ").append(queue1.pop());
				sb.append(" ").append(queue2.pop());
			}
			if(!(queue1.isEmpty())) sb.append(" ").append(queue1.pop());
//			System.out.println(queue1.toString());
//			System.out.println(queue2.toString());
			System.out.println(sb);
		}
		
		br.close();
	}

}
