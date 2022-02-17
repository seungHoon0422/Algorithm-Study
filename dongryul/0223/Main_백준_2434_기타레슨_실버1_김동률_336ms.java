import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_2434_기타레슨_실버1_김동률_336ms {
	private static int N;
	private static int M;
	private static int[] lecture;


	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lecture = new int[N];
		
		int max = 0;
		int low = 0;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			lecture[i] = num;
			max+=num;
			low = Math.max(low, num);
		}
		
		// 무엇을 이분 탐색으로 해야할까? -> 블루레이의 크기를 임의의 mid로 설정하고 
		// 임의의 mid를 어떻게 구할까 max/M? 이걸로했는데 안됐음
		// 최대값이랑 max랑 해보자, 최대값을 사용해야 적어도 강의를 나누지 않으니까
		// 해당 mid일 때 count을 하여 count가 M이면 M이 아닐 때까지 감소, 
		// count <= M 이면 high를 mid-1로 설정하고 다시
		// count >M 이면 low를 mid+1로 설정하고 다시
		
		
		int high = max;
		int mid = 0;
		while(low<=high) {
			mid = (low+high)/2;
			if(count(mid)) {
				high = mid-1;
			}
			else {
				low = mid+1;
			}
		}
		
		// 디버깅 하다보니 low가 답이네요..
		// 제가 이해한 바로 최소 개수인 M개를 사용하는 블루레이 크기 중 최소값을 구하는 거라 low를 사용하는거 같아요
		System.out.println(low);
		
		
	} // end of main
	
	private static boolean count(int mid) {
		int cnt = 1;
		int blueray = mid;
		for(int i=0; i<N; i++) {
			if(blueray-lecture[i]<0) {
				blueray = mid - lecture[i];
				cnt++;
			}
			else {
				blueray-=lecture[i];
			}
		}// end of for
		if(cnt<=M) {
			return true;
		}
		else {
			return false;
		}
	} // end of count
	
	
} // end of class
