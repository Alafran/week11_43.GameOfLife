package game;

import gameoflife.GameOfLifeBoard;
import java.util.Random;

public class PersonalBoard extends GameOfLifeBoard {

    private Random random;

    public PersonalBoard(int width, int height) {
        super(width, height);
        this.random = new Random();
    }

    @Override
    public void initiateRandomCells(double probabilityForEachCell) {
        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
                double randomProbability = this.random.nextDouble();

                this.getBoard()[x][y] = randomProbability < probabilityForEachCell;
            }
        }
    }

    @Override
    public boolean isAlive(int x, int y) {
        if (isCellInbounds(x, y)) {
            return this.getBoard()[x][y];
        }
        return false;
    }

    @Override
    public void turnToLiving(int x, int y) {
        if (isCellInbounds(x,y)) {
            this.getBoard()[x][y] = true;
        }
    }

    @Override
    public void turnToDead(int x, int y) {
        if (isCellInbounds(x, y)) {
            this.getBoard()[x][y] = false;
        }
    }

    @Override
    public int getNumberOfLivingNeighbours(int x, int y) {
        int numLivingNeighbours = 0;

        //checks cell to right
        if (isCellInbounds(x + 1, y)) {
            if (this.getBoard()[x + 1][y]) {
                numLivingNeighbours++;
            }
        }

        //checks cell to left
        if (isCellInbounds(x - 1, y)) {
            if (this.getBoard()[x - 1][y]) {
                numLivingNeighbours++;
            }
        }

        //checks space below
        if (isCellInbounds(x, y + 1)) {
            if (this.getBoard()[x][y + 1]) {
                numLivingNeighbours++;
            }
        }

        //checks space above
        if (isCellInbounds(x, y - 1)) {
            if (this.getBoard()[x][y - 1]) {
                numLivingNeighbours++;
            }
        }

        //checks cell top left
        if (isCellInbounds(x - 1, y - 1)) {
            if (this.getBoard()[x - 1][y - 1]) {
                numLivingNeighbours++;
            }
        }
        //checks cell bottom left
        if (isCellInbounds(x - 1, y + 1)) {
            if (this.getBoard()[x - 1][y + 1]) {
                numLivingNeighbours++;
            }
        }
        //checks cell bottom right
        if (isCellInbounds(x + 1, y + 1)) {
            if (this.getBoard()[x + 1][y + 1]) {
                numLivingNeighbours++;
            }
        }
        //checks cell top right
        if (isCellInbounds(x + 1, y - 1)) {
            if (this.getBoard()[x + 1][y - 1]) {
                numLivingNeighbours++;
            }
        }

        return numLivingNeighbours;
    }

    @Override
    public void manageCell(int x, int y, int livingNeighbors) {
        if (livingNeighbors < 2 || livingNeighbors > 3) {
            turnToDead(x, y);
        }
        if(livingNeighbors == 3) {
            turnToLiving(x, y);
        }

    }

    public boolean isCellInbounds(int x, int y) {
        boolean validCell = (x < this.getWidth() && x >= 0) && (y < this.getHeight() && y >= 0);
        return validCell;
    }

}
