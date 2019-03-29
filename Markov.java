import java.lang.Runnable;
import java.util.concurrent.Callable;
import java.lang.Thread;

/**
 * The threaded class. It executes the finite state machine.
 *
 * @author Evert Ball
 * @author Tyler Baylson
 *
 * @version 04/10/2019
 */
public class Markov implements Runnable { //TODO implement Callable?

    //TODO Initialize fields to zero, zero, and null respectively?
    /** The start state for the finite state machine. */
    private int startState;

    /** The number of iterations the FSM should execute. */
    private int numIterations;

    /** The data. */
    private Data data;

    /**
     * Constructs a Markov object. It accepts a start state, the number of
     * iterations to execute, and the data.
     *
     * @param startState The starting state for the finite state machine.
     * @param numIterations The number of iterations to execute.
     * @param data The data from the data class TODO finish this param
     *
     */
    public Markov(int startState, int numIterations, Data data) {

        this.startState = startState;
        this.numIterations = numIterations;
        this.data = data;

    } // end constructor

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}// end run

} // end Markov class
