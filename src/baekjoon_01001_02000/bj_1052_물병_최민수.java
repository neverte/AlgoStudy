package baekjoon_01001_02000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//[실버 1] 물병
//https://www.acmicpc.net/problem/1052
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_1052_물병_최민수 {
	static int[] power = new int[25]; //2의 거듭제곱 값을 저장할 배열
	static int N, K;
	public static void main(String[] args) throws Exception {

		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_1052"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int val = 1;
		power[0] = 1;
		for (int i = 1; i < power.length; i++) {
			val *= 2;
			power[i] = val;
		}

		int point = 0;
		while (true) {
			if (N == 0) {
				bw.write(0 + "");
				break;
			}
			if (N >= power[point] && N < power[point + 1]) {
				if (K == 1) {
					if (N == power[point]) {
						bw.write(0  + "");
						break;
					} else {
						
						bw.write(power[point + 1] - N  + "");
						break;
					}
				} else {
					N -= power[point];
					K--;
					point = 0;
				}
			} else {
				point++;
			}
		}

		bw.flush();
		bw.close();
		br.close();
	}
}
