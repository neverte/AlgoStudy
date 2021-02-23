package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//[실버 1]쿼드 트리
//https://www.acmicpc.net/problem/1992
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_1992_최민수 {
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_1992"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//영상의 크기 N(1~64, 언제나 2의 제곱수)
		int N = Integer.parseInt(br.readLine());
		int map[][] = new int[N][N];
		
		//문자열 입력
		for (int i = 0; i < N; i++) {
			String[] temp = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}
		//분할정복
		// 2^i 칸이 모두 0또는 1이면 1로 반환
		// 그렇지 않으면 또 분할 정복
		divCon(map, N);
		System.out.println(sb);
		
		
		br.close();
	}
	
	public static void divCon(int[][] arr, int length) {
		//모두 0 또는 1인지 확인
		int chkVal = arr[0][0];
		boolean isSame = true;
		Loop: for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if(length == 1) {
					break Loop;
				}
				if(chkVal != arr[i][j]) {
					sb.append("(");
					isSame = false;
					int[][] divMap = new int[length/2][length/2];
					//1,2,3,4 값을 나눠서 배열을 넘겨줘야함.
					//1
					for (int k = 0; k < length/2; k++) {
						for (int l = 0; l < length/2; l++) {
							divMap[k][l] = arr[k][l];
						}
					}
					divCon(divMap, length/2);
					//2
					for (int k = 0 ; k < length/2; k++) {
						for (int l = length/2; l < length; l++) {
							divMap[k][l-length/2] = arr[k][l];
						}
					}
					divCon(divMap, length/2);
					//3
					for (int k = length/2; k < length; k++) {
						for (int l = 0; l < length/2; l++) {
							divMap[k-length/2][l] = arr[k][l];
						}
					}
					divCon(divMap, length/2);
					//4
					for (int k = length/2; k < length; k++) {
						for (int l = length/2; l < length; l++) {
							divMap[k-length/2][l-length/2] = arr[k][l];
						}
					}
					divCon(divMap, length/2);
					sb.append(")");
					break Loop;
				}
			}
		}
		//다돌았는데 0또는 1이야.
		if(isSame) sb.append(chkVal);
	}
}
