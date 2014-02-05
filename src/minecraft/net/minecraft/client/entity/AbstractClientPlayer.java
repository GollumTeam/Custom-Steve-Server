package net.minecraft.client.entity;

import java.net.HttpURLConnection;
import java.net.URL;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.customsteveserver.common.ModCustomSteveServer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureObject;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public abstract class AbstractClientPlayer extends EntityPlayer
{
    public static final ResourceLocation locationStevePng = new ResourceLocation("textures/entity/steve.png");
    private ThreadDownloadImageData downloadImageSkin;
    private ThreadDownloadImageData downloadImageCape;
    private ResourceLocation locationSkin;
    private ResourceLocation locationCape;

    public AbstractClientPlayer(World par1World, String par2Str)
    {
        super(par1World, par2Str);
        this.setupCustomSkin();
    }

    protected void setupCustomSkin()
    {
        System.out.println("Setting up custom skins");

        if (this.username != null && !this.username.isEmpty())
        {
            this.locationSkin = getLocationSkin(this.username);
            this.locationCape = getLocationCape(this.username);
            this.downloadImageSkin = getDownloadImageSkin(this.locationSkin, this.username);
            this.downloadImageCape = getDownloadImageCape(this.locationCape, this.username);
        }
    }

    public ThreadDownloadImageData getTextureSkin()
    {
        return this.downloadImageSkin;
    }

    public ThreadDownloadImageData getTextureCape()
    {
        return this.downloadImageCape;
    }

    public ResourceLocation getLocationSkin()
    {
        return this.locationSkin;
    }

    public ResourceLocation getLocationCape()
    {
        return this.locationCape;
    }

    public static ThreadDownloadImageData getDownloadImageSkin(ResourceLocation par0ResourceLocation, String par1Str)
    {
        return getDownloadImage(par0ResourceLocation, getSkinUrl(par1Str), locationStevePng, new ImageBufferDownload());
    }

    public static ThreadDownloadImageData getDownloadImageCape(ResourceLocation par0ResourceLocation, String par1Str)
    {
        return getDownloadImage(par0ResourceLocation, getCapeUrl(par1Str), (ResourceLocation)null, (IImageBuffer)null);
    }

    private static ThreadDownloadImageData getDownloadImage(ResourceLocation par0ResourceLocation, String par1Str, ResourceLocation par2ResourceLocation, IImageBuffer par3IImageBuffer)
    {
        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        Object object = texturemanager.getTexture(par0ResourceLocation);

        if (object == null)
        {
            object = new ThreadDownloadImageData(par1Str, par2ResourceLocation, par3IImageBuffer);
            texturemanager.loadTexture(par0ResourceLocation, (TextureObject)object);
        }

        return (ThreadDownloadImageData)object;
    }

    public static String getSkinUrl(String par0Str)
    {
    	String returnTexture = String.format("http://skins.minecraft.net/MinecraftSkins/%s.png", new Object[] {StringUtils.stripControlCodes(par0Str)});
    	
    	try {
    		
    		String customURL = String.format(ModCustomSteveServer.rootSkinURL+"%s.png", new Object[] {StringUtils.stripControlCodes(par0Str)});
    		ModCustomSteveServer.log.info ("Test custom skin : "+customURL);
    		
    		URL url = new URL(customURL);
    		HttpURLConnection huc = (HttpURLConnection) url.openConnection();
    		int responseCode = huc.getResponseCode();
    		if (responseCode != 404) {
    			returnTexture = customURL;
    			ModCustomSteveServer.log.info (par0Str+" found custom skin");
    		} else {
    			ModCustomSteveServer.log.info (par0Str+" custom skin not found use original server : "+responseCode);
    		}
    		
    	} catch (Exception e) {
    	}

		ModCustomSteveServer.log.info ("Return Skin Texture : "+returnTexture);
    	
        return returnTexture;
    }

    public static String getCapeUrl(String par0Str)
    {
        String returnTexture =  String.format("http://skins.minecraft.net/MinecraftCloaks/%s.png", new Object[] {StringUtils.stripControlCodes(par0Str)});
    	
    	try {
    		
    		String customURL = String.format(ModCustomSteveServer.rootSkinURL+"%s.png", new Object[] {StringUtils.stripControlCodes(par0Str)});
    		ModCustomSteveServer.log.info ("Test custom cape : "+customURL);
    		
    		URL url = new URL(customURL);
    		HttpURLConnection huc = (HttpURLConnection) url.openConnection();
    		int responseCode = huc.getResponseCode();
    		if (responseCode != 404) {
    			returnTexture = customURL;
    			ModCustomSteveServer.log.info (par0Str+" found custom cape");
    		} else {
    			ModCustomSteveServer.log.info (par0Str+" custom cape not found use original server : "+responseCode);
    		}
    		
    	} catch (Exception e) {
    	}

		ModCustomSteveServer.log.info ("Return Cape Texture : "+returnTexture);
    	
        return returnTexture;
    }

    public static ResourceLocation getLocationSkin(String par0Str)
    {
        return new ResourceLocation("skins/" + StringUtils.stripControlCodes(par0Str));
    }

    public static ResourceLocation getLocationCape(String par0Str)
    {
        return new ResourceLocation("cloaks/" + StringUtils.stripControlCodes(par0Str));
    }

    public static ResourceLocation getLocationSkull(String par0Str)
    {
        return new ResourceLocation("skull/" + StringUtils.stripControlCodes(par0Str));
    }
}
