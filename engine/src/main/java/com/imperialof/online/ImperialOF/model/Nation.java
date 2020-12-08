package com.imperialof.online.ImperialOF.model;

import java.util.List;

import com.imperialof.online.ImperialOF.util.ActionEnum;
import com.imperialof.online.ImperialOF.util.NationEnum;

public class Nation {

	private Long points;
	
	private NationEnum nation;
	
	private ActionEnum currentAction;
	
	private List<Due> dueList;
	
	private Long money;

	public Nation(final NationEnum nation, final List<Due> dueList) {
		this.points = 0L;
		this.nation = nation;
		this.currentAction = null;
		this.dueList = dueList;
		this.money = 0L;
	}
	
	public Long getPoints() {
		return points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	public NationEnum getNation() {
		return nation;
	}

	public void setNation(NationEnum nation) {
		this.nation = nation;
	}

	public ActionEnum getCurrentAction() {
		return currentAction;
	}

	public void setCurrentAction(ActionEnum currentAction) {
		this.currentAction = currentAction;
	}

	public List<Due> getDueList() {
		return dueList;
	}

	public void setDueList(List<Due> dueList) {
		this.dueList = dueList;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}
	
	
}
