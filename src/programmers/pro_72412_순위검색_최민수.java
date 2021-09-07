package programmers;

import java.util.ArrayList;
import java.util.StringTokenizer;

// 입력값 〉	["java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"], ["java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"]
// 기댓값 〉	[1, 1, 1, 1, 2, 4]

//[카카오 블라인드] 순위검색
//https://programmers.co.kr/learn/courses/30/lessons/72412?language=java
public class pro_72412_순위검색_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		// System.setIn(new FileInputStream("res/baekjoon/bj_input_0000"));
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// br.close();
	}
	
	class Solution {
		public int[] solution(String[] info, String[] query) {

			int[] answer = new int[query.length];

			//각각 cpp, java, python
			int[] language = { 0, 8, 16 };
			//각각 backend, frontend
			int[] jobGroup = { 0, 4 };
			//각각 junior, senior
			int[] career = { 0, 2 };
			//각각 chicken, pizza
			int[] soulFood = { 0, 1 };
			
			ArrayList<Integer>[] list = new ArrayList[24];
			for (int i = 0; i < list.length; i++) {
				list[i] = new ArrayList();
			}

			for (int i = 0; i < info.length; i++) {
				StringTokenizer st = new StringTokenizer(info[i], " ");
				//몇번째 list에 들어갈지 계산
				int index = 0;
				String temp = st.nextToken();
				if (temp.equals("java")) {
					index += language[1];
				} else if (temp.equals("python")) {
					index += language[2];
				}

				temp = st.nextToken();
				if (temp.equals("frontend")) {
					index += jobGroup[1];
				}

				temp = st.nextToken();
				if (temp.equals("senior")) {
					index += career[1];
				}

				temp = st.nextToken();
				if (temp.equals("pizza")) {
					index += soulFood[1];
				}

				list[index].add(Integer.parseInt(st.nextToken()));
			}

			// for (int i = 0; i < list.length; i++) {
			// 	System.out.println(list[i].toString());
			// }

			for (int i = 0; i < query.length; i++) {
				StringTokenizer st = new StringTokenizer(query[i], " ");
				int index = 0;
				String temp = st.nextToken();
				if (temp.equals("java")) {
					index += language[1];
				} else if (temp.equals("python")) {
					index += language[2];
				}
				st.nextToken(); // and
				temp = st.nextToken();
				if (temp.equals("frontend")) {
					index += jobGroup[1];
				}
				st.nextToken(); // and
				temp = st.nextToken();
				if (temp.equals("senior")) {
					index += career[1];
				}
				st.nextToken(); // and
				temp = st.nextToken();
				if (temp.equals("pizza")) {
					index += soulFood[1];
				}

				int cutline = Integer.parseInt(st.nextToken());
				int smallAnswer = 0;
				for (int j = 0; j < list[index].size(); j++) {
					if (list[index].get(j) >= cutline) {
						smallAnswer++;
					}
				}
				answer[i] = smallAnswer;
			}

    	return answer;
    }
	}
}
