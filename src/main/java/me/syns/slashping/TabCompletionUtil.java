package me.syns.slashping;

import com.google.common.base.Functions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TabCompletionUtil {
    public static List<String> getListOfStringsMatchingLastWord(String[] p_175762_0_, Collection<?> p_175762_1_) {
        String s = p_175762_0_[p_175762_0_.length - 1];
        List<String> list = new ArrayList<>();
        if (!p_175762_1_.isEmpty()) {
            list = p_175762_1_.stream().map(Functions.toStringFunction()::apply).collect(Collectors.toList()).stream().filter(s2 ->
                    doesStringStartWith(s, s2)).collect(Collectors.toList());

            if (list.isEmpty()) {
                for (Object object : p_175762_1_) {
                    if (object instanceof ResourceLocation && doesStringStartWith(s, ((ResourceLocation) object).getResourcePath())) {
                        list.add(String.valueOf(object));
                    }
                }
            }
        }

        return list;
    }

    public static List<String> getListOfStringsMatchingLastWord(String[] p_175762_0_, String[] p_175762_1_) {
        return getListOfStringsMatchingLastWord(p_175762_0_, Arrays.asList(p_175762_1_));
    }

    private static boolean doesStringStartWith(String original, String region) {
        return region.regionMatches(true, 0, original, 0, original.length());
    }

    public static List<String> getTabUsernames() {
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        List<String> playerNames = new ArrayList<>();
        return player == null ? playerNames : player.sendQueue.getPlayerInfoMap().stream().map(netPlayerInfo ->
                netPlayerInfo.getGameProfile().getName()).collect(Collectors.toList());
    }

    public static List<EntityPlayer> getLoadedPlayers() {
        return Minecraft.getMinecraft().theWorld.playerEntities;
    }
}
