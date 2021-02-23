package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//[실버 4] 스위치 켜고 끄기
//https://www.acmicpc.net/problem/1244
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class Main_bj_1244_스위치켜고끄기_구미_4_최민수 {
	//1이면 0으로, 0이면 1로
	static String change(String num) {
		if(num.equals("1")) return "0";
		return "1";
	}
	
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_1244_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		//1. 스위치 개수 1~100
		int swNum = Integer.parseInt(br.readLine());
		//2. 각 스위치의 상태
		String[] arr = new String[swNum]; //String보다 int가 더 빠르다@@@
		arr = br.readLine().split(" "); //split보다 StringTokenizer가 더 빠르다@@@
		//3. 학생 수
		int stuNum = Integer.parseInt(br.readLine());
		//4. 성별, 받은 수
		for (int i = 0; i < stuNum; i++) {
			String[] onOff = br.readLine().split(" ");
			int startNum = Integer.parseInt(onOff[1]);
			if(onOff[0].equals("1")) {
				//남학생이면
				for (int j = startNum-1; j < swNum; j+=startNum) {
					arr[j] = change(arr[j]);
				}
			}else {
				//여학생이면
				for (int j = 0; j < swNum; j++) {
					int girlVal = Integer.parseInt(onOff[1])-1;
					//접근하는게 배열 범위 안인지 확인하고
					if(girlVal+j >=swNum || girlVal-j < 0 || girlVal >= swNum) break;
					// 양 옆이 같은지 확인
					if(arr[girlVal+j].equals(arr[girlVal-j])) {
						arr[girlVal+j] = change(arr[girlVal+j]);
						if(j == 0) continue;
						arr[girlVal-j] = change(arr[girlVal-j]);
					}else break;
				}
			}
		}

		for (int i = 0; i < arr.length; i++) {
			//스위치의 상태를 1번 스위치에서 시작하여 마지막 스위치까지 한 줄에 20개씩 출력한다
			if(i>=19 && (i+1) % 20 == 1) sb.append("\n");
			sb.append(Integer.parseInt(arr[i])).append(" ");
		}
		sb.setLength(sb.length()-1);
		System.out.print(sb);
	}
}
