package swea_d4_d6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d6_3813_그래도수명이절반이되어서는_최민수 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d6_3813.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//N개의 블록
		//모든 블록이 얼마나 닳았는지 값 w 1~20만, 값이 클수록 남은 수명이 많다.
		//수명을 최대한 길게 하고 싶다.
		
		//k개의 덩어리, i번째 덩어리는 Si개의 연속된 블록에 저장되어야 한다.
		//또 덩어리들은 입력에 주어진 순서대로 작은 번호의 블록부터 저장되어야 한다.
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			//N: 1~20만
			//K: 1~N
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			//W1~Wn: 소비된 수명
			int[] wear = new int[n+1]; //[0]: 수명값
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= n; i++) {
				wear[i] = Integer.parseInt(st.nextToken());
			}
			
			//S1~Sk: 넣어야 하는 덩어리
			int[] s = new int[k+1];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= k; i++) {
				s[i] = Integer.parseInt(st.nextToken());
			}
			
			//canPut 결과 집어넣을 배열
			//0이면 확인 안함
			//-1이면 안된다.
			//1이면 된다.			
			int low = 1;
			int high = 200000;
			
			int answer = 0;
			
			while(low<high) {
				int median = (low + high)/2;

				boolean possible = false;
				possible = isPossible(wear, s, median);
				
				if(possible) { //범위가 커져야 된다.
					high = median;
					answer = high;
				}else { //작아져야 한다.
					low = median + 1;
				}

			}
			System.out.println("#"+(tc+1)+" "+answer);
	
		}//tc 끝
		br.close();
	}


	private static boolean isPossible(int[] wear, int[] s, int median) {
		//s에 있는 애들 다 넣을 수 있는지 확인
		int data = 1; //통과한 덩어리 개수
		int cnt = 0; //median 보다 작거나 같은 애들 카운트용
		for (int i = 1; i < wear.length; i++) {
			if(wear[i] <= median) {
				cnt++;
			}else {
				cnt = 0;
			}
			
			if(cnt == s[data]) {
				data++;
				cnt = 0;
				//더 작아져도 된다.
				if(data > s.length - 1) return true;
			}
		}

		//다 못넣었다. 커져야 된다.
		return false;
	}

}
//폐기 아이디어 1.
//각 길이별 합산 값을 따로 저장을 한 뒤에
//그 값과 위치를 저장한 다음. 값 순으로 sorting하고
//그다음에 하나씩 뽑는다. (중복안되게_
