import javax.swing.*;
import java.awt.event.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;

public class GUI extends JFrame {
    private JButton scheduleButton;
    private JComboBox scheduleOptions;
    private JPanel mainPanel;
    private JButton addProcessorButton;
    private JLabel test;

    public void actionPerformed(ActionEvent e){
    }

    GUI()
    {
        scheduleOptions.addItem("Priority");
        scheduleOptions.addItem("AGAT");
        scheduleOptions.addItem("SJF");
        scheduleOptions.addItem("SRTF");

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Scheduler");
        frame.setContentPane(new GUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
