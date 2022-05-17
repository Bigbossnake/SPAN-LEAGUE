# SPAN-LEAGUE-NON-UI #
This module provides a _**non-user graphical user interface application**_ (_**Console Application**_) to run 
The _**Span-League-Engine-Module**_ which is responsible to perform all the business logic operations 
regarding  the league processing. The _**Console-Application**_ receives the location of the _**league file**_ 
through an argument of the command line to start the file processing of the league through the _**Span-League-Engine-Module**_
which is included as a dependency of the application.

## Module Compilation ## 
In order to run this application please follow the next instructions, and meet the following requirements:
- _**Apache Maven 3.8.5 or Later**_
- _**Java 18 or later**_

_**IMPORTANT:**_ The following instructions only applies if you want to compile the modules separately. There is
a POM file on the project root that compiles the 3 modules of the application in one step. 

- _**SPAN-LEAGUE-ENGINE**_
- _**SPAN-LEAGUE-NON-UI**_
- _**SPAN-LEAGUE-UI**_

### 1.- COMPILE THE SPAN-LEAGUE-ENGINE-MODULE ###
Compile the _**Span-League-Engine module**_ which is the dependency of this module, to that follow the next steps:

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

### 2.- COMPILE THE SPAN-LEAGUE-NON-UI MODULE ###
Once that you have compiled the _**Span-League-Engine module** you can proceed to compile the next module
in this case _**SPAN-LEAGUE-NON-UI**_ for the console application following the next steps:

``` bash
cd Span-League-Non-UI/
mvn clean install
```

ensure that a jar file called _**Span-League-Non-UI.jar**_ was generated running the next commands:

``` bash
ls -l target/

 # An output similar to this one, should show you that the Jar file was Generated correctly 
 # look for Span-League-Non-UI.jar which is the console application that we want to run
 #
 # -rw-r--r--  1 Foo  Foo   4.3M May 16 17:29 Span-League-Non-UI.jar
 # drwxr-xr-x  4 Foo  Foo   136B May 16 17:29 classes/
 # drwxr-xr-x  3 Foo  Foo   102B May 16 17:29 generated-sources/
 # drwxr-xr-x  3 Foo  Foo   102B May 16 17:29 maven-archiver/
 # drwxr-xr-x  3 Foo  Foo   102B May 16 17:29 maven-status/
 # -rw-r--r--  1 Foo  Foo   3.8K May 16 17:29 original-Span-League-Non-UI.jar
```

Once that you have the application compiled, run the application following the next steps:

``` bash
java -jar target/Span-League-Non-UI.jar <PATH_TO_LEAGUE_FILE>

# NOTE REMEMBER REPLACE THE TOKEN <PATH_TO_LEAGUE_FILE> WITH A VALID PATH IN FILESYSTEM LIKE:
java -jar target/Span-League-Non-UI.jar /Users/foo/Desktop/league-example.league 
```

If the league simulation ran successfully you should see an output showing you the results of 
the league simulation:

```
#########################################
#        WELCOME TO SPAN LEAGUE         #
#########################################
2022-05-16T17:38:18,053 [span-league-non-ui-log] INFO  [main] com.span.league.engine.loader.LeagueLoader.loadLeagueFromFile:94 - League File Received: /Users/oachavez/Desktop/league-example.league
2022-05-16T17:38:18,054 [span-league-non-ui-log] INFO  [main] com.span.league.engine.loader.LeagueLoader.loadLeagueFromFile:98 - Loading Simulation From File: /Users/oachavez/Desktop/league-example.league
2022-05-16T17:38:18,057 [span-league-non-ui-log] INFO  [main] com.span.league.engine.LeagueEngine.runLeague:104 - Running League Simulation...
2022-05-16T17:38:18,058 [span-league-non-ui-log] INFO  [main] com.span.league.engine.LeagueEngine.runLeague:107 - SUCCESS! League Simulation Finished With: 
1. Tarantulas 6 pts
2. Lions 5 pts
3. FC Awesome 1 pts
3. Snakes 1 pts
4. Grouches 0 pts
```




