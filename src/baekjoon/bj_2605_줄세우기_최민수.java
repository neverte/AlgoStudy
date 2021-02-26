package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//[브론즈2] 줄 세우기
//https://www.acmicpc.net/problem/2605
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_2605_줄세우기_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_2605"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		List<Integer> line = new ArrayList<Integer>();
		
		//학생 수 N
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			//location 만큼 뽑아서 앞자리로 이동
			int location = Integer.parseInt(st.nextToken());
			line.add(location, i + 1);
		}
		
		//문제 잘못읽었는데 아무튼 맞음.
		for (int i = N-1; i>= 0; i--) {
			System.out.print(line.get(i) + " ");
		}
	
		br.close();
	}
}
