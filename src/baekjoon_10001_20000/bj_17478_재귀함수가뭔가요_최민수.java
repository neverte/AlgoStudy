package baekjoon_10001_20000;

import java.io.FileInputStream;
import java.util.Scanner;

//[실버 5] 재귀함수가 뭔가요?
//https://www.acmicpc.net/problem/17478
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_17478_재귀함수가뭔가요_최민수 {
	//강사님: 스트링 배열에 출력 문장을 집어넣으면 코드가 깔끔함.
	
	public static int num = 0;
	
	public static void func(int n) {
		for (int i = 0; i < num-n; i++) {
			System.out.print("____");
		}
		System.out.println("\"재귀함수가 뭔가요?\"");
		if(n == 0) {
			for (int i = 0; i < num-n; i++) {
				System.out.print("____");
			}
			System.out.println("\"재귀함수는 자기 자신을 호출하는 함수라네\"");
			for (int i = 0; i < num-n; i++) {
				System.out.print("____");
			}
			System.out.println("라고 답변하였지.");
			return;
		}else {
			for (int i = 0; i < num-n; i++) {
				System.out.print("____");
			}
			System.out.println("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
			for (int i = 0; i < num-n; i++) {
				System.out.print("____");
			}
			System.out.println("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
			for (int i = 0; i < num-n; i++) {
				System.out.print("____");
			}
			System.out.println("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
			
		}
		
		func(n-1);
		for (int i = 0; i < num-n; i++) {
			System.out.print("____");
		}
		System.out.println("라고 답변하였지.");
	}
	
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_17478_input"));
		Scanner sc = new Scanner(System.in);

		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		num = sc.nextInt();
		func(num);
		sc.close();
	}
}
