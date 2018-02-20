import java.util.ArrayList;

public class VideoCollection {
	private ArrayList<WorkoutVideo> videoList=new ArrayList<WorkoutVideo>();//keeps unscheduled regular videos
	private ArrayList<WorkoutVideo> regularVideoList=new ArrayList<WorkoutVideo>();//keeps all regular videos
	private ArrayList<WorkoutVideo> videoLibrary=new ArrayList<WorkoutVideo>();//must keep whole list
	private ArrayList<WorkoutVideo> workoutSchedule=new ArrayList<WorkoutVideo>();//keeps scheduled videos
	private ArrayList<WorkoutVideo> shortVideoList=new ArrayList<WorkoutVideo>();//keeps unscheduled short videos
	private ArrayList<WorkoutVideo> shortVideoLibrary=new ArrayList<WorkoutVideo>();//keeps all short videos
	private ArrayList<NewVideo> newVideoList=new ArrayList<NewVideo>();
	private ArrayList<NewVideo> newVideoLibrary=new ArrayList<NewVideo>();
	private String description;

	public void addVideo(String title, int duration){
		videoLibrary.add(new WorkoutVideo(title, duration));
	}

	public void addNewVideo(String title, int duration){
		newVideoLibrary.add(new NewVideo(title, duration));
	}

	public WorkoutVideo getVideo(int i){
		return videoList.get(i);
	}//returns the video in the location

	public WorkoutVideo getScheduledVideo(int i){
		return workoutSchedule.get(i);
	}//returns the video that has been scheduled

	public void scheduleVideo(){
		int j=(int) (Math.random()*(videoList.size()));
		workoutSchedule.add(videoList.get(j));
		description=videoList.get(j).getTitle();
		videoList.remove(j);//deletes video from list once it has been used
		if(videoList.isEmpty()){
			for(int i=0; i<regularVideoList.size(); i++){
				videoList.add(regularVideoList.get(i));
			}//refills the videoList with the videoLibrary that holds the list all the time
		}
	}

	public void scheduleShortVideo(){
		int k=(int) (Math.random()*(shortVideoList.size()));
		workoutSchedule.add(shortVideoList.get(k));
		description=shortVideoList.get(k).getTitle();
		//int videoLength=shortVideoList.get(k).getDuration();
		shortVideoList.remove(k);
		/*while(videoLength<60){
			int m=(int) (Math.random()*shortVideoList.size());
			while(k==m){
				m=(int) (Math.random()*shortVideoList.size());
			}
			workoutSchedule.add(shortVideoList.get(m));
			description+="\n" + shortVideoList.get(m);
			videoLength+=shortVideoList.get(m).getDuration();
			shortVideoList.remove(m);
		}*/

		if(shortVideoList.isEmpty()){
			for(int i=0; i<shortVideoLibrary.size(); i++){
				shortVideoList.add(shortVideoLibrary.get(i));
			}
		}
	}

	public void scheduleNewVideo(){
		if(newVideoList.isEmpty()){
			scheduleVideo();
		}
		else{
			int i=(int) (Math.random()*(newVideoList.size()));
			workoutSchedule.add(newVideoList.get(i));
			description=newVideoList.get(i).getTitle();
			newVideoList.get(i).used();//increments timesPlayed

			if(!newVideoList.get(i).isStillNew()){
				videoLibrary.add((WorkoutVideo) newVideoList.get(i));
				if(newVideoList.get(i).getDuration()<40){
					shortVideoLibrary.add((WorkoutVideo) newVideoList.get(i));
					shortVideoList.add((WorkoutVideo) newVideoList.get(i));
				}
				else{
					videoList.add((WorkoutVideo) newVideoList.get(i));
					regularVideoList.add((WorkoutVideo) newVideoList.get(i));
				}

				newVideoLibrary.remove(newVideoList.get(i));
			}//puts the no longer new video into the other library


			int workoutDuration=newVideoList.get(i).getDuration();

			newVideoList.remove(i);
			int loops=0;

			while(workoutDuration<60){
				if(newVideoList.isEmpty()){
					for(int k=0; k<newVideoLibrary.size(); k++){
						newVideoList.add(newVideoLibrary.get(k));
					}
				}//refills empty list
				if(newVideoList.isEmpty()){
					scheduleShortVideo();
				}//if still empty adds a short video

				else{
					int j=(int) (Math.random()*(newVideoList.size()));
					if(newVideoList.get(j).getDuration()+workoutDuration <=70){


						workoutSchedule.add(newVideoList.get(j));
						workoutDuration+=newVideoList.get(j).getDuration();
						newVideoList.get(j).used();
						description+="\n" + newVideoList.get(j).getTitle();



						if(!newVideoList.get(j).isStillNew()){
							videoLibrary.add((WorkoutVideo) newVideoList.get(j));
							if(newVideoList.get(j).getDuration()<40){
								shortVideoLibrary.add((WorkoutVideo) newVideoList.get(j));
								shortVideoList.add((WorkoutVideo) newVideoList.get(j));
							}
							else{
								videoList.add((WorkoutVideo) newVideoList.get(j));
								regularVideoList.add((WorkoutVideo) newVideoList.get(j));
							}

							newVideoLibrary.remove(newVideoList.get(j));
						}//puts the no longer new video into the other library
						newVideoList.remove(j);
					}
					else{
						/*for(int l=0; l<newVideoLibrary.size(); l++){
							newVideoList.clear();
							newVideoList.add(newVideoLibrary.get(l));
						}*/
						loops++;
					}
					if(loops>1){
						workoutDuration=100;
					}
				}
			}


			if(newVideoList.isEmpty()){
				for(int k=0; k<newVideoLibrary.size(); k++){
					newVideoList.add(newVideoLibrary.get(k));
				}
			}//refills empty list
		}
	}


	public void separateByType(){
		for(int i=0; i<videoLibrary.size(); i++){
			//if(videoLibrary.get(i).getDuration()<100){
			//	shortVideoLibrary.add(videoLibrary.get(i));
			//}//makes list of the short videos
			//else{
				regularVideoList.add(videoLibrary.get(i));
			//}//puts the remaining videos into a separate list
		}
	}

	public void assignVideosToLists(){
		for(int i=0; i<shortVideoLibrary.size(); i++){
			shortVideoList.add(shortVideoLibrary.get(i));
		}
		for(int i=0; i<regularVideoList.size(); i++){
			videoList.add(regularVideoList.get(i));
		}


		for(int i=0; i<newVideoLibrary.size(); i++){
			newVideoList.add(newVideoLibrary.get(i));
		}//fills with new videos

	}

	public String toString(){
		return description + "\n";
	}
}
