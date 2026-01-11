package org.example;

import javax.swing.*;
import java.awt.*;

public class LogoPanel extends JPanel {
    private final Image logo;

    public LogoPanel(Image logo) {
        this.logo = logo;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (logo != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setComposite(
            AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f)
            );

            int imgW = logo.getWidth(this);
            int imgH = logo.getHeight(this);

            int x = (getWidth() - imgW) / 2;
            int y = (getHeight() - imgH) / 2;

            g2.drawImage(logo, x, y, this);
            g2.dispose();
       }
    }
}
