package baekjoon_01001_02000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

//[실버 5] 단어 정렬
//https://www.acmicpc.net/problem/1181
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_1181_단어정렬_최민수 {
	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_1181"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		PriorityQueue<Word> pq = new PriorityQueue<>();

		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			String temp = br.readLine();
			pq.add(new Word(temp));
		}

		// 단, 같은 단어가 여러 번 입력된 경우에는 한 번씩만 출력한다.
		String prev = "";
		while (!pq.isEmpty()) {
			Word t = pq.poll();
			if (t.w.equals(prev))
				continue;

			bw.write(t.w + "\n");
			prev = t.w;
		}

		bw.flush();
		bw.close();
		br.close();
	}

	static class Word implements Comparable<Word> {
		String w;

		public Word(String w) {
			this.w = w;
		}

		@Override
		public int compareTo(Word o) {
			int length = Integer.compare(this.w.length(), o.w.length());
			if (length == 0) {
				// 사전순, 첫글자부터 비교
				for (int j = 0; j < this.w.length(); j++) {
					if (this.w.charAt(j) < o.w.charAt(j))
						return -1;
					else if (this.w.charAt(j) > o.w.charAt(j))
						return 1;
					else
						continue;
				}

			} else
				return length;
			return 0;
		}

	}
}
