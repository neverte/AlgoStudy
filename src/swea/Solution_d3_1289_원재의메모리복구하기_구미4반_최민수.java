package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//패키지 날려야되나
//class Solution으로 바꾸기
//내부 입력 주석처리하기
public class Solution_d3_1289_원재의메모리복구하기_구미4반_최민수 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d3_1289.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		//00...00에서 목표값으로 바꾸는데 들어가는 횟수
		
		for (int i = 0; i < T; i++) {
			//true면 0, false면 1
			boolean state = true;
			int count = 0;
			char[] ca = br.readLine().toCharArray();
			for (int j = 0; j < ca.length; j++) {
				if(state == true && ca[j] == '1') {
					state = false;
					count++;
				} else if(state == false && ca[j] == '0') {
					state = true;
					count++;
				}
			}
			System.out.println("#"+(i+1)+" "+count);
		}
		
		br.close();
	}

}
