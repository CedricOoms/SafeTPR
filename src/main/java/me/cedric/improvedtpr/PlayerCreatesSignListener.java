package me.cedric.improvedtpr;

import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.util.RGBLike;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import net.kyori.adventure.text.Component;
import org.bukkit.event.block.SignChangeEvent;

public class PlayerCreatesSignListener implements Listener {
    Component text= Component.text("[TPR]");
    RGBLike rgb;
    @EventHandler
    public void OnPlayerCreatesSign(SignChangeEvent event){
        Player p = event.getPlayer();
        p.sendPlainMessage("It worked!");
        Component FirstLine = event.line(0);
        if ( FirstLine.equals(text) ){
            Component ColoredText = text.colorIfAbsent(TextColor.color(rgb.blue()));
            event.line(0,ColoredText); //Sets [TPR] to blue on the sign
            p.sendPlainMessage("Successfully made TPR sign!");
            }
    }
}
