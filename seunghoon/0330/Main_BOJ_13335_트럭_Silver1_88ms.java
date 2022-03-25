import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/** 
 * n개의 트럭이 이동
 * W : 다리의 길이
 * 트럭은 하나의 단위시간 마다 단위 길이만큼 이동
 * 
 * 아이디어 : 
 * 다리(큐)에 0을 채워넣어 다리 상황을 만들어놓는다.
 * 큐의 앞높을 무조건 뺴주고 전체하중에서도 뺴준다.
 * 전체 하중값과 들어가야할 트럭의 무게를 계산 => 가능하면 트럭 넣어주고, 안되면 0 넣어준다.
 * 마지막 트럭이 들어간 경우 탈출
 * 마지막 트럭의 탈출시간은 다리의 길이이므로 마지막에 다리길이 더해주고 end
 * 
 * 
 * */
public class Main_BOJ_13335_트럭_Silver1_88ms {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	private static int N;
	private static int W;
	private static int L;
	public static void main(String[] args) throws IOException {
		
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		int[] trucks = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) trucks[i] = Integer.parseInt(st.nextToken());
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i=0; i<W; i++) q.offer(0);
		int weight = 0;
		int t = 0;
		int next = 0;
		while(next < N) {
			
			// 큐의 가장 앞 트럭을 빼낸다.
			weight -= q.poll();
			// 전체 하중값 계산
			if(next != N && weight+trucks[next] <= L) {
				q.offer(trucks[next]);
				weight += trucks[next++];
			} else {
				q.offer(0);
			}
			t++;
			// 다음 트럭 올라갈 수 있는지 계산
			// 올라갈 수 있으면 트럭 올리고, 아니면 0 올린다.
		} // end of while
		
		System.out.println(t+W);
		
		
		
		
		
		
	} // end of main
} // end of class
