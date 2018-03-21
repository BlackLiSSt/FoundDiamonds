package FoundDiamonds.commands;

import java.util.Arrays;
import java.util.List;

import FoundDiamonds.Config;
import FoundDiamonds.Main;
import SpoutSDK.ChatColor;
import SpoutSDK.CraftCommand;
import SpoutSDK.CraftPlayer;

public class FoundDiamonds implements CraftCommand {

	@Override
	public List<String> getAliases() {
		return Arrays.asList("fd");
	}

	@Override
	public String getCommandName() {
		return "founddiamonds";
	}

	@Override
	public String getHelpLine(CraftPlayer plr) {
		return ChatColor.GOLD + "/fd " + ChatColor.WHITE + "--- FoundDiamonds command";
	}

	@Override
	public List<String> getTabCompletionList(CraftPlayer plr, String[] args) {
		return null;
	}

	@Override
	public void handleCommand(CraftPlayer plr, String[] args) {
		if(args[0].isEmpty()) {
			plr.sendMessage(" ");
			plr.sendMessage(ChatColor.GRAY + "[FD]: Running version " + ChatColor.GOLD + Main.version + ChatColor.GRAY + "!");
			plr.sendMessage(" ");
		}
		if(args[0].equalsIgnoreCase("help")) {
			sendCommandList(plr);
		} else if(args[0].equalsIgnoreCase("reload")) {
			if(plr.hasPermission("fd.reload")) {
				plr.sendMessage(" ");
				plr.sendMessage(ChatColor.GRAY + "[FD]: Reloaded configuration.");
				plr.sendMessage(" ");
				Config.readConfig();
			}
		} else {
			sendCommandList(plr);
		}
		
	}

	@Override
	public boolean hasPermissionToUse(CraftPlayer plr) {
		return true;
	}
	
	public static void sendCommandList(CraftPlayer plr) {
		plr.sendMessage(" ");
		plr.sendMessage(ChatColor.GRAY + "FoundDiamonds command list");
		plr.sendMessage(ChatColor.GOLD + "/fd " + ChatColor.WHITE + "--- Version Info");
		plr.sendMessage(ChatColor.GOLD + "/fd help " + ChatColor.WHITE + "--- Show Commands");
		plr.sendMessage(ChatColor.GOLD + "/fd reload " + ChatColor.WHITE + "--- Reload Configuration");
		plr.sendMessage(" ");
	}

}
