package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//[골드 5] 치킨 배달
//https://www.acmicpc.net/problem/15686
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_15686_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_15686"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//크기 N 2~50, M 1~13
		StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st1.nextToken());
		int M = Integer.parseInt(st1.nextToken()); //M개만큼 폐업 안시킬 것이다.
		
		//도시의 정보
		int[][] map = new int[N][N];
		ArrayList<int[]> home = new ArrayList<int[]>();
		ArrayList<int[]> chick = new ArrayList<int[]>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int temp = Integer.parseInt(st2.nextToken());
				if(temp == 1) home.add(new int[] {i,j}); //집 좌표 저장
				if(temp == 2) chick.add(new int[] {i,j}); //치킨집 좌표 저장
				map[i][j] = temp;
			}
		}
		//아이디어
		//전체 가게 중 M개를 선택한다.
		// 그 M개 중 각 가정집과의 치킨집사이의 거리가 최소가 되는 걸 찾자.
		
		//거리 구하기(직접 좌표로 계산.
		int[][] length = new int[home.size()][chick.size()];
		calcLength(home, chick, length);
		
		
		//조합에 대한 최소 거리 구하기.
		//nextPermutaion + 전처리로 조합 1개씩 받으면서 min값을 구하자
		int semiAnswer = 0;
		int answer = Integer.MAX_VALUE;
		
		//전처리
		int count = 0;
		//조합돌릴 치킨집
		int[] flag = new int[chick.size()];
		//조합으로 나온 치킨집
		ArrayList<Integer> aliveChick = new ArrayList<Integer>();
		
		//치킨집 전체 개수중 M개만큼 flag만듬. 00111 처럼.
		while(++count <= M) flag[flag.length-count] = 1;
		
		do {
			//치킨집 조합에서 치킨집이 뽑혔다.
			//초기화 필수
			aliveChick.clear();
			for (int i = 0; i < chick.size(); i++) {
				if(flag[i] == 1) {
					aliveChick.add(i);
				}
			}
			//1, 2번 치킨집이 뽑혔다고하면
			//1번 집 <-> 1, 2번 치킨집 중 가까운것을 min에 더하기.
//			집 개수만큼 반복
			int minLen;
			semiAnswer = 0;
			for (int j = 0; j < home.size(); j++) {
				minLen = Integer.MAX_VALUE;
				//j번째 집
				for (int i = 0; i < aliveChick.size(); i++) {
					//i번째 치킨집과의 거리가 최소인것을 전체 정답에 저장
					minLen = Math.min(length[j][aliveChick.get(i)], minLen);
				}
				semiAnswer += minLen;
			}
			answer = Math.min(semiAnswer, answer);
		} while (nextPerm(flag));

		System.out.println(answer);
		br.close();
	}
	
	public static void calcLength(ArrayList<int[]> house, ArrayList<int[]> chicken, int[][] length) {
		for (int i = 0; i < house.size(); i++) { //집 개수만큼
			for (int j = 0; j < chicken.size(); j++) {//치킨집 방문
				length[i][j] = Math.abs(house.get(i)[0] - chicken.get(j)[0]) + Math.abs(house.get(i)[1] - chicken.get(j)[1]);
			}
		}
	}
	
	public static boolean nextPerm(int[] arr) {
		int i = arr.length - 1;
		while(i > 0 && arr[i-1] >= arr[i]) i--;
		if(i==0) return false;
		
		int j = arr.length - 1;
		while(arr[i-1] >= arr[j]) j--;
		
		swap(arr, i-1, j);
		
		int k = arr.length -1;
		while(i < k) swap(arr, i++, k--);
		
		return true;
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
