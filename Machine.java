
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
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
 *      	I believe you are correct -Ty
 *
 * @author Evert Ball
 * @author Tyler Baylson
 *
 * @version 04/10/2019
 */

public class Machine /*implements Future*/ {
        
    /**
     * Prompts the user for the number of finite state machines.
     *
     * @return The number of finite state machines.
     */
    public static void main(String[] args) {

    	/** Scanner for reading keyboard input**/
        Scanner input = new Scanner(System.in);

        /** An array of finite state machines**/
        double[][] stateMachine = null;

        System.out.print("How many Finite State Machines to create? > ");
        int numStateMachine = input.nextInt();

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
            System.out.println("STILL HERE");
        }
        
        Markov[] markovPool = new Markov[numStateMachine];
        for(int i = 0; i < numStateMachine; i++) {
        	markovPool[i] = new Markov(/*ThreadLocalRandom.current().nextInt(5)*/i%5, numIters, new Data(stateMachine));
        }

        /**======This variable is never initialized!!======**/
        Markov[] markovPool = null;
        
        /**======This needs to be in a loop. Don't need a variable, just give it right to CompletionS=======**/
        Markov sorin = new Markov(ThreadLocalRandom.current().nextInt(), numIters, dataPool[0]);

        /** Fixed to numThreads, this is the pool of executing threads**/
        ExecutorService threadPool = Executors.newFixedThreadPool(numThreads);
        
        /** Completion Service for retreiving the processed data**/
        CompletionService<Data> completionS = new ExecutorCompletionService<Data>(threadPool);
        ArrayList<Data> returnedData = new ArrayList<Data>();
        
        /** Submits all of the Callable objects to the complete service and collects them**/
        for(Callable<Data> s : markovPool) {
        	completionS.submit(s);// Make Markov objects here?
        }
		int n = markovPool.length;
		for (int i = 0; i < n; ++i) {
			try {
				returnedData.add(completionS.take().get());
                //System.out.println("Result(" + i + "): " + returnedData.remove(0).getResult());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}

		}
		int size = returnedData.size();
		int[] tally = new int[stateMachine[0].length];
		for(int i = 0; i < size; i++){
		    int temp = returnedData.remove(0).getResult();
		    for(int j = 0; j < stateMachine[0].length; j++){
		        if(j == temp){
		            tally[j]++;
                }
            }
        }

		//System.out.println("Result: " + returnedData.remove(0).getResult());
        HashMap<Integer, Integer> count = new HashMap<Integer, Integer>();
		for(int i = 0; i < stateMachine[0].length; i++) {
		    count.put(i, 0);
        }
		for(Data d : returnedData){
            int temp = d.getResult();
            //System.out.println("Temp: " + temp);
            count.put(temp, (count.get(temp)+1));
        }
		for(int i = 0; i < tally.length; i++) {
		    System.out.println("Result of state " + i + ": " + (tally[i]));
        }

        input.close();
    } // end main
    
	/**Modified version of Java 8's example of ExecutorCompletionService**/
	void solve(ExecutorService threadPool, ArrayList<Callable<Data>> markovPool) throws InterruptedException, ExecutionException {
		CompletionService<Data> completionS = new ExecutorCompletionService<Data>(threadPool);
		for (Callable<Data> s : markovPool)
			completionS.submit(s);
		int n = markovPool.size();
		for (int i = 0; i < n; ++i) {
			Data r = completionS.take().get();
			if (r != null);
				//use(r);
		}// end for
	}// end solve
    
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
    }// end getStateMachine
} // end Machine class
