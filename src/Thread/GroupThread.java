package Thread;

import ui.ControllerRegister;

public class GroupThread extends Thread{
	private ControllerRegister cR;
	
	public GroupThread(ControllerRegister cR) {
		this.cR= cR;
	}
	
	public void run() {
		while(true) {
			cR.moveGroup();
			try {
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
