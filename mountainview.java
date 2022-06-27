import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


class interval implements Comparable<interval>{
    public int x,y;
    public interval(int x1, int y1){
        x = x1;
        y = y1;
    }
    public int compareTo(interval other){
        if (other.x == this.x){
            return other.y - this.y;
        }
        return this.x-other.x;
    }
}
public class mountainview {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("mountains.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        interval[] segments = new interval[N];
        for (int i = 0; i < N;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            segments[i] = new interval(x-y,x+y);
        }
        Arrays.sort(segments);
        int ans = N;
        int a = segments[0].x;
        int b = segments[0].y;
        for (int i= 1 ; i < N;i++){
            if (segments[i].y > b){
                a = segments[i].x;
                b = segments[i].y;
                continue;
            }
            ans -= 1;
            a = segments[i].x;
            b = Math.max(b,segments[i].y);
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));
        pw.println(ans);
        pw.close();
    }
}
