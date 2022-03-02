package BOJ0302;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
public class Main_4195_친구네트워크_Gold2_백승훈_556ms {

	private static StringBuilder sb;
	private static int testCase;
	private static int N;
	private static int[] parent;
	private static int[] level;
	private static HashMap<String, Integer> id;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();		
		 
		testCase = Integer.parseInt(st.nextToken());
		
		for(int test=0; test<testCase; test++) {
			N = Integer.parseInt(br.readLine());
			parent = new int[N*2];	// 해당 노드의 루트노드 저장할 배열
			level = new int[N*2];	// 해당 노드의 level을 저장
			id = new HashMap<String, Integer>();
			int index = 0;
			
			Arrays.fill(level, 1);	// 처음 입력되는 노드의 레벨은 1

			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				String from = st.nextToken();
				String to = st.nextToken();
				if(!id.containsKey(from)) {	// 처음 입력되는 이름일 경우
					parent[index] = index;	// parent 는 자기자신
					id.put(from, index++);	// index 입력
					
				}
				if(!id.containsKey(to)) {
					parent[index] = index;
					id.put(to, index++);
				}
				
				sb.append(union(id.get(from), id.get(to))).append("\n");
			}
			
		} // end of testCase
		System.out.println(sb);
		
		
	} // end of main

	private static int union(int id1, int id2) {
		id1 = find(id1);
		id2 = find(id2);
		if(id1 != id2) {
			parent[id2] = id1;
			level[id1] += level[id2];
			level[id2] = 1;
		}
		return level[id1];
	}

	private static int find(int x) {
		if(parent[x] == x ) return x;
		else return parent[x] = find(parent[x]);
	}

	
	
	
	
	
	
	
	
	
} // end of class
