package baekjoon_01001_02000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

//[실버 5] 요세푸스 문제
//https://www.acmicpc.net/problem/1158
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_1158_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_1158"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		//아이디어
		//큐를 만들어서 2명은 빼고 다시 넣고 pop, offer
		//1명은 아예 뺴고 다시 안넣고 출력 pop
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		//최대 정수 N명의 사람
		int N = Integer.parseInt(st.nextToken());
		//K씩 건너뜀
		int K = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			queue.offer(i+1); //0~6말고 1~7넣기
		}
		
		
		sb.append("<");
		while(!(queue.isEmpty())) {
			for (int i = 0; i < K-1; i++) {
				queue.offer(queue.pop());
			}
			sb.append(queue.pop()).append(", ");
		}
		sb.setLength(sb.length() - 2);
		sb.append(">");
		//출력
		//<3, 6, 2, 7, 5, 1, 4>
		System.out.println(sb);
		
		
		br.close();
	}
}
