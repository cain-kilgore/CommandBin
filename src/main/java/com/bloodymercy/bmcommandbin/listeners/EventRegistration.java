package com.bloodymercy.bmcommandbin.listeners;

import com.bloodymercy.bmcommandbin.BMCommandBin;

import org.bukkit.Bukkit;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;

public class EventRegistration
{
	BMCommandBin events;
	
	public static void setup()
	{
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_MOVE, new PListener(), Priority.Normal, BMCommandBin.plugin);
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_INTERACT, new PListener(), Priority.Normal, BMCommandBin.plugin);
		Bukkit.getServer().getPluginManager().registerEvent(Type.ENTITY_DAMAGE, new EListener(), Priority.Normal, BMCommandBin.plugin);
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_COMMAND_PREPROCESS, new PListener(), Priority.Normal, BMCommandBin.plugin);
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_CHAT, new PListener(), Priority.Normal, BMCommandBin.plugin);
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_LOGIN, new PListener(), Priority.Normal, BMCommandBin.plugin);
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_JOIN, new PListener(), Priority.Normal, BMCommandBin.plugin);
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_QUIT, new PListener(), Priority.Normal, BMCommandBin.plugin);
		Bukkit.getServer().getPluginManager().registerEvent(Type.ENTITY_DEATH, new EListener(), Priority.Normal, BMCommandBin.plugin);
		Bukkit.getServer().getPluginManager().registerEvent(Type.ENDERMAN_PICKUP, new EListener(), Priority.Normal, BMCommandBin.plugin);
		Bukkit.getServer().getPluginManager().registerEvent(Type.ENDERMAN_PLACE, new EListener(), Priority.Normal, BMCommandBin.plugin);
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_TOGGLE_SPRINT, new PListener(), Priority.Normal, BMCommandBin.plugin);
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_RESPAWN, new PListener(), Priority.Normal, BMCommandBin.plugin);
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_GAME_MODE_CHANGE, new PListener(), Priority.Normal, BMCommandBin.plugin);
		Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_PLACE, new BListener(), Priority.Normal, BMCommandBin.plugin);
		Bukkit.getServer().getPluginManager().registerEvent(Type.BLOCK_BREAK, new BListener(), Priority.Normal, BMCommandBin.plugin);
		Bukkit.getServer().getPluginManager().registerEvent(Type.PLAYER_EGG_THROW, new PListener(), Priority.Normal, BMCommandBin.plugin);
		Bukkit.getServer().getPluginManager().registerEvent(Type.ENTITY_EXPLODE, new EListener(), Priority.Normal, BMCommandBin.plugin);
		Bukkit.getServer().getPluginManager().registerEvent(Type.PROJECTILE_HIT, new EListener(), Priority.Normal, BMCommandBin.plugin);
	}
}