package baekjoon_20001;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//[골드 4] 🎵니가 싫어 싫어 너무 싫어 싫어 오지 마 내게 찝쩍대지마🎵 - 1
//https://www.acmicpc.net/problem/20440
//제출전에 Main으로 바꾸기, file input 지우기, package 지우기
public class bj_20440_니가싫어싫어_최민수 {

	static int N;

	public static void main(String[] args) throws Exception {
		// 테스트 입력
		System.setIn(new FileInputStream("res/baekjoon/bj_input_20440"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		PriorityQueue<TimeObject> pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			pq.add(new TimeObject(start, 1));
			pq.add(new TimeObject(end, 2));
		}

		int maxCount = 0; //현재까지 측정된 최고 숫자의 모기
		int maxStart = 0; //현재까지 측정된 최고 숫자의 모기의 시작 시간
		int maxEnd = 0; //현재까지 측정된 최고 숫자의 모기의 시작 시간

		int count = 0; //현재 모기 수
		boolean nowUpdate = false; //현재 최고값 갱신중인가
		boolean nowFall = false; //바로 1값 전에 최고값이였다.
		int nowFallVal = 0; //바로 1값 전의 시간
		int lastVal = 0;

		while (!pq.isEmpty()) {
			TimeObject temp = pq.poll();
			if (pq.size() == 0) {
				lastVal = temp.time;
			}

			if (temp.which == 1) { //start
				count++;
				if (nowFall && nowFallVal == temp.time && count == maxCount) { //바로 한 턴 전에 떨어졌으면 유지해야한다.
					nowFall = false;
					nowUpdate = true;
				} else if (count > maxCount) { // 시간 갱신
					maxCount = count;
					maxStart = temp.time;
					nowUpdate = true;
				}
				if(nowFall){
					nowFall = false;
				}
			} else {
				if (nowFall) {
					nowFall = false;
				}
				count--;
				if (nowUpdate && count + 1 == maxCount) { //방금 최고였다가 떨어졌다.
					maxEnd = temp.time;
					nowUpdate = false;
					nowFall = true;
					nowFallVal = temp.time;
				}
			}
		}
		if (nowUpdate) {
			maxEnd = lastVal;
		}
		bw.write(maxCount+"\n");
		bw.write(maxStart+" "+maxEnd);
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static class TimeObject implements Comparable<TimeObject> {
		int time;
		int which; //start는 1 or end는 2

		public TimeObject(int time, int which) {
			this.time = time;
			this.which = which;
		}

		@Override
		public int compareTo(TimeObject o) {
			int compareVal = Integer.compare(this.time, o.time);
			if (compareVal == 0) {
				return -Integer.compare(this.which, o.which);
			} else {
				return compareVal;
			}
		}


		
	}
}
