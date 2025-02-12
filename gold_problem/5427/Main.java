/*
최초 문제파악 : 시작하는 @ 위치에서 bfs로 갈 수 있는 map의 위치로 이동, 이 때 map의 상태를 true로 변경 
불의 위치 또한 4방으로 번지기 때문에 상근이가 이동할 때마다 불의 위치 또한 같이 갱신
bfs 종료 기저 조건을 map의 위치 밖으로 벗어났을 때로 지정 또는 더 이상 그래프 탐색을 할 수 없는 상황
즉 탈출 경로가 없을 때 종료
*/
import java.io.*;
import java.util.*;

// 불과 상근이의 위치 정보를 담아 queue에 저장하기 위한 class
class Node{ 
	int x;
	int y;
	int time;
	Node(int x, int y, int time){
		this.x = x;
		this.y = y;
		this.time = time;
	}
}

public class Main {
	static int T, N, M;
	static int escape;
	static char[][] map;
	static Queue<Node> fire; // 불의 위치를 저장하기 위한 queue
	static Queue<Node> person; // 상근이의 위치정보를 저장하기 위한 queue
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < T; test_case++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[M][N];
			
			fire = new LinkedList<>();
			person = new LinkedList<>();
			
			for (int i = 0; i < M; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++){
					map[i][j] = str.charAt(j); // 건물을 표시할 map 초기화
					if (map[i][j] == '@') // 상근이의 초기 위치 입력
						person.offer(new Node(i,j,0));
					else if (map[i][j] == '*') // 불이 시작하는 위치 입력
						fire.offer(new Node(i,j,0));
				}
			}
			escape = 0;
			bfs();
			if (escape == 0)
				sb.append("IMPOSSIBLE").append('\n');
			else
				sb.append(escape).append('\n');
		}
		System.out.println(sb.toString());
	}

	//탐색하는 map 의 범위를 넘어가는지 check 하는 메소드
	public static boolean isValidation(int nx, int ny){ 
		return (nx >= 0 && ny >= 0 && nx < M && ny <N);
	}

	// 상근이 정보가 담긴 queue가 빌 때까지 반복 => queue가 비었다는 것은 상근이가 갈 수 있는 모든 경로를 다 갔음을 의미
	public static void bfs(){
		while (!person.isEmpty()){
			int nx, ny;
			int queSize = fire.size();

			for (int i = 0; i < queSize; i++){
				Node node = fire.poll();
				for (int j = 0; j < 4; j++){
					nx = node.x + dx[j];
					ny = node.y + dy[j];
					if (isValidation(nx, ny) && (map[nx][ny] == '.' || map[nx][ny] == '@')){
						map[nx][ny] = '*';
						fire.offer(new Node(nx, ny, 0));
					}
				}
			}

			queSize = person.size();
			for (int i = 0; i < queSize; i++){
				Node node = person.poll();
				for (int j = 0; j < 4; j++){
					nx = node.x + dx[j];
					ny = node.y + dy[j];

					if (!isValidation(nx, ny)){ //4방 탐색을 하는 도중 맵의 밖으로 탐색을 한다면 탈줄 가능하는 의미
						escape = node.time + 1;
						return ;
					}
					if (isValidation(nx, ny) && map[nx][ny] == '.'){ // 불 또는 벽이 아닌 경우 상근이의 위치정보 갱신
						map[nx][ny] = '@';
						person.offer(new Node(nx, ny, node.time + 1));
					}
				}
			}
		}
	}
}
