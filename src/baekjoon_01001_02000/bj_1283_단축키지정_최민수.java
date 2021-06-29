package baekjoon_01001_02000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//[실버3] 단축키 지정
//https://www.acmicpc.net/problem/1283
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_1283_단축키지정_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_1283"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//옵션의 개수 N 1~30
		int N = Integer.parseInt(br.readLine());
		
		//단축키 사용된 배열
		boolean hotKey[] = new boolean[26];
		StringBuilder answer = new StringBuilder();
		
		//한 줄씩 처리
		for (int i = 0; i < N; i++) {
			String option = br.readLine();
			char[] temp = option.toCharArray();
			
			//hotkey를 등록했는지 확인할 수 있는 flag
			boolean findHotkey = false;
			
			//단어별로 조회, 첫 글자가 이미 단축키인지 확인
			//단어를 만났는지 알 수 있는 flag
			boolean inMidWord = true;
			for (int j = 0; j < temp.length; j++) {
				if(inMidWord) {
					inMidWord = false;
					
					//지금 글자가 이미 사용되었는지 확인
					//대소문자 구분해야함.
					if((int)temp[j] > 96) { //소문자
						//이미 사용되었으면 스킵
						if(hotKey[temp[j] - 97]) continue;
						else {
							hotKey[temp[j] - 97] = true;
							makeAnswer(answer, option, j);
							findHotkey = true;
							break;
						}
					}else { //대문자
						//이미 사용되었으면 스킵
						if(hotKey[temp[j] - 65]) continue;
						else {
							hotKey[temp[j] - 65] = true;
							makeAnswer(answer, option, j);
							findHotkey = true;
							break;
						}
					}
					
				}else {
					//만난게 공백이면
					if(temp[j] == ' ') {
//						System.out.println("공백 만났다!!!");
						inMidWord = true;
					}
					continue;
				}
			}
			
			//단어별로 다 조회했는데도 못찾았다. 그러면 처음부터 조회해야함.
			if(!findHotkey) {
				for (int j = 0; j < temp.length; j++) {
					if(temp[j] == ' ') continue;
					if((int)temp[j] > 96) { //소문자
						//이미 사용되었으면 스킵
						if(hotKey[temp[j] - 97]) continue;
						else {
							hotKey[temp[j] - 97] = true;
							makeAnswer(answer, option, j);
							findHotkey = true;
							break;
						}
					}else { //대문자
						//이미 사용되었으면 스킵
						if(hotKey[temp[j] - 65]) continue;
						else {
							hotKey[temp[j] - 65] = true;
							makeAnswer(answer, option, j);
							findHotkey = true;
							break;
						}
					}
				}
			}
			if(!findHotkey) { //그래도 못찾았다.
				answer.append(option).append("\n");
				
			}
		}
	
//		System.out.println((int)'A');
//		System.out.println((int)'a');
		System.out.println(answer.toString());
		br.close();
	}

	private static void makeAnswer(StringBuilder answer, String option, int j) {
		//smiles (1,5) = mile m = 1번째, e는 4번째
		answer.append(option.substring(0, j))
		      .append("[").append(option.charAt(j)).append("]")
		      .append(option.substring(j+1)).append("\n");
	}
}
