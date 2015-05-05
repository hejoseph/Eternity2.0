package fr.esiea.glpoo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class TimerGame {
	

	
  public static void main(String[] args) {
    Timer timer = new Timer(1000, new MyTimerActionListener());

    timer.start();
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
    }
    timer.stop();
  }

}

class MyTimerActionListener implements ActionListener {
	
	private int seconds=0;
  public void actionPerformed(ActionEvent e) {

    System.out.println(seconds++);

  }
}