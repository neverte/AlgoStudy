package baekjoon_10001_20000;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[실버 3] 다음 순열
//https://www.acmicpc.net/problem/10972
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_10972_다음순열_최민수 {
	static int perm[];
	
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_10972"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		//아이디어 next permutation 문제
		
		//1~N까지의 수로 이루어진 순열
		int N = Integer.parseInt(br.readLine());
		
		//주어진 순열
		perm = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < perm.length; i++) {
			perm[i] = Integer.parseInt(st.nextToken());
		}
		
		//1. 꼭대기 찾기
		int j = N-1;
		//앞으로 가면서 왼쪽이 더 클 때마다 한칸씩 전진
		while(j > 0 && perm[j-1] >= perm[j]) j--;
		//왼쪽 끝에 도달헀으면 마지막 순열이라는 뜻
		if(j == 0) {
			System.out.println(-1);
			System.exit(0);
		}
		
		//2. 교환할 대상 찾기
		int k = N-1;
		while(perm[j-1] >= perm[k]) k--;
		swap(j-1, k);
		
		//3. 뒷부분 내림차순으로 바꾸기
		int l = N-1;
		//뒷 부분은 이미 내림차순으로 정렬되어있기 때문에
		//최고값, 최저값을 swap하는 과정임.
		while(j < l) swap(j++, l--);
		
		//출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < perm.length; i++) {
			sb.append(perm[i]).append(" ");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb);
		
		br.close();
	}
	
	static void swap(int i, int j) {
		int temp = perm[i];
		perm[i] = perm[j];
		perm[j] = temp;
	}
}
