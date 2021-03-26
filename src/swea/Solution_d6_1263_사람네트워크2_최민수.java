package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d6_1263_사람네트워크2_최민수 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d6_1263.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		final int INF = 9999999;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int adj[][] = new int[n][n];
			int distance[][] = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					adj[i][j] = Integer.parseInt(st.nextToken());
					if(i != j && adj[i][j] == 0) adj[i][j] = INF;
					distance[i][j] = adj[i][j];
				}
			}
			//Floyd-warshall
			for (int k = 0; k < n; k++) {//경유지
				for (int i = 0; i < n; i++) { //출발지
					for (int j = 0; j < n; j++) { //도착지
						if(i == j) continue;
						distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
					}
					
				}
				
			}
			
			int answer = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				int sum = 0;
				for (int j = 0; j < n; j++) {
					if(sum > answer) break;
					sum += distance[i][j];
				}
				answer = Math.min(answer, sum);
			}
			
//			for(int[] ii: adj)System.out.println(Arrays.toString(ii));
//			for(int[] ii: distance)System.out.println(Arrays.toString(ii));
			System.out.println("#"+(tc+1)+" "+answer);
			
		}//tc 끝
		
		br.close();
	}

}
