
public class NewVideo extends WorkoutVideo{
	private boolean stillNew;
	private int timesPlayed;
	
	public NewVideo(String n, int t){
		super(n, t);
		stillNew=true;
		timesPlayed=0;
	}

	public boolean isStillNew(){
		return stillNew;
	}
	
	public int getTimesPlayed(){
		return timesPlayed;
	}
	
	public void used(){
		timesPlayed++;
		if(timesPlayed>0){
			stillNew=true;
		}
		else{
			stillNew=false;
		}
	}
}
