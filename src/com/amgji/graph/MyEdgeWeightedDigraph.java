package com.amgji.graph;

import java.io.File;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class MyEdgeWeightedDigraph {
	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V; // 顶点总数
	private int E; // 边的总数
	private Bag<Edge>[] adj; // 邻接表

	/**
	 * 初始化一个顶点数为V边数为0的加权无向图
	 */
	public MyEdgeWeightedDigraph(int V) {
		if (V < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.V = V;
		this.E = 0;
		adj = (Bag<Edge>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Edge>();
		}
	}

	/**
	 * 初始化一个顶点数为V边数为E的随机的加权无向图
	 */
	public MyEdgeWeightedDigraph(int V, int E) {
		this(V);
		if (E < 0)
			throw new IllegalArgumentException("Number of edges must be nonnegative");
		for (int i = 0; i < E; i++) {
			int v = StdRandom.uniform(V);
			int w = StdRandom.uniform(V);
			double weight = Math.round(100 * StdRandom.uniform()) / 100.0;
			Edge e = new Edge(v, w, weight);
			addEdge(e);
		}
	}

	/**
	 * 从流中读取一个加权无向图 如：src/resource/data.txt
	 */
	public MyEdgeWeightedDigraph(In in) {
		this(in.readInt());
		int E = in.readInt();
		if (E < 0)
			throw new IllegalArgumentException("Number of edges must be nonnegative");
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			validateVertex(v);
			validateVertex(w);
			double weight = in.readDouble();
			Edge e = new Edge(v, w, weight);
			addEdge(e);
		}
	}

	/**
	 * 深度复制一个加权无向图
	 */
	public MyEdgeWeightedDigraph(MyEdgeWeightedDigraph G) {
		this(G.V());
		this.E = G.E();
		for (int v = 0; v < G.V(); v++) {
			// reverse so that adjacency list is in same order as original
			Stack<Edge> reverse = new Stack<Edge>();
			for (Edge e : G.adj[v]) {
				reverse.push(e);
			}
			for (Edge e : reverse) {
				adj[v].add(e);
			}
		}
	}

	/**
	 * 
	 * @return 返回这个无向图顶点的总数
	 */
	public int V() {
		return V;
	}

	/**
	 * 
	 * @return 返回这个无向图边的总数
	 */
	public int E() {
		return E;
	}

	// 检查越界
	private void validateVertex(int v) {
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
	}

	/**
	 * 为这个无向图添加一条边
	 */
	public void addEdge(Edge e) {
		int v = e.either();
		int w = e.other(v);
		validateVertex(v);
		validateVertex(w);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}

	/**
	 * 
	 * @param v 顶点
	 * @return 和v相关联的所有边
	 */
	public Iterable<Edge> adj(int v) {
		validateVertex(v);
		return adj[v];
	}

	/**
	 * 
	 * @param v 顶点
	 * @return 与v相关联所有边的数量
	 */
	public int degree(int v) {
		validateVertex(v);
		return adj[v].size();
	}

	/**
	 * @return 图的所有边
	 */
	public Iterable<Edge> edges() {
		Bag<Edge> list = new Bag<Edge>();
		for (int v = 0; v < V; v++) {
			int selfLoops = 0;
			for (Edge e : adj(v)) {
				if (e.other(v) > v) {
					list.add(e);
				}
				// only add one copy of each self loop (self loops will be
				// consecutive)
				else if (e.other(v) == v) {
					if (selfLoops % 2 == 0)
						list.add(e);
					selfLoops++;
				}
			}
		}
		return list;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " " + E + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (Edge e : adj[v]) {
				s.append(e + "  ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}

	/**
	 * 单元测试
	 */
	public static void main(String[] args) {
		File file = new File("src/resource/data.txt");
		In in = new In(file);
		MyEdgeWeightedDigraph G = new MyEdgeWeightedDigraph(in);
		StdOut.println(G);
	}
}
