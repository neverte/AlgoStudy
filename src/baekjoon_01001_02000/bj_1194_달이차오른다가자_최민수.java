package baekjoon_01001_02000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

//[골드 1] 달이 차오른다, 가자.
//https://www.acmicpc.net/problem/1194
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_1194_달이차오른다가자_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_1194"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		//0: 민식이 현재 위치
		//1: 목적지
		//#: 벽
		//. : 빈곳
		//a~f : 열쇠
		//A~F : 문
		char[][] map = new char[n][m];
		int startx = 0, starty = 0;
		for (int i = 0; i < n; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				map[i][j] = temp[j];
				if(map[i][j] == '0') {
					startx = i;
					starty = j;
				} else if(map[i][j] == '1') {
					map[i][j] = '@'; // @로 저장
				}
			}
		}
		
		visited = new int[n][m][65]; //해당 키를 가지고 방문한적이 있다.
		//0은 획득한 key를 표현하는 6자리 2진수.
		
		//방문처리도 6자리 2진수로 가능은 할꺼같은데 일단 3차원배열로 해보자.
		bfs(startx, starty, map);
		
		//아이디어 3.
		//BFS탐색. 문, 열쇠 아무것도 못찾았는데 더이상 방문할 곳이 없으면 끝
		//열쇠를 찾으면 뭔가 리셋해줘야함
		//a번 열쇠를 찾은 상태에서 bfs를 다시 돌린다거나.
		//열쇠 소지여부는 a~f 6가지니까 6비트로 표현한다?
		//문을 만나면 현재 소지한 열쇠로 이동가능한지 판별하고
		//안되면 벽으로 인식
	
		if(min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min-1);
		
		br.close();
	}

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int[] pows = {1, 2, 4, 8, 16, 32};
	static int[][][] visited;
	static int min = Integer.MAX_VALUE;
	
	private static void bfs(int startx, int starty, char[][] map) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.offer(startx);
		q.offer(starty);
		q.offer(0); //key값
		//첫 위치 방문 체크
		visited[startx][starty][0] = 1;
		
		int n = map.length;
		int m = map[0].length;
		
		while(!q.isEmpty()) {
			int nowx = q.poll();
			int nowy = q.poll();
			int key = q.poll();
			
			//가지치기
			if(visited[nowx][nowy][key] >= min) break;
			
			//현재 위치가 달이면 끝
			if(map[nowx][nowy] == '@') {
				min = Math.min(min, visited[nowx][nowy][key]);
				return;
			}
			
			//자신 위치에서 상하좌우 탐색
			for (int i = 0; i < 4; i++) {
				int nextx = nowx + dx[i];
				int nexty = nowy + dy[i];
				int nextkey = key;
				
				//접근 못하는곳은 패스
				//0)배열 범위 밖
				if(nextx < 0 || nextx >= n || nexty < 0 || nexty >= m) continue;
				//1) 이 key값을 가지고 방문했던 곳
				if(visited[nextx][nexty][key] > 0) continue;
				//2) 벽
				if(map[nextx][nexty] == '#') continue;
				//3) 해당 열쇠가 없는 곳의 문
				//대문자 A는 65, F는 70이고, 
				else if((int)map[nextx][nexty] >= 65 && (int)map[nextx][nexty] <= 70) {
					//내 열쇠보유여부체크
					//65이면 1번쨰 자리가 1인지 확인, 0이면 통과불가 = 1과 & 연산
					//66이면 2번째 자리가 1인지 확인,                = 2랑 & 연산
					//70이면 6번째 자리가 1인지 확인
					int cipher = (int)map[nextx][nexty] - 65;
					//나중에 pow연산이 많다고 생각되어서 배열로 빼둠
					int bit = pows[cipher]; //1, 2, ~32
					
					//일치하는 키가 없으면
					if((key & bit) == 0) continue;
				}
				
				//현재 위치가 열쇠위치면 열쇠값 갱신
				//a = 97, f = 102
				if((int)map[nextx][nexty] >= 97 && (int)map[nextx][nexty] <= 102) {
					//keys값을 갱신하면서 재귀
					int cipher = (int)map[nextx][nexty] - 97;
					
					nextkey = key | pows[cipher];
				}
				
				//접근 가능한 곳은 큐에 넣기
				q.offer(nextx);
				q.offer(nexty);
				q.offer(nextkey);
				visited[nextx][nexty][nextkey] = visited[nowx][nowy][key] + 1;
			}
		}
	}
}

//일단 이동은 BFS, DFS 등을 쓸텐데
//아이디어 1. 거꾸로 생각해보자.
//달부터 이동하면서 문을 통과하고 해당 열쇠가 반드시 필요하다는 것을 기억한다.
//이렇게 이동하면서 시작점에 도달했는데, 해당 열쇠를 만나지 못했다면?

//아이디어 2.
//이동하면서 열쇠 먹으면 따로 저장
//각 문을 만났을 때 최소 이동거리 저장.
//출발지, 열쇠, 문 3요소를 고려하여 최단거리 선택