import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class GameMenu extends JPanel {
    private GameRunner gameRunner;

    private JLabel benchLabel, treesLabel, CloudSunLabel, cloud2Label, cloud3Label, castlesLabel,
            floorLabel, mushroomLabel, starLabel, CATLabel, startImg, h2pImg;

    private JButton startButton, h2p, resetButton, h2pBackButt;

    private JLabel titleLabel, titleLabel2;

    public GameMenu(GameRunner gameRunner) {
        this.gameRunner = gameRunner;
        setPreferredSize(new Dimension(GameRunner.SCREEN_WIDTH, GameRunner.SCREEN_HEIGHT));
        setBackground(Color.decode("#E8FEFF"));
        setLayout(null);

        try {
            benchLabel = createImageLabel("src/Elements/bench.png", 225, 520, 150, 75);
            treesLabel = createImageLabel("src/Elements/trees.png", 820, 340, 500, 250);
            CloudSunLabel = createImageLabel("src/Elements/cloudSun.png", -50, 50, 500, 250);
            cloud2Label = createImageLabel("src/Elements/cloud2.png", 400, 10, 300, 150);
            cloud3Label = createImageLabel("src/Elements/cloud3.png", 800, 20, 500, 250);
            castlesLabel = createImageLabel("src/Elements/castles.png", -115, 317, 475, 275);
            floorLabel = createImageLabel("src/Elements/floor.png", -10, 325, 1300, 680);
            mushroomLabel = createImageLabel("src/Elements/mushroom.png", 1145, 520, 150, 75);
            CATLabel = createImageLabel("src/Elements/catAuan.png", 795, 508, 150, 100);
            starLabel = createImageLabel("src/Elements/star.png", 585, 130, 120, 50);
            h2pImg = createImageLabel("src/Elements/h2p.png", 0, 0, GameRunner.SCREEN_WIDTH, GameRunner.SCREEN_HEIGHT);
            startImg = createImageLabel("src/Elements/startButt.png", (GameRunner.SCREEN_WIDTH - 400) / 2, 350, 400, 225);

            add(benchLabel);
            add(treesLabel);
            add(CloudSunLabel);
            add(cloud2Label);
            add(cloud3Label);
            add(castlesLabel);
            add(floorLabel);
            add(mushroomLabel);
            add(starLabel);
            add(CATLabel);
            add(startImg);

        } catch (IOException e) {
            e.printStackTrace();
        }

        titleLabel = new JLabel("RUN TAN NA ", SwingConstants.CENTER);
        titleLabel2 = new JLabel("Are You READY!", SwingConstants.CENTER);

        titleLabel.setFont(gameRunner.getFont());
        titleLabel.setBounds((GameRunner.SCREEN_WIDTH - 370) / 2, 200, 400, 45);
        add(titleLabel);

        titleLabel2.setFont(gameRunner.getFont());
        titleLabel2.setBounds((GameRunner.SCREEN_WIDTH - 370) / 2, 300, 400, 45);
        add(titleLabel2);

        startButton = new JButton();
        startButton.setBounds(530, 425, 220, 60);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.setOpaque(false);
        startButton.addActionListener((ActionEvent e) -> gameRunner.showGameScreen());
        add(startButton);

        h2p = new JButton("HOW TO PLAY");
        h2p.setFont(gameRunner.getFont());
        h2p.setFocusPainted(false);
        h2p.setBackground(Color.decode("#C0C0C0"));
        h2p.setBounds(30, 20, 240, 50);
        h2p.addActionListener(e -> howToPlay(e));
        add(h2p);

        resetButton = new JButton("Reset");
        resetButton.setFont(gameRunner.getFont());
        resetButton.setBackground(Color.decode("#C0C0C0"));
        resetButton.setFocusPainted(false);
        resetButton.setBounds(1010, 70, 180, 50);
        setComponentZOrder(resetButton, getComponentZOrder(cloud3Label) + 1);
        resetButton.addActionListener(e -> gameRunner.resetHighScore());
        add(resetButton);
    }

    private void howToPlay(ActionEvent e) {
            for (Component component : getComponents()) {
                component.setVisible(false);
            }
            add(h2pImg);
            h2pImg.setBounds(0, 0, GameRunner.SCREEN_WIDTH, GameRunner.SCREEN_HEIGHT);
            h2pImg.setVisible(true);

            h2pBackButt = new JButton("Back");
            h2pBackButt.setBackground(Color.decode("#C0C0C0"));
            h2pBackButt.setFocusPainted(false);
            h2pBackButt.setFont(gameRunner.getFont());
            h2pBackButt.setBounds(20, 20, 150, 50);
            add(h2pBackButt);

            h2pBackButt.addActionListener(ee -> {
                remove(h2pImg);
                remove(h2pBackButt);

                for (Component component : getComponents()) {
                    component.setVisible(true);
                }
            });
        }

    private JLabel createImageLabel(String filePath, int x, int y, int width, int height) throws IOException {
        ImageIcon icon = new ImageIcon(ImageIO.read(new File(filePath)).getScaledInstance(width, height, Image.SCALE_SMOOTH));
        JLabel label = new JLabel(icon);
        label.setBounds(x, y, width, height);
        return label;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.setFont(gameRunner.getFont());
        if (gameRunner.getHighScore() < 100) {
            g.drawString("Highest Score: " + gameRunner.getHighScore(), 960, 50);
        } else {
            g.drawString("Highest Score: " + gameRunner.getHighScore(), 940, 50);
        }
    }
}
