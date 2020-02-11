# Mars Rover
Implementation of the Mars Rover coding challenge.  
This is a (close) functional programming implementation in java  

## Testing and Building
./gradlew build

## Running
To validate run the Unit test `test/java/com/james/marsrover/RunTest`   

Alternatively, download the [jar](https://github.com/jcnorman48/mars_rover/raw/master/mars_rover-1.0.jar)  
and execute: ` java -cp mars_rover-1.0.jar com.james.marsrover.Run "5 5 1 2 N LMLMLMLMM 3 3 E MMRMMRMRRM"`

## Starting webserver
The webserver can be started by downloading the [jar](https://github.com/jcnorman48/mars_rover/raw/master/mars_rover-1.0.jar)  
execute: `java -jar mars_rover-1.0.jar`  
And Open: <http://127.0.0.1:8085/?input=5_5_1_2_N_LMLMLMLMM_3_3_E_MMRMMRMRRM> in a browser to view results