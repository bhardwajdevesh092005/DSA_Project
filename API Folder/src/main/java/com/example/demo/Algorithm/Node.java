package com.example.demo.Algorithm;

class Node {
    String name;
    double latitude;
    double longitude;

    public Node(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Node)) return false;
        Node other = (Node) obj;
        return Math.abs(other.latitude-latitude) < 1e-8d && Math.abs(other.longitude-longitude) < 1e-8d;
            //    name.equals(other.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        long latBits = Double.doubleToLongBits(latitude);
        long lonBits = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (latBits ^ (latBits >>> 32));
        result = 31 * result + (int) (lonBits ^ (lonBits >>> 32));
        return result;
    }
}

