package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기

public class Solution_샘플_1767_프로세서연결하기_구미4반_수업 {
	static int N, max, totalCnt, min, map[][];
	//상, 우, 하, 좌
	static int dr[] = {-1, 0, 1, 0};
	static int dc[] = {0, 1, 0, -1};
	
	static ArrayList<int[]> list; //가장자리 코어 제외, 고려해야하는 코어 담는 리스트
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_샘플_1767.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//테케 T
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			
			//1. 가장자리 코어 제외
			//2. 남은 코어 중 조합 구하기. 5C1, 5C2, ...
			//(1번 코어 -> 2번, 2번->1번 선택하나 같기 때문에 순열이다.
			//=>공집합뺴고 다해봐야되니까 2. 공집합 제외한 부분집합 구하기.
			//3. 코어를 1개 선택/비선택 상하좌우 중 가장 짧은 선 선택하기. (비선택이면 다음코어로)
			//4. 2번째 코어 선택/비선택, 상하좌우~...
			//전선이 놓아진 위치로 접근할 수 없기 때문에 생각보다 시간 복잡도가 낮음.
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			list = new ArrayList<int[]>();
			max = 0;
			min = Integer.MAX_VALUE;
			totalCnt = 0;
			
			for (int j = 0; j < N; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int k = 0; k < N; k++) {
					map[j][k] = Integer.parseInt(st.nextToken());
					if(j ==0 || k==0 || j==N-1 || k == N-1) continue; //가장자리 무시
					if(map[j][k] == 1) {
						list.add(new int[] {j, k});
						totalCnt++;
					}
					
				}
				
			}
			
			go(0, 0);
			System.out.println("#"+(i+1)+" "+min);
			
		}//테케 끝
		
		br.close();
	}

	//부분집합
	//index: 부분집합에 고려할 코어 인덱스,
	//cCnt: 가장자리까지 연결된 코어 개수
	private static void go(int index, int cCnt) {
		//가지치기, backtracking
		//연산해서 max못넘으면 패스
		if(cCnt + (totalCnt - index) < max) return;
		//기저조건, 종료조건
		//부분집합 다 따져봤다. 1번째 원소 선택/비선택, 2번째 ...
		if(index == totalCnt) {
			int res = getLength(); //놓아진 전선의 길이 구하기
			
			//최대 코어 개수보다 지금 연결한게 많으면 갱신
			if(max < cCnt) {
				max = cCnt;
				min = res; //전선길이도 새롭게 값 넣기
			}else if(max == cCnt) { //코어 개수가 같으면
				if(res < min) min = res;
			}
			
			return;
		}
		
		
		//코어 선택해서 전선 놓기(4방향으로 놓아봐야함)
		int[] cur = list.get(index);
		int r = cur[0];
		int c = cur[1];
		for (int d = 0; d < 4; d++) {
			//전선을 놓을 수 있는지 따라가보는 메소드
			//이 위치에서 이 방향으로 전선 놓는게 가능하니?
			if(isAvailable(r, c, d)) {
				//전선을 놓는다.
				setStatus(r, c, d, 2);
				//그리고 다음 코어(index+1)로 넘어갈 것이다. 
				go(index+1, cCnt+1);
				//다음 코어 갔다가 돌아왔다. 지금 4방향중 1곳에만 놓아본 상태이다.
				//다음 코어, 전선을 위해 놓었던 전선을 되돌려놔야 한다.
				//이것도 복잡하니까 메소드로 짤꺼임
				setStatus(r, c, d, 0);
			}
			
		}
		
		//코어 비선택
		go(index+1, cCnt);
	}

	//r, c에서 d방향으로 끝까지 갈 수 있느냐.
	private static boolean isAvailable(int r, int c, int d) {
		//계속 움직일꺼니까 따로 관리할 변수
		int nr = r, nc = c;
		while (true) {
			nr += dr[d];
			nc += dc[d];
			//배열 범위를 벗어났다 = 끝까지 갔다. = 연결됐다
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) break;
			//아직 안벗어났다.
			//이 자리가 전선을 놓을 수 있는지 확인해야 함
			//map에다가 전선을 놓으면 2로 marking할 것이다.
			//0:빈칸, 1:코어, 2:전선
			//1이나 2면 전선 못깐다.
			if(map[nr][nc] >= 1) return false;
		}
		return true; //전선 연결에 성공
	}
	
	//현재 위치 r,c 에서 d방향으로 s상태로 세팅해라
	private static void setStatus(int r, int c, int d, int s) {
		int nr = r, nc = c;
		while (true) {
			nr += dr[d];
			nc += dc[d];
			//끝까지 다 갔다.
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) break;
			//끝까지 다 못갔다.
			//s가 2면 전선깔기, 0이면 전선 치우기
			map[nr][nc] = s;		
		}
	}

	private static int getLength() {
		int ICnt = 0;
		//map에 전선은 2로 놓여있다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 2) ICnt++;
			}
		}
		
		return ICnt;
	}
	//가지치기 할 수 있는데 안 한 코드다.
}
