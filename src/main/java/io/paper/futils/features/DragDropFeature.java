package io.paper.futils.features;

import com.golfing8.kore.util.XMaterial;
import io.paper.futils.FUtils;
import io.paper.futils.utils.Format;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class DragDropFeature implements Listener {

    private Material mobSpawner;
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(FUtils.dragdropspwaner.getBoolean("dragdropspwaner.enabled")){
            if (event.getWhoClicked().getGameMode().equals((Object) GameMode.SURVIVAL)) {
                final Inventory openInv = event.getWhoClicked().getOpenInventory().getTopInventory();
                if (openInv.getType() != InventoryType.CRAFTING) {
                    if (event.getClick() == ClickType.NUMBER_KEY) {
                        ItemStack keyedItem = event.getWhoClicked().getInventory().getItem(event.getHotbarButton());
                        if (keyedItem != null && keyedItem.getType() == XMaterial.SPAWNER.parseMaterial()) {
                            event.setCancelled(true);
                            return;
                        }
                    }
                    if (event.getCurrentItem() != null && event.getCurrentItem().getType() == XMaterial.SPAWNER.parseMaterial()) {
                        event.setCancelled(true);
                    }
                    else if (event.getCursor() != null && event.getCursor().getType() == XMaterial.SPAWNER.parseMaterial()) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if(FUtils.dragdropspwaner.getBoolean("dragdropspwaner.enabled")){
            Player p = e.getPlayer();
            if (e.getItemDrop().getItemStack().getData().getItemType().equals((Object)this.mobSpawner)) {
                e.setCancelled(true);
                p.sendMessage(Format.color(FUtils.dragdropspwaner.getString("dragdropspwaner.not-drop-spawner")));
            }
        }
    }
}
