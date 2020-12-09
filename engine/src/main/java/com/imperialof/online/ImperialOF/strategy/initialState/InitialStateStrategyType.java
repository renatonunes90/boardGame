package com.imperialof.online.ImperialOF.strategy.initialState;

import java.util.HashMap;
import java.util.Map;

import com.imperialof.online.ImperialOF.service.BankService;

public class InitialStateStrategyType {
	
	static Map<Integer, InitialStateStrategy> strategyMap = new HashMap<>();

	static {
		strategyMap.put(2, new TwoPlayersStateStrategy());
		strategyMap.put(3, new ThreePlayersStateStrategy());
		strategyMap.put(4, new FourOrMorePlayersStateStrategy());
		strategyMap.put(5, new FourOrMorePlayersStateStrategy());
		strategyMap.put(6, new FourOrMorePlayersStateStrategy());
	}
	
	public static InitialStateStrategy get(int key, BankService bankService) {
		strategyMap.get(key).setBankService(bankService);
		return strategyMap.get(key);
	}
}
