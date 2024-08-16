package ui;

import input.Coordinates;
import input.parsing.CoordinateParser;
import logic.Plateau;
import logic.Rover;

import java.util.Scanner;

public class ConsoleInteraction {
    public Plateau getPlateau(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! Please specify the dimensions of your plateau in the format N N: ");
        while(true){
            try{
                CoordinateParser cp = new CoordinateParser();
                Coordinates result = cp.parseCoordinates(scanner.nextLine().split(" "));
                return new Plateau(result);
            } catch (IllegalArgumentException e){
                System.out.println("Invalid input, please try again");
            }
        }
    }

    public Rover addRoverToPlateau(){

    }

}
