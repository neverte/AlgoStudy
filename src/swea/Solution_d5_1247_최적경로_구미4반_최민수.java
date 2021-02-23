package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d5_1247_최적경로_구미4반_최민수 {
	
	static int[][] customer;
	static int N, companyX, companyY, homeX, homeY;
	
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d5_1247.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//회시, 집, 고객의 위치는 {x, y} 0~100

		//회사의 좌표, 집의 좌표, 고객들의 좌표는 모두 다르다.
		//회사에서 출발하여 N명의 고객을 모두 방문하고
		//집으로 돌아오는 경로 중 가장 짧은 것
		//고객의 수 N은 2≤N≤10 이다.
		
		//테케 T
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			//고객의 수 N
			N = Integer.parseInt(br.readLine());
			customer = new int[N][2];
			//회사의 좌표,집의 좌표, N명의 고객의 좌표가 차례로 나열된다.
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			companyX = Integer.parseInt(st.nextToken());
			companyY = Integer.parseInt(st.nextToken());
			homeX = Integer.parseInt(st.nextToken());
			homeY = Integer.parseInt(st.nextToken());
			for (int j = 0; j < N; j++) {
				customer[j][0] = Integer.parseInt(st.nextToken());
				customer[j][1] = Integer.parseInt(st.nextToken());	
			}
			
			//회사 -> 고객 N명 -> 집 방문하기
			//20초 주니까 널널하다.
			//DFS로 구하자.
			vis = new boolean[N];
			//초기화
			min = Integer.MAX_VALUE;
			num = new int[N];
			perm(0, 0, 0);
			System.out.println("#"+(i+1)+" "+min);
		}

		br.close();
	}
	
	static boolean vis[];
	static int min;
	static int num[];
	//last: 직전에 만난 고객
	public static void perm(int last, int count, int distance) {
//		System.out.println(count); //명 만났다.
		//고객 숫자만큼
		//N명의 고객을 다 만났다면?		
		if(N == count) {
			//거리 더하기
			distance =0;
			distance += calcDist(-1, num[0]);
			
			for (int i = 1; i < N; i++) {
				distance += calcDist(num[i-1], num[i]);
			}
			distance += calcDist(num[N-1], -1);
			min = Math.min(distance, min);
			return;
		}
		for (int i = 0; i < N ; i++) {			
			if(vis[i]) continue;
			num[count] = i;
			vis[i] = true;
			perm(i, count+1, distance);
			vis[i] = false;
		}
	}
	
	public static int calcDist(int last, int now) {
		//이전 좌표랑, 이후 좌표가 있어야 한다.
		
		//회사에서 출발한 경우
		if(last == -1) return Math.abs(companyX - customer[now][0]) + Math.abs(companyY - customer[now][1]);
		
		//집에 도착하는 경우는 따로 체크하기
		if(now == -1) return Math.abs(homeX - customer[last][0]) + Math.abs(homeY - customer[last][1]);
		
		//두 위치 (x1, y1)와 (x2, y2) 사이의 거리는 |x1-x2| + |y1-y2|
		return Math.abs(customer[last][0] - customer[now][0]) + Math.abs(customer[last][1] - customer[now][1]);
	}

}
