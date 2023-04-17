package io.paper.futils.features;

import com.golfing8.kore.FactionsKore;
import com.golfing8.kore.feature.PrinterFeature;
import io.paper.futils.FUtils;
import me.frep.vulcan.api.event.VulcanFlagEvent;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PrinterBypassFeature implements Listener {

    private final List<String> bypassAreas = FUtils.printerbypass.getStringList("printer.worldbypass");
    private final List<String> fastPlaceChecks = FUtils.flylimiter.getStringList("printer.fastplacecheck");
    private final List<String> scaffoldChecks = FUtils.flylimiter.getStringList("printer.scaffoldcheck");
    private final List<String> invalidChecks = FUtils.flylimiter.getStringList("printer.invalidcheck");
    private final List<String> badpacketsChecks = FUtils.flylimiter.getStringList("printer.badpacketscheck");

    private final PrinterFeature printer = FactionsKore.get().getFeature(PrinterFeature.class);
    private final Material prismarine = Material.PRISMARINE;

    @EventHandler
    public void onPrinter(VulcanFlagEvent e){
        if (printer.isInPrinter(e.getPlayer())){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlace(VulcanFlagEvent e){
        if(FUtils.printerbypass.getBoolean("printer.arenabypass")){
            if (fastPlaceChecks.contains(e.getCheck().getName())){
                if (e.getPlayer().getItemInHand() == null){
                    return;
                }
                ItemStack itemInHand = e.getPlayer().getItemInHand();
                if (itemInHand.getType().equals(prismarine) && itemInHand.hasItemMeta()){
                    System.out.println("flag: "+ e.getCheck() + "| " + e.getCheck().getName());
                }
            }
            if(scaffoldChecks.contains(e.getCheck().getName())){
                if(e.getPlayer().getItemInHand() != null){
                    return;
                }
                ItemStack itemInHand = e.getPlayer().getItemInHand();
                if(itemInHand.getType().equals(prismarine) && itemInHand.hasItemMeta()){
                    System.out.println("flag: "+ e.getCheck() + "| " + e.getCheck().getName());
                }
            }
            if(invalidChecks.contains(e.getCheck().getName())){
                if(e.getPlayer().getItemInHand() != null){
                    return;
                }
                ItemStack itemInHand = e.getPlayer().getItemInHand();
                if(itemInHand.getType().equals(prismarine) && itemInHand.hasItemMeta()){
                    System.out.println("flag: "+ e.getCheck() + "| " + e.getCheck().getName());
                }
            }
            if(badpacketsChecks.contains(e.getCheck().getName())){
                if(e.getPlayer().getItemInHand() != null){
                    return;
                }
                ItemStack itemInHand = e.getPlayer().getItemInHand();
                if(itemInHand.getType().equals(prismarine) && itemInHand.hasItemMeta()){
                    System.out.println("flag: "+ e.getCheck() + "| " + e.getCheck().getName());
                }
            }
        }
    }
    @EventHandler
    public void onWorld(VulcanFlagEvent e){
        if(FUtils.printerbypass.getBoolean("printer.enabled")){
            System.out.println("world: " + e.getPlayer().getLocation().getWorld().getName());
            if (bypassAreas.contains(e.getPlayer().getLocation().getWorld().getName())){
                e.setCancelled(true);
            }
        }
    }

}
