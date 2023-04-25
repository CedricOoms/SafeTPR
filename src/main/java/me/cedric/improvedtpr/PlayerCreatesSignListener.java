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
    @EventHandler
    public void OnPlayerCreatesSign(BlockPlaceEvent event){
        Player p = event.getPlayer();
        Block PlacedBlock = event.getBlock();
        if (PlacedBlock instanceof Sign){
            Sign sign = (Sign) PlacedBlock;
            String FirstLine = String.valueOf(sign.line(0));
            if (FirstLine == "[TPR]"){
                //sign.line(0,Color.BLUE + "[TPR]");
                p.sendPlainMessage("Successfully made TPR sign!");
            }
        }
    }
}
