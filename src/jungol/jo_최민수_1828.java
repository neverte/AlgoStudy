package jungol;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//냉장고
//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1101&sca=99&sfl=wr_hit&stx=1828
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class jo_최민수_1828 {

	public static void main(String[] args) throws IOException {
		//테스트 입력
		System.setIn(new FileInputStream("res/jungol/jo_input_1828"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//아이디어
		//greedy?
		//Activity Selection(회의시간) 문제와 동일한 접근 방법
		//각 화학물질 별 최고온도순으로 정렬한 다음
		//첫번째 원소의 최고온도보다 i번째 원소의 최저온도가 낮으면
		//첫번쨰 냉장고에 들어가고
		//그렇지 않으면 새 냉장고를 만든다.
		
		//화학물질의 수 N: 1~100
		int N = Integer.parseInt(br.readLine());
		
		//각 화학물질의 최저, 최고 보관 온도 -270~10000
		//[i][0]: i번째 원소의 최저, [i][1]: i번째 원소의 최고온도
		int chemicals[][] = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			//단 음수가 들어올 수 있으니 + 270해주자
			chemicals[i][0] = Integer.parseInt(st.nextToken());
			chemicals[i][1] = Integer.parseInt(st.nextToken());
		}
		
		//비교 정렬
		Arrays.sort(chemicals, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				//최고 온도로 비교할 것이기 때문에 [1]
				return Integer.compare(a[1], b[1]);} 
			});

		//정렬 확인용
//		for(int[] in : chemicals) System.out.println(Arrays.toString(in));
		
		//냉장고 한개씩 추가
		int refNum = 1;
		//현재 냉장고의 최고온도
		int refHigh = chemicals[0][1];

		//각 화학물질마다 현재 냉장고에 들어가는지 확인
		for (int i = 1; i < N ; i++) {
			//이번 냉장고 최대 온도보다, 이번 화학물질의 최소 온도가 높으면
			// 새 냉장고를 추가해야 한다.
			if(refHigh < chemicals[i][0]) {
				refNum++;
				refHigh = chemicals[i][1];
			}
		}
		
		System.out.println(refNum);
		
		br.close();
	}
}