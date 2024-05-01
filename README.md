# MorseCo/Con's Morse
MorseCo or Con's Morse is the name of my Program. Unlike alot of Morse Code Translator's found on the internet where all translated morse codes are converted to capitalized letters only, Con's Morse Translator can distinguish and convert morse code to either upper or lower case letters.

## Before Getting Started
Make sure you have installed the latest [JDK](https://www.oracle.com/java/technologies/downloads/#jdk19-linux) for your system

Next, make sure you put the jre/jdk bin folder into your systems enviornmental variable

## Getting Started
1. Download the zip folder of this project then Extract the contents of it.
2. Go into the directory MorseCo/out/artifacts/ConsMorse_jar/
3. There you will find the jar file that you can run to start the program.

## Program
![image](![image](https://github.com/InfernoCycle/MorseCo/assets/105338348/1b602e5c-db50-4ddc-89db-2f43f6a693da))
![image](https://user-images.githubusercontent.com/105338348/222265205-67b9b536-7ec5-4728-be31-f2ec9259f34e.png)

## Con's Morse Code Chart
The Morse Code Chart that I'm using can be found in [/MorseCo/src/randoFiles/morse.txt](https://github.com/InfernoCycle/MorseCo/blob/master/src/randoFiles/morse.txt)

## Issues
If for some reason the jar file cannot run, please run the source code provided by using the command line

Starting from the directory containing the source file "App.java":
```bash
javac App.java
java App.java
```

## Extra Notes
It is recommended to use my [chart](https://github.com/InfernoCycle/MorseCo/blob/master/src/randoFiles/morse.txt) in order for the program to distinguish uppercase from lowercase, but it is not required. 

What is required however is to make sure each morse code pattern has its own line and are space seperated between the letters and pattern.

##Future Updates
There will be a future update before the year end (2024) to accept regular case morse code.
