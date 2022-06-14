import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;


public class GameArea extends JPanel {
    private static final int MineRows = 10;
    private static final int MineColumns = 10;

    public static final Cell[][] cells = new Cell[MineRows][MineColumns];
    public static boolean isGameFinished;
    public GameArea(){
        this.setLayout(new GridLayout(10,10,5,5));
        initCells();
        SetBombs();
        CheckCellBombs();
        this.setBorder(BorderFactory.createEmptyBorder(100,0,0,0));
    }
    private void initCells() {
        URL systemResource = ClassLoader.getSystemResource("Resources/facingDown.png");
        ImageIcon icon = new ImageIcon(systemResource);
        Icon resizedIcon = Cell.resizeIcon(icon,50,30);
        for (int i = 0 ; i<10;i++){
            for (int j = 0 ; j<10;j++){
                cells[i][j] = new Cell(i,j,resizedIcon);
                this.add(cells[i][j]);
            }
        }
    }
    public void CheckCellBombs(){
        for (int i = 0 ; i<10;i++){
            for (int j = 0 ; j<10;j++){
                CheckCell(i,j);

            }
        }
    }
    public void CheckCell(int row,int column){
        Cell cell = cells[row][column];
        if(cell.GetState().equals(CellState.Bomb)) return;

        for (int i = row-1;i<row+2;i++){
            for (int j = column-1;j<column+2;j++){
                if ((i < 0 || i >= 10) || (j < 0 || j >= 10)) continue;
                if (i == row && column == j) continue;
                CellState state = cells[i][j].GetState();
                if (state.equals(CellState.Bomb)){
                    cell.HowManyBombsAround++;
                }

            }
        }

        if (cell.HowManyBombsAround>0&& !cell.GetState().equals(CellState.Bomb)){
            cell.SetState(CellState.NearBomb);
        }
    }
    private void SetBombs(){
        Random random = new Random();
        for (int i = 0; i<10;i++){
            int row = random.nextInt(0,10);
            int column = random.nextInt(0,10);
            cells[row][column].SetState(CellState.Bomb);


        }

    }
    public  static void GameOver(){
        for (int i = 0 ; i<10;i++){
            for (int j = 0 ; j<10;j++){
                Cell cell = cells[i][j];
                if (cell.GetState().equals(CellState.Bomb)){
                    URL systemResource = ClassLoader.getSystemResource("Resources/bomb.png");
                    ImageIcon icon = new ImageIcon(systemResource);
                    cell.setIcon(Cell.resizeIcon(icon,30,30));
                }
                cell.removeMouseListener(cell.getMouseListeners()[1]);
                cell.removeMouseListener(cell.getMouseListeners()[0]);
            }
        }
    }
}
