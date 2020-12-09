package com.imperialof.online.ImperialOF.util;

public enum NationEnum {

	RUSSIA("Ru", 1),
	CHINA("Ch", 2),
	INDIA("In", 3),
	BRAZIL("Br", 4),
	USA("USA", 5),
	EUROPE("Eu", 6);

    private final String code;
    
    private final int order;

    NationEnum(String code, int order) {
        this.code = code;
        this.order = order;
    }

    public String getCode() {
        return code;
    }
    
    public int getOrder() {
        return order;
    }
    
    public static NationEnum fromOrder(final int order) {
        for (NationEnum nation : values()) {
            if (nation.getOrder() == order) {
                return nation;
            }
        }

        throw new IllegalArgumentException("Unknown enum order " + order+ " for NationEnum. Allowed values are 1,2,3,4,5 or 6.");
    }
}
