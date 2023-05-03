package JurassicPark.Map.Elements;

import java.awt.Point;

/** One of basic tile types, certain types of dinosaurs can eat plants. */
public class Plant extends Element{
	private int capacity;

	public Plant(Point point){super(point);}
	
	/** Returns capacity left in plant */
	public int getCapacity() {
		return this.capacity;
	}
	
	/** Decreases capacity in plant */
	public void decreaseCapacity() {
		if(this.capacity - 20 < 0) {
			this.capacity = 0;
		}
		else {
			this.capacity-=20;
		}
	}
}