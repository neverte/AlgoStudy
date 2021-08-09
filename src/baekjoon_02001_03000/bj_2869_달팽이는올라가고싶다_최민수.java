package baekjoon_02001_03000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[브론즈 1] 달팽이는 올라가고 싶다
//https://www.acmicpc.net/problem/2869
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_2869_달팽이는올라가고싶다_최민수 {
	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_2869"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 낮에 A 미터 올라감, 1~10억. int 범위 내
		// 밤에 B 미터 미끄러짐, 1~10억. int 범위 내
		// 목표 높이 V 미터, 1~10억. int 범위 내
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());

		// 하루에 a-b만큼 올라갈 수 있다.
		// 단 마지막날은 순수 a만큼 올라갈 수 있다.
		// 즉 전날에 v-a에 도달하면 +1하면 가능하다.
		int yesterday = v - a;

		// 3 / 2 = 1
		// 3 / 3 = 1

		// 1 / 2 = 0 + 1
		int yesterdayCount = (yesterday > 0) ? (yesterday - 1) / (a - b) + 1 : (yesterday - 1) / (a - b);
		System.out.println(yesterdayCount + 1);

		br.close();
	}
}
