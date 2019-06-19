package me.xtrm.Atlas.module.modules.render;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.render.EventRender3D;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.utils.RainbowUtils;
import me.xtrm.Atlas.utils.RenderUtils;
import net.minecraft.tileentity.TileEntity;

public class ChestFinder extends Module {

	public ChestFinder() {
		super("ChestFinder", Category.Render);
	}
	
	@EventTarget
	public void onRender3D(EventRender3D e) {
		for(Object o : mc.theWorld.loadedTileEntityList) {
			if(o.getClass().getName().toLowerCase().contains("chest")) {
				TileEntity te = (TileEntity)o;
				RenderUtils.init3D();
				{
					RenderUtils.drawOutlinedBlock(e.partialTicks, te.xCoord, te.yCoord, te.zCoord, RainbowUtils.effect(0, 0.7F, 1).getRGB());
				}
				RenderUtils.reset3D();
			}
		}
	}

}
