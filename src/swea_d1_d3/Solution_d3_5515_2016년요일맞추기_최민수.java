package swea_d1_d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d3_5515_2016년요일맞추기_최민수 {
	
	static int month[] = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d3_5515.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int m = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			int totalDay = d;
			for (int i = 0; i < m; i++) { //2월이면 1월을 더해주고, 3월이면, 1, 2를 더해줘야함
				totalDay += month[i];
			}

			//각 달을 더해서 x월y일을 abc일로 만든다음에 계산하자.
			//1월 1일이면 totalDay가 1인데 금요일은 4이다. 따라서 +3를 해줌
			totalDay += 3;
			totalDay %= 7;
			
			//월요일이면 0, 화요일이면 1, 수요일이면 2, 목요일이면 3, 금요일이면 4, 토요일이면 5, 일요일이면 6
			System.out.println("#"+(tc+1)+" "+totalDay);
		}//tc끝
		
		br.close();
	}

}

