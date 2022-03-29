import java.io.*;
import java.util.*;

/** Main_백준_13335_트럭_실버1_148ms*/
public class Main_백준_13335_트럭_실버1_148ms {
	
	private static class Truck{
		int weight, time;
		public Truck(int weight, int time) {
			super();
			this.weight = weight;
			this.time = time;
		}
	}
	

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		/*
		 * 트럭의 수		1 <= n <= 1000
		 * 다리의 길이		1 <= w <= 100
		 * 다리의 최대 하중	10<= L <= 1000
		 * 각 트럭의 무게	1 <= ai<= 10
		 * 
		 * 시간 1당 1칸씩 움직일 수 있다.
		 * 모든 트럭이 다리를 건너는 최단 시간을 출력하라.
		 * 
		 * 맨 앞 트럭부터 이동
		 * 다리에서 탈출함과 동시에 다른 트럭은 다리 위로 올라올 수 있다.
		 * 다리의 길이만큼의 턴이 지나면 다리 위에 있는 트럭 중 가장 앞 트럭을 비운다.
		 * 새로운 트럭이 다리 위로 올라올 수 있다면 올리고, 불가하면 올리지 않는다.
		 * 
		 * 다리 큐를 만들고, 트럭이 다리에 올라간 순간의 시간을 기록. 현재 시간 - 기록 = 다리의 길이가 되면 탈출
		 * 다리 위 하중 변수
		 * 현재 시간 변수
		 * 트럭 큐
		 */
		
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int maxWeight = Integer.parseInt(st.nextToken());
		
		Queue<Integer> trucks = new LinkedList<Integer>();
		Queue<Truck> Bridge = new LinkedList<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) trucks.add(Integer.parseInt(st.nextToken()));
		
		int time = 0;
		int currWeight = 0;
		int passed = 0;
		// 트럭의 개수만큼 통과하지 못했다면 반복
		while(passed<n) {
			//1. 트럭 이동, 다리 위에 트럭이 있을 때
			if(Bridge.peek()!=null) {
				if(time - Bridge.peek().time == w) {
					// 다리 맨 앞에 있는 트럭을 내리면서 현재 하중 갱신
					currWeight-=Bridge.poll().weight;
					passed++;
				}
			}
			
			//2. 다리 위에 트럭이 올라갈 수 있는지 확인
			if(!trucks.isEmpty()) {
				// 현재 하중 + 다음 트럭의 무게 <= 최대 하중인 경우 추가
				if(currWeight+trucks.peek()<=maxWeight) {
					currWeight+=trucks.peek();
					Bridge.offer(new Truck(trucks.poll(), time));
				}
			}
			time++;
		}
		System.out.println(time);
		
		
	} // end of main
	
	
} // end of class 
