package fun.galesi.studentpicker;

import arduino.Arduino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI
{

	private static final Color BLUE = new Color(91, 192, 235);
	private static final Color GREEN = new Color(155, 197, 61);
	private static final Color ORANGE = new Color(250, 131, 33);

	private static JComboBox<String> options;

	private static JFrame frame;
	private static JPanel mainPanel;
	private static JLabel name;

	public GUI()
	{

		RandomStudent randomStudent = new RandomStudent();
		options = new JComboBox<>();

		frame = new JFrame("RandomNamePicker(Beta v1.0.1)");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(3);

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setSize(700, 500);
		mainPanel.setBackground(BLUE);
		mainPanel.setVisible(true);
		frame.add(mainPanel);
		GUI.makeBackground(mainPanel);
		GUI.addCombo(options, RandomStudent.getPeriodNames(), mainPanel);
		frame.setSize(700, 500);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.repaint();
		options.setFont(new Font("Advanced LED Board-7", Font.PLAIN, 12));
		options.setSize(200, 30);
		options.setLocation(470, 220);
		options.setForeground(ORANGE);
		mainPanel.add(options);

		name = new JLabel(" Select a Class and Generate...");
		name.setLocation(10, 25);
		name.setSize(500, 100);
		name.setFont(new Font("Advanced LED Board-7", Font.PLAIN, 100));
		name.setOpaque(true);
		name.setBackground(Color.WHITE);
		name.setForeground(ORANGE);
		mainPanel.add(name);

		JButton bgenerate = new JButton("GENERATE");
		bgenerate.setFont(new Font("Advanced LED Board-7", Font.PLAIN, 10));
		bgenerate.setBackground(GREEN);
		bgenerate.setForeground(GREEN);
		bgenerate.setVerticalTextPosition(AbstractButton.CENTER);
		bgenerate.addActionListener(e -> {
			//names.clear();

			Period period = RandomStudent.getPeriodByName(options.getSelectedItem().toString());


			if (period.getStudentNames().size() > 0)
			{
				String chosenName = period.getRandomStudent();
				name.setText(" " + chosenName);
			} else
			{
				name.setText(" No Name Yet...");
			}


			mainPanel.repaint();
			frame.repaint();
		});

		GUI.makeGenerate(mainPanel, bgenerate, name);
		MenuBar menuBar = new MenuBar();
		Menu mode = new Menu("Mode");
		Menu delete = new Menu("Delete");
		MenuItem add = new MenuItem("Add Students");
		add.addActionListener(e -> {
			mainPanel.removeAll();
			GUI.addCombo(options, RandomStudent.getPeriodNames(), mainPanel);
			GUI.addStudents(mainPanel);
		});

		MenuItem generate = new MenuItem("Generate");
		generate.addActionListener(e -> {
			mainPanel.removeAll();
			GUI.makeBackground(mainPanel);
			GUI.makeGenerate(mainPanel, bgenerate, name);
			GUI.addCombo(options, RandomStudent.getPeriodNames(), mainPanel);
			frame.repaint();
		});

		MenuItem className = new MenuItem("Add Class");
		className.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				JFrame classPop = new JFrame("Add Class");
				classPop.setVisible(true);
				classPop.setSize(250, 220);
				classPop.setLayout(null);
				GUI.makeClass(classPop, mainPanel, options);
				classPop.repaint();
				mainPanel.repaint();
				frame.repaint();
			}
		});

		MenuItem deleteClass = new MenuItem("Delete Class");
		deleteClass.addActionListener(e -> {
			final JFrame deleteC = new JFrame("Delete Class");
			deleteC.setSize(250, 250);
			deleteC.setLayout(null);
			deleteC.setVisible(true);
			JButton delete1 = new JButton("Delete");
			final JComboBox<String> selectD = new JComboBox<>();
			GUI.addCombo(selectD, RandomStudent.getPeriodNames(), deleteC);
			selectD.setSize(210, 35);
			selectD.setLocation(10, 30);
			delete1.setSize(100, 25);
			delete1.setLocation(70, 100);
			delete1.addActionListener(e1 -> {
				RandomStudent.removePeriod(selectD.getSelectedItem().toString());

				GUI.addCombo(selectD, RandomStudent.getPeriodNames(), deleteC);
				GUI.addCombo(options, RandomStudent.getPeriodNames(), mainPanel);
				deleteC.repaint();
				mainPanel.repaint();
				frame.repaint();
			});
			deleteC.add(delete1);
			deleteC.add(selectD);
			deleteC.repaint();
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


	public static JTextField createText(JPanel frame)
	{
		JTextField text = new JTextField();
		text.setSize(200, 100);
		text.setLocation(300, 300);
		text.setFont(new Font("Advanced LED Board-7", Font.PLAIN, 12));
		frame.add(text);
		frame.repaint();
		return text;
	}

	public static JTextField createText(JFrame frame)
	{
		JTextField text = new JTextField();
		text.setSize(200, 100);
		text.setLocation(300, 300);
		frame.add(text);
		frame.repaint();
		return text;
	}

	public static void addStudents(JPanel frame)
	{
		Color green = new Color(155, 197, 61);
		final JTextField text = GUI.createText(frame);
		JButton submit = new JButton("Submit");
		submit.setSize(100, 50);
		submit.setFont(new Font("Advanced LED Board-7", 0, 24));
		submit.setBackground(Color.WHITE);
		submit.setForeground(green);
		submit.setLocation(350, 400);
		submit.addActionListener(e -> {
			System.out.println(text.getText());
			RandomStudent.getPeriodByName(options.getSelectedItem().toString()).addStudent(text.getText());
			text.setText("");
		});
		GUI.makeBackground(frame);
		frame.add(submit);
		frame.repaint();
	}

	public static void makeClass(JFrame frame, final JPanel panel, final JComboBox<String> options)
	{
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
		submit.addActionListener(e -> {

			String periodName = text.getText();
			System.out.println(periodName);

			RandomStudent.addPeriod(periodName);

			GUI.addCombo(options, RandomStudent.getPeriodNames(), panel);
			text.setText("");
		});
		frame.add(submit);
		frame.repaint();
	}

	public static void makeGenerate(JPanel frame, JButton generate, JLabel name)
	{
		name.setSize(400, 80);
		name.setLocation(50, 25);
		name.setFont(new Font("Advanced LED Board-7", 0, 24));
		frame.add(name);
		generate.setSize(100, 25);
		generate.setLocation(350, 400);
		frame.add(generate);
	}

	public static void makeBackground(JPanel frame)
	{
		JLabel name = new JLabel("Random Name Picker");
		name.setFont(new Font("Advanced LED Board-7", Font.PLAIN, 42));
		name.setSize(500, 300);
		name.setLocation(20, 80);
		name.setForeground(new Color(253, 231, 76));
		frame.add(name);
		frame.repaint();
	}

	public static void addCombo(JComboBox<String> options, ArrayList<String> classNames, JPanel frame)
	{
		options.removeAllItems();
		for (int i = 0; i < classNames.size(); ++i)
		{
			options.addItem(classNames.get(i));
		}
		frame.add(options);
		frame.repaint();
	}

	public static void addCombo(JComboBox<String> options, ArrayList<String> classNames, JFrame frame)
	{
		options.removeAllItems();
		for (int i = 0; i < classNames.size(); ++i)
		{
			options.addItem(classNames.get(i));
		}
		frame.add(options);
		frame.repaint();
	}

	public static JComboBox<String> makeSelect(JPanel frame, JComboBox<String> box)
	{
		return box;
	}

}