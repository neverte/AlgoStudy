package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//[브론즈1] 색종이
//https://www.acmicpc.net/problem/10163
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_10163_색종이_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_10163"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//장수 N 1~100
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[101][101];
		int[] answer = new int[N+1];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int lx = Integer.parseInt(st.nextToken());
			int ly = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int startX = lx;
			int startY = ly;
			int endX = lx+w;
			int endY = ly+h;
			for (int j = startX; j < endX; j++) {
				for (int j2 = startY; j2 < endY; j2++) {
					//(i+1)번째 색종이라고 색칠
					if(map[j][j2] == 0) {
						map[j][j2] = i+1;
						answer[i+1]++;
					}else if(map[j][j2] != 0) {
						answer[map[j][j2]]--;
						map[j][j2] = i+1;
						answer[i+1]++;
					}
				}
			}
		}
//		for(int[] in : map) System.out.println(Arrays.toString(in));
		
		for (int i = 1; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
		
		br.close();
	}
}
