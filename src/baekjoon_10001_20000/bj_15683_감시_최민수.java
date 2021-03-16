package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//[골드 5] 감시
//https://www.acmicpc.net/problem/15683
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_15683_감시_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_15683"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//아이디어
		//1. 각 cctv별 모든 방향 다 돌려서 최소값 찾기
		//넓이가 0되면 바로 끝(가지치기)
		
		br.close();
	}
}
