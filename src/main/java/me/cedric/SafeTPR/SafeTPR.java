package me.cedric.SafeTPR;

import org.bukkit.plugin.java.JavaPlugin;

public final class SafeTPR extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerSignInteractListener(),this);
        getServer().getPluginManager().registerEvents(new PlayerCreatesSignListener(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
