import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_1966_프린터큐_S3_백승훈_96ms {
	
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb= new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		for(int tc=0; tc<test; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int n = Integer.parseInt(st.nextToken());
			int index = Integer.parseInt(st.nextToken());
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			Queue<int[]> q = new LinkedList<>();
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<n; i++) {
				int importance = Integer.parseInt(st.nextToken());
				q.offer(new int[] {i, importance}); // {위치, 중요도}
				pq.offer(-importance); // 주용도가 높은 문서가 peek에 오게 음수로 저장
			}
		
			int count = 0;
			while(!q.isEmpty() && !pq.isEmpty()) {
				int[] front = q.poll(); // front queue
				if(-pq.peek() == front[1]) { // 큐의 앞의 원소가 가장 중요도 높은 경우
					count++;
					pq.poll();
					if(front[0] == index) { // 찾고자 하는 문서인 경우
						sb.append(count).append("\n");
						break;
					}
				}else { // 다시 큐에 add
					q.offer(front);
				}
			}	
		}
	System.out.println(sb);
	
	}
}
