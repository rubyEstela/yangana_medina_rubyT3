package Thread;

import ui.ControllerRegister;

public class ImageThread extends Thread{
	
	private ControllerRegister cR;
	
	public ImageThread(ControllerRegister cR) {
		this.cR= cR;
	}
	
	public void run() {
		while(true) {
			cR.moveImage();
			try {
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


}
