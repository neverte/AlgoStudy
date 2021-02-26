package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//[실버 5] 크로아티아 알파벳
//https://www.acmicpc.net/problem/2941
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_2941_크로아티아알파벳_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_2941"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		char line[] = br.readLine().toCharArray();
		
		int count = 0;
		int leftNum = 0;
		//그 다음 문자가 =, -, z, j 인 경우 특별 취급
		for (int i = 0; i < line.length; i++) {
			if(line[i] == 'd' && i < line.length - 2 && line[i+1] == 'z' && line[i+2] == '=') {
				//dz=
				i += 2;
				count++;
			}else if((line[i] == 'c' || line[i] == 's' || line[i] == 'd' || line[i] == 'z') && i < line.length - 1 && (line[i+1] == '=' || line[i+1] == '-')) {
				//c=, c-, s=, d-, z=
				i++;
				count++;
			}else if(line[i] == 'l' || line[i] == 'n') {
				if( i < line.length - 1 && line[i+1] == 'j') {
					// nj, lj
					i++;
					count++;
				}else {
					count++;
				}

			}else {
				count++;
			}
			leftNum = line.length - i - 1;
			
		}
		count += leftNum;
		System.out.println(count);
		br.close();
	}
}
