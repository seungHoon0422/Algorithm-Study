import java.io.*;
import java.util.StringTokenizer;

/** Main_백준_9372_상근이의여행_실버3_384ms*/
public class Main_백준_9372_상근이의여행_실버3_384ms {
	
	private static int N;

	public static void main(String[] args) throws Exception	{
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		/*
		 * 상근이가 가장 적은 종류의 비행기를타고 모든 국가들을 방문할 수 있도록
		 * --> MST를 구현하라
 		 */
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testcase=0; testcase<T; testcase++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
			}
			
			// MST면 무조건 N-1개 간선이 있다
			sb.append(N-1).append("\n");
		}
		
		System.out.println(sb.toString());
		
		
	} // end of main
} // end of class
