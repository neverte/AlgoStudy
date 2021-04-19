package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
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
		int n = Integer.parseInt(br.readLine());
		
		//각 구역의 인구
		int[] population = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			population[i] = Integer.parseInt(st.nextToken());			
		}
		
		//인접 구역
		int[][] adj = new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			st.nextToken();
			while (st.hasMoreTokens()) {
				adj[i][Integer.parseInt(st.nextToken())] = 1;
			}	
		}
		
		//1) 구역의 개수만큼 조합으로 나눈다.
		//2) 1의 결과가 두 선거구로 나눌 수 있는지 검증한다.
		//3) 나눌 수 있다면 인구 차이 값을 구한 후 최소값을 갱신한다.
		
		//1) 구역의 개수만큼 조합으로 나눈다.
		//(1, n-1), (2, n-2)
		for (int i = 1; i <= n/2; i++) { //limit 지정
			int[] select = new int[n+1];
			comb(1, 0, i, select, adj, population);			
		}
		
		
		if(answer == 2147483647) System.out.println(-1);
		else System.out.println(answer);
		
		
		br.close();
	}

	static int answer = Integer.MAX_VALUE;
	//전체 n개 중에 Limit개를 뽑는다.
	private static void comb(int start, int count, int limit, int[] select, int[][] adj, int[] pop) {
		int n = select.length-1;
		
		if(count == limit) {
			
			//1-2) 뽑은 조합을 가지고 두 개의 지역구로 나눔.
			int[] select2 = new int[n+1];
			int j = 1;
			int k = 1;
			for (int i = 1; i <= n; i++) {
				//1번부터 n번 지역구를 물어봄
				if(select[j] == i) { //select가 갖고 있으면 패스
					j++;
				}else {
					select2[k] = i;
					k++;
				}
			}
//			System.out.println(Arrays.toString(select));
//			System.out.println(Arrays.toString(select2));
//			System.out.println();
			
			//2) 1의 결과가 두 선거구로 나눌 수 있는지 검증한다.
			//BFS로 자신의 원소에 도달할 수 있는지 확인
			boolean visit1[] = new boolean[n+1];
			boolean visit2[] = new boolean[n+1];
			if(isAvailable(select, visit1, adj) && isAvailable(select2, visit2, adj)) {
				//3) 나눌 수 있다면 인구 차이 값을 구한 후 최소값을 갱신한다.
				int sum = 0;
				for (int i = 1; i < select.length; i++) {
					if(select[i] != 0) sum += pop[select[i]-1];
					if(select2[i] != 0) sum -= pop[select2[i]-1];
					
				}
				answer = Math.min(answer, Math.abs(sum));
			}
			return;
		}
		
		for (int k = start; k <= n; k++) { 
			select[count+1] = k; 
			//start자리에 k+1이 들어가야 함
			comb(k+1, count+1, limit, select, adj, pop);
		}
		
	}

	private static boolean isAvailable(int[] select, boolean[] visit, int[][] adj) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.offer(select[1]);
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			if(visit[now]) continue;
			visit[now] = true;
			
			for (int i = 0; i < adj.length; i++) {
				if(adj[now][i] == 1 && !visit[i]) {
					//우리 원소중에 있는지 확인
					for (int j = 1; j < select.length; j++) {
						if(select[j] == i) q.offer(i);
					}
				}
			}
		} //bfs끝
		
		for (int i = 0; i < select.length; i++) {
			if(select[i] != 0) { //값이 있는데, visit이 false면
				if(!visit[select[i]]) return false; 
			}
		}
		
		return true;
	}
}
