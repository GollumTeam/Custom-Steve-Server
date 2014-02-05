package mods.customsteveserver.common;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;

@MCVersion(value = "1.6.4")
public class CSSFMLLoadingPlugin implements cpw.mods.fml.relauncher.IFMLLoadingPlugin {

	@Override
	public String[] getLibraryRequestClass() {
		return null;
	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[]{CSSClassTransformer.class.getName()};
	}

	@Override
	public String getModContainerClass() {
		return ModCustomSteveServer.class.getName();
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
	}

}