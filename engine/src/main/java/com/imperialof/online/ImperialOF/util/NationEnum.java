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
}
