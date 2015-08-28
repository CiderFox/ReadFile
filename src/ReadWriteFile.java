import java.io.*;
import javax.swing.*;

/** public class ReadWrite {
 * This class takes in a file from the user's choice, using JFileChooser.
 * The class checks if there is anything to read within the chosen file and
 * writes it to the output file. The output file checks if a file of the same
 * output file names exists, if it does it deletes the file and creates a new one.
 * The output file has the same name as the selected input file; only with "_out"
 * added at the end of the file name.
 *
 * @author Cassidy Halvorson
 *
 */
public class ReadWriteFile {
    public static void main( String[] args ) throws FileNotFoundException {

        //choose a file in the current directory with JFileChooser.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch ( Exception e ) {
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a File");
        fileChooser.setCurrentDirectory(new File("."));

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

            // Get the selected file.
            File inputFile = fileChooser.getSelectedFile();

            // Get output file name.
            String inputFileName = inputFile.toString();

            //delete the .txt part of the file name to receive just the name of the original input file name.
            String baseFileName = inputFileName.substring( 0, inputFileName.length()-4 );

            //add the _out requirement to the output file name.
            String outputFileName = baseFileName.concat( "_out.txt" );

            //create the output file.
            File outputFile = new File( outputFileName );

            //Check if the file already exists, delete the file if it does exist to replace with new file.
            if ( outputFile.exists() ) {
                outputFile.delete();
            }

            // Create output file, using the setOut for what to print in the output file.
            System.setOut( new PrintStream( new FileOutputStream(outputFileName) ) );

            // read input file and write its contents (if it is not null) to the output file.
            try {
                // FileReader reads text files.
                FileReader fileReader = new FileReader(inputFileName);

                // Wrap FileReader in BufferedReader.
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                // create variable for the line to be read, and set it initially to null.
                String line = null;

                // Read the file, if the line is not null then copy it.
                while((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }

                // Close the reader.
                bufferedReader.close();
            }
            catch (IOException error) {
                System.err.format("IOException: %s%n", error);
            }
        }
        else {
            System.out.println("No file selected");
            System.exit( 0 );
        }
    }
}



