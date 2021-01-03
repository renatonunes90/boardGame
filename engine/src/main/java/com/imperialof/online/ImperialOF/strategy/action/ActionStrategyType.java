package com.imperialof.online.ImperialOF.strategy.action;

import java.util.HashMap;
import java.util.Map;

import com.imperialof.online.ImperialOF.service.BankService;
import com.imperialof.online.ImperialOF.service.GameService;
import com.imperialof.online.ImperialOF.util.ActionTypeEnum;

public class ActionStrategyType {
	
	static Map<ActionTypeEnum, ActionStrategy> strategyMap = new HashMap<>();

	static {
		strategyMap.put(ActionTypeEnum.FACTORY, new FactoryActionStrategy());
		strategyMap.put(ActionTypeEnum.IMPORT, new FactoryActionStrategy());
		strategyMap.put(ActionTypeEnum.INVEST, new FactoryActionStrategy());
		strategyMap.put(ActionTypeEnum.MOVEMENT, new FactoryActionStrategy());
		strategyMap.put(ActionTypeEnum.PRODUCTION, new ProductionActionStrategy());
		strategyMap.put(ActionTypeEnum.TAXATION, new FactoryActionStrategy());
	}
	
	public static ActionStrategy get(ActionTypeEnum key, BankService bankService, GameService gameService) {
		strategyMap.get(key).setBankService(bankService);
		strategyMap.get(key).setGameService(gameService);
		return strategyMap.get(key);
	}
}
