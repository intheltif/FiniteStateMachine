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
     * iterations to execute, and the data.
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

	@Override
	public Data call(){
    	int currentState = data.getResult();
    	//Random rand = new Random();
    	double exitProbability;
    	double currentProbability;
    	double[][] stateMach = data.getMatrix();

    	int size = stateMach.length - 1;
    	//int j = 0; TODO not needed out here

    	for(int i=0; i <numIterations; i++) {
    		exitProbability = ThreadLocalRandom.current().nextDouble();
    		currentProbability = stateMach[currentState][0];
    		int j = 0;

    		while(j < size && currentProbability < exitProbability) {
    			j++;
    			currentProbability += stateMach[currentState][j];
			}
    		currentState = j;

		}
    	data.setResult(currentState);
    	return data;
	}// end run
	
} // end Markov class
