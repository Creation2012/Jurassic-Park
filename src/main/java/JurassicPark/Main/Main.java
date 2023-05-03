package JurassicPark.Main;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import JurassicPark.Dinosaurs.*;
import JurassicPark.Gui.Gui;

import JurassicPark.Map.TileMap;
import JurassicPark.Map.Elements.*;

/**
 * <h1>JurassicPark</h1>
 * Simulation that simply simulates world of dinosaurs on a map, with simple AI (simplified to if-elseif) with basic GUI.
 * @author Bartosz Ostrowski
 * @version 5.1.1
 * @since 2021-06-20
 */

public class Main{
	/** Array that stores copied values of entire tilemap from class TileMap */
	protected static Object[][] tiles;
	/** Array that stores all dinos on tilemap */
	protected static ArrayList<Dinosaur> allDinos = new ArrayList<Dinosaur>();
	/** Array that stores dinosaurs in range for active dinosaur, after move */
	protected static ArrayList<Dinosaur> dinosInRange = new ArrayList<Dinosaur>();
	/** Array that stores plants in range for active dinosaur, after move */
	protected static ArrayList<Plant> plantsInRange = new ArrayList<Plant>();
	/** Array that stores water sources in range for active dinosaur, after move */
	protected static ArrayList<WaterSource> waterInRange = new ArrayList<WaterSource>();
	/** Array that stores tiles in range for active dinosaur, for move */
	protected static ArrayList<Tile> tilesInRange = new ArrayList<Tile>();
	
	/** Variable for storing number of iterations */
	private static int iterations;
	/** Variable for storing size of tilemap */
	private static int size;
	/** Variable for storing probability for generating dinosaurs */
	private static int dinoProb;
	/** Variable for storing probability for generating elements (plants, water sources, tiles) */
	private static int elementsProb;
	/** Variable for storing grid size of every tile in tilemap*/
	private static int gridSize;
	/** Variable for storing delay for iteration loop */
	private static int delay;
	
	/** Basic random variable */
	private static Random r = new SecureRandom();
	
	/** Variable of Gui class, that stores entire gui */
	private static Gui gui = new Gui();
	
	/** Buttons that copies functionality from Gui */
	
	private static JButton set;
	private static JButton start;
	private static JButton stop;
	private static JButton thread;
	private static JButton csv;
	
	/** Application icon */
	private static ImageIcon img = new ImageIcon("src/main/resources/jurassicpark.png");
	
	/** Values that stores on which tile simulation stopped */
	public static int jStopped = 0;
	public static int kStopped = 0;
	
	/** Flag that interrups main loop */
	public static boolean flag = true;
	
	/** Creating the frame */
    private static JFrame frame = new JFrame("Jurassic Park");
    
    /** Creating new tile map */
    private static TileMap tilemap = new TileMap(0,0,0,0);
    
    // MAIN FUNCTION AND OPERATIONS
	public static void main(String[] args) {
		//SETTING MAIN FRAME
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(img.getImage());
		
		//SETTING ACTIVITY ON BUTTONS
		set = gui.setReturn();
		set.addActionListener(new Action());
        set.setActionCommand("set");
        
        start = gui.startReturn();
        start.addActionListener(new Action());
        start.setActionCommand("start");
        
        stop = gui.stopReturn();
        stop.addActionListener(new Action());
        stop.setActionCommand("stop");
        
        thread = gui.applyReturn();
        thread.addActionListener(new Action());
        thread.setActionCommand("apply");
        
        csv = gui.csvReturn();
        csv.addActionListener(new Action());
        csv.setActionCommand("csv");
        
        //STOP DISABLED AT START (APPEARS WHEN YOU CLICK START)
        stop.setEnabled(false);
        
        //POSITION GUI ON WEST SIDE OF FRAME
		frame.getContentPane().add(BorderLayout.WEST, gui.Gui());
		
		//VISIBILITY AND PACKING CONTENT TO FRAME
		frame.setVisible(true);
		frame.pack();
	}
	
	/** Method that implements entire logic */
	public static void basicLogic(){
		//VARIABLES FOR RANDOM GENERATION
		int attackRandom;
		int moveRandom;
		
		int speed;
		int iter = iterations;
		
		Tile tileHandler;
		Object handler;
		
		Object[][] tilesAround;;
		Tile[][] tilesAroundToMove;
		
		Dinosaur dino;
		
		//BASIC LOOP
		for(int i=0; i<iter; i++) {
			System.out.println("LOOP " + (i+1));
				for(int j=0; j<size; j++) {
   					for(int k=0; k<size; k++) {
						if(jStopped != 0 || kStopped != 0) {
							// LAST J AND K WHILE STOPPED
							j = jStopped;
							k = kStopped;
							jStopped = 0;
							kStopped = 0;
						}
						// FLAGS FOR JUMPING FROM LOOP
						if(flag) {
							try {
								if(flag) {
									// HOW LONG YOU NEED TO WAIT FOR NEXT TILE TO OCCUR
									Thread.sleep(delay);
									// CREATE DINO
									if(tiles[j][k] instanceof Dinosaur) {
										// COPY DINO TO LOCAL VARIABLE
										dino = (Dinosaur)tiles[j][k];	
										
										// CHECK IF DINO ALIVE AND DINO HASN'T MOVED YET
										if(dino.ifActiveMove() && dino.isAlive()){
											dino.switchDinoMove(); 
											speed = dino.getSpeed();
											tilesAroundToMove = dino.tilesAroundToMove(j, k, tiles, size);
											for(int n=0; n<speed*2+1; n++) {
												for(int m=0; m<speed*2+1; m++) {
													if(tilesAroundToMove[n][m] !=null) {
														if(tilesAroundToMove[n][m] instanceof Tile) {
															tileHandler = (Tile) tilesAroundToMove[n][m];
															tilesInRange.add(tileHandler);
														}
													}	
												}
											}										
											
											System.out.println( dino + " cur pos: " + dino.getCords());
											
											 if(tilesInRange.size() != 0){
													moveRandom = r.nextInt(tilesInRange.size());
													Tile tile = tilesInRange.get(moveRandom);
													
													// MOVE (SWAPPING TILES POSITION WITH DINO POSITION, AND VICE VERSA
													Point tilePos = tile.getCords();
													Point handlerPos;
													
													tiles[j][k] = tile;
													tiles[(int) tilePos.getY()][(int) tilePos.getX()] = dino;
													tilemap.repaint();
													
													handlerPos = dino.getCords();
													dino.setCords(tilePos);
													tile.setCords(handlerPos);
												}
												else {
													System.out.println("STUCK!");
												}
											 
											 System.out.println( dino + " next pos: " + dino.getCords());
											 
												Point newDinoPos = dino.getCords();
												tilesAround = dino.tilesAround((int)newDinoPos.getY(),(int)newDinoPos.getX(),  tiles, size);
											
											// GET 4 ARRAYS (DINOS, PLANTS, WATER SOURCES AND TILES)
											 for(int n=0; n<3; n++) {
													for(int m=0; m<3; m++) {
														if(tilesAround[n][m] instanceof Dinosaur) {
															handler = (Dinosaur) tilesAround[n][m];
															dinosInRange.add((Dinosaur)handler);
														}
														else if(tilesAround[n][m] instanceof Plant) {
															handler = (Plant) tilesAround[n][m];
															plantsInRange.add((Plant)handler);
														}
														else if(tilesAround[n][m] instanceof WaterSource) {
															handler = (WaterSource) tilesAround[n][m];
															waterInRange.add((WaterSource)handler);
														}
														else if(tilesAround[n][m] instanceof Tile) {
															handler = (Tile)tilesAround[n][m];
															tilesInRange.add((Tile)handler);
														}
													}
											 }
											 
											 // BASIC LOGIC 
											 // TODO: BETTER LOGIC :)
											if((!(dinosInRange.isEmpty()) && dino instanceof Carnivore) || ((!(dinosInRange.isEmpty()) && dino instanceof Omnivore) && (dino.getHealth() > (dino.getMaxHealth()/3)))){
													attackRandom = r.nextInt(dinosInRange.size());
													Dinosaur target = (Dinosaur) dinosInRange.get(attackRandom);
													dino.attack(target);
													System.out.println("ATTACK");
											}
											else if((plantsInRange.size() != 0) && (dino.getThirst() > dino.getHunger()) && (dino instanceof Omnivore || dino instanceof Herbivore)) {
												if(!(plantsInRange.isEmpty())) {
													int rTemp = r.nextInt(plantsInRange.size());
													dino.eatPlant(plantsInRange.get(rTemp));
													System.out.println("EAT");
												}
												
											}
											else if((waterInRange.size() != 0) && (dino.getThirst() != dino.getMaxThirst()) && (dino.getThirst() < (dino.getMaxThirst()/3))) {
												if(!waterInRange.isEmpty()) {
													int rTemp = r.nextInt(waterInRange.size());
													dino.drink(waterInRange.get(rTemp));
													System.out.println("DRINK");
												}
											}
											else if((plantsInRange.size() != 0) && (dino instanceof Omnivore || dino instanceof Herbivore)) {
												if(!(plantsInRange.isEmpty())) {
													int rTemp = r.nextInt(plantsInRange.size());
													dino.eatPlant(plantsInRange.get(rTemp));
													System.out.println("EAT");
												}
											}
											else if(waterInRange.size() !=0) {
												if(!waterInRange.isEmpty()) {
													int rTemp = r.nextInt(waterInRange.size());
													dino.drink(waterInRange.get(rTemp));
													System.out.println("DRINK");
												}
											}
											
											
											dino.getStats();
											
											// CLEARS ALL ARRAY LISTS
											dinosInRange.clear();
											plantsInRange.clear();
											waterInRange.clear();
											tilesInRange.clear();
										}
										
									}
									else if(tiles[j][k] instanceof Element) {
										// PRINTING OTHER OBJECTS THAN DINOSAURS
										Element element = (Element) tiles[j][k];
										if(element instanceof WaterSource) {
											System.out.println(element + " " + element.getCords() + " " + (((WaterSource) element).getCapacity()));
										}
										else if(element instanceof Plant) {
											System.out.println(element + " " + element.getCords() + " " + (((Plant) element).getCapacity()));
										}
										else {
											System.out.println(element + " " + element.getCords());
										}
									}
								}
							} 
							catch (InterruptedException e) {
								e.printStackTrace();
							}
						}	
						else {
							jStopped = j;
							kStopped = k;
							flag = false;
							break;
						}
					}
   					
					if(!flag)
						break;
				}	
				//END OF LOOP
				allDinosEndLoop();
				if(!flag)
					break;
			}	
		
		stop.setEnabled(false);
	}
	
	/** Function that changes variables of every dino at the end of loop */
	public static void allDinosEndLoop(){
		for(Dinosaur dino: allDinos) {
			dino.getStats();
			if(dino.isAlive()) {
				dino.switchDinoMove(); 
				dino.decreaseHunger(20);
				dino.decreaseThirst(20);
				if(dino.getHunger() == 0) {
					dino.decreaseHealth(40);
				}
				else if(dino.getThirst() == 0) {
					dino.decreaseHealth(50);
				}
			}
		}
	}
	
	/** Action listeners that implements functionality of buttons */
	static class Action implements ActionListener{
		@Override
	    public void actionPerformed(ActionEvent ae) {
	        String action = ae.getActionCommand();
	        if (action.equals("set")) {
	        	Dinosaur.restartCount();
	            tilemap.jsonHandler = new JSONToSpecie();
	            tilemap.allDinos = new ArrayList<>();
	            tilemap.arrayCarnivore = new ArrayList<>();
	            tilemap.arrayOmnivore = new ArrayList<>();
	            tilemap.arrayHerbivore = new ArrayList<>();
	        	
	        	start.setEnabled(true);
	        	iterations = gui.getIterations();
	        	size = gui.getMapSize();
	        	dinoProb = gui.getSpecies();
	        	elementsProb = gui.getElements();
	        	gridSize = gui.getGrid();
	        	allDinos = tilemap.allDinos;
	        	delay = gui.getDelay();
	        	
	        	jStopped = 0;
	        	kStopped = 0;
	        	
	        	tilemap.setVisible(false);
	        	tilemap = new TileMap(size,dinoProb,elementsProb,gridSize);
	        	tilemap.setVisible(true);
	        	tiles = tilemap.tiles;
	        	tilemap.setVisible(true);
	        	// SETTING TILEMAP ON EAST SIDE OF FRAME
	    		frame.getContentPane().add(BorderLayout.EAST, tilemap);
	    		frame.pack();
	    		tilemap.repaint();
	        }
	        else if(action.equals("start")) {
	        	new Thread(() -> {
	        		flag = true;
	        		stop.setEnabled(true);
        			start.setEnabled(false); 
	        		set.setEnabled(false);
	        	    basicLogic();
	        	    start.setEnabled(true);
	        	    set.setEnabled(true);
	        	    r = new SecureRandom();
	        	}).start();
	        }
	        else if(action.equals("stop")) {
	        	flag = false;
	        	start.setEnabled(true);
	        	set.setEnabled(true);
	        	stop.setEnabled(false);
	        }
	        else if(action.equals("apply")) {
	        	delay = gui.getDelay();
	        }
	        else if(action.equals("csv")) {
	        	csvWriter csv = new csvWriter(allDinos);
	        }
	    }
	}
}
