package swea_d4_d6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d6_3950_괄호_최민수 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d6_3950.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			
			//길이 L 1~1000
			int l = Integer.parseInt(br.readLine());
			//문자열 s
			char[] s = br.readLine().toCharArray();
			
			//홀수면 -1 출력하고 다음 tc로
			if(l % 2 == 1) {
				System.out.println("#"+(tc+1)+" "+-1);
				break;
			}
			
			//1. 쉬운 괄호 문제로 변환
			//스택을 활용하여 괄호 체크
			Stack<bracket> stack = new Stack<>();
			stack.add(new bracket(s[0], 0)); //순번이 0번부터임
			
			for (int i = 1; i < l; i++) { //0은 넣었으니 1번부터
				//이전께 (고, 이번이 )이거면 뽑아서 제거
				if(stack.isEmpty()) stack.add(new bracket(s[i], i));
				else if(stack.peek().object == '(' && s[i] == ')') {
					stack.pop();
				}else {
					stack.add(new bracket(s[i], i));
				}
			}
			
			//애초에 완전한 문자열일 경우
			if(stack.size() == 0) { 
				System.out.println("#"+(tc+1)+" "+0);
				break;
			}
			
			//쉬운 괄호만 따로 저장할 easy 배열
			bracket[] easy = new bracket[stack.size()];
//			boolean waitfirst = true;
			boolean wait = true;
			int changePoint = -1;
			int startPoint = -1;
			for (int i = easy.length - 1; i >= 0; i--) {
				easy[i] = stack.pop();
				//넣으면서 처음으로 ) 이게 나오는것 체크
				if(wait && easy[i].object == ')') {
					wait = false;
					changePoint = easy[i].index; // ~i가 ))))))))되어있다는 것
				}
				if(easy[i].object == ')') {
					startPoint = easy[i].index; // 기준점.
				}
			}
			
//			for (int i = 0; i < easy.length; i++) {
//				System.out.print(easy[i].object);
//			}
//			System.out.println();
//			for (int i = 0; i < easy.length; i++) {
//				System.out.print(easy[i].index);
//			}
//			System.out.println();
//			System.out.println("changePoint"+changePoint);
//			System.out.println("startPoint"+startPoint);
//			System.out.println("========");
			
			
			//2. 한개의 괄호로 통일
			//달라지는 부분 체크해야함. 
			if(wait) { //달라지는 부분이 없다.
				System.out.println("#"+(tc+1)+" "+1); //남은 한번이면 된다.
				//통일할 필요 없다.
			}else {
				System.out.println("#"+(tc+1)+" "+2); //남은 한번 추가해야함 된다.
				
				//0~i번째는 ))))))))) ->> (((((((로 뒤집어야 한다.
				int printCount = -1;
				int first = 0;
				int second = 0;
				for (int i = 0; i < easy.length; i++) {
					if(easy[i].object == ')') {
						//처음 만나는 괄호를 찾기 위해 princtCount변수 사용
						if(++printCount == 0) first = easy[i].index;
						else printCount = easy[i].index; //한번사용한 뒤 마지막으로 바뀐 )를 추적하기 위해 저장
						easy[i].object = '(';
						easy[i].index = Math.abs(easy[i].index - (changePoint) + startPoint);
					}else break;
				}
				second = printCount;
				if(first > second) {
					int temp = first;
					first = second;
					second = temp;
				}
				//마지막으로 변경한 것을 출력
				System.out.print(first+" "+second);
				System.out.println();
				//첫번째 뒤집기
			}
			
			
//			System.out.println("-----------");
//			for (int i = 0; i < easy.length; i++) {
//				System.out.print(easy[i].object);
//			}
//			System.out.println();
//			for (int i = 0; i < easy.length; i++) {
//				System.out.print(easy[i].index);
//			}
//			System.out.println();
//			System.out.println();
			
			//3. 반틈 뒤집기 뒷부분 (((((((((((((((( 이거를 ))))))))))))로 만들어야 함.
			//둘 중에 작은게 먼저 출력되도록
			if(easy[ (easy.length)/2 ].index > easy[ (easy.length-1) ].index) {
				System.out.println(easy[ (easy.length-1) ].index+" "+easy[ (easy.length)/2 ].index);
			}
			else System.out.println(easy[ (easy.length)/2 ].index+" "+easy[ (easy.length-1) ].index);
			
//			for (int i = 0; i < easy.length; i++) {
//				System.out.print(easy[i].object);
//			}
//			System.out.println();
//			for (int i = 0; i < easy.length; i++) {
//				System.out.print(easy[i].index);
//			}
//			System.out.println();
	
		}//tc 끝
		br.close();
	}
	
	static class bracket {
		char object;
		int index;
		
		public bracket(char object, int index) {
			super();
			this.object = object;
			this.index = index;
		}
	}

}