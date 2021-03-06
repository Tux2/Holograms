package com.sainttx.holograms.commands;

import com.sainttx.holograms.HologramPlugin;
import com.sainttx.holograms.data.Hologram;
import com.sainttx.holograms.util.TextUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Matthew on 14/03/2015.
 */
public class CommandAddLine implements CommandExecutor {

    private HologramPlugin plugin;

    public CommandAddLine(HologramPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 3) {
            sender.sendMessage(ChatColor.RED + "Usage: /hologram addline <name> <text>");
        } else {
            String hologramName = args[1];
            Hologram hologram = plugin.getHologramManager().getHologramByName(hologramName);

            if (hologram == null) {
                sender.sendMessage(ChatColor.RED + "Couldn't find a hologram with name \"" + hologramName + "\".");
            } else {
                String text = TextUtil.implode(2, args);
                hologram.addLine(text);
                hologram.refreshAll();
                sender.sendMessage(TextUtil.color("&7Added line &f\"" + text + "\" &7to hologram &f\"" + hologram.getName() + "&f\"."));
            }
        }

        return true;
    }
}
