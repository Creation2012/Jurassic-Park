package JurassicPark.Main;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import JurassicPark.Dinosaurs.Dinosaur;

/**
 * <h1>csvWriter</h1>
 * Class used for writing collected data from simulation, to csv file.
 */
public class csvWriter {
	
	/**
	 * Constructor of csvWriter.
	 * @param dinos collected array of every dinosaur on tilemap
	 */
  public csvWriter(ArrayList<Dinosaur> dinos) {

    try (PrintWriter writer = new PrintWriter(new File("Simulation_Results.csv"))) {

      StringBuilder sb = new StringBuilder();
      sb.append("dinoID");
      sb.append(';');
      sb.append("Specie");
      sb.append(';');
      sb.append("Name");
      sb.append(';');
      sb.append("Health");
      sb.append(';');
      sb.append("Hunger");
      sb.append(';');
      sb.append("Thirst");
      sb.append('\n');
      //this.dinoID + " " + this + " " + this.name + " " + this.health + "/" + this.maxHealth + " " + this.hunger + "/" + this.maxHunger + " " + this.thirst + "/" + this.maxThirst + " active: " + this.getIfActiveMove()
      for(Dinosaur d: dinos) {
    	  sb.append(d.getDinoID() + ";");
    	  sb.append(d.getName() + ";");
    	  sb.append(d.getSpecieName() + ";");
    	  sb.append(d.getHealth() + ";");
    	  sb.append(d.getHunger() + ";");
    	  sb.append(d.getThirst() + ";");
    	  sb.append("\n");
      }

      writer.write(sb.toString());

      System.out.println("Done!");

    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }
}