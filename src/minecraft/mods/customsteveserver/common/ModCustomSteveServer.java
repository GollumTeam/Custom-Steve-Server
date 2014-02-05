package mods.customsteveserver.common;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import com.google.common.eventbus.Subscribe;
import com.google.common.eventbus.EventBus;

import mods.customsteveserver.utils.ConfigLoader;
import mods.customsteveserver.utils.ConfigProp;
import mods.customsteveserver.utils.VersionChecker;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLConstructionEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModCustomSteveServer extends DummyModContainer {
	
	public ModCustomSteveServer() {

		super(new ModMetadata());
		ModMetadata meta = getMetadata();
		meta.modId = "CustomSteveServer";
		meta.name = "Custom Steve Server";
		meta.version = "1.0"; //String.format("%d.%d.%d.%d", majorVersion, minorVersion, revisionVersion, buildVersion);
		meta.credits = "";
		meta.authorList = Arrays.asList("Smeagol");
		meta.description = "";
		meta.url = "";
		meta.updateUrl = "";
		meta.screenshots = new String[0];
		meta.logoFile = "";

	}
	
	@ConfigProp (info = "Display version checker message")
	public static boolean versionChecker = true;

	@ConfigProp (info = "Skins URL access")
	public static String rootSkinURL = "http://www.myserver.fr/skin_path/";
	
	@ConfigProp (info = "Cape URL access")
	public static String rootCapeURL= "http://www.myserver.fr/cape_path/";
	
	// Gestion des logs
	public static Logger log;
	
	
	@Override
	public boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(this);
		return true;
	}

	@Subscribe
	public void modConstruction(FMLConstructionEvent evt){

	}

	@Subscribe
	public void init(FMLInitializationEvent evt) {

	}

	@Subscribe
	public void preInit(FMLPreInitializationEvent evt) {
		// Creation du logger
		log = evt.getModLog();
		
		ConfigLoader configLoader = new ConfigLoader(this.getClass(), evt);
		configLoader.loadConfig();
	}

	@Subscribe
	public void postInit(FMLPostInitializationEvent evt) {

	}

}
