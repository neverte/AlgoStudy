package baekjoon_01001_02000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[실버 1] Z
//https://www.acmicpc.net/problem/1074
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_1074_Z_최민수 {
	static int r, c;
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_1074"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//입력 N, r, c
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		//(r, c)를 몇번쨰로 방문하는지 출력하라.
		int length = (int) Math.pow(2, N);
		
		//r, c가 1, 2, 3, 4분면 중에 어디에 위치한지 파악
		//앞 부분 사분면만큼 숫자 추가 반복
		//크기가 2^30짜리 10억. int안
		int answer = 0;
		int x=0, y=0;
		while(true) {
			//한덩어리는 전체 크기의 1/4
			length /= 2;
			int smallSize = length*length;
			if(r < x + length && c < y + length) { //1
				answer += smallSize*0;
			}else if(r < x + length && c >= y + length) { //2
				y += length;
				answer += smallSize*1;
			}else if(r >= x + length && c < y + length) { //3
				x += length;
				answer += smallSize*2;
			}else { //4
				x += length;
				y += length;
				answer += smallSize*3;
			}
			if(length == 1) {
				System.out.println(answer);
				break;
			}
		}
		
		br.close();
	}
//	static int cnt = 0;
//	public static void divide(int[][] arr, int len, int x, int y) {
//		//2*2가될때까지 나눠
//		int half = len/2; //길이가 4면 half는 2
//		
//		if(x == r && y == c) {
//			System.out.println(cnt);
//			System.exit(0);
//			return;
//		}
//		if(len == 1) {
////			System.out.println(x+", "+y);
//			cnt++;
//			return;
//		}
//		
//		divide(arr, half, x, y);
//		divide(arr, half, x, y + half);
//		divide(arr, half, x + half, y);
//		divide(arr, half, x+half, y+half);
//	}
}
