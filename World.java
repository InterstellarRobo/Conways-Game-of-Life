public class World
  {
    private boolean[][] world;

    public World()
    {
      this(10,10);
    }

    public World(int width, int height)
    {
      world = new boolean[height][width];
    }

    public boolean[][] getAsArray()
    {
      return world;
    }

    public int getWidth() {
      return world[0].length;
    }

    public int getHeight() {
      return world.length;
    }

    public boolean cellStatus(int x, int y) {
      return world[x][y];
    }
    
    public void flipCell(int x, int y)
    {
      world[x][y] = !world[x][y];
    }
    
    public void update()
    {
      boolean[][] newWorld = new boolean[world.length][world[0].length];
      for (int x = 0; x < world.length; x++)
        {
          for (int y = 0; y < world[0].length; y++)
            {
              newWorld[x][y] = shouldCellLive(x, y);
            }
        }
      world = newWorld;
    }
    
    public void display()
    {
      String border = "";
      for (int i = 0; i < world.length; i++)
        {
          border += "- ";
        }
      
      System.out.println(border);
      System.out.print(this);
      System.out.println(border);
    }

    public String toString()
    {
      String board = "";
      for (int y = 0; y < world[0].length; y++)
        {
          for (int x = 0; x < world.length; x++)
            {
              if (world[x][y])
              {
                board += "1 ";
              }
              else
              {
                board += "0 ";
              }
            }
          board += "\n";
        }
      return board;
    }
    
    /*

    * Takes an array of coordinates and populates the cells, e.g. { {0, 0}, {0, 1}, {1, 1} } will be made alive
    * NOT protected against unsafe coordinates/unsafe arrays, e.g. ones not of the format [n][2]
    */
    public void populateCells(int[][] coordinates) 
    {
      for (int i = 0; i < coordinates.length; i++)
        {
          int x = coordinates[i][0];
          int y = coordinates[i][1];
          world[x][y] = true;
        }
      
    }

    /*

    * Takes an array of coordinates and kills the cells, e.g. { {0, 0}, {0, 1}, {1, 1} } will be made dead
    * NOT protected against unsafe coordinates/unsafe arrays, e.g. ones not of the format [n][2]
    */
    public void depopulateCells(int[][] coordinates) 
    {
      for (int i = 0; i < coordinates.length; i++)
        {
          int x = coordinates[i][0];
          int y = coordinates[i][1];
          world[x][y] = false;
        }
    }

    public void depopulateAllCells() {
      for (int row = 0; row < world.length; row++) for (int col = 0; col < world[0].length; col++) world[row][col] = false;
    }

    private int numberOfNeighbours(int x, int y)
    {
      int count = 0;
      for (int i = x - 1; i <= x + 1; i++)
        {
          for (int j = y - 1; j <= y + 1; j++)
            {
              if (!(i < 0 || i >= world.length || j < 0 || j >= world[0].length || (i == x && j == y)) && world[i][j])
              {
                count++;
              }
            }
        }
      return count;
    }

    private boolean shouldCellLive(int x, int y)
    {
      int num = numberOfNeighbours(x, y);
      if (num < 2 || num > 3)
      {
        return false;
      }
      if (num == 3)
      {
        return true;
      }
      return world[x][y];
    }


  }