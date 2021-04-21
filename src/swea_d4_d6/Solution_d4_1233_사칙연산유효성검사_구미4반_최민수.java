package swea_d4_d6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d4_1233_사칙연산유효성검사_구미4반_최민수 {
	public static void main(String[] nodes) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d4_1233.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//총 10개의 테케
		for (int i = 0; i < 10; i++) {
			int nodeNum = Integer.parseInt(br.readLine());
			
			//정답 출력용
			int answer = 1;
			
			//리프노드 파악용
			int temp = 0;
			// 예를들어 8 >= 4이면, 8>= 8이되는 순간 나옴
			while(nodeNum >= Math.pow(2, temp)) {
				temp++;
			}
			int powNow = (int) Math.pow(2, temp-1);
			// 숫자만 와야하는 리프노드는
			// 8 - 8 + 1 =  1,  8번부터 8번까지
			//계산 불가 경우 1. 리프 노드에 숫자대신 기호가 오는 경우
			//                         171 - 128 + 1
			int lastLineNum = nodeNum + 1 - powNow;
			// 2. 높이가 가장 큰 노드들의 갯수가 홀수이면
			if(lastLineNum % 2 == 1) answer = 0;
			// 3. 최대 높이 - 1이면서 리프인 녀석들
			// 8 - lastLineNum/2
			int notLastYesLeefNum = powNow/2 - (lastLineNum / 2 + lastLineNum % 2);
			int notLastStartPoint = powNow - notLastYesLeefNum;

			
			for (int j = 1; j <= nodeNum; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				st.nextToken();
				//경우 1
				if(j >= notLastStartPoint && j <= nodeNum) {
					String leef = st.nextToken();
					if(leef.equals("*") || leef.equals("/") || leef.equals("+") || leef.equals("-")) answer = 0;
//				}else if(j >= notLastStartPoint && j < powNow) {
//					st.nextToken();
				}else {
					for (int k = 0; k < st.countTokens(); k++) {
						st.nextToken();
					}
				}
			}
			System.out.println("#"+(i+1)+" "+answer);
			
		}
		// 총 노드는 200개를 넘지 않음

		//출력
		//계산이 가능하다면 “1”, 계산이 불가능할 경우 “0”을 출력
		br.close();
	}

}
