import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_BOJ_18869_멀티버스2_Gold5 {


	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	private static int M;
	private static int N;
	private static Node[][] spaces;

	public static void main(String[] args) throws Exception {
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		spaces = new Node[M][N];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				spaces[i][j] = new Node(Integer.parseInt(st.nextToken()),j);
			}
			Arrays.sort(spaces[i]);
		}

		int answer = 0;
		for(int i=0; i<M; i++) {
			for(int j=i+1; j<M; j++) {
				if(isTwin(i,j)) answer++;
			}
		}
		System.out.println(answer);
		
		
	} // end of main

	private static boolean isTwin(int space1, int space2) {
		for(int i=0; i<N; i++) {
			if(spaces[space1][i].index != spaces[space2][i].index) return false;
		}
		return true;
	}
	
	
	
	
	static class Node implements Comparable<Node>{
		int size, index;

		public Node(int size, int index) {
			super();
			this.size = size;
			this.index = index;
		}

		@Override
		public int compareTo(Node o) {
			return this.size - o.size;
		}
		
	}
} // end of class








