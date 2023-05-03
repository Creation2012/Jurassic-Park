package JurassicPark.Dinosaurs;

/** One of type of dinosaurs, herbivore can only eat by eating plants. */
public class Herbivore extends Dinosaur{
	/** Basic constructor for Carnivore, gives speed 1 */
	public Herbivore(Specie specie){
		super(specie,specie.getName(),specie.getHerdID(), specie.getHealth(),specie.getAttack(),specie.getHunger(),specie.getThirst(),1);
	}
	
	/** Returns name of type */
	@Override
	public String getSpecieName() {
		return "Herbivore";
	}
}
