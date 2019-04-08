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
	private double result;

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
    public Data(double[][]mat) {
    	this.result = 0;
    	this.matrix = mat;
    } // end constructor

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the result
	 */
	public double getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(double result) {
		this.result = result;
	}

	/**
	 * @return the matrix
	 */
	public double[][] getMatrix() {
		return matrix;
	}

	/**
	 * @param matrix the matrix to set
	 */
	public void setMatrix(double[][] matrix) {
		this.matrix = matrix;
	}
} // end Data class
