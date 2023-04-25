package me.cedric.improvedtpr;

import org.bukkit.plugin.java.JavaPlugin;
import net.ess3.api.IWarps;
import net.ess3.api.IEssentials;
import  net.ess3.api.IUser;

public final class ImprovedTPR extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerSignInteractListener(),this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
