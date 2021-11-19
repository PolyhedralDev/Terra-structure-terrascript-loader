/*
 * Copyright (c) 2020-2021 Polyhedral Development
 *
 * The Terra Core Addons are licensed under the terms of the MIT License. For more details,
 * reference the LICENSE file in this module's root directory.
 */

package com.dfsek.terra.addons.terrascript.parser.lang.operations;

import com.dfsek.terra.addons.terrascript.parser.lang.Returnable;
import com.dfsek.terra.addons.terrascript.tokenizer.Position;


public class ModuloOperation extends BinaryOperation<Number, Number> {
    public ModuloOperation(Returnable<Number> left, Returnable<Number> right, Position start) {
        super(left, right, start);
    }
    
    @Override
    public Number apply(Number left, Number right) {
        return left.doubleValue() % right.doubleValue();
    }
    
    @Override
    public ReturnType returnType() {
        return ReturnType.NUMBER;
    }
}
