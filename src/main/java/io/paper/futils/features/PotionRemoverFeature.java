package io.paper.futils.features;

import com.golfing8.kore.util.XMaterial;
import io.paper.futils.FUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import static io.paper.futils.FUtils.plugin;

public class PotionRemoverFeature implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onConsume(PlayerItemConsumeEvent event) {
        if(FUtils.potionremower.getBoolean("bottlerecyle.enabled")){
            new BukkitRunnable() {
                public void run() {
                    if (event.getItem() == null) {
                        return;
                    }
                    if (event.getItem().getType() != XMaterial.POTION.parseMaterial()) {
                        return;
                    }
                    if (event.getPlayer().getItemInHand().getType() != Material.GLASS_BOTTLE || event.getPlayer().getItemInHand().getAmount() != 1) {
                        for (int i = 0; i < event.getPlayer().getInventory().getSize(); ++i) {
                            final ItemStack itemStack = event.getPlayer().getInventory().getItem(i);
                            if (itemStack != null && itemStack.getType() == Material.GLASS_BOTTLE) {
                                if (itemStack.getAmount() > 1) {
                                    itemStack.setAmount(itemStack.getAmount() - 1);
                                }
                                else {
                                    event.getPlayer().getInventory().setItem(i, (ItemStack)null);
                                }
                                return;
                            }
                        }
                        return;
                    }
                    event.getPlayer().setItemInHand((ItemStack)null);
                }
            }.runTask(plugin);
        }
    }
}
