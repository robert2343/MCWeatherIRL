package com.example.examplemod.events;

import org.slf4j.Logger;

import com.example.examplemod.ExampleMod;
import com.mojang.logging.LogUtils;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=ExampleMod.MODID, bus=Mod.EventBusSubscriber.Bus.FORGE)
public class ModClientEvents {
    private static final Logger LOGGER = LogUtils.getLogger();

	
	@SubscribeEvent
	public static void myOnTick(ServerTickEvent event)
	{
		try {
			byte[] readBuffer = new byte[ExampleMod.comPort.bytesAvailable()];
            ExampleMod.comPort.readBytes(readBuffer, readBuffer.length);
            if(ExampleMod.currentString.length() >= 8 && !ExampleMod.currentString.substring(0, 8).equals("Humidity"))
            {
               	ExampleMod.currentString = "";
            }
            if(ExampleMod.currentString.length() < 80)
            {
            	ExampleMod.currentString += new String(readBuffer).replaceAll("\n", "").replaceAll("\r", "");
            }
            if(ExampleMod.currentString.length() >= 80)
            {
            	String humidityValStr = ExampleMod.currentString.substring(10, 15);
            	double humidityPercent = Double.parseDouble(humidityValStr);
            	if(humidityPercent > 80 && !event.getServer().overworld().isRaining())
            	{
            		event.getServer().overworld().oRainLevel = 1;
            	}
            	else if(humidityPercent <= 90 && event.getServer().overworld().isRaining())
            	{
            		event.getServer().overworld().oRainLevel = 0;
            	}
            	
             	System.out.println(ExampleMod.currentString.substring(0, 80));
              	ExampleMod.currentString = ExampleMod.currentString.substring(80);
            }
            
        } catch (Exception e) { e.printStackTrace(); }
	}
}
