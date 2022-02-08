import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/** Main_백준_1966_프린터큐_실버3_틀림*/
class PrinterQ implements Comparable<PrinterQ>{
	int idx;
	int rank;
	public PrinterQ(int idx, int rank) {
		super();
		this.idx = idx;
		this.rank = rank;
	}
	public int getIdx() {
		return idx;
	}
	public int getRank() {
		return rank;
	}
	// 역순정렬
	@Override
	public int compareTo(PrinterQ o) {
		// TODO Auto-generated method stub
		if(this.rank > o.rank) {
			return -1;
		}
		else if(this.rank < o.rank) {
			return 1;
		}
		return 0;
	}
}

public class Main_백준_1966_프린터큐_실버3_틀림 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		for(int i=0; i<TC; i++) {
			// 내림차순 우선순위 큐		
			PriorityQueue<PrinterQ> q = new PriorityQueue<>();
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				// 이런식으로 데이터를 넣자
				q.offer(new PrinterQ(j, Integer.parseInt(st.nextToken())));
			}
			int cnt = 1;
			while(q.peek().getIdx()!=target) {
				q.poll();
				cnt++;
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}
}	
