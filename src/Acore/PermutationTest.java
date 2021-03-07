package Acore;

import java.util.Arrays;

public class PermutationTest {
	
	static int[] numbers;
	static int N = 3;
	static boolean[] isSelected;
	
	static void permutation(int cnt) {
		for (int i = 1; i <= N; i++) { //i: 시도하는 숫자
			if(cnt == N) {
				System.out.println(Arrays.toString(numbers));
				return;
			}
			if(isSelected[i]) continue; //사용중이면 다음숫자 써라.
			
			//사용 안했으면
			numbers[cnt] = i;
			isSelected[i] = true;
			
			permutation(cnt+1); //난 다뽑았다. 이젠 다음사람이 뽑아라.
			
			//다 만들고 돌아왔다. 이제 현재자리에 다음수 만들어야된다.
			//그 전에 사용한거 안썼다고 돌려놔야 뒤에서 쓸 수 있음
			isSelected[i] = false;
			
		}
		
	}
	
	public static void main(String[] args) {
		numbers = new int[N];
		isSelected = new boolean[N+1];
		
		permutation(0);
		
	}
}
