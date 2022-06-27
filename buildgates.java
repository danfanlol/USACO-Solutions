import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

class pair4{
    public int x,y;
    public pair4(int s, int d){
        x = s;
        y = d;
    }
}
// Imagine the grid is a graph where every empty spot is connected to each other and are separated by the fences. The fences have the possibliity of splitting the graph into multiple regions.
// The number of these regions, K, can then be subtracted by 1 to get the answer. This is because a gate connects two regions. As for implementation, double the length of the fence
// so one unit is really two.
public class buildgates {
    public static void floodfill(boolean[][] grid, int i, int j){
        Stack<pair4> stack = new Stack();
        stack.add(new pair4(i,j));
        while (stack.size() != 0){
            pair4 pair = stack.pop();
            grid[pair.x][pair.y] = true;
            if (pair.x+1 >= 0 && pair.x+1 < 2000 && pair.y >= 0 && pair.y < 2000 && !grid[pair.x+1][pair.y]) stack.add(new pair4(pair.x+1,pair.y));
            if (pair.x-1 >= 0 && pair.x-1 < 2000 && pair.y >= 0 && pair.y < 2000 && !grid[pair.x-1][pair.y]) stack.add(new pair4(pair.x-1,pair.y));
            if (pair.x >= 0 && pair.x < 2000 && pair.y-1 >= 0 && pair.y-1 < 2000 && !grid[pair.x][pair.y-1]) stack.add(new pair4(pair.x,pair.y-1));
            if (pair.x >= 0 && pair.x < 2000 && pair.y+1 >= 0 && pair.y+1 < 2000 && !grid[pair.x][pair.y+1]) stack.add(new pair4(pair.x,pair.y+1));
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("gates.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        String s = st.nextToken();
        boolean[][] grid = new boolean[2000][2000];
        int x = 1000;
        int y = 1000;
        for (int i =0 ; i< N;i++){
            if (s.charAt(i) == 'N'){
                grid[x][y+1] = true;
                grid[x][y+2] = true;
                y += 2;
            }
            else if (s.charAt(i) == 'E'){
                grid[x+1][y] = true;
                grid[x+2][y] = true;
                x += 2;
            }
            else if(s.charAt(i) == 'S'){
                grid[x][y-1] = true;
                grid[x][y-2] = true;
                y -= 2;
            }
            else{
                grid[x-1][y] = true;
                grid[x-2][y] = true;
                x -= 2;
            }
        }
        int ans= 0 ;
        for (int i =0 ;i < 2000;i++){
            for (int j = 0; j < 2000;j++){
                if (!grid[i][j]){
                    floodfill(grid,i,j);
                    ans += 1;
                }
            }
        }
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gates.out")));
        pw.println(ans-1);
        pw.close();
    }
}
