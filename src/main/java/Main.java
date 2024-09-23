import input.Position;
import logic.MissionControl;
import logic.Plateau;
import logic.Rover;
import ui.ConsoleInteraction;

public class Main {
    public static void main(String[] args) {
        MissionControl mc = new MissionControl();
        mc.start();
    }
}
