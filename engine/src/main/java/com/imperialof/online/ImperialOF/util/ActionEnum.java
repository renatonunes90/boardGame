package com.imperialof.online.ImperialOF.util;

public enum ActionEnum {

	FACTORY(ActionTypeEnum.FACTORY, 1),
	PRODUCTION(ActionTypeEnum.PRODUCTION, 2),
	MOVEMENT(ActionTypeEnum.MOVEMENT, 3),
	INVEST(ActionTypeEnum.INVEST, 4),
	IMPORT(ActionTypeEnum.IMPORT, 5),
	PRODUCTION2(ActionTypeEnum.PRODUCTION, 6),
	MOVEMENT2(ActionTypeEnum.MOVEMENT, 7),
	TAXATION(ActionTypeEnum.TAXATION, 8)
	;

    private final ActionTypeEnum typeOf;
    
    private final int order;

    ActionEnum(ActionTypeEnum typeOf, int order) {
        this.typeOf = typeOf;
        this.order = order;
    }

    public ActionTypeEnum getTypeOf() {
        return typeOf;
    }
    
    public int getOrder() {
        return order;
    }
}
