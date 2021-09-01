package swea_etc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_모의_2115_벌꿀채취_최민수 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_모의_2115.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//테케, T
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {

			//변수가 많다. 직접 계산해보는게 제일일듯.
			//모든 범위에 대해 최적해를 구한다음.
			//1, 2등을 뽑는데, 2등이랑 1등 겹치면 그 차선을 찾는 방식으로.
			
			//정리하면
			//1. A를 시작으로하는 부분해를 구해서 저장한다.
			//2. 1의 결과를 가격순으로 정렬
			//3. 1, 2등 선택
			//4. 2등이 1과 안겹칠때까지 등수++
			
			//벌통의 크기 N 3~10
			//선택할 수 있는 벌통의 개수 M
			//꿀을 채취할 수 있는 최대 양 C
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());					
				}
			}
			
			PriorityQueue<price> pq = new PriorityQueue<>();
			
			//1. A를 시작으로하는 부분해를 구해서 저장한다.
			//n이 4고, m가 2면 한줄에 나오는 부분해는 4-2+1
			// 5고 m가 1이면 5개, m
			for (int i = 0; i < n; i++) {
				for (int j = 0; j <= n-m; j++) {
					//2. 1의 결과를 가격순으로 정렬 -> pq로 선택했음.
					int price = getPrice(i, j, map, m, c);
					if(price > 0) pq.offer(new price(i, j, price));
				}
			}
			
			//3. 1, 2등 선택
			price first = pq.poll();
			price second;
			while(true) {
				second = pq.poll();
				
				//4. 2등이 1과 안겹칠때까지 등수++
				if(first.x != second.x) break;
				else {
					//더 큰면서 안겹치면
					if(first.y > second.y && first.y - c >= second.y) break;
					else if(first.y < second.y && first.y <= second.y - c) break;
				}
				//이 외에는 겹친다. while로 새로 뽑자.
			}
			
			System.out.println("#"+(tc+1)+" "+(first.price + second.price));
		}
		br.close();
	}
	
	static int[] square = {0, 1, 4, 9, 16, 25, 36, 49, 64, 81};
	static int subMax;
	
	private static int getPrice(int x, int y, int[][] map, int m, int c) {
		//최대 m 칸
		//한번에 최대 용량 c
		
		//주어진 m칸 중에 c을 넘지않으면서 최대를 선택하면 됨.
		int sum = 0;
		for (int i = 0; i < m; i++) {
			sum += map[x][y+i];
		}
		
		//1. 합계가 c이하면 다 더하면 된다.
		if(sum <= c) {
			//매번 getPrice할 때마다 전역변수 리셋
			subMax = 0;
			for (int j = 0; j < m; j++) {
				subMax += square[map[x][y+j]];
			}
		}else {
			//2. 합계가 c를 초과하면 최고만 골라 넣어야 한다.
			//예를들어 8인데 5 4 4가 있다고 하면 greedy는 안먹힌다.
			//부분집합 중 최고를 선별
			subMax = Integer.MIN_VALUE;
			generateSubset(0, m, map, new boolean[m], x, y, c);
		}
		
		return subMax;
	}

	//최대 m개 선택
	//0에서 1개 선택하면 1됨
	private static void generateSubset(int cnt, int total, int[][] map, boolean[] visited, int x, int y, int c) {
		if(cnt == total) {
			//계산
			int sum = 0;
			int csum = 0;
			for (int i = 0; i < total; i++) {
				if(visited[i]) {
					sum += square[map[x][y+i]];
					//합계가 c를 초과하는지 확인해야 함.
					csum += map[x][y+i];
					if(csum > c) return;
				}
			}
			
			subMax = Math.max(subMax, sum);
			return;
		}
		
		visited[cnt] = true;
		generateSubset(cnt+1, total, map, visited, x, y, c);
		
		visited[cnt] = false;
		generateSubset(cnt+1, total, map, visited, x, y, c);
		
	}

	static class price implements Comparable<price>{
		int x, y, price;

		public price(int x, int y, int price) {
			super();
			this.x = x;
			this.y = y;
			this.price = price;
		}

		@Override
		public int compareTo(price o) {
			return -Integer.compare(this.price, o.price);
		}
		
	}

}
//폐기 아이디어 1: 뭔가 묘-수를 찾아보자
//벌꿀은 한 칸에 1~9
//즉 C가 최대 30일때
//9 9 9 3이랑 // 252
//6 6 6 6 6을 비교해보자ㅣ216
//C가 10일때
//9 1 vs 5 5 는 9 1이 크다.
//6 4 vs 5 5도 6 4가 크다.
//6 3 vs 5 5 