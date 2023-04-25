package me.cedric.improvedtpr;

import net.kyori.adventure.text.TextComponent;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.block.Block;
import org.bukkit.Color;
import net.kyori.adventure.text.Component;

public class PlayerCreatesSignListener implements Listener {
    Component text= Component.text("[TPR]");
    @EventHandler
    public void OnPlayerCreatesSign(BlockPlaceEvent event){
        Player p = event.getPlayer();
        Block PlacedBlock = event.getBlock();
        if (PlacedBlock instanceof Sign){
            Sign sign = (Sign) PlacedBlock;
            p.sendPlainMessage("It's a sign bro");
            Component FirstLine = sign.line(0);
            if ( text == FirstLine ){
                //sign.line(0,Color.BLUE + "[TPR]");
                p.sendPlainMessage("Successfully made TPR sign!");
            }
        }
    }
}
