import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Collections;
public class cowdanceshow {
    public static boolean simulate(ArrayList<Integer> order, int K, ArrayList<Integer> cows, int tmax){
        int t = 0;
        while (K != cows.size()){
            int min = Collections.min(order);
            if (min > t){
                t = min;
                if (t > tmax){
                    return false;
                }
            }
            int cnt=  0;
            while (order.contains(min)){
                order.remove(order.indexOf(min));
                cnt += 1;
            }
            for (int i = 0; i < cnt; i++){
                K += 1;
                if (K == cows.size())
                    break;
                if (order.size() > K + 1){
                    K -= 1;
                    break;
                }
                order.add(cows.get(K)+t);
            }
        }
        if (Collections.max(order) > tmax){
            return false;
        }
        return true;
    }
    public static int binarySearch(ArrayList<Integer> cows,int tmax){
        int high = cows.size()-1;
        int low = 0;
        int mid;
        while (high >= low){
            mid = (high + low) / 2;
            ArrayList<Integer> order = new ArrayList();
            for (int i =0;i<mid+1;i++){
                order.add(cows.get(i));
            }
            boolean bool = simulate(order, mid,cows,tmax);
            if (bool){
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }
        return low + 1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowdance.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int tmax = Integer.parseInt(st.nextToken());
        ArrayList<Integer> cows = new ArrayList();
        for (int i = 0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int cow = Integer.parseInt(st.nextToken());
            cows.add(cow);
        }
        int ans = binarySearch(cows,tmax);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
        pw.println(ans);
        pw.close();
    }
}
