import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_11399_ATM_실버3_김동률_128ms {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		/*
		 * 오름 차순 정렬 후 
		 * 더해진 값을 총합에 더한다 -> 반복
		 */
		
		int[] atm = new int[n];
		for(int i=0; i<n; i++) {
			atm[i] = Integer.parseInt(st.nextToken());
		}
		// 오름차순 정렬
		Arrays.sort(atm);
		int total = 0;
		int added = 0;
		for(int time:atm) {
			added+=time;
			total+=added;
		}
		System.out.println(total);
	}
}
