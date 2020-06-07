package me.syns.slashping;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.io.IOException;

@Mod(modid = "slashping", name = "/Ping", version = "1.0")
public class Ping {
    public static final String MODID = "slashping";
    public static final String VERSION = "1.0";
    public static Minecraft mc;


    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) throws IOException {
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand(new PingCommand());
    }
}
