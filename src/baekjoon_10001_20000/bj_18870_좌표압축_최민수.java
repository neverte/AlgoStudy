package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//[실버 2] 좌표 압축
//https://www.acmicpc.net/problem/0000
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_18870_좌표압축_최민수 {
	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_18870"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		String original = br.readLine();

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		StringTokenizer st = new StringTokenizer(original, " ");

		// pq에 수를 넣으면 순서대로 정렬이 된다.
		for (int i = 0; i < n; i++) {
			pq.add(Integer.parseInt(st.nextToken()));
		}

		Map<Integer, Integer> rank = new HashMap<>();
		int prev = Integer.MIN_VALUE;
		int ranking = 0;
		// pq에서 수를 꺼내면서 순서대로 등수를 map에 저장한다.
		while (!pq.isEmpty()) {
			int num = pq.poll();
			if (num == prev)
				continue;
			rank.put(num, ranking++);
			prev = num;
		}

		st = new StringTokenizer(original, " ");
		// 원본을 보면서 각 숫자가 몇등에 매칭되는지 map으로 매칭한다.
		for (int i = 0; i < n; i++) {
			int temp = Integer.parseInt(st.nextToken());
			bw.write(rank.get(temp) + " ");
		}

		bw.flush();
		bw.close();
		br.close();
	}
}
