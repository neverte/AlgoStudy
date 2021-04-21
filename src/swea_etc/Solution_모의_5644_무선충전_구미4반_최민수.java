package swea_etc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_모의_5644_무선충전_구미4반_최민수 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_모의_5644.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//1. 자기 혼자만 해당 비콘에 접근하면 그 값을 획득
		//2. 같이 가면, 2개 겹쳐있는지 확인하고, 겹치면 둘다 획득, 1개면 나눠 가지기
		//2를 시행하기 위해서 겹쳐있는지를 알아야 하기 때문에
		//map의 각 칸에 비콘이 몇개 겹쳤는지, 겹쳤다면 최대값과 차선값만 저장해둔다. (최대 4개가 겹칠 수 있음)
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			//총 이동시간 Move 20~100
			//BC의 개수 beaconNum 1~8
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int move = Integer.parseInt(st.nextToken());
			int beaconNum = Integer.parseInt(st.nextToken());
			
			//A, A와 B의 이동 정보
			//0 이동 X, 1234 (상우하좌)
			int[] moveA = new int[move];
			int[] moveB = new int[move];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < moveB.length; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < moveB.length; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}
			
			//baecon 정보를 저장할 map
			//[0]: 인접한 비콘 수, [1]~[비콘수]각 비콘의 값 업데이트
			int[][][] map = new int[10][10][beaconNum+1];
			
			//beacon의 정보
			for (int i = 0; i < beaconNum; i++) {
				st = new StringTokenizer(br.readLine());
				//표시가 x, y 바뀌어 있음.
				int bcy = Integer.parseInt(st.nextToken()) - 1; //0~9로 할꺼임
				int bcx = Integer.parseInt(st.nextToken()) - 1;
				//충전범위
				int bccharge = Integer.parseInt(st.nextToken());
				//처리량
				int bcprocess = Integer.parseInt(st.nextToken());
				
				updateMap(map, bcx, bcy, bccharge, bcprocess, i+1);
			}
			
			//정답
			int answer = 0;
			
			//A, B가 탐색할꺼임
			//사용자가 지도 밖으로 이동하는 경우는 없다.
			ax = 0;
			ay = 0;
			bx = 9;
			by = 9;
			for (int i = 0; i < move+1; i++) {
				//사용자의 초기 위치(0초)부터 충전을 할 수 있다.
				
				//둘 중 한명이 비콘 영역 밖이다.
				int maxa = 0, maxb = 0, Abc = 0, Bbc = 0;
				for (int j = 1; j < beaconNum+1; j++) {
					if(maxb < map[bx][by][j]) {
						maxb = map[bx][by][j];
						Bbc = j;
					}
				}
				for (int j = 1; j < beaconNum+1; j++) {
					if(maxa < map[ax][ay][j]) {
						maxa = map[ax][ay][j];
						Abc = j;
					}
				}
				
				if(map[ax][ay][0] == 0) answer += maxb;
				else if(map[bx][by][0] == 0) answer += maxa;
				else { //둘 다 비콘 위다.
					//같은 비콘위에 있는지 확인
					if(Abc == Bbc) {
						//차선이 둘 다 없으면 2명이서 나눠가짐.
						if(map[ax][ay][0] == 1 && map[bx][by][0] == 1 ) {
							answer += maxa;
						}
						else {
							//둘 중의 한명의 차선이 높은걸 선택
							int secondBest = 0;
							for (int j = 1; j < beaconNum+1; j++) {
								if(j == Abc) continue;
								secondBest = Math.max(secondBest, map[ax][ay][j]);
							}
							for (int j = 1; j < beaconNum+1; j++) {
								if(j == Bbc) continue;
								secondBest = Math.max(secondBest, map[bx][by][j]);
							}
							answer += secondBest + maxa;
						}
					}else {
						//최대 값이 있는 비콘이 다르면 더하면 끝
						answer += maxa + maxb;
					}
				}
				
				//
				//둘다 이동
				if(i < move) {
					Amov(moveA[i]);
					Bmov(moveB[i]);
					
				}
			}
			
			System.out.println("#"+(tc+1)+" "+answer);
			
		} //tc끝
		
		br.close();
	}

	static int ax, ay, bx, by;
	
	private static void Amov(int i) {
		switch (i) {
		case 0: //제자리
			break;
		case 1: //상
			ax--;
			break;
		case 2: //우
			ay++;
			break;
		case 3: //하
			ax++;
			break;
		case 4: //좌
			ay--;
			break;
		}
		
	}
	private static void Bmov( int i) {
		switch (i) {
		case 0: //제자리
			break;
		case 1: //상
			bx--;
			break;
		case 2: //우
			by++;
			break;
		case 3: //하
			bx++;
			break;
		case 4: //좌
			by--;
			break;
		}
		
	}

	private static void updateMap(int[][][] map, int bcx, int bcy, int bccharge, int bcprocess, int beaconCnt) {
		//비콘 영역만큼 업데이트하는데 최대, 차선값 고려
		
		//비콘 8개 다 계산해봐야 800개임. 그냥 하자
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				int len = Math.abs(bcx - i) + Math.abs(bcy - j);
				if(len <= bccharge) {
					map[i][j][beaconCnt] = bcprocess;
					map[i][j][0]++;
				}
			}
		}
		
	}

	
	
	
}
