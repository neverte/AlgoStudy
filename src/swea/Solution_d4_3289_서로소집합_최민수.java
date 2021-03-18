package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 패키지 날리기
// class Solution으로 바꾸기
// 내부 입력 주석처리하기
public class Solution_d4_3289_서로소집합_최민수 {
	
	static int findSet(int s) {
		if(parent[s] == s) return s;
		return parent[s] = findSet(parent[s]);
	}
	
	static boolean union(int s, int t){
		int rootS = findSet(s);
		int rootT = findSet(t);
		if(rootS == rootT) return true;
		
		parent[rootT] = rootS;
		return false;
		
	}
	
	static int parent[];
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea/input_d4_3289.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		

		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#").append((tc+1)).append(" ");
			//n 1~100만, m 1~10만
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int oper = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				
				if(oper == 0) { //합집합
					union(a, b);
				} else { // 두 원소가 같은 집합인지 확인
					int rootA = findSet(a);
					int rootB = findSet(b);
					if(rootA == rootB) {
						sb.append(1);
					}else {
						sb.append(0);
					}
				}
			}
			System.out.println(sb);
			
		}//tc끝
		
		br.close();
	}

}
