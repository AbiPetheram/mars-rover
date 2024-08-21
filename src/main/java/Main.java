import input.Position;
import logic.MissionControl;
import logic.Plateau;
import logic.Rover;
import ui.ConsoleInteraction;

public class Main {
    public static void main(String[] args) {
        ConsoleInteraction cs = new ConsoleInteraction();
        MissionControl mc = new MissionControl();
        Plateau plateau = cs.getPlateau(mc);
        Rover rover = cs.addRoverToPlateau(mc, plateau);
        cs.moveRover(rover);
        cs.optionsList(mc, plateau, rover);
    }
}
