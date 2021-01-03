package com.imperialof.online.ImperialOF.model;

import com.imperialof.online.ImperialOF.util.NationEnum;
import com.imperialof.online.ImperialOF.util.TroopTypeEnum;

public class Troop {

	private TroopTypeEnum type;
	
	private NationEnum nation;
	
	private boolean isHostile;

	public Troop(final TroopTypeEnum type, final NationEnum nation, boolean isHostile) {
		this.type = type;
		this.nation = nation;
		this.isHostile = isHostile;
	}
	
	public TroopTypeEnum getType() {
		return type;
	}

	public void setType(TroopTypeEnum type) {
		this.type = type;
	}

	public NationEnum getNation() {
		return nation;
	}

	public void setNation(NationEnum nation) {
		this.nation = nation;
	}

	public boolean isHostile() {
		return isHostile;
	}

	public void setHostile(boolean isHostile) {
		this.isHostile = isHostile;
	}
}
