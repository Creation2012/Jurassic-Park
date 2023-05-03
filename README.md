# Jurassic Park Simulation
Project for WUST course PO (Objective Programming). Jurassic Park simulation, with tile based map. 
The reason for creating this project, was to learn paradigms of OOP. 

## Overview
The simulation will be a kind of reenactment of the movie Jurassic Park, with the main focus on the
on the life cycle of dinosaurs - the division into herbivory, omnivorousness and carnivory. A limited list of
dinosaurs based on an encyclopedia (this encyclopedia will be included in the simulation code, it will be a
a list of specific species imported from the dinosaurs.json file). The board on which the
simulation will randomly generate specific dinosaurs. The board for herbivores will create an object
which is a representation of their food. A water object will also be created - to fill their thirst.

## Running
For running this project, use Java JDK 11, with gradle 6.6. 
If Gradle project won't import, add in Gradle Advanced Options to Java home destination to the JDK.
