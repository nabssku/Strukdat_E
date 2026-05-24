// Tugas Hashmap
//Salsabila Ayu Azhara - 202410370110348
//Derrick M Hanif - 202410370110383
//Nabil Sahsada Suratno - 202410370110357


package teory.pertemuan7;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class KamusMini extends JFrame {

    private static final Color BG = new Color(248, 250, 252);
    private static final Color CARD = Color.WHITE;
    private static final Color BORDER = new Color(218, 220, 224);
    private static final Color BLUE = new Color(26, 115, 232);
    private static final Color BLUE_HOVER = new Color(24, 90, 188);
    private static final Color TEXT = new Color(32, 33, 36);
    private static final Color MUTED = new Color(95, 99, 104);
    private static final Color ERROR = new Color(217, 48, 37);
    private static final Color SUCCESS = new Color(24, 128, 56);

    private final HashMap<String, String> engToInd = new LinkedHashMap<>();
    private final HashMap<String, String> indToEng = new LinkedHashMap<>();

    private JTextArea inputArea;
    private JTextArea outputArea;
    private JLabel sourceLangLabel;
    private JLabel targetLangLabel;
    private JLabel statusLabel;
    private JButton translateButton;
    private JButton swapButton;

    private boolean engToIndMode = true;

    public KamusMini() {
        initDictionary();
        buildUI();
        setVisible(true);

        SwingUtilities.invokeLater(() -> inputArea.requestFocusInWindow());
    }

    static class WordNotFoundException extends RuntimeException {
        public WordNotFoundException(String message) {
            super(message);
        }
    }

    private void initDictionary() {
        String[][] data = {
                {"sun", "matahari"}, {"moon", "bulan"}, {"star", "bintang"}, {"sky", "langit"},
                {"rain", "hujan"}, {"wind", "angin"}, {"cloud", "awan"}, {"river", "sungai"},
                {"sea", "laut"}, {"mountain", "gunung"}, {"forest", "hutan"}, {"flower", "bunga"},
                {"tree", "pohon"}, {"stone", "batu"}, {"fire", "api"}, {"water", "air"},
                {"earth", "bumi"}, {"island", "pulau"}, {"lake", "danau"}, {"beach", "pantai"},

                {"cat", "kucing"}, {"dog", "anjing"}, {"bird", "burung"}, {"fish", "ikan"},
                {"cow", "sapi"}, {"horse", "kuda"}, {"rabbit", "kelinci"}, {"tiger", "harimau"},
                {"elephant", "gajah"}, {"snake", "ular"}, {"chicken", "ayam"}, {"duck", "bebek"},
                {"butterfly", "kupu-kupu"}, {"bee", "lebah"}, {"ant", "semut"}, {"frog", "katak"},
                {"monkey", "monyet"}, {"deer", "rusa"}, {"crocodile", "buaya"}, {"eagle", "elang"},

                {"mother", "ibu"}, {"father", "ayah"}, {"brother", "saudara laki-laki"},
                {"sister", "saudara perempuan"}, {"child", "anak"}, {"baby", "bayi"},
                {"friend", "teman"}, {"teacher", "guru"}, {"student", "murid"}, {"doctor", "dokter"},
                {"farmer", "petani"}, {"soldier", "prajurit"}, {"king", "raja"}, {"queen", "ratu"},
                {"grandfather", "kakek"}, {"grandmother", "nenek"}, {"husband", "suami"},
                {"wife", "istri"}, {"nephew", "keponakan laki-laki"}, {"niece", "keponakan perempuan"},

                {"rice", "nasi"}, {"bread", "roti"}, {"egg", "telur"}, {"milk", "susu"},
                {"meat", "daging"}, {"fruit", "buah"}, {"vegetable", "sayuran"}, {"sugar", "gula"},
                {"salt", "garam"}, {"oil", "minyak"}, {"coffee", "kopi"}, {"tea", "teh"},
                {"juice", "jus"}, {"cake", "kue"}, {"soup", "sup"}, {"noodle", "mie"},
                {"chocolate", "cokelat"}, {"cheese", "keju"}, {"butter", "mentega"}, {"honey", "madu"},

                {"house", "rumah"}, {"school", "sekolah"}, {"hospital", "rumah sakit"},
                {"market", "pasar"}, {"church", "gereja"}, {"mosque", "masjid"},
                {"road", "jalan"}, {"bridge", "jembatan"}, {"city", "kota"}, {"village", "desa"},
                {"hotel", "hotel"}, {"airport", "bandara"}, {"station", "stasiun"},
                {"library", "perpustakaan"}, {"park", "taman"}, {"office", "kantor"},

                {"run", "berlari"}, {"walk", "berjalan"}, {"eat", "makan"}, {"drink", "minum"},
                {"sleep", "tidur"}, {"read", "membaca"}, {"write", "menulis"}, {"speak", "berbicara"},
                {"listen", "mendengarkan"}, {"see", "melihat"}, {"love", "cinta"}, {"hate", "benci"},

                {"happy", "bahagia"}, {"sad", "sedih"}, {"big", "besar"}, {"small", "kecil"},
                {"good", "baik"}, {"bad", "buruk"}, {"fast", "cepat"}, {"slow", "lambat"},

                {"book", "buku"}, {"pen", "pena"}, {"door", "pintu"}, {"window", "jendela"},
                {"table", "meja"}, {"chair", "kursi"}, {"color", "warna"}, {"music", "musik"},
                {"time", "waktu"}, {"day", "hari"}, {"night", "malam"}, {"morning", "pagi"},
                {"name", "nama"}, {"number", "angka"}, {"dream", "mimpi"}, {"life", "kehidupan"},
                {"world", "dunia"}, {"heart", "hati"}, {"mind", "pikiran"}, {"hope", "harapan"}
        };

        for (String[] pair : data) {
            engToInd.put(pair[0].toLowerCase(), pair[1].toLowerCase());
            indToEng.put(pair[1].toLowerCase(), pair[0].toLowerCase());
        }
    }

    private void buildUI() {
        setTitle("Kamus Mini");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(760, 520);
        setMinimumSize(new Dimension(660, 460));
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG);
        root.setBorder(new EmptyBorder(24, 28, 18, 28));

        root.add(buildHeader(), BorderLayout.NORTH);
        root.add(buildMainContent(), BorderLayout.CENTER);
        root.add(buildFooter(), BorderLayout.SOUTH);

        setContentPane(root);
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(new EmptyBorder(0, 0, 18, 0));

        JPanel titleBox = new JPanel();
        titleBox.setOpaque(false);
        titleBox.setLayout(new BoxLayout(titleBox, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Kamus Mini");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(TEXT);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel("Terjemahkan kata Inggris dan Indonesia dengan cepat");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setForeground(MUTED);
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        titleBox.add(title);
        titleBox.add(Box.createVerticalStrut(4));
        titleBox.add(subtitle);

        translateButton = createPrimaryButton("Terjemahkan");
        translateButton.addActionListener(e -> doTranslate());

        header.add(titleBox, BorderLayout.WEST);
        header.add(translateButton, BorderLayout.EAST);

        return header;
    }

    private JPanel buildMainContent() {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);

        JPanel translateCard = new JPanel(new BorderLayout());
        translateCard.setBackground(CARD);
        translateCard.setBorder(new RoundedBorder(18, BORDER, 1));

        JPanel languageBar = buildLanguageBar();
        JPanel body = buildTranslateBody();

        translateCard.add(languageBar, BorderLayout.NORTH);
        translateCard.add(body, BorderLayout.CENTER);

        wrapper.add(translateCard, BorderLayout.CENTER);

        return wrapper;
    }

    private JPanel buildLanguageBar() {
        JPanel bar = new JPanel(new GridBagLayout());
        bar.setBackground(CARD);
        bar.setBorder(new MatteBorder(0, 0, 1, 0, BORDER));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(14, 18, 14, 18);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        sourceLangLabel = createLangLabel("Inggris");
        targetLangLabel = createLangLabel("Indonesia");

        swapButton = new RoundedButton("Tukar", BLUE, BLUE_HOVER);
        swapButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        swapButton.setPreferredSize(new Dimension(86, 38));
        swapButton.addActionListener(e -> swapLanguage());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        bar.add(sourceLangLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0;
        bar.add(swapButton, gbc);

        gbc.gridx = 2;
        gbc.weightx = 1;
        bar.add(targetLangLabel, gbc);

        return bar;
    }

    private JPanel buildTranslateBody() {
        JPanel body = new JPanel(new GridLayout(1, 2));
        body.setBackground(CARD);

        JPanel inputPanel = createTextPanel(true);
        JPanel outputPanel = createTextPanel(false);

        body.add(inputPanel);
        body.add(outputPanel);

        return body;
    }

    private JPanel createTextPanel(boolean isInput) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CARD);

        if (isInput) {
            panel.setBorder(new MatteBorder(0, 0, 0, 1, BORDER));
        }

        JTextArea area = new JTextArea();
        area.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        area.setForeground(TEXT);
        area.setBackground(CARD);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(new EmptyBorder(22, 22, 22, 22));

        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setBorder(null);
        scrollPane.setBackground(CARD);
        scrollPane.getViewport().setBackground(CARD);

        if (isInput) {
            inputArea = area;
            inputArea.setText("");
            inputArea.setToolTipText("Contoh: cat, drink, book");

            inputArea.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER && !e.isShiftDown()) {
                        e.consume();
                        doTranslate();
                    }
                }
            });

            JPanel bottom = new JPanel(new BorderLayout());
            bottom.setBackground(CARD);
            bottom.setBorder(new EmptyBorder(0, 22, 18, 22));

            JLabel hint = new JLabel("Tekan Enter untuk menerjemahkan");
            hint.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            hint.setForeground(MUTED);

            bottom.add(hint, BorderLayout.WEST);

            panel.add(scrollPane, BorderLayout.CENTER);
            panel.add(bottom, BorderLayout.SOUTH);

        } else {
            outputArea = area;
            outputArea.setEditable(false);
            outputArea.setForeground(MUTED);
            outputArea.setText("Hasil terjemahan");
            outputArea.setFont(new Font("Segoe UI", Font.PLAIN, 24));

            panel.add(scrollPane, BorderLayout.CENTER);
        }

        return panel;
    }

    private JPanel buildFooter() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setOpaque(false);
        footer.setBorder(new EmptyBorder(14, 0, 0, 0));

        statusLabel = new JLabel(engToInd.size() + " kata tersedia");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusLabel.setForeground(MUTED);

        JLabel copyright = new JLabel("© 2026 Translate Universitas Muhammadiyah Malang | UMM");
        copyright.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        copyright.setForeground(MUTED);

        footer.add(statusLabel, BorderLayout.WEST);
        footer.add(copyright, BorderLayout.EAST);

        return footer;
    }

    private JLabel createLangLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(BLUE);
        return label;
    }

    private JButton createPrimaryButton(String text) {
        JButton button = new RoundedButton(text, BLUE, BLUE_HOVER);
        button.setPreferredSize(new Dimension(136, 46));
        return button;
    }

    private void doTranslate() {
        String input = inputArea.getText().trim().toLowerCase();

        if (input.isEmpty()) {
            outputArea.setForeground(MUTED);
            outputArea.setFont(new Font("Segoe UI", Font.PLAIN, 24));
            outputArea.setText("Masukkan kata terlebih dahulu");

            statusLabel.setForeground(ERROR);
            statusLabel.setText("Input masih kosong");
            return;
        }

        try {
            String result;

            if (engToIndMode) {
                result = engToInd.get(input);
            } else {
                result = indToEng.get(input);
            }

            if (result == null) {
                throw new WordNotFoundException("Kata tidak ditemukan di kamus");
            }

            outputArea.setForeground(TEXT);
            outputArea.setFont(new Font("Segoe UI", Font.BOLD, 28));
            outputArea.setText(capitalize(result));

            statusLabel.setForeground(SUCCESS);
            statusLabel.setText("Berhasil menerjemahkan: " + input);

        } catch (WordNotFoundException e) {
            outputArea.setForeground(ERROR);
            outputArea.setFont(new Font("Segoe UI", Font.PLAIN, 22));
            outputArea.setText(e.getMessage());

            statusLabel.setForeground(ERROR);
            statusLabel.setText("Kata \"" + input + "\" tidak tersedia");
        }
    }

    private void swapLanguage() {
        engToIndMode = !engToIndMode;

        if (engToIndMode) {
            sourceLangLabel.setText("Inggris");
            targetLangLabel.setText("Indonesia");
        } else {
            sourceLangLabel.setText("Indonesia");
            targetLangLabel.setText("Inggris");
        }

        String outputText = outputArea.getText().trim();

        boolean outputValid =
                !outputText.equalsIgnoreCase("Hasil terjemahan")
                        && !outputText.equalsIgnoreCase("Masukkan kata terlebih dahulu")
                        && !outputText.equalsIgnoreCase("Kata tidak ditemukan di kamus")
                        && !outputText.isEmpty();

        if (outputValid) {
            inputArea.setText(outputText.toLowerCase());
        }

        outputArea.setForeground(MUTED);
        outputArea.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        outputArea.setText("Hasil terjemahan");

        statusLabel.setForeground(MUTED);
        statusLabel.setText("Bahasa berhasil ditukar");

        inputArea.requestFocusInWindow();
    }

    private String capitalize(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    static class RoundedButton extends JButton {
        private final Color normalColor;
        private final Color hoverColor;
        private boolean hover = false;

        public RoundedButton(String text, Color normalColor, Color hoverColor) {
            super(text);
            this.normalColor = normalColor;
            this.hoverColor = hoverColor;

            setFont(new Font("Segoe UI", Font.BOLD, 14));
            setForeground(Color.WHITE);
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setOpaque(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setBorder(new EmptyBorder(10, 20, 10, 20));

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    hover = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hover = false;
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(hover ? hoverColor : normalColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);

            g2.setFont(getFont());
            FontMetrics fm = g2.getFontMetrics();

            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;

            g2.setColor(getForeground());
            g2.drawString(getText(), x, y);

            g2.dispose();
        }
    }

    static class RoundedBorder extends AbstractBorder {
        private final int radius;
        private final Color color;
        private final int thickness;

        RoundedBorder(int radius, Color color, int thickness) {
            this.radius = radius;
            this.color = color;
            this.thickness = thickness;
        }

        @Override
        public void paintBorder(Component component, Graphics graphics, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) graphics.create();

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(thickness));
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);

            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component component) {
            return new Insets(thickness, thickness, thickness, thickness);
        }
    }

    public static void main(String[] args) {
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            new KamusMini();
        });
    }
}