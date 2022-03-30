import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/** Main_백준_13913_숨바꼭질4_골드4_388ms*/
public class Main_백준_13913_숨바꼭질4_골드4_388ms {
	
	public static int next;
	private static int K;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		K = sc.nextInt();
		if(N==K) {
			System.out.println(0);
			System.out.println(N);
			return;
		}
		else {
			// BFS 탐색
			go(N);
		}
		
	} // end of main

	private static void go(int n) {
		int[] vis = new int[100001];
		Arrays.fill(vis, -1);
		vis[n] = n;
		Queue<Integer> q = new LinkedList<>();
		q.offer(n);
		int time = 0;
		while(!q.isEmpty()) {
			for(int i=0, len=q.size(); i<len; i++) {
				int curr = q.poll();
				// 기저 조건
				if(curr==K) {
					StringBuilder sb = new StringBuilder();
					sb.append(time).append("\n");

					Stack<Integer> stack = new Stack<>();
					stack.push(curr);
					int parent = vis[curr];
					// 부모 경로 따라가기
					while(true) {
						stack.push(parent);
						if(parent==n) {
							while(!stack.isEmpty()) sb.append(stack.pop()).append(" ");
							break;
						}
						parent = vis[parent];
					} // end of while 
					System.out.println(sb.toString());
					return;
				} // end of 기저조건
				// 곱하기 2 
				next = 2*curr;
				if(next<=100000 && curr<K && vis[next]==-1) {
					vis[next] = curr; // 부모 저장
					q.offer(next);
				}
				// 더하기 1
				next = curr+1;
				if(curr<K && vis[next]==-1) {
					vis[next] = curr; // 부모 저장
					q.offer(next);
				}
				// 뺴기 1 
				next = curr-1;
				if(next>=0 && vis[next]==-1) {
					vis[next] = curr; // 부모 저장
					q.offer(next);
				}
			} // end of for size
			time++;
		} // end of while 
	} // end of go 
} // end of class 
