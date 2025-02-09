import java.util.*;
import java.io.*;

class IceBerg{
	int x;
	int y;

	IceBerg(int x, int y){
		this.x = x;
		this.y = y;
	}
}

// dfs,bfs 알고리즘을 통해 빙하가 2개 이상 분리 되는지 체크하고
// 만약 분리되지 않으면 0을 반환, 분리된다면 분리 될 때 까지 걸린 시간 반환
public class Main {
	public static int N, M; // 2차원 배열의 행과 열
	public static int iceCnt, year; // 분리될 빙하의 갯수, 2개 이상으로 분리 될때 까지 걸리는 시간
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, 1, 0, -1};
	public static int[][] map; // 빙하의 정보를 담을 2차원 배열

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		iceCnt = 0;
		year = 0;
		while ((iceCnt = iceNum()) < 2){
			if (iceCnt == 0){
				year = 0;
				break ;
			}
			deleteIceBerg();
			year++;
		}
		System.out.println(year);
	}

	// 빙하를 녹여 나가면서 몇개의 빙하로 분리 되는지 확인
	public static int iceNum(){
		boolean[][] visited = new boolean[N][M];
		int cnt = 0;

		for (int i = 0; i < N; i++){
			for (int j = 0; j < M; j++){
				if (map[i][j] != 0 && !visited[i][j]){
					dfs(i , j, visited);
					cnt++;
				}
			}
		}
		return cnt;
	}

	public static boolean isValidation(int nx, int ny){
		return (nx >= 0 && ny >= 0 && nx < N && ny < M);
	}

	// map 배열에 0이 아닌 부분을 true로 변경해 나간다.
	public static void dfs(int x, int y, boolean[][] visited){
		int nx, ny;
		
		visited[x][y] = true;
		for (int i = 0; i < 4; i++){
			nx = x + dx[i];
			ny = y + dy[i];

			if (isValidation(nx, ny) && map[nx][ny] != 0 && !visited[nx][ny]){
				dfs(nx, ny, visited);
			}
		}
	}

	// 빙하를 녹이기 위한 함수
	// visited 함수를 만들어서 중복으로 빙하를 녹이는 걸 방지지
	public static void deleteIceBerg(){
		Queue<IceBerg> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		int nx, ny;

		for (int i = 0; i < N; i++){
			for (int j = 0; j < M; j++){
				if (map[i][j] != 0){
					q.add(new IceBerg(i, j));
					visited[i][j] = true;
				}
			}
		}

		while (!q.isEmpty()){
			IceBerg iceBerg = q.poll();
			int deleteNum = 0; // 빙하가 접하고 있는 바다의 갯수

			for (int i = 0; i < 4; i++){
				nx = iceBerg.x + dx[i];
				ny = iceBerg.y + dy[i];
				if (isValidation(nx, ny) && map[nx][ny] == 0 && !visited[nx][ny])
					deleteNum++;
			}

			if (map[iceBerg.x][iceBerg.y] - deleteNum < 0)
				map[iceBerg.x][iceBerg.y] = 0;
			else
				map[iceBerg.x][iceBerg.y] -= deleteNum;
		}
	}
}
