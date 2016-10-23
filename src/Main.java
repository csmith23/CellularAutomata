/**
 * Created by Coleman on 10/28/2015.
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

enum Neighborhood_Type {
    Wolfram, vonNeumann, Moore
}

enum Value_Type {
    SUM, POINT
}

class Entry<L, R> {
    private L left;
    private R right;

    public Entry(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L get_0() {
        return left;
    }
    public R get_1() {
        return right;
    }

    public void set_0(L left) {
        this.left = left;
    }
    public void set_1(R right) {
        this.right = right;
    }
}

public class Main extends JFrame {
    static int READ = 0;
    static int DESIRED = 1;
    static int NEXT = -2;
    static int DEFAULT = -1;
    public static int screenWidth = 800;
    public static int screenHeight = 800;
    public static Hashtable<Integer, Color> colors;
    public static Hashtable<Integer, Entry<Neighborhood_Type, Value_Type>> read;
    public static Hashtable<Integer, ArrayList<int[/**state to read, desired value 1, desired value 2, next value if conditions met, chance that the jump will occur*/]>> jump;
//                                                                              desired value 1 and 2 are inclusive
//                                      series of sets of neighborhood states to jump to the next state

    public static void main(String[] args) {
        colors.put(0, Color.WHITE);
        colors.put(1, Color.DARK_GRAY);

        read.put(1, new Entry<Neighborhood_Type, Value_Type>(Neighborhood_Type.Moore, Value_Type.SUM));


        /**Conways Game of Life, S23/B3*/
        jump = new Hashtable<Integer, ArrayList<int[]>>();
        ArrayList<int[]> list = new ArrayList<int[]>();
        list.add(new int[]{1, 3, 3, 1, 1});
        jump.put(0, list);
        list = new ArrayList<int[]>();
        list.add(new int[]{1, 0, 1, 0, 1});
        list.add(new int[]{1, 4, 8, 0, 1});
        jump.put(1, list);

        new Main().run(200, 200);
    }

    public void run(int width, int height) {

    }
}
