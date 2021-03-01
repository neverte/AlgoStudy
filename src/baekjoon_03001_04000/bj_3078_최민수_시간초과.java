package baekjoon_03001_04000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//[골드 3] 좋은 친구
//https://www.acmicpc.net/problem/3078
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_3078_최민수_시간초과 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_3078_input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//학생 수 N명( 3~300,000), 등수차 K(1~N)
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		//2K+1길이의 큐
		List<Integer> al = new ArrayList<Integer>();
		
		//정답
		int answer=0;
		
		//마지막에 줄어드는 케이스 비교 못한다.
		// al에 남아있는 K개 애들끼리 비교해야한다.
		// 그러면 입력을 K개를 더주자.
		// 따라서 i<N+K
		
		// 반학생의 이름이 성적순
		for (int i = 0; i < N+K; i++) {
			if(i<N) al.add(br.readLine().length());
			else al.add(0);
				
			if(al.size() == K*2+1) {
				//가운데 친구가 비교해야함.
				//가운데 친구 길이
				int center = al.get(K);
				for (int j = K+1; j < al.size(); j++) {
					if(center == al.get(j)) answer++;
				}
				//자기자신도 돌렸으니까
//				answer--;
				//하나 빼서 2K개로 유지
				al.remove(0);
			}else if(al.size() < K*2+1 && al.size() >= K+1){ //추가했는데도 길이보다 작을 때
				//K+1부터는 가장 첫원소가 비교대상,
				//길이가 4번 0번째 원소, 5면 1번, 6이면 2번째, 7부터는 위의 if조건에 걸림
				//al.get(0) ~ al.get(K) 까지 비교를한다.
				// i랑 같다.
				int center = al.get(i-K);
				for (int j = i-K+1; j < al.size(); j++) {
					//i-K를 해줘야 0번쨰, ~ i-K번쨰 숫자를 입력받는다.
					if(center == al.get(j)) answer++;
				}
				//자기자신도 돌렸으니까
				//answer--;
//				System.out.println(i+"@"+answer);
			}
			//else {
				//길이가 K+1이 될때까지는 비교 X
			//}
		}

//		System.out.println(answer/2);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(answer+"\n");
		bw.flush();
		bw.close();
//		System.out.println(answer);

		br.close();
	}
}
//시간초과 1. arr 검사 반으로 줄이기
//시간초과 2. syso 출력 bufferedWriter 사용
//시간초과 3. 세부적인 연산 효율 업
//시간초과 4. 입력받는걸 아예 길이로 바꿔서 넣기
//=> 코드 삭제