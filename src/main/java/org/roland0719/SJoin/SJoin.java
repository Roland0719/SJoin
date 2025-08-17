package org.roland0719.SJoin;

import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.roland0719.SJoin.Command.Reload;
import org.roland0719.SJoin.Listeners.Join;
import org.roland0719.SJoin.Listeners.Leave;

public final class SJoin extends JavaPlugin implements Listener {
    private FileConfiguration messages;
    private File messagesFile;

    public void onEnable() {
        this.saveDefaultMessages();
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getPluginManager().registerEvents(new Join(this.messages), this);
        this.getServer().getPluginManager().registerEvents(new Leave(this.messages), this);
        Reload reloadCommand = new Reload(this);
        this.getCommand("sjoin").setExecutor(reloadCommand);
        this.getCommand("sjoin").setTabCompleter(reloadCommand);
        this.getLogger().info("SJoin » The plugin successfully started.");
    }

    public void onDisable() {
        this.getLogger().info("SJoin » Plugin failed to load!");
    }

    private void saveDefaultMessages() {
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdirs();
        }

        this.messagesFile = new File(this.getDataFolder(), "messages.yml");
        if (!this.messagesFile.exists()) {
            this.saveResource("messages.yml", false);
        }

        this.messages = YamlConfiguration.loadConfiguration(this.messagesFile);
    }

    public void reloadMessages() {
        this.messages = YamlConfiguration.loadConfiguration(this.messagesFile);
    }

    public FileConfiguration getMessages() {
        return this.messages;
    }
}
