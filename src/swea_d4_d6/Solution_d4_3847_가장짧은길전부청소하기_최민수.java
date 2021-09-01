package swea_d4_d6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d4_3847_가장짧은길전부청소하기_최민수 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d4_3947.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {

			//건물 1~N, 1은 집, N: 1~20만
			//길 M개 0~50만
			
			//자신이 이용하는 길만 청소
			//자기 집에서 어떤 한 건물로 가는 최단경로를 이용해서 그 건물에 갔다가 다시 집으로 돌아오는 것
			
			//석환이는 동네에 있는 모든 건물을 방문
			//각 건물에 대해서 석환이 집에서 그 건물에 가는 최단경로에 존재하는 길들을 청소
			
			System.out.println("#"+(tc+1)+" "+" ");
		}//tc끝
		
		br.close();
	}

}
