package com.imperialof.online.ImperialOF.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imperialof.online.ImperialOF.model.Match;
import com.imperialof.online.ImperialOF.model.Nation;
import com.imperialof.online.ImperialOF.model.Player;
import com.imperialof.online.ImperialOF.model.Region;
import com.imperialof.online.ImperialOF.model.Troop;
import com.imperialof.online.ImperialOF.util.NationEnum;
import com.imperialof.online.ImperialOF.util.RegionTypeEnum;
import com.imperialof.online.ImperialOF.util.TroopTypeEnum;

@Service
public class GameService {

    private static Logger LOGGER = LoggerFactory.getLogger(GameService.class);

    private static final Long FACTORY_PRICE = 5l;

    @Autowired
    private BankService bankService;

	public boolean buildFactory(final Match match, final String regionName, final boolean isInitial) {
		if(match != null) {
			final Region region = match.getRegion(regionName);
			if(region != null) {
				if(!region.getType().equals(RegionTypeEnum.NEUTRAL) && !region.getHasFactory()) {
					final Nation nation = match.getNation(region.getOwner());
					if(!isInitial && !bankService.nationHasMoney(nation, FACTORY_PRICE)) {
						LOGGER.error(String.format("buildFactory: Can't build factory in region %s. %s hasn't money.", 
								regionName, nation.getNation().getCode()));
						return false;	
					}
					
					if(!isInitial) {
						bankService.nationPayment(nation, FACTORY_PRICE);
					}
					region.setHasFactory(true);
					LOGGER.info(String.format("buildFactory: Factory built in region %s.", regionName));
					return true;
				} else {
					LOGGER.error(String.format("buildFactory: Can't build factory in region %s. It's neutral or already has a factory.", regionName));
					return false;
				}
			} else {
				LOGGER.error(String.format("buildFactory: Region %s invalid.", regionName));
				return false;
			}
		} else {
			LOGGER.error("buildFactory: Invalid match.");
			return false;
		}
	}

	
	public Player getCurrentInvestor(final Match match) {
		if(match != null) {
			final Player player = match.getPlayers().stream()
					.filter(p -> p.getIsInvestor()).findFirst().orElse(null);
			if(player != null) {
				LOGGER.info(String.format("getCurrentInvestor: Player %s is the current investor.", player.getName()));
				return player;
			}
			
			LOGGER.error("getCurrentInvestor: There is no current investor");
			return null;
		} else {
			LOGGER.error("getCurrentInvestor: Invalid match.");
			return null;
		}
	}
	
	public Player getLeftPlayerOf(final Match match, final Player player) {
		if(match != null && player != null) {
			final List<Player> players = match.getPlayers().stream()
					.sorted((a,b) -> { return (int) (a.getId() - b.getId()); }).collect(Collectors.toList());
			
			int indexOf = players.indexOf(player);
			if(indexOf != -1) {
				if ( indexOf == 0 ) {
					final Player leftPlayer = players.get(players.size()-1);
					LOGGER.info(String.format("getLeftPlayerOf: Player %s is on the left of the player %s.", leftPlayer.getName(), player.getName()));
					return leftPlayer;
				} else {
					final Player leftPlayer = players.get(indexOf-1);
					LOGGER.info(String.format("getLeftPlayerOf: Player %s is on the left of the player %s.", leftPlayer.getName(), player.getName()));
					return leftPlayer;
				}
			}  else {
				LOGGER.error(String.format("getLeftPlayerOf: Player %s isn't the match.", player.getName()));
				return null;
			}
		} else {
			LOGGER.error("getLeftPlayerOf: Invalid match or player.");
			return null;
		}
	}
	
	public Player getOwnerOfNation(final Match match, final NationEnum nation) {
		if(match != null) {
			final List<Player> players = match.getPlayers();
			Player owner = players.stream()
					.max((a,b) -> { return a.getInfluenceOnNation(nation) - b.getInfluenceOnNation(nation); }).orElse(null);
			if ( owner.getInfluenceOnNation(nation) > 0 ) {
				LOGGER.info(String.format("getOwnerOfNation: Player %s owns the nation %s.", owner.getName(), nation.getCode()));
				return owner;
			} else {
				LOGGER.info(String.format("getOwnerOfNation: No one owns the nation %s.", nation.getCode()));
				return null;
			}
		} else {
			LOGGER.error("getOwnerOfNation: Invalid match.");
			return null;
		}
	}
	
	public boolean passInvestorCard(final Match match) {
		if(match != null) {
			final Player currentInvestor = getCurrentInvestor(match);
			if(currentInvestor != null) {
				
				final Player leftOfInvestor = getLeftPlayerOf(match, currentInvestor);
				if(leftOfInvestor != null) {
					leftOfInvestor.setIsInvestor(true);
					currentInvestor.setIsInvestor(false);
					LOGGER.info(String.format("passInvestorCard: Player %s passed the investor card to player %s.", 
							currentInvestor.getName(), leftOfInvestor.getName()));
					return true;
				}
			}
	
			return false;
		} else {
			LOGGER.error("passInvestorCard: Invalid match.");
			return false;
		}
	}
	
	public boolean canTrainTroop(final Region region) {
		return region.getHasFactory() && !region.getType().equals(RegionTypeEnum.NEUTRAL) && !region.hasHostileTroop();
	}
	
	public boolean trainTroops(final Match match, final NationEnum nationEnum) {
		if(match != null) {
			final List<Region> regions = match.getRegionsOfNation(nationEnum);
			if(regions.size() > 0) {
				regions.stream().filter(r -> canTrainTroop(r))
					.forEach(r ->  {
						TroopTypeEnum troopType = r.getType().equals(RegionTypeEnum.LANDCAPITAL) ? TroopTypeEnum.LANDTROOP : TroopTypeEnum.SEATROOP;
						r.addTroop(new Troop(troopType, nationEnum, false));
						LOGGER.info(String.format("trainTroop: Troop trained in region %s of nation %s.", r.getName(), nationEnum.getCode()));
					});		
				return true;
			} else {
				LOGGER.error(String.format("trainTroop: Nation %s doesn't has regions.", nationEnum.getCode()));
				return false;
			}
		} else {
			LOGGER.error("trainTroop: Invalid match.");
			return false;
		}
	}

}
