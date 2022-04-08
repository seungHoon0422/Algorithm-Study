import java.util.Scanner;

/** Main_백준_11053_가장긴증가하는부분수열_실버2_176ms*/
public class Main_백준_11053_가장긴증가하는부분수열_실버2_176ms {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * 수업 떄 배운 LIS 적용하면 된다...
		 */
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int [] arr = new int[n];
		int [] LIS = new int[n];
		
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		} 
		sc.close();
		
		int max = 0;
		for (int i = 0; i < n; i++) {
			LIS[i]=1; 
			
			for(int j=0; j<i; j++) {
				if(arr[j] < arr[i] && LIS[i] <= LIS[j]) {
					LIS[i] = LIS[j]+1;
				}
			}
			
			if(max<LIS[i]) max = LIS[i];
		}
		
		System.out.println(max);
	} // end of main

} // end of class
