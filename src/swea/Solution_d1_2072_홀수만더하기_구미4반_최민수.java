package swea;
import java.util.Scanner;
//패키지 날려야되나
//class Solution으로 바꾸기
//내부 입력 주석처리하기
public class Solution_d1_2072_홀수만더하기_구미4반_최민수 {
	//정월, 백준은 Main

	public static void main(String[] args) throws Exception {
		Scanner line = new Scanner(System.in);
		int T = line.nextInt();
	
		int sum = 0;
		for(int i=0; i<T; i++) {
			int array[] = new int[10];
			for(int k = 0; k < array.length; k++) {
				array[k] = line.nextInt();
			}
			for(int j : array) {
				if(j%2 ==1) {
					sum += j;
				}
			}
			System.out.println("#" + i+1 + " " + sum);
			sum = 0;

		}

	}

}
