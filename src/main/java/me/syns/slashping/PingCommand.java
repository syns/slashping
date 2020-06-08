package me.syns.slashping;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class PingCommand extends CommandBase {
    public List<String> getCommandAliases() {
        return Arrays.asList("ping");
    }

    @Override
    public String getCommandName() {
        return "ping";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }

    @Override
    public String getCommandUsage(final ICommandSender sender) {
        return "";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        String name = (args.length == 1) ? args[0] : Minecraft.getMinecraft().getSession().getUsername();
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE.toString() + name + EnumChatFormatting.DARK_PURPLE.toString() + " has a ping of " + EnumChatFormatting.LIGHT_PURPLE.toString() + getPing(name) + "ms"));
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, final BlockPos pos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, FMLCommonHandler.instance().getMinecraftServerInstance().getAllUsernames());
        } else {
            return null;
        }
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
