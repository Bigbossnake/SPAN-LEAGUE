# SPAN-LEAGUE-ENGINE-MODULE #
The _**Span-League-Engine-Module**_ is responsible to perform all the business logic operations regarding
the league processing. This module is responsible to load league information from a league file that needs
to be provided by the user then load the information into a list and process the list with the information
received from the file. At the end of the execution if no errors where found the engine prints the league
results of the league sorted by points and by name in a Desc. order, for more information please see the 
Sample Output.

## League Rules ##
In this league (_**Span-League**_), we have the following rules

- A draw (tie) is worth _**1 point**_ for each team of the match
- A win is worth _**3**_ points for the winner team of the match
- A loss is worth _**0**_ points.

_**IMPORTANT:** If two or more teams have the same number of points, they should have the same rank and be
printed in alphabetical order (as in the tie for 3rd place in the sample data)._

## Span League File ## 
The _**Span-League-File**_ is the input that contains the results of the games, one per line, that later is
going to be processed by the _**Span-League-Engine-Module**_ . For more information about the _**Span-League-File**_
See _**Sample input**_. The _**Span-League-File**_ generally ends with an extension _**league**_ although any other
text-based file is accepted as an input as long as contains a valid structure like the below snapshot:

#### Sample Input ####
```
Lions 3, Snakes 3
Tarantulas 1, FC Awesome 0
Lions 1, FC Awesome 1
Tarantulas 3, Snakes 1
Lions 4, Grouches 0
```

#### Sample Output ####
```
1. Tarantulas, 6 pts
2. Lions, 5 pts
3. FC Awesome, 1 pt
3. Snakes, 1 pt
4. Grouches, 0 pts
```

### COMPILE THE SPAN-LEAGUE-ENGINE-MODULE ###
To Compile the _**Span-League-Engine module**_ separately, follow the next steps:

``` bash
cd Span-League-Engine/
mvn clean install
```
ensure that a jar file called _**Span-League-Engine.jar**_ was generated running the next commands:

``` bash
 ls -l target/
 
 # An output similar to this one, should show you that the Jar file was Generated correctly 
 # look for Span-League-Engine.jar which is the dependency of the console application
 #
 # -rw-r--r--  1 Foo  Foo   4.2M May 16 17:17 Span-League-Engine.jar
 # drwxr-xr-x  3 Foo  Foo   102B May 16 17:17 classes/
 # drwxr-xr-x  3 Foo  Foo   102B May 16 17:17 generated-sources/
 # drwxr-xr-x  3 Foo  Foo   102B May 16 17:17 maven-archiver/
 # drwxr-xr-x  3 Foo  Foo   102B May 16 17:17 maven-status/
 # -rw-r--r--  1 Foo  Foo    22K May 16 17:17 original-Span-League-Engine.jar
```

## IMPORTANT-NOTE ##
This module doesn't produce an _**executable file**_. it produces a jar file called _**Span-League-Engine.jar**_
which later is going to be imported as a dependency in the different modules of the application:

- _**Span-League-Non-UI**_
- _**Span-League-UI**_





