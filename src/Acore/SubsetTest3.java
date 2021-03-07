package Acore;

import java.util.Scanner;

public class SubsetTest3 {
	static int N, S, R, totalCnt;
	static int[] input;
	static int[] numbers;
	static boolean[] v;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		//합이 얼마가 되기를 원하는지
		S = sc.nextInt();
		input = new int[N];
		v = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		subset(0);
		System.out.println("total: "+totalCnt);
		
		sc.close();
	}
	
    static void perm(int cnt) {
        if(cnt==R) {
//            출력;
            return;
        }
        for(int i=1; i<=N; ++i) {
            if(v[i]) continue;
            numbers[cnt] = i;
            v[i] = true;
            perm(cnt+1);
            v[i] = false;
        }
    }
    
    static void comb(int cnt,int start) {
        if(cnt==R) {
//            출력;
            return;
        }
        for(int i=start; i<=N; ++i) {
            numbers[cnt] = i;
            comb(cnt+1,i+1);
        }
    }
	
	
	
    static void subset(int cnt) {
        if(cnt==N) {
//            출력;
            return;
        }
        v[cnt] = true;
        subset(cnt+1);
        v[cnt] = false;
        subset(cnt+1);
    }
}
