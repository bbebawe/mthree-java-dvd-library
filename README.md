# DvdLibrary 1.0 Release Notes
23rd July 2020

## Overview
A program that allows the user to store and manipulate a collection of DVDs

First release.

## What’s New
* You can add a new DVD to the collection
* You can remove a DVD from the collection
* You can search a for a DVD by title and display information
* You can List all DVDs in the collection
* You can add, edit, or remove more than one DVD in one session
* Collection data is saved and loaded from hard drive

## Installing DvdLibray

### Prerequisites
The program can run on Windows. To be able to run the program ensure you have the following

* Latest Java Runtime Environment (version 8)
* Java path is correctly set on your operating system

### Downloading Latest Java Runtime Environment (JRE)
In your web browser navigate to the following website 

https://www.oracle.com/java/technologies/javase-jre8-downloads.html

If you are using windows 32-bits, choose the executable file jre-8u261-windows-i586-iftw.exe

If you are using windows 64-bits, choose the executable file jre-8u261-windows-x64.exe

Accept the license agreement and click the download button to download your file

### Installing Latest Java Runtime Environment (JRE)
Navigate to the location where you have download the JRE file (default location is downloads)

Start the JRE installer by double-clicking the installer's icon or file name in the download location.

Follow the instructions provided by the Installation wizard

After the installation is finished you should have the JRE installed on the following path C:\Program Files\Java\jre1.8.0_261 

### Add Java path to operating system
Open windows run by pressing win + r 
1.	Type sysdm.cpl and press Enter button
2.	In the system properties window, navigate to the Advanced tab and click on Environment Variables
3.	Under System variables double click on Path
4.	Click on New and paste the following path C:\Program Files\Java\jre1.8.0_261\bin
5.	Then we will click OK and OK in the environment variables screen which will save and activated new path configuration.

### Check Java path to is set correctly
Open windows run by pressing win + r 
1.	Type cmd and hit enter
2.	In cmd.exe window type java -version and hit enter
3.	You should see java version “1.8.0_261” if not, ensure you have set the path correctly using the steps mentioned above
### Installing and Running DvdLibray
Download the DvdLibray.zip and extract it. 

Move the extract DvdLibrary folder to following path C:\Users\Public then hold win + r to open windows run, then type cmd 

In cmd window type cd C:\Users\Public\DvdLibrary and hit enter

To run the program, type the following command and hit enter

java -jar DvdLibrary-1.0-SNAPSHOT-jar-with-dependencies.jar

## Uninstalling DvdLibrary 
Hold win + r keys to open windows run

In cmd window type cd C:\Users\Public and hit enter

Type the following command and hit enter to remove the application 

rmdir DvdLibrary

## Troubleshooting
If you run into problems running the program, ensure the following:
* Latest Java Runtime Environment is installed correctly
* Java path is correctly set in your operating systemy
*	You are using the correct path to run the jar file
* Jar file and collection.txt are in same folder in the correct path
