import java.io.*;
import java.util.*;

public class Main_백준_1799_비숍_골드1_160ms {
	
	private static class Point{
		int row, col;
		public Point(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	
	private static int N, B, W;
	private static int max = 0;
	private static boolean[] RtoL, LtoR;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		/*
		 * 바닥의 색깔에 따라 분리해서 접근해야 한다. --> 시간 복잡도 절반으로 줄어듦... ( 질문검색 참조 )
		 * 검정과 흰색은 서로 1도 관여하지 않기 때문에. 이러한 특성을 살리는 방법인 것 같다.
		 * 
		 * 오른쪽에서 왼쪽 아래로 떨어지는 대각선에 경우
		 * row+col = i와 일치
		 * 왼쪽에서 오른쪽 아래로 가는 대각선은
		 * 왼쪽 바닥이 0번이라 가정했을 때 
		 * col - row + (N-1) 의 값을 갖는다.
		 * 
		 * 위 특성을 이용하는 boolean 배열을 만들어서 하자
		 */
		
		N = Integer.parseInt(br.readLine());
		
		ArrayList<Point> black = new ArrayList<>();
		ArrayList<Point> white = new ArrayList<>();
		
		RtoL = new boolean[2*N-1]; LtoR = new boolean[2*N-1];
		
		String temp = "";
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				temp = st.nextToken();
				if(temp=="1") {
					if((i+j)%2==0) black.add(new Point(i, j));
					else white.add(new Point(i, j));
				}
			}
		} // end of initialize
		
		go(0, black.size(), 0, black);
		B = max;
		max = 0;
		go(0, white.size(), 0, white);
		W = max;
		
		System.out.println(B+W);
		
	} // end of main 
	
	private static void go(int start, int size, int picked, ArrayList<Point> bishop) {
		if(start==size) {
			if(max<picked) max = picked;
			return;
		} // end of 기저조건 
		
		for(int i=start; i<size; i++) {
			if(BishopAble(bishop.get(i))) {
				setBishop(bishop.get(i), true);
				go(i+1, size, picked+1, bishop);
				setBishop(bishop.get(i), false);
			} // end if if 
		} // end of for
		
	} // end of go 

	private static boolean BishopAble(Point point) {
		if(RtoL[point.row+point.col] || LtoR[(point.col-point.row)+(N-1)]) return false;
		return true;
	} // end of BishopAble
	
	private static void setBishop(Point point, boolean flag) {
		RtoL[point.row+point.col] = LtoR[(point.col-point.row)+(N-1)] = flag;
	} // end of setBishop

} // end of class 
