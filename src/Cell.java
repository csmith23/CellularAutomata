/**
 * Created by Coleman on 10/28/2015.
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Set;

class Panel extends JPanel {
    int x, y;
    int width;
    int height;
    Color color;

    public Panel(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void paintComponenet(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(x * width, y * height, width, height);
    }
}

public class Cell {
    private int state = 0;
    private int x, y;
    private Panel panel;

    public Cell(int x, int y, int width, int height) {
        panel = new Panel(x, y, width, height);
        this.x = x;
        this.y = y;
    }

    public void nextState(Hashtable<Integer/**read state*/, Integer/**neighborhood*/> current_neighborhood) {
        ArrayList<ArrayList<int[/**4*/]>> jump = Main.jump.get(state);
        boolean successful = true;
        for(int i = 0; i < Main.jump.size(); i++) {
            successful = true;
            ArrayList<int[]> current_set = jump.get(i);
            for(int j = 0; j < current_set.size() - 1; j++) {
                int[] current_requirements = current_set.get(j);
                if(current_neighborhood.get(current_requirements[Main.READ]) != current_requirements[Main.DESIRED]) {
                    successful = false;
                    break;
                }
            }
            if(successful) {
                state = current_set.get(Main.NEXT)[0];
                panel.color = Main.colors.get(state);
                break;
            }
        }
        if(!successful) {
            state = jump.get(Main.DEFAULT).get(0)[0];
        }
        panel.repaint();
    }

    public Panel getPanel() {
        return panel;
    }
}

/**
 * Neighborhood types: Wolfram, vonNeumann, Moore
 * Value types: sum, point
 * Config:
 * -color{state: color}
 * -read{state: (neighborhood_type, value_type)}
 * -jump{state(current): ({state(read): (value(desired), state(next))}, state(default))}*/