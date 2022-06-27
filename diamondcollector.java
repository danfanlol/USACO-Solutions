import java.io.*;
import java.util.*;
public class diamondcollector {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("diamond.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] nums = new int[n];
        for (int i= 0; i <n;i++){
            st = new StringTokenizer(br.readLine());
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);
        int[] possibilities = new int[n];
        for (int i = 0; i < n;i++){
            int number = nums[i];
            int cnt = 0;
            for (int j = i; j < n;j++){
                if (number + k >= nums[j])
                    cnt += 1;
                else
                    break;
            }
            possibilities[i] = cnt;
        }
        int max = 0;
        for (int i = 0;i <n;i++){
            int max2 = 0;
            for (int j = i + possibilities[i]; j < n;j++){
                max2 = Math.max(max2,possibilities[i]+possibilities[j]);
            }
            max = Math.max(max,max2);
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("diamond.out")));
        pw.println(max);
        pw.close();
    }
}
