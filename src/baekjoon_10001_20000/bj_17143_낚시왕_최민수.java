package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

//[골드 2] 낚시왕
//https://www.acmicpc.net/problem/17143
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_17143_낚시왕_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_17143"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//격자판의 크기 R, C: 2~100
		//상어의 수 M 0~r*c
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		ArrayList<shark> sharkList = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int speed = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken()) - 1;
			//거리는 해당 방향의 (길이-1)*2마다 반복된다.
			if(direction <= 1) {
				speed %= (r-1)*2;
			}else {
				speed %= (c-1)*2;
			}
			int size = Integer.parseInt(st.nextToken());
			sharkList.add(new shark(x, y, speed, direction, size));
		}
		//1. 낚시왕 이동
		//2. 상어 1마리 낚기
		//3. 상어 이동
		
		int score = 0;
		for (int i = 0; i < c; i++) {
			//i가 낚시왕의 위치
			
			//1(B) 상어 위치 추적해보자.
			
			//2. 상어 1마리 낚기
			//2-1. 상어들을 지상에 가까운 순으로 정렬
			Collections.sort(sharkList);
			//2-2. 이 중에 i가 일치하는 상어 낚기
			for (int j = 0; j < sharkList.size(); j++) {
				if(sharkList.get(j).y == i) {
					score += sharkList.get(j).size;
					sharkList.remove(j);
					break;
				}
			}
			
			//3. 상어 이동
			//3-1. 이동
			for (int j = 0; j < sharkList.size(); j++) {
				move(sharkList, j, r, c);
			}
			
			//3-2. 포식
			//x, y 기준으로 정렬되어있음.
			Collections.sort(sharkList);
			
			//같은 위치면 작은것을 삭제한다.
			//삭제한 뒤의 index가 흐트러짐.
			//그러면 뒤에서부터 보자.
			for (int j = sharkList.size() - 1; j > 0; j--) {
				//위치가 같으면
				if(sharkList.get(j).x == sharkList.get(j-1).x && sharkList.get(j).y == sharkList.get(j-1).y) {
					//j가 j-1보다 사이즈가 큰 경우
					if(sharkList.get(j).size > sharkList.get(j-1).size) {
						sharkList.remove(j-1);
					}
					else sharkList.remove(j);
				}
			}
		}
		
		System.out.println(score);
		
		br.close();
	}
	
	static int[] dx = {-1, 1, 0, 0}; //상, 하, 우 좌
	static int[] dy = {0, 0, 1, -1}; //상, 하, 우 좌
	static int[] reverse = {1, 0, 3, 2};
	
	private static void move(ArrayList<shark> sharkList, int j, int r, int c) {
		shark now = sharkList.get(j);
		
		//d가 0인 경우는 위, 1인 경우는 아래, 2인 경우는 오른쪽, 3인 경우는 왼쪽
		for (int i = 0; i < now.speed; i++) {
			int x = now.x + dx[now.direction];
			int y = now.y + dy[now.direction];
			
			//범위를 초과했나?
			//그러면 방향 변경
			if(x < 0 || x >= r || y < 0 || y >= c) {
				now.direction = reverse[now.direction];
			}
			
			now.x += dx[now.direction];
			now.y += dy[now.direction];
		}
		
	}

	static class shark implements Comparable<shark>{
		int x, y, speed, direction, size;

		public shark(int x, int y, int speed, int direction, int size) {
			super();
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.direction = direction;
			this.size = size;
		}

		//지상에 가까운 순으로 상어 정렬
		@Override
		public int compareTo(shark o) {
			int temp = Integer.compare(this.x, o.x);
			
			if(temp != 0) return temp;
			else return Integer.compare(this.y, o.y); 
		}
		
	}
}
