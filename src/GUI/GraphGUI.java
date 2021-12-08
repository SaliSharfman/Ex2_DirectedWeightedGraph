package GUI;
import java.awt.*;
import javax.swing.*;

public class GraphGUI extends JFrame{

    public JPanel sideMenu,menu,file,nodes,edges,tsp;
    protected GraphCanvas canvas;
    public JTextField fileinput,nodeinput,srcinput, destinput,winput,tspinput;
    protected JRadioButton[] buttons;
    private JButton ok,center,isconnected,load,save,clear,clearc,cancel,help,sidemenu,close,close1;
    public RadioButtonListener rbl;
    private String filename;
    private ButtonListener bl;

    /*
     * A default constructor for the GraphGUI
     */
    public GraphGUI(String filename) {
        setTitle("Graph GUI");
        this.filename=filename;
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(d);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rbl = new RadioButtonListener(this);
        bl = new ButtonListener(this);
        setComponents();
        setVisible(true);
    }

    /*
     * A helper function that sets up the whole GUI
     */
    public String getFileName()
    {
        return this.filename;
    }

    public void setComponents() {
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));

        sideMenu = new JPanel();
        sideMenu.setAlignmentX(LEFT_ALIGNMENT);
        sideMenu.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        setSideMenu();
        canvas = new GraphCanvas(this);
        canvas.setAlignmentX(LEFT_ALIGNMENT);
        canvas.setSize(400,400);
        add(canvas);
        add(sideMenu);



    }

    /*
     * A helper function that sets up the side menu
     * for the GUI
     */
    public void setSideMenu() {
        sideMenu.setLayout(new GridLayout(6,7));

        buttons = new JRadioButton[6];

        file = new JPanel();
        file.setLayout(new GridLayout(1,1));
        file.setBounds(50,50,40,15);

        file.add(new JLabel("File name:"));
        fileinput = new JTextField(15);
        file.add(fileinput);

        load = new JButton("load");
        load.addActionListener(bl);
        file.add(load);

        save = new JButton("save");
        save.addActionListener(bl);
        file.add(save);


        sideMenu.add(file);

        nodes = new JPanel();
        nodes.setLayout(new GridLayout(1,3));

        buttons[0] = new JRadioButton("add node");
        buttons[0].addActionListener(rbl);
        nodes.add(buttons[0]);

        buttons[1] = new JRadioButton("remove node");
        buttons[1].addActionListener(rbl);
        nodes.add(buttons[1]);

        nodeinput = new JTextField(7);
        nodes.add(new JLabel("Node ID:"));
        nodes.add(nodeinput);

        sideMenu.add(nodes);

        edges = new JPanel();
        edges.setLayout(new GridLayout(1,4));

        buttons[2] = new JRadioButton("connect edge");
        buttons[2].addActionListener(rbl);
        edges.add(buttons[2]);

        buttons[3] = new JRadioButton("remove edge");
        buttons[3].addActionListener(rbl);
        edges.add(buttons[3]);

        buttons[4] = new JRadioButton("shortest path");
        buttons[4].addActionListener(rbl);
        edges.add(buttons[4]);

        srcinput = new JTextField(1);
        destinput = new JTextField(1);
        winput=new JTextField(1);
        edges.add(new JLabel("Src ID:"));
        edges.add(srcinput);
        edges.add(new JLabel("Dest ID:"));
        edges.add(destinput);
        edges.add(new JLabel("Edge weight to connect:"));
        edges.add(winput);

        sideMenu.add(edges);

        tsp = new JPanel();
        tsp.setLayout(new GridLayout(1,2));

        buttons[5] = new JRadioButton("tsp");
        buttons[5].addActionListener(rbl);
        tsp.add(buttons[5]);

        tspinput = new JTextField(2);
        tsp.add(new JLabel("Write node keys for tsp seperates by ','"));
        tsp.add(tspinput);


        sideMenu.add(tsp);

        menu = new JPanel();
        menu.setLayout(new GridLayout(1,2));

        ok = new JButton("ok");
        ok.addActionListener(bl);
        menu.add(ok);

        center = new JButton("center");
        center.addActionListener(bl);
        menu.add(center);

        isconnected = new JButton("is connected");
        isconnected.addActionListener(bl);
        menu.add(isconnected);

        clearc = new JButton("clear colors");
        clearc.addActionListener(bl);
        menu.add(clearc);

        clear = new JButton("clear graph");
        clear.addActionListener(bl);
        menu.add(clear);

        cancel = new JButton("cancel");
        cancel.addActionListener(bl);
        menu.add(cancel);

        help = new JButton("Help");
        help.addActionListener(bl);
        menu.add(help);

        sideMenu.add(menu);
    }


}