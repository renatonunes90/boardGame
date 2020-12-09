package com.imperialof.online.ImperialOF.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.imperialof.online.ImperialOF.model.Due;
import com.imperialof.online.ImperialOF.model.Nation;
import com.imperialof.online.ImperialOF.model.Player;

@Service
public class BankService {

    private static Logger LOGGER = LoggerFactory.getLogger(BankService.class);

	public boolean buyDue(final Player player, final Nation nation, final long value) {
		if(player != null && nation != null && value > 0) {
			if(!playerHasMoney(player, value)) {
				LOGGER.error(String.format("buyDue: Player %s hasn't money to buy due %s from nation %s.", 
						player.getName(), value, nation.getNation().getCode()));;
				return false;	
			}
			
			final Due due = nation.getDueByPrice(value);
			if(due == null) {
				LOGGER.error(String.format("buyDue: Player %s can't buy inexistent due %s from nation %s.", 
						player.getName(), value, nation.getNation().getCode()));;
				return false;	
			}

			nation.removeDue(value);
			player.addDue(nation.getNation(), due);
			transferMoneyToNation(player, nation, value);
						
			LOGGER.info(String.format("buyDue: Player %s bought due of value %s from nation %s.", 
					player.getName(), value, nation.getNation().getCode()));;
			return true;
		} else {
			LOGGER.error("buyDue: Player or nation or value invalid.");
			return false;
		}
	}
	
	public boolean playerHasMoney(final Player player, final long value) {
		if(player != null) {		
			return player.getMoney() > value;
		} else {
			LOGGER.error("playerHasMoney: Invalid player.");
			return false;
		}
	}
	
	public boolean nationHasMoney(final Nation nation, final long value) {
		if(nation != null) {		
			return nation.getMoney() > value;
		} else {
			LOGGER.error("nationHasMoney: Invalid nation.");
			return false;
		}
	}
	
	public boolean nationPayment(final Nation nation, final long value) {
		if(nation != null) {
			if(nationHasMoney(nation, value)) {
				LOGGER.info(String.format("nationPayment: Nation %s paid $ %s to the bank.", nation.getNation().getCode(), value));
				nation.setMoney(nation.getMoney()-value);
				return true;
			} else {
				LOGGER.error(String.format("nationPayment: Nation %s hasn't $ %s to pay to the bank.", nation.getNation().getCode(), value));
				return false;	
			}
		} else {
			LOGGER.error("nationPayment: Invalid nation.");
			return false;
		}
	}

	public boolean receiveMoney(final Player player, final long value) {
		if(player != null && value > 0) {
			player.setMoney(value + player.getMoney());
			LOGGER.info(String.format("receiveMoney: Player %s received $ %s from bank.", player.getName(), value));
			return true;
		} else {
			LOGGER.error("receiveMoney: Player or value invalid.");
			return false;
		}
	}
	
	public boolean transferMoneyToNation(final Player player, final Nation nation, final long value) {
		if(player != null && nation != null && value > 0) {
			if(!playerHasMoney(player, value)) {
				LOGGER.error(String.format("transferMoneyToNation: Player %s hasn't money to transfer %s to nation %s.", 
						player.getName(), value, nation.getNation().getCode()));;
				return false;	
			}
			nation.setMoney(value + nation.getMoney());
			player.setMoney(player.getMoney() - value);
			
			LOGGER.info(String.format("transferMoneyToNation: Player %s transfered value %s to nation %s.", 
					player.getName(), value, nation.getNation().getCode()));;
			return true;
		} else {
			LOGGER.error("transferMoneyToNation: Player or nation or value invalid.");
			return false;
		}
	}
}
