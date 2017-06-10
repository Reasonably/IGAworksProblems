package themePark;

public class Rides implements Comparable<Rides> {
	public int waitingTime;
	public int operatingTime;
	
	
	public Rides(int defaultTime)
	{
		this.waitingTime = defaultTime;
		this.operatingTime = defaultTime;
	}
	
	@Override
	public int compareTo(Rides target) {
        return this.waitingTime - target.waitingTime;
    }
	
}
