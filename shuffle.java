import java.io.*;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class shuffle {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("shuffle.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] cnt = new int[N];
        HashMap<Integer,Integer> graph = new HashMap();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(st.nextToken());
            graph.put(i+1,a);
            cnt[a-1] += 1;
        }
        Stack<Integer> stack = new Stack<Integer>();
        for (int i= 0 ; i < N;i++) if (cnt[i] == 0) stack.push(i+1);
        int cnts = 0;
        while (stack.size() != 0){
            int node = stack.pop();
            cnts += 1;
            cnt[graph.get(node)-1] -= 1;
            if (cnt[graph.get(node)-1] == 0) stack.push(graph.get(node));
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));
        pw.println(N-cnts);
        pw.close();
    }
}
