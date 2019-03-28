import java.util.concurrent.Future;

/**
 * This class starts and collects results from all threads. All input and
 * output is done here.
 * 
 * It will print the correct answer AFTER all threads have finished.
 *      TODO I believe this is done by the Future interface's get() method.
 *
 * @author Evert Ball
 * @author Tyler Baylson
 *
 * @version 04/10/2019
 */

public class Machine implements Future {

    public Machine() {

        //TODO finish constructor

    } //end constructor 
    
    /**
     * Prompts the user for the number of finite state machines.
     *
     * @return The number of finite state machines.
     */
    public int promptUser() {

        System.out.println("Please enter the total number of" +
                            " finite state machines > ");

    } // end promptUser() method

        

} // end Machine class
