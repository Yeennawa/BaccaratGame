package baccaratgame;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Poker Game");
        this.setResizable(false);
        this.add(new Menu(this));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void showMenu() {
        getContentPane().removeAll();
        Menu menu = new Menu(this);
        this.add(menu);
        this.pack();
        revalidate();
        repaint();
    }

    public void startGame(){
        this.getContentPane().removeAll();
        GamePanel panel = new GamePanel();
        this.add(panel);
        this.pack();
        this.revalidate();
        panel.requestFocus();
    }
}
