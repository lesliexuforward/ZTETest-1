package com.amgji.graph;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SymbolGraph;

public class MySymbolGraph {

	private ST<String, Integer> st; // 节点字符串映射到索引
	private String[] keys; // 索引——》字符串
	private MyEdgeWeightedDigraph graph; // the underlying graph

	public MySymbolGraph(String filename, String delimiter) {
        st = new ST<String, Integer>();

        In in = new In(filename);
        //将数据读入符号表
        while (!in.isEmpty()) {
            String[] a = in.readLine().split(delimiter);
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i]))
                    st.put(a[i], st.size());
            }
        }
        StdOut.println("Done reading " + filename);

        // 将数据写入反向索引表
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        // 将数据读入图的邻接表
        graph = new MyEdgeWeightedDigraph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                int w = st.get(a[i]);
                //graph.addEdge(v, w);
            }
        }
    }
	//是否包含某节点
	public boolean contains(String s) {
		return st.contains(s);
	}
	//通过符号名获取索引值
	public int indexOf(String s) {
		return st.get(s);
	}
	//通过索引值获取符号名
	public String nameOf(int v) {
		validateVertex(v);
		return keys[v];
	}
	//返回无向图
	public MyEdgeWeightedDigraph graph() {
		return graph;
	}

	private void validateVertex(int v) {
		int V = graph.V();
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
	}

	public static void main(String[] args) {
		String filename = args[0];
		String delimiter = args[1];
		SymbolGraph sg = new SymbolGraph(filename, delimiter);
		Graph graph = sg.graph();
		while (StdIn.hasNextLine()) {
			String source = StdIn.readLine();
			if (sg.contains(source)) {
				int s = sg.index(source);
				for (int v : graph.adj(s)) {
					StdOut.println("   " + sg.name(v));
				}
			} else {
				StdOut.println("input not contain '" + source + "'");
			}
		}
	}

}
