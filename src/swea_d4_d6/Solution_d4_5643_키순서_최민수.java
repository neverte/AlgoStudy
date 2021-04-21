package swea_d4_d6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d4_5643_키순서_최민수 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d4_5643.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			
			//a번 원소보다 작은것의 갯수 + 큰것의 갯수를 더해서 자신을 제외한 전체 숫자랑 같으면 판정할 수 있다.
			
			//그래프에서 BFS를 타면 자기보다 큰 값은 알 수 있다.
			//작은 값은 어떻게 알까 역으로 bfs를 탄다. 인접행렬을 j, i로 타자.

			//학생의 수 N 2~500
			int n = Integer.parseInt(br.readLine());
			int[][] map = new int[n][n];
			
			//키 비교 회수 M
			int m = Integer.parseInt(br.readLine());
			for (int i = 0; i < m; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int start = Integer.parseInt(st.nextToken()) - 1;
				int from = Integer.parseInt(st.nextToken()) - 1;
				map[start][from] = 1;
			}

			int answer = 0;
			for (int i = 0; i < n; i++) {
				int more = bfs(map, i);
				int less = bfsReverse(map, i);
				if(more + less == n - 1) {
					answer++;
				}
				
			}

			System.out.println("#"+(tc+1)+" "+answer);
		}//tc끝
		
		br.close();
	}

	private static int bfsReverse(int[][] map, int student) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		int n = map.length;
		int[] visited = new int[map.length];
		visited[student] = 1;
		q.offer(student);
		
		int count = 0;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for (int j = 0; j < n; j++) {
				//[j][now]로 역으로 설계
				if(map[j][now] == 1 && visited[j] == 0) {
					visited[j] = ++count;
					q.offer(j);
				}
			}
		}
		
		return count;
	}

	private static int bfs(int[][] map, int student) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		int n = map.length;
		int[] visited = new int[map.length];
		visited[student] = 1;
		q.offer(student);
		
		int count = 0;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for (int j = 0; j < n; j++) {
				if(map[now][j] == 1 && visited[j] == 0) {
					visited[j] = ++count;
					q.offer(j);
				}
			}
		}
		
		return count;
	}
}

//폐기 아이디어 1
//대우를 쓰는 문제인가?
//1번보다 5번이 키가 크다 = 5보다 1번이 키가 작다.

//폐기 아이디어 2
//입력받으면서  a < b가 들어오면
//b보다 작은 항목에 a를 추가하고, a보다 큰 항목에 b를 추가하는 것까지는 OK
//근데 a < b < c인데 a < b가 들어오면 어떻게 처리할까.
//매번 배열을 조회하면서 b가 작은게 들어가있는 항목에다가 a를 추가한다

//참고: https://data-make.tistory.com/568