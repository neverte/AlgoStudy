package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d4_1238_Contact_구미4반_최민수2 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d4_1238.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		for (int tc = 0; tc < 10; tc++) {
			largestVisit = Integer.MIN_VALUE;
			largestNum = Integer.MIN_VALUE;
			
			//아이디어 그래프 BFS로 마지막 단계 사람들 구하기
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int length = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken())-1;//1~100을 0~99
			//다음과 같이 동일한 {from, to}쌍이 여러 번 반복되는 경우도 있으며
			int[][] adjArr = new int[100][100];
			st = new StringTokenizer(br.readLine(), " ");
			while(st.hasMoreTokens()) {
				int from = Integer.parseInt(st.nextToken())-1; //1~100을 0~99
				int to = Integer.parseInt(st.nextToken())-1;
				adjArr[from][to] = 1;
			}
			
//			for(int[] kk : adjArr)System.out.println(Arrays.toString(kk));
			
			bfs(adjArr, start);
			//가장 나중에 연락을 받게 되는 사람 중 번호가 가장 큰 사람
			System.out.println("#"+(tc+1)+" "+(largestNum+1)); //0~99로 저장해서 다시 +1 출력
			
		}
		

		br.close();
	}

	static int largestVisit = Integer.MIN_VALUE;
	static int largestNum = Integer.MIN_VALUE;
	
	private static void bfs(int[][] arr, int st) {
		int visited[] = new int[100];
		
		//저장할 q
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		q.offer(st);
		visited[st] = 1;
		
		while(!q.isEmpty()) {
			int current = q.poll();
//			System.out.println(current);
			for (int i = 0; i < arr.length; i++) {
				if(arr[current][i] == 1 && visited[i] == 0) { //current와 이어져 있고, 방문한적이 없는 사람이면
//					System.out.println("in if");
					visited[i] = visited[current]+1;
					q.offer(i);
					if(largestVisit == visited[i]) { //단계가 같으면
						largestVisit = visited[i];
						largestNum = Math.max(largestNum, i); //번호가 가장 큰사람
					}else if(largestVisit < visited[i]) { //이번에 방문한게 단계가 가장 크니까
						largestVisit = visited[i];
						largestNum = i; //비교 없이 바로 얘가 마지막 사람.
						
					}
				}
			}
			
		}
		
	}

}
