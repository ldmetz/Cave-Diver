//Levi Metzger
//Cave Diver

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;

/**
 * Constructs the GUI for the Cave Diver program
 */

public class CaveDiverFrame extends JFrame {
    private JPanel mainPanel;
    private JLabel topText;
    private JPanel bottomPanel;
    private CaveComponent caveComponent;
    Cave cave;

    public CaveDiverFrame(){
        this.setTitle("Cave Diver - find an escape route");
        this.setResizable(false);

        cave = new Cave();

        mainPanel = new JPanel(new BorderLayout());
        this.add(mainPanel);

        topText = new JLabel("The diver starts in the top-left corner and escapes by moving down/right to the bottom-right corner.");
        topText.setHorizontalAlignment(SwingConstants.CENTER);
        topText.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));
        mainPanel.add(topText, BorderLayout.NORTH);

        caveComponent = new CaveComponent();
        mainPanel.add(caveComponent, BorderLayout.CENTER);

        bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));
        bottomPanel.add(new JLabel("Enter the diver's depth rating:"));

        JTextField depthRatingField = new JTextField(3);
        JButton escapeButton = new JButton("Escape");
        escapeButton.addActionListener(e -> {
            try{
                if(cave.getCell(0,0).isEscapeRoute()){
                    cave.resetCave();
                }

                int depthRating = Integer.parseInt(depthRatingField.getText());
                if(cave.escape(depthRating)){
                    caveComponent.repaint();
                }
                else{
                    JOptionPane.showMessageDialog(CaveDiverFrame.this, "No escape route found", "Try again", JOptionPane.INFORMATION_MESSAGE);                    
                }
            }
            catch(IllegalArgumentException error){
                JOptionPane.showMessageDialog(CaveDiverFrame.this, "You must enter a number between 1 and 10", "Wrong input", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton newCaveButton = new JButton("New Cave");
        newCaveButton.addActionListener(e -> {
            cave = new Cave();
            caveComponent.repaint();
        });

        bottomPanel.add(depthRatingField);
        bottomPanel.add(escapeButton);
        bottomPanel.add(newCaveButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        bottomPanel.getRootPane().setDefaultButton(escapeButton);

        pack();
    }

    /**
     * Creates the cave grid
     */

    private class CaveComponent extends JComponent {
            final int CELL_SIZE = 45;

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                final int LEFT = (getWidth() - CELL_SIZE * 10) / 2;
                final int TOP = (getHeight() - CELL_SIZE * 10) / 2;

                for(int i = 0; i < 10; i++){
                    for(int j = 0; j < 10; j++){
                        CaveCell cell = cave.getCell(j, i);
                        g.setColor(cell.isEscapeRoute() ? Color.RED : new Color(0, 0, (150 - (15 * cell.getCellDepth()))));
                        g.fillRect(LEFT + j * CELL_SIZE, TOP + i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                        g.setColor(Color.WHITE);
                        g.drawString(cell.getCellDepth() + "" , LEFT + j * CELL_SIZE, TOP + i * CELL_SIZE + 10);
                    }
                }
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(CELL_SIZE * 10, CELL_SIZE * 10);
            }
        }

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            CaveDiverFrame frame = new CaveDiverFrame();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}
