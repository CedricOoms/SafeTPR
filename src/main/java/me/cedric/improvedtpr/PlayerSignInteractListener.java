package me.cedric.improvedtpr;

import com.earth2me.essentials.ChargeException;
import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import com.earth2me.essentials.signs.EssentialsSign;
import com.earth2me.essentials.signs.SignException;
import net.ess3.api.IEssentials;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.block.Sign;

public class PlayerSignInteractListener implements Listener {
    Block RegisteredBlock;
    SignTPR signTPR = new SignTPR();
    IEssentials ess;
    EssentialsSign.ISign iSign;

    @EventHandler
    public void onPlayerClicksSign(PlayerInteractEvent event){
        Player p = event.getPlayer();
        String pString = p.toString();
        User user = new User(p,ess);
        RegisteredBlock = event.getClickedBlock();
        if (RegisteredBlock instanceof Sign){
            String FirstLine = ((Sign) RegisteredBlock).getLine(0);
            if (FirstLine == "TPR") {
                try {
                    signTPR.onSignInteract(iSign, user, pString, ess);
                } catch (SignException e) {
                    System.out.print("SignException");
                } catch (ChargeException e){
                    System.out.print("ChargeException");
                }
            }
        }
    }
}
