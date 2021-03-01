package baekjoon_02001_03000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//[골드 5] 탑
//https://www.acmicpc.net/problem/2493
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_2493_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_2493_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	
		//탑의 수 N 1~50만
		int N = Integer.parseInt(br.readLine());
		//각 탑들의 높이 1~1억 int안
		List<int[]> q = new ArrayList<int[]>();
		
		//모든 탑의 레이저 송신기는 레이저 신호를 지표면과 평행하게 수평 직선의 왼쪽 방향으로 발사하고,
		//탑의 기둥 모두에는 레이저 신호를 수신하는 장치가 설치되어 있다.
		//하나의 탑에서 발사된 레이저 신호는 가장 먼저 만나는 단 하나의 탑에서만 수신이 가능하다. 
		
		//아이디어 1.
		//입력 받으면서 왼쪽으로 가면서 나보다 높은 탑이 있는지 확인
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		//가장 높은 타워 높이
		int max = 0;
		for (int i = 0; i < N; i++) {
			int tower = Integer.parseInt(st.nextToken());
			//추가 아이디어
			//지금 들어온 탑이, 왼쪽탑보다 높으면 왼쪽에 있는거 싹다 무시.
			//왜냐하면 앞으로 들어올 탑 입력을 얘가 못받으면 아무도 못받으니까.
			if(tower > max) {
				//얘를 받을 애강 없다.
				sb.append(0).append(" ");
				int[] te = {tower, i+1};
				q.add(te);
				max = tower;
			}else { //낮으면 내 왼쪽 값이 나보다 클때까지 검사.
				int[] te = {tower, i+1};
				q.add(te);
				for (int j = q.size()-2; j>=0 ; j--) {
					//j번째 원소가 지금 값보다 크냐? 
					//max까지 간다.
					if(q.get(j)[0] >= tower) {
//						System.out.println(q.get(j));
						sb.append(q.get(j)[1]).append(" ");
						break;
					}else {
						q.remove(j);
					}
				}
			}
			//System.out.println(q.toString());
		}		
		//출력
		// 각각의 탑들에서 발사한 레이저 신호를 수신한 탑들의 번호를 하나의 빈칸을 사이에 두고 출력한다.
		// 만약 레이저 신호를 수신하는 탑이 존재하지 않으면 0을 출력한다.
		sb.setLength(sb.length()-1);
		System.out.println(sb);
		
		br.close();
	}
}
