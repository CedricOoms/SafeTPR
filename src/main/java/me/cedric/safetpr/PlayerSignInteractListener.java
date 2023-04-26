package me.cedric.safetpr;

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
    Component TpText = Component.text("Searching for a suitable location...");
    Component SuccessText = Component.text("You have been teleported to a random new location, no other players close-by. Good luck!");
    Style style = Style.style(TextColor.color(0xBF7));
    Style SuccessTextStyle = Style.style(TextColor.color(0xFF0012));
    @EventHandler
    public void onPlayerClicksSign(PlayerInteractEvent event){
        Player p = event.getPlayer();
        Block RegisteredBlock = event.getClickedBlock();
        World world = p.getWorld();
        if ( RegisteredBlock.getState() instanceof  Sign ){
            Sign sign = (Sign) RegisteredBlock.getState();
            Component FirstLine = sign.line(0);
            if (FirstLine.equals(text.style(style))) {
                p.sendMessage(TpText.style(style));
                FindLoc findLoc = new FindLoc();
                p.teleport(findLoc.getRandLoc(p,world));
                p.sendPlainMessage("");
                p.sendMessage(SuccessText.style(SuccessTextStyle));
            }
        }
    }
}
