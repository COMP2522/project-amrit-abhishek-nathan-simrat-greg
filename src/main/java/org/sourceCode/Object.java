package org.sourceCode;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Object {
  public BufferedImage image;
  public int currentFrame = 0;
  public String name;
  public boolean collision = false;
  public int x, y;
  public Rectangle solidArea = new Rectangle(0,0,48,48);
  public int solidAreaDefaultX = 0;
  public int solidAreaDefaultY = 0;
  public void draw(Graphics2D g2, GamePanel gp) {
    if (name != null && name.equals("fire")) {
      g2.drawImage(gp.objects[5 + currentFrame].image, x, y, gp.tileSize, gp.tileSize, null);
    } else {
      g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
//    g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
  }
}
