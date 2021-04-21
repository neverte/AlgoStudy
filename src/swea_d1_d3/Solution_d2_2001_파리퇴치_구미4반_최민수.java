package swea_d1_d3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 패키지 날려야되나
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d2_2001_파리퇴치_구미4반_최민수 {
	static int calc(int x, int y) {
		int re = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				re += arr[x+i][y+j];
			}
		}
		return re;
	}
	
	static int[][] arr;
	static int M;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d2_2001.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//테케
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			// N * N 배열, 파리채크기 M*M
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			//정답 담을 변수
			int result = 0;
			
			arr = new int[N][N];
			for (int j = 0; j < N; j++) {
				//String[]을 int[]로 바꾸는 법
				//https://zetawiki.com/wiki/%EC%9E%90%EB%B0%94_String_%EB%B0%B0%EC%97%B4%EC%9D%84_int_%EB%B0%B0%EC%97%B4%EB%A1%9C_%EB%B3%80%ED%99%98
				arr[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			
			//시작점을 이중for문으로 주고, 값 계산만 method로
			for (int j = 0; j < N-M+1; j++) {
				for (int j2 = 0; j2 < N-M+1; j2++) {
					result = Math.max(result, calc(j, j2));
				}
			}
			
			System.out.println("#"+(i+1)+" "+result);
		}
		br.close();
	}

}
