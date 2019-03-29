import java.util.Random;

/**
 * Contains the result of the execution and representation of the finite
 * state machine.
 *
 * @author Evert Ball
 * @author Tyler Baylson
 *
 * @version 04/10/2019
 */
public class Data {
	
	/** Unique ID**/
	private int id;
	
	/** The result of the execution**/
	private double result;

	/** Representation of the FSM. The column represents the state we are currently on and
	 *  the row represents our probability of changing states. So for figure 1: the first
	 *  column represents being in state zero. The first row of the first column (0) 
	 *  represents a 0% chance of moving from state zero to state zero.**/
	private double[][] matrix;

    /**
     * The constructor.
     *
     * @param something
     */
    public Data(double res, double[][]mat) {
    	this.result = res;
    	this.matrix = mat;
    } // end constructor
    
    private void iterate() {
    	Random chance = new Random();
    }

} // end Data class
