
package BOJ0302;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_14719_빗물_Gold5_백승훈 {

	private static StringBuilder sb;
	private static int N;
	private static int M;
	private static int[] board;
	private static List<Integer> list;

	
	/*
		아이디어 :
		변곡점에서 주의하는데 내려가는 지점 저장해놓는다.
		맨앞에 맨 뒤는 항상 저장
		앞에서부터 확인하는데 i, i+1 번째 변곡점을 확인하고
		i번째 변곡점의 인덱스부터 i+1 번째 변곡접의 인덱스까지 두 벽중 낮은 높이로 체크하며
		차이를 더해간다. => 빗물의 양
	*/
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[M];
		st = new StringTokenizer(br.readLine());
		board[0] = Integer.parseInt(st.nextToken());
		list = new ArrayList<Integer>();
		for(int i=1; i<M; i++) {
			board[i] = Integer.parseInt(st.nextToken());
			if(board[i] < board[i-1]) list.add(i-1);
		}
		if(board[M-2] < board[M-1]) list.add(M-1);		

		
		
		int cost = 0;
		int prev = list.get(0);
		for(int i=1; i<list.size(); i++) {
			int curr = list.get(i);
			int minValue = Math.min(board[prev], board[curr]);
			for(int j=prev+1; j<curr; j++) {
				cost += minValue-board[j];
			}
			prev = curr;
		}
		
		System.out.println(cost);
		
		
		 
	} // end of main
	
} // end of class
