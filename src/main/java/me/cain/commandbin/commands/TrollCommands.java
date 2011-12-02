package main.java.me.cain.commandbin.commands;

import main.java.me.cain.commandbin.CommandBin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TrollCommands implements CommandExecutor
{
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		if(l.equalsIgnoreCase("troll"))
		{
			if(args.length < 1)
			{
				s.sendMessage("/troll [player]");
			} else {
				if(s instanceof Player)
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(CommandBin.plugin.pCheck(((Player) s), "CommandBin.general.troll"))
					{
						if(target != null) {
							target.getWorld().dropItemNaturally(target.getLocation(), new ItemStack(Material.DIAMOND, 0));
							s.sendMessage(ChatColor.GREEN + "You trolled " + target.getName());
						} else {
						s.sendMessage(CommandBin.plugin.PlayerOffline);
					}
					} else {
						s.sendMessage(CommandBin.plugin.NoPermission);
					}
				} else {
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null) {
						target.getWorld().dropItemNaturally(target.getLocation(), new ItemStack(Material.DIAMOND, 0));
						s.sendMessage(ChatColor.GREEN + "You trolled " + target.getName());
					}
				}
			}
		}
		return false;
	}

}