/*
 * Copyright (c) 2020-2021 Polyhedral Development
 *
 * The Terra Core Addons are licensed under the terms of the MIT License. For more details,
 * reference the LICENSE file in this module's root directory.
 */

package com.dfsek.terra.addons.terrascript.parser.lang.keywords.looplike;

import java.util.Map;

import com.dfsek.terra.addons.terrascript.parser.lang.Block;
import com.dfsek.terra.addons.terrascript.parser.lang.ImplementationArguments;
import com.dfsek.terra.addons.terrascript.parser.lang.Keyword;
import com.dfsek.terra.addons.terrascript.parser.lang.Returnable;
import com.dfsek.terra.addons.terrascript.parser.lang.variables.Variable;
import com.dfsek.terra.addons.terrascript.tokenizer.Position;


public class WhileKeyword implements Keyword<Block.ReturnInfo<?>> {
    private final Block conditional;
    private final Returnable<Boolean> statement;
    private final Position position;
    
    public WhileKeyword(Block conditional, Returnable<Boolean> statement, Position position) {
        this.conditional = conditional;
        this.statement = statement;
        this.position = position;
    }
    
    @Override
    public Block.ReturnInfo<?> apply(ImplementationArguments implementationArguments, Map<String, Variable<?>> variableMap) {
        while(statement.apply(implementationArguments, variableMap)) {
            Block.ReturnInfo<?> level = conditional.apply(implementationArguments, variableMap);
            if(level.getLevel().equals(Block.ReturnLevel.BREAK)) break;
            if(level.getLevel().isReturnFast()) return level;
        }
        return new Block.ReturnInfo<>(Block.ReturnLevel.NONE, null);
    }
    
    @Override
    public Position getPosition() {
        return position;
    }
    
    @Override
    public ReturnType returnType() {
        return ReturnType.VOID;
    }
}
