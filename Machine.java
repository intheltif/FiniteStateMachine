import java.util.Scanner;
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

public class Machine /*implements Future*/ {
    
    //TODO Decide if constructor is needed or not.
    
    /**
     * Prompts the user for the number of finite state machines.
     *
     * @return The number of finite state machines.
     */
    public static void main(String[] args) {



        Scanner input = new Scanner(System.in);

        System.out.println("How many Finite State Machines to create? > ");
        int numFSM = input.nextInt();

        System.out.println("How many iterations for each machine? > ");
        int numIters = input.nextInt();

        System.out.println("How many threads? > ");
        int numThreads = input.nextInt();

        System.out.println("Please enter input filename > ");
        String inputFilename = input.next();

        input.close();

    } // end main method

        

} // end Machine class
