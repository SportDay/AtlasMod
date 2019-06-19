package me.xtrm.Atlas.settings;

import java.util.ArrayList;

import me.xtrm.Atlas.module.Module;

public class Setting {
	
	private String name;
	private Module parent;
	private String mode;
	private String unlocalizedName;
	
	private String sval;
	public ArrayList<String> options;
	
	private boolean bval;
	
	private double dval;
	private double min;
	private double max;
	private boolean onlyint = false;
	private String label;

	public Setting(String name, Module parent, String sval, ArrayList<String> options){
		this.name = name;
		this.unlocalizedName = parent.getName() + " " + name;
		this.parent = parent;
		this.sval = sval;
		this.options = options;
		this.mode = "Combo";
	}
	
	public Setting(String name, Module parent, boolean bval){
		this.name = name;
		this.unlocalizedName = parent.getName() + " " + name;
		this.parent = parent;
		this.bval = bval;
		this.mode = "Check";
	}
	
	public Setting(String name, Module parent, double dval, double min, double max, boolean onlyint){
		this.name = name;
		this.unlocalizedName = parent.getName() + " " + name;
		this.parent = parent;
		this.dval = dval;
		this.min = min;
		this.max = max;
		this.onlyint = onlyint;
		this.mode = "Slider";
	}
	
	public Setting(String name, Module parent, String label){
		this.name = name;
		this.label = label;
		this.unlocalizedName = parent.getName() + " " + name;
		this.parent = parent;
		this.mode = "Label";
	}
	
	public String getName(){
		return name;
	}
	
	public Module getParentMod(){
		return parent;
	}
	
	public String string(){
		return this.sval;
	}
	
	public void setValString(String in){
		this.sval = in;
	}
	
	public ArrayList<String> getOptions(){
		return this.options;
	}
	
	public void setOptions(ArrayList<String> o) {
		this.options = o;
	}
	
	public int getOptionIndex() {
		int optionIndex = 0;
		for(String s : getOptions()) {
			if(s.equalsIgnoreCase(string())) {
				return optionIndex;
			}
			optionIndex++;
		}
		return -42;
	}
	
	public String getNextOption() {
		int optionIndex = getOptionIndex();
		if(optionIndex == -42) 
			return options.get(0);
		
		optionIndex++;
		if(optionIndex >= options.size()) {
			optionIndex = 0;
		}
		
		return options.get(optionIndex);
	}
	
	public boolean bool(){
		return this.bval;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public String getUnlocalizedName(){
		return this.unlocalizedName;
	}
	
	public void setUnlocalizedName(String n){
		this.unlocalizedName = n;
	}
	
	public void setValBoolean(boolean in){
		this.bval = in;
	}
	
	public double doubl(){
		if(this.onlyint){
			this.dval = Math.round(dval);
		}
		return this.dval;
	}

	public void setValDouble(double in){
		this.dval = in;
	}
	
	public double getMin(){
		return this.min;
	}
	
	public double getMax(){
		return this.max;
	}
	
	public boolean isCombo(){
		return this.mode.equalsIgnoreCase("Combo") ? true : false;
	}
	
	public boolean isCheck(){
		return this.mode.equalsIgnoreCase("Check") ? true : false;
	}
	
	public boolean isSlider(){
		return this.mode.equalsIgnoreCase("Slider") ? true : false;
	}
	
	public boolean isLabel(){
		return this.mode.equalsIgnoreCase("Label") ? true : false;
	}
	
	public boolean onlyInt(){
		return this.onlyint;
	}
}
