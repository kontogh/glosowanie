package pl.probka.glosujonline.guicomponents;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.progressbar.ProgressBarVariant;

public class NeededThread extends Thread{
    private final UI ui;
    private final ProgressBar view;

    private int count = 0;
    private double val = 0;

    public NeededThread(UI ui, ProgressBar view) {
        this.ui = ui;
        this.view = view;
        view.setHeight("10px");
    }

    @Override
    public void run() {
        try {
            // Update the data for a while
            while (count < 100) {
                // Sleep to emulate background work
                Thread.sleep(1000);

                ui.access(() -> view.setValue(val+=0.01));
                }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
