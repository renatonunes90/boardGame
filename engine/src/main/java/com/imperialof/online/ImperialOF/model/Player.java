package com.imperialof.online.ImperialOF.model;

import java.util.List;
import java.util.Map;

import com.imperialof.online.ImperialOF.util.NationEnum;

public class Player {

	private Boolean isInvestor;
	
	private Map<NationEnum, List<Due>> dueList;
	
	private Long money;

	public Boolean getIsInvestor() {
		return isInvestor;
	}

	public void setIsInvestor(Boolean isInvestor) {
		this.isInvestor = isInvestor;
	}

	public Map<NationEnum, List<Due>> getDueList() {
		return dueList;
	}

	public void setDueList(Map<NationEnum, List<Due>> dueList) {
		this.dueList = dueList;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}
}
