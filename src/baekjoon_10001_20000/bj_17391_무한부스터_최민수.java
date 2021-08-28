package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//[실버 1] 무한부스터
//https://www.acmicpc.net/problem/17391
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_17391_무한부스터_최민수 {
	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_17391"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		bw.flush();
		bw.close();
		br.close();
	}
}
