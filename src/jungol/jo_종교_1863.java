package jungol;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//종교
//http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1136&sca=99&sfl=wr_hit&stx=1863
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class jo_종교_1863 {

	static int n;
	static int parents[];

	static void make() {
		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}
	}

	static int findSet(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = findSet(parents[a]);
	}

	static boolean union(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		if (rootA == rootB)
			return false;

		parents[rootB] = rootA;
		//
		return true;
	}

	public static void main(String[] args) throws IOException {
		// 테스트 입력
		System.setIn(new FileInputStream("res/jungol/jo_input_1863"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 학생 n 0~5만
		// 쌍의 수 m 0~10만
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		// u-f에 사용할 부모 배열
		// n번의 부모값을 저장할 것이다.
		parents = new int[n];
		make();

		// 종교의 수
		// 1쌍의 입력이 들어오면
		// 각각 집합으로 만들어 준뒤에
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1; // 0번 학생부터 시작하도록
			int b = Integer.parseInt(st.nextToken()) - 1;
			union(a, b);
		}

		// //각 원소에 대해 findSet을 돌려서 종교 목록에 추가함
		// int[] religionList = new int[50000];
		// int religionCnt = 0;
		// boolean alreadyExist = false;
		// for (int i = 0; i < n; i++) { //모든 학생에 대해
		// int religion = findSet(i);
		// alreadyExist = false;
		// for (int j = 0; j < religionCnt; j++) {
		// if(religionList[j] == religion) { //일치하는 종교가 있다.
		// alreadyExist = true;
		// break;
		// }
		// }
		// //해당 종교가 종교 목록에 없으면 추가
		// if(!alreadyExist) religionList[religionCnt++] = religion;
		// }
		int religionCnt = 0;
		for (int i = 0; i < n; i++) {
			if (parents[i] == i) {
				religionCnt++;
			}
		}
		// System.out.println(Arrays.toString(parents));
		System.out.println(religionCnt);

		br.close();

	}

}