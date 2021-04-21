package swea_d1_d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 패키지 날리기.
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d3_5215_햄버거다이어트_구미4반_최민수 {
	static int N, L, maxPoint = Integer.MIN_VALUE, curPoint = 0, curCal = 0;
	static int[][] al;
	static boolean[] isSelected;
	
	static void hamSubset(int count) {
		if(count == N) { 
			for (int i = 0; i < N; i++) {
				if(isSelected[i] == true) { //예시 [true, true, true, false, false]
					curPoint += al[i][0];
					curCal += al[i][1];
				}
				if(curCal > L) { //현재 총 칼로리 > 제한 칼로리
					curCal = 0;
					curPoint = 0;
					return;
				}
			}
			//최대 칼로리 이하이면서, 재료 선택 끝
			maxPoint = Math.max(maxPoint, curPoint);
			//한 개의 부분집합 계산이 끝남.
			curCal = 0;
			curPoint = 0;
//			System.out.println(Arrays.toString(isSelected));
			return;
		}
		isSelected[count] = true;
		hamSubset(count+1);
		
		isSelected[count] = false;
		hamSubset(count+1);
	}
	
	//[아이디어]
	//부분집합으로 경우의 수를 다 찾은 다음
	//제한 칼로리를 초과한 집합을 다 제거하고
	//남은 부분집합 중 가장 높은 점수를 출력
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d3_5215.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//테케
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			//다음 테스트케이스를 위해 초기화.
			maxPoint = 0;
			curPoint = 0;
			curCal = 0;
			
			//재료의 수 N(1~20), 제한 칼로리 L(1~1만)
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			//재료에 대한 맛, 칼로리
			al = new int[N][2];
			for (int i = 0; i < N; i++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
				//i번째 재료에 대한 맛 점수, 칼로리
				al[i][0] = Integer.parseInt(st2.nextToken());
				al[i][1] = Integer.parseInt(st2.nextToken());
			}
			//최고의 점수 버거 구성하기
			isSelected = new boolean[N];
			hamSubset(0);
			
			//각 줄마다 "#T" (T는 테스트 케이스 번호)를 출력한 뒤,
			//주어진 제한 칼로리 이하의 조합중에서
			//가장 맛에 대한 점수가 높은 햄버거의 점수를 출력한다.
			System.out.println("#"+(t+1)+" "+maxPoint);
		}

		//개선 방안: DP
		//큰 맥락은 연산 결과를 저장해서, 다시 연산하지 않고 불러오자.
		//피보나치 수열할 때 잠시 언급.
		//예시 [true, true, true, false, false]
		br.close();
	}

}
