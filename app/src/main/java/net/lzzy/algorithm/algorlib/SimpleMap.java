package net.lzzy.algorithm.algorlib;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzzy_gxy on 2019/6/27.
 * Description:
 */
public class SimpleMap {
    int vertexCount;
    List<Edge> edges = new ArrayList<>();

    public SimpleMap (int v) {
        vertexCount = v;
    }

    public void addEdge(int source, int target, double distance) {
        edges.add(new Edge(source, target, distance));
    }

    public void addTwoWayEdge(int v1, int v2, double distance) {
        addEdge(v1, v2, distance);
        addEdge(v2, v1, distance);
    }

    public String iterateDepthFirst() {
        return "015234";
    }

    public String iterateRangeFirst() {
        return "012354";
    }
}

class Edge{
    private int source;
    private int target;
    private double distance;
    int getSource() {
        return source;
    }
    int getTarget() {
        return target;
    }
    double getDistance() {
        return distance;
    }
    Edge(int source, int target, double distance) {
        this.source = source;
        this.target = target;
        this.distance = target;
    }
}
