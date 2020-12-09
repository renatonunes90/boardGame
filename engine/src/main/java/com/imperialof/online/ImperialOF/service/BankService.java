package com.imperialof.online.ImperialOF.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.imperialof.online.ImperialOF.model.Due;
import com.imperialof.online.ImperialOF.model.Match;
import com.imperialof.online.ImperialOF.model.Nation;
import com.imperialof.online.ImperialOF.model.Player;
import com.imperialof.online.ImperialOF.model.Region;
import com.imperialof.online.ImperialOF.util.RegionTypeEnum;

@Service
public class BankService {

    private static Logger LOGGER = LoggerFactory.getLogger(BankService.class);

    private static final Long FACTORY_PRICE = 5l;

	public boolean buyDue(final Player player, final Nation nation, final long value) {
		if(player != null && nation != null && value > 0) {
			if(value > player.getMoney()) {
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
	
	public boolean buildFactory(final Match match, final String regionName, final boolean isInitial) {
		if(match != null) {
			final Region region = match.getRegion(regionName);
			if(region != null) {
				if(!region.getType().equals(RegionTypeEnum.NEUTRAL)) {
					final Nation nation = match.getNation(region.getOwner());
					if(!isInitial && nation.getMoney() < 5) {
						LOGGER.error(String.format("buildFactory: Can't build factory in region %s. %s hasn't money.", 
								regionName, nation.getNation().getCode()));
						return false;	
					}
					
					if(!isInitial) {
						nation.setMoney(nation.getMoney()-FACTORY_PRICE);
					}
					LOGGER.info(String.format("buildFactory: Factory built in region %s.", regionName));
					region.setHasFactory(true);
					return true;
				} else {
					LOGGER.error(String.format("buildFactory: Can't build factory in neutral  region %s.", regionName));
					return false;
				}
			} else {
				LOGGER.error(String.format("buildFactory: Region %s invalid.", regionName));
				return false;
			}
		} else {
			LOGGER.error("buildFactory: Match invalid.");
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
			if(value > player.getMoney()) {
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
