package me.cedric.improvedtpr;

import com.earth2me.essentials.ChargeException;
import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import com.earth2me.essentials.signs.EssentialsSign;
import com.earth2me.essentials.signs.SignException;
import net.ess3.api.IEssentials;
import net.kyori.adventure.text.Component;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.block.Sign;

public class PlayerSignInteractListener implements Listener {
    Component text= Component.text("[TPR]");
    @EventHandler
    public void onPlayerClicksSign(PlayerInteractEvent event){
        Player p = event.getPlayer();
        Block RegisteredBlock = event.getClickedBlock();
        if (RegisteredBlock instanceof org.bukkit.block.Sign){
            String FirstLine = String.valueOf(((Sign) RegisteredBlock).line(0));
            if (FirstLine.equalsIgnoreCase("[TPR]")) {
                p.sendPlainMessage("Teleporting to random location now!");
                FindLoc findLoc = new FindLoc();
                p.teleport(findLoc.getRandLoc());
            }
        }
    }
}
