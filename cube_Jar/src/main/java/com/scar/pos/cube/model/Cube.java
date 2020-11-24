package com.scar.pos.cube.model;

public class Cube {
    private int     id = 0;
    private int     sides = 0;
    private String  material = "";
    private int     corners = 0;
    private float   edges = 0;

    public Cube(int id, int sides, String material, int corners, float edges) {
        this.id = id;
        this.sides = sides;
        this.material = material;
        this.corners = corners;
        this.edges = edges;
    }

    @Override
    public String toString() {
        return "CubeModel{" +
                "id=" + id +
                ", sides=" + sides +
                ", material='" + material + '\'' +
                ", corners=" + corners +
                ", edges=" + edges +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSides() {
        return sides;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getCorners() {
        return corners;
    }

    public void setCorners(int corners) {
        this.corners = corners;
    }

    public float getEdges() {
        return edges;
    }

    public void setEdges(float edges) {
        this.edges = edges;
    }
}