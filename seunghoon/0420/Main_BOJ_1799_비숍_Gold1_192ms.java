import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 * 
 * [ IDEA 1 ]
 * 
 * nQueen처럼 재귀의 기준으로 잡을 대각선을 찾는다.
 * 1. r+c값에 대해 우상 대각선 기준 선택, 0<= r+c <=2*N-2
 * 2. 각 대각선 라인에서 비숍을 놓을 수 있는 자리 확인
 * 3. 그 자리에 해당하는 r-c자리에 비숍이 놓여있는지 확인
 * 4. 안놓여있면 놓고 놓여있으면 백트래킹
 * 
 * ===> 시간초과... 일부러 list에 담아서 돌렸는데도 시간초과가 난다...
 * 
 * [ IDEA 2 ]
 * 
 * nQueen에서는 row마다 재귀를 돌릴때 직전의 결과가 바로 다음에 영향을 끼쳤지만
 * 비숍의 경우에는 diagonal이 하나 움직일때마다 서로 영향을 전혀 받지않는다!! => 대각선 search의 특징
 * 따라서  **홀수, 짝수일 때를 구분해서 serach** 를 하면 필요없이 절반을탐색하지 않아도됨
 * 
 * @author SeongHoon
 *
 */
public class Main_BOJ_1799_비숍_Gold1_192ms {


	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	
	private static int N;
	private static ArrayList[] diagonal;
	private static ArrayList[] diagonalOp;
	private static boolean[] visitOp;
	private static int evenResult;
	private static int oddResult;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		N = Integer.parseInt(br.readLine());
		diagonal = new ArrayList[2*N-1];
		diagonalOp = new ArrayList[2*N-1];
		
		for(int i=0; i<2*N-1; i++) {
			diagonal[i] = new ArrayList<Node>();
			diagonalOp[i] = new ArrayList<Node>();
		}
		
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0, index=0; j<N; j++,index+=2) {
				if(str.charAt(index) == '1') {
					// 우상향 대각선 리스트 좌표 저장  ex) (1,3) => 1+3 = 4 자리에 저장
					diagonal[i+j].add(new Node(i,j));
					// 우하향 대각선 리스트 좌표 저장 ex) (1,3) => 1 - 3 = -2 + (N-1) => -2 + 3 = 1에 저장, 
					// 나올수 있는 가장 작은수가 0이 될 수 있게 N-1로 offset 설정 
					diagonalOp[i-j+N-1].add(new Node(i,j));
				}
			}
		}
		
		// 탐색을 진행할때 반대 diagonal에 비숍이 놓여있는지 판단할 boolean 배열
		visitOp = new boolean[2*N-1];
		
		// 짝수번째 diagonal을 확인할 때 결과를 저장할 변수
		evenResult = 0;
		// 홀수 번째 diagonal을 확인할 때 결과를 저장할 변수
		oddResult = 0;
		
		bishop(0,true,0);
		bishop(1,false,0);
		
		System.out.println(evenResult+oddResult);
		
		
		
	} // end of main

	private static void bishop(int dia, boolean even, int bs) {
		if(dia >= 2*N-1) {
			if(even) evenResult = Math.max(evenResult, bs);
			else oddResult = Math.max(oddResult, bs);
			return;
		}

		// dia diagonal에서 비숍을 세우지 않은 경우
		bishop(dia+2, even, bs);

		// i+j = dia 라인에 비숍을 놓을 수 있는 자리
		ArrayList<Node> arrayList = diagonal[dia];
		for (Node node : arrayList) {
			
			// i+j=dia인 자리의 i-j 인덱스
			int index = node.r-node.c+N-1;
			if(!visitOp[index]) {
				visitOp[index] = true;
				bishop(dia+2, even, bs+1);
				visitOp[index] = false;
				
			}
		}
		

		
		
	}
	
	static class Node{
		int r, c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}
	
} // end of class








	
