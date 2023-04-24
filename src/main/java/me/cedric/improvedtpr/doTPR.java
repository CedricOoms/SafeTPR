package me.cedric.improvedtpr;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.entity.Player;
import me.cedric.improvedtpr.FindLoc;

public class doTPR implements Listener {
    Random rand = new Random();

    // Player right-clicks an entity:
    @EventHandler
    public void onSignTouch(PlayerInteractAtEntityEvent event){
        Player player = event.getPlayer();
        World world = player.getWorld();
        Location pos = player.getLocation();




    }
}
