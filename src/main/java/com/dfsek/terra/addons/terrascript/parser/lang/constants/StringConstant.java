package com.dfsek.terra.addons.terrascript.parser.lang.constants;

import com.dfsek.terra.addons.terrascript.parser.lang.Returnable;
import com.dfsek.terra.addons.terrascript.tokenizer.Position;


public class StringConstant extends ConstantExpression<String> {
    public StringConstant(String constant, Position position) {
        super(constant, position);
    }
    
    @Override
    public Returnable.ReturnType returnType() {
        return Returnable.ReturnType.STRING;
    }
}
