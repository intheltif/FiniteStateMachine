import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
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
        Scanner inputFile = null;
        //String[][] stateMachineString = new String[100][100];
        double[][] FSM = null;
        int size = 0;

        System.out.print("How many Finite State Machines to create? > ");
        //int numFSM = input.nextInt();

        System.out.print("How many iterations for each machine? > ");
        //int numIters = input.nextInt();

        System.out.print("How many threads? > ");
        //int numThreads = input.nextInt();

        System.out.print("Please enter input filename > ");
        try {
            inputFile = new Scanner(new File(input.next()));
            size = inputFile.nextInt();
            FSM = new double[size][size];
            int row = 0;
            int column = 0;
            while (inputFile.hasNextDouble()) {
            	FSM[(row++)%size][(column++)%size] = inputFile.nextDouble();
            }
            inputFile.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("Please give a valid filename");
        }
        finally{
            if(inputFile != null) {
            	inputFile.close();
            }
        }

		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				System.out.print(FSM[i][j] + " ");
				if(j == size - 1) {System.out.println("");}
			}
		}
        input.close();
    } // end main method

        

} // end Machine class
