package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

// 패키지 날려야되나
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d4_1223_계산기2_구미4반_최민수 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d4_1223.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//1. 스택에 차례대로 넣는데 곱하기면 pop해서 계산해서 넣고
		//2. 마지막에 +만 좌르륵 하면 되는거 아냐?
		
		ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
		
		
		//10개의 테케
		for (int i = 0; i < 10; i++) {
			//테케의 길이
			int tcLeng = Integer.parseInt(br.readLine());
			String[] temp = br.readLine().split("");
			for (int j = 0; j < tcLeng; j++) {
				if(temp[j].equals("+")) { //01. 더하기거나
					
				}else if(temp[j].equals("*")) { //02. 곱하기거나
					//* 앞의 숫자와 // *뒤의 숫자를 가져오자. 
					//stack이니까 pop이 아니고 pollLast로 접근해야함.
					stack.add(stack.pollLast() * Integer.parseInt(temp[++j]));
					//뒷숫자를 빼왔으니 그다음접근하면 문자다.
				}else { //03. 숫자다
					stack.add(Integer.parseInt(temp[j]));
				}
			}
//			System.out.println(stack.toString());
			
			int answer = 0;
			while(!(stack.isEmpty())) answer += stack.pop();
			
			System.out.println("#"+(i+1)+" "+answer);
		}
		br.close();
	}

}
