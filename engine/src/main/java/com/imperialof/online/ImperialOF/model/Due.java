package com.imperialof.online.ImperialOF.model;

public class Due {

	private int interest;
	
	private int purchasePrice;

	public Due(final int interest, final int purchasePrice) {
		this.interest = interest;
		this.purchasePrice = purchasePrice;
	}

	public int getInterest() {
		return interest;
	}

	public void setInterest(int interest) {
		this.interest = interest;
	}

	public int getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(int purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
}
