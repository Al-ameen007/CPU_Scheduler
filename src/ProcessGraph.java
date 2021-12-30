import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class ProcessGraph extends JPanel {

    private static int RECT_HEIGHT = 50;
    private static int STEP_LENGTH = 30;
    private ArrayList<ProcessGraphData> data;

    ProcessGraph(ArrayList<ProcessGraphData> data)
    {
        this.data = data;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int totalLength = data.size();
        int currentProcessSegmentLength = 0;
        String lastP = data.get(0).processName;
        int rectX = 25, rectY = 25;
        for (int i = 0; i < totalLength; i++)
        {
            ProcessGraphData p = data.get(i);
            if(p.processName != lastP || i == totalLength - 1)
            {
                g.setColor(data.get(i-1).color);
                g.fillRect(rectX + 1, rectY + 1, currentProcessSegmentLength - 1, RECT_HEIGHT - 1);
                g.drawRect(rectX, rectY, currentProcessSegmentLength, RECT_HEIGHT);
                g.setColor(Color.black);
                g.drawLine(rectX, rectY, rectX, rectY + (int)(RECT_HEIGHT * 1.35f));
                g.drawString(Integer.toString(i-1), rectX + 2, rectY + (int)(RECT_HEIGHT * 1.35f));
                rectX += currentProcessSegmentLength;
                currentProcessSegmentLength = 0;
                if(i == totalLength - 1)
                {
                    g.drawLine(rectX, rectY, rectX, rectY + (int)(RECT_HEIGHT * 1.35f));
                    g.drawString(Integer.toString(totalLength), rectX + 2, rectY + (int)(RECT_HEIGHT * 1.35f));
                }
            }
            currentProcessSegmentLength += STEP_LENGTH;
            lastP = p.processName;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        // so that our GUI is big enough
        return new Dimension(500, 150);
    }
}