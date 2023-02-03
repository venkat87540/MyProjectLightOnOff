package com.Light;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Room{
	int count1 =0;
	Scanner s = new Scanner(System.in);
	public void roomWant() {
		System.out.println("How much room you want?");
		int roomCount = s.nextInt();
		count1 =roomCount;
	}

	int roomNumber;
	int personCount;
	Map<Integer,List<String>> roomNumberWithDetails = new HashMap<>();
	//List<Integer> countDuplicate = new ArrayList<Integer>();
	List<Integer> roomNumbers = new ArrayList<Integer>();
	Map<Integer,List<String>> lightOnHistory = new HashMap<>();
	Map<Integer,List<String>> lightOffHistory = new HashMap<>();
	
	int intial =0;
	public void roomMember() {
		for(int i=0;i<count1;i++) {
			System.out.println("Please enter the room number from 1 to 100");
			roomNumber = s.nextInt();
			if(roomNumber >= 1 && roomNumber <=100 ) {
				if(roomNumbers.contains(roomNumber)) {
					intial +=1;
				}
				if(intial == 0) {
					System.out.println("Enter the person count for each room");
					personCount =s.nextInt();
					List<String> eachRoomMembers = new ArrayList<String>();
					System.out.println("Enter the persons you want add in room");
					for(int j=0;j<personCount;j++) {
						String names = s.next();
						eachRoomMembers.add(names);
						if(j==0) {
							Date date = new Date();
							List<String> lightOnList = new ArrayList<String>();
							lightOnList.add(names);
							String dateString = date.toString();
							lightOnList.add(dateString);
							lightOnHistory.put(roomNumber, lightOnList);
						}
					}
					roomNumbers.add(roomNumber);
					roomNumberWithDetails.put(roomNumber, eachRoomMembers);
				}else {
					System.out.println("Enter the valid roomNumber");
					count1 +=1;
				}intial =0;
			}else {
				System.out.println("Enter the valid roomNumber");
				count1 +=1;
			}

		}count1 =0;
	}
	
	int roomFrom;
	int roomTo;
	String swapPersonName;
	int personIndex;
	public void swapPersonToRoom() {
		System.out.println("Which room you need to move from");
		roomFrom = s.nextInt();
		System.out.println("Which room the person want to move");
		roomTo = s.nextInt();
		System.out.println("Enter the person name from one room to another");
		swapPersonName = s.next();
		if(roomNumbers.contains(roomFrom) && roomNumbers.contains(roomTo) && roomFrom != roomTo) {
				if(roomNumberWithDetails.get(roomFrom).contains(swapPersonName)) {
					personIndex = roomNumberWithDetails.get(roomFrom).indexOf(swapPersonName);
					if(roomNumberWithDetails.get(roomFrom).size() == 1) {
						Date date = new Date();
						List<String> lightOffList = new ArrayList<String>();
						lightOffList.add(swapPersonName);
						String dateString = date.toString();
						lightOffList.add(dateString);
						lightOffHistory.put(roomFrom, lightOffList);
					}if(roomNumberWithDetails.get(roomTo).size() == 0) {
						Date date = new Date();
						List<String> lightOnList = new ArrayList<String>();
						lightOnList.add(swapPersonName);
						String dateString = date.toString();
						lightOnList.add(dateString);
						lightOnHistory.put(roomTo, lightOnList);
					}
					roomNumberWithDetails.get(roomFrom).remove(personIndex);
					List<String> updateDetails = roomNumberWithDetails.get(roomTo);
					updateDetails.add(swapPersonName);
					roomNumberWithDetails.put(roomTo, updateDetails);
					System.out.println(roomNumberWithDetails);
				}	
		}else {
			System.out.println("Please enter the valid room Number");
		}
	}
	int selectRoomNumber;
	String  removePersonName;
	public void removePerson() {
		System.out.println("Which room number you want to remove that person?");
		selectRoomNumber = s.nextInt();
		System.out.println("Enter the person name to remove");
		removePersonName = s.next();
		if(roomNumberWithDetails.containsKey(selectRoomNumber) && roomNumberWithDetails.get(selectRoomNumber).contains(removePersonName)) {
			if(roomNumberWithDetails.get(selectRoomNumber).size() == 1) {
				Date date = new Date();
				List<String> lightOffList = new ArrayList<String>();
				lightOffList.add(removePersonName);
				String dateString = date.toString();
				lightOffList.add(dateString);
				lightOffHistory.put(selectRoomNumber, lightOffList);
			}
			roomNumberWithDetails.get(selectRoomNumber).remove(removePersonName);
		}else {
			System.out.println("Please enter the valid details");
		}
	}
	public void lightOnOffHistorys() {
		System.out.println("Do you want lightOn or lightOff history (lightOn means type ON) / (lightOff means type OFF) ");
		String lightOnOffInput = s.next();
		if(lightOnOffInput.equalsIgnoreCase("on")) {
			System.out.println(lightOnHistory);
		}
		else if(lightOnOffInput.equalsIgnoreCase("off")) {
			System.out.println(lightOffHistory);
		}else {
			System.out.println("Enter the valid input");
		}
	}
	public void emptyRoom() {
		List<Integer> totalRooms = new ArrayList<Integer>();
		for(int i=1;i<=100;i++) {
			totalRooms.add(i);
		}
		for(int y=0;y<roomNumbers.size();y++) {
			for(int j=0;j<totalRooms.size();j++) {
				if(roomNumbers.get(y)==(totalRooms.get(j))) {
					totalRooms.remove(totalRooms.get(j));
					break;
				}
			}
		}
		System.out.println("Empty rooms are mentioned below: ");
		System.out.println(totalRooms);
		System.out.println("Total empty rooms: ");
		System.out.println(totalRooms.size());
	}
}
public class LightOnOff2 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Room obj = new Room();
		String wish;
		int option;
		do {
			System.out.println("\nPlease choose an option :\n1.Add a room\n2.Add a person to a room\n3.Remove a person from a room\n4.Swap a person from one room to another\n5.Engaged rooms and names of persons in the respective room\n6.Lights on/off history\n7.Empty rooms\n");
			option = s.nextInt();
			switch(option) {
			case 1:
				obj.roomWant();
				break;
			case 2:
				if(obj.count1 == 0) {
					System.out.println("\nPlease add a room first");
				}
				else{
					obj.roomMember();
				}
				break;
			case 3:
				obj.removePerson();
				break;
			case 4:
				if(obj.roomNumbers.size() >=2) {
					obj.swapPersonToRoom();
				}else{
					System.out.println("We need atleast two room for swapping.....");
					break;
				}
			case 5:
				if(obj.roomNumberWithDetails.size()>=1) {
					System.out.println("Engaged rooms and names of persons in the respective room listed below:");
					System.out.println(obj.roomNumberWithDetails);
				}else {
					System.out.println("No Engaged rooms");
				}break;
			case 6:
				obj.lightOnOffHistorys();
				break;
			case 7:
				obj.emptyRoom();
				break;
			}
			System.out.println("\nDo you want to Continue? (yes/no)");
			wish = s.next(); 
			if(!(wish.equalsIgnoreCase("Yes")||wish.equalsIgnoreCase("no")))
			{
				System.out.println("Invalid Option");
				System.out.println("\nDo you want to Continue? (Yes/No)");
				wish=s.next(); 
			}

		}while(wish.equalsIgnoreCase("Yes"));
		System.out.println(obj.roomNumberWithDetails);
		s.close();
	}
}
//validation controller.java -- contains only validation

