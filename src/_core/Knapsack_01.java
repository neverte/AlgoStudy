package _core;

import java.util.Scanner;

public class Knapsack_01 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); //물건의 개수
		int W = sc.nextInt(); //가방의 무게
		
		int[] weights = new int[N+1]; //물건의 무게 정보
		int[] profits = new int[N+1]; //물건의 가치 정보
		int[][] D = new int[N+1][W+1]; //해당 물건까지 고려하여 해당 무게를 만들 때의 최대가치
		
		for (int i = 1; i <= N; i++) {
			weights[i] = sc.nextInt();
			profits[i] = sc.nextInt();
		}
		
		for (int i = 1; i <= N; i++) { //1번 물건부터 고려
			for (int w = 1; w <= W; w++) { //무게 1부터 고려, 가방의 무게라고 생각해도 좋다
				if(weights[i] <= w) { //가방에 넣을 수 있는 상황
					//넣을까: D[i-1][w-weights[1]] + profits[i]
					//말까: D[i-1][w]
					D[i][w] = Math.max(D[i-1][w-weights[1]] + profits[i], D[i-1][w]);
					
				}else { //가방에 넣지 못하는 상황
					D[i][w] = D[i-1][w];
					
				}
			}
		}
		System.out.println(D[N][W]);
		sc.close();
	}

}
