package me.cedric.improvedtpr;

import org.bukkit.plugin.java.JavaPlugin;

public final class ImprovedTPR extends JavaPlugin {

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
