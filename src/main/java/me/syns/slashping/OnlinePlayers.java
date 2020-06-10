package me.syns.slashping;

import net.minecraft.client.network.*;
import java.util.*;

public class OnlinePlayers
{
    public static String[] getListOfPlayerUsernames() {
        final String[] users = null;
        final Collection<NetworkPlayerInfo> players = Ping.mc.getNetHandler().getPlayerInfoMap();
        final List<String> list = new ArrayList<>();
        for (final NetworkPlayerInfo info : players) {
            list.add(info.getGameProfile().getName());
        }
        return list.toArray(new String[0]);
    }
}