package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//[실버 1] 세훈이의 선물가게
//https://www.acmicpc.net/problem/17225
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_17225_세훈이의선물가게_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_17225"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//상민 - 파란색, A초, 동시에 가져오면 상민이가 먼저 가져옴
		//지수 - 빨간색, B초
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		//방문한 손님 수 n 1~1000
		int n = Integer.parseInt(st.nextToken());
		
		PriorityQueue<present> pq = new PriorityQueue<present>();
		int aEnd=0, bEnd=0; //각각 상민, 지수 포장 끝나는 시간
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			//주문 시각 | 포장지 색 | 선물의 개수
			int startTime = Integer.parseInt(st.nextToken());
			String color = st.nextToken();
			int presentNum = Integer.parseInt(st.nextToken());
			
			//주문을 받으면, pq에 각 물건당 일처리 완료시간을 저장하고
			//해당 인물의 마지막 포장 시간을 따로 저장하여, 그 저장시간 이전에 주문이 들어오는 경우 처리한다.
			if(color.equals("B")) {
				if(startTime >= aEnd) { //상민이가 포장 끝낸 후 주문이 들어왔다.
					int endTime = startTime;
					for (int j = 0; j < presentNum; j++) {
						pq.offer(new present(endTime, "B"));
						endTime += a;
						aEnd = endTime;
					}
				}else { //아직 상민이가 다른일을 처리 중인데 주문이 들어왔다.
					int endTime = aEnd;
					for (int j = 0; j < presentNum; j++) {
						pq.offer(new present(endTime, "B"));
						endTime += a;
						aEnd = endTime;
					}
				}

			} else { // 지수한테 주문이 들어왔다.
				if (startTime >= bEnd) {
					int endTime = startTime;
					for (int j = 0; j < presentNum; j++) {
						pq.offer(new present(endTime, "R"));
						endTime += b;
						bEnd = endTime;
					}
				} else { // 아직 지수가 다른일을 처리 중인데 주문이 들어왔다.
					int endTime = bEnd;
					for (int j = 0; j < presentNum; j++) {
						pq.offer(new present(endTime, "R"));
						endTime += b;
						bEnd = endTime;
					}
				}
			}
		}
		
		//pq에서 꺼내면서 같은 시간이 같고 상민, 지수가 각각 처리하는 경우 처리.
		int aCnt = 0;
		int bCnt = 0;
		int[] aArr = new int[100000];
		int[] bArr = new int[100000];
		
		while(!pq.isEmpty()) {
			
			present temp = pq.poll();
//			System.out.println(temp.endTime + " " + temp.who);
			//상민
			if(temp.who.equals("B")) aArr[aCnt++] = aCnt+bCnt;
			else bArr[bCnt++] = aCnt+bCnt;
		}
		System.out.println(aCnt);
		for (int i = 0; i < aCnt; i++) {
			System.out.print(aArr[i]+" ");
		}
		System.out.println("\n"+bCnt);
		for (int i = 0; i < bCnt; i++) {
			System.out.print(bArr[i]+" ");
		}
		
		br.close();
	}
	
	static class present implements Comparable<present>{
		int endTime;
		String who;
		
		public present(int endTime, String who) {
			super();
			this.endTime = endTime;
			this.who = who;
		}



		@Override
		public int compareTo(present o) {
			int condition1 = Integer.compare(this.endTime, o.endTime);
			//숫자가 같으면
			if(condition1 == 0) {
				if(this.who.equals(o.who)) return 1; //같은 사람이면 상관없다.
				else if(this.who.equals("B")) return -1; //this가 세훈이면
				else return 1; //this가 지수면
			} else return condition1;
			 
		}
	}
}