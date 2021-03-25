package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

//[실버 1] 맥주 마시면서 걸어가기
//https://www.acmicpc.net/problem/9205
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_9205_맥주마시면서걸어가기_최민수 {
	static class position{
		int x, y, number;

		public position(int x, int y, int number) {
			super();
			this.x = x;
			this.y = y;
			this.number = number;
		}
	}
	
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_9205"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//맥주 20개, 50미터에 한병씩
		//맥주 추가구매 필요, 최대 20병까지
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			//전역변수 초기화
			arrive = false;
			
			//편의점의 개수 n: 0~100
			int n = Integer.parseInt(br.readLine());
			//상근이네 집 -> 편의점 -> 락 페스티벌 좌표
			int pos[][] = new int[n+2][2];
			for (int i = 0; i < n+2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				pos[i][0] = Integer.parseInt(st.nextToken());
				pos[i][1] = Integer.parseInt(st.nextToken());
			}
			
			//집에서 편의점 방문하는 것을 DFS로?
			boolean visited[] = new boolean[n+2];
			bfs(pos, visited);

			if(arrive) System.out.println("happy");
			else System.out.println("sad");
		}//tc끝
	
		br.close();
	}

	static boolean arrive;
	
	private static void bfs(int[][] pos, boolean[] v) {
		ArrayDeque<position> q = new ArrayDeque<position>();
		q.offer(new position(pos[0][0], pos[0][1], 0));
		
		while(!q.isEmpty()) {
			position now = q.poll();
			v[now.number] = true;
			
			if(now.number == v.length - 1) {
				arrive = true;
				return;
			}
			
			for (int i = v.length - 1; i > 0; i--) { //마지막부터 체크
				if(v[i]) continue;
				//거리가 되는지 체크
				if(!canMove(pos, now.number, i)) continue;
				q.offer(new position(pos[i][0], pos[i][0], i));
			}
	
		}

	}

	private static boolean canMove(int[][] pos, int start, int end) {
		int x = Math.abs(pos[start][0] - pos[end][0]);
		int y = Math.abs(pos[start][1] - pos[end][1]);
		if(1000 >= x+y) return true; //총 이동거리가 1000보다 작거나 같으면 이동가능하다.
		else return false;
		
	}
}

//1. 시간초과: DFS->BFS
