import java.io.*;
import java.util.*;

public class Main_백준_1744_수묶기_골드4_84ms {
	
	public static void main(String[] args) throws Exception{
		
		/*
		 * 양수일 땐 큰 값부터 묶고
		 * 음수일 땐 가장 작은 값부터 묶는다.
		 * 
		 * 양수큐
		 * Zero boolean (어차피 음수랑 곱할 녀석인데, 하나 빼고는 다 곱해질거니까 boolean으로 쓰자.
		 * 음수큐를 만들어서
		 * 
		 * 양수는 큰수부터 묶어서 더하고
		 * 음수는 작은수부터 묶어서 더한다.
		 * 
		 * 음수가 남았더라면, zero가 있었으면 곱해서 0으로 처리해리고ㅓ
		 * zero가 없으면 더해버린다.
		 * 
		 * 양수도 짝이 없는건 더해버린다.
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> plusQ = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> minusQ = new PriorityQueue<>();
		boolean zero = false;
		int temp = 0;
		for(int i=0; i<N; i++) {
			temp = Integer.parseInt(br.readLine());
			if(temp>0) plusQ.offer(temp);
			else if(temp<0) minusQ.offer(temp);
			else zero = true;
		}
		
		int ans = 0;
		int add, mult = 0;
		
		int curr, next;
		// 1. 양수큐 짝에 맞춰서 더한다.
		while(!plusQ.isEmpty()) {
			// 하나를 먼저 뺀다.
			curr = plusQ.poll();
			// 다음 수가 있을 때 
			if(plusQ.peek()!=null) {
				next = plusQ.poll();
				add = curr+next;
				mult = curr*next;
				// 두 수를 곱한 것과 더한 값을 비교해서 더 큰 값을 넣는다 ( 1 1 1 1 1의 경우 더한게 더 크니까 비교해준다 )
				if(mult>add) ans+=mult;
				else ans+=add;
			}
			// 짝이 없는 양수
			else {
				ans += curr;
			}
		} // end of while plus 
		
		// 2. 음수큐 짝에 맞춰서 더한다.
		while(!minusQ.isEmpty()) {
			curr = minusQ.poll();
			if(minusQ.peek()!=null) {
				next = minusQ.poll();
				mult = curr*next;
				ans+=mult;
			}
			// 짝이 없는 음수일 때 zero가 있다면 안더하고, 없다면 더한다.
			else {
				if(!zero) ans+=curr;
			}
		}
		
		System.out.println(ans);
		
	} // end of main
	
} // end of class 
