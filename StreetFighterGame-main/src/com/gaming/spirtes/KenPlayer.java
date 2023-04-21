package com.gaming.spirtes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class KenPlayer extends Sprites {
	private BufferedImage walkImages[] = new BufferedImage[6];
	private BufferedImage kickImages[] = new BufferedImage[6]; 
	private BufferedImage punchImages[] = new BufferedImage[6]; 
	private BufferedImage damageEffectImages[] = new BufferedImage[6]; 
	public KenPlayer() throws IOException {
		x = GWIDTH - 400;
		h = 200;
		w = 200;
		y = FLOOR - h;
		speed = 0;
		image = ImageIO.read(RyuPlayer.class.getResource(KEN_IMAGE));
		loadWalkImages();
		loadKickImages();
		loadPunchImages();
		loadDamageEffect();
	}
	public void jump() {
		if(!isJump) {
		isJump = true;
		force = -20;
		y = y + force;
		}
	}
	
	public void fall() {
		if(y>=(FLOOR-h)) {
			isJump = false;
			return ;
		}
		y = y + force;
		force = force + GRAVITY;
	}
	
	private void loadWalkImages() {
		walkImages[0]  = image.getSubimage(2033, 868,58,91);
		walkImages[1]  = image.getSubimage(1966, 867,64,89);
		walkImages[2]  = image.getSubimage(1887,864,72,92);
		walkImages[3]  = image.getSubimage(1818, 864,70,93);
		walkImages[4]  = image.getSubimage(1746, 863,73,94);
		walkImages[5]  = image.getSubimage(1686, 866,63,92);
	}
	private void loadKickImages() {
		kickImages[0] = image.getSubimage(1733, 1665,70,100);
		kickImages[1] = image.getSubimage(1660, 1669,71,97);
		kickImages[2] = image.getSubimage(1593, 1666,70,95);
		kickImages[3] = image.getSubimage(1464, 1666,121,98);
		kickImages[4] = image.getSubimage(1665,1144,116,99);
		kickImages[5] = image.getSubimage(1589, 1143,76,100);
	}
	
	private void loadPunchImages() {
		punchImages[0] = image.getSubimage(2028,1148,68,97);
		punchImages[1] = image.getSubimage(1933,1147,97,98);
		punchImages[2] = image.getSubimage(1865,1150,67,90);
		punchImages[3] = image.getSubimage(1785,1145,81,96);
		punchImages[4] = image.getSubimage(401,817,108,105);
		punchImages[5] = image.getSubimage(518,816,76,105);
	}
	
	public void loadDamageEffect() {
		damageEffectImages[0]  = image.getSubimage(1365,3276,65,95);
		damageEffectImages[1]  = image.getSubimage(1437,3271,88,99);
		damageEffectImages[2]  = image.getSubimage(1537,3278,75,91);
		damageEffectImages[3]  = image.getSubimage(1627,3277,70,92);
		damageEffectImages[4]  = image.getSubimage(1712	,3274,63,98);
	}
	
	private BufferedImage printWalk() {
		isAttacking=false;
		if(imageIndex>5) {
			imageIndex=0;
		}
		BufferedImage img = walkImages[imageIndex];
		imageIndex++; // Change Image Frames
		return img;
	}
	private BufferedImage printKick() {
		isAttacking=false;
		if(imageIndex>5) {
			imageIndex=0;
			currentMove = WALK;
			isAttacking = false;
		}
		isAttacking = true;
		BufferedImage img = kickImages[imageIndex];
		imageIndex++; // Change Image Frames
		return img;
	}
	private BufferedImage printPunch() {
		if(imageIndex>5) {
			imageIndex=0;
			currentMove = WALK;
			isAttacking = false;
		}
		isAttacking = true;
		BufferedImage img = punchImages[imageIndex];
		imageIndex++; // Change Image Frames
		return img;
	}
	
	public BufferedImage printDamageImage() {
		if(imageIndex>damageEffectImages.length-1) {
			imageIndex = 0;
			currentMove = WALK;
		}
		BufferedImage img =  damageEffectImages[imageIndex];
		imageIndex++;
		return img;
		
	}
	@Override
	public BufferedImage defaultImage() {
		if(currentMove == KICK) {
			return printKick();
		}
		 else if (currentMove == PUNCH) {
			 return printPunch();
		 }
		 else {
			 return printWalk();
		 }
	}
	
}
