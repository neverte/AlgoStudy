package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d3_3307_최장증가부분수열_최민수 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d3_3307.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		//수열의 길이 N 1~1000
		for (int tc = 0; tc < T; tc++) {
			int n = Integer.parseInt(br.readLine());
			int arr[] = new int[n];
			int lis[] = new int[n];
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int answer = 0;
			
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				lis[i] = 1;
				for (int j = 0; j < i; j++) {
					if(arr[j] < arr[i] && lis[i] < lis[j]+1) {
						lis[i] = lis[j]+1;
					}
				}
				answer = Math.max(answer, lis[i]);
			}
//			System.out.println(Arrays.toString(arr));
//			System.out.println(Arrays.toString(lis));
			System.out.println("#"+(tc+1)+" "+answer);
		}

		br.close();
	}

}
