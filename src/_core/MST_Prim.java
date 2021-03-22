package _core;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//최소 신장 트리(MST, Minimum Spanning Tree)
public class MST_Prim {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/0318_prim_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] adjMatrix = new int[N][N];
		boolean[] visited = new boolean[N];
		int[] minEdge = new int[N];
		
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
			minEdge[i] = Integer.MAX_VALUE; //신장트리에 연결된 정점에서 자신으로의 최소간선비용
		}
		
		int result = 0;
		minEdge[0] = 0; //0을 시작 정점으로 처리하기 위해 0 세팅
		
		for (int c = 0; c < N; c++) {
			int min = Integer.MAX_VALUE;
			int minVertex = 0;
			//신장 트리에 연결되지 않은 정점 중 minEdge 비용이 최소인 정점
			for (int i = 0; i < N; i++) {
				if(!visited[i] && min > minEdge[i]) {
					min = minEdge[i];
					minVertex = i;
				}
			}
			//목표로 하는 정점 찾았다.
			result += min;
			visited[minVertex] = true;
			
			for (int i = 0; i < N; i++) {
				if(!visited[i] && adjMatrix[minVertex][i] != 0 && minEdge[i] > adjMatrix[minVertex][i]) {
					//minVertex에서 i로 오는게 더 유리한 경우
					minEdge[i] = adjMatrix[minVertex][i];
					
				}
			}
			
		}
		System.out.println(result);
	}

}
