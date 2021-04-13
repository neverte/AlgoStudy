package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_모의_4014_활주로건설_최민수 {
	
	static int N;
	static int synergy[][];
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_모의_4014.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//테케, T
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {

			//지도 한 변의 크기 n 6~20
			//경사로의 길이 x 2~4
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int[][] map = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			//구현
			//우선 map을 찢어서 줄 것이다.
			int[] sub = new int[n];
			//정답을 저장할 곳
			int answer = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					sub[j] = map[i][j];
				}
				answer += build(sub, x);
//				System.out.println(build(sub, x));
//				System.out.println(Arrays.toString(sub));
//				System.out.println();
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					sub[j] = map[j][i];
				}
				answer += build(sub, x);
//				System.out.println(build(sub, x));
//				System.out.println(Arrays.toString(sub));
//				System.out.println();
			}

			System.out.println("#"+(tc+1)+" "+answer);
		}
		br.close();
	}
	
	//아이디어
	//활주로 한쪽끝에서부터 현재 지형의 높이, 해당 높이가 이어진 횟수를 기록하면서 온다
	//높이가 달라진 것을 마주하면 2가지 경우가 존재한다
	//1. 높이가 높아졌다 = 현재까지 이어진 횟수와 경사로 길이를 비교하여 설치 가능불가능 판정
	//2. 높이가 낮아졌다 = 경사로를 놓을 수 있을 때까지 낮아진 높이가 이어지는지 확인. 중간에 높이가 달라지면 안됨.
	private static int build(int[] sub, int slopeLen) {
		int value = sub[0];
		int straight = 1;
		boolean waitSlope = false;

		for (int i = 1; i < sub.length; i++) { // 1부터 시작
			if (!waitSlope) {// 공사 대기 중이 아닌 경우
				if (value == sub[i]) { // 이전 값이랑 일치하면
					straight++; // 연속값 증가
				} else if (value < sub[i]) { // 현재보다 지형이 높아졌다.
					//높이가 2이상 차이나는 경우
					if(sub[i] - value > 1) return 0;
					if (straight < slopeLen) { // 슬로프를 놓을 수 없다.
						return 0;
					} else { // 슬로프를 놓을 수 있다.
						value = sub[i];
						straight = 1;
					}
				} else { // 현재보다 지형이 낮아졌다.
					//높이가 2이상 차이나는 경우
					if(value - sub[i] > 1) return 0;
					waitSlope = true;
					straight = 1;
					value = sub[i];
				}
			} else { // 높이가 낮아져서 공사 대기중
				//높이가  다른게 올때
				if(value != sub[i]) {
					//이전에 설치를 못했는데 이번에 다른게 오면 설치 못한다.
					return 0;
				}else { //같은게 올때,
					straight++;
					if(straight == slopeLen) { //설치 완료
						waitSlope = false;
						straight = 0;
					}
				}
			}

		}
		//공사 다했는데 아직 경사로 대기중이면 0
		if(waitSlope) return 0;
		return 1;
	}
	
}
