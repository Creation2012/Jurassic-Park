package JurassicPark.Dinosaurs;

import JurassicPark.Map.Elements.Plant;
import JurassicPark.Map.Elements.WaterSource;

/** Interface for storing basic actions available for dinosaur */
public interface DinosaurActions {
	public void attack(Dinosaur target);
	public void eatPlant(Plant plant);
	public void drink(WaterSource water);
	public void switchDinoMove();
	public void decreaseHealth(int value);
	public void decreaseHunger(int value);
	public void decreaseThirst(int value);
	public void increaseDinoCount();
	public void setDinoID();
	public void deadDino();
}
