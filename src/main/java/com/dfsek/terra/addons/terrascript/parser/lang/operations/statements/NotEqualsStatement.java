/*
 * Copyright (c) 2020-2021 Polyhedral Development
 *
 * The Terra Core Addons are licensed under the terms of the MIT License. For more details,
 * reference the LICENSE file in this module's root directory.
 */

package com.dfsek.terra.addons.terrascript.parser.lang.operations.statements;

import com.dfsek.terra.addons.terrascript.parser.lang.Returnable;
import com.dfsek.terra.addons.terrascript.parser.lang.operations.BinaryOperation;
import com.dfsek.terra.addons.terrascript.tokenizer.Position;


public class NotEqualsStatement extends BinaryOperation<Object, Boolean> {
    public NotEqualsStatement(Returnable<Object> left, Returnable<Object> right, Position position) {
        super(left, right, position);
    }
    
    @Override
    public Boolean apply(Object left, Object right) {
        return !left.equals(right);
    }
    
    
    @Override
    public Returnable.ReturnType returnType() {
        return Returnable.ReturnType.BOOLEAN;
    }
}
