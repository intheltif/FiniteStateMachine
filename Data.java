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
	
	/** Unique ID of a thread **/
	private int id;
	
	/** The state that we ended in from the last iteration**/
	private int result;

	/** Representation of the FSM. The column represents the state we are currently on and
	 *  the row represents our probability of changing states. So for figure 1: the first
	 *  column represents being in state zero. The first row of the first column (0) 
	 *  represents a 0% chance of moving from state zero to state zero.**/
	private double[][] matrix;

    /**
     * The constructor.
     *
     * @param res the result of the last execution of the FSM.
	 * @param mat The matrix that represents the FSM
     */
    public Data(int id, double[][]mat, int startState) {
    	this.id = id;
    	this.result = startState;
    	this.matrix = mat;
    } // end constructor

	/**
	 * Returns the unique id for this thread.
	 * @return the id of this thread.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Allows the unique id to be set for this thread.
	 * @param id the unique id of this thread.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get's the result of the Finite State Machine.
	 * @return the result of the Finite State Machine execution.
	 */
	public int getResult() {
		return result;
	}

	/**
	 * Allows the result of the execution of the Finite State Machine to be set.
	 * @param result the result of the FSM.
	 */
	public void setResult(int result) {
		this.result = result;
	}

	/**
	 * Gets the matrix, which represents our FSM.
	 * @return the Finite State Machine.
	 */
	public double[][] getMatrix() {
		return matrix;
	}

	/**
	 * Allows the FSM to be set (Used when reading in the file).
	 * @param matrix the matrix to set
	 */
	public void setMatrix(double[][] matrix) {
		this.matrix = matrix;
	}
} // end Data class
