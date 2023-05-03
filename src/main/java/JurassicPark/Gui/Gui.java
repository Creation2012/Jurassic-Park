package JurassicPark.Gui;

import javax.swing.*;
import java.awt.*;

/**
 * Class gui that extends JPanel from javax.swing, stores all buttons, sliders, and textfields.
 * @author Bartosz Ostrowski
 *
 */

public class Gui extends JPanel{
	private JLabel labelIterations = new JLabel("Iterations:");
	//TODO: MAYBE BETTER IDEA IS TO IMPLEMENT SETTING ITERATIONS AS FIELD INSTEAD OF SLIDER
    //private JTextField tf_i = new JTextField(5); 
    /** JSlider for changing number of iterations 
     * @see JSlider*/
    private JSlider sliderIterations = new JSlider(0,100,3);
    
    private JLabel labelSize = new JLabel("Map size:");
    /** JSlider for changing map size 
     * @see JSlider*/
    private JSlider sliderSize = new JSlider(0,100,4);
    
    private JLabel labelElements = new JLabel("Elements probability:");
    /** JSlider for changing probability for elements to appear 
     * @see JSlider*/
    private JSlider sliderElements = new JSlider(0,100,30);
    
    private JLabel labelSpecies = new JLabel("Dinos probability:");
    /** JSlider for changing probability for species to appear 
     * @see JSlider*/
    private JSlider sliderSpecies = new JSlider(0,100,20);

    private JLabel labelGrid = new JLabel("Grid Size:");
    /** JSlider for changing grid size of tile 
     * @see JSlider*/
    private JSlider sliderGrid = new JSlider(0,100,50);
    
    private JLabel labelThread = new JLabel("refresh (ms):");
    /** JTextField for storing refresh rate of iterations 
     * @see JTextField*/
    private JTextField textThread = new JTextField("1000",6);
    
	/** JButton for starting simulation 
	 * @see JButton*/
	private JButton start = new JButton("Start");
    
    /** JButton for setting all options 
     * @see sliderIterations
     * @see sliderSize
     * @see sliderElements
     * @see sliderSpecies
     * @see sliderGrid
     * @see textThread*/
    private JButton set = new JButton("Set");
    
    /** JButton that stops simulation */
    private JButton stop = new JButton("Stop");
    
    /** JButton that apply simulation delay */
    private JButton apply = new JButton("Apply");
    
    /** JButton that extracts simulation data to CSV */
    private JButton csv = new JButton("Get CSV results");
    
    /** Constructor for gui that creates entire JPanel with all elements */
	public JPanel Gui() {   
        JPanel panel = new JPanel(new GridBagLayout());
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 10, 10, 10);
        
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        panel.add(start, constraints);
        start.setEnabled(false);
        
        constraints.gridx = 1;
        panel.add(stop, constraints);  
        
        constraints.anchor = GridBagConstraints.WEST;
        
        constraints.gridx = 0;
        constraints.gridy = 1;     
        constraints.gridwidth = 1;
        panel.add(labelIterations, constraints);
        
        constraints.gridx = 1;
        sliderIterations.setPreferredSize(new Dimension(180, 50));
        sliderIterations.setMajorTickSpacing(20);
        sliderIterations.setMinorTickSpacing(5);
        sliderIterations.setPaintTicks(true);
        sliderIterations.setPaintLabels(true);
        panel.add(sliderIterations, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 2;     
        panel.add(labelSize, constraints);
        
        constraints.gridx = 1;
        sliderSize.setPreferredSize(new Dimension(180, 50));
        sliderSize.setMajorTickSpacing(20);
        sliderSize.setMinorTickSpacing(5);
        sliderSize.setPaintTicks(true);
        sliderSize.setPaintLabels(true);
        panel.add(sliderSize, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 3;     
        constraints.gridwidth = 1;
        panel.add(labelSpecies, constraints);
        
        constraints.gridx = 1;
        sliderSpecies.setPreferredSize(new Dimension(180, 50));
        sliderSpecies.setMajorTickSpacing(20);
        sliderSpecies.setMinorTickSpacing(5);
        sliderSpecies.setPaintTicks(true);
        sliderSpecies.setPaintLabels(true);
        panel.add(sliderSpecies, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 4;     
        constraints.gridwidth = 1;
        panel.add(labelElements, constraints);
        
        constraints.gridx = 1;
        sliderElements.setPreferredSize(new Dimension(180, 50));
        sliderElements.setMajorTickSpacing(20);
        sliderElements.setMinorTickSpacing(5);
        sliderElements.setPaintTicks(true);
        sliderElements.setPaintLabels(true);
        panel.add(sliderElements, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 5;     
        constraints.gridwidth = 1;
        panel.add(labelGrid, constraints);
        
        constraints.gridx = 1;
        sliderGrid.setPreferredSize(new Dimension(180, 50));
        sliderGrid.setMajorTickSpacing(20);
        sliderGrid.setMinorTickSpacing(5);
        sliderGrid.setPaintTicks(true);
        sliderGrid.setPaintLabels(true);
        panel.add(sliderGrid, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        
        constraints.anchor = GridBagConstraints.CENTER;
        
        panel.add(set, constraints);  
        
        
        constraints.insets = new Insets(1,1,1,1);
        constraints.gridy = 7;

        constraints.anchor = GridBagConstraints.EAST;
        panel.add(labelThread, constraints);
        
        constraints.gridy = 8;
        
        constraints.anchor = GridBagConstraints.EAST;
        textThread.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(textThread, constraints);
        
        constraints.gridy = 9;
        panel.add(apply, constraints);
        
        constraints.gridx = 0;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(csv,constraints);

		return panel;
	}
	
	/** return iterations from slider 
	 * @see sliderIterations*/
	public int getIterations() {
		return sliderIterations.getValue();
	}
	
	/** return size of map from slider 
	 * @see sliderSize*/
	public int getMapSize() {
		return sliderSize.getValue();
	}
	
	/** return iterations from slider 
	 * @see sliderIterations*/
	public int getSpecies() {
		return sliderSpecies.getValue();
	}
	
	/** return probability of elements from slider 
	 * @see sliderElements*/
	public int getElements() {
		return sliderElements.getValue();
	}
	
	/** return grid size of tile from slider 
	 * @see sliderGrid*/
	public int getGrid() {
		return sliderGrid.getValue();
	}
	
	/** return delay of simulation (if non-integer value returned, set to 1000ms) */
	public int getDelay() {
		try {
			Integer.parseInt(textThread.getText());
			return Integer.parseInt(textThread.getText());
		} catch(final NumberFormatException e) {
			return 1000;
		}
	}
	
	/** return JButton start*/
	public JButton startReturn() {
		return start;
	}
	
	/** return JButton stop*/
	public JButton stopReturn() {
		return stop;
	}
	
	/** return JButton set*/
	public JButton setReturn() {
		return set;
	}
	
	/** return JButton apply*/
	public JButton applyReturn() {
		return apply;
	}
	
	/** return JButton csv*/
	public JButton csvReturn() {
		return csv;
	}
}
