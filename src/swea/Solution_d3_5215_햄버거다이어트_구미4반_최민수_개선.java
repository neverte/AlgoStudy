package swea;

import java.awt.geom.Area;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

// 패키지 날려야되나
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d3_5215_햄버거다이어트_구미4반_최민수_개선 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d3_5215.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//테케
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			//재료의 수 N(1~20), 제한 칼로리 L(1~1만)
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			
			//재료에 대한 재료번호, 맛, 칼로리
			int[][] al = new int[N][2];
			for (int i = 0; i < N; i++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
				//재료번호, 맛 점수, 칼로리
				al[i][0] = Integer.parseInt(st2.nextToken());
				al[i][1] = Integer.parseInt(st2.nextToken());
			}
			//최고의 점수 버거 구성하기
			//아이디어 2. 
			//int[][] map // map[i][j] = 총 점수
			//0~i번쨰 원소를 선택했다.
			//
			
			
			//각 줄마다 "#T" (T는 테스트 케이스 번호)를 출력한 뒤,
			//주어진 제한 칼로리 이하의 조합중에서
			//가장 맛에 대한 점수가 높은 햄버거의 점수를 출력한다.
			System.out.println("#"+(t+1)+" ");

		}

		br.close();
	}

}
