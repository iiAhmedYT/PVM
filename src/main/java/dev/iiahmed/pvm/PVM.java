package dev.iiahmed.pvm;

import dev.iiahmed.pvm.command.PVMCommand;
import dev.iiahmed.pvm.listener.ProjectileListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class PVM extends JavaPlugin {

    private static PVM instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new ProjectileListener(), this);
        getServer().getPluginCommand("PVM").setExecutor(new PVMCommand());
    }

    public static PVM getInstance() {
        return instance;
    }
}
