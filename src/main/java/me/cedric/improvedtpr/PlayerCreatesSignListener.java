package me.cedric.improvedtpr;

import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.util.RGBLike;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import net.kyori.adventure.text.Component;
import org.bukkit.event.block.SignChangeEvent;

public class PlayerCreatesSignListener implements Listener {
    RGBLike rgb;
    //int blue = rgb.blue();
    Style style = Style.style(TextColor.color(0x841));
    Component text = Component.text("[TPR]");
    Component SuccessText = Component.text("Successfully made a TPR sign!");
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
