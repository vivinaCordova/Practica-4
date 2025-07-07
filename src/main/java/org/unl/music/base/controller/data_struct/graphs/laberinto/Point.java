package org.unl.music.base.controller.data_struct.graphs.laberinto;

public class Point {
    Integer r;
    Integer c;
    Point parent;

    public Point(int x, int y, Point p) {
        this.r = x;
        this.c = y;
        this.parent = p;
    }
    // compute opposite node given that it is in the other direction from the parent

    public Point opposite() {
        if (parent == null) {
            return null; // no parent, cannot compute opposite
        }
        if (this.r.compareTo(parent.r) != 0) {
            return new Point(this.r + this.r.compareTo(parent.r), this.c, this);
        }
        if (this.c.compareTo(parent.c) != 0) {
            return new Point(this.r, this.c + this.c.compareTo(parent.c), this);
        }
        return null;
    }
}
