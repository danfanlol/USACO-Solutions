import java.io.*;
import java.util.StringTokenizer;

public class loanrepayment {
    public static boolean works(long deadline, long N, long minimum, long X){
        long days = 0;
        while (days != deadline){
            long y = Math.max(minimum,N/X);
            if (y == minimum) return N-y - ((deadline - (days+1)) * minimum) <= 0;
            long lowest = X*y;
            long dayslog = (long)Math.ceil((N-lowest)/y)+1;
            N -= y*dayslog;
            if (N <= 0) return true;
            days += dayslog;
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("loan.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        long deadline = Long.parseLong(st.nextToken());
        long minimum = Long.parseLong(st.nextToken());
        long left = 1;
        long right = N;
        while (left <= right){
            long mid = (right + left) / 2;
            if (works(deadline,N,minimum,mid)){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("loan.out")));
        pw.println(right);
        pw.close();
    }
}
