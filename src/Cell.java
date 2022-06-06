import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;

public class Cell extends JButton {

    private CellState state;
    public int HowManyBombsAround;
    public int Row,Column;
    public boolean IsChecked = false;
    public boolean IsFlagged;
    public Cell(int row,int column,Icon icon){
        super(icon);
        this.state = CellState.Empty;
        this.addMouseListener(CellListener.GetInstance());
        this.Row = row;
        this.Column = column;
    }
    public CellState GetState(){
        return state;
    }
    public static ImageIcon resizeIcon(ImageIcon icon, int width ,int height) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
    public void SetState(CellState state){
        this.state = state;
    }

}
