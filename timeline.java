import java.io.*;
import java.util.*;


// You have to start with the session that doesn't come after any other sessions because that is the only session that you know
// certain of its minimum time. You can then use that session to solve the times for other sessions and we want to take the max
// of the possible times of each session because taking the minimum will lead to a inconsistency.
public class timeline {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("timeline.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        HashMap<Integer, ArrayList<int[]>> graph = new HashMap();
        HashMap<Integer,Integer> times = new HashMap();
        HashMap<Integer,Integer> maxs = new HashMap();
        int[] days = new int[N];
        for (int i =0 ; i < N;i++) days[i] = Integer.parseInt(st.nextToken());
        for (int i =0 ; i < N;i++){
            graph.put(i+1, new ArrayList<int[]>());
            times.put(i+1,0);
            maxs.put(i+1,0);
        }
        for (int i= 0 ; i < C;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            int[] array = new int[2];
            array[0] = end;
            array[1] = val;
            graph.get(start).add(array);
            times.put(end,times.get(end)+1);
        }
        LinkedList<Integer> stack = new LinkedList();
        for (int i =0 ; i < N;i++) if (times.get(i+1) == 0) stack.push(i+1);
        while(stack.size() != 0){
            int node = stack.poll();
            maxs.put(node,Math.max(maxs.get(node),days[node-1]));
            for (int i= 0 ; i < graph.get(node).size();i++){
                int end = graph.get(node).get(i)[0];
                int val = graph.get(node).get(i)[1];
                times.put(end,times.get(end)-1);
                if (times.get(end) == 0) stack.push(end);
                maxs.put(end,Math.max(maxs.get(end),maxs.get(node) + val));
            }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("timeline.out")));
        for (int i= 0 ; i < N;i++) pw.println(maxs.get(i+1));
        pw.close();
    }
}
