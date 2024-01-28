package components;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ShadowJLabel extends JLabel {
    private int shadowSize = 1;
    private Color shadowColor = Color.black;
    private boolean shadowEnabled = true;

    public ShadowJLabel(String text) {
        super(text, SwingConstants.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Enable antialiasing for smoother graphics
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Get the FontMetrics to calculate text dimensions
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(getText());
        int textHeight = fm.getHeight();
        
        // Calculate the center position for the shadow based on text dimensions
        int x = (getWidth() - textWidth) / 2;
        int y = ((getHeight() - textHeight) / 2) + fm.getAscent(); // Adjust y to account for ascent

        // Define the ellipse size based on shadowSize and text dimensions
        int ellipseWidth = (int) ((textWidth + shadowSize * 2) * 1.4f);
        int ellipseHeight = (int) ((textHeight + shadowSize * 2) * 1.4f);

        // Center the ellipse around the text
        int xOffset = (getWidth() - ellipseWidth) / 2;
        int yOffset = (getHeight() - ellipseHeight) / 2;

        // Adjust the x and y positions so the ellipse is centered around the text
        x -= ellipseWidth / 12; // Adjust x to center
        y -= ellipseHeight / 1.5; // Adjust y to center behind text
        
        // Create a radial gradient paint for the ellipse        
        Point2D center = new Point2D.Float(xOffset + ellipseWidth / 2f, yOffset + ellipseHeight / 2f);
        float radius = Math.max(ellipseWidth, ellipseHeight) / 2f;
        float[] dist = {0.0f, 1.0f};
        Color[] colors = {new Color(0, 0, 0, 150), new Color(0, 0, 0, 0)};
        RadialGradientPaint rgp = new RadialGradientPaint(center, radius, dist, colors);

        // Apply the gradient paint to the Graphics2D context
        g2d.setPaint(rgp);
 
        Ellipse2D shadowEllipse = new Ellipse2D.Float(xOffset, yOffset, ellipseWidth, ellipseHeight);
        g2d.fill(shadowEllipse);

        // Dispose of the graphics context we're done with it
        g2d.dispose();

        // Paint the text over the shadow
        super.paintComponent(g);
    }

    public void setShadowSize(int size) {
        this.shadowSize = size;
    }

    public void setShadowColor(Color color) {
        this.shadowColor = color;
    }

    public void setShadowEnabled(boolean enabled) {
        this.shadowEnabled = enabled;
    }
}
