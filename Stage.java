
public class Stage {
	private int xLocation;
	private int yLocation;
	private int numberOfStage;
	
	public Stage(int xLocation, int yLocation, int numberOfStage)
	{
		this.setxLocation(xLocation);
		this.setyLocation(yLocation);
		this.numberOfStage = numberOfStage;
	}

	public int getxLocation() {
		return xLocation;
	}

	public void setxLocation(int xLocation) {
		this.xLocation = xLocation;
	}

	public int getyLocation() {
		return yLocation;
	}

	public void setyLocation(int yLocation) {
		this.yLocation = yLocation;
	}

	public int getNumberOfStage() {
		return numberOfStage;
	}

	public void setNumberOfStage(int numberOfStage) {
		this.numberOfStage = numberOfStage;
	}
}
