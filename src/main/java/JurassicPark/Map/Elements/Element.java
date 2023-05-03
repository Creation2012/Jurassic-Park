package JurassicPark.Map.Elements;

import java.awt.Point;
/** Parent abstrac class of all elements, stores coordinates */
public abstract class Element {
	protected Point cords;
	
	public Element(Point point) {this.cords = point;}
	
	/** Return current coordinates of element */
	public Point getCords() {
		return this.cords;
	}
	
	/** Set new coordinates of element */
	public void setCords(Point point) {
		cords.setLocation(point);
	}
}
