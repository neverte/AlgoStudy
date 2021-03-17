package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

//[골드 4] 드래곤 커브
//https://www.acmicpc.net/problem/15685
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_15685_드래곤커브_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_15685"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//K(K > 1)세대 드래곤 커브는 
		//K-1세대 드래곤 커브를 끝 점을 기준으로 90도 시계 방향 회전 시킨 다음, 그것을 끝 점에 붙인 것
		
		// 끝 점이란 시작 점에서 선분을 타고 이동했을 때, 가장 먼 거리에 있는 점
		
		//입력으로 주어지는 드래곤 커브는 격자 밖으로 벗어나지 않는다.
		//드래곤 커브는 서로 겹칠 수 있다.
		
		//드래곤 커브의 개수 n 1~20
		int n = Integer.parseInt(br.readLine());
		map = new int[101][101];
		//[i][0] 시작점 x, [1] 시작점 y, [2] 시작방향, [3] 세대
		//시작방향: 0오른쪽, 1위쪽, 2왼쪽, 3아래쪽
		//x, y 1~100, 세대 g 0~10
		int curve[][] = new int[n][4];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			curve[i][1] = Integer.parseInt(st.nextToken());
			curve[i][0] = Integer.parseInt(st.nextToken());
			curve[i][2] = Integer.parseInt(st.nextToken());
			curve[i][3] = Integer.parseInt(st.nextToken());
		}
		
		//아이디어
		//각각의 드래곤 커브에 대해 세대 단위로 재귀적으로 연산을 시행한다.
		//연산의 구성은
		//1. 끝 점을 기준으로 90도 돌리기
		//2. 새로이 생긴 커브를 저장하기 <<덱에 저장한다?
		//3. 끝 점 갱신하기.
		for (int i = 0; i < n; i++) {
			ArrayDeque<int[]> dq = new ArrayDeque<int[]>();
			int[] temp = new int[2];
			temp[0] = curve[i][0];
			temp[1] = curve[i][1];

//			System.out.println(curve[i][0] +",@"+ curve[i][1]);
//			System.out.println(temp[0] +",@"+ temp[1]);
			dq.offer(temp);
			int[] temp2 = new int[2];
			temp2[0] = temp[0];
			temp2[1] = temp[1];
			if(curve[i][2] == 0) { //우
				temp2[1] = curve[i][1] + 1;
				
			}else if(curve[i][2] == 1) { //상
				temp2[0] = curve[i][0] - 1;
				
			}else if(curve[i][2] == 2) { // 좌
				temp2[1] = curve[i][1] - 1;
				
			}else { //하
				temp2[0] = curve[i][0] + 1;
			}
			dq.offer(temp2);
			//시작점, 끝점을 dq에 넣은 뒤 시작
			
			makeCurve(curve, dq, curve[i][3], i);
		}
		
		//정사각형 찾기
		int answer = 0;
		for (int i = 0; i < 99; i++) {
			for (int j = 0; j < 99; j++) {
				if(map[i][j] == 1 && map[i+1][j] == 1 && map[i][j+1] == 1 && map[i+1][j+1] == 1) answer++;
			}
		}
//		for(int[] ii: map)System.out.println(Arrays.toString(ii));
		System.out.println(answer);
		br.close();
	}

	static int map[][];
	
	private static void makeCurve(int[][] curve, ArrayDeque<int[]> deque, int leftGen, int curveNum) {
		//내가 추적해야 되는게 끝점(다음 기준점), 다음 끝점(0, 0이 계속 이동하는게 끝점이다)
		int[] start = new int[2];
		
		if(leftGen == 0) {
			while(!deque.isEmpty()) {
				int[] t = deque.poll();
				map[t[0]][t[1]] = 1;
//				for(int[] ii: map)System.out.println(Arrays.toString(ii));
			}
			return;
		}
		//q에있는걸 다 꺼내서 세대횟수만큼 재귀를 돌릴꺼다.
		
		//끝 점은 항상 덱의 마지막에 있을 것이다.
		//뽑아서 기준점으로 삼음.
		int[] temp = deque.pollLast();
		int pointx = temp[0];
		int pointy = temp[1];
		
		//기준점을 기준으로 시계방향으로 90도 돌린다.
		//큐에 남아있는만큼 반복
		int repeat = deque.size();

		//반복 횟수 정했으니까 끝점도 덱에 넣을꺼다
		deque.offer(temp);
		for (int i = 0; i < repeat; i++) {
			int[] temp2 = new int[2];
			temp2 = deque.poll();
			int targetx = temp2[0];
			int targety = temp2[1];
			deque.offer(temp2); //방금 뽑은 것도 빼먹지 않고 넣기
			//temp[0]랑 [1]을 90도로 꺾어서 덱에 넣는다.
			//(a,b) 옮길 타켓, (c, d) 기준점
			//타겟 - 기준점을 해서 기준점을 원점으로 만든 후에 계산 다하고 기준점 다시 더해주기
			//원점과 (a-c, b-d)
			//(b-d, c-a)에서 원점 더해주기
			//(b-d+c), (c-a+d)
			int[] temp3 = new int[2];
			temp3[0] = targety - pointy + pointx;
			temp3[1] = pointx - targetx + pointy;
			
//			System.out.println(temp[0] +",기준점 "+temp[1]);
//			System.out.println(temp2[0] +",옮길꺼 "+temp2[1]);
//			System.out.println(temp3[0] +",옮긴거 "+temp3[1]);
//			System.out.println();
			
			if(targetx == curve[curveNum][0] && targety == curve[curveNum][1]) {
				start[0] = temp3[0];
				start[1] = temp3[1];
				if(i != repeat - 1) continue; //시작점이면서 마지막원소이면
			}
			
			if(i != repeat - 1) deque.offer(temp3);
			if(i == repeat - 1) deque.offer(start);
		}
		makeCurve(curve, deque, leftGen-1, curveNum);
		
	}
}
