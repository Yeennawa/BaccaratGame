package baccaratgame;
import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    GameFrame frame;
    public Menu(GameFrame frame) {
        this.frame = frame;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1366, 768));

        JLabel bg = background();
        bg.setBounds(0, 0, 1366, 768);
        this.add(bg);

    }

    private JButton startButton() {
        JButton startButton = new JButton("START GAME");
        startButton.addActionListener(e -> {
            frame.startGame();
        });
        startButton.setPreferredSize(new Dimension(200, 50));

        return startButton;
    }

    private JButton exitButton() {
        JButton exitButton = new JButton("EXIT GAME");
        exitButton.addActionListener(e -> {
            System.exit(0);
        });
        exitButton.setPreferredSize(new Dimension(200, 50));

        return exitButton;
    }

    private JLabel background() {
        java.net.URL imgURL = getClass().getResource("/Pokerimgame/Game front page.jpg");
        ImageIcon background;

        if (imgURL != null) {
            background = new ImageIcon(imgURL);
        } else {
            System.err.println("หาภาพพื้นหลังไม่เจอที่: /images/backfree.jpg");
            background = new ImageIcon(); // สร้าง Object ว่างเพื่อไม่ให้โปรแกรม Crash
        }

        JLabel label = new JLabel(background);
        label.setLayout(null);



        JButton start = startButton();
        start.setBounds(150, 350, 175, 40);
        label.add(start);

        JButton exit = exitButton();
        exit.setBounds(150, 400, 175, 40);
        label.add(exit);

        return label;
    }}
