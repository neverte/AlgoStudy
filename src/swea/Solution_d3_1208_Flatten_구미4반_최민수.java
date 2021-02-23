package swea;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//패키지 날려야되나
//class Solution으로 바꾸기
//내부 입력 주석처리하기
public class Solution_d3_1208_Flatten_구미4반_최민수 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/swea/input_d3_1208.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//가로길이 100
		//상자높이 1~100
		//총 10번의 Testcase
		for (int i = 0; i < 10; i++) {
			//덤프횟수 받기 1~1000
			 int dump = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			//boxes갯수 넣기
			int[] boxes = new int[100];
			for (int j = 0; j < 100; j++) {
				boxes[j] = Integer.parseInt(st.nextToken());
			}
			//sorting
			Arrays.sort(boxes);
			//dump횟수만큼 dump
			while(dump-- > 0) {
				boxes[0]++;
				boxes[99]--;
				Arrays.sort(boxes);
				
			}

			System.out.println("#"+(i+1)+" "+(boxes[99]-boxes[0]) );
		}
	
		br.close();
	}

}
