import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Random;

public class Board extends JFrame {

    public static JPanel descriptionPanel= new JPanel();

    public static final ImageIcon[] Resources = new ImageIcon[10];
    public  static JLabel Mines_Remaining_Text;
    public static int RemainingMines = 15;
    public static JButton PlayAgainBtn;

    public Board(){
        descriptionPanel.setSize(new Dimension(500,100));
        this.add(descriptionPanel);
        GameArea gamePanel = new GameArea();
        this.add(gamePanel);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GetImages();
        SetDescriptions();
        this.setSize(500,500);
        this.setResizable(false);
        this.setVisible(true);
    }
    private void GetImages(){
        for (int i = 0; i < 9; i++) {
            URL systemResource = ClassLoader.getSystemResource("Resources/"+i+".png");
            ImageIcon icon = new ImageIcon(systemResource);

            Resources[i] = Cell.resizeIcon(icon,40,30);
        }
    }
    private void SetDescriptions(){
        Mines_Remaining_Text = new JLabel("15 Mines Remaining");
        descriptionPanel.add(Mines_Remaining_Text);

    }
    public void Remove(){

    }






}
