package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[실버 1] 경비원
//https://www.acmicpc.net/problem/2564
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_2564_경비원_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_2564"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//블록 가로, 세로 1~100
		StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
		int width = Integer.parseInt(st1.nextToken());
		int height = Integer.parseInt(st1.nextToken());
		
		//상점의 수 1~100
		int N = Integer.parseInt(br.readLine());
		//[0]에는 동서남북, [1]에는 그 좌표, [2] x좌표, [3] y좌표
		
		//1은 블록의 북쪽, 2는 블록의 남쪽, 3은 블록의 서쪽, 4는 블록의 동쪽
		//북쪽 또는 남쪽에 위치한 경우 블록의 왼쪽 경계로부터의 거리
		//동쪽 또는 서쪽에 위치한 경우 블록의 위쪽 경계로부터의 거리
		int store[][] = new int[N+1][4];
		for (int i = 0; i < N+1; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
			//북동남서가 1234가 아니다. 북남서동이 1234. 따라서 상우하좌가 되도록 입력을 변경해서 넣기
			//2 -> 3, 3->4
			int temp = Integer.parseInt(st2.nextToken());
			if(temp == 2) store[i][0] = 3;				
			else if(temp == 3) store[i][0] = 4;
			else if(temp == 4) store[i][0] = 2;
			else store[i][0] = temp;
			store[i][1]  = Integer.parseInt(st2.nextToken());
			//북
			if(store[i][0] == 1) {
				store[i][2] = 0;
				store[i][3] = store[i][1];
			}else if(store[i][0] == 2) {
				store[i][2] = store[i][1];
				store[i][3] = width;
			}else if(store[i][0] == 3) {
				store[i][2] = height;
				store[i][3] = store[i][1];
			}else{
				store[i][2] = store[i][1];
				store[i][3] = 0;
			}
		}		
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			//상우하좌가 1234
			switch (Math.abs(store[i][0] - store[N][0])) {
			case 0: //같은 구역
				answer += Math.abs(store[N][1] - store[i][1]);
				break;
			case 1: //한칸 떨어진 구역(4, 3), (3, 2) 등
				//한칸 떨어진 구역은 x좌표끼리 빼고, y좌표끼리 뺀 값의 절대값을 더하면 된다.
				answer += Math.abs(store[N][2] - store[i][2]);
				answer += Math.abs(store[N][3] - store[i][3]);
				break;
			case 3://한칸 떨어진 구역 (1, 2), (2, 3) 등
				answer += Math.abs(store[N][2] - store[i][2]);
				answer += Math.abs(store[N][3] - store[i][3]);
				break;
			case 2: //반대편 (2, 4) (1, 3)
				//반대편은 (북,남 기준) 세로 길이 더하고, 왼쪽 부분 < 오른쪽 부분 비교해서 더 작은거 더하기
				if(store[N][0] == 1 || store[N][0] == 3) {
					answer += height;
					int left = store[N][1] + store[i][1];
					int right = ((width - store[N][1]) + (width - store[i][1]));
					answer += (left > right) ? right : left;
				}else {
					answer += width;
					int left = store[N][1] + store[i][1];
					int right = ((width - store[N][1]) + (width - store[i][1]));
					answer += (left > right) ? right : left;
				}
				break;
			}
		}
		System.out.println(answer);
		br.close();
	}
}
