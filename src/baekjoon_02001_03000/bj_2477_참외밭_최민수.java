package baekjoon_02001_03000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

//[실버 5] 참외밭
//https://www.acmicpc.net/problem/2477
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_2477_참외밭_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_2477"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//K 1~20
		int K = Integer.parseInt(br.readLine());
		
		int check[] = new int[4];
		int val[][] = new int[6][2]; 
		ArrayDeque<Integer> qVal = new ArrayDeque<Integer>();
		ArrayDeque<Integer> qDir = new ArrayDeque<Integer>();
		for (int i = 0; i < 6; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int direction = Integer.parseInt(st.nextToken())-1;
			int meter = Integer.parseInt(st.nextToken());
			val[i][0] = direction;
			check[direction]++;
			val[i][1] = meter; 
		}
		for (int i = 0; i < val.length; i++) {
			//val[i][0] = 방향값
			//check[val[i][0]] = 방향값의 개수
			qDir.offer(check[val[i][0]]);
			qVal.offer(val[i][1]);
		}
		while(qDir.peekFirst() != 1 || qDir.peekLast() != 2) {
			qDir.offer(qDir.pop());
			qVal.offer(qVal.pop());
		}
		
		
		//나온 방향 개수가 112222가 되도록 정렬하면
		// [3][4]를 곱하면 된다.
		
		int largeBox = qVal.pop() * qVal.pop();
		qVal.pop();
		int smallBox = qVal.pop() * qVal.pop();
		System.out.println((largeBox - smallBox)*K);
		
		
		br.close();
	}
}
