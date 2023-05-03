package JurassicPark.Dinosaurs;

import java.awt.Point;
/** Interface for storing basic methods that return statistic of dinosaur:w
 *  */
public interface DinosaurStats {
	public int getDinoID();
	public Specie getSpecie();
	public int getHealth();
	public int getHunger();
	public int getThirst();
	public String getName();
	public boolean isAlive();
	public int getHerdID();
	public int getDinoCount();
	public Point getCords();
	public int getMaxHealth();
	public int getMaxHunger();
	public int getMaxThirst();
	public boolean ifActiveMove();
	public int getSpeed();
	public String getSpecieName();
	public void getStats();
}
