import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class data implements Comparable<data>{
    public int arrival, senority, timeeating;
    public data(int a, int s, int t){
        arrival = a;
        senority = s;
        timeeating = t;
    }
    public int compareTo(data other) {
        if (this.arrival==other.arrival)
            return this.senority - other.senority;
        return this.arrival - other.arrival;
    }
}
class data2 implements Comparable<data2>{ //for priority Queue
    public int arrival, senority, timeeating;
    public data2(int a, int s, int t){
        arrival = a;
        senority = s;
        timeeating = t;
    }
    public int compareTo(data2 other) {
        return this.senority - other.senority;
    }
}
public class convention2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("convention2.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        data[] data = new data[N];
        for (int i = 0; i < N;i++){
            st = new StringTokenizer(br.readLine());
            int arrival = Integer.parseInt(st.nextToken());
            int timeeating = Integer.parseInt(st.nextToken());
            data[i] = new data(arrival,i,timeeating);
        }
        Arrays.sort(data);
        PriorityQueue<data2> pq = new PriorityQueue();
        int curidx = 0;
        int time = 0;
        int max = 0;
        while (true){
            if (pq.size() == 0){
                time = data[curidx].arrival + data[curidx].timeeating;
                curidx += 1;
            }
            else{
                data2 element = pq.poll();
                max = Math.max(max,time-element.arrival);
                time += element.timeeating;
            }
            if (curidx == N)
                break;
            while (data[curidx].arrival < time){
                pq.add(new data2(data[curidx].arrival,data[curidx].senority,data[curidx].timeeating));
                curidx += 1;
                if (curidx == N) break;
            }
            if (curidx == N)
                break;
        }
        while (pq.size() != 0){
            data2 element = pq.poll();
            max = Math.max(max,time-element.arrival);
            time += element.timeeating;
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("convention2.out")));
        pw.println(max);
        pw.close();
    }
}
