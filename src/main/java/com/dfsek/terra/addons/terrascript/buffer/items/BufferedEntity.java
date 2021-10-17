package com.dfsek.terra.addons.terrascript.buffer.items;

import com.dfsek.terra.api.Platform;
import com.dfsek.terra.api.entity.Entity;
import com.dfsek.terra.api.entity.EntityType;
import com.dfsek.terra.api.event.events.world.generation.EntitySpawnEvent;
import com.dfsek.terra.api.structure.buffer.BufferedItem;
import com.dfsek.terra.api.util.vector.Vector3;
import com.dfsek.terra.api.world.World;


public class BufferedEntity implements BufferedItem {
    
    private final EntityType type;
    private final Platform platform;
    
    public BufferedEntity(EntityType type, Platform platform) {
        this.type = type;
        this.platform = platform;
    }
    
    @Override
    public void paste(Vector3 origin, World world) {
        Entity entity = world.spawnEntity(origin.clone().add(0.5, 0, 0.5), type);
        platform.getEventManager().callEvent(new EntitySpawnEvent(entity.world().getConfig().getPack(), entity));
    }
}
