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
    
    public static ActionTypeEnum fromCode(final String code) {
        for (ActionTypeEnum action : values()) {
            if (action.getCode().equalsIgnoreCase(code) ) {
                return action;
            }
        }

        throw new IllegalArgumentException("Unknown enum code " + code + " for ActionTypeEnum. Allowed values are FAC, PRD, MOV, INV, IMP or TAX.");
    }
}
