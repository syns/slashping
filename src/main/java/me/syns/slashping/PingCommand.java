package me.syns.slashping;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.Collection;
import java.util.List;

public class PingCommand extends CommandBase {
    public String getCommandName() {
        return "ping";
    }

    public int getRequiredPermissionLevel() {
        return -1;
    }

    public String getCommandUsage(final ICommandSender sender) {
        return "/ping <user>";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        String name = (args.length == 1) ? args[0] : Minecraft.getMinecraft().getSession().getUsername();
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY.toString() + name + ": " + getPing(name)));
    }

    public List<String> addTabCompletionOptions(final ICommandSender sender, final String[] args, final BlockPos pos) {
        return (args.length == 1) ? getListOfStringsMatchingLastWord(args, OnlinePlayers.getListOfPlayerUsernames()) : null;
    }

    private NetworkPlayerInfo getPlayerInfo(String name) {
        Collection<NetworkPlayerInfo> map = Minecraft.getMinecraft().getNetHandler().getPlayerInfoMap();
        return map.stream().filter(networkPlayerInfo -> networkPlayerInfo.getGameProfile().getName().equals(name)).findFirst().orElse(null);
    }

    private int getPing(String ign) {
        NetworkPlayerInfo networkPlayerInfo = getPlayerInfo(ign);
        if (networkPlayerInfo == null) {
            return -1;
        }

        return networkPlayerInfo.getResponseTime();
    }
}
