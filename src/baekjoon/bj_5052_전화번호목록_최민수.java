package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

//[골드 4] 전화번호 목록
//https://www.acmicpc.net/problem/5052
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_5052_전화번호목록_최민수 {
	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_5052"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {

			ArrayList<Phone> list = new ArrayList<>();
			int numbers = Integer.parseInt(br.readLine());
			for (int i = 0; i < numbers; i++) {
				String stri = br.readLine();
				long numb = Long.parseLong(stri);
				list.add(new Phone(numb, stri));
			}

			Collections.sort(list);
			boolean flag = false;

			LOOP: for (int i = 0; i < numbers; i++) {
				for (int j = i + 1; j < numbers; j++) {
					Phone temp = list.get(i);
					// System.out.println(temp.str);
					// System.out.println(list.get(j).str.substring(0, temp.str.length()));
					// System.out.println("----");
					if (temp.str.equals(list.get(j).str.substring(0, temp.str.length()))) {
						bw.write("NO\n");
						flag = true;
						break LOOP;
					}

				}

			}

			if (!flag)
				bw.write("YES\n");

		}

		bw.flush();
		bw.close();
		br.close();
	}

	static class Phone implements Comparable<Phone> {
		long number;
		String str;

		public Phone(long number, String str) {
			this.number = number;
			this.str = str;
		}

		@Override
		public int compareTo(Phone o) {
			int length = Integer.compare(this.str.length(), o.str.length());
			if (length == 0)
				return Long.compare(this.number, o.number);
			else
				return length;
		}

	}
}
