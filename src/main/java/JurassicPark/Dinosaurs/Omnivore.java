package JurassicPark.Dinosaurs;

/** One of type of dinosaurs, omnivore can eat by attacking other dinosaurs or eat plants. */
public class Omnivore extends Dinosaur{
	
	/** Basic constructor for Carnivore, gives speed 2 */
	public Omnivore(Specie specie){
		super(specie,specie.getName(),specie.getHerdID(), specie.getHealth(),specie.getAttack(),specie.getHunger(),specie.getThirst(),2);
	}
	
	/** Returns name of type */
	@Override
	public String getSpecieName() {
		return "Omnivore";
	}
}
