package logic;

import input.Coordinates;

public class Plateau {
    private Coordinates size;

    public Plateau(Coordinates size) {
        this.size = size;
    }

    public Coordinates getSize() {
        return size;
    }
}
