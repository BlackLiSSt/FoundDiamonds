package FoundDiamonds;

import java.util.ArrayList;
import java.util.List;

import FoundDiamonds.commands.FoundDiamonds;

import SpoutSDK.BlockHelper;
import SpoutSDK.CraftBlock;
import SpoutSDK.CraftLocation;
import SpoutSDK.CraftPlayer;
import SpoutSDK.CraftServer;
import SpoutSDK.ModBase;
import SpoutSDK.ModInfo;
import SpoutSDK.SpoutHelper;

public class Main extends ModBase {
	public static String modName = "FoundDiamonds";
	public static String version = "1.0.0";
	// Config options
	public static Boolean alertAll;
	public static String notification;
	public static String enabledMessage;
	public static String disabledMessage;
	public static List<Integer> blocks = new ArrayList<>();
	
	public void onStartup(CraftServer argServer) {
		System.out.println("[FoundDiamonds/INFO]: FoundDiamonds version " + version + " starting up...");
		Config.readConfig();
		SpoutHelper.getServer().registerCommand(new FoundDiamonds());
	}
	
    public ModInfo getModInfo() {
		ModInfo info = new ModInfo();
		info.description = "Notify people when certain blocks are mined (" + version + ")";
		info.name = modName;
		info.version = version;
		return info;
    }
    
    public void onBlockBroke(CraftPlayer plr, CraftLocation loc, CraftBlock blk) {
    	if(blocks.contains(blk.getId())) {
    		System.out.println("[FoundDiamonds/INFO]: " + plr.getName() + " mined " + BlockHelper.getBlockFriendlyName(blk.getId(), blk.getSubtype()).toUpperCase() + " at " + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ() + "!");
        	String message = notification.replace("%player%", plr.getName()).replace("%displayname%", plr.getCustomName()).replace("%ore%", BlockHelper.getBlockFriendlyName(blk.getId(), blk.getSubtype()).toUpperCase());
        	if(alertAll) {
        	   	SpoutHelper.getServer().broadcastMessage(message);
        	} else {
        	   	for(CraftPlayer player : SpoutHelper.getServer().getPlayers()) {
        	   		if(player.hasPermission("fd.notify")) {
        	   			player.sendMessage(message);
        	   		}
        	   	}
        	}
    	}

    }
    

}
