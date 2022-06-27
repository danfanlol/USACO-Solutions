import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class acowdemia {
    public static boolean simulate(int h, int K, int L, int[] numbers, int length){
        int[] nums = new int[h];
        int cnt = 0;
        for (int i = length-1; i >=0 ; i--){
            if (cnt == h)
                break;
            nums[cnt] = numbers[i];
            cnt += 1;
        }
        for (int i= 0; i < h;i++){
            if (nums[i] < h){
                int buffer = nums[i];
                if (h-buffer > K)
                    return false;
                for (int j = i;j < i+L;j++){
                    if (j == h) break;
                    nums[j] += h-buffer;
                }
                K -= h-buffer;
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader sd = new BufferedReader(new InputStreamReader(System.in));
        String[] name = sd.readLine().split(" " );
        int N = Integer.parseInt(name[0]);
        String[] name2 = sd.readLine().split(" " );
        int[] numbers = new int[N];
        for (int i= 0; i < N;i++){
            numbers[i] = Integer.parseInt(name2[i]);
        }
        Arrays.sort(numbers);
        int K = Integer.parseInt(name[1]);
        int L = Integer.parseInt(name[2]);
        int low = 0;
        int high = 100000;
        while (low <= high){
            int mid = (low+ high) /2;
            if (simulate(mid,K,L,numbers, N)){
                low = mid + 1;
            }
            else{
                high = mid- 1;
            }
        }
        System.out.println(high);
    }
}
