package me.xtrm.Atlas.module;

import me.xtrm.Atlas.main.Atlas;
import me.xtrm.Atlas.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Module {

	protected Minecraft mc = Minecraft.getMinecraft();
	protected FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
	
	private String name, displayName;
	private int key, animation;
	private Category category;
	private boolean toggled;
	
	public Module(String name, String displayName, int key, Category category, boolean toggled) {
		this.name = name;
		this.displayName = displayName;
		this.key = key;
		this.animation = toggled ? fr.getStringWidth(name) + 2 : 0;
		this.category = category;
		this.toggled = toggled;
		if(toggled)
			iEnable();
	}
	
	public Module(String name, String displayName, int key, Category category) { this(name, displayName, key, category, false); }	
	public Module(String name, int key, Category category, boolean toggled) { this(name, name, key, category, toggled); }	
	public Module(String name, String displayName, Category category) { this(name, displayName, -1, category, false); }
	public Module(String name, Category category, boolean toggled) { this(name, name, -1, category, toggled); }	
	public Module(String name, int key, Category category) { this(name, name, key, category, false); }	
	public Module(String name, Category category) { this(name, name, -1, category, false); }

	public enum Category {
		Combat, Movement, Player, World, Render, Miscellaneous, GUI;
	}
	
//	Simple Setters and Getters
	
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getAnimation() {
		return animation;
	}

	public void setAnimation(int animation) {
		this.animation = animation;
	}

	public String getName() {
		return name;
	}

	public Category getCategory() {
		return category;
	}

	public boolean isToggled() {
		return toggled;
	}
	
	public boolean isEnabled() {
		return toggled;
	}
	
	public boolean isCategory(Category cat) {
		return this.category == cat;
	}
	
//	Useful methods to set the state of the module	

	public void toggle() {
		boolean next = !toggled;
		iToggle();
		if(next)
			iEnable();
		else
			iDisable();
		
		this.toggled = next;
	}
	
	public void setState(boolean state) {
		if(state != this.toggled)
			iToggle();
		
		if(state)
			iEnable();
		else
			iDisable();
		
		this.toggled = state;
	}
	
	/**
	 * Adds a setting to the module's settings
	 * @param setting
	 */
	public void addSetting(Setting setting) {
		Atlas.instance.settingsManager.rSetting(setting);
	}
	
	/**
	 * Stands for "Get Setting by Unlocalized name"
	 * Gets a setting using the name, transformed into the Unlocalized name
	 * @param name
	 */
	public Setting gsu(String name) {
		String unlocalizedName = this.getName() + " " + name;
		return Atlas.instance.settingsManager.getSettingByUnlocalizedName(unlocalizedName);
	}
	
	public String getMode() {
		return gsu("Mode") != null ? gsu("Mode").string() : "none";
	}
	
	private void iEnable() { Atlas.instance.eventManager.register(this); if(getAnimation() == -1) setAnimation(0); onEnable(); }
	private void iDisable() { Atlas.instance.eventManager.unregister(this); onDisable(); }
	private void iToggle() { onToggle(); }
	
	public void onEnable() {}
	public void onDisable() {}
	public void onToggle() {}
}
