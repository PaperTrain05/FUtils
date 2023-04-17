package io.paper.futils;

import io.paper.futils.commands.FUtilsCommand;
import io.paper.futils.features.*;
import me.frep.vulcan.api.VulcanAPI;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class FUtils extends JavaPlugin {

    public static FileConfiguration printerbypass;
    public static FileConfiguration dragdropspwaner;
    public static FileConfiguration flylimiter;
    public static FileConfiguration punchfeature;
    public static FileConfiguration potionremower;
    public static JavaPlugin rawplugin;
    public static Plugin plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        rawplugin = this;
        printerbypass = createFileConfig("printerbypass.yml");
        dragdropspwaner = createFileConfig("dragdropspwaner.yml");
        flylimiter = createFileConfig("flylimiter.yml");
        punchfeature = createFileConfig("punchfeature.yml");
        potionremower = createFileConfig("potionremower.yml");

        this.getCommand("futils").setExecutor(new FUtilsCommand());
        Listeners();
    }
    public void Listeners(){
        this.getServer().getPluginManager().registerEvents(new ArrowRemoveFeature(), this);
        this.getServer().getPluginManager().registerEvents(new DragDropFeature(), this);
        this.getServer().getPluginManager().registerEvents(new FlyLimiterFeature(), this);
        this.getServer().getPluginManager().registerEvents(new PotionRemoverFeature(), this);
        //this.getServer().getPluginManager().registerEvents(new PrinterBypassFeature(), this);
        this.getServer().getPluginManager().registerEvents(new PunchFeature(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static FileConfiguration createFileConfig(String fileName) {
        File configFile = new File(plugin.getDataFolder(), fileName);

        if(!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            plugin.saveResource(fileName, false);
        }

        FileConfiguration configuration = new YamlConfiguration();

        try {
            configuration.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        return configuration;
    }

    public static void saveMainConfig(FileConfiguration config, String fileName) {
        File configFile = new File(FUtils.plugin.getDataFolder(), fileName);
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
