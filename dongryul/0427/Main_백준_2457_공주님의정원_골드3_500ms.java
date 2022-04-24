import java.io.*;
import java.util.*;

/** Main_백준_2457_공주님의정원_골드3_500ms */
public class Main_백준_2457_공주님의정원_골드3_500ms {

	private static class Date implements Comparable<Date> {
		int month, day;

		public Date(int month, int day) {
			super();
			this.month = month;
			this.day = day;
		}

		// 날짜 비교를 위한 메서드
		@Override
		public int compareTo(Date o) {
			if (this.month == o.month) {
				return this.day - o.day;
			}
			return this.month - o.month;
		}
	}

	private static class Flower implements Comparable<Flower> {
		Date start, end;

		public Flower(Date start, Date end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Flower o) {
			int comp = this.start.compareTo(o.start);
			if (comp == 0) {
				return o.end.compareTo(this.end);
			}
			return comp;
		}
	}

	public static void main(String[] args) throws Exception {

		/*
		 * 1. 공주가 좋아하는 게절인 3월 1일부터 11월 30일까지 매일 꽃이 한 가지 이상 피어있어야 한다. 
		 * 2. 정원에 심는 꽃의 종류가 최소가 되어야 한다.
		 * 
		 * IDEA 1. 날짜 처리를 어덯게 할까 월 데이터를 다 일수 데이터로 변환? 좀 어렵다.. 2. Date 클래스를 사용해볼까 X 클래스
		 * 구현하자
		 * 
		 * 짜피 그리디니까 정렬을 해야 함... 어떤 기준으로 정렬할까 일찍 시작하는 좌표 기준, 동일한 시작 좌표면 가장 늦게 끝나는게 앞으로
		 * 
		 * 1. 데이터 타입을 정의하자. 1.1 날짜 비교를 위한 Date 클래스 1.2 꽃의 수명을 담고 있는 Flower 클래스
		 * 
		 * 정렬 후 이전 꽃의 끝나는 날짜가 다음 꽃의 시작 날짜와 겹치지 않는다면 + 이때 공주님이 원하는 조건 1번을 만족하지 않는다면 종료 ( 실패 ) 
		 * 
		 * 1.1 3월 1일 이전 꽃들 중 가장 긴거를 먼저 선택
		 * 1.2 만약 처음 심은 꽃으로 11월 30일 이후까지 심을 수 있다면 종료
		 * 
		 * 2. 다른 꽃들 심기 ( 먼저 심은 꽃의 끝나는 시점 이전에 심는 꽃 탐색 )
		 * 2.1 가장 지는 시기가 늦은 꽃 선택
		 * 2.2 꽃 선택에 실패했다면 종료 ( 실패 )
		 * 3. 꽃 선택에 성공했다면 심고 다음 탐색.. 
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Flower> flowers = new PriorityQueue<>();

		int sm, sd, em, ed;
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			sm = Integer.parseInt(st.nextToken());
			sd = Integer.parseInt(st.nextToken());
			em = Integer.parseInt(st.nextToken());
			ed = Integer.parseInt(st.nextToken());
			// 의미 없는 데이터인, 2월 종료, 12월 시작하는 꽃 제외
			if (em <= 2 || sm >= 12)
				continue;
			flowers.offer(new Flower(new Date(sm, sd), new Date(em, ed)));
		}

//		while(!flowers.isEmpty()) {
//			System.out.println(flowers.poll().toString());
//		}

		Date start = new Date(3, 1);
		Date end = new Date(11, 30);

		Flower curr = new Flower(new Date(1, 1), new Date(1, 1));
		Flower temp = new Flower(new Date(0, 0), new Date(0, 0));
		Flower update = new Flower(new Date(0, 0), new Date(0, 0));

		boolean flag = false;
		// 1. start 꽃 찾기
		while (!flowers.isEmpty()) {
			// 큐 맨 앞에 있는 꽃의 시작일이 3월 1일보다 늦는다면 종료
			if (flowers.peek().start.compareTo(start) > 0) break;
			// 처음으로 심을 꽃 선택 ( 더 오래 가는 꽃을 선택 )
			temp = flowers.poll();
			if (temp.end.compareTo(curr.end) > 0) {
				curr = temp;
				flag = true;
			}
		}
		if (!flag) System.out.println(0);
		else {
			// 하나 심은 것으로 간주
			int cnt = 1;
			flag = false;
			// 만약 처음 심은 꽃이 일년 내내 심을 수 있는 꽃이라면 종료
			if (curr.end.compareTo(end) > 0) System.out.println(cnt);
			else {
				while (true) {
					// 꽃 큐가 비어있지 않고, 심은 꽃의 종료일 이전에 심는 꽃이라면 탐색
					while (!flowers.isEmpty() && curr.end.compareTo(flowers.peek().start) >= 0) {
						// 탐색할 꽃 빼기
						temp = flowers.poll();
						// 이전 선택된 꽃의 범위에 포함되면서 더 오랜 기간 심을 수 있는 꽃이라면 갱신
						if (curr.end.compareTo(temp.end) < 0) {
							// 가장 긴 녀석으로 업데이트
							if (update.end.compareTo(temp.end) < 0) {
								update = temp;
							}
							// 찾았다는 의미의 flag
							flag = true;
						}
					}
					// 갱신할 꽃을 찾지 못했다면 종료
					if (!flag) break;
					// 가장 긴 꽃으로 갱신
					curr = update;
					cnt++;
					// 가장 최근에 심은 꽃으로 11월 30일까지 키울 수 있는 경우 종료
					if (curr.end.compareTo(end) > 0) break;
					flag = false;
				} // end of while view flowers
					// 꽃 심기에 실패하지 않았다면 cnt : 0
				System.out.println(flag ? cnt : 0);
			}
		}
	} // end of main
} // end of class
