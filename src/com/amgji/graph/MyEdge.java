package com.amgji.graph;

import edu.princeton.cs.algs4.StdOut;

public class MyEdge implements Comparable<MyEdge> {
	private final int v;
	private final int w;
	private final double weight;

	/**
	     * Initializes an MyEdge between vertices {@code v} and {@code w} of
	     * the given {@code weight}.
	     *
	     * @param  v one vertex
	     * @param  w the other vertex
	     * @param  weight the weight of this MyEdge
	     * @throws IllegalArgumentException if either {@code v} or {@code w} 
	     *         is a negative integer
	     * @throws IllegalArgumentException if {@code weight} is {@code NaN}
	     */
	    public MyEdge(int v, int w, double weight) {
	        if (v < 0) throw new IllegalArgumentException("vertex index must be a nonnegative integer");
	        if (w < 0) throw new IllegalArgumentException("vertex index must be a nonnegative integer");
	        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
	        this.v = v;
	        this.w = w;
	        this.weight = weight;
	    }

	/**
	 * Returns the weight of this MyEdge.
	 *
	 * @return the weight of this MyEdge
	 */
	public double weight() {
		return weight;
	}

	/**
	 * Returns either endpoint of this MyEdge.
	 *
	 * @return either endpoint of this MyEdge
	 */
	public int either() {
		return v;
	}

	/**
	 * Returns the endpoint of this MyEdge that is different from the given
	 * vertex.
	 *
	 * @param vertex
	 *            one endpoint of this MyEdge
	 * @return the other endpoint of this MyEdge
	 * @throws IllegalArgumentException
	 *             if the vertex is not one of the endpoints of this MyEdge
	 */
	public int other(int vertex) {
		if (vertex == v)
			return w;
		else if (vertex == w)
			return v;
		else
			throw new IllegalArgumentException("Illegal endpoint");
	}

	/**
	 * Compares two MyEdges by weight. Note that {@code compareTo()} is not
	 * consistent with {@code equals()}, which uses the reference equality
	 * implementation inherited from {@code Object}.
	 *
	 * @param that
	 *            the other MyEdge
	 * @return a negative integer, zero, or positive integer depending on
	 *         whether the weight of this is less than, equal to, or greater
	 *         than the argument MyEdge
	 */
	@Override
	public int compareTo(MyEdge that) {
		return Double.compare(this.weight, that.weight);
	}

	/**
	 * Returns a string representation of this MyEdge.
	 *
	 * @return a string representation of this MyEdge
	 */
	public String toString() {
		return String.format("%d-%d %.5f", v, w, weight);
	}

	/**
	 * Unit tests the {@code MyEdge} data type.
	 *
	 * @param args
	 *            the command-line arguments
	 */
	public static void main(String[] args) {
		MyEdge e = new MyEdge(12, 34, 5.67);
		StdOut.println(e);
	}
}
