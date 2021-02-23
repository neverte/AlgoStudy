package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

//패키지 날려야되나
//class Solution으로 바꾸기
//내부 입력 주석처리하기
public class Solution_d4_1218_괄호짝짓기_구미4반_최민수 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d4_1218.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Stack<String> st = new Stack<String>();
		//10개의 테케
		for (int i = 0; i < 10; i++) {
			//스택초기화
			st.clear();
			int number = Integer.parseInt(br.readLine());
			String[] arr = br.readLine().split("");
			for (int j = 0; j < number; j++) {
				//1. ([{< 이쪽 계열이면 넣는다.
				if(arr[j].equals("(") || arr[j].equals("{") || arr[j].equals("[") || arr[j].equals("<")) st.push(arr[j]);
				//2. )]}>이쪽이면 맨위랑 짝 맞는지 확인하고 맞으면 같이제거
				else if(arr[j].equals(")")) {
					if(st.peek().equals("(")) st.pop();
					else break;
				}else if(arr[j].equals("}")) {
					if(st.peek().equals("{")) st.pop();
					else break;
				}else if(arr[j].equals("]")) {
					if(st.peek().equals("[")) st.pop();
					else break;
				}else if(arr[j].equals(">")) {
					if(st.peek().equals("<")) st.pop();
					else break;
				}
			}
			//마지막에 잔여물 남았으면 실패
			if(st.isEmpty()) System.out.println("#"+(i+1)+" "+1);//1
			else System.out.println("#"+(i+1)+" "+0);
		}
		
		//#부호와 함께 테스트 케이스의 번호를 출력하고,
		//공백 문자 후 유효성 여부를 1 또는 0으로 표시한다
		//(1 - 유효함, 0 - 유효하지 않음).
		
		
		br.close();
	}

}
