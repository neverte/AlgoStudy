package swea_d1_d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 패키지 날려야되나
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d3_1225_암호생성기_구미4반_최민수 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d3_1225.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			//테케 번호 입력 받기
			int T = Integer.parseInt(br.readLine());
			//배열 입력 받기
			Queue<Integer> q = new LinkedList<Integer>();
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			//배열 종료조건 체크
			int count = 0;
			for (int i = 0; i < 8; i++) {
				int temp = Integer.parseInt(st.nextToken());
				q.add(temp);
				//마지막 암호 배열은 모두 한 자리 수로 구성되어 있다.
				if(temp<10) count++;
			}
			if(count == 8)break;
			
			//연산 처리
			Loop: while(true) {
				int temp;
				for (int j = 1; j < 6; j++) {
					if(q.peek() - j <= 0) {
						q.poll();
						temp = 0;
						q.add(temp);
						break Loop;
					}else {
						temp = q.poll() - j;
						q.add(temp);
					}
				}
				
			}
			sb.setLength(0);
			sb.append("#"+T);
			for (int i = 0; i < 8; i++) {
				sb.append(" ").append(q.poll());
			}
			//출력
			System.out.println(sb);
			if(T == 10) break;
		}
		br.close();
	}

}
