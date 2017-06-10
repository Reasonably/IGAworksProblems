package themePark;
/*
 * 놀이기구 클래스는 운행시간(operatingTime)과 아이가 그 놀이기구에 왔을 때 기다려야하는 시간(waitingTime)으로 나뉘어진다.
 * waiting time을 기준으로 최소 heap을 구성한다면, heap의 head에는 i번 iterate했을 때, i+1 명이 적어도 한 번 놀이기구를
 * 탈 때의 최소시간이 나온다.
 * 따라서 childCount - 1만큼 iterate한다면 원하는 값이 나온다. 
 */

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	
	public static void main(String arg[]) {
		
		
		Scanner sc = new Scanner(System.in);
		
		int testCaseCount = sc.nextInt();
		
		for(int j=0; j<testCaseCount; j++) {
			int childCount = sc.nextInt();
			int rideCount = sc.nextInt();
			
			int[] playTimes = new int[rideCount];
			
			for(int i=0; i<rideCount; i++) {
				playTimes[i] = sc.nextInt();
			}
			
			/*
			System.out.println("TestCase : " + String.valueOf(testCaseCount));
			System.out.println("ChildCount : " + String.valueOf(childCount));
			System.out.println("rideCount : " + String.valueOf(rideCount));
			System.out.println("playTimes : " + Arrays.toString(playTimes));
			*/
			
			calculateResult(childCount, rideCount, playTimes);
		}
		
		sc.close();
	}

	
	public static void calculateResult(int childCount, int rideCount, int[] playTimes) {
		PriorityQueue<Rides> heap = new PriorityQueue<>(rideCount);

	
		for(int i=0; i<rideCount; i++) {
			heap.add(new Rides(playTimes[i]));
		}
		
		for(int i=0; i<childCount-1; i++) {
			Rides ride = heap.poll();
			
			ride.waitingTime += ride.operatingTime;
			
			heap.add(ride);
		}
		
		System.out.println(String.valueOf(heap.peek().waitingTime));
	
	}
}
