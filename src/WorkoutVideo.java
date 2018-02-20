
public class WorkoutVideo {
	private String title;
	private int time;
	
	public WorkoutVideo(String name, int duration){
		title=name;
		time=duration;
	}
	
	public String toString(){
		String description;
		description=title;
		return description;
	}
	
	public int getDuration(){
		return time;
	}
	
	public String getTitle(){
		return title;
	}
}
