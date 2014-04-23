package weather;

import acm.gui.*;
import acm.program.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;

public class WeatherAPIGUI extends Program
{

   private static final long serialVersionUID = 1L;
   // TextFields that both methods need to access
   WeatherAPI w = new WeatherAPI();
   private JTextField zipCodeInput;
   private JTextField weatherInfoOutput;
   private JTextField temperatureInfoOutput;
   private JTextField windInfoOutput;
   
   // Constructor. Calls init(), then sets the size of the
   // window.
   public WeatherAPIGUI()
   {
       this.start();
       this.setSize(500, 300);
   }
   
   public void init()
   {
       
       JLabel zipLabel = new JLabel("Enter Zip Code:");
       JLabel weatherLabel = new JLabel("Weather:");
       JLabel temperatureLabel = new JLabel("Temperature (F):");
       JLabel windLabel = new JLabel("Wind Speed (mph):");
       
       ImageIcon Calculate = new ImageIcon("assets/buttonCalc.png");
       JButton calcButton = new JButton(Calculate);
       calcButton.setOpaque(false);
       calcButton.setContentAreaFilled(false);
       calcButton.setBorderPainted(false);
       ImageIcon Clear = new ImageIcon("assets/buttonClear.png");
       JButton clearButton = new JButton(Clear);
       clearButton.setOpaque(false);
       clearButton.setContentAreaFilled(false);
       clearButton.setBorderPainted(false);
      
       calcButton.setActionCommand("calc");
       clearButton.setActionCommand("clear");
       
       zipCodeInput = new JTextField();
       weatherInfoOutput = new JTextField();
       temperatureInfoOutput = new JTextField();
       windInfoOutput = new JTextField();
       
       TableLayout t = new TableLayout(6, 2);
       this.setLayout(t);
       
       Color b = new Color(46, 140, 234);
       this.setBackground(b);
       
       this.add(zipLabel);
       this.add(zipCodeInput);
       this.add(calcButton);
       this.add(clearButton);
       this.add(weatherLabel);
       this.add(weatherInfoOutput);
       this.add(temperatureLabel);
       this.add(temperatureInfoOutput);
       this.add(windLabel);
       this.add(windInfoOutput);

       
      
       
       addActionListeners();
   }
   
   public void actionPerformed(ActionEvent e)
   {
       String cmd = e.getActionCommand();
       
       if (cmd.equals("calc"))
       {
       	   String zip = zipCodeInput.getText();
       	   w.getInformation(zip);
       	   String weather = w.getWeather();
       	   weatherInfoOutput.setText("" + weather);
       	   double temp = w.getTemperature();
       	   temperatureInfoOutput.setText("" + temp);
           double wind = w.getWindSpeed();
           windInfoOutput.setText("" + wind);
       	
       }
       
       if (cmd.equals("clear"))
       {
           zipCodeInput.setText("");
           weatherInfoOutput.setText("");
           temperatureInfoOutput.setText("");
           windInfoOutput.setText("");
       }
   }
}