import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_13335_트럭_S1_박지호_108ms {

	static class truck {
		int weight;
		int idx;

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		public int getIdx() {
			return idx;
		}

		public void setIdx(int idx) {
			this.idx = idx;
		}

		public truck(int weight, int idx) {
			super();
			this.weight = weight;
			this.idx = idx;
		}

		@Override
		public String toString() {
			return "truck [weight=" + weight + ", idx=" + idx + "]";
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<truck> queue = new LinkedList<truck>();
		Queue<truck> bridge = new LinkedList<truck>();

		int N = Integer.parseInt(st.nextToken()); // 트럭 수
		int W = Integer.parseInt(st.nextToken()); // 다리 길이
		int L = Integer.parseInt(st.nextToken()); // 다리 최대 하중

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			queue.offer(new truck(Integer.parseInt(st.nextToken()), 0));
		}

		int currentW = 0;
		int finish = 0;
		int result = 0;
		while (true) {
			result++;
			for (int i = 0, end=bridge.size(); i < end; i++) {
				truck T = bridge.poll();
				int next = T.getIdx() + 1;
				if(next > W) {
					currentW -= T.getWeight();
					finish++;
				} else {
					T.setIdx(next);
					bridge.offer(T);
				}
				
			}
			
			if(!queue.isEmpty()) {
				int temp = queue.peek().getWeight();
				
				if (currentW + temp <= L) {
					currentW += temp;
					truck a = queue.poll();
					a.setIdx(a.getIdx()+1);
					bridge.offer(a);
				}
			}

			if(finish == N) {
				break;
			}
			
		}
		
		System.out.println(result);

	}

}
