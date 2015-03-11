package segundaFase;

import java.awt.*;
import javax.swing.*;
import java.awt.print.*;

public class PrintUtilities implements Printable {

    private static boolean table;

    static void printComponent(JTable jTable1, int i, Printable printable) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    private Book b;
    private static int type;
    private Component componentToBePrinted;
    private PrinterJob printJob;
    private static Printable toPrint;

    public static void printComponent(Component c, int t) {
        type = t;
        new PrintUtilities(c).print();
    }

    public PrintUtilities(Component componentToBePrinted) {
        this.componentToBePrinted = componentToBePrinted;
    }

    public void print() {
        printJob = PrinterJob.getPrinterJob();
        b = new Book();
        PageFormat pf = new PageFormat();
        Paper paper = new Paper();
        paper.setImageableArea(12, 12, 612, 792);
        pf.setPaper(paper);
        b.append(this, pf);
        if (type == 0) {
            printJob.setPageable(b);
            if (printJob.printDialog()) {
                try {
                    printJob.print();
                } catch (PrinterException pe) {
                    System.out.println(pe);
                }
            }
        } else {
            b.append(SiteAdmin.mdos, pf);
            b.append(SiteAdmin.mdol, pf);
            b.append(SiteAdmin.ts, pf);
            b.append(SiteAdmin.pm, pf);
            b.append(SiteAdmin.ip, pf);
            b.append(SiteAdmin.gruas, pf);
            b.append(SiteAdmin.exc, pf);
            b.append(SiteAdmin.ctv, pf);
            b.append(SiteAdmin.misc, pf);
            b.append(SiteAdmin.totales, pf);
            printJob.setPageable(b);
            if (printJob.printDialog()) {
                try {
                    printJob.print();
                } catch (PrinterException pe) {
                    System.out.println(pe);
                }
            }
        }
    }

    public static void disableDoubleBuffering(Component c) {
        RepaintManager currentManager = RepaintManager.currentManager(c);
        currentManager.setDoubleBufferingEnabled(false);
    }

    public static void enableDoubleBuffering(Component c) {
        RepaintManager currentManager = RepaintManager.currentManager(c);
        currentManager.setDoubleBufferingEnabled(true);
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (table) {
            return 0;
        } else {
            Graphics2D g2 = (Graphics2D) graphics;
            //  for faster printing, turn off double buffering
            disableDoubleBuffering(componentToBePrinted);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
            Dimension d = componentToBePrinted.getSize(); //get size of document
            double panelWidth = d.width;//width in pixels
            double pageWidth = pageFormat.getImageableWidth(); //width of printer page
            double scale = (pageWidth) / panelWidth;
            //  shift Graphic to line up with beginning of print-imageable region
            g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY() + 100);
            //  scale the page so the width fits...
            g2.scale(scale, scale);
            componentToBePrinted.paint(g2); //repaint the page for printing
            enableDoubleBuffering(componentToBePrinted);
            return Printable.PAGE_EXISTS;
        }
    }
}