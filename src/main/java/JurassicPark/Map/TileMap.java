package JurassicPark.Map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import JurassicPark.Dinosaurs.*;
import JurassicPark.Map.Elements.*;

/** Main component of simulation, tile map that stores all elements and dinosaurs as tile - extends from JPanel, drawn by Graphics2D */
public class TileMap extends JPanel{
	// COLOR SETS
    public static final Color cCarnivore = new Color(175,0,0);
    public static final Color cOmnivore = new Color(0,153,153);
    public static final Color cHerbivore = new Color(157, 204, 74);
    public static final Color cPlant = new Color(0,175,0);
    public static final Color cWater = new Color(0,0,175);
    public static final Color cTile = new Color(128,128,128);
    
    // CONSTRUCTORS
    public static Carnivore carnivore;
    public static Omnivore omnivore;
    public static Herbivore herbivore;
    public static Plant plant;
    public static WaterSource water;
    public static Tile tile;
    public static ArrayList<Object> gather;

    // ARRAY OF CONSTRUCTORS
    /** Array of constructors to store every tile that can appear on map */
    public static final Object[] array = {
    	carnivore,
    	omnivore,
    	herbivore,
    	plant,
    	water,
    	tile,
    	gather
    };

    /** size of tile map */
    private int size;    

    /** grid size of every tile */
    private int gridSize;
    
    // PROPABILITIES
    /** dinos probability to generate */
    private int dinosProb;
    /** elements probability to generate */
    private int elementsProb;

    //TODO: NOT SURE IF IT COULD BE STATIC
    /** Object two-dimensional array of all tiles */
    public static Object[][] tiles;
    
    //EVERY ARRAY FOR GENERATING RANDOM DINOSAURS TYPES ETC.
    /** Array that handles JSONToSpecie class methods 
     * @see JSONToSpecie*/
    public static JSONToSpecie jsonHandler = new JSONToSpecie();
    /** Array that stores every specie avilable to generate */
    public static ArrayList<Specie> arraySpecie = jsonHandler.readArrayOfSpecies();
    /** Array that stores all dinosaurs that were generated */
    public static ArrayList<Dinosaur> allDinos = new ArrayList<>();
    /** Array that stores all species of Carnivore */
    public static ArrayList<Specie> arrayCarnivore = new ArrayList<>();
    /** Array that stores all species of Omnivore */
    public static ArrayList<Specie> arrayOmnivore = new ArrayList<>();
    /** Array that stores all species of Herbivore */
    public static ArrayList<Specie> arrayHerbivore = new ArrayList<>();

    /** Constructor for TileMap, that stores 4 different parameters
     * @param size size of tilemap
     * @param dinosProb probability to generate dinosaur on tile
     * @param elementsProb probability to generate element on tile
     * @param gridSize grid size of every tile*/
    public TileMap(int size, int dinosProb, int elementsProb, int gridSize){
    	
    	this.size = size;
    	this.dinosProb = dinosProb;
    	this.elementsProb = elementsProb;
    	this.gridSize = gridSize;
        this.tiles = new Object[size][size];
        
        // STANDARD RANDOM IMPLEMENTATION
        Random r = new SecureRandom();
        
        int index = 0;
        
        // GET 3 ARRAYS OF 3 DIFFERENT TYPES OF SPECIES
        int specieIndex;
        
        for (int counter = 0; counter < arraySpecie.size(); counter++) { 		      
    		specieIndex = arraySpecie.get(counter).getType(); 
    		switch(specieIndex) {
    		case 0: arrayCarnivore.add(arraySpecie.get(counter));
    			break;
    		case 1: arrayOmnivore.add(arraySpecie.get(counter));
    			break;
    		case 2: arrayHerbivore.add(arraySpecie.get(counter));
    			break;
    		}
        }
        
        // RANDOMIZATION STRUCTURE
        int checkProb = 0;
        boolean checkProbDoubeled = false;
        String[][] whereProb = new String[size][size];
        int checkProbPW = 0;
        
        // TILE RANDOMIZER
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
            	if(i != 0 || j != 0) {
            	if( (checkProb <= elementsProb && checkProb >= dinosProb) || (checkProb <= elementsProb*2 && checkProbDoubeled == true && checkProb >= dinosProb)) {
            		checkProbDoubeled = true;	
            		for(int o = 0; o < 2; o ++) {
            			for(int  k= -2; k < 1; k ++) {
            				if(i+o >=0 && i+o < size && j+k >= 0 && j+k <size) {
            					if(index == 3)
            						whereProb[i+o][j+k] = "p";
            					else if(index == 4)
									whereProb[i+o][j+k] = "w";
            				}
            			}
            		}
            		
            	} 
            	else {
            		checkProbDoubeled = false;
            	}}
            	
            	//RANGE FROM 0 - 100 PROPABILITY
            	checkProb = r.nextInt(100-0)+0;
            	
            	//PROPABILITY RANDOMNESS SETUP
            	if(checkProb <= dinosProb) {
            		index = r.nextInt(array.length-3);
            	}
            	else if(checkProb <= elementsProb || (checkProbDoubeled == true && checkProb <= elementsProb*3)) {
            		checkProbPW = r.nextInt(101);
            		if(whereProb[i][j] == "p") {
            			if(checkProbPW < 90) {
            				index = 3;
            			}
            			else {
            				index = 4;
            			}
            		}
            		else if(whereProb[i][j] == "w") {
            			if(checkProbPW < 90) {
            				index = 4;
            			}
            			else {
            				index = 3;
            			}
            		}
            		else {
            			index = r.nextInt(array.length-4)+3;
            		}
            	}
            	else {
            		index = 5;
            	}
               
                Object randomObject = new Object();
                
                int indexSpecieRandom;
                
                switch(index) {
	                case 0: indexSpecieRandom = r.nextInt(arrayCarnivore.size());
	                		randomObject = new Carnivore(arrayCarnivore.get(indexSpecieRandom));
	                		((Carnivore)randomObject).setCords(j,i);
	                		((Carnivore)randomObject).increaseDinoCount();
	                		((Carnivore)randomObject).setDinoID();
		            	break;
		            case 1: indexSpecieRandom = r.nextInt(arrayOmnivore.size());
		            		randomObject = new Omnivore(arrayOmnivore.get(indexSpecieRandom));
		            		((Omnivore)randomObject).setCords(j,i);
		            		((Omnivore)randomObject).increaseDinoCount();
	                		((Omnivore)randomObject).setDinoID();
		            	break;
		            case 2: indexSpecieRandom = r.nextInt(arrayHerbivore.size());
		            		randomObject = new Herbivore(arrayHerbivore.get(indexSpecieRandom));
		            		((Herbivore)randomObject).setCords(j,i);
		            		((Herbivore)randomObject).increaseDinoCount();
	                		((Herbivore)randomObject).setDinoID();
		            	break;
		            case 3: randomObject = new Plant(new Point(j,i));
		            	break;
		            case 4: randomObject = new WaterSource(new Point(j,i));
		            	break;
		            case 5: randomObject = new Tile(new Point(j,i));
		            	break;
                }
                if(randomObject instanceof Dinosaur) {
                	allDinos.add((Dinosaur) randomObject);
                }
                this.tiles[i][j] = randomObject; 
            }
        }
        int preferredWidth = size * gridSize;
        int preferredHeight = preferredWidth;
        setPreferredSize(new Dimension(preferredWidth, preferredHeight));  
    }
    
    /** Method for painting graphics of tiles on screen 
     * @param g basic paint component*/
    @Override
    public void paintComponent(Graphics g) {
    	Graphics2D g2 = (Graphics2D) g;
        // Important to call super class method
    	//super.paint(g);
        super.paintComponents(g);
        super.paintComponents(g2);
        // Clear the board
        g2.clearRect(0, 0, getWidth(), getHeight());
        // Draw the grid
        int rectWidth = getWidth() / size;
        int rectHeight = getHeight() / size; 
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) { 	
                // Upper left corner of this terrain rect
                int x = j * rectWidth;
                int y = i * rectHeight;
   
                Object terrainObject = tiles[i][j];
                if(terrainObject instanceof Carnivore)
                {
                	g2.setColor(cCarnivore);
                }
                else if(terrainObject instanceof Herbivore)
                {
                	g2.setColor(cHerbivore);
                }
                else if(terrainObject instanceof Omnivore)
                {
                	g2.setColor(cOmnivore);
                }
                else if(terrainObject instanceof Plant)
                {
                	g2.setColor(cPlant);
                }
                else if(terrainObject instanceof WaterSource)
                {
                	g2.setColor(cWater);
                }
                else if(terrainObject instanceof Tile)
                {
                	g2.setColor(cTile);
                }
                
                g2.fillRect(x, y, rectWidth, rectHeight);
                
                g2.setColor(new Color(0,0,0));
                g2.drawRect(x,y, rectWidth, rectHeight);
            }
        }
    }
}
