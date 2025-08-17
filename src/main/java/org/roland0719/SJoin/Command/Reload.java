package org.roland0719.SJoin.Command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.roland0719.SJoin.SJoin;

public class Reload implements CommandExecutor, TabCompleter {
    private final SJoin plugin;

    public Reload(SJoin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("sjoin")) {
            FileConfiguration messages = this.plugin.getMessages();
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("sjoin.reload")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("no-permission", "&cYou don't have permission to use this command!")));
                    return true;
                } else {
                    this.plugin.reloadConfig();
                    this.plugin.reloadMessages();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("reload-success", "&aSJoin configuration reloaded successfully!")));
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("usage", "&eUsage: /sjoin reload")));
                return true;
            }
        } else {
            return false;
        }
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("sjoin") && args.length == 1) {
            List<String> completions = new ArrayList();
            if (sender.hasPermission("sjoin.reload")) {
                completions.add("reload");
            }

            return completions;
        } else {
            return Collections.emptyList();
        }
    }
}
