package input;

public class Position {
    private Coordinates coordinates;
    private CompassDirection facing;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public CompassDirection getFacing() {
        return facing;
    }

    public Position(Coordinates coordinates, CompassDirection facing) {
        this.coordinates = coordinates;
        this.facing = facing;
    }

    @Override
    public String toString() {
        return this.getCoordinates().x() + " " + this.getCoordinates().y() + " " + facing.name();
    }
}
