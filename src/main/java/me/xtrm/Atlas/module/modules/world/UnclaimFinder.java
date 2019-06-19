package me.xtrm.Atlas.module.modules.world;

import java.util.HashMap;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.render.EventRender2D;
import me.xtrm.Atlas.module.Module;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;

@SuppressWarnings("all")
public class UnclaimFinder extends Module {

	public String show = "0%";
	public RenderItem itemRenderer = new RenderItem();
	
	private HashMap<String, Integer> values = new HashMap<String, Integer>();
	
	public UnclaimFinder() {
		super("UnclaimFinder", Category.Render);
	}
	
	@EventTarget
	public void onRender(EventRender2D event) {
		
		// Getting TileEntities of Chunks Arround
		int amountTiles = this.mc.theWorld.getChunkFromChunkCoords(this.mc.thePlayer.chunkCoordX + 0, this.mc.thePlayer.chunkCoordZ + 0).chunkTileEntityMap.values().size();
		amountTiles += this.mc.theWorld.getChunkFromChunkCoords(this.mc.thePlayer.chunkCoordX + 0, this.mc.thePlayer.chunkCoordZ + 1).chunkTileEntityMap.values().size();  
		amountTiles += this.mc.theWorld.getChunkFromChunkCoords(this.mc.thePlayer.chunkCoordX + 0, this.mc.thePlayer.chunkCoordZ - 1).chunkTileEntityMap.values().size();
		amountTiles += this.mc.theWorld.getChunkFromChunkCoords(this.mc.thePlayer.chunkCoordX + 1, this.mc.thePlayer.chunkCoordZ).chunkTileEntityMap.values().size();
		amountTiles += this.mc.theWorld.getChunkFromChunkCoords(this.mc.thePlayer.chunkCoordX - 1, this.mc.thePlayer.chunkCoordZ).chunkTileEntityMap.values().size();
		
		// Getting TileEntities values
		values.clear();
		values.put("chests", 0);
		values.put("moddedChest", 0);
		values.put("grinder", 0);
		values.put("palamachine", 0);
		values.put("cobblebreaker", 0);
		
		for(Object o : mc.theWorld.loadedTileEntityList) {
			if(o instanceof TileEntity) {
				TileEntity te = (TileEntity)o;
				if(te.getClass().toString().toLowerCase().contains("net.minecraft.tileentity.tileentitychest")) {
					values.put("chests", values.get("chests") + 1);
				}else if(te.getClass().toString().toLowerCase().contains("chest")) {
					values.put("moddedChest", values.get("moddedChest") + 1);
				}else if(te.getClass().toString().toLowerCase().contains("grinder")) {
					values.put("grinder", values.get("grinder") + 1);
				}else if((te.getClass().toString().toLowerCase().contains("paladium") && te.getClass().toString().toLowerCase().contains("machine"))) {
					values.put("palamachine", values.get("palamachine") + 1);
				}else if(te.getClass().toString().toLowerCase().contains("cobblebreaker")) {
					values.put("cobblebreaker", values.get("cobblebreaker") + 1);
				}
			}
		}
		
		int x = 4;
		int y = 20;
		fr.drawStringWithShadow("Chests:", x, y, -1);
		fr.drawStringWithShadow(EnumChatFormatting.RED + "" + values.get("chests") + "%", x + (fr.getStringWidth("Chests:") / 2) - fr.getStringWidth(values.get("chests") + "%") / 2, y + 10, -1);
		x += (fr.getStringWidth("Chests:") + 2);
		
		fr.drawStringWithShadow("Grinders:", x, y, -1);
		fr.drawStringWithShadow(EnumChatFormatting.RED + "" + values.get("moddedChest") + "%", x + (fr.getStringWidth("Grinders:") / 2) - fr.getStringWidth(values.get("moddedChest") + "%") / 2, y + 10, -1);
		x += (fr.getStringWidth("Grinders:") + 2);
		
		fr.drawStringWithShadow("ModdedChests:", x, y, -1);
		fr.drawStringWithShadow(EnumChatFormatting.RED + "" + values.get("moddedChest") + "%", x + (fr.getStringWidth("ModdedChests:") / 2) - fr.getStringWidth(values.get("moddedChest") + "%") / 2, y + 10, -1);
		x += (fr.getStringWidth("ModdedChests:") + 2);
		
		fr.drawStringWithShadow("PalaMachine:", x, y, -1);
		fr.drawStringWithShadow(EnumChatFormatting.RED + "" + values.get("palamachine") + "%", x + (fr.getStringWidth("PalaMachine:") / 2) - fr.getStringWidth(values.get("palamachine") + "%") / 2, y + 10, -1);
		x += (fr.getStringWidth("PalaMachine:") + 2);
		
		fr.drawStringWithShadow("CobbleBreaker:", x, y, -1);
		fr.drawStringWithShadow(EnumChatFormatting.RED + "" + values.get("cobblebreaker") + "%", x + (fr.getStringWidth("CobbleBreaker:") / 2) - fr.getStringWidth(values.get("cobblebreaker") + "%") / 2, y + 10, -1);
		x += (fr.getStringWidth("CobbleBreaker:") + 2);
		
//		int i = 0;
//		for(String s : values.keySet()) {
//			fr.drawStringWithShadow(s, 2 + i, 20, -1);
//			i += fr.getStringWidth(s) + 4;
//		}
//
//	    int chests = 0;
//	    int moddedChests = 0;
//	    int grinder = 0;
//	    int paladiumMachine = 0;
//	    int cobbleBreaker = 0;
//	    
//	    for (int x = -3; x < 3; x++) {
//	    	for (int z = -3; z < 3; z++)
//	    	{
//	    		Map tileMap = this.mc.theWorld.getChunkFromChunkCoords(this.mc.thePlayer.chunkCoordX + x, this.mc.thePlayer.chunkCoordZ + z).chunkTileEntityMap;
//	    		Iterator entries = tileMap.entrySet().iterator();
//	    		while (entries.hasNext()) {
//	    			Map.Entry e = (Map.Entry)entries.next();
//	    			TileEntity te = (TileEntity)e.getValue();
//	    			if (te != null) {
//	    				if (te.getClass().toString().contains("net.minecraft.tileentity.TileEntityChest")) {
//	    					chests++;
//	    				} else if ((te.getClass().toString().contains("Chest")) || (te.getClass().toString().contains("chest"))) {
//	    					moddedChests++;
//	    				}else if ((te.getClass().toString().contains("Grinder")) || (te.getClass().toString().contains("grinder"))) {
//	    					grinder++;
//	    				}else if ((te.getClass().toString().toLowerCase().contains("paladium machine")) || (te.getClass().toString().toLowerCase().contains("paladium") && te.getClass().toString().toLowerCase().contains("machine"))) {
//	    					paladiumMachine++;
//	    				}else if ((te.getClass().toString().contains("CobbleBreaker")) || (te.getClass().toString().contains("CobbleBreaker"))) {
//	    					cobbleBreaker++;
//	    				}
//	    			}
//	    		}
//	    	}
//	    }
//	     
		ItemStack stackChest = new ItemStack(Blocks.chest);
	    ItemStack stackModdedChests = new ItemStack(Blocks.cobblestone);
	    ItemStack stackGrinder = new ItemStack(Blocks.cobblestone);
	    ItemStack stackPaladiumMachine = new ItemStack(Blocks.cobblestone);
	    ItemStack stackCobbleBreaker = new ItemStack(Blocks.cobblestone);
	    
		boolean pala = false;
		try {
			Class.forName("fr.paladium.palamod.PalaMod");
			pala = true;
		} catch(Exception e) { pala = false; }
		
		if(pala) {
		    stackModdedChests = new ItemStack(Block.getBlockById(631));
		    stackGrinder = new ItemStack(Block.getBlockById(617));
		    stackPaladiumMachine = new ItemStack(Block.getBlockById(620));
		    stackCobbleBreaker = new ItemStack(Block.getBlockById(649));
		}
//	        
//	    int xChests = 60;
//	    int xModdedChests = 100;
//	    int xGrinder = 140;
//	    int xPaladiumMachine = 180;
//	    int xCobbleBreaker = 220;
//	    
//	    if (chests > 100) {
//	    	chests = 100;
//	    }
//	    
//	    if (moddedChests > 100) {
//	    	moddedChests = 100;
//	    }
//	    
//	    if (grinder > 100) {
//	    	grinder = 100;
//	    }
//	    
//	    if (paladiumMachine > 100) {
//	    	paladiumMachine = 100;
//	    }
//	    
//	    if (cobbleBreaker > 100) {
//	    	cobbleBreaker = 100;
//	    }
//	    
//	    String showChests = chests + "%";
//	    String showModdedChests = moddedChests + "%";
//	    String showGrinder = grinder + "%";
//	    String showPaladiumMachine = paladiumMachine + "%";
//	    String showCobbleBreaker = cobbleBreaker + "%";
//	        
//	    if (chests > 9) {
//	    	xChests -= 3;
//	    }
//	    if (moddedChests > 9) {
//	    	xModdedChests -= 3;
//	    }
//	    if (grinder > 9) {
//	    	xGrinder -= 3;
//	    }
//	    if (paladiumMachine > 9) {
//	    	xPaladiumMachine -= 3;
//	    }
//	    if (cobbleBreaker > 9) {
//	    	xCobbleBreaker -= 3;
//	    }
//	        
//	    int y = 37;
//	    	
//	    String chestsTxt = "Chests:";
//	    String ironChestsTxt = "IronChests:";
//	    String grindersTxt = "Grinders:";
//	    String palaMachineTxt = "PalaMachines:";
//	    String cobbleBreakerTxt = "CobbleBreaker:";
//	    
//	    mc.fontRenderer.drawStringWithShadow(chestsTxt, xChests - fr.getStringWidth(chestsTxt) / 2, y - 12, -1);
//	    y += 25;
//	    mc.fontRenderer.drawStringWithShadow(ironChestsTxt, xModdedChests - fr.getStringWidth(ironChestsTxt) / 2, y - 12, -1);
//	    y += 25;
//	    mc.fontRenderer.drawStringWithShadow(grindersTxt, xGrinder - fr.getStringWidth(grindersTxt) / 2, y - 12, -1);
//	    y += 25;
//	    mc.fontRenderer.drawStringWithShadow(palaMachineTxt, xPaladiumMachine - fr.getStringWidth(palaMachineTxt) / 2, y - 12, -1);
//	    y += 25;
//	    mc.fontRenderer.drawStringWithShadow(cobbleBreakerTxt, xCobbleBreaker - fr.getStringWidth(cobbleBreakerTxt) / 2, y - 12, -1);
//	    
//	    mc.fontRenderer.drawStringWithShadow(EnumChatFormatting.RED + showChests, xChests, y, -1);
//	    mc.fontRenderer.drawStringWithShadow(EnumChatFormatting.RED + showModdedChests, xModdedChests, y, -1);
//	    mc.fontRenderer.drawStringWithShadow(EnumChatFormatting.RED + showGrinder, xGrinder, y, -1);
//	    mc.fontRenderer.drawStringWithShadow(EnumChatFormatting.RED + showPaladiumMachine, xPaladiumMachine, y, -1);
//	    mc.fontRenderer.drawStringWithShadow(EnumChatFormatting.RED + showCobbleBreaker, xCobbleBreaker, y, -1);
//	
//	    if (amountTiles > 100) {
//	    	amountTiles = 100;
//	    }
//	    this.show = (amountTiles + "%");
//	    int x = 0;
//	    if (amountTiles > 9) {
//	    	x = 12;
//	    } else {
//	    	x = 15;
//	    }
//	    mc.fontRenderer.drawStringWithShadow("Total:", x - fr.getStringWidth("Total:") / 2, y - 12, -1);
//	    mc.fontRenderer.drawStringWithShadow(EnumChatFormatting.RED + this.show, x, y, -1);
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
	}

}
