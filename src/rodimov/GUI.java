package rodimov;

import rodimov.CGoLonSet.CGoLonSet;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class GUI extends JFrame{
    public JPanel panelMM = new JPanel();
    private JPanel panelSet = new JPanel();
    private String bFont = null;
    private String lFont = null;
    private int bFontSize = 20;
    private int lFontSize = 30;
    protected JButton bSG = new JButton("Start Game");
    private JButton bSet = new JButton("Settings");
    private JButton bExit = new JButton("Exit");
    private JRadioButton onArr = new JRadioButton("On Array");
    private JRadioButton onSet = new JRadioButton("On Set", true);
    private JRadioButton onList = new JRadioButton("On List");
    private JRadioButton Glider = new JRadioButton("Glider");
    private JRadioButton CP = new JRadioButton("Caterpillar");
    private JRadioButton Rnd = new JRadioButton("Random", true);
    private ButtonGroup bg = new ButtonGroup();
    private ButtonGroup bg2 = new ButtonGroup();
    private JButton back = new JButton("Back");
    private JPanel imp = new JPanel();
    private JPanel draw = new JPanel();
    private Border border = BorderFactory.createTitledBorder("Choose implementation");
    private Border border2 = BorderFactory.createTitledBorder("What to draw?");
    private Font buttonFont = new Font(bFont, Font.BOLD, bFontSize);
    private Font labelFont = new Font(lFont, Font.BOLD, lFontSize);
    private JLabel Gol = new JLabel("Conway's Game of Life", SwingConstants.CENTER);
    private JLabel Sett = new JLabel("Settings", SwingConstants.CENTER);
    static int WIDTH = 800;
    static int HEIGHT = 600;
    private int BWIDTH = 150;
    private int BHEIGHT = 50;
    private int LWIDTH = 328;
    private int LHEIGHT = 50;
    private int PWIDTH = 200;
    private int PHEIGHT = 200;
    private int RHEIGHT = 50;
    private int RWIDTH = 150;
    private int SPACE = 25;
    private int RSPACE = 25;
    private boolean isMM = true;
    private boolean isSet = true;
    private boolean first = false;
    private Canvas canvas = new Canvas();

    public GUI(){
        super("Conway's Game of Life Launcher");
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int MonitorWidth = gd.getDisplayMode().getWidth();
        int MonitorHeight = gd.getDisplayMode().getHeight();
        setBounds((MonitorWidth - WIDTH) / 2, (MonitorHeight - HEIGHT) / 2, WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        panelMM.setLayout(null);
        panelSet.setLayout(null);


        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Component component = e.getComponent();
                Rectangle bounds = component.getBounds ();

                HEIGHT = (int) bounds.getHeight();
                WIDTH = (int) bounds.getWidth();

                if (isMM) {
                    renderMainMenu();
                } else {
                    renderSettings();
                }
            }
        });

        onArr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Glider.setEnabled(false);
                CP.setEnabled(false);
                Rnd.setSelected(true);
            }
        });
        onSet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Glider.setEnabled(true);
                CP.setEnabled(true);
            }
        });
        onList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Glider.setEnabled(true);
                CP.setEnabled(true);
            }
        });
        bSet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                first = true;
                renderSettings();
            }
        });
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                first = true;
                renderMainMenu();
            }
        });
        bExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void renderMainMenu(){
        isMM = true;
        if (panelSet.isVisible()){
            panelSet.setVisible(false);
        }
        if (!panelMM.isVisible()) {
            panelMM.setVisible(true);
        }
        Gol.setFont(labelFont);
        bSG.setFont(buttonFont);
        bSet.setFont(buttonFont);
        bExit.setFont(buttonFont);

        Dimension p = panelMM.getSize();
        int h = (int) p.getHeight();
        int w = (int) p.getWidth();
        if (first){
            p = panelSet.getSize();
            h = (int) p.getHeight();
            w = (int) p.getWidth();
            first = false;
        }

        int move = 25;
        int xc = ((w - 1)  - BWIDTH) / 2;
        int yc = ((h - 1) - BHEIGHT) / 2;
        int xcl = ((w - 1) - LWIDTH) / 2;
        bSG.setBounds(xc, (yc - BHEIGHT - (SPACE / 2)) + move, BWIDTH, BHEIGHT);
        bSet.setBounds(xc, yc + move, BWIDTH, BHEIGHT);
        bExit.setBounds(xc, (yc + BHEIGHT + (SPACE / 2)) + move, BWIDTH, BHEIGHT);
        Gol.setBounds(xcl,((yc - LHEIGHT - (SPACE / 2)) + move) - 150, LWIDTH, LHEIGHT);
        panelMM.add(Gol);
        panelMM.add(bSG);
        panelMM.add(bSet);
        panelMM.add(bExit);
        setContentPane(panelMM);
    }

    private void renderSettings(){
        isMM = false;
        if (panelMM.isVisible()){
            panelMM.setVisible(false);
        }
        if (!panelSet.isVisible()){
            panelSet.setVisible(true);
        }
        panelSet.setLayout(null);
        imp.setBorder(border);
        draw.setBorder(border2);

        Dimension p = panelSet.getSize();
        int h = (int) p.getHeight();
        int w = (int) p.getWidth();
        if (first){
            p = panelMM.getSize();
            h = (int) p.getHeight();
            w = (int) p.getWidth();
            first = false;
        }
        int xc = ((w - 1)  - PWIDTH) / 2;
        int yc = ((h - 1) - PHEIGHT) / 2;
        int xcl = ((w - 1) - 328) / 2;
        int xc2 = ((w - 1) - 300) / 2;
        int yc2 = ((h - 1) - 50) / 2;

        imp.setBounds(xc, yc, PWIDTH, PHEIGHT);
        draw.setBounds(xc2, yc2 + (PHEIGHT / 2) + 30, 300, 50);
        back.setBounds(0,0,BWIDTH ,BHEIGHT);
        back.setFont(buttonFont);

        imp.setLayout(null);
        onArr.setBounds(5,RHEIGHT / 2, RWIDTH, RHEIGHT);
        onSet.setBounds(5,RHEIGHT + RSPACE, RWIDTH, RHEIGHT);
        onList.setBounds(5,(2 * RHEIGHT) +  RSPACE, RWIDTH, RHEIGHT);
        Sett.setBounds(xcl,yc - 135, 328, 50);
        Sett.setFont(new Font(lFont, Font.BOLD, 30));

        bg.add(onArr);
        bg.add(onSet);
        bg.add(onList);
        bg2.add(Glider);
        bg2.add(CP);
        bg2.add(Rnd);
        imp.add(onArr);
        imp.add(onSet);
        imp.add(onList);
        draw.add(Glider);
        draw.add(CP);
        draw.add(Rnd);

        panelSet.add(Sett);
        panelSet.add(imp);
        panelSet.add(draw);
        panelSet.add(back);
        setContentPane(panelSet);
    }

    public boolean isOnSet(){
        return onSet.isSelected();
    }
    public boolean isOnList(){
        return onList.isSelected();
    }
    public boolean isRnd(){
        return Rnd.isSelected();
    }
    public boolean isCP(){
        return CP.isSelected();
    }

}
