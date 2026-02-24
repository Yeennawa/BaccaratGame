package baccaratgame;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {

    Baccaratgame game = new Baccaratgame();
    JLabel p1, p2, p3, b1, b2, b3;
    GameFrame frame;

    public GamePanel(GameFrame frame) {
        this.frame = frame;
        this.setPreferredSize(new Dimension(1280, 720));
        this.setLayout(null);
        this.setOpaque(false);
        nextRound();
    }

    void nextRound() {
        removeAll();
        JLabel bg = background();
        bg.setBounds(0, 0, 1280, 720);
        add(bg);

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

        JButton menu = new JButton("Back to Menu");
        menu.setFont(new Font("Tahoma", Font.BOLD, 15));
        menu.setBounds(20, 20, 150, 40);
        menu.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to quit this game?",
                    "Exit Game",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                frame.showMenu();
            }
        });
        bg.add(menu);

        revalidate();
        repaint();
    }

    void endRound() {
        String result = game.judge();
        JOptionPane.showMessageDialog(this,
                result + "\nPlayer:" + game.playerTotal() + " ❤️" + game.player.hearts +
                        "\nBot:" + game.botTotal() + " ❤️" + game.bot.hearts);

        if (game.player.isAlive() && game.bot.isAlive()) {
            nextRound();
        } else {
            JOptionPane.showMessageDialog(this, game.player.isAlive() ? "YOU WIN" : "BOT WIN");
            frame.showMenu();
        }
    }

    public ImageIcon card(int num) {

        if (num <= 0) return new ImageIcon();
        java.util.Random rand = new java.util.Random();
        String[] suits = {"H", "D", "C", "S"};
        String randomSuit = suits[rand.nextInt(suits.length)];
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String rankName = ranks[(num - 1) % 13];
        String fileName = rankName + "-" + randomSuit + ".png";
        return load("/Baccaratimgame/" + fileName);
    }

    ImageIcon load(String path) {
        java.net.URL url = getClass().getResource(path);
        if (url == null) return new ImageIcon();
        ImageIcon icon = new ImageIcon(url);
        Image img = icon.getImage().getScaledInstance(160, 240, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    JLabel background() {
        java.net.URL bgUrl = getClass().getResource("/Baccaratimgame/Ingame.jpg");
        if (bgUrl == null) return new JLabel();
        ImageIcon icon = new ImageIcon(bgUrl);
        Image img = icon.getImage().getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(img));
        label.setLayout(null);
        return label;
    }
}