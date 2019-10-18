import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class homework {
	private static int heuristic1(int sx,int sy,int dx,int dy) {
		return Math.abs(sx-dx)+Math.abs(sy-dy);
	}
	private static int heuristic2(int sx,int sy,int dx,int dy,int z1,int z2) {
		return Math.abs(sx-dx)+Math.abs(sy-dy)+Math.abs(z1-z2);
	}
	private static int heuristic3(int sx,int sy,int dx,int dy) {
		return (int) Math.sqrt((sx-dx)*(sx-dx)+(sy-dy)*(sy-dy));
	}
	private static int heuristic4(int sx,int sy,int dx,int dy,int z1,int z2) {
		return (int) Math.sqrt((sx-dx)*(sx-dx)+(sy-dy)*(sy-dy)+(z1-z2)*(z1-z2));
	}
	
	private static Stack<Pair> myA1(int n, int m, int sx, int sy, int ex, int ey, int[][] grid,
			int thresh) {
		Stack<Pair> ans=new Stack<>();
		PriorityQueue<Triple> q=new PriorityQueue<>(new Comparator<Triple>() {

			@Override
			public int compare(Triple o1, Triple o2) {
				// TODO Auto-generated method stub
				return o1.w-o2.w;
			}
			
		});
		Triple source=new Triple(sx,sy,0);
		q.add(source);
		boolean[][] visited=new boolean[grid.length][grid[0].length];
		Pair[][] parent=new Pair[grid.length][grid[0].length];
		visited[sx][sy]=true;
		
		while(!q.isEmpty()) {
			Triple curr=q.remove();
			int i=curr.x;
			int j=curr.y;
			visited[i][j]=true;
//			System.out.print(q);
			if(i==ex && j==ey) 
			{	
				int x=ex;
				int y=ey;
//				System.out.println("Cost"+curr.w);
//				System.out.println("PASS");
//				System.out.print(ex+","+ey+" ");
				while(true) {
					if(x==sx && y==sy) {
						ans.push(new Pair(x,y));
//						System.out.print(y+","+x+" ");
						break;
					}
					ans.push(new Pair(x,y));
//					System.out.print(y+","+x+" ");
					Pair temp=parent[x][y];
					x=temp.x;
					y=temp.y;
					
				}
//				System.out.println("PARENT"+parent[3][2].x+" "+parent[3][2].y+"PARENT");
				
				return ans;
			}
			
			
			
			if(i-1>=0 && j-1>=0) {
				if(Math.abs(grid[i-1][j-1]-grid[i][j])<=thresh && !visited[i-1][j-1]){
					q.add(new Triple(i-1,j-1,curr.w+14+Math.abs(grid[i-1][j-1]-grid[i][j])+heuristic1(i-1,j-1,ex,ey)-heuristic1(i,j,ex,ey)));
					visited[i-1][j-1]=true;
					parent[i-1][j-1]=new Pair(i,j);
				}
				
			}
			
			if(i-1>=0) {
				if(Math.abs(grid[i-1][j]-grid[i][j])<=thresh && !visited[i-1][j]){
					q.add(new Triple(i-1,j,curr.w+10+Math.abs(grid[i-1][j]-grid[i][j])+heuristic1(i-1,j,ex,ey)-heuristic1(i,j,ex,ey)));
					visited[i-1][j]=true;
					parent[i-1][j]=new Pair(i,j);
				}
				
			}
			
			
			if(i-1>=0 && j+1< grid[0].length) {
				if(Math.abs(grid[i-1][j+1]-grid[i][j])<=thresh && !visited[i-1][j+1]){
					q.add(new Triple(i-1,j+1,curr.w+14+Math.abs(grid[i-1][j+1]-grid[i][j])+heuristic1(i-1,j+1,ex,ey)-heuristic1(i,j,ex,ey)));
					visited[i-1][j+1]=true;
					parent[i-1][j+1]=new Pair(i,j);
				}
				
			}
			if(j-1>=0) {
				if(Math.abs(grid[i][j-1]-grid[i][j])<=thresh && !visited[i][j-1]){
					q.add(new Triple(i,j-1,curr.w+10+Math.abs(grid[i][j-1]-grid[i][j])+heuristic1(i,j-1,ex,ey)-heuristic1(i,j,ex,ey)));
					visited[i][j-1]=true;
					parent[i][j-1]=new Pair(i,j);
				}
				
			}
			
			if(j+1<grid[0].length) {
				if(Math.abs(grid[i][j+1]-grid[i][j])<=thresh && !visited[i][j+1]){
					q.add(new Triple(i,j+1,curr.w+10+Math.abs(grid[i][j+1]-grid[i][j])+heuristic1(i,j+1,ex,ey)-heuristic1(i,j,ex,ey)));
					visited[i][j+1]=true;
					parent[i][j+1]=new Pair(i,j);
				}
				
			}
			
			if(i+1<grid.length && j-1>=0) {
				if(Math.abs(grid[i+1][j-1]-grid[i][j])<=thresh && !visited[i+1][j-1]){
					q.add(new Triple(i+1,j-1,curr.w+14+Math.abs(grid[i+1][j-1]-grid[i][j])+heuristic1(i+1,j-1,ex,ey)-heuristic1(i,j,ex,ey)));
					visited[i+1][j-1]=true;
					parent[i+1][j-1]=new Pair(i,j);
				}
				
			}
			
			if(i+1<grid.length) {
				if(Math.abs(grid[i+1][j]-grid[i][j])<=thresh && !visited[i+1][j]){
					q.add(new Triple(i+1,j,curr.w+10+Math.abs(grid[i+1][j]-grid[i][j])+heuristic1(i+1,j,ex,ey)-heuristic1(i,j,ex,ey)));
					visited[i+1][j]=true;
					parent[i+1][j]=new Pair(i,j);
				}
				
			}
			
			if(i+1<grid.length && j+1<grid[0].length) {
				if(Math.abs(grid[i+1][j+1]-grid[i][j])<=thresh && !visited[i+1][j+1]){
					q.add(new Triple(i+1,j+1,curr.w+14+Math.abs(grid[i+1][j+1]-grid[i][j])+heuristic1(i+1,j+1,ex,ey)-heuristic1(i,j,ex,ey)));
					visited[i+1][j+1]=true;
					parent[i+1][j+1]=new Pair(i,j);
				}
				
			}
	
			
		}
//		System.out.println("FAIL");
		return null;

		
		
	}
	public static Stack<Pair> myUCS(int n,int m,int sx,int sy,int ex,int ey,int[][] grid,int thresh) {
//		System.out.println("HERE");
		Stack<Pair> ans=new Stack<>();
		PriorityQueue<Triple> q=new PriorityQueue<>(new Comparator<Triple>() {

			@Override
			public int compare(Triple o1, Triple o2) {
				// TODO Auto-generated method stub
				return o1.w-o2.w;
			}
			
		});
		Triple source=new Triple(sx,sy,0);
		q.add(source);
		boolean[][] visited=new boolean[grid.length][grid[0].length];
		Pair[][] parent=new Pair[grid.length][grid[0].length];
		visited[sx][sy]=true;
		
		while(!q.isEmpty()) {
			Triple curr=q.remove();
			int i=curr.x;
			int j=curr.y;
			visited[i][j]=true;
//			System.out.print(q);
			if(i==ex && j==ey) 
			{	
				int x=ex;
				int y=ey;
//				System.out.println("Cost"+curr.w);
//				System.out.println("PASS");
//				System.out.print(ex+","+ey+" ");
				while(true) {
					if(x==sx && y==sy) {
						ans.push(new Pair(x,y));
//						System.out.print(y+","+x+" ");
						break;
					}
					ans.push(new Pair(x,y));
//					System.out.print(y+","+x+" ");
					Pair temp=parent[x][y];
					x=temp.x;
					y=temp.y;
					
				}
//				System.out.println("PARENT"+parent[3][2].x+" "+parent[3][2].y+"PARENT");
				
				return ans;
			}
			
			
			
			if(i-1>=0 && j-1>=0) {
				if(Math.abs(grid[i-1][j-1]-grid[i][j])<=thresh && !visited[i-1][j-1]){
					q.add(new Triple(i-1,j-1,curr.w+14));
					visited[i-1][j-1]=true;
					parent[i-1][j-1]=new Pair(i,j);
				}
				
				
			}
			
			if(i-1>=0) {
				if(Math.abs(grid[i-1][j]-grid[i][j])<=thresh && !visited[i-1][j]){
					q.add(new Triple(i-1,j,curr.w+10));
					visited[i-1][j]=true;
					parent[i-1][j]=new Pair(i,j);
				}
				
			}
			
			
			if(i-1>=0 && j+1< grid[0].length) {
				if(Math.abs(grid[i-1][j+1]-grid[i][j])<=thresh && !visited[i-1][j+1]){
					q.add(new Triple(i-1,j+1,curr.w+14));
					visited[i-1][j+1]=true;
					parent[i-1][j+1]=new Pair(i,j);
				}
				
			}
			if(j-1>=0) {
				if(Math.abs(grid[i][j-1]-grid[i][j])<=thresh && !visited[i][j-1]){
					q.add(new Triple(i,j-1,curr.w+10));
					visited[i][j-1]=true;
					parent[i][j-1]=new Pair(i,j);
				}
				
			}
			
			if(j+1<grid[0].length) {
				if(Math.abs(grid[i][j+1]-grid[i][j])<=thresh && !visited[i][j+1]){
					q.add(new Triple(i,j+1,curr.w+10));
					visited[i][j+1]=true;
					parent[i][j+1]=new Pair(i,j);
				}
				
			}
			
			if(i+1<grid.length && j-1>=0) {
				if(Math.abs(grid[i+1][j-1]-grid[i][j])<=thresh && !visited[i+1][j-1]){
					q.add(new Triple(i+1,j-1,curr.w+14));
					visited[i+1][j-1]=true;
					parent[i+1][j-1]=new Pair(i,j);
				}
				
			}
			
			if(i+1<grid.length) {
				if(Math.abs(grid[i+1][j]-grid[i][j])<=thresh && !visited[i+1][j]){
					q.add(new Triple(i+1,j,curr.w+10));
					visited[i+1][j]=true;
					parent[i+1][j]=new Pair(i,j);
				}
				
			}
			
			if(i+1<grid.length && j+1<grid[0].length) {
				if(Math.abs(grid[i+1][j+1]-grid[i][j])<=thresh && !visited[i+1][j+1]){
					q.add(new Triple(i+1,j+1,curr.w+14));
					visited[i+1][j+1]=true;
					parent[i+1][j+1]=new Pair(i,j);
				}
				
			}
	
			
		}
//		System.out.println("FAIL");
		return null;
		
		
	}
	public static Stack<Pair> myBFS(int n,int m,int sx,int sy,int ex,int ey,int[][] grid,int thresh) {
		Stack<Pair> ans=new Stack<>(); 
//		System.out.println("HERE");
		Queue<Pair> q=new LinkedList<>();
		Pair source=new Pair(sx,sy);
		q.add(source);
		boolean[][] visited=new boolean[grid.length][grid[0].length];
		Pair[][] parent=new Pair[grid.length][grid[0].length];
		visited[sx][sy]=true;
		
		while(!q.isEmpty()) {
			Pair curr=q.remove();
			int i=curr.x;
			int j=curr.y;
//			System.out.print(q);
			if(i==ex && j==ey) 
			{	
				int x=ex;
				int y=ey;
//				System.out.println("PASS");
//				System.out.print(ex+","+ey+" ");
				while(true) {
					if(x==sx && y==sy) {
						ans.push(new Pair(x,y));
//						System.out.print(y+","+x+" ");
						break;
					}
					ans.push(new Pair(x,y));
//					System.out.print(y+","+x+" ");
					Pair temp=parent[x][y];
					x=temp.x;
					y=temp.y;
					
				}
//				System.out.println("PARENT"+parent[3][2].x+" "+parent[3][2].y+"PARENT");
				
				return ans;
			}
			
			if(i-1>=0 && j-1>=0) {
				if(Math.abs(grid[i-1][j-1]-grid[i][j])<=thresh && !visited[i-1][j-1]){
					q.add(new Pair(i-1,j-1));
					visited[i-1][j-1]=true;
					parent[i-1][j-1]=new Pair(i,j);
				}
				
			}
			
			if(i-1>=0) {
				if(Math.abs(grid[i-1][j]-grid[i][j])<=thresh && !visited[i-1][j]){
					q.add(new Pair(i-1,j));
					visited[i-1][j]=true;
					parent[i-1][j]=new Pair(i,j);
				}
				
			}
			
			if(j-1>=0) {
				if(Math.abs(grid[i][j-1]-grid[i][j])<=thresh && !visited[i][j-1]){
					q.add(new Pair(i,j-1));
					visited[i][j-1]=true;
					parent[i][j-1]=new Pair(i,j);
				}
				
			}
			if(i-1>=0 && j+1< grid[0].length) {
				if(Math.abs(grid[i-1][j+1]-grid[i][j])<=thresh && !visited[i-1][j+1]){
					q.add(new Pair(i-1,j+1));
					visited[i-1][j+1]=true;
					parent[i-1][j+1]=new Pair(i,j);
				}
				
			}
			if(j+1<grid[0].length) {
				if(Math.abs(grid[i][j+1]-grid[i][j])<=thresh && !visited[i][j+1]){
					q.add(new Pair(i,j+1));
					visited[i][j+1]=true;
					parent[i][j+1]=new Pair(i,j);
				}
				
			}
			
			if(i+1<grid.length && j-1>=0) {
				if(Math.abs(grid[i+1][j-1]-grid[i][j])<=thresh && !visited[i+1][j-1]){
					q.add(new Pair(i+1,j-1));
					visited[i+1][j-1]=true;
					parent[i+1][j-1]=new Pair(i,j);
				}
				
			}
			
			if(i+1<grid.length) {
				if(Math.abs(grid[i+1][j]-grid[i][j])<=thresh && !visited[i+1][j]){
					q.add(new Pair(i+1,j));
					visited[i+1][j]=true;
					parent[i+1][j]=new Pair(i,j);
				}
				
			}
			
			if(i+1<grid.length && j+1<grid[0].length) {
				if(Math.abs(grid[i+1][j+1]-grid[i][j])<=thresh && !visited[i+1][j+1]){
					q.add(new Pair(i+1,j+1));
					visited[i+1][j+1]=true;
					parent[i+1][j+1]=new Pair(i,j);
				}
				
			}
	
			
		}
//		System.out.println("FAIL");
		return null;
		
		
	}
	class Solution {
	    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		List<Integer>[] graph = new ArrayList[n];
		for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
		for (int i = 0; i < connections.size(); i++) {
			List<Integer> edge = connections.get(i);
			graph[edge.get(0)].add(edge.get(1));
			graph[edge.get(1)].add(edge.get(0));
		}
		boolean[] visited = new boolean[n];
		int[] order = new int[n];
		List<List<Integer>> results = new ArrayList<>();
		int[] uuid = new int[1]; // a global unique counter that assigns traversal order to each node
		dfs(graph, -1, 0, visited, order, uuid, results);
		return results;
	}

	public void dfs(List<Integer>[] graph, int pre, int src, boolean[] visited, int[] order, int[] uuid, List<List<Integer>> results) {
		visited[src] = true;
		order[src] = uuid[0]++;
		int orig_order = order[src];
		for (Integer neighbour : graph[src]) {
			if (neighbour == pre) continue; // pre: guarantee no backward traversal
			if (!visited[neighbour]) dfs(graph, src, neighbour, visited, order, uuid, results);
			// order[src] keeps the earliest point that src can reach without passing src->pre
			order[src] = Math.min(order[src], order[neighbour]); // used by its predecessor to tell if pre->src is critical.
			if (orig_order < order[neighbour]) results.add(Arrays.asList(src, neighbour));
		}
	}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file=new File("input.txt");
		Scanner sc=null;
		try {
			sc=new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		while(sc.hasNext()) {
			String algo=sc.next();
			int W=sc.nextInt();
			int H=sc.nextInt();
			int landingX=sc.nextInt();
			int landingY=sc.nextInt();
			int elevationThreshold=sc.nextInt();
			int numOftargetSites=sc.nextInt();
			int[][] targetCoords=new int[numOftargetSites][2];
			for(int i=0;i<numOftargetSites;i++) {
				targetCoords[i][0]=sc.nextInt();
				targetCoords[i][1]=sc.nextInt();
			}
			int[][] grid =new int[H][W];
			for(int i=0;i<H;i++) {
				for(int j=0;j<W;j++) {
					grid[i][j]=sc.nextInt();
//					System.out.print(grid[i][j]+" ");
				}
				System.out.println(" ");
			}
//		}
		FileWriter fw=null;
		try {
			fw=new FileWriter("output.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if(algo.equals("BFS")){

			for(int i=0;i<numOftargetSites;i++) {
				Stack<Pair> ans=myBFS(H,W,landingY,landingX,targetCoords[i][1],targetCoords[i][0],grid,elevationThreshold);
				if(ans==null) {
//					System.out.print("FAIL");
					fw.write("FAIL\n");
//					System.out.println();
					continue;
				}
				while(!ans.isEmpty()) {
					Pair p=ans.pop();
					String a=p.y+","+p.x+" ";
					fw.write(a);
				}
				fw.write("\n");
//				System.out.println();
			}
				
		}
		else if(algo.equals("UCS")) {
			for(int i=0;i<numOftargetSites;i++) {
				Stack<Pair> ans=myUCS(H,W,landingY,landingX,targetCoords[i][1],targetCoords[i][0],grid,elevationThreshold);
				if(ans==null) {
//					System.out.print("FAIL");
					fw.write("FAIL\n");
//					System.out.println();
					continue;
				}
				while(!ans.isEmpty()) {
					Pair p=ans.pop();
					
					fw.write(p.y+","+p.x+" ");
				}
				fw.write("\n");
//				System.out.println();
			}
		}
		else {
			
			for(int i=0;i<numOftargetSites;i++) {
				Stack<Pair> ans=myA1(H,W,landingY,landingX,targetCoords[i][1],targetCoords[i][0],grid,elevationThreshold);
				if(ans==null) {
//					System.out.print("FAIL");
					fw.write("FAIL\n");
//					System.out.println();
					continue;
				}
				while(!ans.isEmpty()) {
					Pair p=ans.pop();
					
					fw.write(p.y+","+p.x+" ");
				}
				fw.write("\n");
//				System.out.println();
				System.out.println();
			}
		}
		fw.flush();
		fw.close();
		

	}

}
class Pair{
	int x;
	int y;
	Pair(int x,int y){
		this.x=x;
		this.y=y;
	}
}

class Triple{
	int x;
	int y;
	int w;
	
	Triple(int x,int y,int w){
		this.x=x;
		this.y=y;
		this.w=w;
	}
}