import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class notimetopaint {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] name = br.readLine().split(" " );
        System.out.println(name);
        int N = Integer.parseInt(name[0]);
        int M = Integer.parseInt(name[1]);
        String string = br.readLine();
        HashMap<Character,Integer> map = new HashMap();
        int[] sums = new int[N];
        int sum = 1;
        map.put(string.charAt(0),0);
        sums[0] = sum;
        char last = string.charAt(0);
        for (int i= 1; i < N;i++){
            char ch = string.charAt(i);
            if (ch > last){
                map.put(ch,i);
                sum += 1;
            }
            else if (ch < last){
                if (!map.containsKey(ch)){
                    map.put(ch,i);
                    sum += 1;
                }
                else{
                    for (int j = map.get(ch); j < i;j++){
                        if (string.charAt(j) < ch){
                            sum +=1;
                            break;
                        }
                    }
                    map.put(ch,i);
                }
            }
            sums[i] = sum;
            last = ch;
        }
        HashMap<Character,Integer> map2 = new HashMap();
        int[] sums2 = new int[N];
        int sum2 = 1;
        map2.put(string.charAt(N-1),N-1);
        sums2[0] = sum2;
        last = string.charAt(N-1);
        for (int i= N-1; i >= 0;i--){
            char ch = string.charAt(i);
            if (ch > last){
                map2.put(ch,i);
                sum2 += 1;
            }
            else if (ch < last){
                if (!map2.containsKey(ch)){
                    map2.put(ch,i);
                    sum2 += 1;
                }
                else{
                    for (int j = map2.get(ch); j > i;j--){
                        if (string.charAt(j) < ch){
                            sum2 +=1;
                            break;
                        }
                    }
                    map2.put(ch,i);
                }
            }
            sums2[N-i-1] = sum2;
            last = ch;
        }
        StringBuilder s = new StringBuilder();
        for (int i= 0 ; i < M;i++){
            name = br.readLine().split(" " );
            int start = Integer.parseInt(name[0]);
            int end = Integer.parseInt(name[1]);
            int ans = 0;
            if (start == 1) ans += 0;else ans += sums[start-2];
            if (end == N) ans += 0; else ans += sums2[N-end-1];
            if (i != M-1) s.append(ans).append('\n');
            else s.append(ans);
        }
        System.out.println(s);
    }
}
