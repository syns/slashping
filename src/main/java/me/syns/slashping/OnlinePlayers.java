package me.syns.slashping;

import net.minecraft.client.network.NetworkPlayerInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OnlinePlayers {
    public static String[] getListOfPlayerUsernames() {
        final String[] users = null;
        final Collection<NetworkPlayerInfo> players = Ping.mc.getNetHandler().getPlayerInfoMap();
        final List<String> list = new ArrayList<String>();
        for (final NetworkPlayerInfo info : players) {
            list.add(info.getGameProfile().getName());
        }
        return list.toArray(new String[0]);
    }
}
