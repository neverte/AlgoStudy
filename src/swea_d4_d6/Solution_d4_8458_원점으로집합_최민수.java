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
public class Solution_d4_8458_원점으로집합_최민수 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d4_8458.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			//x군을 구해보면
			//1군은 1
			//2군은 1, 3
			//3군이면 0, 2, 4, 6
			//4군이면 0, 2, 4, 6, 8, 10
			//5군의 합은 15이고 홀수이니 1~15까지의 홀수
			//합이 짝수면 0~합까지의 짝수
			
			//두개가 홀홀인지 짝짝인지 확인, 홀짝이면 절대 될 수가 없음.
			//둘 중에 큰 값을 구해서
			//큰 값이 속하는 범위를 찾자.
			
			//n 1~10
			int n = Integer.parseInt(br.readLine());
			boolean isOdd = false;
			boolean isEven = false;
			int answer = 0;
			int max = 0;
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int x = Math.abs(Integer.parseInt(st.nextToken()));
				int y = Math.abs(Integer.parseInt(st.nextToken()));
				
				//홀짝, 짝홀이면
				if((x+y)%2 == 1) isOdd = true;
				else if((x+y)%2 == 0) isEven = true;
				
				//최대값 구하기
				max = Math.max((x+y), max);
			}
			if(isOdd && isEven) {
//					answer = -1;
				System.out.println("#"+(tc+1)+" "+(-1));
				continue;
			}
			
			//최대값이 속하는 범위 찾기
			//범위가 20억까지 가능함.
			int num = rootFormula(max);
			//max = (x^2 + x) / 2
			//2max = x^2 + x
			
			while(true) { //근의공식부터 더해나감.
				long sum = sum(num);
				//짝이면 짝수, 홀이면 홀수여야함
				//아무튼 된다. 더 줄여도 된다.
				if((isOdd && sum % 2 == 1)||(isEven && sum % 2 == 0)) {
					answer = num;
					break;
				}
				num++;
			}

			System.out.println("#"+(tc+1)+" "+answer);
		}//tc끝
		
		br.close();
	}
	
	static long sum(int num) {
		return 0L + (long)num*(num+1L)/2L;
	}

	static int rootFormula(long c){
	
		return (int)Math.ceil((-1.0 +Math.sqrt(1.0 + 8.0*c)) / 2.0);
	}
}
//1. 시간초과
