package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// 패키지 날려야되나
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d3_1210_Ladder1_구미4반_최민수 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d3_1210.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] ladder = new int[100][100]; 
		
		//10개의 testcase
		for (int i = 0; i < 10; i++) {
			//첫번째줄 날려
			int endPoint = 100;
			br.readLine();
			for (int j = 0; j < 100; j++) {
				//https://zetawiki.com/wiki/%EC%9E%90%EB%B0%94_String_%EB%B0%B0%EC%97%B4%EC%9D%84_int_%EB%B0%B0%EC%97%B4%EB%A1%9C_%EB%B3%80%ED%99%98
				ladder[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			//도착지점 찾기
			for (int j = 0; j < 100; j++) {
				if(ladder[99][j] == 2) {
					endPoint = j;
					break;
				}
			}
			//위로 올라가면서 검사
			for (int j = 99; j >= 0; j--) {
				//접근하려는 곳이 범위 안인지 확인
				//좌우에 1이 있는지 검사
				//있으면 그 방향으로 이동
				//<오른쪽>
				while (true) {
					if(endPoint+1 > 99) {
						break;
					}else {
						//오른쪽이 범위 안인지 확인
						if(ladder[j][endPoint+1] == 1) {
							ladder[j][endPoint+1] = 7;
							endPoint++;
						}
						else break;
					}
				}
				//<왼쪽>
				while (true) {
					if(endPoint-1 < 0) {
						break;
						
					}else {
						//오른쪽이 범위 안인지 확인
						if(ladder[j][endPoint-1] == 1) {
							ladder[j][endPoint-1] = 7;
							endPoint--;
						}
						else break;
					}
				}
				//없으면 위로 한 칸 이동
			}
			ladder[0][endPoint] = 5;
//			for(int[] d : ladder) System.out.println(Arrays.toString(d));

			System.out.println("#"+(i+1)+" "+endPoint);
		}
		
		br.close();
	}

}
