package elevator;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Elevator {
	
	public List<Passenger> people;
	public int currentFloor;
	public int maxNumber;
	public int limitNumber;
	
	
	public Elevator(int limitNumber) {
		this.people = new ArrayList<Passenger>();
		this.currentFloor = 1;
		this.limitNumber = limitNumber;
		this.maxNumber = 0;
	}
	
	public void arrive(int currentFloor, PriorityQueue<Passenger> passengers) {
		
		this.currentFloor = currentFloor;

		people.removeIf(p-> p.dest == currentFloor);
		
		if(passengers.size() <= 0)
			return;
		
	
		int size = people.size();
		
		if(size > limitNumber)
			return;
		
		people.add(passengers.peek());
		size++;
		
		if(size > maxNumber)
			maxNumber = size;
		
//		}
	}

}
