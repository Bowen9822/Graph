package ca.ubc.ece.cpen221.mp3.graph;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import ca.ubc.ece.cpen221.mp3.staff.Graph;
import ca.ubc.ece.cpen221.mp3.staff.Vertex;


/**
 * This class represents a graph as an adjacenylist
 * 
 * Representation Invariant: the vertices in the graph must not be null
 * Abstraction Function: creates a unique graph for every unique input two Lists of non-null Vertex
 * @author Bowen
 *
 */
public class AdjacencyListGraph implements Graph {
	private HashMap<Vertex, ArrayList<Vertex>> graphRep = new HashMap<Vertex, ArrayList<Vertex>>();
	private List<Vertex> vertices = new ArrayList<Vertex>();

	public AdjacencyListGraph() {
	}

	public AdjacencyListGraph(List<Vertex> inNodes, List<Vertex> toNodes) {

		for (int i = 0; i < inNodes.size(); i++) {
			Vertex fromVertex = inNodes.get(i);
			Vertex toVertex = toNodes.get(i);

			if (!graphRep.containsKey(fromVertex)) {
				graphRep.put(fromVertex, new ArrayList<Vertex>());
				vertices.add(fromVertex);
			}
			if (!graphRep.containsKey(toVertex)) {
				graphRep.put(toVertex, new ArrayList<Vertex>());
				vertices.add(toVertex);
			}
			if (!graphRep.get(fromVertex).contains(toVertex)) {
				graphRep.get(fromVertex).add(toVertex);
			}
		}

	}

	/**
	 * Adds a vertex to the graph.
	 *
	 * Precondition: v is not already a vertex in the graph
	 */
	public void addVertex(Vertex v) {
		if (!vertices.contains(v)) {
			graphRep.put(v, new ArrayList<Vertex>());
			vertices.add(v);
		}

	}

	/**
	 * Adds an edge from v1 to v2.
	 *
	 * Precondition: v1 and v2 are vertices in the graph
	 */
	public void addEdge(Vertex v1, Vertex v2) {
		graphRep.get(v1).add(v2);
	}

	/**
	 * Check if there is an edge from v1 to v2.
	 *
	 * Precondition: v1 and v2 are vertices in the graph Postcondition: return true
	 * iff an edge from v1 connects to v2
	 */
	public boolean edgeExists(Vertex v1, Vertex v2) {
		return graphRep.get(v1).contains(v2);
	}

	/**
	 * Get an array containing all downstream vertices adjacent to v.
	 *
	 * Precondition: v is a vertex in the graph
	 * 
	 * Postcondition: returns a list containing each vertex w such that there is an
	 * edge from v to w. The size of the list must be as small as possible (No
	 * trailing null elements). This method should return a list of size 0 iff v has
	 * no downstream neighbors.
	 */
	public List<Vertex> getDownstreamNeighbors(Vertex v) {
		return graphRep.get(v);
	}

	/**
	 * Get an array containing all upstream vertices adjacent to v.
	 *
	 * Precondition: v is a vertex in the graph
	 * 
	 * Postcondition: returns a list containing each vertex u such that there is an
	 * edge from u to v. The size of the list must be as small as possible (No
	 * trailing null elements). This method should return a list of size 0 iff v has
	 * no upstream neighbors.
	 */
	public List<Vertex> getUpstreamNeighbors(Vertex v) {
		List<Vertex> upstreamNeighbours = new ArrayList<Vertex>();
		for (Vertex vertex : graphRep.keySet()) {
			if (graphRep.get(vertex).contains(v)) {
				upstreamNeighbours.add(vertex);
			}
		}
		return upstreamNeighbours;
	}

	/**
	 * Get all vertices in the graph.
	 *
	 * Postcondition: returns a list containing all vertices in the graph. This
	 * method should return a list of size 0 iff the graph has no vertices.
	 */
	public List<Vertex> getVertices() {
		return new ArrayList<Vertex>(vertices);
	}
}
