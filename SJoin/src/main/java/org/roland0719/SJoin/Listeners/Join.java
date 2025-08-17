package org.roland0719.SJoin.Listeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class Join implements Listener {
    private final FileConfiguration messages;

    public Join(FileConfiguration messages) {
        this.messages = messages;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (player.hasPlayedBefore()) {
            String rawJoinMsgChat = this.messages.getString("chat.join-message", "&8[&a+&8]&f %player%");
            rawJoinMsgChat = rawJoinMsgChat.replace("%player%", player.getDisplayName());
            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', rawJoinMsgChat));

            String rawJoinMsgActionbar = this.messages.getString("actionbar.join-message", "&8[&a+&8]&f %player%");
            rawJoinMsgActionbar = rawJoinMsgActionbar.replace("%player%", player.getDisplayName());
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', rawJoinMsgActionbar)));

        } else {
            int playerNumber = Bukkit.getOfflinePlayers().length;

            String rawFirstJoinChat = this.messages.getString("chat.first-join-message", "&8[&a+&8]&f %player% joined for the first time! They are player #%number%");
            rawFirstJoinChat = rawFirstJoinChat
                    .replace("%player%", player.getDisplayName())
                    .replace("%number%", String.valueOf(playerNumber));
            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', rawFirstJoinChat));

            String rawFirstJoinActionbar = this.messages.getString("actionbar.first-join-message", "&8[&a+&8]&f %player% joined for the first time! They are player #%number%");
            rawFirstJoinActionbar = rawFirstJoinActionbar
                    .replace("%player%", player.getDisplayName())
                    .replace("%number%", String.valueOf(playerNumber));
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.translateAlternateColorCodes('&', rawFirstJoinActionbar)));
        }
    }
}
