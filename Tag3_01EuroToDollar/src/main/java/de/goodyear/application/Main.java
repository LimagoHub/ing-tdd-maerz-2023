package de.goodyear.application;


import de.goodyear.gui.Euro2DollarRechnerViewImpl;
import de.goodyear.gui.Euro2DollarRechnerView;
import de.goodyear.gui.presenter.Euro2DollarPresenterImpl;
import de.goodyear.gui.presenter.Euro2DollarPresenter;
import de.goodyear.model.Euro2DollarRechnerImpl;
import de.goodyear.model.Euro2DollarRechner;

public class Main {


	public static void main(String[] args) {
		Euro2DollarRechner model = new Euro2DollarRechnerImpl();
		
		Euro2DollarPresenter presenter = new Euro2DollarPresenterImpl();
		
		Euro2DollarRechnerView view = new Euro2DollarRechnerViewImpl();
		
		
		presenter.setView(view);
		presenter.setModel(model);
		
		view.setPresenter(presenter);
		
		presenter.onPopulateItems();
		
		view.show();

	}

}
