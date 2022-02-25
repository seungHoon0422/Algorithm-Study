package BOJ0302;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;


/**
	입력 친구관계의 수 : 최대 100,000
	편향 트리로 친구관계가주어지는 경우 => 10만 * 10만 => 시간초과 발생
	
	입력되는 친구관계 양쪽 모두 arraylist에 연결 추가
	매번 입력이 들어올 때 마다 search함수를 실행해 출발 노드에 연결되어있는 관계의 수를 파악 => 이과정에서 매번 똑같은 작업을 반복하다보니 시간초과 나는듯...
	결국 union 써야함
	
	
*/
public class Main_4195_친구네트워크_Gold2_백승훈_시간초과 {

	private static StringBuilder sb;
	static Map<String, ArrayList<String>> map;
	private static int testCase;
	private static int N;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();		
		 
		testCase = Integer.parseInt(st.nextToken());
		
		for(int test=0; test<testCase; test++) {
			map = new HashMap<String, ArrayList<String>>();
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			String from = st.nextToken();
			String to = st.nextToken();
			String start = from;
			
			map.put(from, new ArrayList<>());
			map.get(from).add(to);
			map.put(to, new ArrayList<>());
			map.get(to).add(from);
			sb.append("2").append("\n");
			
			for(int i=1; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				from = st.nextToken();
				to = st.nextToken();			
				
				// 처음 나온 친구인 경우
				if(!map.containsKey(from)) 
					map.put(from, new ArrayList<>());
				
				map.get(from).add(to);
				if(!map.containsKey(to)) {
					map.put(to, new ArrayList<>());
				}
				map.get(to).add(from);
//				print();
				sb.append(search(start)).append("\n");
				
			}
			
		} // end of testCase
		System.out.println(sb);
		
		
	} // end of main

	static void print() {
		System.out.println();
		for (String name : map.keySet()) {
			System.out.print(name+" : ");
			System.out.println(map.get(name).toString());
		}
		System.out.println();
	}
	
	private static int search(String start) {
		Set<String> set = new HashSet<String>();
		Queue<String> q = new LinkedList<String>();
		set.add(start);
		q.offer(start);
		while(!q.isEmpty()) {
			String front = q.poll();
			for (String name : map.get(front)) {
				if(set.contains(name)) continue;
				q.offer(name);
				set.add(name);
			}
		}
		return set.size();
		
	} // end of search
	
	
	
	
	
	
	
	
	
} // end of class
