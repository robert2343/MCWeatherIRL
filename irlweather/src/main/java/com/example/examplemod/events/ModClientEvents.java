package com.example.examplemod.events;

import org.slf4j.Logger;

import com.example.examplemod.ExampleMod;
import com.mojang.logging.LogUtils;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

//https://www.youtube.com/watch?v=fA1K4qEH7cs
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
            if(ExampleMod.currentString.length() < 28)
            {
            	ExampleMod.currentString += new String(readBuffer).replaceAll("\n", "").replaceAll("\r", "");
            }
            if(ExampleMod.currentString.length() >= 28)
            {
            	System.out.println(ExampleMod.currentString.substring(0, 28));
            	String humidityValStr = ExampleMod.currentString.substring(10, 15);
            	double humidityPercent = Double.parseDouble(humidityValStr);
            	if(humidityPercent > 70)
            	{
            		event.getServer().overworld().setRainLevel(Float.MAX_VALUE);
            		//LOGGER.info("rain");
            	}
            	else if(humidityPercent <= 70)
            	{
            		event.getServer().overworld().setRainLevel(Float.MIN_VALUE);
            		//LOGGER.info("sun");
            	}
            	
            	String lightStr = ExampleMod.currentString.substring(25, 28);
            	int lightLvl = Integer.parseInt(lightStr);
            	
            	if(lightLvl > 500)
            	{
            		event.getServer().overworld().setDayTime(6000);
            	}
            	else
            	{
            		event.getServer().overworld().setDayTime(18000);
            	}
            	
             	
              	ExampleMod.currentString = ExampleMod.currentString.substring(28);
            }
            
        } catch (Exception e) { 
        	ExampleMod.currentString = "";
        	e.printStackTrace(); 
        }
	}
}
