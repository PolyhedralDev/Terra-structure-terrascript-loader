/*
 * Copyright (c) 2020-2021 Polyhedral Development
 *
 * The Terra Core Addons are licensed under the terms of the MIT License. For more details,
 * reference the LICENSE file in this module's root directory.
 */

package com.dfsek.terra.addons.terrascript.script.functions;

import net.jafama.FastMath;

import java.util.Map;

import com.dfsek.terra.addons.terrascript.buffer.items.BufferedStateManipulator;
import com.dfsek.terra.addons.terrascript.parser.lang.ImplementationArguments;
import com.dfsek.terra.addons.terrascript.parser.lang.Returnable;
import com.dfsek.terra.addons.terrascript.parser.lang.functions.Function;
import com.dfsek.terra.addons.terrascript.parser.lang.variables.Variable;
import com.dfsek.terra.addons.terrascript.script.TerraImplementationArguments;
import com.dfsek.terra.addons.terrascript.tokenizer.Position;
import com.dfsek.terra.api.Platform;
import com.dfsek.terra.api.util.RotationUtil;
import com.dfsek.terra.api.util.vector.Vector2;
import com.dfsek.terra.api.util.vector.Vector3;


public class StateFunction implements Function<Void> {
    private final Returnable<String> data;
    private final Returnable<Number> x, y, z;
    private final Position position;
    private final Platform platform;
    
    public StateFunction(Returnable<Number> x, Returnable<Number> y, Returnable<Number> z, Returnable<String> data, Platform platform,
                         Position position) {
        this.position = position;
        this.platform = platform;
        this.data = data;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    @Override
    public Void apply(ImplementationArguments implementationArguments, Map<String, Variable<?>> variableMap) {
        TerraImplementationArguments arguments = (TerraImplementationArguments) implementationArguments;
        Vector2 xz = new Vector2(x.apply(implementationArguments, variableMap).doubleValue(),
                                 z.apply(implementationArguments, variableMap).doubleValue());
        RotationUtil.rotateVector(xz, arguments.getRotation());
        
        arguments.getBuffer().addItem(new BufferedStateManipulator(platform, data.apply(implementationArguments, variableMap)),
                                      new Vector3(FastMath.roundToInt(xz.getX()), y.apply(implementationArguments, variableMap).intValue(),
                                                  FastMath.roundToInt(xz.getZ())));
        return null;
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
