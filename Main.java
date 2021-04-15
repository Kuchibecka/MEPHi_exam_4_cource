import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws IOException {
        class Book {
            private int id;
            private String author;
            private String name;
            private String published_by;
            private String category;

            // All Args Constructor
            public Book(String author, String name, String published_by,
                        String category) {
                this.author = author;
                this.name = name;
                this.published_by = published_by;
                this.category = category;
            }

            // getters and setters:
            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPublished_by() {
                return published_by;
            }

            public void setPublished_by(String published_by) {
                this.published_by = published_by;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }

        class Library {
            private List<Book> bookList;

            public void addBook(Book book) {
                try {
                    this.bookList.add(book);
                    System.out.println("Successfully added book");
                } catch (Exception e) {
                    System.out.println("Error occurred while trying to add the book");
                    System.out.println(e.getMessage());
                }
            }

            public boolean bookAvailable(Book book) {
                return this.bookList.contains(book);
            }

            public void removeBook(Book book) {
                if (bookAvailable(book)) {
                    this.bookList.remove(book);
                    System.out.println("Successfully removed book");
                } else {
                    System.out.println("Error occurred while trying to remove the book:");
                    System.out.println("No such book in library");
                }
            }

            public List<Book> byAuthor(String author) {
                List<Book> books = new ArrayList<>();
                for (Book b : this.bookList) {
                    if (b.getAuthor().toLowerCase(Locale.ROOT) == author.toLowerCase(Locale.ROOT))
                        books.add(b);
                }
                return books;
            }

            public List<Book> byCategory(String category) {
                List<Book> books = new ArrayList<>();
                for (Book b : this.bookList) {
                    if (b.getCategory().toLowerCase(Locale.ROOT) == category.toLowerCase(Locale.ROOT))
                        books.add(b);
                }
                return books;
            }

            public Book byName(String name) {
                for (Book b : this.bookList) {
                    if (b.getName().toLowerCase(Locale.ROOT) == name.toLowerCase(Locale.ROOT))
                        return b;
                }
                return null;
            }

            public List<Book> byAvailability() {
                return this.bookList;
            }

            // All Args Constructor
            public Library(List<Book> bookList) {
                this.bookList = bookList;
            }

            // No Args Constructor
            public Library() {
            }

        }


        // testing

        Book book1 = new Book("Фридрих Ницше", "Так говорил Заратустра",
                "Издательство АСТ", "Философия");
        Book book2 = new Book("Данте Алигьери", "Божественная комедия",
                "Издательство АСТ", "Философия");

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        System.out.println(books);

        Library library = new Library(books);

        System.out.println("1) Поиск книг \n" +
                "2) Добавление/удаление книг \n" +
                "3) Сделать дамп-файл \n"
        );

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String menu = reader.readLine();

        if (menu == "1"){
            System.out.println("Выберите вариант поиска \n" +
                    "1) По автору \n" +
                    "2) По разделу \n" +
                    "3) По наличию \n"
            );
            String search = reader.readLine();
            if (search == "1"){
                System.out.println("Введите имя автора \n");
                String author = reader.readLine();
                System.out.println(library.byAuthor(author));
            }
            if (search == "2"){
                System.out.println("Введите название раздела \n");
                String category = reader.readLine();
                System.out.println(library.byCategory(category));
            }
            if (search == "3"){
                System.out.println(library.byAvailability());
            }
        }
        if (menu == "2"){
            System.out.println("1) Добавить книгу \n" +
                    "2) Удалить книгу по названию"
            );
            String s = reader.readLine();
            if (s == "1") {
                System.out.println("Введите автора книги \n");
                String author = reader.readLine();
                System.out.println("Введите название книги \n");
                String name = reader.readLine();
                System.out.println("Введите издательство \n");
                String lib = reader.readLine();
                System.out.println("Введите раздел \n");
                String category = reader.readLine();
                Book addedBook = new Book(author, name, lib, category);
                library.addBook(addedBook);
            }
            if (s == "2"){
                System.out.println("Введите название книги \n");
                String nname = reader.readLine();
                library.removeBook(library.byName(nname));
            }
        }
        if (menu == "3"){
            try(FileWriter writer = new FileWriter("dump.txt", false))
            {
                String text = library.bookList.toString();
                writer.write(text);
                writer.flush();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

}
