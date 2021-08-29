package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[골드 3] 경사로
//https://www.acmicpc.net/problem/14890
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_14890_경사로_최민수 {
	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_14890"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 지도 한 변의 크기 n 6~20
		// 경사로의 길이 x 2~4
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

		// 구현
		// 우선 map을 찢어서 줄 것이다.
		int[] sub = new int[n];
		// 정답을 저장할 곳
		int answer = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sub[j] = map[i][j];
			}
			answer += build(sub, x);
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sub[j] = map[j][i];
			}
			answer += build(sub, x);
		}

		System.out.println(answer);
		br.close();
	}

	// 아이디어
	// 높이가 그대로면 straight증가
	// 높이가 증가하면 이전단계(straight)에 슬로프 건설 (불가면 return 0)
	// 높이가 감소하면
	// 같은 자리에 건설 불가
	// 높이 2 건설 불가
	private static int build(int[] sub, int slopeLen) {
		int beforeVal = sub[0];
		int straight = 1;
		boolean needBuild = false;

		for (int i = 1; i < sub.length; i++) {
			// 1. 높이 차가 2이상 나는 경우
			if (Math.abs(sub[i] - beforeVal) >= 2)
				return 0;

			// 2. 높이가 같은 경우
			else if (sub[i] == beforeVal) {
				straight++;
				beforeVal = sub[i];
			}

			// 3. 높이가 커진 경우
			else if (sub[i] > beforeVal) {
				// 1. straight가 slope 짓기에 충분하다
				if (straight >= slopeLen) {
					straight = 1; // 올라간 한칸
					beforeVal = sub[i];
				}
				// 2. 충분하지 않다
				else
					return 0;
			}

			// 4. 높이가 낮아진 경우
			else if (sub[i] < beforeVal) {
				straight = 0; // 이전까지 체크하던 것이 의미없다.
				needBuild = true;
				// 한번에 지을 수 있는지 확인하러 간다
				for (int j = i; j < sub.length; j++) {
					if (sub[i] == sub[j]) {
						straight++;
						beforeVal = sub[i];
					} else { // 또 높이가 변했다. 근데 아직 슬로프 못지었다
						return 0;
					}

					if (straight == slopeLen) {
						straight = 0; // 방금까지 확보한 공간을 슬로프에 다썼다.
						beforeVal = sub[i];
						i = j;
						needBuild = false;
						break;
					}

				}
			}
		}
		if (needBuild)
			return 0;
		return 1;
	}

}
