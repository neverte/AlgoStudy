package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

//[실버1] Baba is Rabbit
//https://www.acmicpc.net/problem/17839
//참고: https://daebalprime.tistory.com/entry/BOJ
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_17839_BabaisRabbit_최민수 {

	static int N;

	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_17839"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		HashMap<String, LinkedList<String>> hm = new HashMap<>();
		PriorityQueue<String> pq = new PriorityQueue<>();

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String parent = st.nextToken();
			st.nextToken();
			String child = st.nextToken();

			if (!hm.containsKey(parent)) { //parent key로 시작하는게 없으면
				hm.put(parent, new LinkedList<>());
			}
			hm.get(parent).add(child);
		}

		Stack<String> stack = new Stack<>();
		stack.push("Baba");

		while (!stack.isEmpty()) {
			String now = stack.pop();
			if (!hm.containsKey(now)) {
				break;
			}
			LinkedList<String> next = hm.get(now);
			while (!next.isEmpty()) {
				String temp = next.poll();
				stack.push(temp);
				pq.add(temp);
			}
		}

		String prev = "";
		while (!pq.isEmpty()) {
			String current = pq.poll();
			if (!prev.equals(current)) {
				bw.write(current + "\n");
			}
			prev = current;
		}

		bw.flush();
		bw.close();
		br.close();
	}

}
