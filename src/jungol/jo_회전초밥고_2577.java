package jungol;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//회전초밥(고)
//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1838&sca=99&sfl=wr_hit&stx=2577
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class jo_회전초밥고_2577 {

	public static void main(String[] args) throws IOException {
		//테스트 입력
		System.setIn(new FileInputStream("res/jungol/jo_input_2577"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//벨트 위에는 같은 종류의 초밥이 둘 이상 있을 수 있다.
		//행사 1. 특정 위치부터 K접시 연속해서 먹으면 할인된 가격으로 제공함
		//행사 2. 초밥 종류 x가 적힌 쿠폰을 주고, 1번 행사에 참가하면 x를 줌.(벨트위에 없어도 만들어서 줌)
		
		//무조건 할인행사 참여할 것임.
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		//접시의 수 n: 2~300만
		//초밥의 가짓수 d: 2~3000
		//연속해서 먹는 접시의 수 k: 2~3천
		//쿠폰번호 c 1~d
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken()) - 1; //초밥번호가 0~ d-1로 계산할꺼임
		
		int[] sushiList = new int[n];
		int[] sushiType = new int[3001];
		
		//행사 2의 쿠폰 무조건 쓸꺼니까 미리 1 추가
		int currentType = 1;
		sushiType[c]++; //쿠폰 사용
		
		for (int i = 0; i < n; i++) {
			sushiList[i] = Integer.parseInt(br.readLine())-1; //0~d-1번까지로 입력 넣기
			//k개가 될때까지 (0~k-1번째
			if(i < k) {
				sushiType[sushiList[i]]++;
				//방금 추가해서 1개 추가된 것이면 타입 수가 증가했다는 것.
				if(sushiType[sushiList[i]] == 1) currentType++;
			}
		}
		
		int max = Math.max(Integer.MIN_VALUE, currentType);
		//단순하게 생각해보자
		//n-k가지 방법의 수를 모두 고려할 수 있다.

		//k개의 리스트를 가지고 벨트를 1칸씩 이동하면서
		//가장 마지막에 먹은것은 제외하고, 새로 추가된거 추가하면서 최대 경우의 수 체크
		for (int i = 0; i < n-1; i++) {
			//삭제포인터 i: i번째에 있는 친구 삭제할꺼임
			sushiType[sushiList[i]]--;
			if(sushiType[sushiList[i]] == 0) currentType--;
			
			//추가 포인터 i+k: 1+k번째에 있는 친구 추가할꺼임
			//근데 배열 범위 초과하는 경우 있다. -> (i+k) % n  
			sushiType[sushiList[(i+k) % n]]++;
			if(sushiType[sushiList[(i+k) % n]] == 1) currentType++;
			
			max = Math.max(max, currentType);
		}
		//출력: 초밥의 가짓수의 최대값
		System.out.println(max);
		
		br.close();
	}

}
//폐기 아이디어 1
//가지치기: 쿠폰번호가 들어간 상품은 제외한다?
//1 2 3 4 ... "10" .... 100 이런경우엔 10이 들어가는 경우를 제외하면 빠르겠지만
//1 1 1 1 1 2 3 4 "10" 5 6 7 1 1 1 1 1 이런경우엔 오히려 10을 고려해야 한다.