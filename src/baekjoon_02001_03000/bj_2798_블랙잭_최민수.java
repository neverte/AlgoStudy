package baekjoon_02001_03000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[브론즈2] 블랙잭
//https://www.acmicpc.net/problem/2798
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_2798_블랙잭_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_2798"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//카드의 개수 N 3~100, 숫자 M 10~30만
		StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st1.nextToken());
		int M = Integer.parseInt(st1.nextToken());
		
		int[] card = new int[N];
		// 1~1만
		StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			card[i] = Integer.parseInt(st2.nextToken());
		}
		
		//조합문제
		visit = new boolean[N];
		combNums = new int[3];
		comb(visit, 0, combNums, card, M);
		
		System.out.println(max);
		
	
		br.close();
	}

	static boolean visit[];
	static int combNums[];
	static int max = Integer.MIN_VALUE;
	//NC3
	private static void comb(boolean v[], int count, int[] result, int[] card, int M) {
		if(count == 3) {
			//합계 구하고
			int sum = 0;
			for (int i = 0; i < result.length; i++) {
				sum += result[i];
			}
			if(sum <= M ) {
				max = Math.max(max, sum);
			}
			return;
		}
		
		for (int i = count; i < v.length ; i++) {
			if(v[i]) continue;
			v[i]= true;
			result[count] = card[i];
			comb(v, count+1, result, card, M);
			v[i]=false;
			
		}
	}
}
