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
    MissionControl missionControl;
    CoordinateParser coordinateParser;
    DirectionParser directionParser;
    InstructionParser instructionParser;

    public ConsoleInteraction(MissionControl missionControl) {
        this.missionControl = missionControl;
        this.coordinateParser = new CoordinateParser();
        this.directionParser = new DirectionParser();
        this.instructionParser = new InstructionParser();
    }

    public Plateau getPlateau(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! Please specify the dimensions of your plateau in the format 0 0: ");
        while(true){
            try{
                Coordinates result = coordinateParser.parseCoordinates(scanner.nextLine().split(" "));
                return missionControl.createPlateau(result);
            } catch (IllegalArgumentException e){
                System.out.println("Invalid input, please try again");
            }
        }
    }

    public Rover addRoverToPlateau(Plateau plateau){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Now add a rover to your plateau. Specify the location in the format 0 0 N: ");
        while(true){
            try{
                String[] input = scanner.nextLine().split(" ");
                Coordinates coordinates = coordinateParser.parseCoordinates(new String[] {input[0], input[1]});
                CompassDirection direction = directionParser.parseDirection(input[2]);
                return missionControl.createRover(new Position(coordinates, direction), plateau);
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
                Position result = rover.move(instructionParser.parseInstructions(scanner.nextLine()));
                System.out.println("The rover is now in position " + result.toString());
                return result;
            } catch (IllegalArgumentException e){
                System.out.println("Rover cannot move here, please try again");
            }
        }
    }

    public void optionsList(Plateau plateau, Rover rover){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to do next?\n1. Add another Rover\n2. Move current Rover again\n3. Quit");
        switch(scanner.nextInt()){
            case 1 -> {
                moveRover(addRoverToPlateau(plateau));
                optionsList(plateau, rover);
            }
            case 2 -> {
                moveRover(rover);
                optionsList(plateau, rover);
            }
            case 3 -> System.exit(0);
            default -> {
                System.out.println("Invalid choice, therefore we have chosen to quit on your behalf.");
                System.exit(0);
            }
        }

    }

}
