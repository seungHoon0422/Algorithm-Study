package BOJ0316;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15683_감시_Gold5 {

	private static int N;
	private static int M;
	private static ArrayList<int[]> cctv;
	private static int result;
	private static int[] cctvDirections = {4, 2, 4, 4, 1};
	
	
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][];
		
		cctv = new ArrayList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0 && map[i][j] != 6) {
					cctv.add(new int[]{i,j});
				}
			}
		}
		
		
		
		
		result = N*M;
		go(0,map);
		
		
	}



	private static void go(int cctvIndex, int[][] map) {
		if(cctvIndex == cctv.size()) {
			int count = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] == 0) count++;
				}
			}
			result = Math.min(result, count);
			return;
		}
		
		int[] cc = cctv.get(cctvIndex);
		int cctvType = map[cc[0]][cc[1]];
		
		for(int i=0; i<cctvDirections[cctvType]; i++) {
			int[][] copiedMap = copy(map);
			
			
		}
		
		
		
		
		
	}



	private static int[][] copy(int[][] map) {
		int[][] m = new int[N][];
		for(int i=0; i<N; i++) m[i] = Arrays.copyOf(map[i], M);
		return m;
	}
}
