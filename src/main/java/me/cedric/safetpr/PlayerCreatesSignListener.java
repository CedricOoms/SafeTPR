package me.cedric.safetpr;

import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import net.kyori.adventure.text.Component;
import org.bukkit.event.block.SignChangeEvent;

public class PlayerCreatesSignListener implements Listener {
    Style style = Style.style(TextColor.color(0xBF7));
    Component text = Component.text("[TPR]");
    Component SuccessText = Component.text("You have successfully made a TPR sign!");
    @EventHandler
    public void OnPlayerCreatesSign(SignChangeEvent event){
        Player p = event.getPlayer();
        Component FirstLine = event.line(0);
        if ( FirstLine.equals(text) ){
            event.line(0,text.style(style)); //Sets [TPR] to blue on the sign
            p.sendMessage(SuccessText.style(style));
            }
    }
}
