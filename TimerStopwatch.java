import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TimerStopwatch extends JFrame implements ActionListener {
    private JTextField inputField;
    private JButton startButton, stopButton, resetButton;
    private JLabel timeLabel;
    private Timer timer;
    private int seconds, stopwatchSeconds;

    public TimerStopwatch() {
        
        setTitle("Timer and Stopwatch");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 1));

        inputField = new JTextField(10);
        inputField.setHorizontalAlignment(JTextField.CENTER);
        inputField.addActionListener(this);
        panel.add(inputField);

        timeLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        panel.add(timeLabel);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        startButton = new JButton("Start");
        startButton.addActionListener(this);
        buttonPanel.add(startButton);

        stopButton = new JButton("Stop");
        stopButton.addActionListener(this);
        buttonPanel.add(stopButton);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        buttonPanel.add(resetButton);

        JButton b=new JButton("Timer"); 
        b.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                try {
                    int inputSeconds = Integer.parseInt(inputField.getText());
                    if (inputSeconds > 0) {
                        seconds = inputSeconds;
                        updateTimeLabel();
                        inputField.setEnabled(false);
                        startButton.setEnabled(false);
                        timer.start();
                    }
                } catch (NumberFormatException ex) {
                    // ignore
                }      
            }     
        });  
         
        buttonPanel.add(b);

        panel.add(buttonPanel);

        timer = new Timer(1000, this);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == startButton){
            timer.start();
        }
        else if (e.getSource() == stopButton) {
            timer.stop();
            inputField.setEnabled(true);
            startButton.setEnabled(true);
        } else if (e.getSource() == resetButton) {
            timer.stop();
            seconds = 0;
            stopwatchSeconds = 0;
            updateTimeLabel();
            inputField.setEnabled(true);
            startButton.setEnabled(true);
        } else if (e.getSource() == timer) {
            seconds--;
            updateTimeLabel();
            stopwatchSeconds++;
            updateStopwatchLabel();
            if (seconds == 0) {
                timer.stop();
                inputField.setEnabled(true);
                startButton.setEnabled(true);
                JOptionPane.showMessageDialog(this, "Time's up!");
            }
        }
    }

    private void updateTimeLabel() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        timeLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, secs));
    }

    private void updateStopwatchLabel() {
        int hours = stopwatchSeconds / 3600;
        int minutes = (stopwatchSeconds % 3600) / 60;
        int secs = stopwatchSeconds % 60;
        timeLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, secs));
    }

    public static void main(String[] args) {
        new TimerStopwatch();
    }
}
