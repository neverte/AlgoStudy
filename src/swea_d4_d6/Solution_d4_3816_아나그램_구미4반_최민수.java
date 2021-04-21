package swea_d4_d6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

// 패키지 날려야되나
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d4_3816_아나그램_구미4반_최민수 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d4_3816.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String s1 = st.nextToken();
			String s2 = st.nextToken();
			
			//알파벳 개수를 세아릴 배열
			int[][] alpha = new int[26][2]; //[0]에는 s1, [1]에는 s2의 부분
			
			//s1의 알파벳 구성을 분석해야 한다.
			char[] split1 = s1.toCharArray();
			int s1len = split1.length;
			for (int i = 0; i < s1len; i++) {
				//97이 소문자 a
				alpha[(int)split1[i] - 97][0]++;
			}
			
			//s2를 이제 접근할 것이다.
			//일단 s1 길이만큼 접근
			char[] split2 = s2.toCharArray();
			for (int i = 0; i < s1len; i++) {
				alpha[(int)split2[i] - 97][1]++;
			}
			
			int answer = 0;
			if(isAnagram(alpha)) answer++;
			
			for (int i = s1len; i < split2.length; i++) {
				//맨 앞의 문자를 빼고, 새로 추가된 문자는 추가함
				alpha[(int)split2[i] - 97][1]++;
				alpha[(int)split2[i-s1len] - 97][1]--;
				
				if(isAnagram(alpha)) answer++;
			}
			
			System.out.println("#"+(tc+1)+" "+answer);
			
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
