package de.goodyear.gui;

import de.goodyear.gui.presenter.Euro2DollarPresenter;


public interface Euro2DollarRechnerView {

	 Euro2DollarPresenter getPresenter();



	 void setPresenter(Euro2DollarPresenter presenter);

	
	 void close();

	 String getEuro();

	 void setEuro(String euro);

	String getDollar();

	void setDollar(String dollar);
	
	boolean isRechnenEnabled();

	void setRechnenEnabled(boolean enabled);
	
	void show();


}