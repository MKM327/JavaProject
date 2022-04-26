import javax.swing.*;

public class Board extends JFrame {
    private JPanel MainBoard;
    private JButton button1;

    public Board(){
    this.setContentPane(MainBoard);
    this.setVisible(true);
    this.setSize(500,500);
    this.setResizable(false);
}
}
