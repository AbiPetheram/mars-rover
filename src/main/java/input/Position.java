package input;

public class Position {
    private Coordinates coordinates;
    private CompassDirection facing;

    public Position(Coordinates coordinates, CompassDirection facing) {
        this.coordinates = coordinates;
        this.facing = facing;
    }
}
