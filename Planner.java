import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class Planner extends JInternalFrame{
	private JPanel panel = new JPanel(new BorderLayout());
	public Planner()
	{
		super("Planner");
		initializePanel();
		this.setContentPane(panel);
	}
	public void initializePanel()
	{
		final JTextField nameField = new JTextField("Insert the artist's name here");
		nameField.addMouseListener(new MouseAdapter(){
		   public void mouseReleased(MouseEvent e) {
			    if(nameField.getText().equals("Insert the artist's name here"))
			   		nameField.setText("");
			   }
			});
		nameField.setPreferredSize(new Dimension(200, 20));
		final JTextField stageWidthField = new JTextField("Insert the stage width here");
		stageWidthField.addMouseListener(new MouseAdapter(){
			   public void mouseReleased(MouseEvent e) {
				    if(stageWidthField.getText().equals("Insert the stage width here"))
				    	stageWidthField.setText("");
				   }
				});
		
		final JTextField stageHeightField = new JTextField("Insert the stage height here");
		stageHeightField.addMouseListener(new MouseAdapter(){
			   public void mouseReleased(MouseEvent e) {
				    if(stageHeightField.getText().equals("Insert the stage height here"))
				    	stageHeightField.setText("");
				   }
				});
		
		final JTextField stageNumberField = new JTextField("Insert the stage number here");
		stageNumberField.addMouseListener(new MouseAdapter(){
			   public void mouseReleased(MouseEvent e) {
				    if(stageNumberField.getText().equals("Insert the stage number here"))
				    	stageNumberField.setText("");
				   }
				});
		
		final JTextField estimatedPopularityField = new JTextField("Insert the estmiated popularity here");
		estimatedPopularityField.addMouseListener(new MouseAdapter(){
			   public void mouseReleased(MouseEvent e) {
				    if(estimatedPopularityField.getText().equals("Insert the estmiated popularity here"))
				    	estimatedPopularityField.setText("");
				   }
				});
		
		final JTextField startHourField = new JTextField("Insert the hour it starts here");
		startHourField.addMouseListener(new MouseAdapter(){
			   public void mouseReleased(MouseEvent e) {
				    if(startHourField.getText().equals("Insert the hour it starts here"))
				    	startHourField.setText("");
				   }
				});
		
		final JTextField startMinuteField = new JTextField("Insert the minute it starts here");
		startMinuteField.addMouseListener(new MouseAdapter(){
			   public void mouseReleased(MouseEvent e) {
				    if(startMinuteField.getText().equals("Insert the minute it starts here"))
				    	startMinuteField.setText("");
				   }
				});
		
		final JTextField endHourField = new JTextField("Insert the hour it ends here");
		endHourField.addMouseListener(new MouseAdapter(){
			   public void mouseReleased(MouseEvent e) {
				    if(endHourField.getText().equals("Insert the hour it ends here"))
				    	endHourField.setText("");
				   }
				});
		
		final JTextField endMinuteField = new JTextField("Insert the minute it ends here");
		endMinuteField.addMouseListener(new MouseAdapter(){
			   public void mouseReleased(MouseEvent e) {
				    if(endMinuteField.getText().equals("Insert the minute it ends here"))
				    	endMinuteField.setText("");
				   }
				});
		
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionevent)
            {
            	String name = nameField.getText();
            	String stageWidth = stageWidthField.getText();
            	String stageHeight = stageHeightField.getText();
            	String stageNumber = stageNumberField.getText();
            	String estimatedPopularity = estimatedPopularityField.getText();
            	String startHour = startHourField.getText();
            	String startMinutes = startMinuteField.getText();
            	String endHour = endHourField.getText();
            	String endMinute = endMinuteField.getText();
            	try
            	{
            		boolean add = true;
            		int width = -1;
            		if((Integer.parseInt(stageWidth) > 0))
            			width = Integer.parseInt(stageWidth);
            		else{
            			Agenda.getInstance().setStatusMessage("The width of your stage cannot be below 0.");
            			add = false;
            		}
            		int height = -1;
            		if((Integer.parseInt(stageHeight) > 0))
            			height = Integer.parseInt(stageHeight);
            		else{
            			Agenda.getInstance().setStatusMessage("The height of your stage cannot be below 0.");
            			add = false;
            		}
            		int number = -1;
            		if((Integer.parseInt(stageNumber) > 0) && (Integer.parseInt(stageNumber) <= 3))
            			number = Integer.parseInt(stageNumber);
            		else{
            			Agenda.getInstance().setStatusMessage("The stagenumber must be between 0 and 3");
            			add = false;
            		}
            		int estimatedPop = -1;
            		if(Integer.parseInt(estimatedPopularity) > 0)
            			estimatedPop = Integer.parseInt(estimatedPopularity);
            		else{
            			Agenda.getInstance().setStatusMessage("The estimated popularity cannot be below 0.");
            			add = false;
            		}
            		
            		int sHour = -1;
            		if((Integer.parseInt(startHour) <= 25)){	
            			if(Integer.parseInt(startHour) == 25)
            				if(Integer.parseInt(startMinutes) == 0)
            					Integer.parseInt(startHour);
            				else{
                    			Agenda.getInstance().setStatusMessage("Your start time is not correct.");
                				add = false;
            			}else
                			sHour = Integer.parseInt(startHour);
            		}else{
            			Agenda.getInstance().setStatusMessage("Your start time is not correct.");
        				add = false;
            		}
            		int sMinute = -1;
            		if((Integer.parseInt(startMinutes) >= 0) && (Integer.parseInt(startMinutes) <= 60))
            			sMinute = Integer.parseInt(startMinutes);
            		else{
            			Agenda.getInstance().setStatusMessage("Your start time is not correct.");
        				add = false;
            		}

            		int eHour = -1;
            		if((Integer.parseInt(endHour) <= 25)){	
            			if(Integer.parseInt(endHour) == 25)
            			{
            				if(Integer.parseInt(endMinute) == 0)
            					eHour = Integer.parseInt(endHour);
            				else
            				{
                    			Agenda.getInstance().setStatusMessage("Your end time is not correct.");
                				add = false;
            				}
            			}
            			else
                			eHour = Integer.parseInt(endHour);
            		}else{
            			Agenda.getInstance().setStatusMessage("Your end time is not correct.");
        				add = false;
            		}
            		int eMinute = -1;
            		
            		if((Integer.parseInt(endMinute) >= 0) && (Integer.parseInt(endMinute) < 60))
            			eMinute = Integer.parseInt(endMinute);
            		else{
            			Agenda.getInstance().setStatusMessage("Your end time is not correct.");
        				add = false;
            		}
            		
            		if((((eHour*60)+eMinute) <= ((sHour*60)+sMinute)))
            		{
            			Agenda.getInstance().setStatusMessage("Your end time has to be later as your start time.");
        				add = false;
            		}
            			
            		if(add)
            		{
            			Agenda.getInstance().addButton(new Event(new Stage(width,height,number),new Artist(name), estimatedPop, sHour,sMinute, eHour,eMinute));
            			Agenda.getInstance().setStatusMessage("You have succesfully entered a new event.");
            		}
            	}
            	catch(NumberFormatException e)
            	{
            		Agenda.getInstance().setStatusMessage("You have entered wrong data.");
            	}
            }
        });
		JPanel TextFieldPanel = new JPanel(new GridLayout(9,1));
		panel.add(TextFieldPanel, BorderLayout.EAST);

		JLabel nameLabel = new JLabel("Name: ");
		JLabel stageWidthLabel = new JLabel("Stage Width: ");
		JLabel stageHeightLabel = new JLabel("Stage Height: ");
		JLabel stageNumberLabel = new JLabel("Stage Number: ");
		JLabel estimatedPopularityLabel = new JLabel("Estimated Popularity: ");
		JLabel startHourLabel = new JLabel("Starting Hour: ");
		JLabel startMinuteLabel = new JLabel("Starting Minute: ");
		JLabel endHourLabel = new JLabel("Ending Hour: ");
		JLabel endMinuteLabel = new JLabel("Ending Minute: ");
		
		JPanel labelPanel = new JPanel(new GridLayout(9,1));
		
		labelPanel.add(nameLabel);
		labelPanel.add(stageWidthLabel);
		labelPanel.add(stageHeightLabel);
		labelPanel.add(stageNumberLabel);
		labelPanel.add(estimatedPopularityLabel);
		labelPanel.add(startHourLabel);
		labelPanel.add(startMinuteLabel);
		labelPanel.add(endHourLabel);
		labelPanel.add(endMinuteLabel);
		
		TextFieldPanel.add(nameField);
		TextFieldPanel.add(stageWidthField);
		TextFieldPanel.add(stageHeightField);
		TextFieldPanel.add(stageNumberField);
		TextFieldPanel.add(estimatedPopularityField);
		TextFieldPanel.add(startHourField);
		TextFieldPanel.add(startMinuteField);
		TextFieldPanel.add(endHourField);
		TextFieldPanel.add(endMinuteField);
		
		panel.add(labelPanel, BorderLayout.WEST);
		panel.add(saveButton, BorderLayout.SOUTH);
	}
}
