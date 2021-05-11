package programmers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

//[lv 3] 경주로 건설
//https://programmers.co.kr/learn/courses/30/lessons/67259
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class pro_67259_경주로건설_최민수 {
	public static void main(String[] args) throws Exception {
		//테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_0000"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		br.close();
	}
}

//코너를 최소화하고, 경로 길이를 최소화해야 한다.
//생각나는 경우1. 벽이 없는 상태에서 도달할 수 있는 경우와 동일한 최소거리가 있는 경우

//경우 2. 경로 자체는 최소인데, 코너가 발생해서, 경로가 더 긴데 싼 경우?
//없는 것 같기도한데
//=> 없다면 무조건 최소 경로 찾는게 답이다?
//단, 같은 최소 경로 중에선 가장 적게 꺾는 답을 찾아야 한다.


//아이디어 1.
//brute force

//아이디어 2.
//BFS를 하는데 한 방향을 우선적으로 끝까지 민다?

//아이디어 3
//BFS를 하면서 각 위치에 도달할 수 있는 최소값을 계산해서 저장한다
//이게 정답인듯?