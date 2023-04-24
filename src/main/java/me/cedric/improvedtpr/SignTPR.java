package me.cedric.improvedtpr;

import com.earth2me.essentials.ChargeException;
import com.earth2me.essentials.Trade;
import com.earth2me.essentials.User;
import com.earth2me.essentials.signs.EssentialsSign;
import com.earth2me.essentials.signs.SignException;
import net.ess3.api.IEssentials;
import org.bukkit.Location;
import org.bukkit.event.player.PlayerTeleportEvent;
import me.cedric.improvedtpr.FindLoc;

import java.util.concurrent.CompletableFuture;

import static com.earth2me.essentials.I18n.tl;

public class SignTPR extends EssentialsSign{
    FindLoc findLoc = new FindLoc();
    public SignTPR() {
        super("TPR");
    }

    @Override
    protected boolean onSignCreate(final ISign sign, final User player, final String username, final IEssentials ess) throws SignException {
        validateTrade(sign, 3, ess);
        final String group = sign.getLine(2);
        if ("Everyone".equalsIgnoreCase(group) || "Everybody".equalsIgnoreCase(group)) {
            sign.setLine(2, "§2Everyone");
        }
        return true;
    }

    @Override
    protected boolean onSignInteract(final ISign sign, final User player, final String username, final IEssentials ess) throws SignException, ChargeException {
        final String group = sign.getLine(2);

        if (!group.isEmpty()) {
            if (!"§2Everyone".equals(group) && !player.inGroup(group)) {
                throw new SignException(tl("warpUsePermission"));
            }
        } else {
            if (ess.getSettings().getPerWarpPermission()) {
                throw new SignException(tl("warpUsePermission"));
            }
        }

        final Trade charge = getTrade(sign, 3, ess);
        final CompletableFuture<Boolean> future = new CompletableFuture<>();
        //Teleport player to random location:
        findLoc.getRandLoc(); //Generate the new random position
        Location NewLoc = findLoc.NewLoc;
        player.getAsyncTeleport().teleportPlayer(player, NewLoc,charge, PlayerTeleportEvent.TeleportCause.PLUGIN, future);
        future.thenAccept(success -> {
            if (success) {
                Trade.log("Sign", "TPR", "Interact", username, null, username, charge, sign.getBlock().getLocation(), player.getMoney(), ess);
            }
        });
        future.exceptionally(e -> {
            ess.showError(player.getSource(), e, "\\ sign: " + signName);
            return false;
        });
        return true;
    }
}
