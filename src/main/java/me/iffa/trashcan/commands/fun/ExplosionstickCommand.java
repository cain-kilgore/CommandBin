// Package Declaration
package me.iffa.trashcan.commands.fun;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /explosionstick.
 * TrashCan license: http://creativecommons.org/licenses/by-nc-nd/3.0/
 * 
 * @author iffamies
 */
public class ExplosionstickCommand extends TrashCommand {
    /**
     * Constructor of ExplosionstickCommand.
     * 
     * @param label Command label
     */
    public ExplosionstickCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (cs.hasPermission("trashcan.fun.explosionstick")) {
            if (!(cs instanceof Player)) {
                MessageUtil.sendMessage(cs, "Sorry, only players can use this command.");
                return true;
            }
            TrashCan.getConfigHandler().setExplosionstick(!TrashCan.getConfigHandler().getExplosionStick((Player)cs), (Player)cs);
            if (TrashCan.getConfigHandler().getExplosionStick((Player)cs)) {
                MessageUtil.sendMessage(cs, ChatColor.GOLD + "Explosionstick has been enabled for you!");
            } else {
                MessageUtil.sendMessage(cs, ChatColor.GOLD + "Explosionstick has been disabled for you!");
            }
            return true;
        } else {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
    }
}
