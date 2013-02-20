import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class Agenda extends JDesktopPane
{
    private static Agenda INSTANCE;
    private JFrame frame;
    private JPanel panel;
    private JPanel calendarPanel;
    private JPanel monthSwitchPanel;
    private JPanel dayPanel;
    private JPanel dayNumberPanel;
    private ArrayList<Event> eventArrayList = new ArrayList<Event>();
    private JInternalFrame inFrame;
    private JInternalFrame startSimulationIFrame;
    private Calendar cal;
    private Planner plannerIFrame;
    private String statusMessage = "Everything appears to be working fine.";
    //private javax.swing.Timer timer = new Timer(1000/60, new ActionListener(){
    //    public void actionPerformed(ActionEvent e)
    //    {
    //    	repaint();
    //    }
    //});

    public Agenda()
    {
        frame = new JFrame("Agenda");
        panel = new JPanel();
        inFrame = new JInternalFrame();
        cal = Calendar.getInstance();
        plannerIFrame = new Planner();
        //timer.start();
    }

    public static void main(String args[])
    {
        getInstance().initializeJFrame();
    }

    public static Agenda getInstance()
    {
        if(INSTANCE == null)
            INSTANCE = new Agenda();
        return INSTANCE;
    }

    private void initializeJFrame()
    {
        frame.setDefaultCloseOperation(3);
        frame.setPreferredSize(new Dimension(1280, 720));
        inFrame.setVisible(true);
        inFrame.setResizable(false);
        inFrame.setClosable(false);
        inFrame.setIconifiable(true);
        inFrame.setTitle("Calendar");
        inFrame.setBounds(0, 0, 350, 240);
        initializeJPanel();
        inFrame.setContentPane(panel);
        inFrame.setLocation(new Point((int)frame.getPreferredSize().getWidth() - 350, 0));
        plannerIFrame.setVisible(true);
        plannerIFrame.setResizable(false);
        plannerIFrame.setClosable(false);
        plannerIFrame.setBounds(0, 0, 350, 240);
        plannerIFrame.setIconifiable(true);  
        plannerIFrame.setTitle("Planner"); 
        plannerIFrame.setLocation(new Point((int)frame.getPreferredSize().getWidth() - 350, 240));
        panel.add(calendarPanel);
        startSimulationIFrame = new JInternalFrame();
        startSimulationIFrame.setVisible(true);
        startSimulationIFrame.setResizable(false);
        startSimulationIFrame.setClosable(false);
        startSimulationIFrame.setIconifiable(true);
        startSimulationIFrame.setTitle("Menu");
        startSimulationIFrame.setBounds(0,0,350,180);
        JPanel buttonPanel = new JPanel();
        ImageIcon runImage = new ImageIcon("runButton.png");
        ImageIcon runOverImage = new ImageIcon("runOverButton.gif");
        JButton runButton = new JButton(runImage);
        runButton.setRolloverIcon(runOverImage);
        runButton.setPreferredSize(new Dimension(330,115));
        runButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent actionEvent) 
			{
				//Code to run simulator.
			}
        });
        buttonPanel.add(runButton);
        startSimulationIFrame.setContentPane(buttonPanel);
        startSimulationIFrame.setLocation(new Point((int)frame.getPreferredSize().getWidth() - 350, 480));
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem quitItem = new JMenuItem("Quit");
        saveItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent actionEvent) 
			{
				try 
				{
					JFileChooser chooser = new JFileChooser();
					int option = chooser.showSaveDialog(Agenda.getInstance());
					if(option == JFileChooser.APPROVE_OPTION)
					{
						File fileName = new File(chooser.getSelectedFile() + ".log");
						BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
						for(Event event : eventArrayList)
						{
							out.write(event.getArtist().getName() + "\r\n");
							out.write(String.valueOf(event.getStage().getxLocation()) + "\r\n");
							out.write(String.valueOf(event.getStage().getyLocation()) + "\r\n");
							out.write(String.valueOf(event.getStage().getNumberOfStage()) + "\r\n");
							out.write(String.valueOf(event.getEstimatedPopularity()) + "\r\n");
							out.write(String.valueOf(event.getsHour()) + "\r\n");
							out.write(String.valueOf(event.getsMinute()) + "\r\n");
							out.write(String.valueOf(event.geteHour()) + "\r\n");
							out.write(String.valueOf(event.geteMinute()) + "\r\n");
							out.write("----------" + "\r\n");
						}
						out.close();
						statusMessage = "Your file has been saved succesfully.";
					}
				} 
				catch (Exception e) 
				{
					statusMessage = "An error has occured while writing a file.";
				}
			}
        });
        openItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent actionEvent) 
			{
				JFileChooser chooser = new JFileChooser();
				int option = chooser.showOpenDialog(Agenda.getInstance());
				if(option == JFileChooser.APPROVE_OPTION)
				{
					try {
						String name = "";
						int width = -1;
						int height = -1;
						int stageNumber = -1;
						int estimatedPopularity = -1;
						int sHour = -1;
						int sMinute = -1;
						int eHour = -1;
						int eMinute = -1;
						
						int index = 0;
						String thisLine;
						
						File file = chooser.getSelectedFile();
				        FileInputStream in = new FileInputStream(file);
				        BufferedReader br = new BufferedReader(new InputStreamReader(in));
				        while((thisLine = br.readLine()) != null)
				        {
				        	switch(index)
				        	{
					        	case 0:
					        		name = thisLine;
					        	break;
					        	case 1:
					        		width = Integer.parseInt(thisLine);
					        	break;
					        	case 2:
					        		height = Integer.parseInt(thisLine);
					        	break;
					        	case 3:
					        		stageNumber = Integer.parseInt(thisLine);
					        	break;
					        	case 4:
					        		estimatedPopularity = Integer.parseInt(thisLine);
					        	break;
					        	case 5:
					        		sHour = Integer.parseInt(thisLine);
					        	break;
					        	case 6:
					        		sMinute = Integer.parseInt(thisLine);
					        	break;
					        	case 7:
					        		eHour = Integer.parseInt(thisLine);
					        	break;
					        	case 8:
					        		eMinute = Integer.parseInt(thisLine);
					        	break;
					        	case 9:
					        		Event event = new Event(new Stage(width,height,stageNumber),new Artist(name), estimatedPopularity,sHour,sMinute,eHour,eMinute);
					        		boolean addButton = true;
					            	for(int i = 0; i < eventArrayList.size(); i++)
					            	{
					            		if(event.getArtist().name.equals(eventArrayList.get(i).getArtist().name));
					            		{
					            			if(event.getStage().getNumberOfStage() == eventArrayList.get(i).getStage().getNumberOfStage());
					            			{
					            				if(((eventArrayList.get(i).getsHour()*60) + (eventArrayList.get(i).getsMinute())) == ((event.getsHour()*60) + event.getsMinute()))
					            				{
					            					addButton = false;
					            				}
					            			}
					            		}
					            	}

					        		if(addButton)
					        		{
					        			addButton(event);
					        			statusMessage = "You have loaded a file succesfully.";
					        		}
					        	break;
				        	}
				        	index++;
				        	if(index == 10)
				        		index = 0;
				        }
				        br.close();
					} catch (Exception e) {
						statusMessage = "This file could not be loaded.";
					}
				}
			}
        });
        quitItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent actionEvent) 
			{
				System.exit(0);
			}
        });
        fileMenu.add(saveItem);
        fileMenu.add(openItem);
        fileMenu.add(quitItem);
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent actionEvent) 
			{
        		JOptionPane.showMessageDialog(frame,
        	    "This application has been made by Thom Trignol. \nVersion: 2.1",
        	    "About",
        	    JOptionPane.PLAIN_MESSAGE);
			}
        });
        JMenuItem infoItem = new JMenuItem("Index");
        infoItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent actionEvent) 
			{
        		JOptionPane.showMessageDialog(frame,
        	    "In order to start the application, please load a preset file with events first, or create your own with the planner.\nYou can create your own by filling in the form in the planner, you can then save this by going to file, save and select the path and filename of your file.\nWhen the data has been set, please click the run button on the menu to start the simulation.",
        	    "Index",
        	    JOptionPane.PLAIN_MESSAGE);
			}
        });
        JMenu helpMenu = new JMenu("Help");
        helpMenu.add(aboutItem);
        helpMenu.add(infoItem);
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        startSimulationIFrame.setJMenuBar(menuBar);
        this.add(inFrame);
        this.add(plannerIFrame);
        this.add(startSimulationIFrame);
        panel.setBackground(new Color(-1));
        initializeJPanel();
        JPanel mainPanel = new JPanel(new BorderLayout());
        panel.add(mainPanel);
        frame.setContentPane(this);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel initializeJPanel()
    {
        calendarPanel = new JPanel(new BorderLayout());
        calendarPanel.setBackground(new Color(-1));
        calendarPanel.setLayout(new BorderLayout());
        calendarPanel.add(initializeJPanelMonthSwitcher(), "North");
        calendarPanel.add(initializeJPanelDays(), "Center");
        calendarPanel.add(initializeJPanelDayNumbers(), "South");
        return calendarPanel;
    }

    private JPanel initializeJPanelMonthSwitcher()
    {
        monthSwitchPanel = new JPanel();
        monthSwitchPanel.setBackground(new Color(-1));
        monthSwitchPanel.setLayout(new GridLayout(0, 3));
        ImageIcon leftArrowImage = new ImageIcon("LeftArrow.png");
        ImageIcon rightArrowImage = new ImageIcon("RightArrow.png");
        JButton leftArrow = new JButton(leftArrowImage);
        JButton rightArrow = new JButton(rightArrowImage);
        leftArrow.setBorder(null);
        leftArrow.setBackground(new Color(-1));
        rightArrow.setBorder(null);
        rightArrow.setBackground(new Color(-1));
        JLabel monthLabel = new JLabel((new StringBuilder()).append((new SimpleDateFormat("MMMMMMMMMMMM")).format(Long.valueOf(cal.getTimeInMillis())))+(" - ")+ (cal.get(Calendar.YEAR)), 0);
        leftArrow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                int month = cal.get(2) - 1;
                cal.set(2, month);
                panel = new JPanel();
                panel.setBackground(new Color(-1));
                initializeJPanel();
                panel.add(calendarPanel);
                inFrame.setContentPane(panel);
                inFrame.validate();
                inFrame.repaint();
            }
        });
        rightArrow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                int month = cal.get(2) + 1;
                cal.set(2, month);
                panel = new JPanel();
                panel.setBackground(new Color(-1));
                initializeJPanel();
                panel.add(calendarPanel);
                inFrame.setContentPane(panel);
                inFrame.validate();
                inFrame.repaint();
            }
        });
        monthSwitchPanel.add(leftArrow);
        monthSwitchPanel.add(monthLabel);
        monthSwitchPanel.add(rightArrow);
        return monthSwitchPanel;
    }

    private JPanel initializeJPanelDays()
    {
        dayPanel = new JPanel();
        dayPanel.setBackground(new Color(-1));
        dayPanel.setLayout(new FlowLayout());
        dayPanel.add(new JLabel("ma"));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel("di"));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel("wo"));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel("do"));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel("vr"));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel("za"));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel(""));
        dayPanel.add(new JLabel("zo"));
        return dayPanel;
    }

    public JPanel initializeJPanelDayNumbers()
    {
        dayNumberPanel = new JPanel();
        dayNumberPanel.setBackground(new Color(-1));
        dayNumberPanel.setLayout(new GridLayout(0, 7));
        cal.set(5, 1);
        int dayThatThisMonthsStarts = 0;
        String firstDayOfMonth = (new SimpleDateFormat("EEEEEEEEEE")).format(Long.valueOf(cal.getTimeInMillis()));
        switch(firstDayOfMonth)
        {
        case "maandag": 
            dayThatThisMonthsStarts = 0;
            break;

        case "dinsdag": 
        	dayThatThisMonthsStarts = 1;
            break;

        case "woensdag": 
        	dayThatThisMonthsStarts = 2;
            break;

        case "donderdag": 
        	dayThatThisMonthsStarts = 3;
            break;

        case "vrijdag": 
        	dayThatThisMonthsStarts = 4;
            break;

        case "zaterdag": 
        	dayThatThisMonthsStarts = 5;
            break;

        case "zondag": 
        	dayThatThisMonthsStarts = 6;
            break;
        }
        cal.set(2, cal.get(2) - 1);
        for(int i = 0 - dayThatThisMonthsStarts; i < 0; i++)
        {
            JButton dayButton = new JButton(Integer.toString(cal.getActualMaximum(5) + (i + 1)));
            dayButton.setBackground(new Color(0xaaaaaa));
            dayButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionevent)
                {
                	
                }
            });
            dayNumberPanel.add(dayButton);
        }

        cal.set(2, cal.get(2) + 1);
        for(int i = 1; i < cal.getActualMaximum(5) + 1; i++)
        {
            JButton dayButton = new JButton(Integer.toString(i));
            dayButton.setBackground(new Color(0xffffff));
            dayButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionevent)
                {
                	
                }
            });
            dayNumberPanel.add(dayButton);
        }
        return dayNumberPanel;
    }
    
	public void paintComponent(Graphics g)
    {	
		g.setColor(new Color(0xaaaaaa));
		
		for(int i = 0; i <= 25; i = i + 2)
		{
			g.fillRect(50, (25 + (i * 25)), 850, 25);
		}

		g.setColor(new Color(0x000000));
    	g.drawLine(50,25,50,650);
    	g.drawLine(900,25,900,650);
    	g.drawLine(50,25,900,25);
    	
    	g.drawLine(150,25,150,650);
    	g.drawLine(400,25,400,650);
    	g.drawLine(650,25,650,650);
    	
    	for(int i = 0; i <= 24; i++)
    	{
    		g.drawLine(50, 50+(i*25), 900, 50+(i*25));
    	}
    	
    	for(int i = 0; i <= 23; i++)
    	{
    		g.drawString(i+":00",50+5,50+((i+1)*25)-5);
    	}

    	g.drawString("Time",54,48);
    	g.drawString("Stage 1",152,48);
    	g.drawString("Stage 2",402,48);
    	g.drawString("Stage 3",652,48);
    	g.drawString(statusMessage, 930, 675);
    }

	public void addToArrayList(Event event) {
		eventArrayList.add(event);
	}
	
	public ArrayList<Event> getEventArrayList()
	{
		return eventArrayList;
	}
	
	public void addButton(Event event)
	{
		final JButton button = new JButton(event.getArtist().getName());
		button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionevent)
            {
            	boolean areYouSure = false;
            	int n = JOptionPane.showConfirmDialog(
            		    frame,
            		    "Would you like to remove this event?",
            		    "Confirmation",
            		    JOptionPane.YES_NO_OPTION);
            	if(n == 0)
            		areYouSure = true;
            	if(areYouSure)
            	{
	            	Container parent = button.getParent();
	            	parent.remove(button);
	            	((JComponent) parent).revalidate();
	            	parent.repaint();
	            	parent.validate();
	            	for(int i = 0; i < eventArrayList.size(); i++)
	            	{
	            		if(button.getText().equals(eventArrayList.get(i).getArtist().name));
	            		{
	            			if((((button.getX() - 150)/250)+1) == eventArrayList.get(i).getStage().getNumberOfStage())
	            			{
	            				if((((eventArrayList.get(i).getsHour() * 25) + 55) + (eventArrayList.get(i).getsMinute() * 25/60))-5 == button.getY())
	            					eventArrayList.remove(i);
	            			}
	            		}
	            	}
	            	statusMessage = "You have deleted an event.";
            	}
            }
        });
		
		int x = ((event.getStage().getNumberOfStage() - 1)*250)+150;
		int y = (((event.getsHour() * 25) + 55) + (event.getsMinute() * 25/60));
		
		int minutes;
		int hours;
		
		if(event.geteMinute() - event.getsMinute() < 0){
			minutes = 60 + (event.geteMinute() - event.getsMinute());
			hours = -1;
		}else{
			minutes = event.geteMinute() - event.getsMinute();
			hours = 0;
		}
		int height = (((event.geteHour() - event.getsHour())+ hours )*60) + minutes;
		
		button.setBounds(x, y - 5, 250, (height*25/60));
		Border border = BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		button.setBorder(border);
		button.setBackground(new Color(0xff0000));
		button.setToolTipText("<html>" + "Artist Name: " + event.getArtist().getName() + "<br>" 
							 + "Stage Width: " + event.getStage().getxLocation() + "<br>" 
							 + "Stage Height: " + event.getStage().getyLocation() + "<br>"
							 + "Stage Number: " + event.getStage().getNumberOfStage() + "<br>"
							 + "Estimated Popularity: " + event.getEstimatedPopularity() + "<br>"
							 + "Starting Hours: " + event.getsHour() + "<br>"
							 + "Starting Minutes: " + event.getsMinute() + "<br>"
							 + "Ending Hours: " + event.geteHour() + "<br>"
							 + "Ending Minutes: " + event.geteMinute() + "<br>"
							 + "</html>");
		
		boolean addButton = true;
    	for(int i = 0; i < eventArrayList.size(); i++)
    	{
    		if(button.getText().equals(eventArrayList.get(i).getArtist().name));
    		{
    			if((((button.getX() - 150)/250)+1) == eventArrayList.get(i).getStage().getNumberOfStage())
    			{
    				if((((eventArrayList.get(i).getsHour() * 25) + 55) + (eventArrayList.get(i).getsMinute() * 25/60))-5 == button.getY())
    					addButton = false;
    			}
    		}
    	}
    	if(addButton)
		{
    		this.add(button);
    		eventArrayList.add(event);
		}
		ToolTipManager.sharedInstance().setDismissDelay(10000);
		
		
    }

	public void setStatusMessage(String string) {
		this.statusMessage = string;
	}
	
}