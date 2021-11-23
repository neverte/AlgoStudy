package baekjoon_02001_03000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//[실버 1] 기타 레슨
//https://www.acmicpc.net/problem/2343
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
//참고: https://withthemilkyway.tistory.com/28
public class bj_2343_기타레슨_최민수 {

	static int N, M;
	static int[][] lesson; //[0]에는 일반 값 , [1]에는 누적값
	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_2343"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lesson = new int[N][2];
		
		int low = 0;
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < lesson.length; i++) {
			lesson[i][0] = Integer.parseInt(st.nextToken());
			low = Math.max(low, lesson[i][0]);
			if (i == 0) {
				lesson[i][1] = lesson[i][0];
			} else {
				lesson[i][1] = lesson[i - 1][1] + lesson[i][0];
			}
		}
		
		// 이분 탐색
		int high = lesson[N-1][1]; //최대 크기
		int mid = (high + low) / 2 + 1; 

		while (low <= high) {
			mid = (high + low) >> 1;
			int sum = 0;
			int count = 0;

			for (int i = 0; i < N; i++) {
				if (sum + lesson[i][0] > mid) {
					sum = 0;
					count++;
				}
				sum += lesson[i][0];
			}

			// 남은 것 처리
			if (sum > 0) {
				count++;
			} 
			
			if (count <= M) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}

		}
		bw.write(low + "");

		bw.flush();
		bw.close();
		br.close();
	}
}
