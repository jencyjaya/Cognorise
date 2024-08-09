import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Book {
    String title;
    String author;
    String status;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.status = "Available";
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

public class LibraryCatalogGUI extends JFrame {
    private ArrayList<Book> books;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField searchField;
    private JLabel statusLabel;

    public LibraryCatalogGUI() {
        books = new ArrayList<>();

        createGUI();
    }

    private void createGUI() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));


        JPanel addBookPanel = new JPanel();
        addBookPanel.add(new JLabel("Title:"));
        titleField = new JTextField(10);
        addBookPanel.add(titleField);
        addBookPanel.add(new JLabel("Author:"));
        authorField = new JTextField(10);
        addBookPanel.add(authorField);
        JButton addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(new AddBookButtonActionListener());
        addBookPanel.add(addBookButton);
        add(addBookPanel);


        JPanel searchBookPanel = new JPanel();
        searchBookPanel.add(new JLabel("Search:"));
        searchField = new JTextField(10);
        searchBookPanel.add(searchField);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchButtonActionListener());
        searchBookPanel.add(searchButton);
        add(searchBookPanel);


        statusLabel = new JLabel();
        add(statusLabel);

        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class AddBookButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String title = titleField.getText();
            String author = authorField.getText();
            Book book = new Book(title, author);
            books.add(book);
            titleField.setText("");
            authorField.setText("");
            statusLabel.setText("Book added successfully!");
        }
    }

    private class SearchButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String search = searchField.getText();
            for (Book book : books) {
                if (book.getTitle().equals(search) || book.getAuthor().equals(search)) {
                    statusLabel.setText("Book found! Status: " + book.getStatus());
                    return;
                }
            }
            statusLabel.setText("Book not found!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LibraryCatalogGUI();
            }
        });
    }
}

