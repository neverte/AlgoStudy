package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d4_3234_준환이의양팔저울_구미4반_최민수 {
	static int pendulum[];
	static int N;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d4_3234.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//테케, T
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			//N 1~9
			N = Integer.parseInt(br.readLine());
			pendulum = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				pendulum[j] = Integer.parseInt(st.nextToken());
			}
			
			answer = 0;
			
			//1. 순열을 돌린다.
			//2. 순열 돌린 결과값을 가지고 왼 / 오 선택
			//3. 놓는 순간 오른쪽으로 기울어지면 return;
			vis = new boolean[N];
			permList = new int[N];
			answer = 0;
			perm(0, N);
			System.out.println("#"+(i+1)+" "+answer);
		} //테케 끝
		
		br.close();
	}
	
	static boolean[] vis;
	static int[] permList;
	public static void perm(int count, int N) {
		if(count == N) {
//			System.out.println(Arrays.toString(permList));
			//permList가지고 왼오
			checkLR(permList, 0, 0, 0);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(vis[i]) continue;
			vis[i] = true;
			permList[count] = i;
			perm(count+1, N);
			vis[i] = false;
		}
	}

	static int answer;
	private static void checkLR(int[] permL, int Ltotal, int Rtotal, int count) {

		if(count == N) {
			answer++;
			return;
		}
		checkLR(permL, Ltotal+pendulum[permL[count]], Rtotal, count+1);
		//더해서 무거우면 스킵
		if(Ltotal < Rtotal + pendulum[permL[count]]) {
			return;
		}
		checkLR(permL, Ltotal, Rtotal+pendulum[permL[count]], count+1);

	}
}
