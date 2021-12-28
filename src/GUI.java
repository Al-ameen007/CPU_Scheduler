import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JLabel;

public class GUI extends JFrame {

    private static Scheduler scheduler;

    public class ProcessWindow extends JFrame
    {
        ProcessWindow ()
        {
            JFrame frame = new JFrame("Process");
            JButton addButton = new JButton("Add Process");

            // Name, Burst Time, Arrival Time, Priority, Quantum
            JLabel nameLabel = new JLabel("Name");
            JTextField name = new JTextField("P", 20);

            JLabel burstLabel = new JLabel("Burst Time");
            JTextField burst = new JTextField("0", 5);

            JLabel arrivalLabel = new JLabel("Arrival Time");
            JTextField arrival = new JTextField("0",5);

            JLabel priorityLabel = new JLabel("Priority");
            JTextField priority = new JTextField("0", 5);

            JLabel quantumLabel = new JLabel("Quantum");
            JTextField quantum = new JTextField("0", 5);

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    scheduler.createProcess( name.getText()
                            , Integer.parseInt(burst.getText())
                            , Integer.parseInt(arrival.getText())
                            , Integer.parseInt(priority.getText())
                            , Integer.parseInt(quantum.getText()));
                    frame.setVisible(false);
                }
            });

            frame.add(nameLabel);
            frame.add(name);
            frame.add(burstLabel);
            frame.add(burst);
            frame.add(arrivalLabel);
            frame.add(arrival);
            frame.add(priorityLabel);
            frame.add(priority);
            frame.add(quantumLabel);
            frame.add(quantum);

            frame.add(addButton);
            frame.setResizable(false);
            frame.setSize(420,420);
            frame.setLayout(new FlowLayout());
            frame.setVisible(true);
        }
    }

    private JButton scheduleButton;
    private JComboBox scheduleOptions;
    private JPanel mainPanel;
    private JButton addProcessorButton;
    private JLabel schedulerName;
    private JLabel awatLabel;
    private JLabel atatLabel;

    GUI()
    {

        scheduleOptions.addItem("AGAT");
        scheduleOptions.addItem("SRTF");
        scheduleOptions.addItem("SJF");
        scheduleOptions.addItem("Priority");

        scheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setScheduleButton();
            }
        });
        addProcessorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewProcess();
            }
        });
    }

    private void setScheduleButton()
    {
        String s = scheduleOptions.getSelectedItem().toString();
        schedulerName.setText("Schedule Name: " + s);
        scheduler.setScheduleType(s);
        scheduler.schedule();
        updateSchedulerData();
    }

    private void updateSchedulerData()
    {
        awatLabel.setText("AWAT:" + scheduler.getScheduleOutput().avgWaitingTime);
        atatLabel.setText("ATAT:" + scheduler.getScheduleOutput().avgTurnaroundTime);
    }

    private void addNewProcess()
    {
        ProcessWindow window = new ProcessWindow();
    }

    public static void main(String[] args) {
        scheduler = new Scheduler();
        JFrame frame = new JFrame("Scheduler");
        frame.setContentPane(new GUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280,720);
        frame.setVisible(true);
    }

}
