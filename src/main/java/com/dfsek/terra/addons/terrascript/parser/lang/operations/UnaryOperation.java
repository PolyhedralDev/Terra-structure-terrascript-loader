/*
 * Copyright (c) 2020-2021 Polyhedral Development
 *
 * The Terra Core Addons are licensed under the terms of the MIT License. For more details,
 * reference the LICENSE file in this module's root directory.
 */

package com.dfsek.terra.addons.terrascript.parser.lang.operations;

import java.util.Map;

import com.dfsek.terra.addons.terrascript.parser.lang.ImplementationArguments;
import com.dfsek.terra.addons.terrascript.parser.lang.Returnable;
import com.dfsek.terra.addons.terrascript.parser.lang.variables.Variable;
import com.dfsek.terra.addons.terrascript.tokenizer.Position;


public abstract class UnaryOperation<T> implements Returnable<T> {
    private final Returnable<T> input;
    private final Position position;
    
    public UnaryOperation(Returnable<T> input, Position position) {
        this.input = input;
        this.position = position;
    }
    
    public abstract T apply(T input);
    
    @Override
    public T apply(ImplementationArguments implementationArguments, Map<String, Variable<?>> variableMap) {
        return apply(input.apply(implementationArguments, variableMap));
    }
    
    @Override
    public Position getPosition() {
        return position;
    }
}
