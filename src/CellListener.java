import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class CellListener implements MouseListener {
    private static CellListener instance;
    private  CellListener(){

    }

    public static  CellListener GetInstance(){
        if (instance == null)
            return new CellListener();

        return instance;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        Cell cell = (Cell)e.getSource();

        if (SwingUtilities.isRightMouseButton(e)){
           SetFlag(cell);

        }
        else {
            if (cell.GetState().equals(CellState.Bomb)){

                URL systemResource = ClassLoader.getSystemResource("Resources/bomb.png");
                ImageIcon icon = new ImageIcon(systemResource);
                cell.setIcon(Cell.resizeIcon(icon,30,30));
                Board.Mines_Remaining_Text.setText("Game Over!");
                Board.GameOver();

            }
            else {
                CheckCells(cell.Row, cell.Column);
            }
        }
    }
    public void SetFlag(Cell cell){
        if (!cell.IsFlagged){
            cell.IsFlagged = true;
            URL systemResource = ClassLoader.getSystemResource("Resources/flagged.png");
            ImageIcon icon = new ImageIcon(systemResource);
            cell.setIcon(Cell.resizeIcon(icon,40,30));
            Board.Mines_Remaining_Text.setText(""+--Board.RemainingMines+" Mines Remaining");
        }
        else{
            cell.IsFlagged = false;
            URL systemResource = ClassLoader.getSystemResource("Resources/facingDown.png");
            ImageIcon icon = new ImageIcon(systemResource);
            cell.setIcon(Cell.resizeIcon(icon,50,30));
            Board.Mines_Remaining_Text.setText(""+ ++Board.RemainingMines+" Mines Remaining");

        }
    }
    public void CheckCells(int row,int column){
        Cell cell = Board.cells[row][column];
        if (cell.GetState().equals(CellState.NearBomb)){
            cell.setIcon(Board.Resources[cell.HowManyBombsAround]);
            return;
        }
        cell.IsChecked = true;
        for (int i = row-1;i<row+2;i++){
            for (int j = column-1;j<column+2;j++){
                if ((i < 0 || i >= 10) || (j < 0 || j >= 10)) continue;
                CellState state = Board.cells[i][j].GetState();
                if (state.equals(CellState.Empty)&&!Board.cells[i][j].IsChecked)
                {
                    cell.setIcon(Board.Resources[0]);

                    CheckCells(i,j);
                }
                else if(state.equals(CellState.NearBomb))
                {
                    Board.cells[i][j].setIcon(Board.Resources[Board.cells[i][j].HowManyBombsAround]);

                }
                else if(state.equals(CellState.Empty))
                {
                    cell.setIcon(Board.Resources[0]);

                }
            }
        }
    }



    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
