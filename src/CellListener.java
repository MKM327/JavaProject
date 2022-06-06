import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class CellListener implements ActionListener {
    private static CellListener instance;
    private  CellListener(){

    }

    public static  CellListener GetInstance(){
        if (instance == null)
            return new CellListener();

        return instance;
    }
    @Override
    public void actionPerformed(ActionEvent e) {



        Cell cell = (Cell)e.getSource();
        if (cell.GetState().equals(CellState.Bomb)){
            URL systemResource = ClassLoader.getSystemResource("Resources/bomb.png");
            ImageIcon icon = new ImageIcon(systemResource);

        }
        CheckEmptyCell(cell.Row,cell.Column);

    }
    public void CheckEmptyCell(int row,int column){
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

                    CheckEmptyCell(i,j);
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
}
