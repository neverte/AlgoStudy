package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//[골드 5] 게리맨더링
//https://www.acmicpc.net/problem/17471
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_17471_게리맨더링_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_17471"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//구역의 개수 N 2~10
		int N = Integer.parseInt(br.readLine());
		//각 구역의 인구
		int[] population = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			population[i] = Integer.parseInt(st.nextToken());			
		}
		//인접 구역
		int[][] close = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
			st2.nextToken();
			while (st2.hasMoreTokens()) {
				close[i][Integer.parseInt(st2.nextToken())-1] = 1;
			}	
		}
		
//		for(int[] a : close)System.out.println(Arrays.toString(a));
		
		//어떻게 지역구를 나눌 것인가.
		//진영은 조합으로 선택하나?
		//고려해야하는 조건 양 진영은 각각 서로 이어져있어야 한다. 검사 DFS?
		//인구수 최소
		
		//일단 나눈다음 각각의 경우에 대해 인구수 계산?
		
		br.close();
	}
}
