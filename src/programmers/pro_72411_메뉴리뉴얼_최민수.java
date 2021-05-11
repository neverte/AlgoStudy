package programmers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

//[lv 2] 메뉴 리뉴얼
//https://programmers.co.kr/learn/courses/30/lessons/72411
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class pro_72411_메뉴리뉴얼_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
//		System.setIn(new FileInputStream("res/baekjoon/bj_input_72411"));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//코스요리 메뉴는 최소 2가지 이상의 단품메뉴
		//최소 2명 이상의 손님으로부터 주문된 단품메뉴 조합에 대해서만 코스요리 메뉴 후보에 포함
		
		//각 손님들이 주문한 단품메뉴들이 문자열 형식으로 담긴 배열 orders
		//"스카피"가 추가하고 싶어하는 코스요리를 구성하는 단품메뉴들의 갯수가 담긴 배열 course
		//"스카피"가 새로 추가하게 될 코스요리의 메뉴 구성을 문자열 형태로 배열에 담아 return 
		
		//정답은 각 코스요리 메뉴의 구성을 문자열 형식으로 배열에 담아 사전 순으로 오름차순 정렬해서 return 해주세요.
		//pq에 담는다?
		
//		System.out.println((int)'A'); //65
	
//		br.close();
	}
	PriorityQueue<String> nominate;
	
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        
        nominate = new PriorityQueue<>();
        
        //1. orders에 있는 것을 다 분해해서 26길이 짜리 배열에 저장할 것이다.
        int[][] alphabet = new int[orders.length][26];
        for (int i = 0; i < orders.length; i++) {
        	char[] temp = orders[i].toCharArray();
        	for (int j = 0; j < temp.length; j++) {
        		alphabet[i][(int)temp[j] - 65]++;
			}
		}
        
        //2. 이중 포문을 돌면서 길이가 course에 맞는지,
        //중복검사는 어떻게 하지. pq에서 꺼내면서 이전껄 저장해서 중복이면 카운트 안하는걸로 해보자
        StringBuilder sb = new StringBuilder();
        findComb(sb, alphabet, course, 0, 0);
        
        
        
        return answer;
    }

	private void findComb(StringBuilder sb, int[][] alphabet, int[] course, int courseIndex, int position) {
		//재귀로 들어왔는데, sb 길이가 course마지막원소 길이를 넘어섰다 = 더이상 고려할 필요가 없다.
		if(sb.length() > course[course.length - 1]) return;
		
		int x = position / 26;
		int y = position % 26;
		
		for (int i = x; i < alphabet.length; i++) {
			for (int j = y; j < 26; j++) {
				//값이 0이면 해당 문자열이 아니라는 것
				if(alphabet[i][j] == 0) continue;
				else {
					//문자열을 만났다.
					sb.append(alphabet[i][j]);
					//추가한 것이 코스 요리 메뉴 길이랑 같다면
					if(sb.length() == course[courseIndex]) {
						//정답 후보군에 넣는다.
						nominate.add(sb.toString());
						//다음 길이를 찾는다.
						findComb(sb, alphabet, course, courseIndex, x*26 + y);
					}
				}
			}
		}
		
	}
}

//아이디어 1: 가능한 모든 부분 문자열을 만들어서 2회 이상 포함되면 생성
//2: 각각의 문자를 카운팅해서 1회 이하인 문자는 조합 후보에조차 넣지 않음.

//3: 각각의 손님에 대해 메뉴 별로 연산을 한다.
//예를 들어 1번을 선택 -> A 선택 -> A 주문한 손님 후보군 뽑기 -> B 선택 -> A 후보군에서 B를 뽑은 사람 있는지 선택
//이런식으로 진행하는데, 갯수는 계속 늘려나가다가 일치하는 후보군이 없으면 해당 갯수를 유지하고 다음 후보로 넘어간다.
// ABC -> ABF -> ABG -> AC -> 이런식으로 지나간다.
//저장은 문자열 형태로 만들어서 