<h1>Mars Rover</h1>
plan of approach:
UI layer
- takes input from user
- validates input and prepares the data to send to the input layer
- gets position of rover to print to user

Input layer
- parse coordinates
- parse direction
- parse instructions
- contains classes & enums to store each

Logic layer
- holds the calculations
- stores rover and plateau

process flows
initial position:
console input from user
UI layer splits string into string array
pass to parser to return coordinates and direction
pass results of that to the position class

movement:
console input from user
UI layer validates the string input
pass to parser to return list of enum (?) or pass in one by one?
pass enums to logic layer to perform movement and print final position

