import java.util.Stack;


public class TopologicalOrdering {
	static boolean[]  visited; //to keep track of the visited nodes
	static boolean cycle=false;	//to check if a cycle has been detected
	static int adjMatrix[][];	//represents the graph, adjMatrix[o][j]=1 represents an outgoing edge from i to j
	static Stack<Integer> explored=new Stack<>();	//to keep track of the explored nodes
	static final int source=0;	
	
	public static void main(String[] args) {
		adjMatrix= new int[][]{
			{0,1,0,0,1,1,0,0,1,0},
			{0,0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,1,0,0,0,0},
			{0,0,1,0,0,0,0,0,1,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,1,0,1,0,0,1},
			{0,0,0,0,0,0,0,1,0,1},
			{0,0,0,0,0,0,0,0,1,0},
			{0,0,0,0,0,0,0,0,0,1},
			{0,0,0,0,1,0,0,0,0,0},
		
		};
		System.out.println("BEFORE:");
		System.out.println("Graph (without cycle): ");
		printAdjMatrix(adjMatrix);		
		topologicalDfs(adjMatrix,source);
		checkIfAllVisited();	
		System.out.println("AFTER topological (DFS) ordering: ");		
		printOrder(explored);
		
		adjMatrix= new int[][]{
			{0,1,0,0,1,1,0,0,1,0},
			{0,0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,1,0,0,0,0},
			{0,0,1,0,0,0,0,0,1,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,1,0,1,0,0,1},
			{0,0,0,0,0,0,0,1,0,1},
			{0,0,0,1,0,0,0,0,1,0},
			{0,0,0,0,0,0,0,0,0,1},
			{0,0,0,0,1,0,0,0,0,0},
		};
		System.out.println("\n\nBEFORE:");
		System.out.println("Graph (with cycle): ");
		printAdjMatrix(adjMatrix);		
		topologicalDfs(adjMatrix,source);
		checkIfAllVisited();		
		System.out.println("AFTER topological (DFS) ordering: ");			
		printOrder(explored);

	}

	//print the initial adjacency matrix
	private static void printAdjMatrix(int[][] adjMatrix) {
		System.out.print("Vertices are:");
		visited=new boolean[adjMatrix.length];
		//initialize the visited array to false
		for(int i=0;i<adjMatrix.length;i++){
			visited[i]=false;
			System.out.print(" "+i);
		}
		System.out.println();
		for(int i=0;i<adjMatrix.length;i++){
			for(int j=0;j<adjMatrix.length;j++){
				System.out.print(" "+adjMatrix[i][j]);	
			}
			System.out.println();
		}		
	}

	//there can be other nodes which do not have any incoming edges
	private static void checkIfAllVisited() {
		//if cycle detected
		if(cycle){
			System.out.println("Cycle Detected!");
			return;
		}
		//if no cycle detected
		for(int b=0;b<visited.length;b++){
			if(!visited[b] ){
				if(!cycle)
				{
					topologicalDfs(adjMatrix,b);
				}
				else{
					System.out.println("Cycle Detected!");
					break;
				}
			}
		}
	}

	//pop the elements from the stack to get the correct order of nodes
	private static void printOrder(Stack<Integer> explored) {
		while(!explored.isEmpty()){
			System.out.print(" "+explored.pop());
		}
	}
	
	//topological ordering
	private static void topologicalDfs(int[][] adjMatrix, int source) {
		visited[source]=true;
		for(int k=0;k<adjMatrix.length;k++){
			if(adjMatrix[source][k]==1 && !cycle){
				if(visited[k]==false){
					topologicalDfs(adjMatrix,k); //if the neighbouring nodes have not been visited, call topologicalDfs on it
				}
				else{	
					if(!explored.contains(k)){
						cycle=true;
						return;
					}				
				}			
		}
	}
		//if the neighbouring nodes have already been visited, add them to explored
		//nodes are added into explored when no outgoing edge is found from it
		if(!explored.contains(source)){
			explored.push(source);
			
		}		
		return;	
	}
}
/*
OUTPUT


BEFORE:
Graph (without cycle): 
Vertices are: 0 1 2 3 4 5 6 7 8 9
 0 1 0 0 1 1 0 0 1 0
 0 0 0 1 0 0 0 0 0 0
 0 0 0 0 0 1 0 0 0 0
 0 0 1 0 0 0 0 0 1 0
 0 0 0 0 0 0 0 0 0 0
 0 0 0 0 1 0 1 0 0 1
 0 0 0 0 0 0 0 1 0 1
 0 0 0 0 0 0 0 0 1 0
 0 0 0 0 0 0 0 0 0 1
 0 0 0 0 1 0 0 0 0 0
AFTER topological (DFS) ordering: 
 0 1 3 2 5 6 7 8 9 4

BEFORE:
Graph (with cycle): 
Vertices are: 0 1 2 3 4 5 6 7 8 9
 0 1 0 0 1 1 0 0 1 0
 0 0 0 1 0 0 0 0 0 0
 0 0 0 0 0 1 0 0 0 0
 0 0 1 0 0 0 0 0 1 0
 0 0 0 0 0 0 0 0 0 0
 0 0 0 0 1 0 1 0 0 1
 0 0 0 0 0 0 0 1 0 1
 0 0 0 1 0 0 0 0 1 0
 0 0 0 0 0 0 0 0 0 1
 0 0 0 0 1 0 0 0 0 0
Cycle Detected!
AFTER topological (DFS) ordering: 
 0 1 3 2 5 6 4
*/