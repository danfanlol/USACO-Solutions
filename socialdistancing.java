import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class socialdistancing {
    public static boolean compute (long[] intervals, long num, int N){
        int cowsplaced = 1;
        int curinterval = 1; //right point of current interval
        long lastpos = intervals[0];
        long mindist = Long.MAX_VALUE;
        while (true){
            if (lastpos + num <= intervals[curinterval]){
                //next rightmost point is in current interval
                cowsplaced += 1;
                lastpos += num;
                mindist = Math.min(mindist,num);
            }
            else if (lastpos + num > intervals[curinterval]){
                // next rightmost point is outside of current interval
                while (true){
                    curinterval += 2;
                    if (curinterval >= intervals.length){
                        break;
                    }
                    if (intervals[curinterval] >= lastpos + num){
                        break;
                    }
                }
                if (curinterval >= intervals.length)
                    break;
                if (lastpos + num < intervals[curinterval-1]){
                    mindist = Math.min(mindist,intervals[curinterval-1]-lastpos);
                    lastpos = intervals[curinterval-1];
                }
                else{
                    lastpos += num;
                    mindist = Math.min(mindist,num);
                }
                cowsplaced += 1;
            }
            if (cowsplaced == N)
                break;
        }
        if (mindist >= num && cowsplaced == N){
            return true;
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("socdist.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[] intervals = new long[M*2];
        long cnt = 0;
        for (int i = 0 ; i < M;i++){
            st = new StringTokenizer(br.readLine());
            long a = Integer.parseInt(st.nextToken());
            long b = Integer.parseInt(st.nextToken());
            intervals[(int) cnt] = a;
            intervals[(int) (cnt+1)] = b;
            cnt += 2;
        }
        Arrays.sort(intervals);
        long r = 0;
        long l = intervals[M*2-1];
        while (r <= l){
            long mid = (r+l)/2;
            boolean res = compute(intervals,mid,N);
            if (res){
                r = mid + 1;
            }
            else{
               l = mid - 1;
           }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("socdist.out")));
        pw.println(l);
        pw.close();
    }
}
