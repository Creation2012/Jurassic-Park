package JurassicPark.Dinosaurs;

/** One of type of dinosaurs, carnivore can only eat by attacking other dinosaurs, can't eat plants. */
public class Carnivore extends Dinosaur {
	
	/** Basic constructor for Carnivore, gives speed 3 */
	public Carnivore(Specie specie){
		super(specie,specie.getName(),specie.getHerdID(), specie.getHealth(),specie.getAttack(),specie.getHunger(),specie.getThirst(),3);
	}
	
	/** Returns name of type */
	@Override
	public String getSpecieName() {
		return "Carnivore";
	}
}


