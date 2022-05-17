# SPAN-LEAGUE-UI #
This module provides a _**graphical user interface application**_ (_**GUI Application**_) to run
The _**Span-League-Engine-Module**_ which is responsible to perform all the business logic operations
regarding  the league processing. The _**GUI-Application**_ receives the location of the _**league file**_
through a text field to start the file processing of the league through the _**Span-League-Engine-Module**_
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
Compile the _**Span-League-Engine module**_ which is the dependency of this module, to do that follow 
the next steps:

``` bash
cd Span-League-Engine/
mvn clean install
```
ensure that a jar file called _**Span-League-Engine.jar**_ was generated running the next commands:

``` bash
 ls -l target/
 
 # An output similar to this one, should show you that the Jar file was Generated correctly 
 # look for Span-League-Engine.jar which is the dependency of the gui application
 #
 # -rw-r--r--  1 Foo  Foo   4.2M May 16 17:17 Span-League-Engine.jar
 # drwxr-xr-x  3 Foo  Foo   102B May 16 17:17 classes/
 # drwxr-xr-x  3 Foo  Foo   102B May 16 17:17 generated-sources/
 # drwxr-xr-x  3 Foo  Foo   102B May 16 17:17 maven-archiver/
 # drwxr-xr-x  3 Foo  Foo   102B May 16 17:17 maven-status/
 # -rw-r--r--  1 Foo  Foo    22K May 16 17:17 original-Span-League-Engine.jar
```

### 2.- COMPILE THE SPAN-LEAGUE-UI MODULE ###
Once that you have compiled the _**Span-League-Engine module** you can proceed to compile the next module
in this case _**SPAN-LEAGUE-UI**_ for the GUI application following the next steps:

``` bash
cd Span-League-UI/
mvn clean install
```

ensure that a jar file called _**Span-League-UI.jar**_ was generated running the next commands:

``` bash
ls -l target/

 # An output similar to this one, should show you that the Jar file was Generated correctly 
 # look for Span-League-UI.jar which is the GUI application that we want to run
 #
 # -rw-r--r--  1 Foo  Foo   4.9M May 16 18:31 Span-League-UI.jar
 # drwxr-xr-x  4 Foo  Foo   136B May 16 18:31 classes/
 # drwxr-xr-x  3 Foo  Foo   102B May 16 18:31 generated-sources/
 # drwxr-xr-x  3 Foo  Foo   102B May 16 18:31 maven-archiver/
 # drwxr-xr-x  3 Foo  Foo   102B May 16 18:31 maven-status/
 # -rw-r--r--  1 Foo  Foo    15K May 16 18:31 original-Span-League-UI.jar
```

Once that you have the application compiled, run the application following the next steps:

``` bash
java -jar target/Span-League-UI.jar 
```
A UI Should be displayed, introduce the file path of the league file or choose a path through a file chooser
in the menu file and then press the start league button to show the results.