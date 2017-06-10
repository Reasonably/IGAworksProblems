package elevator;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static int currFloor = 1;
	public static int currTime = 0;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int testCaseCount = sc.nextInt();
		
		for(int j=0; j<testCaseCount; j++) {
			currTime = 0;
			currFloor = 1;
			
			int highestFloor = sc.nextInt();
			int availableCount = sc.nextInt();
			int boardCount = sc.nextInt();
			
			PriorityQueue<Passenger> allPassengers = new PriorityQueue<Passenger>();
			
			for(int i=1; i<=boardCount; i++) {
				allPassengers.add(new Passenger(sc.nextInt(), sc.nextInt(), i));
			}
			
			simulate(highestFloor, availableCount, boardCount, allPassengers);
			
		}
		
		sc.close();
	}
	
	public static void simulate(int highestFloor, int availableCount, int boardCount, PriorityQueue<Passenger> all){
		
		Elevator elv = new Elevator(availableCount);
		boolean isFull = false;
		int maxTime = 0;
		int elvStatus = 1; // 1: up, -1: down
		
		for(int i=1; i<=boardCount; i++) {
			
			currTime = i;
			
			PriorityQueue<Passenger> curr = new PriorityQueue<Passenger>(all);
			curr.removeIf(p -> p.startTime > currTime || p.waitFloor != currFloor || p.waitFloor == p.dest);
			
			elv.arrive(currFloor, curr);
			
			if(elv.people.size() > availableCount) {
				isFull = true;
				maxTime = i;
				break;
			}
			
			
			if(currFloor == highestFloor || (currFloor == 1 && elvStatus == -1))
				elvStatus = -elvStatus;
			
			currFloor += elvStatus;
		}
		
		if(isFull) {
			System.out.println(maxTime);
		} 
		else {
			System.out.println(elv.maxNumber - availableCount);
		}
		
	}
	

}
