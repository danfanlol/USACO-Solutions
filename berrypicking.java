import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// Take the optimal set of berries that Bessie gives to Elsie and Bessie keeps herself. In this set, the minimum berries
// she gives to Elsie is K while the maximum berries she keeps herself is also K. We need to find this K. Since it's only 1000 at most,
// we can simulate through all possible K's and take the maximum number of berries Bessie can get for herself.
// This part can be solved with a greedy strategy. We now want to minimize the berries of Elsie and maximize the berries of Bessie.
// It makes no sense to give more than B berries to Elsie since we want to minimize her number as much as possible, so
// simply give Elsie B berries in each of her K/2 buckets (if it isn't possible, then skip to next B value)
// Afterward, fill as many of Bessie's buckets with B berries (to maximize her number) as possible and if there are still buckets that are empty,
// Sort the remaining berry trees by decreasing order and fill the empty buckets with the largest trees.

public class berrypicking {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] baskets = new int[N];
        for (int i= 0 ; i < N;i++) baskets[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(baskets);
        int max = 0;
        for (int i =1; i < baskets[N-1];i++){
            int[] arr = baskets.clone();
            int curr = N-1;
            int cnt= 0 ;
            while (cnt < k/2){
                if (curr < 0) break;
                if (arr[curr] >= i){
                    arr[curr] -= i;
                    cnt += 1;
                }
                else curr--;
            }
            if (curr < 0) continue;
            Arrays.sort(arr);
            cnt= 0;
            int total = 0;
            curr = N-1;
            while (cnt < k/2){
                if (curr < 0) break;
                if (arr[curr] >= i){
                    arr[curr] -= i;
                    total += i;
                    cnt += 1;
                }
                else curr--;
            }
            if (curr < 0){
                curr = N-1;
                Arrays.sort(arr);
                while (cnt < k/2){
                    total += arr[curr];
                    cnt += 1;
                    curr--;
                    if (curr < 0) break;
                }
            }
            max = Math.max(total,max);
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("berries.out")));
        pw.println(max);
        pw.close();
    }
}
