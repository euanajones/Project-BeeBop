package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screenHeight/2 - (gamePanel.tileSize/2);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 23;

        speed = 3;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right_2.png"));
            idle1 = ImageIO.read(getClass().getResourceAsStream("/player/idle_1.png"));
            idle2 = ImageIO.read(getClass().getResourceAsStream("/player/idle_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyHandler.upPressed) {
            worldY -= speed;
            direction = "up";
        } else if (keyHandler.downPressed) {
            worldY += speed;
            direction = "down";
        } else if (keyHandler.leftPressed) {
            worldX -= speed;
            direction = "left";
        } else if (keyHandler.rightPressed) {
            worldX += speed;
            direction = "right";
        }

        if (!keyHandler.upPressed && !keyHandler.downPressed
                && !keyHandler.leftPressed && !keyHandler.rightPressed) {
            direction = "idle";
        }

        spriteCounter++;

        if (spriteCounter >= 20) {
            if (spriteNumber == 1) {
                spriteNumber = 2;
            } else if (spriteNumber == 2) {
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = up1;
                } else if (spriteNumber == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = down1;
                } else if (spriteNumber == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = left1;
                } else if (spriteNumber == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    image = right1;
                } else if (spriteNumber == 2) {
                    image = right2;
                }
                break;
            case "idle":
                if (spriteNumber == 1) {
                    image = idle1;
                } else if (spriteNumber == 2) {
                    image = idle2;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);

    }
}
