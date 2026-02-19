package baccaratgame;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    Baccaratgame game = new Baccaratgame();

    JLabel p1,p2,p3,b1,b2,b3;
    GameFrame frame ;

    public GamePanel(GameFrame frame){
        this.frame=frame;
        setPreferredSize(new Dimension(1280,720));
        setLayout(null);
        setOpaque(false);
        nextRound();
    }

    void nextRound() {
        removeAll();
        JLabel bg = background();
        add(bg, Integer.valueOf(0));
        bg.setBounds(0, 0, 1280, 720);
        game.deal();

        int cardW = 160;
        int cardH = 240;
        int gap = 20;
        int startX = 380;

        b1 = new JLabel(card(game.b1));
        b2 = new JLabel(card(game.b2));
        b1.setBounds(startX, 50, cardW, cardH);
        b2.setBounds(startX + cardW + gap, 50, cardW, cardH);
        bg.add(b1);
        bg.add(b2);

        p1 = new JLabel(card(game.p1));
        p2 = new JLabel(card(game.p2));
        p1.setBounds(startX, 350, cardW, cardH);
        p2.setBounds(startX + cardW + gap, 350, cardW, cardH);
        bg.add(p1);
        bg.add(p2);

        JButton draw = new JButton("Draw");
        draw.setFont(new Font("Tahoma", Font.BOLD, 20));
        draw.setBounds(950, 400, 150, 60);

        draw.addActionListener(e -> {
            game.playerDraw();
            game.botDraw();
            if (game.p3 > 0) {
                p3 = new JLabel(card(game.p3));
                p3.setBounds(startX + (cardW + gap) * 2, 350, cardW, cardH);
                bg.add(p3);
            }
            if (game.b3 > 0) {
                b3 = new JLabel(card(game.b3));
                b3.setBounds(startX + (cardW + gap) * 2, 50, cardW, cardH);
                bg.add(b3);
            }
            bg.revalidate();
            bg.repaint();

            Timer t = new Timer(800, ev -> endRound());
            t.setRepeats(false);
            t.start();
        });

        JButton stop = new JButton("Stop");
        stop.setFont(new Font("Tahoma", Font.BOLD, 20));
        stop.setBounds(950, 480, 150, 60);

        stop.addActionListener(e -> {

            game.botDraw();

            if (game.b3 > 0) {
                b3 = new JLabel(card(game.b3));
                b3.setBounds(startX + (cardW + gap) * 2, 50, cardW, cardH);
                bg.add(b3);
                bg.revalidate();
                bg.repaint();
            }

            Timer t = new Timer(800, ev -> endRound());
            t.setRepeats(false);
            t.start();
        });
        bg.add(draw);
        bg.add(stop);

        revalidate();
        repaint();
    }

    void endRound(){

        String result=game.judge();

        JOptionPane.showMessageDialog(this,
                result+
                        "\nPlayer:"+game.playerTotal()+" ❤️"+game.player.hearts+
                        "\nBot:"+game.botTotal()+" ❤️"+game.bot.hearts
        );

        if(game.player.isAlive() && game.bot.isAlive()){
            nextRound();
        }else{
            JOptionPane.showMessageDialog(this,
                    game.player.isAlive()?"YOU WIN":"BOT WIN");
            frame.showMenu();
        }
    }

    ImageIcon card(int num){

        if(num <= 0) return new ImageIcon();
        String[] cards={
                "A-H.png","2-H.png","3-H.png","4-H.png","5-H.png","6-H.png",
                "7-H.png","8-H.png","9-H.png","10-H.png",
                "J-H.png","Q-H.png","K-H.png"
        };

        return load("/Baccaratimgame/" +cards[num-1]);
    }

    ImageIcon load(String path){

        java.net.URL url=getClass().getResource(path);

        if(url==null){

            return new ImageIcon();
        }

        ImageIcon icon=new ImageIcon(url);
        Image img=icon.getImage().getScaledInstance(160,240,Image.SCALE_SMOOTH);

        return new ImageIcon(img);
    }

    JLabel background(){

        java.net.URL bg = getClass().getResource("/Baccaratimgame/Ingame.jpg");

        if(bg==null){
            System.out.println("ไม่เจอพื้นหลัง");
            return new JLabel();
        }

        ImageIcon icon = new ImageIcon(bg);
        Image img = icon.getImage().getScaledInstance(1280,720,Image.SCALE_SMOOTH);

        JLabel label = new JLabel(new ImageIcon(img));
        label.setLayout(null);

        return label;
    }
}
