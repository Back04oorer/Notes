package WizardTD;

import WizardTD.Tiles.*;
import processing.core.PApplet;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GameBoard {
    private PApplet p;
    private Tile[][] tileArray;//a 32*32 matrix to contain all Tile classes
    private boolean[][] visited;//store visited path
    private char[][] mapArray;//initial from text file
    private int CELLSIZE;
    private String map_path;
    public GameBoard(PApplet p , int CELLSIZE , String map_path) {
        this.p = p;
        this.CELLSIZE = CELLSIZE;
        this.map_path = map_path;
    }
    public int wizardHouse_position_X;
    public int wizardHouse_position_Y;
    public List<List<Tile>> paths = new ArrayList<>();//All valid paths for monster to move

    public void loadMap() {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(map_path));
            String line;
            while ((line = br.readLine()) != null) {
                // Pad the line with spaces if it's shorter than 20 characters
                while (line.length() < 20) {
                    line += " ";
                }
                lines.add(line);
            }

            // If there are fewer than 20 lines, add empty lines(just to make sure in 20 * 20 map,
            // there always be a Tile including shrub,grass,path)
            while (lines.size() < 20) {
                lines.add("                    "); // 20 spaces
            }

            // Convert lines to 2D array
            mapArray = new char[20][20];
            for (int i = 0; i < 20; i++) {
                mapArray[i] = lines.get(i).toCharArray();
            }

            //assign different type of tiles into tileArray
            tileArray = new Tile[20][20];
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    if (mapArray[i][j] == 'S') {
                        tileArray[i][j] = new shrub(j * CELLSIZE, 40 + i * CELLSIZE, p ,false);
                    } else if (mapArray[i][j] == 'X') {
                        assign_path(i, j);
                    } else if (mapArray[i][j] == 'W') {
                        //get wizard_house's position
                        this.wizardHouse_position_X = j * CELLSIZE - 8;
                        this.wizardHouse_position_Y = 40 + i * CELLSIZE - 8;
                        //still assign a path
                        tileArray[i][j] = new grass(j * CELLSIZE, 40 + i * CELLSIZE, p ,false);
                    } else {
                        // Treat all other cases (space, null, etc.) as grass
                        tileArray[i][j] = new grass(j * CELLSIZE, 40 + i * CELLSIZE, p ,true);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //assign different type of path and store it into Tile[][]
    private void assign_path(int x, int y) {
        int value = 0;

        // Calculate the value based on neighboring paths
        //basic idea: for a 4 bit binary number <top><down><left><right>
        if ((x > 0) && ((mapArray[x - 1][y] == 'X') || mapArray[x - 1][y] == 'W')) value += 8;
        if ((x < (mapArray.length - 1)) && ((mapArray[x + 1][y] == 'X') || (mapArray[x + 1][y] == 'W'))) value += 4;
        if (y > 0 && (mapArray[x][y - 1] == 'X' || mapArray[x][y - 1] == 'W')) value += 2;
        if (y < mapArray[x].length - 1 && (mapArray[x][y + 1] == 'X' || mapArray[x][y + 1] == 'W')) value += 1;

        int posX = y * CELLSIZE;
        int posY = 40 + x * CELLSIZE;

        switch (value) {
            case 1: case 2: case 3:
                tileArray[x][y] = new path(posX, posY, p, 0, 0,false);
                break;
            case 4: case 8: case 12:
                tileArray[x][y] = new path(posX, posY, p, 0, 90,false);
                break;
            case 5:
                tileArray[x][y] = new path(posX, posY, p, 1, 270,false);
                break;
            case 6:
                tileArray[x][y] = new path(posX, posY, p, 1, 0,false);
                break;
            case 7:
                tileArray[x][y] = new path(posX, posY, p, 2, 0,false);
                break;
            case 11:
                tileArray[x][y] = new path(posX, posY, p, 2, 180,false);
                break;
            case 13:
                tileArray[x][y] = new path(posX, posY, p, 2, 270,false);
                break;
            case 14:
                tileArray[x][y] = new path(posX, posY, p, 2, 90,false);
                break;
            case 9:
                tileArray[x][y] = new path(posX, posY, p, 1, 180,false);
                break;
            case 10:
                tileArray[x][y] = new path(posX, posY, p, 1, 90,false);
                break;
            case 15:
                tileArray[x][y] = new path(posX, posY, p, 3, 0,false);
                break;
        }
    }



    public void drawmap(){
        for (int i = 0; i < mapArray.length; i++) {
            for (int j = 0; j < mapArray[i].length; j++) {
                tileArray[i][j].draw() ;
                }
        }
    }

    public void findPaths() {
        visited = new boolean[tileArray.length][tileArray[0].length];

        for (int i = 0; i < tileArray.length; i++) {
            for (int j = 0; j < tileArray[0].length; j++) {
                if (i == 0 || i == tileArray.length - 1 || j == 0 || j == tileArray[0].length - 1) {
                    if ((tileArray[i][j] instanceof path) && !visited[i][j]) {
                        List<Tile> currentPath = new ArrayList<>();

                        // Add a starting tile outside of the map based on the boundary
                        Tile startingTile;
                        if (i == 0) {
                            startingTile = new path(
                                    j * CELLSIZE,
                                    40 - CELLSIZE, p, 0, 0, false);
                        } else if (i == tileArray.length - 1) {
                            startingTile = new path(j * CELLSIZE, 40 + i * CELLSIZE + CELLSIZE, p, 0,
                                    0, false);
                        } else if (j == 0) {
                            startingTile = new path(-CELLSIZE, 40 + i * CELLSIZE, p, 0, 0,
                                    false);
                        } else {
                            startingTile = new path((j + 1) * CELLSIZE, 40 + i * CELLSIZE, p, 0, 0,
                                    false);
                        }
                        currentPath.add(startingTile);

                        searchPath(i, j, currentPath);
                    }
                }
            }
        }
    }


    private void searchPath(int i, int j, List<Tile> currentPath) {
        if (i < 0 || i >= tileArray.length || j < 0 || j >= tileArray[0].length || visited[i][j]) {
            return;
        }

        if (mapArray[i][j] == 'W') {
            paths.add(new ArrayList<>(currentPath));
            return;
        }

        if (!(tileArray[i][j] instanceof path)) {
            return;
        }

        visited[i][j] = true;
        currentPath.add(tileArray[i][j]);

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] dir : dirs) {
            searchPath(i + dir[0], j + dir[1], currentPath);
        }

        currentPath.remove(currentPath.size() - 1);
        visited[i][j] = false;
    }


    public List<List<Tile>> getPaths(){
        return paths;
    }
    public int getWizardHouse_position_X(){
        return this.wizardHouse_position_X;
    }
    public int getWizardHouse_position_Y(){
        return this.wizardHouse_position_Y;
    }

    //get a tile based on x,y
    public Tile getTile(int X, int Y) {
        if (X <= 640 && X >= 0 && Y >= 40 && Y <= 680) {
            int i = (Y-40) / App.CELLSIZE;
            int j = X / App.CELLSIZE;
            return tileArray[i][j];
        } else {
            return null;  // Return null if the x and y are out of bounds
        }
    }

}


