package swea_d1_d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// 패키지 날려야되나
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d2_1954_달팽이숫자_구미4반_최민수 {
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/swea/input_d2_1954.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//Testcase
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			int arrSize = Integer.parseInt(br.readLine());
			//배열 생성
			int[][] arr = new int[arrSize][arrSize];
			
			//0 초기, 1우, 2하, 3좌, 4상
			int state = 0;
			int count = 1;
			int x=0, y=0;
			while (count <= arrSize*arrSize) {
				switch (state) {
				case 0:
					arr[0][0] = count++;
					state = 1;
					break;
				case 1: //우
					//오른쪽 공간이 비어있거나, 차있으면 스킵
					if(y+1 >= arrSize || arr[x][y+1] != 0) state = 2;
					else {
						y++;
						arr[x][y] = count++;
					}
					break;
				case 2: //하
					//아래쪽 공간이 비어있거나, 차있으면 스킵
					if(x+1 >= arrSize || arr[x+1][y] != 0) state = 3;
					else {
						x++;
						arr[x][y] = count++;
					}
					break;
				case 3: //좌
					//왼쪽 공간이 비어있거나, 차있으면 스킵
					if(y-1 < 0 || arr[x][y-1] != 0) state = 4;
					else {
						y--;
						arr[x][y] = count++;
					}
					break;
				case 4: //상
					//위쪽 공간이 비어있거나, 차있으면 스킵
					if(x-1 < 0 || arr[x-1][y] != 0) state = 1;
					else {
						x--;
						arr[x][y] = count++;
					}
					break;
				}
			}
			System.out.println("#"+(i+1));
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < arrSize; j++) {
				for (int j2 = 0; j2 < arrSize; j2++) {
					sb.append(arr[j][j2]).append(" ");
				}
				sb.setLength(sb.length()-1);
				sb.append("\n");
			}
			System.out.print(sb);
		}
		br.close();
	}
}
