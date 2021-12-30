import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JLabel;

public class GUI extends JFrame {

    private static Scheduler scheduler;
    private ArrayList<Color> availableColors;
    private int colorIndex = 0;
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
                    scheduler.createProcess( name.getText(),
                            availableColors.get(colorIndex)
                            , Integer.parseInt(burst.getText())
                            , Integer.parseInt(arrival.getText())
                            , Integer.parseInt(priority.getText())
                            , Integer.parseInt(quantum.getText()));
                    frame.setVisible(false);
                    processesPanel.add(getProcessPanel(name.getText(), burst.getText(),priority.getText(),arrival.getText()));
                    processesPanel.revalidate();
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

        public JPanel getProcessPanel(String processName, String burstTime, String priority, String quantum)
        {
            // TODO idk just figure out a solution for a proper color box
            JPanel square = new JPanel();
            square.setLayout(null);
            square.setPreferredSize(new Dimension(5,5));
            square.setMaximumSize(new Dimension(5,5));
            square.setMinimumSize(new Dimension(5,5));
            square.setBackground(availableColors.get(colorIndex));
            colorIndex = (colorIndex + 1) % availableColors.size();

            JPanel p = new JPanel(new GridLayout(1,7));
            p.setPreferredSize(new Dimension(350,50));
            p.add(new JLabel( Integer.toString(scheduler.getSize()) ));
            p.add(square);
            p.add(new JLabel(processName));
            p.add(new JLabel(burstTime));
            p.add(new JLabel(priority));
            p.add(new JLabel(quantum));
            return p;
        }
    }


    private JButton scheduleButton;
    private JComboBox scheduleOptions;
    private JPanel mainPanel;
    private JButton addProcessorButton;
    private JLabel schedulerName;
    private JLabel awatLabel;
    private JLabel atatLabel;
    private JPanel processesPanel;

    GUI()
    {
        availableColors = new ArrayList<Color>();
        availableColors.add(Color.red);
        availableColors.add(Color.blue);
        availableColors.add(Color.orange);
        availableColors.add(Color.yellow);
        availableColors.add(Color.pink);
        availableColors.add(Color.cyan);

        scheduleOptions.addItem("AGAT");
        scheduleOptions.addItem("SRTF");
        scheduleOptions.addItem("SJF");
        scheduleOptions.addItem("Priority");

        FlowLayout layout = new FlowLayout();
        Dimension sd = layout.preferredLayoutSize(processesPanel);
        sd.width = 400;
        processesPanel.setLayout(layout);

        JPanel p = new JPanel(new GridLayout(1,6));
        p.setPreferredSize(new Dimension(350,50));
        p.add(new JLabel("INDEX"));
        p.add(new JLabel("COLOR"));
        p.add(new JLabel("NAME"));
        p.add(new JLabel("BURST"));
        p.add(new JLabel("PRIORITY"));
        p.add(new JLabel("ARRIVAL"));
        processesPanel.add(p);

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
        ScheduleData output = scheduler.schedule();
        awatLabel.setText("AWAT:" + output.avgWaitingTime);
        atatLabel.setText("ATAT:" + output.avgTurnaroundTime);
        drawScheduleGraph(output.processGraphData);
    }

    private void drawScheduleGraph(ArrayList<ProcessGraphData> data)
    {
        ProcessGraph mainPanel = new ProcessGraph(data);
        JFrame frame = new JFrame("Process Execution Graph");
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    private void addNewProcess()
    {
        ProcessWindow window = new ProcessWindow();
    }

    public static void main(String[] args)
    {
        scheduler = new Scheduler();
        //scheduler.addTestData();

        JFrame frame = new JFrame("Scheduler");
        frame.setContentPane(new GUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
