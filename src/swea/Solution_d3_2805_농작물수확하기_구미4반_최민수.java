package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// 패키지 날려야되나
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d3_2805_농작물수확하기_구미4반_최민수 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d3_2805.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//testcase
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			//농장의 크기 N
			int N = Integer.parseInt(br.readLine());
			//농작물 넣을 배열 arr
			int[][] arr = new int[N][N];
			//임시배열 temp
			String[] temp = new String[N];
			//정답
			int value = 0;
			for (int j = 0; j < N; j++) {
				temp = br.readLine().split("");
				
				//(N/2) - j => 2, 1, 0, -1, -2
				//Math.abs(((N/2) - j)*2) => 2, 1, 0, 1, 2
				
				//(출력 안할꺼야) - (출력할꺼야) - (출력안할꺼야)
				
				//이만큼 각 줄에서 출력안할 것이다.
				int delForward = Math.abs((N/2) - j);
				//이만큼 출력할 꺼다.
				int printNum = N - delForward*2;
				for (int k = 0; k < N; k++) {
					arr[j][k] = Integer.parseInt(temp[k]);
					if(delForward-- > 0) continue;
					else if(printNum-- > 0) {
						//결과 저장
						value += arr[j][k];
					}else continue;
					
					
					// 1이면 원소 1개
					// 3이면 1-3-1
					// 5면 1-3-5-3-1개를 받는다.
					//5 기준으로
					 // j=0일 때 k=2 (2-0) = 2
					// j=1일때 k=1,2,3 (2-1) = 1
					//j=2 k 01234 (2-2 = 0
					//j=3 k123
				}
			}
			System.out.println("#"+(i+1)+" "+value);
		}//테스트 케이스 끝
		br.close();
	}

}
