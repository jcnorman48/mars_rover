package com.james.marsrover;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Run {

    public static void main(String[] args){

        //initial input
        String input = "5 5 1 2 N LMLMLMLMM 3 3 E MMRMMRMRRM";
        System.out.println(run(input));
    }

    static String run(String input){
        //parse the input
        Parser parser = Parser.parse(input);

        //parse, map each RoverArgs to Rover, execute Operations, print result
        return parser.roverArgs.stream()
                .map(r ->
                        new Rover(parser.gridWidth, parser.gridHeight, r.startPosition)
                                .executeOperations(r.operations)
                )
                .map(Object::toString) //toString is actually the endorsed method for joining strings...
                .collect(Collectors.joining(" "));
    }

    /**
     * Starting position and Operations to send to the rover
     */
    private static final class RoverArgs {

        final Position startPosition;
        final List<Operation> operations;

        RoverArgs(Position startPosition, List<Operation> operations){
            this.startPosition = startPosition;
            this.operations = operations;
        }
    }

    /**
     * Parse the string as:
     * gridWidth gridHeight r1X r1Y r1Orientation r1Operations r2X r2Y r2Orientation r2Operations
     *
     * Note: as there is no external input the string will NOT be validated in the parser
     */
    static final class Parser {

        final int gridWidth;
        final int gridHeight;
        final List<RoverArgs> roverArgs;

        public Parser(int gridWidth, int gridHeight, List<RoverArgs> roverArgs){
            this.gridWidth = gridWidth;
            this.gridHeight = gridHeight;
            this.roverArgs = roverArgs;
        }

        static Parser parse(String arg){

            List<String> parts = Arrays.asList(arg.split(" "));
            int gridX = Integer.parseInt(parts.get(0));
            int gridY = Integer.parseInt(parts.get(0));

            List<String> rawRoverArgs = parts.subList(2, parts.size());
            List<RoverArgs> roverArgs = new ArrayList<>();
            //Went with a sublist list partition to parse all the rover args, this would be much cleaner
            //with a guava PartitionedList....
            for(int i=0; i<rawRoverArgs.size(); i+=4){
                roverArgs.add(Stream.of(rawRoverArgs.subList(i, i+4)).map(Parser::parseRoverArgs).findFirst().get());
            }

            return new Parser(gridX, gridY, roverArgs);
        }

        static RoverArgs parseRoverArgs(List<String> roverArgs){

            int startX = Integer.parseInt(roverArgs.get(0));
            int startY = Integer.parseInt(roverArgs.get(1));

            Orientation orient = Orientation.valueOf(roverArgs.get(2));
            List<Operation> ops = roverArgs.get(3).chars()
                    .mapToObj(i -> (char)i)
                    .map(ch -> Operation.valueOf(Character.toString(ch)))
                    .collect(Collectors.toList());

            return new RoverArgs(new Position(startX,startY,orient), ops);
        }
    }
}
