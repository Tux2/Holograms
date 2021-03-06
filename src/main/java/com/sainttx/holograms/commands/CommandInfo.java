package com.sainttx.holograms.commands;

import com.sainttx.holograms.HologramPlugin;
import com.sainttx.holograms.data.Hologram;
import com.sainttx.holograms.data.HologramLine;
import com.sainttx.holograms.util.TextUtil;
import com.sainttx.holograms.HologramPlugin;
import com.sainttx.holograms.data.Hologram;
import com.sainttx.holograms.data.HologramLine;
import com.sainttx.holograms.util.TextUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * Created by Matthew on 14/03/2015.
 */
public class CommandInfo implements CommandExecutor {

    private HologramPlugin plugin;

    public CommandInfo(HologramPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Usage: /hologram info <name>");
        } else {
            String hologramName = args[1];
            Hologram hologram = plugin.getHologramManager().getHologramByName(hologramName);

            if (hologram == null) {
                sender.sendMessage(ChatColor.RED + "Couldn't find a hologram with name \"" + hologramName + "\".");
            } else {
                sender.sendMessage(TextUtil.color("&7Hologram information for &f\"" + hologram.getName() + "\"&7:"));
                List<HologramLine> lines = hologram.getHologramLines();
                sender.sendMessage(TextUtil.color("&7Location: &f" + TextUtil.locationAsString(hologram.getLocation())));
                if (lines.isEmpty()) {
                    sender.sendMessage(ChatColor.GRAY + "Hologram has no lines.");
                } else {
                    sender.sendMessage(ChatColor.GRAY + "Lines:");
                    for (HologramLine line : lines) {
                        sender.sendMessage(" - \"" + line.getText() + ChatColor.WHITE + "\"");
                    }
                }
            }
        }

        return true;
    }
}