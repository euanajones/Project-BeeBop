package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tile;
    int[][] mapTileCoord;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        tile = new Tile[10];
        mapTileCoord = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];

        getTileImage();
        loadMap("/maps/worldmap01.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResource("/tiles/grass.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResource("/tiles/grass2.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResource("/tiles/water.png"));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResource("/tiles/path.png"));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResource("/tiles/bridge.png"));
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResource("/tiles/wall.png"));
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResource("/tiles/tree.png"));
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResource("/tiles/sand.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream input = getClass().getResourceAsStream(filePath);
            BufferedReader buffReader = new BufferedReader(new InputStreamReader(input));

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {

                String line = buffReader.readLine();

                while (col < gamePanel.maxWorldCol) {
                    String[] numbers = line.split(" ");

                    int number = Integer.parseInt(numbers[col]);
                    mapTileCoord[col][row] = number;
                    col++;
                }
                if (col == gamePanel.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            buffReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {

            int tileNum = mapTileCoord[worldCol][worldRow];

            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            if (worldX > gamePanel.player.worldX - gamePanel.player.screenX - gamePanel.tileSize &&
                    worldX < gamePanel.player.worldX + gamePanel.player.screenX + gamePanel.tileSize  &&
                    worldY > gamePanel.player.worldY - gamePanel.player.screenY - gamePanel.tileSize  &&
                    worldY < gamePanel.player.worldY + gamePanel.player.screenY + gamePanel.tileSize ) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }

            worldCol++;

            if (worldCol == gamePanel.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}

