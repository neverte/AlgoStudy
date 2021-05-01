package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//[실버3] 13일의 금요일
//https://www.acmicpc.net/problem/16463
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_16463_13일의금요일_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_16463"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//N년
		int n = Integer.parseInt(br.readLine());
		int year = 2019;
		//31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
		
//		400배수 연도는 윤년
//		400의 배수가 아니면서 100의 배수인 연도는 윤년이 아니다.
//		100의 배수가 아니면서 4의 배수인 연도는 윤년이다.
//		그 외의 연도는 윤년이 아니다.
		
		
		int month = 1;
		int day = 4;
		
		int answer = 0;
		
		//2019년 1월 1일은 화요일이다. = 1월 4일
		//normal의 N, leap의 L
		int Nmonth[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int Lmonth[] = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		//1. 올해가 윤년인지 아닌지 확인해서 2월달 날짜 조정
		//윤년이면 true
		boolean isLeapYear = false;
		for (int i = 2019; i <= n; i++) {
			year = i;
			if(year % 400 == 0) isLeapYear = true;
			else if(year % 100 == 0) isLeapYear = false;
			else if(year % 4 == 0) isLeapYear = true;
			else isLeapYear = false;
			//2. 매달 13일이 금요일인지 아닌지 체크
			//금요일마다 7을 더해서 13일인지 확인
			while(month < 13) {
				if(day == 13) answer++;
				day += 7;
				if(isLeapYear) {
					if(day > Lmonth[month]) {
						day -= Lmonth[month];
						month++;
					}
				}else {
					if(day > Nmonth[month]) {
						day -= Nmonth[month];
						month++;
					}
				}
			}
			month = 1;			
		}
		
		System.out.println(answer);
		
		br.close();
	}
}
