package com.imperialof.online.ImperialOF.model;

import com.imperialof.online.ImperialOF.util.NationEnum;
import com.imperialof.online.ImperialOF.util.TroopTypeEnum;

public class Troop {

	private TroopTypeEnum type;
	
	private NationEnum nation;

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
}
