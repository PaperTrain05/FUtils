package io.paper.futils.commands;

import io.paper.futils.FUtils;
import io.paper.futils.utils.Format;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FUtilsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){

            Player p = (Player) sender;
            p.sendMessage("");
            p.sendMessage(Format.color("&c&lFUtils by &e&lPaperTrain&7, Versione: &c1.0.0 STABLE"));
            p.sendMessage(Format.color("&c&lFeatures"));
            p.sendMessage(Format.color("&7Arrow remover: "+ FUtils.punchfeature.getBoolean("punch.enabled")));
            p.sendMessage(Format.color("&7Punch: "+ FUtils.punchfeature.getBoolean("punch.enabled")));
            p.sendMessage(Format.color("&7DragDrop: " + FUtils.dragdropspwaner.getBoolean("dragdropspwaner.enabled")));
            p.sendMessage(Format.color("&7FlyLimiter: "+ FUtils.flylimiter.getBoolean("flylimiter.enabled") + Format.color(" &eSpeed MAX: ") + FUtils.flylimiter.getDouble("flylimiter.speed")));
            p.sendMessage(Format.color("&7Potion remover: " + FUtils.potionremower.getBoolean("potionremower.enabled")));
            p.sendMessage(Format.color("&7Printer: ") + FUtils.printerbypass.getBoolean("printer.enabled"));
            p.sendMessage("");

        }else {
            sender.sendMessage("Only players can use this command!");
        }
        return true;
    }
}
