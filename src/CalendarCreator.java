import java.util.Scanner;
import java.io.*;



public class CalendarCreator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			File videoList=new File(args[0]);
			Scanner input=new Scanner(videoList);
			
			String title;
			String length;
			int duration;
			
			VideoCollection workoutVideos=new VideoCollection();
			
			while(input.hasNext()){
				title=input.nextLine();
				if(input.hasNext()){
					length=input.nextLine();
					duration=Integer.parseInt(length);
					//workoutVideos.addVideo(title, duration);
					if(title.length()>5){
						if(title.substring(0,5).equalsIgnoreCase("(NEW)")){
							title=title.substring(5,title.length());
							workoutVideos.addNewVideo(title, duration);
						}
						else{
							workoutVideos.addVideo(title, duration);
						}
					}
					else{
						workoutVideos.addVideo(title, duration);
					}
					
					if(input.hasNext()){
						input.nextLine();
					}
				}
			}
			
			int startYear=Integer.parseInt(args[4]);
			int endYear=Integer.parseInt(args[7]);
			int startMonth=Integer.parseInt(args[2]);
			int endMonth=Integer.parseInt(args[5]);
			int startDay=Integer.parseInt(args[3]);
			int endDay=Integer.parseInt(args[6]);
			
			DateSetter workouts=new DateSetter(startYear, endYear, startMonth, endMonth, startDay, endDay);
			
			File calendar=new File(args[1]);
			PrintWriter output=new PrintWriter(calendar);//need output.print stuff and then output.close
			
			workoutVideos.separateByType();//organizes the videos into short or regular
			workoutVideos.assignVideosToLists();//assigns the videos from the master lists to the dynamic ones
			
			while(workouts.isNotDone()){
				output.println(workouts);
				//double i=Math.random();
				/*if(i>0.25){
					workoutVideos.scheduleNewVideo();
				}*/
				//else{
					//if(workouts.getDayOfWeek().equals("Monday")){
					//	workoutVideos.scheduleShortVideo();
					//}
					//else{
						workoutVideos.scheduleVideo();
					//}
				//}
				output.println(workoutVideos);
				workouts.advanceDate();
			}
			output.close();
			
		}
		catch(FileNotFoundException e){
			System.out.println("Please input the correct file name.");
		}
	}

}
