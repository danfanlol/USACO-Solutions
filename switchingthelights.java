import java.io.*;
import java.util.*;

public class switchingthelights {
    public static boolean floodfill(ArrayList<Integer> source,boolean[][] visited){
        boolean[][] grid = new boolean[visited.length][visited.length];
        int i = source.get(0)-1;
        int j = source.get(1)-1;
        Stack<ArrayList<Integer>> stack = new Stack();
        ArrayList<Integer> src = new ArrayList();
        src.add(i);
        src.add(j);
        if (i == 0 && j == 0)
            return true;
        if (i-1 >= 0){
            if (visited[i-1][j])
                return true;
        }
        if (i+1 < visited.length){
            if (visited[i+1][j])
                return true;
        }
        if (j -1 >= 0){
            if (visited[i][j-1])
                return true;
        }
        if (j+1 < visited.length){
            if (visited[i][j+1])
                return true;
        }
        return false;
    }
    public static int bfs(boolean[][] visited, HashMap<ArrayList,ArrayList<ArrayList>> connections, boolean[][] lightson,int N){
        int cnt = 0;
        Stack<ArrayList<Integer>> stack = new Stack(); // use pop()
        ArrayList<Integer> starting = new ArrayList();
        lightson[0][0] = true;
        starting.add(1);
        starting.add(1);
        stack.add(0,starting);
        while (stack.size() != 0){
            ArrayList<Integer> node = stack.pop();
            int i = node.get(0);
            int j = node.get(1);
            if (i < 1 || i > N || j < 1 || j > N)
                continue;
            if (visited[i-1][j-1])
                continue;
            if (floodfill(node,visited)){
                visited[i-1][j-1] = true;
                cnt += 1;
                if (connections.containsKey(node)){
                    for (int e =0; e < connections.get(node).size(); e ++){
                        lightson[(int)connections.get(node).get(e).get(0)-1][(int)connections.get(node).get(e).get(1)-1] = true;
                        if (!stack.contains(connections.get(node).get(e)))
                            stack.add(connections.get(node).get(e));
                    }
                }
                i -= 1;
                j -= 1;
                if (i-1 >= 0){
                    if (lightson[i-1][j] && !visited[i-1][j]){
                        ArrayList<Integer> poss1 = new ArrayList();
                        poss1.add(i);
                        poss1.add(j+1);
                        if (!stack.contains(poss1))
                            stack.add(poss1);
                    }
                }
                if (i + 1 < N){
                    if (lightson[i+1][j] && !visited[i+1][j]){
                        ArrayList<Integer> poss2 = new ArrayList();
                        poss2.add(i+2);
                        poss2.add(j+1);
                        if (!stack.contains(poss2))
                            stack.add(poss2);
                    }
                }
                if (j-1 >= 0){
                    if (lightson[i][j-1] && !visited[i][j-1]){
                        ArrayList<Integer> poss3 = new ArrayList();
                        poss3.add(i+1);
                        poss3.add(j);
                        if (!stack.contains(poss3))
                            stack.add(poss3);
                    }
                }
                if (j+1 < N){
                    if (lightson[i][j+1] && !visited[i][j+1]){
                        ArrayList<Integer> poss4 = new ArrayList();
                        poss4.add(i+1);
                        poss4.add(j+2);
                        if (!stack.contains(poss4))
                            stack.add(poss4);
                    }
                }
            }
        }
        return cnt;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lightson.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[][] visited = new boolean[N][N];
        boolean[][] lightson = new boolean[N][N];
        HashMap<ArrayList,ArrayList<ArrayList>> connections = new HashMap();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            ArrayList<Integer> pair1 = new ArrayList();
            ArrayList<Integer> pair2 = new ArrayList();
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            pair1.add(x);
            pair1.add(y);
            pair2.add(a);
            pair2.add(b);
            if (connections.containsKey(pair1)) {
                connections.get(pair1).add(pair2);
            } else {
                ArrayList<ArrayList> starting = new ArrayList();
                starting.add(pair2);
                connections.put(pair1, starting);
            }
        }
        int ans = bfs(visited,connections,lightson,N);
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if(!visited[i][j] && lightson[i][j]){
                    ans += 1;
                }
            }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lightson.out")));
        pw.println(ans);
        pw.close();
    }
}
