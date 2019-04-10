import java.util.Random;
import java.util.concurrent.Callable;
import java.lang.Thread;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The threaded class. It executes the finite state machine.
 *
 * @author Evert Ball
 * @author Tyler Baylson
 *
 * @version 04/10/2019
 */
public class Markov implements Callable<Data>{

    /** The number of iterations the FSM should execute. */
    private int numIterations;

    /** The data. */
    private Data data;

    /**
     * Constructs a Markov object. It accepts a start state, the number of
     * iterations to execute, and the information to create a Data object.
     *
     * @param startState The starting state for the finite state machine.
     * @param numIters The number of iterations to execute.
     * @param mat The data from the data class.
	 * @param id The unique id of each Data class.
     *
     */
    public Markov(int id, double[][] mat, int startState, int numIters) {
        this.numIterations = numIters;
        this.data = new Data(id, mat, startState);
    } // end constructor

	/**
	 * Implements the call method that is required from Callable. It allows us to execute a thread
	 * and return a value. Using the call method allows us to pass messages back and forth.
	 * @return the Data object that represents our FSM.
	 */
	@Override
	public Data call(){

		//currentState initialized to what our original start state is.
    	int currentState = data.getResult();
    	double exitProbability;
    	//The current probability we are on in the FSM.
    	double currentProbability;
    	//local variable so that we do not have to call data.getMatrix() a lot.
    	double[][] stateMach = data.getMatrix();

    	int sizeOfFSM = stateMach.length - 1;

    	for(int i=0; i < numIterations; i++) {
			//Random probability that we check is the current one against.
			//Should always be less than this. If it isn't, we exit that iteration.
    		exitProbability = ThreadLocalRandom.current().nextDouble();
    		//set current probability to the current state we are at in the FSM.
    		currentProbability = stateMach[currentState][0];
    		int j = 0;

    		//Iterate over the FSM
    		while(j < sizeOfFSM && currentProbability < exitProbability) {
    			j++;
    			currentProbability += stateMach[currentState][j];
			}
    		currentState = j;

		}
    	//let data show where we ended up in the execution of our thread.
    	data.setResult(currentState);
    	//Return the whole data object so that it's methods can be accessed.
    	return data;
	}// end run
	
} // end Markov class
