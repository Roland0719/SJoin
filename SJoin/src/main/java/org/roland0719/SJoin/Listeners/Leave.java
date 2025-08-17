package org.roland0719.SJoin.Listeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public final class Leave implements Listener {
    private FileConfiguration messages;

    public Leave(FileConfiguration messages) {
        this.messages = messages;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        String rawQuitMsgChat = this.messages.getString("chat.quit-message", "§8[§c-§8]§f %player%");
        rawQuitMsgChat = rawQuitMsgChat.replace("%player%", player.getDisplayName());
        e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', rawQuitMsgChat));

        String rawQuitMsgActionbar = this.messages.getString("actionbar.quit-message", "§8[§c-§8]§f %player%");
        rawQuitMsgActionbar = rawQuitMsgActionbar.replace("%player%", player.getDisplayName());
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', rawQuitMsgActionbar)));
    }
}
