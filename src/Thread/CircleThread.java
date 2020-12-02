package Thread;

import ui.ControllerRegister;

public class CircleThread extends Thread{
	private ControllerRegister cR;
	
	public CircleThread(ControllerRegister cR) {
		this.cR= cR;
	}
	
	public void run() {
		while(true) {
			cR.moveCircle();
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
