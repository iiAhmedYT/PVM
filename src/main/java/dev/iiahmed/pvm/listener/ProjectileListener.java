package dev.iiahmed.pvm.listener;

import dev.iiahmed.pvm.PVM;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ProjectileListener implements Listener {

    private final PVM instance = PVM.getInstance();

    @EventHandler
    public void onThrow(ProjectileLaunchEvent event) {
        Entity entity = event.getEntity();
        String name = entity.getType().name();
        double multiplier = getMultiplier(name);
        entity.setVelocity(entity.getVelocity().multiply(multiplier));
    }

    private double getMultiplier(String name) {
        String path = "VELOCITY-MULTIPLIER." + name;
        if(instance.getConfig().contains(path)) {
            return instance.getConfig().getDouble(path);
        }
        return 1.0;
    }

}
