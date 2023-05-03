package JurassicPark.Map.Elements;

import java.awt.Point;

/** One of basic tile types, dinosaurs can drink from water source */
public class WaterSource extends Element{
	private int capacity;
	
	/** Constructor of WaterSource, that use constructor of */
	public WaterSource(Point point){super(point);}
	
	/** Returns capacity left in water source */
	public int getCapacity() {
		return capacity;
	}
	
	/** Decreases capacity in water source */
	public void decreaseCapacity() {
		if(this.capacity - 20 < 0)
			this.capacity = 0;
		else
			this.capacity -= 20;
	}
}