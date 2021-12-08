package GUI;
import java.awt.*;
import javax.swing.*;

public class GraphGUI extends JFrame{

    public JPanel sideMenu, buttonpanel1,buttonpanel2,file,nodes,edgesr,edgesb,tsp;
    protected GraphCanvas canvas;
    public JTextField fileinput,nodeinput,srcinput, destinput,winput,tspinput;
    protected JRadioButton[] buttons;
    private JButton load,save,ok,isconnected,center,numbers,clear,clearc,cancel,help;
    public RadioButtonListener rbl;
    private String filename;
    private ButtonListener bl;

    /*
     * A default constructor for the GraphGUI
     */
    public GraphGUI(String filename) {
        setTitle("Sali and Yosef Graph GUI");
        ImageIcon icon = new ImageIcon("icon.jpg");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.jpg")));
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
        sideMenu.setAlignmentX(TOP_ALIGNMENT);
        sideMenu.setMaximumSize(new Dimension(400,200));
        setSideMenu();
        canvas = new GraphCanvas(this);
        canvas.setAlignmentX(BOTTOM_ALIGNMENT);
        canvas.setSize(800,800);

        add(canvas);
        add(sideMenu);







    }

    /*
     * A helper function that sets up the side buttonpanel1
     * for the GUI
     */
    public void setSideMenu() {
        sideMenu.setLayout(new GridLayout(7,7));
        buttons = new JRadioButton[6];

        file = new JPanel();
        file.setLayout(new GridLayout(1,1));
        file.setBounds(5,5,5,5);

        load = new JButton("load");
        load.setBounds(5,5,5,5);
        load.addActionListener(bl);
        file.add(load);

        save = new JButton("save");
        save.setBounds(5,5,5,5);
        save.addActionListener(bl);
        file.add(save);



        fileinput = new JTextField(15);
        fileinput.setBounds(5,5,5,5);
        file.add(fileinput);

        file.add(new JLabel("File name"));


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
        nodes.add(nodeinput);
        nodes.add(new JLabel("Node ID"));

        sideMenu.add(nodes);

        edgesb = new JPanel();
        edgesb.setLayout(new GridLayout(1,4));

        srcinput = new JTextField(1);
        destinput = new JTextField(1);
        winput=new JTextField(1);

        edgesb.add(srcinput);
        edgesb.add(new JLabel("Src"));

        edgesb.add(destinput);
        edgesb.add(new JLabel("Dest"));

        edgesb.add(winput);
        edgesb.add(new JLabel("Weight"));

        sideMenu.add(edgesb);

        edgesr = new JPanel();
        edgesr.setLayout(new GridLayout(1,4));

        buttons[2] = new JRadioButton("connect edge");
        buttons[2].addActionListener(rbl);
        edgesr.add(buttons[2]);

        buttons[3] = new JRadioButton("remove edge");
        buttons[3].addActionListener(rbl);
        edgesr.add(buttons[3]);

        buttons[4] = new JRadioButton("shortest path");
        buttons[4].addActionListener(rbl);
        edgesr.add(buttons[4]);
        sideMenu.add(edgesr);

        tsp = new JPanel();
        tsp.setLayout(new GridLayout(1,2));

        buttons[5] = new JRadioButton("tsp");
        buttons[5].addActionListener(rbl);
        tsp.add(buttons[5]);

        tspinput = new JTextField(2);

        tsp.add(tspinput);
        tsp.add(new JLabel("Write keys for tsp "));
        tsp.add(new JLabel("seperates by ,"));


        sideMenu.add(tsp);

        buttonpanel1 = new JPanel();
        buttonpanel1.setLayout(new GridLayout(1,2));
        buttonpanel1.setBounds(5,5,5,5);

        ok = new JButton("ok");
        ok.setBounds(50,100,80,30);
        ok.addActionListener(bl);
        buttonpanel1.add(ok);

        isconnected = new JButton("is connected");
        isconnected.addActionListener(bl);
        buttonpanel1.add(isconnected);


        center = new JButton("center");
        center.addActionListener(bl);
        buttonpanel1.add(center);

        numbers = new JButton("numbers");
        numbers.addActionListener(bl);
        buttonpanel1.add(numbers);
        sideMenu.add(buttonpanel1);


        buttonpanel2 = new JPanel();
        buttonpanel2.setLayout(new GridLayout(1,2));
        buttonpanel2.setBounds(5,5,5,5);


        clearc = new JButton("clear colors");
        clearc.addActionListener(bl);
        buttonpanel2.add(clearc);

        clear = new JButton("clear graph");
        clear.addActionListener(bl);
        buttonpanel2.add(clear);

        cancel = new JButton("cancel");
        cancel.addActionListener(bl);
        buttonpanel2.add(cancel);

        help = new JButton("Help");
        help.addActionListener(bl);
        buttonpanel2.add(help);

        sideMenu.add(buttonpanel2);
        JPanel pp=new JPanel();



    }


}