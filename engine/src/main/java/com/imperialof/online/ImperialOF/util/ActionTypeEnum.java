package com.imperialof.online.ImperialOF.util;

public enum ActionTypeEnum {

	FACTORY("FAC"),
	PRODUCTION("PRD"),
	MOVEMENT("MOV"),
	INVEST("INV"),
	IMPORT("IMP"),
	TAXATION("TAX")
	;

    private final String code;
    
    ActionTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
