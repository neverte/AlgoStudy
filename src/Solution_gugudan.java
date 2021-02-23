import java.util.Scanner;

public class Solution_gugudan {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("단: ");
		int dan = sc.nextInt();
		
		
		end: for(int i=1;i<10;i++) {
			for(int j=2; j<=dan; j++) {
				System.out.print(j+"x"+i+"="+i*j+" ");
			}
			if(i==5) {
				break end;//break + end label을 활용하면 통채로 스킵하는 기술 label 명은 마음대로 붙여도 됨.
			}
			System.out.println();
		}
		sc.close();
	}
}
//딘: 5
// 2x1=2   3x1=