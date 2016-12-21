import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class calculates the start date and end date of each period.</p>
 * The first period ends on the current date form the system clock. </br>
 * Calling the mveBackward() will set the current period to its previous one.
 * @author CJC
 *
 */
public class DateCalculator {	
	
	private LocalDate localDate = LocalDate.now();
	private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private int period;
	
	/**
	 * Constructor
	 * @param period number of days one period contains
	 */
	public DateCalculator(int period) {
		this.period = period;
	}
	
	/**
	 * calculates the start date of this period
	 * @return the start date of this period in a format string "YYYY-MM-DD" 
	 */
	public String getStartDate() {
		return dateFormat.format(localDate.minusDays(period));
	}
	
	/**
	 * calculates the end date of this period
	 * @return the end date of this period in a format string "YYYY-MM-DD"
	 */
	public String getEndData() {
		return dateFormat.format(localDate);
	}
	
	/**
	 * sets this period to its previous one.
	 */
	public void moveBackward() {
		localDate = localDate.minusDays(period);
	}
}
