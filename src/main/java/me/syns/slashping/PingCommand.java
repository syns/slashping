package me.syns.slashping;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.command.*;
import net.minecraft.util.*;
import java.util.*;


public class PingCommand extends CommandBase
{
    public List<String> getCommandAliases() {
        return Arrays.asList("ping");
    }

    public String getCommandName() {
        return "ping";
    }

    public int getRequiredPermissionLevel() {
        return -1;
    }

    public String getCommandUsage(final ICommandSender sender) {
        return "";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        String name = (args.length == 1) ? args[0] : Minecraft.getMinecraft().getSession().getUsername();
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE.toString() + name + EnumChatFormatting.DARK_PURPLE.toString() + " has a ping of " + EnumChatFormatting.LIGHT_PURPLE.toString() + getPing(name) + "ms"));
    }

    public List<String> addTabCompletionOptions(final ICommandSender sender, final String[] args, final BlockPos pos) {
        return (List<String>)((args.length >= 1) ? getListOfStringsMatchingLastWord(args, OnlinePlayers.getListOfPlayerUsernames()) : null);
    }

    private NetworkPlayerInfo getPlayerInfo(String name) {
        Collection<NetworkPlayerInfo> map = Minecraft.getMinecraft().getNetHandler().getPlayerInfoMap();
        return map.stream().filter(networkPlayerInfo -> networkPlayerInfo.getGameProfile().getName().equals(name)).findFirst().orElse(null);
    }

    private String getPing(String ign) {
        NetworkPlayerInfo networkPlayerInfo = getPlayerInfo(ign);
        if (networkPlayerInfo == null) {
            return "Offline";
    }

        return String.valueOf(networkPlayerInfo.getResponseTime());
    }
}
