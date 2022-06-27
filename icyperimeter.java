import com.sun.security.jgss.GSSUtil;

import java.io.*;
import java.util.*;

public class icyperimeter {
    public static ArrayList<Integer> get_area(boolean[][] graph, boolean[][] visited, int x, int y,int N){ // dfs
        int area = 0;
        int perimeter = 0;
        Stack<List<Integer>> stack = new Stack();
        stack.add(Arrays.asList(x,y));
        while (stack.size() != 0){
            System.out.println(stack);
            List<Integer> node = stack.pop();
            int i = node.get(0);
            int j = node.get(1);
            if (!graph[i][j]){
                perimeter += 1;
                continue;
            }
            area += 1;
            visited[i][j] = true;
            if (i-1 >= 0 && i-1 < N){
                if (!visited[i-1][j])
                    stack.add(Arrays.asList(i-1,j));
            }
            else perimeter += 1;
            if (i+1 >= 0 && i+1 < N) {
                if (!visited[i+1][j])
                    stack.add(Arrays.asList(i+1,j));
            }
            else perimeter += 1;
            if (j-1 >= 0 && j-1 < N) {
                if (!visited[i][j-1])
                    stack.add(Arrays.asList(i,j-1));
            }
            else perimeter += 1;
            if (j+1 >= 0 && j+1 < N) {
                if (!visited[i][j+1])
                    stack.add(Arrays.asList(i,j+1));
            }
            else perimeter += 1;
        }
        ArrayList<Integer> res = new ArrayList();
        res.add(area);
        res.add(perimeter);
        return res;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("perimeter.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        boolean[][] grid = new boolean[N][N];
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            String[] ch = st.nextToken().split("");
            for (int j = 0; j < N; j++){
                grid[i][j] = ch[j].equals("#");
                visited[i][j] = false;
            }
        }
        int maxarea = 0;
        int maxperimeter = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (grid[i][j] && !visited[i][j]){
                    System.out.println("UES");
                    ArrayList<Integer> res = get_area(grid,visited,i,j,N);
                    int area = res.get(0);
                    int perimeter = res.get(1);
                    if (area >= maxarea){
                        maxarea = area;
                        maxperimeter = perimeter;
                    }
                }
            }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));
        pw.print(maxarea + " ");
        pw.print(maxperimeter);
        pw.close();

    }
}
