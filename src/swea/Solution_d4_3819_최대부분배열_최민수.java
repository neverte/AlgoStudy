package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d4_3819_최대부분배열_최민수 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d4_3819.txt"));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		
//		int T = Integer.parseInt(br.readLine());
		int T = Integer.parseInt(sc.next());
		for (int tc = 0; tc < T; tc++) {
			//길이 N 1~20만
//			int n = Integer.parseInt(br.readLine());
			int n = Integer.parseInt(sc.next());
			int[] arr = new int[n]; //[0]에는 원소 값, [1]에는 해당위치에서 최대값
			int max = -1001;
			for (int i = 0; i < n; i++) {
//				arr[i] = Integer.parseInt(br.readLine());
				arr[i] = Integer.parseInt(sc.next());
				//연산 1~n개 선택
				if(i == 0) {
					arr[i] = arr[i];
				}
				else {
					arr[i] = Math.max(arr[i], arr[i] + arr[i-1]);
				}
				max = Math.max(max, arr[i]);
			}
			
			System.out.println("#"+(tc+1)+" "+max);
		}//tc끝
		
//		br.close();
		sc.close();
	}

}
