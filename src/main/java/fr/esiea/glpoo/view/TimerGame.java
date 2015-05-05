package fr.esiea.glpoo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class TimerGame implements ActionListener {

		private int seconds[] = { 0, 0 };
		private int minutes[] = { 0, 0 };
		private int hours[] = { 0, 0 };

		private int s = 0;
		
		public int getS() {
			return s;
		}

		private JLabel jl;
		
		public TimerGame(JLabel jl){
			this.jl = jl;
		}
		
		public void actionPerformed(ActionEvent e) {
			jl.setText(convertSeconds(s));
			s++;
		}
		
		public static String convertSeconds(int total){
			int h,m,s;
			m=total/60;
			s=total%60;
			h=m/60;
			m=m%60;
			
			String hS = "0"+h;
			String mS = "0"+m;
			String sS = "0"+s;
			
			return hS.substring(hS.length()-2, hS.length())+":"+mS.substring(mS.length()-2, mS.length())+":"+sS.substring(sS.length()-2, sS.length());
		}
		
	}

