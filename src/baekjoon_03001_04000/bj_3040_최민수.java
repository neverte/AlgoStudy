package baekjoon_03001_04000;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//[브론즈2] 백설 공주와 일곱 난쟁이
//https://www.acmicpc.net/problem/3040
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_3040_최민수 {
	public static void main(String[] args) throws Exception {
		// 테스트 입력
		// System.setIn(new FileInputStream("res/baekjoon/bj_input_3040"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 아이디어
		// 입력받으면서 totalSum 구한다음에
		// 이중 for문으로 i, j번쨰 원소를 동시에 뺐을 때 100이 나오면 컷

		int[] dwarf = new int[9];
		int totalSum = 0;
		// 9 난쟁이 입력
		for (int i = 0; i < 9; i++) {
			dwarf[i] = Integer.parseInt(br.readLine());
			totalSum += dwarf[i];
		}
		int i = 0, j = 0;
		Loop: for (i = 0; i < 9; i++) {
			for (j = i + 1; j < 9; j++) {
				if (totalSum - dwarf[i] - dwarf[j] == 100)
					break Loop;
			}
		}
		for (int k = 0; k < 9; k++) {
			if (k == i || k == j)
				continue;
			System.out.println(dwarf[k]);
		}

		br.close();
	}

}