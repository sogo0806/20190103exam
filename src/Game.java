import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Game extends JFrame {
    private ImageIcon iconbackgroung = new ImageIcon("backgroung.jpg");
    private ImageIcon iconleft = new ImageIcon("left.png");
    private ImageIcon iconright = new ImageIcon("右.png");
    private ImageIcon iconup = new ImageIcon("up.png");
    private ImageIcon icondown = new ImageIcon("down.png");
    private ImageIcon imgleft = new ImageIcon("左2.png");
    private ImageIcon imgright = new ImageIcon("右2.png");
    private ImageIcon imgup = new ImageIcon("上2.png");
    private ImageIcon imgdown = new ImageIcon("下2.png");
    private ImageIcon iconball = new ImageIcon("球.png");
    private ImageIcon bombbs = new ImageIcon("1.png");

    private ArrayList<Thread> ballThread = new ArrayList<Thread>();

    //////////////////////////////////////////////////////////////////////////
    private JPanel jpGrid = new JPanel(new GridLayout(7, 11, 0, 0));
    private JPanel jpGrid2 = new JPanel(new GridLayout(7, 11, 0, 0));
    private JPanel jpGridball = new JPanel(new GridLayout(7, 11, 0, 0));


    /////////////////////////////////////////////////////////////////////////////
    private JPanel jpNull = new JPanel(null);
    private JPanel jpNull2 = new JPanel(null);


    /////////////////////////////////////////////////////////////////////////////
    private JLabel jlbbackgroung = new JLabel();
    private JLabel jlbPlayer = new JLabel();
    private JLabel jlbPlayer2 = new JLabel();


    ///////////////////////////////////////////////////////////////////////
    private Player player[][] = new Player[11][7];
    private Player player2[][] = new Player[11][7];
    //    private Ball jlbBall [][] = new Ball[11][7];
    private JLabel jlbBall[][] = new JLabel[11][7];


    ////////////////////////////////////////////////////////////////
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int screenw = screenSize.width;
    private int screenh = screenSize.height;
    private int frw = 800, frh = 533;
    private Container cp;
    //    private Ball ball=new Ball(jlbPlayer.getX(),jlbPlayer.getY());
//    private Ball ball2=new Ball(jlbPlayer2.getX(),jlbPlayer2.getY());
    ////    private Vector<Ball> balls =new Vector<>();
    private int x = 0, y = 0, x1 = 10, y1 = 0;
    private int[][] loc = new int[11][7];

    public Game() {
        ex1();
    }

    public void ex1() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setBounds(screenw / 2 - frw / 2, screenh / 2 - frh / 2, frw, frh);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                Login fr = new Login();
                fr.setVisible(true);
            }
        });
        this.setLayout(null);
        this.add(jlbbackgroung);
        this.setResizable(false);
        cp = this.getContentPane();
        cp.setLayout(null);
        jlbPlayer.setIcon(icondown);
        jpNull.add(jlbPlayer);
        jpNull.setBounds(0, 0, 800, 533);
        jpNull.setBackground(new Color(255, 255, 255, 0));
        jpNull.setLayout(null);

        jlbPlayer2.setIcon(imgdown);
        jpNull2.add(jlbPlayer2);
        jpNull2.setBounds(0, 0, 800, 533);
        jpNull2.setBackground(new Color(255, 255, 255, 0));
        jpNull2.setLayout(null);

        jpGrid.setBounds(0, 0, 800, 533);
        jpGrid.setBackground(new Color(255, 255, 255, 77));
        jpGrid.setOpaque(false);
        jpGrid2.setBounds(0, 0, 800, 533);
        jpGrid2.setBackground(new Color(255, 255, 255, 77));
        jpGrid2.setOpaque(false);
        jpGridball.setBounds(0, 0, 800, 533);
        jpGridball.setBackground(new Color(255, 255, 255, 77));
        jpGridball.setOpaque(false);
        jpNull.setOpaque(false);

        jlbbackgroung.setOpaque(false);
        jlbbackgroung.setBounds(0, 0, 800, 533);
        jlbbackgroung.setIcon(iconbackgroung);

        cp.add(jpNull);
        cp.add(jpNull2);
        cp.add(jpGrid);
        cp.add(jpGrid2);
        cp.add(jpGridball);
        cp.add(jlbbackgroung);

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 11; j++) {
                player[j][i] = new Player();
                player[j][i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                jpGrid.add(player[j][i]);
            }
        }
        player[0][0].setIcon(icondown);

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 7; j++) {
                loc[i][j] = 0;
            }
        }

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 11; j++) {
                player2[j][i] = new Player();
                player2[j][i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                jpGrid2.add(player2[j][i]);

            }
        }
        player2[10][0].setIcon(imgdown);


        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 11; j++) {
//                jlbBall[j][i] = new Ball();
                jlbBall[j][i] = new JLabel();
                jlbBall[j][i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                jpGridball.add(jlbBall[j][i]);

            }
        }


        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        if (x < 10) {
                            player[x][y].setIcon(null);
                            x++;
                            player[x][y].setIcon(iconright);
                        }
                        
                        break;

                    case KeyEvent.VK_LEFT:
                        if (x > 0) {
                            player[x][y].setIcon(null);
                            x--;
                            player[x][y].setIcon(iconleft);
                        }

                        break;

                    case KeyEvent.VK_DOWN:
                        if (y < 6) {
                            player[x][y].setIcon(null);
                            y++;
                            player[x][y].setIcon(icondown);
                        }

                        break;

                    case KeyEvent.VK_UP:
                        if (y > 0) {
                            player[x][y].setIcon(null);
                            y--;
                            player[x][y].setIcon(iconup);
                        }

                        break;

                    case KeyEvent.VK_ENTER:
                        ballChangeIcon(x, y);
                        ballThread.add(new Ball1(jlbBall[x][y]));
                        ballThread.get(ballThread.size() - 1).start();
                        if (x > 0 && x < 10 && y > 0 && y < 6) {
                            ballThread.add(new Ball1(jlbBall[x + 1][y]));
                            ballThread.get(ballThread.size() - 1).start();
                            ballThread.add(new Ball1(jlbBall[x - 1][y]));
                            ballThread.get(ballThread.size() - 1).start();
                            ballThread.add(new Ball1(jlbBall[x][y + 1]));
                            ballThread.get(ballThread.size() - 1).start();
                            ballThread.add(new Ball1(jlbBall[x][y - 1]));
                            ballThread.get(ballThread.size() - 1).start();
                        }

                        if (x == 0) {
                            ballThread.add(new Ball1(jlbBall[x + 1][y]));
                            ballThread.get(ballThread.size() - 1).start();
                            ballThread.add(new Ball1(jlbBall[x][y + 1]));
                            ballThread.get(ballThread.size() - 1).start();
                            ballThread.add(new Ball1(jlbBall[x][y - 1]));
                            ballThread.get(ballThread.size() - 1).start();
                        } else if (x == 10) {
                            ballThread.add(new Ball1(jlbBall[x - 1][y]));
                            ballThread.get(ballThread.size() - 1).start();
                            ballThread.add(new Ball1(jlbBall[x][y + 1]));
                            ballThread.get(ballThread.size() - 1).start();
                            ballThread.add(new Ball1(jlbBall[x][y - 1]));
                            ballThread.get(ballThread.size() - 1).start();
                        } else if (y == 0) {
                            ballThread.add(new Ball1(jlbBall[x + 1][y]));
                            ballThread.get(ballThread.size() - 1).start();
                            ballThread.add(new Ball1(jlbBall[x - 1][y]));
                            ballThread.get(ballThread.size() - 1).start();
                            ballThread.add(new Ball1(jlbBall[x][y + 1]));
                            ballThread.get(ballThread.size() - 1).start();
                        } else if (y == 6) {
                            ballThread.add(new Ball1(jlbBall[x + 1][y]));
                            ballThread.get(ballThread.size() - 1).start();
                            ballThread.add(new Ball1(jlbBall[x - 1][y]));
                            ballThread.get(ballThread.size() - 1).start();
                            ballThread.add(new Ball1(jlbBall[x][y - 1]));
                            ballThread.get(ballThread.size() - 1).start();
                        }


                        break;

                    case KeyEvent.VK_A:
                        if (x1 > 0) {
                            player2[x1][y1].setIcon(null);
                            x1--;
                            player2[x1][y1].setIcon(imgleft);
                        }

                        break;

                    case KeyEvent.VK_D:
                        if (x1 < 10) {
                            player2[x1][y1].setIcon(null);
                            x1++;
                            player2[x1][y1].setIcon(imgright);
                        }

                        break;

                    case KeyEvent.VK_S:
                        if (y1 < 6) {
                            player2[x1][y1].setIcon(null);
                            y1++;
                            player2[x1][y1].setIcon(imgdown);
                        }

                        break;

                    case KeyEvent.VK_W:
                        if (y1 > 0) {
                            player2[x1][y1].setIcon(null);
                            y1--;
                            player2[x1][y1].setIcon(imgup);
                        }
                        break;

                    case KeyEvent.VK_V:
                        ballChangeIcon(x1, y1);
                        ballThread.add(new Ball1(jlbBall[x1][y1]));
                        ballThread.get(ballThread.size() - 1).start();
                        if (x1 > 0 && x1 < 10 && y1 > 0 && y1 < 6) {
                            ballThread.add(new Ball1(jlbBall[x1 + 1][y1]));
                            ballThread.get(ballThread.size() - 1).start();
                            ballThread.add(new Ball1(jlbBall[x1 - 1][y1]));
                            ballThread.get(ballThread.size() - 1).start();
                            ballThread.add(new Ball1(jlbBall[x1][y1 + 1]));
                            ballThread.get(ballThread.size() - 1).start();
                            ballThread.add(new Ball1(jlbBall[x1][y1 - 1]));
                            ballThread.get(ballThread.size() - 1).start();
                        }

                        if (x1 == 0) {
                            ballThread.add(new Ball1(jlbBall[x1 + 1][y1]));
                            ballThread.get(ballThread.size() - 1).start();
                            ballThread.add(new Ball1(jlbBall[x1][y1 + 1]));
                            ballThread.get(ballThread.size() - 1).start();
                            ballThread.add(new Ball1(jlbBall[x1][y1 - 1]));
                            ballThread.get(ballThread.size() - 1).start();
                        } else if (x1 == 10) {
                            ballThread.add(new Ball1(jlbBall[x1 - 1][y1]));
                            ballThread.get(ballThread.size() - 1).start();
                            ballThread.add(new Ball1(jlbBall[x1][y1 + 1]));
                            ballThread.get(ballThread.size() - 1).start();
                            ballThread.add(new Ball1(jlbBall[x1][y1 - 1]));
                            ballThread.get(ballThread.size() - 1).start();
                        } else if (y1 == 0) {
                            ballThread.add(new Ball1(jlbBall[x1 + 1][y1]));
                            ballThread.get(ballThread.size() - 1).start();
                            ballThread.add(new Ball1(jlbBall[x1 - 1][y1]));
                            ballThread.get(ballThread.size() - 1).start();
                            ballThread.add(new Ball1(jlbBall[x1][y1 + 1]));
                            ballThread.get(ballThread.size() - 1).start();
                        } else if (y1 == 6) {
                            ballThread.add(new Ball1(jlbBall[x1 + 1][y1]));
                            ballThread.get(ballThread.size() - 1).start();
                            ballThread.add(new Ball1(jlbBall[x1 - 1][y1]));
                            ballThread.get(ballThread.size() - 1).start();
                            ballThread.add(new Ball1(jlbBall[x1][y1 - 1]));
                            ballThread.get(ballThread.size() - 1).start();
                        }
                        break;
                }
            }
        });
    }

    private void ballChangeIcon(int x2, int y2) {
        jlbBall[x2][y2].setIcon(iconball);
    }

//    public void lost() {
//        if (player[x][y] == jlbBall[x][y]) {
//            JOptionPane.showMessageDialog(Game.this, "player1 輸嘞!!");
//        } else if (player[x][y] == jlbBall[x - 1][y]) {
//            JOptionPane.showMessageDialog(Game.this, "player1 輸嘞!!");
//        } else if (player[x][y] == jlbBall[x][y + 1]) {
//            JOptionPane.showMessageDialog(Game.this, "player1 輸嘞!!");
//        } else if (player[x][y] == jlbBall[x][y + 1]) {
//            JOptionPane.showMessageDialog(Game.this, "player1 輸嘞!!");
//        }
//    }



}