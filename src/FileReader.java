import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * reads an ASCII file line by line. works exact the same as a {@link Scanner}.
 * @author CJC
 *
 */
class FileReader {
	private File input;
	private Scanner in;
	
	/**
	 * Constructor: initialize a new file reader object of the file on the path
	 * @param path the path of the input file
	 * @throws FileNotFoundException if the path is not valid
	 */
	public FileReader(String path) throws FileNotFoundException{

		input = new File(path);
		in = new Scanner(input);

	}
	
	/**
	 * Checks if the input file has more lines
	 * @return true if the the input file has next line
	 */
	public boolean hasNextLine() {
		return in.hasNextLine();
	}

	/**
	 * Reads and returns the next line of the input file.
	 * @return the next line in the input file
	 */
	public String nextLine() {
		return in.nextLine();
	}
}

