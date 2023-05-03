package JurassicPark.Dinosaurs;

/** Class Specie stores default values of every dino, to add Specie look for src/main/resources/dinosaurs.json */
public class Specie {
	private int herdID;
	private String name;
	private int type;
	private int health;
	private int attack;
	private int hunger;
	private int thirst;
	
	public Specie(int herdID, String name, int type, int health, int attack, int hunger, int thirst) {
		this.herdID = herdID;
		this.name = name;
		this.type = type;
		this.health = health;
		this.attack = attack;
		this.hunger = hunger;
		this.thirst = thirst;
	};
	
	public int getHerdID() {return herdID;};
	public String getName() {return name;};
	public int getType() {return type;};
	public int getHealth() {return health;};
	public int getAttack() {return attack;};
	public int getHunger() {return hunger;};
	public int getThirst() {return thirst;};
	
	public void setHerdID(int herdID) {this.herdID = herdID;};
	public void setType(int type) {this.type = type;};
	public void setName(String name) {this.name = name;};
	public void setHealth(int health) {this.health = health;};
	public void setAttack(int attack) {this.attack = attack;};
	public void setHunger(int hunger) {this.hunger = hunger;};
	public void setThirst(int thirst) {this.thirst = thirst;};
	
	/** Override toString() method, helps debbuging */
	@Override
	public String toString() {
		return "Specie [herdID=" + herdID + ", name=" + name + ", type=" + type + ", health=" + health + ", attack=" + attack + ",hunger=" + hunger + ",thirst=" + thirst + "]";
	}
	}
