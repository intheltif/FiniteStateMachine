
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

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

    	/** Scanner for reading keyboard input**/
        Scanner input = new Scanner(System.in);

        /** A finite state machine**/
        double[][] stateMachine = null;

        System.out.print("How many Finite State Machines to create? > ");
        int numFSM = input.nextInt();

        System.out.print("How many iterations for each machine? > ");
        int numIters = input.nextInt();

        System.out.print("How many threads? > ");
        int numThreads = input.nextInt();

        System.out.print("Please enter input filename > \n");
        boolean fileFound = false;
        while(!fileFound) {
            try {
            	stateMachine = getStateMachine(input.next());
            } 
            catch (FileNotFoundException e) {
                System.out.println("Please give a valid filename");
            }
        }
        Data[] pool = new Data[numThreads];
        for(int i = 0; i < numThreads; i++) {
            pool[i] = new Data(0, stateMachine);
        }

        Markov sorin = new Markov(ThreadLocalRandom.current().nextInt(), numIters, pool[0]);
        
        
        ExecutorService es = Executors.newCachedThreadPool();
        Future<Data> returnedData = es.submit(sorin);
        while(!returnedData.isDone());
        
        try {
			System.out.println(returnedData.get());
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		} catch (ExecutionException ee) {
			ee.printStackTrace();
		}

        input.close();
    } // end main method
    
    /**
     * getStateMachien- Helper method that recieves a filename string, read the file, and converts
     * the contents into a state machine if the contents are in the appropriate format.
     * @param fileName- The file to be opened
     * @return stateMach- The finite state machine
     * @throws FileNotFoundException- When filename recieved does not match a file in the current directory
     */
    private static double[][] getStateMachine(String fileName) throws FileNotFoundException{
        Scanner inputFile = new Scanner(new File(fileName));
        int size = inputFile.nextInt();
        double[][] stateMach = new double[size][size];
        int row = 0;
        int column = 0;
        while (inputFile.hasNextDouble()) {
        	stateMach[(row)%size][(column++)%size] = inputFile.nextDouble();
        	if(column%size == 0) {row++;}
        }
        inputFile.close();
        return stateMach;
    }

} // end Machine class
