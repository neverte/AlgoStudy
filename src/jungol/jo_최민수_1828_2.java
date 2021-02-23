package jungol;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//냉장고
//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1101&sca=99&sfl=wr_hit&stx=1828
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class jo_최민수_1828_2 {

	public static void main(String[] args) throws IOException {
		//테스트 입력
		System.setIn(new FileInputStream("res/jungol/jo_input_1828"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//화학물질의 수 N: 1~100
		int N = Integer.parseInt(br.readLine());
		
		//각 화학물질의 최저, 최고 보관 온도 -270~10000
		//각 온도를 저장할 온도 배열 생성
		int temperature[] = new int[10271];

		//[i][0]: i번째 원소의 최저, [i][1]: i번째 원소의 최고온도
		// [][2]: 0이면 삭제 안했다. 1이면 삭제했다.
		int chemicals[][] = new int[N][3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			//단 음수가 들어올 수 있으니 + 270해주자
			chemicals[i][0] = Integer.parseInt(st.nextToken()) + 270;
			chemicals[i][1] = Integer.parseInt(st.nextToken()) + 270;
			//온도만큼 ++
			for (int j = chemicals[i][0]; j <= chemicals[i][1]; j++) {
				//++하면서 max까지 한번에 체크
				++temperature[j];
			}
		}
		
		Arrays.sort(chemicals, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[1] <= b[1] ? -1 : 1; } 
			});

		
		for(int[] in : chemicals) System.out.println(Arrays.toString(in));
//		System.out.println(Arrays.toString(temperature));
		
		//냉장고 한개씩 추가
		int refNum = 0;
		while (true) {
			//가장 많이 겹친 부분
			int max = Integer.MIN_VALUE;
			//max 좌표
			int maxPoint = Integer.MIN_VALUE;
			//temperature 돌면서 최고 점수 체크
			for (int i = 0; i < temperature.length; i++) {
				if(temperature[i] >= max) {
					max = temperature[i];
					maxPoint = i;
				}
			}
//			for (int i = temperature.length - 1; i >= 0; i--) {
//				if(temperature[i] >= 2) {
//					max = temperature[i];
//					maxPoint = i;
//					break;
//				}
//			}
			
			if(max == 0) break;
			System.out.println("max: " + max);
			System.out.println("maxPoint: " + (maxPoint-270 ));
			//이제 해당 부분에 냉장고를 한개 추가하면서
			refNum++;
			
			//냉장고가 커버하는 화학물질만큼 temperature에서 뺄것임.
			//화학물질 개수만큼 확인
			for (int i = 0; i < N; i++) {
				//i번쨰 원소가 삭제된 원소니?
				if(chemicals[i][2] == 1) continue;
				//냉장고를 추가한 지점과 화학물질 보관온도가 겹치면
				if(maxPoint >= chemicals[i][0] && maxPoint <= chemicals[i][1]) {
					//temperature에서 해당 구역을 싹다 1빼줌.
					for (int j = chemicals[i][0]; j <= chemicals[i][1]; j++) {
						temperature[j]--;
					}
					//i번째원소 이제 삭제했다. 다시 삭제하지마.
					chemicals[i][2] = 1;
				}
				
			}
			
		}
//		System.out.println(Arrays.toString(temperature));
		System.out.println(refNum);
		
		//아이디어
		//모든 구간에 1을 더하고
		//가장 높은 구간부터 냉장고를 추가하면서 한개씩 뺀다?
		//그러면 냉장고 1개 추가할때마다 배열의 모든 원소를 순회해야함
		//그러면 최대 N*N 만큼 돌아야함.
		//N이 100이니까 할만한듯?

		br.close();
	}
}