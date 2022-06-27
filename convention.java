import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class convention {
    public static boolean cmp(int maximum, int[] arrivals, int capacity, int buses){
        int maxdiff = 0;
        int idx = -1;
        for (int i= 0; i < buses;i++){
            idx += 1;
            if (idx >= arrivals.length)
                break;
            int first = arrivals[idx];
            int difference = 0;
            int cowsin = 1;
            while (true){
                cowsin += 1;
                idx += 1;
                if (idx >= arrivals.length)
                    break;
                difference = arrivals[idx] - first;
                if (difference > maximum){
                    difference = arrivals[idx-1] - first;
                    idx -= 1;
                    break;
                }
                if (cowsin == capacity)
                    break;
            }
            maxdiff = Math.max(maxdiff,difference);
        }
        if (maxdiff <= maximum && idx >= arrivals.length-1)
            return true;
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("convention.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int numberofbuses = Integer.parseInt(st.nextToken());
        int capacity = Integer.parseInt(st.nextToken());
        int[] arrivals = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N;i++){
            int arrival = Integer.parseInt(st.nextToken());
            arrivals[i] = arrival;
        }
        Arrays.sort(arrivals);
        int low = 0;
        int high = arrivals[N-1];
        while (low <= high){
            int mid = (low + high) / 2;
            if (cmp(mid,arrivals,capacity,numberofbuses))
                high = mid - 1;
            else
                low = mid + 1;
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));
        pw.println(low);
        pw.close();
    }
}
