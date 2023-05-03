package JurassicPark.Dinosaurs;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/** Class that transfers JSON from resources into ArrayList of Specie Class 
 * @see Specie */

public class JSONToSpecie extends ArrayList{
	// DEFAULT VALUES OF EVERY ESSENTIAL VARIABLE FOR DINOSAUR
	private static int herdID;
	private static String name;
	private static int type;
	private static int health;
	private static int attack;
	private static int hunger;
	private static int thirst;
	
	/** Constructor that starts method @see readArrayOfSpecies, to add Specie look for src/main/resources/dinosaurs.json */
	public JSONToSpecie(){readArrayOfSpecies();}
	
	/** Main function of JSONToSpecie, that converts all data 
	 * @return arraySpecie*/
	public ArrayList<Specie> readArrayOfSpecies(){
		String path = "src/main/resources/dinosaurs.json";
		ArrayList <Specie> arraySpecie = new ArrayList<Specie>();
		
		try {
			Reader reader = null;
			
			reader = Files.newBufferedReader(Paths.get(path));
			
			JsonObject parser = JsonParser.parseReader(reader).getAsJsonObject();
			
			JsonArray species = parser.get("dinosaurs").getAsJsonArray();
			
			species.forEach(entry -> {JsonObject pro = (JsonObject) entry;
			herdID = pro.get("herdID").getAsInt();
			name = pro.get("name").getAsString();
			type = pro.get("type").getAsInt();
			health = pro.get("health").getAsInt();
			attack = pro.get("attack").getAsInt();
			hunger = pro.get("hunger").getAsInt();
			thirst = pro.get("thirst").getAsInt();
			arraySpecie.add(new Specie(herdID, name, type, health, attack, hunger, thirst));
			});
			
			/**Iterator it = arraySpecie.iterator();
			while(it.hasNext()) {
				System.out.println(it.next() + " ");
			}**/
			
			//return arraySpecie;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return null;
		}
		return arraySpecie;
		
	}
}
