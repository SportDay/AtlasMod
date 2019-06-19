package me.xtrm.Atlas.particle.impl;

import java.util.Random;

import me.xtrm.Atlas.particle.BorderingSide;
import me.xtrm.Atlas.particle.Particle;
import me.xtrm.Atlas.utils.RainbowUtils;
import me.xtrm.Atlas.utils.RenderUtils;
import me.xtrm.Atlas.utils.ScaledUtils;
import net.minecraft.client.gui.ScaledResolution;

public class BaseParticle extends Particle {

    private final Random random = new Random();

    private int MOVE_SPEED;

    private int oof = 0;
    
    public BaseParticle(float radius) {
        super(radius);

        Random r = new Random();
        
        oof = r.nextInt(1000000);
        
        ScaledResolution sr = ScaledUtils.gen();
        
        setX(r.nextBoolean() ? -1 : sr.getScaledWidth() + 1);
        setY(r.nextBoolean() ? -1 : sr.getScaledHeight() + 1);
        
        MOVE_SPEED = 5;
    }

    public void deploy(BorderingSide borderingSide) {
        ScaledResolution scaledResolution = ScaledUtils.gen();        
        
        // Sexy Code
        switch (borderingSide) {
            case TOP:
                setY(0);
                setXIncrease(random.nextBoolean() ? random.nextInt(MOVE_SPEED) + 1 : -(random.nextInt(MOVE_SPEED) + 1));
                setYIncrease(random.nextInt(MOVE_SPEED) + 1);
                break;
            case LEFT:
                setX(0);
                setXIncrease(random.nextInt(MOVE_SPEED) + 1);
                setYIncrease(random.nextBoolean() ? random.nextInt(MOVE_SPEED) + 1 : -(random.nextInt(MOVE_SPEED) + 1));
                break;
            case BOTTOM:
                setY(scaledResolution.getScaledHeight());
                setXIncrease(random.nextBoolean() ? random.nextInt(MOVE_SPEED) + 1 : -(random.nextInt(MOVE_SPEED) + 1));
                setYIncrease(-(random.nextInt(MOVE_SPEED) + 1));
                break;
            case RIGHT:
                setX(scaledResolution.getScaledWidth());
                setXIncrease(-(random.nextInt(MOVE_SPEED) + 1));
                setYIncrease(random.nextBoolean() ? random.nextInt(MOVE_SPEED) + 1 : -(random.nextInt(MOVE_SPEED) + 1));
                break;
        }
    }

    @Override
    public void connect(int x, int y) {
    	if(MOVE_SPEED > 3) {
    		MOVE_SPEED--;
    	}
        RenderUtils.connectPoints(getX(), getY(), x, y);
    }

    @Override
    public void draw() {    	
        RenderUtils.drawCircle(getX(), getY(), getRadius(), RainbowUtils.effect(oof, 0.85F, 1).getRGB());
    }
}