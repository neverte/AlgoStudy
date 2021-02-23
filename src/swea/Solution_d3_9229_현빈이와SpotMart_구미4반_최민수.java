package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 패키지 날려야
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d3_9229_현빈이와SpotMart_구미4반_최민수 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d3_9229.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i <T; i++) {
			//과자 봉지 개수 N: 2~1000, 무게 합 제한 M: 2~200만
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[] weight = new int[N];
			StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				//N개의 과자봉지 무게 1~100만
				weight[j] = Integer.parseInt(st2.nextToken());
//				int temp = Integer.parseInt(st2.nextToken());
//				if(temp >= M) continue; //1개가 무게재한급이면 패스
			}
			
			int max = -1;
			for (int j = 0; j < N; j++) {
				for (int k = j+1; k < N; k++) {
					int temp = weight[j]+weight[k];
					if (temp > M) continue;
					max = Math.max(weight[j]+weight[k], max);
				}
				
			}
			
			System.out.println("#"+(i+1)+" "+max);
		}
		
		br.close();
	}

}
