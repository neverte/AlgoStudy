package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d4_3814_평등주의_최민수 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d4_3814.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//길이 N 수열
		//수열의 수를 하나 골라 1 감소시킬 수 있다.
		//이 연산을 K할 수 있다.
		//인접한 숫자 차이의 최대값을 최소로 하는 프로그램
		//즉 수열에서 인접한 숫자의 차이는 모두 c이하
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			//길이 N 2~10만
			//연산회수 K 1~10^9(10억)
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			int[][] arr = new int[n][3];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < n; i++) {
				arr[i][0] = Integer.parseInt(st.nextToken());
			}
			
			//연산을 K번해서 C값으로 줄일 수 있어? 이분탐색
			int low = 1;
			int high = 1000000000;
			int mid = 0;
			while(low < high) {
				mid = (low + high) / 2;
				
				for (int i = 0; i < n; i++) {
					arr[i][1] = arr[i][0];
				}
				
				if(isPossible(arr, k, mid)) { //mid 이하로 줄일 수 있다. 
					high = mid;
				}else {
					low = mid+1;
				}
			}
			System.out.println("#"+(tc+1)+" "+high);
		}//tc끝
		br.close();
	}

	private static boolean isPossible(int[][] arr, int k, int mid) {
		//수열을 처음부터 읽으면서 k번 연산해서
		//인접한 숫자의 차이를 mid이하로 만들 수 있는가

		int len = arr.length;
		
		//왼 -> 오
		arr[0][1] = arr[0][0];
		for (int i = 1; i < len; i++) {
			if(arr[i][0] > arr[i-1][1] + mid) arr[i][1] = arr[i-1][1] + mid;
			else arr[i][1] = arr[i][0];
		}
		
		//오->왼
		arr[len-1][2] = arr[len-1][0];
		for (int i = len-2; i >= 0; i--) {
			if(arr[i][0] > arr[i+1][2] + mid) arr[i][2] = arr[i+1][2] + mid;
			else arr[i][2] = arr[i][0];
		}
		
		//두개 합산
		int count = 0;
		for (int i = 0; i < len; i++) {
			if(arr[i][1] > arr[i][2]) arr[i][1] = arr[i][2];
			count += arr[i][0] - arr[i][1];
			if(count > k) return false;
		}
		return true;
	}

}
