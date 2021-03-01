package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[브론즈2] 방 배정
//https://www.acmicpc.net/problem/13300
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_13300_방배정_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_13300"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//N, K
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int answer = 0;
		int[][] room = new int[2][6];
		for (int i = 0; i < N; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
			int temp1 = Integer.parseInt(st2.nextToken());
			int temp2 = Integer.parseInt(st2.nextToken())-1;
			room[temp1][temp2]++;
			if(room[temp1][temp2] >= K) {
				answer++;
				room[temp1][temp2] -= K;
			}
		}
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 6; j++) {
				if(room[i][j] >= 1) answer++;
			}
			
		}
		System.out.println(answer);
		
		br.close();
	}
}
