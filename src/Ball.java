import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ball extends JLabel implements Runnable {
    private ImageIcon iconball = new ImageIcon("球.png");
    private ImageIcon bombbs = new ImageIcon("1.png");
    private JLabel jlb = new JLabel();
    private int x,y;
    private Timer t1,t2;
    private int i = 0;
    private boolean show =false;
    private boolean bomb = false;
    public Ball(int x1, int y1) {
        x = x1;
        y = y1;
        t1 = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball.this.setIcon(bombbs);
            }
        });
        t2 = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball.this.setIcon(null);
            }
        });
    }
    @Override
    public void run() {
        t1.start();
    }
}