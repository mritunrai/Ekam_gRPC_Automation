package ekam.example.api.dataClient;

import com.testvagrant.ekam.commons.data.DataSetsClient;
import ekam.example.api.dataClient.models.Book;

public class BookDataClient extends DataSetsClient {

    public Book getBook() {
        return getValue("book", Book.class);
    }
}