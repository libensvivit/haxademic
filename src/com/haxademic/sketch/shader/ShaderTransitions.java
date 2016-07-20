package com.haxademic.sketch.shader;

import com.haxademic.core.app.AppSettings;
import com.haxademic.core.app.P;
import com.haxademic.core.app.PAppletHax;
import com.haxademic.core.image.ImageUtil;
import com.haxademic.core.image.filters.shaders.PixelateFilter;
import com.haxademic.core.math.easing.Penner;
import com.haxademic.core.system.FileUtil;

import processing.core.PImage;
import processing.opengl.PShader;

public class ShaderTransitions
extends PAppletHax {
	public static void main(String args[]) { PAppletHax.main(Thread.currentThread().getStackTrace()[1].getClassName()); }

	protected PImage img1;
	protected PImage img2;
	protected PShader transitionShader;
	protected float frames = 200;

	protected void overridePropsFile() {
		p.appConfig.setProperty( AppSettings.WIDTH, 372/2 );
		p.appConfig.setProperty( AppSettings.HEIGHT, 484/2 );
		p.appConfig.setProperty( AppSettings.WIDTH, 640 );
		p.appConfig.setProperty( AppSettings.HEIGHT, 640 );
		p.appConfig.setProperty( AppSettings.RENDERING_MOVIE, false );
	}

	public void setup() {
		super.setup();
		
		img1 = ImageUtil.imageToGraphics(p, p.loadImage(FileUtil.getFile("images/snowblinded-beach.jpg")));
		img2 = ImageUtil.imageToGraphics(p, p.loadImage(FileUtil.getFile("images/snowblinded-mtn-2.jpg")));
//		img1 = ImageUtil.imageToGraphics(p, p.loadImage(FileUtil.getFile("images/aholes-bors.png")));
//		img2 = ImageUtil.imageToGraphics(p, p.loadImage(FileUtil.getFile("images/aholes-trmp.png")));
		
//		transitionShader = p.loadShader(FileUtil.getFile("shaders/transitions/colour-distance.glsl"));
//		transitionShader.set("interpolationPower", 2f);
//		transitionShader = p.loadShader(FileUtil.getFile("shaders/transitions/polka-dots-curtain.glsl"));
//		transitionShader = p.loadShader(FileUtil.getFile("shaders/transitions/fly-eye.glsl"));
//		transitionShader = p.loadShader(FileUtil.getFile("shaders/transitions/circle-open.glsl"));
//		transitionShader = p.loadShader(FileUtil.getFile("shaders/transitions/directional-wipe.glsl"));
//		transitionShader = p.loadShader(FileUtil.getFile("shaders/transitions/warp-fade.glsl"));
//		transitionShader = p.loadShader(FileUtil.getFile("shaders/transitions/morph.glsl"));
//		transitionShader = p.loadShader(FileUtil.getFile("shaders/transitions/swap.glsl"));
//		transitionShader = p.loadShader(FileUtil.getFile("shaders/transitions/dissolve.glsl"));
//		transitionShader.set("blocksize", 6f);
//		transitionShader = p.loadShader(FileUtil.getFile("shaders/transitions/cross-zoom.glsl"));
		
//		transitionShader = p.loadShader(FileUtil.getFile("shaders/transitions/cube.glsl"));
		transitionShader = p.loadShader(FileUtil.getFile("shaders/transitions/glitch-displace.glsl"));
		transitionShader.set("from", img1);
		transitionShader.set("to", img2);
	}

	public void drawApp() {
		p.background(0);
		float progress = (p.frameCount % frames*2) / frames; 
		if(progress < 1.0f) {
			transitionShader.set("from", img1);
			transitionShader.set("to", img2);
		} else {
			transitionShader.set("from", img2);
			transitionShader.set("to", img1);
		}
		float loopProgress = progress % 1f;
		float easedProgress = Penner.easeInOutCubic(loopProgress, 0, 1, 1);

		transitionShader.set("progress", easedProgress);
		p.filter(transitionShader);		
	}

	public void keyPressed() {
		super.keyPressed();
		if(p.key == ' ') {
		}
	}


}