import java.util.*;
import java.io.*;
public class Main {
	static int N,M;
	static int[] parents;

	static void make(){
		parents = new int[N + 1];
		for (int i = 0; i <= N; i++){
			parents[i] = i;
		}
	}

	static int find(int x){
		if (parents[x] == x)
			return parents[x];
		return parents[x] = find(parents[x]);
	}

	static void union(int x, int y){
		int rootX = find(x);
		int rootY = find(y);

		if (rootX == rootY){
			return ;
		}

		if (rootX < rootY){
			parents[rootY] = rootX;
		}else{
			parents[rootX] = rootY;
		}
	}
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		make();

		for (int i = 0; i < M; i++){
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			if (cmd == 0){
				union(x, y);
			}else{
				int xVal = find(x);
				int yVal = find(y);
				if (xVal == yVal){
					sb.append("YES").append('\n');
				}else{
					sb.append("NO").append('\n');
				}
			}
		}
		System.out.println(sb.toString());
	}
}
