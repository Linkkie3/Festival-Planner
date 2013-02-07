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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
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
    private Calendar cal;
    private Planner plannerIFrame;
    private String statusMessage = "Everything appears to be working fine.";
    private javax.swing.Timer timer = new Timer(1000/60, new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
        	repaint();
        }
    });

    public Agenda()
    {
        frame = new JFrame("Agenda");
        panel = new JPanel();
        inFrame = new JInternalFrame();
        cal = Calendar.getInstance();
        plannerIFrame = new Planner();
        timer.start();
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
        this.add(inFrame);
        this.add(plannerIFrame);
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
        int day = -1;
        switch(firstDayOfMonth)
        {
        case "maandag": 
            if(firstDayOfMonth.equals("maandag"))
                day = 0;
            break;

        case "dinsdag": 
            if(firstDayOfMonth.equals("dinsdag"))
                day = 1;
            break;

        case "woensdag": 
            if(firstDayOfMonth.equals("woensdag"))
                day = 2;
            break;

        case "donerdag": 
            if(firstDayOfMonth.equals("donderdag"))
                day = 3;
            break;

        case "vrijdag": 
            if(firstDayOfMonth.equals("vrijdag"))
                day = 4;
            break;

        case "zaterdag": 
            if(firstDayOfMonth.equals("zaterdag"))
                day = 5;
            break;

        case "zondag": 
            if(firstDayOfMonth.equals("zondag"))
                day = 6;
            break;
        }
        switch(day)
        {
        case 0: // '\0'
            dayThatThisMonthsStarts = 0;
            break;

        case 1: // '\001'
            dayThatThisMonthsStarts = 1;
            break;

        case 2: // '\002'
            dayThatThisMonthsStarts = 2;
            break;

        case 3: // '\003'
            dayThatThisMonthsStarts = 3;
            break;

        case 4: // '\004'
            dayThatThisMonthsStarts = 4;
            break;

        case 5: // '\005'
            dayThatThisMonthsStarts = 5;
            break;

        case 6: // '\006'
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
    	g.drawLine(50,25,50,675);
    	g.drawLine(900,25,900,675);
    	g.drawLine(50,25,900,25);
    	
    	g.drawLine(150,25,150,675);
    	g.drawLine(400,25,400,675);
    	g.drawLine(650,25,650,675);
    	
    	for(int i = 0; i <= 25; i++)
    	{
    		g.drawLine(50, 50+(i*25), 900, 50+(i*25));
    	}
    	
    	for(int i = 0; i <= 24; i++)
    	{
    		g.drawString(i+":00",50+5,50+((i+1)*25)-5);
    	}

    	g.drawString("Time",54,48);
    	g.drawString("Stage 1",152,48);
    	g.drawString("Stage 2",402,48);
    	g.drawString("Stage 3",652,48);
    	g.drawString(statusMessage, 925, 670);
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
		this.add(button);
		eventArrayList.add(event);
    }

	public void setStatusMessage(String string) {
		this.statusMessage = string;
	}
}
