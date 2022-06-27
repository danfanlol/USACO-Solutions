import java.io.IOException;
import java.util.*;
import java.io.*;
public class moocast {
    public static void main(String[] args) throws IOException {
         ArrayList<ArrayList<Integer>> pairs = new ArrayList();
         ArrayList<Integer> powers = new ArrayList();
         BufferedReader br = new BufferedReader(new FileReader("moocast.in"));
         StringTokenizer st = new StringTokenizer(br.readLine());
         int n = Integer.parseInt(st.nextToken());
         for (int i = 0; i < n;i++){
             st = new StringTokenizer(br.readLine());
             ArrayList<Integer> pair = new ArrayList();
             pair.add(Integer.parseInt(st.nextToken()));
             pair.add(Integer.parseInt(st.nextToken()));
             pairs.add(pair);
             powers.add(Integer.parseInt(st.nextToken()));
         }
         HashMap<ArrayList<Integer>, ArrayList<ArrayList<Integer>>> graph = new HashMap();
         for (int i = 0; i < powers.size(); i++){
             int power = powers.get(i);
             int x = pairs.get(i).get(0);
             int y = pairs.get(i).get(1);
             for (int j = 0; j < powers.size(); j++){
                 if (i == j)
                     continue;
                 if (Math.pow(Math.pow(pairs.get(j).get(0) -x,2) + Math.pow(pairs.get(j).get(1) - y,2),.5) <= power){
                     if (graph.containsKey(pairs.get(i)))
                         graph.get(pairs.get(i)).add(pairs.get(j));
                     else{
                         ArrayList<ArrayList<Integer>> connections = new ArrayList();
                         connections.add(pairs.get(j));
                         graph.put(pairs.get(i),connections);
                     }
                 }
             }
         }
         int max = 0; 
         for (int i = 0; i < powers.size(); i++){
             int ans = dfs(pairs.get(i),graph);
             max = Math.max(ans,max);
         }
         PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
         pw.println(max);
         pw.close();
    }
    public static int dfs(ArrayList<Integer> pair, HashMap<ArrayList<Integer>, ArrayList<ArrayList<Integer>>> graph){
        int cnt = 0;
        Stack<ArrayList<Integer>> nodes = new Stack();
        nodes.push(pair);
        HashMap<ArrayList<Integer>, Boolean> visited = new HashMap();
        visited.put(pair,true);
        while (nodes.size() != 0){
            cnt += 1;
            ArrayList<Integer> node = nodes.pop();
            if (graph.containsKey(node)){
                for (int i = 0; i < graph.get(node).size(); i++){
                    pair = graph.get(node).get(i);
                    if (!visited.containsKey(pair)){
                        visited.put(pair,true);
                        nodes.push(pair);
                    }
                }
            }
        }
        return cnt;
    }
}
