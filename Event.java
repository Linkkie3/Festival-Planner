

public class Event {
	private Stage stage;
	private Artist artist;
	private int estimatedPopularity;
	private int sHour;
	private int sMinute;
	private int eHour;
	private int eMinute;
	public Event(Stage stage, Artist artist, int estimatedPopularity, int sHour, int sMinute, int eHour, int eMinute)
	{
		this.setStage(stage);
		this.setArtist(artist);
		this.setEstimatedPopularity(estimatedPopularity);
		this.setsHour(sHour);
		this.seteHour(eHour);
		this.setsMinute(sMinute);
		this.seteMinute(eMinute);
		
	}
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public Artist getArtist() {
		return artist;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	public int getEstimatedPopularity() {
		return estimatedPopularity;
	}
	public void setEstimatedPopularity(int estimatedPopularity) {
		this.estimatedPopularity = estimatedPopularity;
	}
	public int getsHour() {
		return sHour;
	}
	public void setsHour(int sHour) {
		this.sHour = sHour;
	}
	public int getsMinute() {
		return sMinute;
	}
	public void setsMinute(int sMinute) {
		this.sMinute = sMinute;
	}
	public int geteHour() {
		return eHour;
	}
	public void seteHour(int eHour) {
		this.eHour = eHour;
	}
	public int geteMinute() {
		return eMinute;
	}
	public void seteMinute(int eMinute) {
		this.eMinute = eMinute;
	}
}
