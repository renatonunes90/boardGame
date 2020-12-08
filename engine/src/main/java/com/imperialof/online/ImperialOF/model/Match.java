package com.imperialof.online.ImperialOF.model;

import java.util.ArrayList;
import java.util.List;

import com.imperialof.online.ImperialOF.util.NationEnum;
import com.imperialof.online.ImperialOF.util.RegionTypeEnum;

public class Match {

	private List<Nation> nations;
	
	private List<Region> regions;
	
	private List<Player> players;

	public Match() {
		nations = new ArrayList<>();
		for(NationEnum nation : NationEnum.values()) {
			nations.add(new Nation(nation, createDueList()));
		}

		regions = new ArrayList<>();
		
		// south america
		regions.add(new Region("Rio de Janeiro", RegionTypeEnum.OCEANCAPITAL, false, NationEnum.BRAZIL));
		regions.add(new Region("Brasilia", RegionTypeEnum.LANDCAPITAL, false, NationEnum.BRAZIL));
		regions.add(new Region("Manaus", RegionTypeEnum.LANDCAPITAL, false, NationEnum.BRAZIL));
		regions.add(new Region("Fortaleza", RegionTypeEnum.OCEANCAPITAL, false, NationEnum.BRAZIL));
		regions.add(new Region("Argentina", RegionTypeEnum.NEUTRAL, false, null));
		regions.add(new Region("Peru", RegionTypeEnum.NEUTRAL, false, null));
		regions.add(new Region("Colombia", RegionTypeEnum.NEUTRAL, false, null));
		
		// north america
		regions.add(new Region("New York", RegionTypeEnum.OCEANCAPITAL, false, NationEnum.USA));
		regions.add(new Region("Chicago", RegionTypeEnum.LANDCAPITAL, false, NationEnum.USA));
		regions.add(new Region("New Orleans", RegionTypeEnum.OCEANCAPITAL, false, NationEnum.USA));
		regions.add(new Region("San Francisco", RegionTypeEnum.OCEANCAPITAL, false, NationEnum.USA));
		regions.add(new Region("Mexico", RegionTypeEnum.NEUTRAL, false, null));
		regions.add(new Region("Quebec", RegionTypeEnum.NEUTRAL, false, null));
		regions.add(new Region("Canada", RegionTypeEnum.NEUTRAL, false, null));
		regions.add(new Region("Alaska", RegionTypeEnum.NEUTRAL, false, null));

		// africa
		regions.add(new Region("Guinea", RegionTypeEnum.NEUTRAL, false, null));
		regions.add(new Region("North-Africa", RegionTypeEnum.NEUTRAL, false, null));
		regions.add(new Region("Nigeria", RegionTypeEnum.NEUTRAL, false, null));
		regions.add(new Region("East-Africa", RegionTypeEnum.NEUTRAL, false, null));
		regions.add(new Region("Congo", RegionTypeEnum.NEUTRAL, false, null));
		regions.add(new Region("South-Africa", RegionTypeEnum.NEUTRAL, false, null));
		
		// europe
		regions.add(new Region("Paris", RegionTypeEnum.LANDCAPITAL, false, NationEnum.EUROPE));
		regions.add(new Region("Rome", RegionTypeEnum.OCEANCAPITAL, false, NationEnum.EUROPE));
		regions.add(new Region("Berlin", RegionTypeEnum.LANDCAPITAL, false, NationEnum.EUROPE));
		regions.add(new Region("London", RegionTypeEnum.OCEANCAPITAL, false, NationEnum.EUROPE));
		regions.add(new Region("Ukraine", RegionTypeEnum.NEUTRAL, false, null));
		
		// oceania
		regions.add(new Region("Australia", RegionTypeEnum.NEUTRAL, false, null));
		regions.add(new Region("New Zealand", RegionTypeEnum.NEUTRAL, false, null));
		
		// asia
		regions.add(new Region("Murmansk", RegionTypeEnum.OCEANCAPITAL, false, NationEnum.RUSSIA));
		regions.add(new Region("Moscow", RegionTypeEnum.LANDCAPITAL, false, NationEnum.RUSSIA));
		regions.add(new Region("Novosibirsk", RegionTypeEnum.LANDCAPITAL, false, NationEnum.RUSSIA));
		regions.add(new Region("Vladivostok", RegionTypeEnum.OCEANCAPITAL, false, NationEnum.RUSSIA));
		regions.add(new Region("Urumqi", RegionTypeEnum.LANDCAPITAL, false, NationEnum.CHINA));
		regions.add(new Region("Beijing", RegionTypeEnum.LANDCAPITAL, false, NationEnum.CHINA));
		regions.add(new Region("Chongqing", RegionTypeEnum.LANDCAPITAL, false, NationEnum.CHINA));
		regions.add(new Region("Shangai", RegionTypeEnum.OCEANCAPITAL, false, NationEnum.CHINA));
		regions.add(new Region("New Delhi", RegionTypeEnum.LANDCAPITAL, false, NationEnum.INDIA));
		regions.add(new Region("Mumbai", RegionTypeEnum.OCEANCAPITAL, false, NationEnum.INDIA));
		regions.add(new Region("Kolkata", RegionTypeEnum.OCEANCAPITAL, false, NationEnum.INDIA));
		regions.add(new Region("Chennai", RegionTypeEnum.LANDCAPITAL, false, NationEnum.INDIA));
		regions.add(new Region("Indonesia", RegionTypeEnum.NEUTRAL, false, null));
		regions.add(new Region("Philippines", RegionTypeEnum.NEUTRAL, false, null));
		regions.add(new Region("Indochina", RegionTypeEnum.NEUTRAL, false, null));
		regions.add(new Region("Japan", RegionTypeEnum.NEUTRAL, false, null));
		regions.add(new Region("Korea", RegionTypeEnum.NEUTRAL, false, null));
		regions.add(new Region("Mongolia", RegionTypeEnum.NEUTRAL, false, null));
		regions.add(new Region("Kazakhstan", RegionTypeEnum.NEUTRAL, false, null));
		regions.add(new Region("Afghanistan", RegionTypeEnum.NEUTRAL, false, null));
		regions.add(new Region("Iran", RegionTypeEnum.NEUTRAL, false, null));
		regions.add(new Region("Turkey", RegionTypeEnum.NEUTRAL, false, null));
		regions.add(new Region("Near East", RegionTypeEnum.NEUTRAL, false, null));
		
		// sea
		regions.add(new Region("North Atlantic", RegionTypeEnum.NEUTRAL, true, null));
		regions.add(new Region("North Pacific", RegionTypeEnum.NEUTRAL, true, null));
		regions.add(new Region("Caribbean Sea", RegionTypeEnum.NEUTRAL, true, null));
		regions.add(new Region("South Pacific", RegionTypeEnum.NEUTRAL, true, null));
		regions.add(new Region("South Atlantic", RegionTypeEnum.NEUTRAL, true, null));
		regions.add(new Region("Gulf of Guinea", RegionTypeEnum.NEUTRAL, true, null));
		regions.add(new Region("Mediterranean Sea", RegionTypeEnum.NEUTRAL, true, null));
		regions.add(new Region("Indian Ocean", RegionTypeEnum.NEUTRAL, true, null));
		regions.add(new Region("Tasman Sea", RegionTypeEnum.NEUTRAL, true, null));
		regions.add(new Region("China Sea", RegionTypeEnum.NEUTRAL, true, null));
		regions.add(new Region("Sea of Japan", RegionTypeEnum.NEUTRAL, true, null));
		
		players = new ArrayList<>();
	}
	
	public boolean startGame(final int numberOfPlayers) {
		return true;
	}
	
	private List<Due> createDueList() {
		final List<Due> dues = new ArrayList<Due>();
		dues.add(new Due(1, 2));
		dues.add(new Due(2, 4));
		dues.add(new Due(3, 6));
		dues.add(new Due(4, 9));
		dues.add(new Due(5, 12));
		dues.add(new Due(6, 16));
		dues.add(new Due(7, 20));
		dues.add(new Due(8, 25));
		dues.add(new Due(9, 30));
		return dues;
	}
	
	public List<Nation> getNations() {
		return nations;
	}

	public List<Region> getRegions() {
		return regions;
	}

	public List<Player> getPlayers() {
		return players;
	}

}
