package swea_etc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_모의_4013_특이한자석_최민수 {
	
	static ArrayList<Integer> cog[] = new ArrayList[4];
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_모의_4013.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//테케, T
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			//톱니 8개, 톱니바퀴 4개, 일렬, 각 톱니는 N or S극
			//톱니바퀴는 가장왼쪽부터 1~4번 번호 가지고 있음
			//돌기전에 맞닿은 부분의 극이 다르면 반대방향으로 돔
			
			//아이디어
			//각 톱니바퀴를 ArrayList로 구현해서 시계/반시계 돌리는거 다 버팀
			//(deque의 경우에 3, 9시 톱니에 접근할 수 없다.)
			//돌리는거 따로 method로 제작
			
			//입력
			//회전 횟수 k = 1~100
			int k = Integer.parseInt(br.readLine());
			
			//0은 N극, 1은 S극
			//cog1234를 배열로 하나로 묶음
			for (int i = 0; i < 4; i++) {
				cog[i] = new ArrayList<Integer>();
			}
			//0번 원소가 12시. 2가 3시, 6이 9시
			StringTokenizer st;
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 8; j++) {
					cog[i].add(Integer.parseInt(st.nextToken()));
				}
			}

			
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int spin = Integer.parseInt(st.nextToken()) - 1; //1~4를 0~3으로 만들기 위해
				int clock = Integer.parseInt(st.nextToken());
				//돌리는 함수
				if(clock == 1) spinClockwise(spin, 0);
				else spinCounterClockwise(spin, 0);
			}
			
			//출력
			//12시가 N이면 0점, S이면 1-1, 2-2, 3-4, 4-8점
			int answer = 0;
			for (int i = 0; i < 4; i++) {
				if(cog[i].get(0) == 1) {
					answer += Math.pow(2, i);
				}
			}
			
			System.out.println("#"+(tc+1)+" "+answer);
		}
		br.close();
	}

	
	//시계방향 회전(spin번째 톱니바퀴)
	private static void spinClockwise(int spin, int first) {
		//돌리기 전에 양옆 톱니바퀴 회전
		//x번째면 ..., x-1, x+1,...번쨰 톱니바퀴의 회전여부 파악후 함수 호출
		if(first == 0) {
			//한번에 회전시킬 목록
			//[i] = i번쨰 톱니바퀴 회전방향, 1 시계, -1 반시계
			int spinList[] = new int[4];
			
			//spin 번째 왼쪽에 돌릴 톱니바퀴 있는지 체크
			int centerCog = spin;
			spinList[centerCog] = 1;
			for (int i = centerCog; i >= 0; i--) {
				//centerCog왼쪽 톱니바퀴가 있다면
				if(i-1 >=0) {
					//NS극 체크
					//N-N = 0, N-S = -1, S-N = 1, S-S = 0
					if(cog[i-1].get(2) != cog[i].get(6)) {
						//다르니까 centerCog-i를 centerCog의 반대방향으로 돌려야함.
						spinList[i-1] = -spinList[i];
					}else {
						break;
					}
				}
			}
			
			//spin번째 오른쪽에 돌릴 톱니바퀴 있는지 체크
			centerCog = spin; //초기화
			for (int i = centerCog; i < 4; i++) {
				if(i+1 < 4) { //centerCog 오른쪽 톱니바퀴가 있다면
					//NS극 체크
					if(cog[i].get(2) != cog[i+1].get(6)) {
						//다르니까 centerCog+i를 centerCog의 반대방향으로 돌려야함.
						spinList[i+1] = -spinList[i];
					}else {
						break;
					}
				}
			}
			
			//spinList에 있는 애들 다 회전
			for (int i = 0; i < spinList.length; i++) {
				if(spinList[i] == 1) {
					spinClockwise(i, -1); //-1 위의 if문 처리 안할것이다.
				}else if(spinList[i] == -1) {
					spinCounterClockwise(i, -1); //-1 위의 if문 처리 안할것이다.
				}
			}
		}else { //first가 아닌 애들은 ArrayList회전할것이다.
			//12시가 1시로 간다. 0번원소가 1번원소로 간다. 마지막 원소가 0번 원소로 온다.
			cog[spin].add(0, cog[spin].remove(7));
		}
	}

	//반시계 방향 회전
	private static void spinCounterClockwise(int spin, int first) {
		//돌리기 전에 양옆 톱니바퀴 회전
		//x번째면 ..., x-1, x+1,...번쨰 톱니바퀴의 회전여부 파악후 함수 호출
		if(first == 0) {
			//한번에 회전시킬 목록
			//[i] = i번쨰 톱니바퀴 회전방향, 1 시계, -1 반시계
			int spinList[] = new int[4];
			
			//spin 번째 왼쪽에 돌릴 톱니바퀴 있는지 체크
			int centerCog = spin;
			spinList[centerCog] = -1;
			for (int i = centerCog; i >= 0; i--) {
				if(i - 1 >= 0) { //centerCog왼쪽 톱니바퀴가 있다면
					//NS극 체크
					//N-N = 0, N-S = -1, S-N = 1, S-S = 0
					if(cog[i-1].get(2) != cog[i].get(6)) {
						//다르니까 centerCog-i를 centerCog의 반대방향으로 돌려야함.
						spinList[i-1] = -spinList[i];
					}else {
						//옆이 같은 극이다 = 더이상 이 방향은 돌리면 안된다.
						break;
					}
				}
			}
			
			//spin번째 오른쪽에 돌릴 톱니바퀴 있는지 체크
			centerCog = spin; //초기화
			for (int i = centerCog; i < 4; i++) {
				if(i+1 < 4) { //centerCog 오른쪽 톱니바퀴가 있다면
					//NS극 체크
					if(cog[i].get(2) != cog[i+1].get(6)) {
						//다르니까 centerCog+i를 centerCog의 반대방향으로 돌려야함.
						spinList[i+1] = -spinList[i];
					}else {
						//옆이 같은 극이다 = 더이상 이 방향은 돌리면 안된다.
						break;
					}
				}
			}
			
			//spinList에 있는 애들 다 회전
			for (int i = 0; i < spinList.length; i++) {
				if(spinList[i] == 1) {
					spinClockwise(i, -1); //-1 위의 if문 처리 안할것이다.
				}else if(spinList[i] == -1) {
					spinCounterClockwise(i, -1); //-1 위의 if문 처리 안할것이다.
				}
			}
		}else { //first가 아닌 애들은 ArrayList회전할것이다.
			//12시가 11시로 간다. 0번원소가 7번원소로 간다. 0번원소를 빼서 마지막에 넣어준다.
			cog[spin].add(cog[spin].remove(0));
		}
	}
}
