package jungol;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.stream.events.StartDocument;

//조커
//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=488&sca=99&sfl=wr_hit&stx=1205
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class jo_조커_1205 {

	public static void main(String[] args) throws IOException {
		//테스트 입력
		System.setIn(new FileInputStream("res/jungol/jo_input_1205"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//카드의 개수 N ~1000
		int N = Integer.parseInt(br.readLine());
		int[] card = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
	
		int jokerNum = 0;
		for (int i = 0; i < N; i++) {
			//숫자 범위 0~100만
			card[i] = Integer.parseInt(st.nextToken());
			if(card[i] == 0)jokerNum++;
		}
		
		//정렬
		Arrays.sort(card);
		
		int nextStart = Integer.MIN_VALUE;
		int max = Integer.MIN_VALUE;
		int jokerCount = 0;
		List<Integer> straight = new ArrayList<Integer>();
		
		//조커 수만큼 스킵해서 시작
		if(jokerNum == card.length) max = jokerNum;
		for (int i = jokerNum; i < card.length; i++) {
			//첫번쨰 원소 삽입
			if(straight.size() == 0) {
				straight.add(card[i]);
				nextStart = i+1;
			}
			else {
				//숫자가 같은게 들어오면 스킵
				if( card[i] == straight.get(straight.size()-1) ) continue;
				//이번 카드랑 straight와 비교해서 1차이나면 삽입
				if( card[i] - straight.get(straight.size()-1) == 1) {
					straight.add(card[i]);
					if(i == card.length-1) {
						//조커안쓰고 끝까지 온 경우는?
						max = Math.max(max, straight.size() + (jokerNum - jokerCount));
					}else {
						max = Math.max(max, straight.size());
					}
				}
				//1초과 차이나고 조커가 남아있으면 조커삽입
				else if(jokerCount < jokerNum){
					jokerCount++;
					//조커 삽입
					straight.add((straight.get(straight.size()-1) + 1));
					i--; //이번 카드랑 조커 넣은거랑 다시 비교해야함.
				//1초과 차이나는데 조커가 없으면 리셋	
				//조커가 다 떨어지면 straight끝. clear
				}else if(jokerCount >= jokerNum) {
					max = Math.max(max, straight.size());
					jokerCount = 0;
					straight.clear();
					i = nextStart-1; //끝나자마자 i++되니까
				}
			}
		}
		System.out.println(max);

		br.close();
	}

}