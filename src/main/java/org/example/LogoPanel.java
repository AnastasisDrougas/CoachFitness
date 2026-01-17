package org.example;

import javax.swing.*;
import java.awt.*;

/**
 * @author Anastasis Drougas
 * @author Angjelo Hoxhaj
 */

/**
 * Displays an image, either centered at its original size or stretched to fill the panel.
 */

public class LogoPanel extends JPanel {
    private final Image logo;
    private final boolean stretchToFit; // New flag to control behavior

    public LogoPanel(Image logo) {
        this(logo, false);
    }


    public LogoPanel(Image logo, boolean stretchToFit) {
        this.logo = logo;
        this.stretchToFit = stretchToFit;
        setOpaque(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (logo != null) {
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f));
            if (stretchToFit) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

                g2.drawImage(logo, 0, 0, getWidth(), getHeight(), this);
            } else {
                int imgW = logo.getWidth(this);
                int imgH = logo.getHeight(this);

                int x = (getWidth() - imgW) / 2;
                int y = (getHeight() - imgH) / 2;

                g2.drawImage(logo, x, y, this);
            }
            g2.dispose();
        }
    }
}