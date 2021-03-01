package baekjoon_02001_03000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[실버 5] 색종이
//https://www.acmicpc.net/problem/2563
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_2563_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		//System.setIn(new FileInputStream("res/baekjoon/bj_input_2563"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//색칠할 색종이 배열
		int black[] = new int[10000];
		
		//색종이 수
		int paper = Integer.parseInt(br.readLine());
		
		//정답
		int counter = 0;
		//색종이 좌표
		// 색종이가 도화지 밖으로 나가는 경우는 없다
		//가로, 세로의 크기가 각각 10인 정사각형 모양 색종이
		for (int i = 0; i < paper; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			//왼쪽변
			//black의 시작점 x ~ x+9
			int x = Integer.parseInt(st.nextToken());
			//아래쪽변
			//black의 시작점 99-y-8 ~ 99-y+1
			int y = Integer.parseInt(st.nextToken());
			
			//색칠하기
			for (int j = x; j <= x+9; j++) {
				for (int k = 99-y-8; k <= 99-y+1; k++) {
					if(black[j + k*100] == 1) continue;
					else {
						black[j + k*100] = 1;
						counter++;
					}
					
				}
			}
		}
		System.out.println(counter);
		
		br.close();
	}
}
