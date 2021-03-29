package baekjoon_02001_03000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[골드 2] 양팔저울
//https://www.acmicpc.net/problem/2629
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_2629_양팔저울_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_2629"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//추의 개수 1~30
		int num = Integer.parseInt(br.readLine());
		
		//추의 무게
		int weight[] = new int[num];
		//StringTokenizer로 분리해서 입력 받음
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		//weight에다가 0~num-1번 추의 무게를 저정한다.
		for (int i = 0; i < weight.length; i++) {
			//StringTokenizer는 String값이므로 Integer로 변환
			weight[i] = Integer.parseInt(st.nextToken());
		}
		
		//각 추들은 3가지 선택지가 있다.
		//1. 저울에 안올라간다.
		//2. 저울 왼편에 올라간다(-)
		//3. 저울 오른편에 올라간다(+)
		//3^30 = 200조.
		//=> DP로 푼다?
		
		//A를 썼을 때의 가능ㅇ한 무게 정리
		//B를 썼을 때 가능한 무게 정리
		
		
		//확인하고자 하는 구슬들의 개수
		int checkNum = Integer.parseInt(br.readLine());
		//확인하고자 하는 구슬의 무게 목록
		int[] checkList = new int[checkNum];
		//입력을 나눠받기 위한 StringTokenizer
		st = new StringTokenizer(br.readLine(), " ");
		
		//구슬을 입력 받는다
		for (int i = 0; i < checkNum; i++) {
			//integer로 변환해 저장
			checkList[i] = Integer.parseInt(st.nextToken());
			//단 무게가 15000을 넘으면 -1로 따로 저장. 추 30 * 500g = 최대 15000이기 때문
			if(checkList[i] > 15000) checkList[i] = -1;
		}
		
		//dp를 할 것이다. 최대 추의 무게는 15000이기에 15001로 공간을 잡았다.
		//[i][j] : 0번째 추부터 j-1번 추, 즉 j개의 추를 사용하 였을 때 i무게를 만들 수 있는지 확인하고 저장할 것이다.
		int dp[][] = new int[15001][num+1];

		
		//dp를 채울 것이다. 추의 개수만큼 돌려야 한다.
		for (int i = 1; i < num+1; i++) { 
			//내 추만 하나 추가한 상황.
			dp[weight[i-1]][i] = 1; //0번추를 1번쨰 추가했을 때, 1번 추를 2번쨰에 추가했을 때
			
			//dp를 순회한다.
			for (int j = 1; j < dp.length; j++) {
				//이전에 추로 완성한 적이 있음
				if(dp[j][i-1] != 0) {
					//추를 양쪽 어디에도 추가 안한다.
					//아무것도 안함
					
					//왼편에 추가한다.
					if(j-weight[i-1] > 0) dp[j-weight[i-1]][i] = 1;
					
					//오른쪽 저울에 올리는 것
					if(j+weight[i-1] < 15001) dp[j+weight[i-1]][i] = 1;
					
					//좌우반전해서 추가
					if(weight[i-1] - j > 0) dp[weight[i-1] - j][i] = 1;

				}
			}
			//이전 단계 값을 지금 단계 값으로 옮겨준다.
			for (int j = 1; j < dp.length; j++) {
				if(dp[j][i-1] == 1) dp[j][i] = dp[j][i-1];
			}
		}

		
		//편한 출력을 위한 StringBuilder
		StringBuilder sb = new StringBuilder();
		//각각의 체크리스트를 돌면서 dp에 값이 있는지 확인할 것이다.
		for (int i = 0; i < checkList.length; i++) {
			//특수 케이스: 15000을 초과하는 경우
			if(checkList[i] == -1) sb.append("N ");
			//추로 만들 수 있는 경우
			else if(dp[checkList[i]][num] == 1) sb.append("Y ");
			//추로 만들 수 없는 경우
			else sb.append("N ");
		}
		
		//출력
		System.out.println(sb.toString());

		
		br.close();
	}

}

//