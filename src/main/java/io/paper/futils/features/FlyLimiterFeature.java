package io.paper.futils.features;

import io.paper.futils.FUtils;
import me.frep.vulcan.api.VulcanAPI;
import me.frep.vulcan.spigot.VulcanPlugin;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class FlyLimiterFeature implements Listener {

    private final double maxFlySpeed = FUtils.flylimiter.getDouble("flylimiter.maxflyspeed");
    private VulcanAPI api;

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onMove(PlayerMoveEvent event) {
        if(FUtils.flylimiter.getBoolean("flylimiter.enabled")){
            if(event.getPlayer().hasPermission("flylimiter.bypass")){
                return;
            }
            if (event.getPlayer().isInsideVehicle()) {
                return;
            }
            if (!event.getPlayer().isFlying()) {
                return;
            }
            final Location to = event.getTo();
            final Location from = event.getFrom();
            if (to.getBlockX() != from.getBlockX() || to.getBlockZ() != from.getBlockZ() || to.getBlockY() != from.getBlockY()) {
                double horizontalDistance = Math.hypot(from.getX() - to.getX(), from.getZ() - to.getZ());
                double verticalDistance = Math.abs(from.getY() - to.getY());
                String check = "Fly Limiter";
                String type = "A";
                String hd = "Horizonta distance" + horizontalDistance + " Vertical distance" + verticalDistance + " Max flyspeed" + this.maxFlySpeed;
                if (horizontalDistance > this.maxFlySpeed || verticalDistance > this.maxFlySpeed) {
                    event.setTo(from);
                }
            }
        }
    }
}
