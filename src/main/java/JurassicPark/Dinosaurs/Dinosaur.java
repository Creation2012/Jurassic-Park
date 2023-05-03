package JurassicPark.Dinosaurs;

import java.awt.Point;
import JurassicPark.Map.Elements.Plant;
import JurassicPark.Map.Elements.Tile;
import JurassicPark.Map.Elements.WaterSource;

/** Basic abstract function of actor Dinosaur in simulation, that implements basically every method from implemented interfaces
 * @see DinosaurStats
 * @see DinosaurProperties
 * */

public abstract class Dinosaur implements DinosaurActions, DinosaurStats {
	/** Coordinates of dinosaur */
	private Point cords = new Point();
	/** Variable that stores number of dinosaurs */
	private static int dinoCount = 0;
	/** Specific unique id for every dinosaur */
	private int dinoID;
	
	private Specie specie;
	
	private String name;
	
	private int herdID;
	
	private int health;
	private final int maxHealth;
	
	private int attack;
	
	private int hunger;
	private final int maxHunger;
	
	private int thirst;
	private final int maxThirst;
	
	private int speed;
	
	/** Boolean variable for determining if dino is able to move in this iteration */
	private boolean active = true;
	private boolean alive = true;
	
	/** Constructor for dinosaur, that stores data from Specie */
	public Dinosaur(Specie specie, String name, int herdID, int health, int attack, int hunger, int thirst, int speed) {
		this.specie = specie;
		this.name = name;
		this.herdID = herdID;
		this.health = health;
		this.attack = attack;
		this.hunger = hunger;
		this.thirst = thirst;
		this.speed = speed;
		this.maxHealth = health;
		this.maxHunger = hunger;
		this.maxThirst = thirst;
	}
	
	/** Void method that switched if dino is able to move or not */
	@Override
	public void switchDinoMove() {
		if(this.active == true)
			this.active = false;
		else
			this.active = true;
	}
	
	/** Boolean method that returns active state of dinosaur
	 * @return active */
	@Override
	public boolean ifActiveMove() {
		return this.active;
	}
	
	@Override
	public int getSpeed() {
		return this.speed;
	}
	
	/** Method that searches by speed of dino, to find every empty tile where he can move 
	 * @return tilesAroundToMove
	 * @param j  current y position of dinosaur
	 * @param k current x position of dinosaur
	 * @param tiles all tiles
	 * @param size size of tilemap*/
	public Tile[][] tilesAroundToMove(int j, int k, Object[][] tiles, int size) {
		int sizeFix = speed*2+1;
		//System.out.println("SIZEFIX: " + sizeFix);
		Tile[][] tilesAroundToMove = new Tile[sizeFix][sizeFix];
		for(int n=j-speed; n<=j+speed; n++) {
			for(int m=k-speed; m<=k+speed; m++) {
				if(n==j && m ==k) {
					tilesAroundToMove[n-j+speed][m-k+speed] = null;
					}
				else if(n >= 0 && n < size && m >= 0 && m <size) {
					if(tiles[n][m] instanceof Tile){
						tilesAroundToMove[n-j+speed][m-k+speed] = (Tile) (tiles[n][m]);
					}
					else {
						tilesAroundToMove[n-j+speed][m-k+speed] = null;
					}
					
				}
			}
		}
		return tilesAroundToMove;
	};	
	
	/** Method that returns every tile around dinosaur
	 * @return tilesAround
	 * @param j  current y position of dinosaur
	 * @param k current x position of dinosaur
	 * @param tiles all tiles
	 * @param size size of tilemap*/
	public Object[][] tilesAround(int j, int k, Object[][] tiles, int size) {
		Object[][] tilesAround = new Object[3][3];
		for(int n=j-1; n<=j+1; n++) {
			for(int m=k-1; m<=k+1; m++) {
				if(n == j && m == k) {
					tilesAround[n-j+1][m-k+1] = null;
					}
				else if(n >= 0 && n < size && m >= 0 && m <size) {
					if(tiles[n][m] instanceof Dinosaur){
						if(tiles[n][m] instanceof Carnivore) {
							tilesAround[n-j+1][m-k+1] = (Carnivore)tiles[n][m];
						}
						else if(tiles[n][m] instanceof Omnivore) {
							tilesAround[n-j+1][m-k+1] = (Omnivore)tiles[n][m];
						}
						else if(tiles[n][m] instanceof Herbivore) {
							tilesAround[n-j+1][m-k+1] = (Herbivore)tiles[n][m];
						}
						
					}
					else if(tiles[n][m] instanceof Plant) {
						tilesAround[n-j+1][m-k+1] = (Plant)tiles[n][m];
					}
					else if(tiles[n][m] instanceof WaterSource) {
						tilesAround[n-j+1][m-k+1] = (WaterSource)tiles[n][m];
					}
					else {
						tilesAround[n-j+1][m-k+1] = null;
					}
				}
			}
		}
		return tilesAround;
	};	
	
	@Override
	public int getDinoID() {return this.dinoID;}
	
	@Override
	public int getDinoCount() {return dinoCount;}
	
	@Override
	public Specie getSpecie() {return specie;}
	
	@Override
	public String getName() {return name;}
	
	@Override
	public int getHerdID() {return herdID;}
	
	@Override
	public int getHealth() {return health;}
	
	@Override
	public int getMaxHealth() {return maxHealth;}
	
	@Override
	public int getHunger() {return hunger;}
	
	@Override
	public int getMaxHunger() {return maxHunger;}
	
	@Override
	public int getThirst() {return thirst;}
	
	@Override
	public int getMaxThirst() {return maxThirst;}
	
	public void setCords(int x, int y) {
		cords.setLocation(x,y);
		}
	
	/** Method for operating on Point of dino (Point is class from java.awt to store X and Y position) */
	public void setCords(Point tilePos) {
		cords.setLocation(tilePos);
	}
	
	public Point getCords() {
		return cords.getLocation();
		};
	
	/** Method checks if dinosaur is alive*/
	@Override
	public boolean isAlive() {return alive;}
	
	/** Method increase number of dinosaurs */
	@Override
	public void increaseDinoCount() {this.dinoCount++;}
	
	@Override
	public void setDinoID() {this.dinoID = this.dinoCount;}
	
	/** Method for resetting dinosaur count */
	public static void restartCount() {dinoCount = 0;}
	
	/** Method that handles attack of dinosaur
	 * @param target dinosaur that is being attacked */
	@Override
	public void attack(Dinosaur target) {
		target.decreaseHealth(this.attack);
		if(this.hunger + this.attack > this.maxHunger) {
			this.hunger = this.maxHunger;
		}
		else {
			this.hunger += this.attack;
		}
	}
	
	/** Method that handles eating plant 
	 * @param plant plant thas is being eaten*/
	@Override
	public void eatPlant(Plant plant) {
		plant.decreaseCapacity();
		if(this.hunger + 20 > this.maxHunger) 
			this.hunger = this.maxHunger;
		else
			this.hunger += 20;
	}

	/** Method that handles drinking water 
	 * @param water water source that is being consumed*/
	@Override
	public void drink(WaterSource water) {
		// TODO Auto-generated method stub
		water.decreaseCapacity();
		if(this.thirst + 20 > this.maxThirst)
			this.thirst = this.maxThirst;
		else
			this.thirst += 20;
	}
	
	/** Method for decreasing health of dinosaur
	 * @param value value of how much it will decrease health */
	@Override
	public void decreaseHealth(int value) {
		if(this.health - value < 0) {
			this.health = 0;
			deadDino();
			
		}
		else {
			this.health-= value;
		}
	}
	
	/** Method that decreases hunger of dinosaur
	 * @param value value how much is decreased hunger */
	@Override
	public void decreaseHunger(int value) {
		if(this.hunger - value < 0) {
			this.hunger = 0;
		}
		else {
			this.hunger -= value;
		}
	}
	
	/** Method that decreases thirst of dinosaur
	 * @param value value how much is decreased thirst */
	@Override
	public void decreaseThirst(int value) {
		if(this.thirst - value < 0) {
			this.thirst = 0;
		}
		else {
			this.thirst -= 20;
		}
	}

	/** Method that set dinosaur to inactive, and alive status to false
	 * @see alive
	 * @see active*/
	@Override
	public void deadDino() {
		this.alive = false;
		this.active  = false;
	}
	
	/** Method for debugging, and checking all current stats of dinosaur */
	@Override
	public void getStats() {
		System.out.println(this.dinoID + " " + this + " " + this.name + " " + this.health + "/" + this.maxHealth + " " + this.hunger + "/" + this.maxHunger + " " + this.thirst + "/" + this.maxThirst + " active: " + this.ifActiveMove());
	}

	/** Method for returning name of type of dinosaur (Carnivore, Omnivore, Herbivore) 
	 * @return name */
	@Override
	public String getSpecieName() {
		return name;
		
	}
}
