package swea_d1_d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d3_6808_규영이와인영이의카드게임_구미4반_최민수 {
	
	static int gWin = 0, iWin = 0;
	static int[] iii;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d3_6808.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//테케 T
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			//규영이 카드.
			int[] ggg = new int[9];
			//인영이 카드
			int[] iii = new int[9];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 9; j++) {
				//아홉 개의 정수 1~18 중에서 나옴. 중복 X
				//규영이가 받은 카드.
				ggg[j] = Integer.parseInt(st.nextToken());
			}
			int[] ttt = ggg.clone();
			Arrays.sort(ttt);
//			System.out.println(Arrays.toString(ttt));
			int index = 0;
			int in = 0;
			for (int j = 0; j < 18; j++) {
				if(in == 9)break;
				//규영이가 카드를 들고 있다.
				if(index < 9 && ttt[index] == j+1) index++;
				//인영이가 받은 카드
				else iii[in++] = j+1;
			}
			
			// 규영이 순열 vs 인영이 1번~마지막 순열
			do {
				isWin(ggg, iii);
			} while (np(iii));
			System.out.println("#"+(i+1)+" "+gWin+" "+iWin);
			gWin = 0;
			iWin = 0;
		}
		br.close();
	}

	
	public static boolean np(int[] arr) {
		//1. 꼭대기 찾기
		int i = 8;
		while(i > 0 && arr[i-1] >= arr[i]) i--;
		if(i == 0) {
			return false;
		}
		//2. 교환할 값 찾기
		int j = 8;
		while(arr[i-1] >= arr[j]) j--;
		swap(arr, i-1, j);
		
		//3. 뒤로가면서 내림차순 정렬
		int k = 8;
		while(i < k) swap(arr, i++, k--);
		
		return true;
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static void isWin(int[] arr1, int[] arr2) {
		int gPoint = 0, iPoint = 0;
		for (int i = 0; i < 9; i++) {
			if(arr1[i] > arr2[i]) gPoint += arr1[i]+arr2[i];
			else iPoint += arr1[i]+arr2[i];
		}
		if (gPoint > iPoint) gWin++;
		else if(iPoint > gPoint) iWin++;
	}

}
