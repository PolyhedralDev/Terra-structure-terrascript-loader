package com.dfsek.terra.addons.terrascript.parser.lang.operations.statements;

import net.jafama.FastMath;

import com.dfsek.terra.addons.terrascript.parser.lang.Returnable;
import com.dfsek.terra.addons.terrascript.parser.lang.operations.BinaryOperation;
import com.dfsek.terra.addons.terrascript.tokenizer.Position;


public class EqualsStatement extends BinaryOperation<Object, Boolean> {
    private static final double EPSILON = 0.000000001D;
    
    public EqualsStatement(Returnable<Object> left, Returnable<Object> right, Position position) {
        super(left, right, position);
    }
    
    @Override
    public Boolean apply(Object left, Object right) {
        if(left instanceof Number && right instanceof Number) {
            return FastMath.abs(((Number) left).doubleValue() - ((Number) right).doubleValue()) <= EPSILON;
        }
        
        return left.equals(right);
    }
    
    
    @Override
    public Returnable.ReturnType returnType() {
        return Returnable.ReturnType.BOOLEAN;
    }
}
