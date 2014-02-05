package mods.customsteveserver.common;

import java.io.File;
import java.util.List;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;

public class CSSFineForgeTweaker implements ITweaker {
	public void acceptOptions(List<String> args, File gameDir, File assetsDir,
			String profile) {
		dbg("CSSForgeTweaker: acceptOptions");
	}

	public void injectIntoClassLoader(LaunchClassLoader classLoader) {
		dbg("CSSForgeTweaker: injectIntoClassLoader");
		classLoader.registerTransformer(CSSClassTransformer.class.getName());
	}

	public String getLaunchTarget() {
		dbg("CSSForgeTweaker: getLaunchTarget");
		return "net.minecraft.client.main.Main";
	}

	public String[] getLaunchArguments() {
		dbg("CSSForgeTweaker: getLaunchArguments");

		return new String[0];
	}

	private static void dbg(String str) {
		System.out.println(str);
	}
}