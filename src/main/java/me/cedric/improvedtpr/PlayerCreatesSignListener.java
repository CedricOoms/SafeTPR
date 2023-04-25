package me.cedric.improvedtpr;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import net.kyori.adventure.text.Component;
import org.bukkit.event.block.SignChangeEvent;

public class PlayerCreatesSignListener implements Listener {
    Component text= Component.text("[TPR]");
    @EventHandler
    public void OnPlayerCreatesSign(SignChangeEvent event){
        Player p = event.getPlayer();
        p.sendPlainMessage("It worked!");
        Component FirstLine = event.line(0);
        if ( FirstLine.equals(text) ){
            p.sendPlainMessage("Successfully made TPR sign!");
            }

    }
}
