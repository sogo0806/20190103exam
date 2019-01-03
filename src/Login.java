import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Login extends JFrame{
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int screenw = screenSize.width;
    private int screenh = screenSize.height;
    private int frw =400 , frh =700 ;
    private JButton jbt = new JButton("遊戲開始");
    private JButton exit = new JButton("離開");
    private JLabel jname = new JLabel("簡易版爆爆王");
    private JLabel jph = new JLabel();
    private ImageIcon icon = new ImageIcon("ball.png");
    private Container cp ;
    private JPanel jpnn = new JPanel();
    private JPanel jpnc = new JPanel(new GridLayout(2,1,3,3));
    public Login(){
        init();
    }
    public void init(){
        this.setBounds(screenw/2-frw/2,screenh/2-frh/2,frw,frh);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cp = this.getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(jpnn,BorderLayout.NORTH);
        cp.add(jpnc,BorderLayout.CENTER);

        jpnn.setSize(300,500);
        jpnn.setPreferredSize(new Dimension(100,250));
        jpnn.add(jname);
        jname.setFont(new Font(null,Font.BOLD,50));
        jpnn.setBackground(new Color(255, 255, 255));

        jpnn.add(jph);
        jph.setIcon(icon);



        jbt.setFont(new Font(null,Font.BOLD,28));
        jbt.setBorder(BorderFactory.createLineBorder(Color.BLUE,5));
        exit.setFont(new Font(null,Font.BOLD,28));
        exit.setBorder(BorderFactory.createLineBorder(Color.BLUE,5));
        jpnc.add(jbt);
        jpnc.add(exit);
        this.setResizable(false);


        jbt.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Game fr = new Game();
                fr.setVisible(true);
                Login.this.dispose();
            }
        });



        exit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

    }
}
