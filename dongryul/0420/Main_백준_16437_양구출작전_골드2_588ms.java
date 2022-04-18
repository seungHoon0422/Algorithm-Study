import java.io.*;
import java.util.*;

public class Main_백준_16437_양구출작전_골드2_588ms {
	
	private static class Node{
		char type;
		int NoInd, index;
		public Node(char type, int noInd, int index) {
			super();
			this.type = type;
			NoInd = noInd;
			this.index = index;
		}
	}
	
	private static int N;
	private static ArrayList<Node>[] list;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		/*
		 * DFS로 진행하면서 
		 * 바닥을 찍은 기준으로 값을 계속 올리면서 해당 깊이에서의 값을 반환하자
		 * 
		 * 바닥을 찍고 올라오는 방법을 사용해야 함을 알았는데.. 갔다 왔다 시간이 될까? 하면서 질문 검색 보다가 너무 잘 풀린 예제가 있어서
		 * 참고했습니다..ㅎ
		 */
		
		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		for(int i=1; i<=N; i++) list[i] = new ArrayList<Node>();
		
		char type = '\u0000';
		int NoInd = 0;
		int to = 0;
		for(int i=2; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			type = st.nextToken().charAt(0);
			NoInd = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			list[to].add(new Node(type, NoInd, i));
		}
		
		System.out.println(go(new Node('S', 0, 1)));
		
	} // end of main

	private static long go(Node curr) {
		long total = 0;
		// 연결된 섬들에 대한 총합을 받고
		for(Node island : list[curr.index]) {
			total += go(island);
		} // end of for 
		if(curr.type=='S') return total+curr.NoInd;
		else return total-curr.NoInd>0?total-curr.NoInd:0;
	} // end of go 
	
} // end of class 
