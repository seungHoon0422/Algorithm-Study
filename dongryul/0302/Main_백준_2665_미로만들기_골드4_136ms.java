import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

/** Main_백준_2665_미로만들기_골드4_136ms*/
public class Main_백준_2665_미로만들기_골드4_136ms {
	
	private static class Node implements Comparable<Node>{
		int row, col, weight;

		public Node(int row, int col, int weight) {
			super();
			this.row = row;
			this.col = col;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.weight-o.weight;
		}
	}
	
	private static int n;
	private static char[][] map;
	private static int[][] distance;
	
	private static void go() {
		// TODO Auto-generated method stub
		distance = new int[n][n];
		for(int i=0; i<n; i++) {
			Arrays.fill(distance[i], 10000);
		}
		// 1이 빈칸이다..... 
		// 첫 좌표까지의 벽 개수를 map[0][0]으로 초기화 ( Math.abs보다 -'1' * -1이 더 좋았을거 같다 ) 
		// 막상 해보니 시간적 이점은 없었다..
		distance[0][0] = Math.abs(map[0][0]-'1');
		// 상하좌우
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.offer(new Node(0, 0, distance[0][0]));
		
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			int row = curr.row;
			int col = curr.col;
			for(int i=0; i<4; i++) {
				int nr = row + dr[i];
				int nc = col + dc[i];
				// 범위를 벗어났다면
				if(nr<0 || nr>=n || nc<0 || nc>=n) continue;
				int wall = Math.abs(map[nr][nc]-'1');
				// 초기화된적 없거나, 더 효율적인 경로가 있다면 갱신
				if(distance[nr][nc]>distance[row][col] + wall) {
					distance[nr][nc] = distance[row][col] + wall;
					pq.offer(new Node(nr, nc, distance[nr][nc]));
				}
			}
		}
	}

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // 1 <= n <= 50 배열의 크기
		
		/*
		 * 아이디어 벽이 있는 위치의 가중치를 1로 두고, 벽이 없는 공간은 0으로 한다.
		 * (0, 0) -> (n-1, n-1)까지 갈 때 가중치의 합이 가장 적은 것을 출력
		 * --> 다익스트라 예제이다
		 * 
		 * 가중치를 저장할 distance 배열 
		 * map 배열 
		 */
		
		map = new char[n][];
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		go();
		
		System.out.println(distance[n-1][n-1]);
	}

}
