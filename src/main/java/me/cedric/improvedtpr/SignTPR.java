package me.cedric.improvedtpr;

import com.earth2me.essentials.ChargeException;
import com.earth2me.essentials.Trade;
import com.earth2me.essentials.User;
import com.earth2me.essentials.signs.EssentialsSign;
import com.earth2me.essentials.signs.SignException;
import net.ess3.api.IEssentials;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.concurrent.CompletableFuture;

import static com.earth2me.essentials.I18n.tl;

public class SignTPR extends EssentialsSign{
    public SignTPR() {
        super("TPR");
    }

    @Override
    protected boolean onSignCreate(final ISign sign, final User player, final String username, final IEssentials ess) throws SignException {
        validateTrade(sign, 3, ess);
        final String warpName = sign.getLine(1);

        if (warpName.isEmpty()) {
            sign.setLine(1, "§c<Warp name>");
            throw new SignException(tl("invalidSignLine", 1));
        } else {
            try {
                ess.getWarps().getWarp(warpName);
            } catch (final Exception ex) {
                throw new SignException(ex.getMessage(), ex);
            }
            final String group = sign.getLine(2);
            if ("Everyone".equalsIgnoreCase(group) || "Everybody".equalsIgnoreCase(group)) {
                sign.setLine(2, "§2Everyone");
            }
            return true;
        }
    }

    @Override
    protected boolean onSignInteract(final ISign sign, final User player, final String username, final IEssentials ess) throws SignException, ChargeException {
        final String warpName = sign.getLine(1);
        final String group = sign.getLine(2);

        if (!group.isEmpty()) {
            if (!"§2Everyone".equals(group) && !player.inGroup(group)) {
                throw new SignException(tl("warpUsePermission"));
            }
        } else {
            if (ess.getSettings().getPerWarpPermission() && !player.isAuthorized("essentials.warps." + warpName)) {
                throw new SignException(tl("warpUsePermission"));
            }
        }

        final Trade charge = getTrade(sign, 3, ess);
        final CompletableFuture<Boolean> future = new CompletableFuture<>();
        player.getAsyncTeleport().warp(player, warpName, charge, PlayerTeleportEvent.TeleportCause.PLUGIN, future);
        future.thenAccept(success -> {
            if (success) {
                Trade.log("Sign", "Warp", "Interact", username, null, username, charge, sign.getBlock().getLocation(), player.getMoney(), ess);
            }
        });
        future.exceptionally(e -> {
            ess.showError(player.getSource(), e, "\\ sign: " + signName);
            return false;
        });
        return true;
    }
}
