package baekjoon_01001_02000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//[골드 5] 암호 만들기
//https://www.acmicpc.net/problem/1759
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_1759_암호만들기_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_1759"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//서로 다른 L개의 알파벳 소문자들로 구성되며
		//최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음으로 구성
		// 암호를 이루는 알파벳이 암호에서 증가하는 순서로 배열
		//문자의 종류는 C가지
		
		//3~L~C~15, 소문자, 중복없음
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int l = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		String[] s = new String[c];
		for (int i = 0; i < s.length; i++) {
			s[i] = st.nextToken();
		}
		Arrays.sort(s);
		
		//<아이디어> : 2로 구현했음.
		//1. 최소 1개의 모음이 들어가야 한다.
		//모음 1개, 자음 2개를 뽑고 나머지에서 조합하는 식으로 한다음
		//결과들을 모아서 사전순으로 출력
		
		//2. 그냥 l길이만큼 다 뽑은 다음에 조건 쳐내기
		//뽑는 과정에서 조건 쳐내기?
		// 모음 아직 0개 뽑았는데 이제 뽑을 자리에서 모음이 없는 경우
		// 자음 0개 뽑았는데 이제 뽑을 자리에서 1개 이하로밖에 없는 경우
		//자음 1개 뽑았는데 이제 뽑을 자리에서 0개 박에 없는 경우
		//=>이걸 하려면 각 자릿수에서 남은 자음, 모음개수를 저장하는 배열이 한개 필요하다.
		
		//남은 자음, 모음 저장할 배열
		int[][] consonantVowelCheck = new int[c][2];
		//[i][0]: i번째 자리에서 남아있는 자음 개수
		//[i][1]: i번째 자리에서 남아있는 모음 개수
		for (int i = c-1; i >= 0; i--) {
			if(s[i].equals("a") || s[i].equals("e") ||s[i].equals("i") ||s[i].equals("o") || s[i].equals("u")) {
				consonantVowelCheck[i][1]++;
			}else {
				consonantVowelCheck[i][0]++;
			}
			if(i > 0) {
				consonantVowelCheck[i-1][0] = consonantVowelCheck[i][0];
				consonantVowelCheck[i-1][1] = consonantVowelCheck[i][1];
			}
		}
		
		//중간 확인용
//		System.out.println(Arrays.toString(s));
//		for(int[] kk : consonantVowelCheck)System.out.println(Arrays.toString(kk));
		
		//조합 돌리기
		//길이가 l인 출력할 배열
		String answer[] = new String[l];
		int count = 0, start = 0;
		makeComb(s, consonantVowelCheck, answer, start, count, l, 0, 0);

		br.close();
	}

	private static void makeComb(String[] s, int[][] CVC, String[] answer, int start, int count, int limit, int consonant, int vowel) {

		if(count == limit) { //종료조건 다 뽑았따.
			//bi를 뽑았고 남은게 os면, 자음 뽑은게 1개이고, 남은 자음이 1개인데, o를 뽑으면 끝난다.
			//따라서 귀찮지만 체크 한번더 해주자
			if(consonant < 2 || vowel < 1) return;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < answer.length; i++) {
				sb.append(answer[i]);
			}
			System.out.println(sb);
			return;
		}
		
		//가지치기 해서 통과하면 출력
		//limit - count: 남은 자리
		//뽑아야 할 자리보다 남은 자리가 적으면 돌아가라
		if(limit-count > s.length - start) return;
		//앞으로 남은 모음 개수 0개인데, 모음 뽑은게 0개인 경우
		if(CVC[count][1] == 0 && vowel == 0)return;
		//앞으로 남은 자음 개수 1개 이하인데, 자음 뽑은게 0개인 경우
		if(CVC[count][0] <= 1 && consonant == 0)return;
		//앞으로 남은 자음 개수 0개 이하인데, 자음 뽑은게 1개인 경우
		if(CVC[count][0] == 0 && consonant == 1)return;

		
		for (int i = start; i < s.length; i++) {
			answer[count] = s[i];
			//자, 모음 갯수 트랙킹
			if(s[i].equals("a") || s[i].equals("e") ||s[i].equals("i") ||s[i].equals("o") || s[i].equals("u")) {
				//재귀
				makeComb(s, CVC, answer, i+1, count+1, limit, consonant, vowel+1);
			}else{
				//재귀
				makeComb(s, CVC, answer, i+1, count+1, limit, consonant+1, vowel);
				
			}
		}
		
	}
}
