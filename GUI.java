/*
 * Decompiled with CFR 0.139.
 */
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import javax.swing.AbstractButton;

public class GUI {
    public static void main(String[] args) {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        String[] fontNames = ge.getAvailableFontFamilyNames();

        Font[] allFonts = ge.getAllFonts();

        Color blue = new Color(91, 192, 235);
        Color green = new Color(155, 197, 61);
        Color orange = new Color(250, 131, 33);

        final ArrayList<String> classNames = new ArrayList<String>();
        final JComboBox<String> options = new JComboBox<String>();
        String classes = GUI.reader("allClasses");
        classes = classes.replace("\n", ",");
        System.out.println(classes);
        String main = "";
        for (int i = 0; i < classes.length(); ++i) {
            String temp = classes.substring(i, i + 1);
            if (!temp.equals(",")) {
                main = String.valueOf(main) + temp;
            } else {
                classNames.add(main);
                main = "";
            }
            System.out.println(main);
        }
        final JFrame frame = new JFrame("RandomNamePicker(Beta v1.0.1)");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(3);
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setSize(700, 500);
        mainPanel.setBackground(blue);
        mainPanel.setVisible(true);
        frame.add(mainPanel);
        GUI.makeBackground(mainPanel);
        GUI.addCombo(options, classNames, mainPanel);
        frame.setSize(700, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.repaint();
        options.setFont(new Font("Advanced LED Board-7", Font.PLAIN, 12));
        options.setSize(200, 30);
        options.setLocation(470, 220);
        options.setForeground(orange);
        mainPanel.add(options);
        final JLabel name = new JLabel(" Select a Class and Generate...");
        name.setLocation(10, 25);
        name.setSize(500, 100);
        name.setFont(new Font("Advanced LED Board-7", Font.PLAIN, 100));
        name.setOpaque(true);
        name.setBackground(Color.WHITE);
        name.setForeground(orange);
        mainPanel.add(name);
        final ArrayList names = new ArrayList();
        JButton bgenerate = new JButton("GENERATE");
        bgenerate.setFont(new Font("Advanced LED Board-7", Font.PLAIN, 10));
        bgenerate.setBackground(green);
        bgenerate.setForeground(green);
        bgenerate.setVerticalTextPosition(AbstractButton.CENTER);
        bgenerate.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                names.clear();
                String chooseNames = GUI.reader((String)options.getSelectedItem());
                chooseNames = chooseNames.replaceAll("\n", ",");
                String main = "";
                for (int i = 0; i < chooseNames.length(); ++i) {
                    String temp = chooseNames.substring(i, i + 1);
                    if (!temp.equals(",")) {
                        main = String.valueOf(main) + temp;
                        continue;
                    }
                    names.add(main);
                    main = "";
                }
                if (names.size() > 0) {
                    name.setText(" " + (String)names.get((int)(Math.random() * (double)(names.size() + 1))));
                } else {
                    name.setText(" No Name Yet...");
                }
                mainPanel.repaint();
                frame.repaint();
            }
        });
        GUI.makeGenerate(mainPanel, bgenerate, name);
        MenuBar menuBar = new MenuBar();
        Menu mode = new Menu("Mode");
        Menu delete = new Menu("Delete");
        MenuItem add = new MenuItem("Add Students");
        add.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                GUI.addStudents(mainPanel, (String)options.getSelectedItem());
                GUI.addCombo((JComboBox<String>)options, (ArrayList<String>)classNames, mainPanel);
            }
        });
        MenuItem generate = new MenuItem("Generate");
        generate.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                GUI.makeBackground(mainPanel);
                GUI.makeGenerate(mainPanel, bgenerate, name);
                GUI.addCombo((JComboBox<String>)options, (ArrayList<String>)classNames, mainPanel);
                frame.repaint();
            }
        });
        MenuItem className = new MenuItem("Add Class");
        className.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame classPop = new JFrame("Add Class");
                classPop.setVisible(true);
                classPop.setSize(250, 220);
                classPop.setLayout(null);
                GUI.makeClass(classPop, classNames, mainPanel, options);
                classPop.repaint();
                mainPanel.repaint();
                frame.repaint();
            }
        });
        MenuItem deleteClass = new MenuItem("Delete Class");
        deleteClass.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                final JFrame deleteC = new JFrame("Delete Class");
                deleteC.setSize(250, 250);
                deleteC.setLayout(null);
                deleteC.setVisible(true);
                JButton delete = new JButton("Delete");
                final JComboBox<String> selectD = new JComboBox<String>();
                GUI.addCombo(selectD, (ArrayList<String>)classNames, deleteC);
                selectD.setSize(210, 35);
                selectD.setLocation(10, 30);
                delete.setSize(100, 25);
                delete.setLocation(70, 100);
                delete.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            File toDelete = new File(String.valueOf((String)selectD.getSelectedItem()) + ".txt");
                            toDelete.delete();
                        }
                        catch (Exception f) {
                            f.printStackTrace();
                        }
                        classNames.remove(selectD.getSelectedItem());
                        GUI.clear("allClasses");
                        for (int i = 0; i < classNames.size(); ++i) {
                            GUI.writer((String)classNames.get(i), "allClasses");
                        }
                        GUI.addCombo((JComboBox<String>)selectD, (ArrayList<String>)classNames, deleteC);
                        GUI.addCombo((JComboBox<String>)options, (ArrayList<String>)classNames, mainPanel);
                        deleteC.repaint();
                        mainPanel.repaint();
                        frame.repaint();
                    }
                });
                deleteC.add(delete);
                deleteC.add(selectD);
                deleteC.repaint();
            }

        });
        mode.add(add);
        mode.add(generate);
        mode.add(className);
        delete.add(deleteClass);
        menuBar.add(mode);
        menuBar.add(delete);
        frame.setMenuBar(menuBar);
        frame.repaint();
    }

    public static String reader(String file) {
        String returnString = "";
        String line = null;
        try {
            FileReader reader = new FileReader(String.valueOf(file) + ".txt");
            BufferedReader bReader = new BufferedReader(reader);
            while ((line = bReader.readLine()) != null) {
                System.out.print(String.valueOf(line) + "\n");
                returnString = String.valueOf(returnString) + line + "\n";
            }
            bReader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return returnString;
    }

    public static void writer(String toWrite, String loc) {
        try {
            PrintWriter write = new PrintWriter(new FileWriter(String.valueOf(loc) + ".txt", true));
            write.write(toWrite);
            write.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clear(String loc) {
        try {
            PrintWriter write = new PrintWriter(new FileWriter(String.valueOf(loc) + ".txt", false));
            write.write("");
            write.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JTextField createText(JPanel frame) {
        JTextField text = new JTextField();
        text.setSize(200, 100);
        text.setLocation(300, 300);
        text.setFont(new Font("Advanced LED Board-7", Font.PLAIN, 12));
        frame.add(text);
        frame.repaint();
        return text;
    }

    public static JTextField createText(JFrame frame) {
        JTextField text = new JTextField();
        text.setSize(200, 100);
        text.setLocation(300, 300);
        frame.add(text);
        frame.repaint();
        return text;
    }

    public static void addStudents(JPanel frame, final String location) {
        Color green = new Color(155, 197, 61);
        final JTextField text = GUI.createText(frame);
        JButton submit = new JButton("Submit");
        submit.setSize(100, 50);
        submit.setFont(new Font("Advanced LED Board-7", 0, 24));
        submit.setBackground(Color.WHITE);
        submit.setForeground(green);
        submit.setLocation(350, 400);
        submit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(text.getText());
                GUI.writer(String.valueOf(text.getText()) + "\r\n", location);
                text.setText("");
            }
        });
        GUI.makeBackground(frame);
        frame.add(submit);
        frame.repaint();
    }

    public static void makeClass(JFrame frame, final ArrayList<String> classNames, final JPanel panel, final JComboBox<String> options) {
        frame.setBackground(new Color(91, 192, 235));
        final JTextField text = new JTextField();
        text.setSize(200, 75);
        text.setFont(new Font("Advanced LED Board-7", 0, 24));
        text.setLocation(10, 10);
        frame.add(text);
        JButton submit = new JButton("Submit");
        submit.setFont(new Font("Advanced LED Board-7", 0, 24));
        submit.setBackground(Color.WHITE);
        submit.setForeground(new Color(155, 197, 61));
        submit.setSize(100, 50);
        submit.setLocation(60, 105);
        submit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(text.getText());
                GUI.writer("", text.getText());
                GUI.writer(String.valueOf(text.getText()) + "\r\n", "allClasses");
                classNames.add(text.getText());
                GUI.addCombo((JComboBox<String>)options, (ArrayList<String>)classNames, panel);
                text.setText("");
            }
        });
        frame.add(submit);
        frame.repaint();
    }

    public static void makeGenerate(JPanel frame, JButton generate, JLabel name) {
        name.setSize(400, 80);
        name.setLocation(50, 25);
        name.setFont(new Font("Advanced LED Board-7", 0, 24));
        frame.add(name);
        generate.setSize(100, 25);
        generate.setLocation(350, 400);
        frame.add(generate);
    }

    public static void makeBackground(JPanel frame) {
        JLabel name = new JLabel("Random Name Picker");
        name.setFont(new Font("Advanced LED Board-7", Font.PLAIN, 42));
        name.setSize(500, 300);
        name.setLocation(20, 80);
        name.setForeground(new Color(253, 231, 76));
        frame.add(name);
        frame.repaint();
    }

    public static void addCombo(JComboBox<String> options, ArrayList<String> classNames, JPanel frame) {
        options.removeAllItems();
        for (int i = 0; i < classNames.size(); ++i) {
            options.addItem(classNames.get(i));
        }
        frame.add(options);
        frame.repaint();
    }

    public static void addCombo(JComboBox<String> options, ArrayList<String> classNames, JFrame frame) {
        options.removeAllItems();
        for (int i = 0; i < classNames.size(); ++i) {
            options.addItem(classNames.get(i));
        }
        frame.add(options);
        frame.repaint();
    }

    public static JComboBox<String> makeSelect(JPanel frame, JComboBox<String> box) {
        return box;
    }

}

