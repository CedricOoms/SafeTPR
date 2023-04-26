package me.cedric.improvedtpr;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.block.Sign;

public class PlayerSignInteractListener implements Listener{
    Component text = Component.text("[TPR]");
    Component TpText = Component.text("Teleporting to random location now!");
    Component SuccessText = Component.text("You have been teleported to a random new location, no other players close-by. Good luck!");
    Style style = Style.style(TextColor.color(0x841));
    Style SuccessTextStyle = Style.style(TextColor.color(0x640000));
    @EventHandler
    public void onPlayerClicksSign(PlayerInteractEvent event){
        Player p = event.getPlayer();
        Block RegisteredBlock = event.getClickedBlock();
        World world = p.getWorld();
        if ( RegisteredBlock.getType().toString() == "OAK_WALL_SIGN" || RegisteredBlock.getType().toString() == "OAK_SIGN"){
            Sign sign = (Sign) RegisteredBlock.getState();
            Component FirstLine = sign.line(0);
            if (FirstLine.equals(text.style(style))) {
                p.sendMessage(TpText.style(style));
                FindLoc findLoc = new FindLoc();
                p.teleport(findLoc.getRandLoc(p,world));
                p.sendMessage(SuccessText.style(SuccessTextStyle));
            }
        }
    }
}
