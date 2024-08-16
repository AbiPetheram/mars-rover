package ui;

import input.CompassDirection;
import input.Coordinates;
import input.Position;
import input.parsing.CoordinateParser;
import input.parsing.DirectionParser;
import input.parsing.InstructionParser;
import logic.MissionControl;
import logic.Plateau;
import logic.Rover;

import java.util.Scanner;

public class ConsoleInteraction {
    public Plateau getPlateau(MissionControl mc){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! Please specify the dimensions of your plateau in the format 0 0: ");
        while(true){
            try{
                CoordinateParser cp = new CoordinateParser();
                Coordinates result = cp.parseCoordinates(scanner.nextLine().split(" "));
                return mc.createPlateau(result);
            } catch (IllegalArgumentException e){
                System.out.println("Invalid input, please try again");
            }
        }
    }

    public Rover addRoverToPlateau(MissionControl mc, Plateau plateau){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Now add a rover to your plateau. Specify the location in the format 0 0 N: ");
        while(true){
            try{
                String[] input = scanner.nextLine().split(" ");
                CoordinateParser cp = new CoordinateParser();
                DirectionParser dp = new DirectionParser();
                Coordinates coordinates = cp.parseCoordinates(new String[] {input[0], input[1]});
                CompassDirection direction = dp.parseDirection(input[2]);
                return mc.createRover(new Position(coordinates, direction), plateau);
            } catch (IllegalArgumentException | IndexOutOfBoundsException e){
                System.out.println("Invalid input, please try again");
            }
        }
    }

    public Position moveRover(Rover rover){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Now time to take your rover for a walk. Enter a series of turn and move commands: ");
        while(true){
            try{
                InstructionParser ip = new InstructionParser();
                Position result = rover.move(ip.parseInstructions(scanner.nextLine()));
                System.out.println("The rover is now in position " + result.toString());
                return result;
            } catch (IllegalArgumentException e){
                System.out.println("Rover cannot move here, please try again");
            }
        }
    }

    public void optionsList(MissionControl mc, Plateau plateau, Rover rover){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to do next?\n1. Add another Rover\n2. Move current Rover again\n3. Quit");
        switch(scanner.nextInt()){
            case 1 -> {
                moveRover(addRoverToPlateau(mc, plateau));
                optionsList(mc, plateau, rover);
            }
            case 2 -> {
                moveRover(rover);
                optionsList(mc, plateau, rover);
            }
            case 3 -> System.exit(0);
            default -> {
                System.out.println("Invalid choice, therefore we have chosen to quit on your behalf.");
                System.exit(0);
            }
        }

    }

}
