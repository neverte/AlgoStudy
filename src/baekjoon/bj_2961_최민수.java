package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[실버 1] 도영이가 만든 맛있는 음식
//https://www.acmicpc.net/problem/2961
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_2961_최민수 {
	
	static int taste[][];
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_2961"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//브루트 포스
		//모든 부분집합을 구해, 그 때마다 신맛, 쓴맛을 구한다.
		//재료가 최대 10개니까 1023가지의 경우의 수.
		
		//재료의 개수 N 1~10
		int N = Integer.parseInt(br.readLine());
		
		
		
		//신맛과 쓴맛
		// 계산 결과 ~10억: int형 가능.
		taste = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			taste[i][0] = Integer.parseInt(st.nextToken());
			taste[i][1] = Integer.parseInt(st.nextToken());
		}

		
		//부분집합 돌리기
		subset(N);
		
		
		//출력: abs(신맛 -쓴맛)이 가장 작은 것.
		System.out.println(result);
		
		br.close();
	}
	
	//binary Counting해보기
	public static void subset(int N) {
		//신맛, 쓴맛
		//신맛 = 재료 신맛의 곱
		//쓴맛 = 재료 쓴맛의 합
		
		//재료는 적어도 1개 들어가야되기 때문에 1부터 시작
		for (int i = 1; i < Math.pow(2, N); i++) {
			int tempS = 1, tempB = 0;
			for (int j = 0; j < N; j++) {
				if((i & 1<<j) != 0) { //1을 만나면
					tempS *= taste[j][0];
					tempB += taste[j][1];
				}
			}
			result = Math.min(result, Math.abs(tempS - tempB));
		}
	}
}
