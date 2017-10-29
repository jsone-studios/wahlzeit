package org.wahlzeit.model;

public class Coordinate {

    private double x;
    private double y;
    private double z;

    public Coordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getDistance(Coordinate other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        double dz = this.z - other.z;

        return Math.sqrt((dx * dx) + (dy * dy) + (dz * dz));
    }

    public boolean isEqual(Coordinate other) {
        if (other == null) {
            return false;
        } else if (this == other) {
            return true;
        } else return this.x == other.x
                && this.y == other.y
                && this.z == other.z;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Coordinate)) {
            return false;
        }
        Coordinate other = (Coordinate) obj;
        return isEqual(other);
    }
}
