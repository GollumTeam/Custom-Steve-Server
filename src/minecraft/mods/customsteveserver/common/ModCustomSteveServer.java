package mods.customsteveserver.common;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.logging.Logger;

import mods.customsteveserver.utils.ConfigLoader;
import mods.customsteveserver.utils.ConfigProp;
import mods.customsteveserver.utils.VersionChecker;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "CustomSteeveServer", name = "Custom Steeve Server", version = "1.0", acceptedMinecraftVersions = "1.6.4")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class ModCustomSteveServer {
	
	@Instance("ModCustomSteeveServer")
	public static ModCustomSteveServer instance;
	
	@SidedProxy(clientSide = "mods.customsteveserver.common.ClientProxyCustomSteveServer", serverSide = "mods.customsteveserver.common.CommonProxyCustomSteveServer")
	public static CommonProxyCustomSteveServer proxy;
	
	@ConfigProp (info = "Display version checker message")
	public static boolean versionChecker = true;

	@ConfigProp (info = "Skins URL access")
	public static String rootSkinURL = "http://www.myserver.fr/skin_path/";
	
	@ConfigProp (info = "Cape URL access")
	public static String rootCapeURL= "http://www.myserver.fr/cape_path/";
	
	// Gestion des logs
	public static Logger log;
	
	
	/**
	 * 1
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// Creation du logger
		log = event.getModLog();
		
		ConfigLoader configLoader = new ConfigLoader(this.getClass(), event);
		configLoader.loadConfig();
		
	}
	
	/**
	 * 2 
	 * @throws IOException
	 * **/
	@EventHandler
	public void load(FMLInitializationEvent event) throws Exception {
		
		// Execution du renderer en fonction du serveur ou du client
		proxy.registerRenderers();
		
		// Creation du checker de version
		VersionChecker.getInstance(this.versionChecker).check(this);
		
		
	}
	
}
