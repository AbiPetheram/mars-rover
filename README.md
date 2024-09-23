# Mars Rover

Welcome to Mission Control, a place where exploration zones and rovers are managed on the surface of Mars. Join us for a space adventure where you can create Plateaus, place Rovers and move the rovers around the surface of Mars. 

# Installation

1. Clone the repo using `git clone https://github.com/AbiPetheram/mars-rover`
2. Open using IDE such as Intellij and run the application.
3. Follow the instructions and use the command line to interact with the application. 


# How to use

## Key features

In this Command Line Interface application, you can:

* Create a Plateau of your choice of size.
* Add a Rover to a position on the Plateau.
* Move a rover around the Plateau.
* Add additional Rover to the Plateau.

Collision handling between rovers and movement outside the plateau is handled with descriptive messages.

## Input format

* Pleateau size must be defined by 2 positive integer coordiantes in the format `0 0`.
* Position of a rover is defined as coordinates and a direction (North, East, South, West) and must be defined in the format `0 0 N`.
    * If coordinates are not within the defined Plateau size, you will be prompted to define a new position.
* Instructions to move a rover can include L/R for change in direction and M to move foward, e.g. `MMMLMR`.
    * If end position is not within the defined Plateau size, you will be prompted to define new instructions.
 
### Future considerations

The current implementation of this project is a simple CLI with basic allocation of plateaus and rovers. Future considerations for improvements and extra features are:
* Split out the logic from Console Interaction into the service layer to ensure the UI only handles the communication with the user to fully separate concerns.
* Graphical user interface using something such as an android app with a view of the plateau and rover within the application.
* Ability to set up multiple Mission Control centres, perhaps looking at multiple planets. 
