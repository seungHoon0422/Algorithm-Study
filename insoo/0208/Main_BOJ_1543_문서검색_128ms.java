import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main_BOJ_1543_문서검색_128ms {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        String target = br.readLine();

        int strLength = str.length();
        int targetLength = target.length();

        int resCnt = 0;
        for (int i = 0; i <= strLength - targetLength;) {
            int equalCnt = 0;
            for (int j = 0; j < targetLength; j++) {
                if (str.charAt(i + j) == target.charAt(j)) {
                    equalCnt++;
                } // end of if
            } // end of for
            if (equalCnt == targetLength) {
                i += targetLength;
                resCnt++;
            } else {
                i++;
            }
        } // end of for
        System.out.println(resCnt);
    } // end of main
}
