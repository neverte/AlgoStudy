package programmers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//[lv 3] 불량 사용자
//https://programmers.co.kr/learn/courses/30/lessons/64064
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class pro_64064_불량사용자_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_0000"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		br.close();
	}
}

//(1글자짜리 * 그 경우의 수) = A1이라고 하면
//경우의 수 =  A1 * A2 * ...?
//예제 3이 틀렸다.

//아이디어 2
//각각의 banned id에 대해 가능한 조합을 다 파악한 후
//각각의 id가 조합을 뽑으면서 개수를 세아린다.
//즉 실제로 조합을 뽑아본다.
