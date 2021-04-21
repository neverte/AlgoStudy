package swea_d1_d3;
import java.util.Scanner;

//패키지 날려야되나
//class Solution으로 바꾸기
//내부 입력 주석처리하기
public class Solution_d1_1545_거꾸로출력해보아요_구미4반_최민수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		for(int i=n; i>-1; i--) {
			System.out.print(n+" ");
			n--;
		}
		
		sc.close();
	}

}
