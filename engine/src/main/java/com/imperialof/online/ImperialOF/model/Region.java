package com.imperialof.online.ImperialOF.model;

import java.util.ArrayList;
import java.util.List;

import com.imperialof.online.ImperialOF.util.NationEnum;
import com.imperialof.online.ImperialOF.util.RegionTypeEnum;

public class Region {

	private String name;
	
	private Boolean isOcean;
	
	private Boolean hasFactory;
	
	private RegionTypeEnum type;
	
	private NationEnum owner;
	
	private List<Troop> troops;

	public Region(final String name, final RegionTypeEnum type, final Boolean isOcean, final NationEnum owner) {
		this.name = name;
		this.isOcean = isOcean;
		this.type = type;
		this.hasFactory = false;
		this.owner = owner;
		this.troops = new ArrayList<>();
	}
	
	public void addTroop(final Troop troop) {
		this.troops.add(troop);
	}
	
	public boolean hasHostileTroop() {
		return troops.size() > 0 && troops.stream().filter(t -> t.isHostile()).findFirst().isPresent();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsOcean() {
		return isOcean;
	}

	public void setIsOcean(Boolean isOcean) {
		this.isOcean = isOcean;
	}

	public Boolean getHasFactory() {
		return hasFactory;
	}

	public void setHasFactory(Boolean hasFactory) {
		this.hasFactory = hasFactory;
	}

	public RegionTypeEnum getType() {
		return type;
	}

	public void setType(RegionTypeEnum type) {
		this.type = type;
	}

	public NationEnum getOwner() {
		return owner;
	}

	public void setOwner(NationEnum owner) {
		this.owner = owner;
	}

	public List<Troop> getTroops() {
		return troops;
	}

	public void setTroops(List<Troop> troops) {
		this.troops = troops;
	}
}
