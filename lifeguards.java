import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class lifeguard implements Comparable<lifeguard>{
    int start, end;
    public lifeguard(int s, int e){
        start = s;
        end = e;
    }
    public int compareTo(lifeguard other){
        if (this.start == other.start) return this.end-other.end;
        return this.start - other.start;
    }
}
public class lifeguards {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lifeguards.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        lifeguard[] lifeguards = new lifeguard[N];
        for (int i =0 ; i < N;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lifeguards[i] = new lifeguard(start,end);
        }
        Arrays.sort(lifeguards);
        int b = 0;
        int minimum = Integer.MAX_VALUE;
        int minidx = 0;
        for (int i= 0 ; i < N;i++){
            int areacovered = lifeguards[i].end - lifeguards[i].start;
            int front = Math.max(0,b-lifeguards[i].start);
            areacovered -= front;
            areacovered = Math.max(areacovered,0);
            if (areacovered == 0){
                minidx = i;
                minimum =0;
                continue;
            }
            int start = 0;
            int end =0;
            for (int j = i+1;j < N;j++){
                if (lifeguards[j].start > lifeguards[i].end) break;
                if (lifeguards[j].start > end){
                    areacovered -= end - start;
                    start = Math.max(b,lifeguards[j].start);
                    end = Math.max(b,Math.min(lifeguards[j].end,lifeguards[i].end));
                }
                else end = Math.min(lifeguards[i].end,Math.max(end,lifeguards[j].end));
            }
            areacovered -= end - start;
            b = Math.max(b,lifeguards[i].end);
            if (areacovered < minimum){
                minidx = i;
                minimum = areacovered;
            }
        }
        int start = 0;
        int end = 0;
        int time = 0;
        for(int i= 0 ;i< N;i++){
            if (i == minidx) continue;
            if (lifeguards[i].start > end){
                time += end-start;
                start = lifeguards[i].start;
                end = lifeguards[i].end;
            }
            else end = Math.max(end,lifeguards[i].end);
        }
        time += end - start;
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));
        pw.println(time);
        pw.close();
    }
}
