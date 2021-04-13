package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[브론즈 1] 애너그램
//https://www.acmicpc.net/problem/6996
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_6996_애너그램_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_6996"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String s1 = st.nextToken();
			String s2 = st.nextToken();
			
			//알파벳 개수를 세아릴 배열
			int[][] alpha = new int[26][2]; //[0]에는 s1, [1]에는 s2
			
			//s1의 알파벳 구성을 분석해야 한다.
			char[] split1 = s1.toCharArray();
			int s1len = split1.length;
			for (int i = 0; i < s1len; i++) {
				//97이 소문자 a
				alpha[(int)split1[i] - 97][0]++;
			}
			
			//s2를 이제 접근할 것이다.
			char[] split2 = s2.toCharArray();
			for (int i = 0; i < split2.length; i++) {
				alpha[(int)split2[i] - 97][1]++;
			}
			if(isAnagram(alpha)) System.out.println(s1+" & "+s2+" are anagrams.");
			else System.out.println(s1+" & "+s2+" are NOT anagrams.");
			
		}//tc끝
		
		br.close();
	}

	private static boolean isAnagram(int[][] alpha) {
		for (int i = 0; i < alpha.length; i++) {
			if(alpha[i][0] != alpha[i][1]) return false;
		}
		
		return true;
	}
}
