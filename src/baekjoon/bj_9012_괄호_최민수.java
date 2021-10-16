package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//[실버4] 괄호
//https://www.acmicpc.net/problem/9012
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_9012_괄호_최민수 {

	static int T;
	static char arr[];
	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_9012"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());
		Loop: for (int tc = 0; tc < T; tc++) {
			String[] temp = br.readLine().split("");
			arr = new char[50];
			int point = 0;

			for (int i = 0; i < temp.length; i++) {
				if (temp[i].equals("(")) { // (
					arr[point] = '(';
					point++;
				} else { // )
					if (point == 0) {
						bw.write("NO\n");
						continue Loop;
					} else if (arr[point - 1] == '(') {
						arr[point - 1] = ' ';
						point--;
					}
				}
			}
			
			if (point != 0) {
				bw.write("NO\n");
			} else {
				bw.write("YES\n");
			}
			
		}

		bw.flush();
		bw.close();
		br.close();
	}
}
