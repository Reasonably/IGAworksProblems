package elevator;


public class Passenger implements Comparable<Passenger>{
	public int dest;
	public int startTime;
	public int waitFloor;
	
	public Passenger(int waitFloor, int dest, int startTime) {
		this.dest = dest;
		this.startTime = startTime;
		this.waitFloor = waitFloor;
	}
	
	@Override
	public int compareTo(Passenger target) {
        return this.startTime - target.startTime;
    }

}
