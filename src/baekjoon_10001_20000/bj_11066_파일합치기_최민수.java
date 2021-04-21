package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//[골드 3] 파일 합치기
//https://www.acmicpc.net/problem/11066
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_11066_파일합치기_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_11066"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//두 개의 파일을 합쳐서 하나의 임시파일
		//두 개의 파일을 합칠 때 필요한 비용(시간 등)이 두 파일 크기의 합
		
		//조건 1. 연속이 되어야 함

		//DP 거꾸로 생각해보자.
		//마지막 상황 = 1~x개짜리 덩어리 + x+1번쨰~k덩어리
		

		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			//첫행: 소설 장수 K 3~500
			int k = Integer.parseInt(br.readLine());
//			int[] pageSize = new int[k];
			ArrayList<Integer> pageSize = new ArrayList<Integer>();
			//두번째행: 파일의 크기 1~10000
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < k; i++) {
//				pageSize[i] = Integer.parseInt(st.nextToken());
				pageSize.add(Integer.parseInt(st.nextToken()));
			}
			
			int leftPage = k;
			int answer = 0;
			while(leftPage > 1) {
				//1. 최소값 찾고
				int sum = Integer.MAX_VALUE;
				int minNum = -1;
				for (int i = 1; i < leftPage; i++) {
					int temp = pageSize.get(i-1) + pageSize.get(i);
					if(temp < sum) { //합이 같으면 뭘 우선으로 해야할까.
						minNum = i;
						sum = temp;
					}
				}
				// 0 1 2 3에서
				// 2를 저장하고 있다면
				// 1번쨰 자리 2번 빼고 1번자리에 넣으면 된다
				pageSize.remove(minNum-1);
				pageSize.remove(minNum-1);
				pageSize.add(minNum-1, sum);
				answer += sum;
				System.out.println(sum);
				
				//2. 합치고
				//합칠 때 배열이면 인덱스 관리가 귀찮고, collection들은 속도가 느릴듯
				//3. 다시 1로 반복
				leftPage--;
			}
			System.out.println(answer);
			
			
		}//tc 끝
		
		br.close();
	}
}


//단서 1. 최소비용인 장이 여러번 포함될 수록 전체 비용이 줄어듦
//=> 모든 장수를 고려해서 합이 최소인 애들을 더한다
//=> 합연산 500회 * 
//=> 정답 안나옴.

//단순히 합연산 최대 횟수가 499회임. 합칠 때마다 1개씩 페이지가 줄어드니까.
//근데 그 가짓수는 아마 500개짜리 순열