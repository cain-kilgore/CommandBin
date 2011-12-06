package com.bloodymercy.bmcommandbin.listeners;

import com.bloodymercy.bmcommandbin.BMCommandBin;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.CreatureType;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PListener extends PlayerListener {

	static BMCommandBin derp;
	
	public void onPlayerMove(PlayerMoveEvent e)
	{
			if(BMCommandBin.plugin.getConfig().getBoolean(e.getPlayer().getName() + ".frozen"))
			{
				e.setCancelled(true);
			}
			
			if(BMCommandBin.plugin.getConfig().getBoolean(e.getPlayer().getName() + ".smoke"))
			{
				e.getPlayer().getWorld().playEffect(e.getPlayer().getLocation(), Effect.SMOKE, 5);
			}
		
			if(BMCommandBin.plugin.getConfig().getBoolean(e.getPlayer().getName() + ".snowman"))
			{
				if(!(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN, 1).getType() == Material.AIR))
				{
				e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN, 0).setType(Material.SNOW);
				}
				
				
				if((e.getPlayer()).getLocation().getBlock().getRelative(BlockFace.DOWN, 1).getType() == Material.WATER) {
					Block b = e.getPlayer().getLocation().getBlock().getChunk().getBlock(e.getPlayer().getLocation().getBlockX(), e.getPlayer().getLocation().getBlockY()-1, e.getPlayer().getLocation().getBlockZ());
					b.setType(Material.ICE);
				}
				
				
				if(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN, 1).getType() == Material.SNOW)
				{
					e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN, 1).setType(Material.SNOW_BLOCK);
				}
			}
			
		return;
	}
	
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e)
	{
			if(BMCommandBin.plugin.getConfig().getBoolean(e.getPlayer().getName() + ".handicapped"))
			{
				e.getPlayer().sendMessage(ChatColor.RED + BMCommandBin.plugin.getConfig().getString("settings.playercannotusecommands"));
				e.setCancelled(true);
			}
			
			if(BMCommandBin.plugin.getConfig().getBoolean("jail.players." + e.getPlayer().getName()))
			{
				e.setCancelled(true);
				e.getPlayer().sendMessage(ChatColor.RED + "You are jailed. Stop trying to use commands!");
			}
			return;
	}
	
	public void onPlayerChat(PlayerChatEvent e)
	{
			if(BMCommandBin.plugin.getConfig().getBoolean(e.getPlayer().getName() + ".muted"))
			{
				e.getPlayer().sendMessage(ChatColor.RED + BMCommandBin.plugin.getConfig().getString("settings.playerismuted"));
				e.setCancelled(true);
			}
			
			/*if(CommandBin.plugin.getConfig().getBoolean("settings.customchat"))
			{
				String message = e.getMessage();
	            String lol = message.replaceAll("(&([a-f0-9]))", "§$2");
	            e.setCancelled(true);
	            String test = CommandBin.permissionHandler.getUserPrefix(e.getPlayer().getWorld().getName(), e.getPlayer().getName());
	            Bukkit.getServer().broadcastMessage("[" + test + "] " + e.getPlayer().getName() + ": " + lol);
			}
			*/
			return;
	}
		
	
	public void onPlayerInteract(PlayerInteractEvent e)
	{
		if(e.getAction() == Action.RIGHT_CLICK_AIR)
		{
				if(BMCommandBin.plugin.getConfig().getBoolean(e.getPlayer().getName() + ".explosionstick"))
				{
					if(e.getPlayer().getItemInHand().getType() == Material.STICK)
					{
						Location block = e.getPlayer().getTargetBlock(null, 0).getLocation();
						e.getPlayer().getWorld().createExplosion(block, 8);
					}
				}

				if(BMCommandBin.plugin.getConfig().getBoolean(e.getPlayer().getName() + ".lightningstick"))
				{
					if(e.getPlayer().getItemInHand().getType() == Material.STICK)
					{
						Location block = e.getPlayer().getTargetBlock(null, 0).getLocation();
						e.getPlayer().getWorld().strikeLightning(block);
					}
				}
		}
		
		if(e.getAction() == Action.LEFT_CLICK_BLOCK)
		{
			if(BMCommandBin.plugin.getConfig().getBoolean("settings.spawn-enderdragon-on-hitting-enderegg"))
			{
				if(e.getClickedBlock().getType() == Material.DRAGON_EGG)
				{ // Full Maven Support! :) 
					Location dragon = e.getClickedBlock().getLocation();
					e.getPlayer().getWorld().spawnCreature(dragon, CreatureType.ENDER_DRAGON);
					e.getPlayer().sendMessage(ChatColor.RED + "You have made the Ender Dragon arise from hell!");
					e.getClickedBlock().setType(Material.AIR);
				}
			}
		}
		
		if(BMCommandBin.plugin.getConfig().getBoolean(e.getPlayer().getName() + ".frozen"))
		{
			e.setCancelled(true);
		}
		return;
	}
	
	public void onPlayerLogin(PlayerLoginEvent e)
	{
			if(BMCommandBin.plugin.getConfig().getBoolean(e.getPlayer().getName() + ".banned"))
			{
				e.setKickMessage(BMCommandBin.plugin.getConfig().get(e.getPlayer().getName() + ".banreason").toString());
				e.disallow(Result.KICK_BANNED, "You were banned for: " + ChatColor.GRAY + BMCommandBin.plugin.getConfig().get(e.getPlayer().getName() + ".banreason").toString());
			}
			return;
	}
	
	public void onPlayerJoin(PlayerJoinEvent e)
	{
		if(BMCommandBin.plugin.getConfig().get("settings.joinmessage") != null)
		{
			e.setJoinMessage(ChatColor.YELLOW + e.getPlayer().getName() + " " + BMCommandBin.plugin.getConfig().get("settings.joinmessage").toString());
		}
		
		if(BMCommandBin.plugin.getConfig().get(e.getPlayer().getName() + ".nickname") != null)
		{
			e.getPlayer().setDisplayName(BMCommandBin.plugin.getConfig().getString(e.getPlayer().getName() + ".nickname"));
		}
		
		String motd = BMCommandBin.plugin.getConfig().getString("settings.message-of-the-day");
		for(String str : motd.split("/break"))
		{
			e.getPlayer().sendMessage(str.replace("[p]", e.getPlayer().getName()));
		}
		

		
		if(BMCommandBin.plugin.getConfig().getBoolean("bannedips." + e.getPlayer().getAddress().getAddress().getHostAddress().toString().replace(".", "")))
		{
			e.getPlayer().kickPlayer("You have been IP banned from this server");
			e.setJoinMessage("");
			e.getPlayer().sendMessage("your ip is banned ;O");
		}
		
		if(BMCommandBin.plugin.pCheck(e.getPlayer(), "CommandBin.other.strikeonjoin"))
		{
			if(BMCommandBin.plugin.getConfig().getBoolean("settings.lightningonjoin"))
			{
				e.getPlayer().getWorld().strikeLightningEffect(e.getPlayer().getLocation());
			}
		}
		
		return;
	}
	
	public void onPlayerQuit(PlayerQuitEvent e)
	{
		if(BMCommandBin.plugin.getConfig().get("settings.leavemessage") != null)
		{
			e.setQuitMessage(ChatColor.YELLOW + e.getPlayer().getName() + " " + BMCommandBin.plugin.getConfig().get("settings.leavemessage").toString());
		}
		return;
	}
	
	public void onPlayerRespawn(PlayerRespawnEvent e)
	{
		e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
		return;
	}
	
	public void onPlayerEggThrow(PlayerEggThrowEvent e)
	{
		if(BMCommandBin.plugin.getConfig().getBoolean("settings.teleportonthrowegg"))
		{
			if(BMCommandBin.plugin.pCheck(e.getPlayer(), "CommandBin.general.canteleportonthrowegg"))
			{
				e.getPlayer().teleport(e.getEgg().getLocation());
				e.getPlayer().sendMessage(ChatColor.GREEN + "Teleported to the egg!");
			}
		}
		return;
	}
}
