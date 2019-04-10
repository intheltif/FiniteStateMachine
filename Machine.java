
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.*;
import java.util.concurrent.*;

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

        //This let's us check later if the starting state was given as a command line argument.
        int startingState = -1;

        if(args.length > 0) {
            //If a command line argument was given, set it as our start state.
            startingState = Integer.parseInt(args[0]);
        }

        /** An array of finite state machines**/
        double[][] stateMachine = null;

        System.out.print("How many Finite State Machines to create? > ");
        int numStateMachine = input.nextInt();

        System.out.print("How many iterations for each machine? > ");
        int numIters = input.nextInt();

        System.out.print("How many threads? > ");
        int numThreads = input.nextInt();

        System.out.print("Please enter input filename > ");
        boolean fileFound = false;
        while(!fileFound) {
            try {
            	stateMachine = getStateMachine(input.next());
            	fileFound = true;
            }
            catch (FileNotFoundException fnfe) {
                System.out.println("Please give a valid filename");
            }
            catch(NoSuchElementException nsee){
                System.out.println("Please give a filename with a valid state machine");
            }
        }
        //checking time program takes. We start counting after we get input.
        long startTime = System.currentTimeMillis();
        //Holds the results of the FSM as they become available.
        double[] results = new double[stateMachine.length];

        //Creates a pool and a Completetion Service so that we can get results as they finish.
        final ExecutorService pool = Executors.newFixedThreadPool(numThreads);
        final CompletionService<Data> completionService = new ExecutorCompletionService<>(pool);


        //Creates all the threads and submits them to the completion service.
        for(int i = 0; i < numStateMachine; i++) {
            if(startingState == -1) {
                // Creates a random start state when not given as a command line argument.
                Random aRandomNum = new Random();
                startingState = aRandomNum.nextInt(stateMachine.length);
            }
            completionService.submit(new Markov(i, stateMachine, startingState, numIters));
        }
        //Shuts the pool down once all threads have been submitted to the completion service.
        pool.shutdown();

        /*
         * For all of the threads, gets the data as soon as it is available and adds it to our
         * result array. Once the result array has the data correctly, we can parse it to get our
         * precentages.
         */
        try{

            int count = 0;

            while(count < numStateMachine) {
                Future<Data> future = completionService.take();
                Data thisData = future.get();
                /*This print statement proves that our threads are concurrent and non-sequential.
                   During the print you see that lower thread ids print after higher thread ids.*/
                //System.out.println("thread_id: " + thisData.getId());
                int end = thisData.getResult();
                results[end] += 1;
                count++;
            }
        } catch (ExecutionException ee) {
            System.out.println("Error getting the result.");
        } catch (InterruptedException ie) {
            System.out.println("Error getting the result");
        }

        System.out.println("\nSteady state results:");
        for(int i = 0; i < results.length; i++) {
            double eachResult = (results[i]/((double)numStateMachine)/* *100 */);
            /**NOTE: Dr. K's output has a percent but we are not returning a precent.
             * Uncomment the "*100" at the end of the eachResult computation to get a precentage.
             */
            System.out.println("State " + i + ": " + eachResult + "%");
        }
        //the ending time of the program execution.
        long endTime = System.currentTimeMillis();
        //Used to show how long the execution of the threads take.
        System.out.println("\nTime taken: " + ((endTime - startTime)/1000.0) + " sec.");
    } // end main

    /**
     * getStateMachine- Helper method that recieves a filename string, read the file, and converts
     * the contents into a state machine if the contents are in the appropriate format.
     * @param fileName The file to be opened
     * @return stateMach  The finite state machine
     * @throws FileNotFoundException- When filename received does not match a file in the current directory
     */
    private static double[][] getStateMachine(String fileName) throws FileNotFoundException,
            NoSuchElementException{

        Scanner inputFile = new Scanner(new File(fileName));
        int size = inputFile.nextInt();
        double[][] stateMach = new double[size][size];
        double[] columnSum = new double[size];

        double currInput;
        for(int i=0; i <size; i++) {
            for(int j = 0; j<size; j++) {
                currInput = inputFile.nextDouble();
                if(currInput > 1){
                    throw new InputMismatchException();
                }else {
                    stateMach[j][i] = currInput;
                    columnSum[j] += currInput;
                }
            }
        }
        for(double i : columnSum)
            if(i != 1.0){throw new InputMismatchException();}

        inputFile.close();
        return stateMach;
    }// end getStateMachine
} // end Machine class
