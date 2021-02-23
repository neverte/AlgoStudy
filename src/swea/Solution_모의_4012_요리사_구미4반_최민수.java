package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_모의_4012_요리사_구미4반_최민수 {
	
	static int N;
	static int synergy[][];
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_모의_4012.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//테케, T
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			//식재료의 수 N(4~16의 짝수)
			N = Integer.parseInt(br.readLine());
			synergy = new int[N][N];
			for (int j = 0; j < N; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int k = 0; k < N; k++) {
					int temp = Integer.parseInt(st.nextToken());
					//12 + 21 하기 귀찮으니까 입력받을 때 한번에 해버리겠다.
					if(synergy[k][j] != 0) synergy[k][j] += temp;
					else synergy[j][k] = temp;
				}
			}
			//배열 입력 테스트용
			for(int[] in: synergy) System.out.println(Arrays.toString(in));
			
			//재료 나누는걸 조합 NC(N/2)을 뽑아서 계산해서 출력
			getIngrA = new int[N/2];
			getIngrB = new int[N/2];
			minResult = Integer.MAX_VALUE;
			pickIngredient(0, 0);

			//출력
			//정답은 두 음식 간의 맛의 차이가 최소가 되도록 A음식과 B음식을 만들었을 때 그 차이 값
			System.out.println("#"+(i+1)+" "+minResult);
		}
		br.close();
	}
	
	static int[] getIngrA;
	static int[] getIngrB;
	static int minResult;
	//재료 조합뽑기
	public static void pickIngredient(int count, int start) {
		if(count == N/2) {
			int check = 0;
			int checkB = 0;
			for (int i = 0; i < N; i++) {
				if(check < N/2 && getIngrA[check] == i) check++;
				else if(checkB < N/2) getIngrB[checkB++] = i;
			}
			
			calcTaste(getIngrA, getIngrB);
			minResult = Math.min(minResult, diff);
			return;
		}
		
		for (int i = start; i < N; i++) {
			getIngrA[count] = i;
			pickIngredient(count+1, i+1);
		}
		
		
	}
	
	static int diff;
	
	public static void calcTaste(int[] A, int[] B) {
		//A맛 계산
		int tasteA = 0;
		//B맛 계산
		int tasteB = 0;
		for (int i = 0; i < A.length-1; i++) {
			for (int j = i+1; j < A.length; j++) {
				tasteA += synergy[A[i]][A[j]];
				tasteB += synergy[B[i]][B[j]];
			}
		}
		diff = 0;
		diff = Math.abs(tasteA - tasteB);
	}

}
