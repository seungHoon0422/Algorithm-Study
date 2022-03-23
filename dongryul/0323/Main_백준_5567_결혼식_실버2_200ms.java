import java.io.*;
import java.util.*;

/** Main_백준_5567_결혼식_실버2_200ms*/
public class Main_백준_5567_결혼식_실버2_200ms {
	
	private static class Friend implements Comparable<Friend>{
		int idx, depths;
		public Friend(int idx, int depths) {
			super();
			this.idx = idx;
			this.depths = depths;
		}
		@Override
		public int compareTo(Friend o) {
			// TODO Auto-generated method stub
			return this.depths - o.depths;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		ArrayList<Integer>[] list = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		int n1, n2;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());
			list[n1].add(n2);
			list[n2].add(n1);
		}
		
		int cnt = 0;
		boolean[] friend = new boolean[N+1];
		Queue<Friend> q = new LinkedList<>();
		friend[1] = true;
		q.offer(new Friend(1, 0));
		// 다익스트라
		while(!q.isEmpty()) {
			Friend curr = q.poll();
			for(int n : list[curr.idx]) {
				if(friend[n]) continue;
				friend[n] = true;
				cnt++;
				// 상근이의 친구까지만 포함
				if(curr.depths==0) {
					q.offer(new Friend(n, curr.depths+1));
				}
			}
		}
		System.out.println(cnt);
	} // end of main
	
	
} // end of class 
