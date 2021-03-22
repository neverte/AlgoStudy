package jungol;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//해밀턴 순환회로
//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=954&sca=99&sfl=wr_hit&stx=1681
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class jo_해밀턴순환회로_1681 {

	public static void main(String[] args) throws IOException {
		//테스트 입력
		System.setIn(new FileInputStream("res/jungol/jo_input_1681"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//배달해야 하는 장소의 수 n 1~12
		int n = Integer.parseInt(br.readLine());
		
		int adjArr[][] = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				adjArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//회사에서 출발 -> 모든 장소 다 둘러봄 -> 다시 회사로 돌아오기
		boolean visited[] = new boolean[n];
		dfs(adjArr, visited, 0, n-1, 0, 0);
		
		System.out.println(answer);
		
		br.close();

	}

	static int answer = Integer.MAX_VALUE;

	//cnt: 현재까지 방문한 개수
	//now: 지금 위치하고 있는 장소
	private static void dfs(int[][] adjArr, boolean[] visited, int cnt, int end, int totalCost, int now) {
		//가지치기
		if(totalCost >= answer) return;
		
		if(cnt == end) {
			if(adjArr[now][0] == 0) return; //회사로 이어지는 길이 없을 수도 있다.
			//0번은 회사니까 마지막에 방문
			int temp = totalCost+adjArr[now][0];
//			System.out.println(temp);
			answer = Math.min(answer, temp);
			return;
		}
		
		//1~n-1번까지 방문 다하면 끝
		for (int i = 1; i < end+1; i++) {
			if(visited[i] || adjArr[now][i] == 0 ) continue; //방문한 곳이거나, 연결되지 않은 곳이면
			visited[i] = true;
			dfs(adjArr, visited, cnt+1, end, totalCost+adjArr[now][i], i);
			visited[i] = false;
		}
	}

}