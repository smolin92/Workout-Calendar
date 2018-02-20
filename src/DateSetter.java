import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateSetter {
	private Calendar workouts=GregorianCalendar.getInstance();
	private Calendar finishedCalendar=GregorianCalendar.getInstance();
	
	public DateSetter(int initialYear, int finishYear, int initialMonth, int finishMonth, int initialDay, int finishDay){
		workouts.clear();
		workouts.set(initialYear, initialMonth, initialDay);
		finishedCalendar.clear();
		finishedCalendar.set(finishYear, finishMonth, finishDay);
		finishedCalendar.roll(GregorianCalendar.DAY_OF_YEAR, true);
	}//sets the start date and end date for the calendar
	
	public void advanceDate(){
		workouts.roll(GregorianCalendar.DAY_OF_YEAR, 1);
	}//moves date forward by one
	
	public boolean isNotDone(){
		boolean notDone;
		notDone=finishedCalendar.after(workouts);
		return notDone;
	}
	
	public String getDayOfWeek(){
		return workouts.getDisplayName(Calendar.DAY_OF_WEEK, 2, Locale.US);
	}
	
	public String toString(){
		String month;
		int date;
		String day;
		int year;
		String description;
		
		month=workouts.getDisplayName(Calendar.MONTH, 2, Locale.ENGLISH);
		day=workouts.getDisplayName(Calendar.DAY_OF_WEEK, 2, Locale.ENGLISH);
		date=workouts.get(Calendar.DATE);
		year=workouts.get(Calendar.YEAR);
		
		description=day + " (" + month + " " + date + ", " + year + ")";
		return description;
	}//returns date, month, day, year
	
}
