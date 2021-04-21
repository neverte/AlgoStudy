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
public class Solution_d4_8382_방향전환_최민수 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d4_8382.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			//x1, y1을 원점으로 삼을 것이다.
			int x = (x2-x1);
			int y = (y2-y1);
			int t = (x+y) / 2;

			//0 0 0 4 같은 경우만 처리할 줄알면 다 처리되는 듯.
			//T = X+Y/2
			//Math.abs(X-T)+Math.abs(Y-T)
//			System.out.println(x+", "+y+", "+t);
			System.out.println("#"+(tc+1)+" "+(Math.abs(x-t) + Math.abs(y-t) + Math.abs(t * 2)));
		}//tc끝
		
		br.close();
	}
}