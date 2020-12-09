package com.imperialof.online.ImperialOF.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.imperialof.online.ImperialOF.util.NationEnum;

public class Player {

	private Long id;
	
	private String name;

	private Boolean isInvestor;
	
	private Map<NationEnum, List<Due>> dueList;
	
	private Long money;

	public Player(final String name) {
		this.id = Math.round(Math.random()*1000);
		this.name = name;
		this.isInvestor = false;
		this.money = 0L;
		this.dueList = new HashMap<NationEnum, List<Due>>();
	}

	public void addDue(final NationEnum nation, final Due due) {
		if(dueList.get(nation) == null) {
			dueList.put(nation, new ArrayList<Due>());
		}
		dueList.get(nation).add(due);
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
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
