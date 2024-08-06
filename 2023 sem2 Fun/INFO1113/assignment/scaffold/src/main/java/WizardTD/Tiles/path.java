package WizardTD.Tiles;
import WizardTD.App;
import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import static processing.core.PConstants.ARGB;

public class path extends Tile {
    public PImage pathImage;

    public path(int x, int y, PApplet pApplet , int type, int angle ,boolean can_place_tower) {
        super(x, y,  pApplet,can_place_tower);
        if(type == 0){
            pathImage = rotateImageByDegrees(App.path0Image,angle);
        }else if(type == 1) {
            pathImage = rotateImageByDegrees(App.path1Image, angle);
        } else if (type == 2) {
            pathImage = rotateImageByDegrees(App.path2Image, angle);
        } else if (type == 3) {
            pathImage = rotateImageByDegrees(App.path3Image,angle);
        }
    }

    public void draw(){
        this.pApplet.image(pathImage,this.x,this.y);
    }

    public PImage rotateImageByDegrees(PImage pimg, double angle) {
        BufferedImage img = (BufferedImage) pimg.getNative();
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        PImage result = pApplet.createImage(newWidth, newHeight, ARGB);
        BufferedImage rotated = (BufferedImage) result.getNative();
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        for (int i = 0; i < newWidth; i++) {
            for (int j = 0; j < newHeight; j++) {
                result.set(i, j, rotated.getRGB(i, j));
            }
        }

        return result;
    }

}
