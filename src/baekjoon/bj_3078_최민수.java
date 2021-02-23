package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//[골드 3] 좋은 친구
//https://www.acmicpc.net/problem/3078
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_3078_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_3078_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//학생 수 N명( 3~300,000), 등수차 K(1~N)
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		//이름 길이는 최대 20이다.
		//[i]길이를 가진 갯수 넣을 것임.
		int nameLeng[] = new int[21];
		
		//이름 길이 넣을 것
		Queue<Integer> nameList = new LinkedList<Integer>();
		
		//int 범위를 초과한다.
		long answer = 0;
		
		//비교과정 설명
		// queue에 원소를 넣을 때, 그 원소 길이 번째 숫자를 늘린다.
		// nameLeng[원소길이]++
		// AA라는 이름이면 2글자짜리가 현재 1개 늘었다는 뜻
		// queue갯수를 K개로 유지함.
		for (int i = 0; i < N; i++) {
			int nl = br.readLine().length();
			//학생의 이름 길이
			if(nameList.size()>K) {
				//가장 앞 원소를 빼면서 그 길이를 제거.
				nameLeng[nameList.poll()]--;
			}
			answer += nameLeng[nl]++;
			nameList.add(nl);
		}
		System.out.println(answer);
		br.close();
	}
}