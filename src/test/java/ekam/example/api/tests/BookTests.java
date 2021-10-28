package ekam.example.api.tests;

import com.examples.bookstore.BookAuthorRequest;
import com.examples.bookstore.BookResponse;
import com.examples.bookstore.GetBookRequest;
import com.google.inject.Inject;
import com.testvagrant.ekam.testBases.testng.APITest;
import ekam.example.api.client.GrpcClient;
import ekam.example.api.dataClient.BookDataClient;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.testvagrant.ekam.commons.LayoutInitiator.Client;

public class BookTests extends APITest {
    @Inject
    private BookDataClient bookDataClient;

    @Test(groups = "api")
    public void getBookTests() {
        Integer isbn = bookDataClient.getBook().getIsbn();

        GetBookRequest getBookRequest = GetBookRequest.newBuilder().setIsbn(isbn).build();

        BookResponse bookResponse = Client(GrpcClient.class)
                .getBookByISBN(getBookRequest);

        Assert.assertEquals(bookResponse.getResponseCode(), "200");
    }

    @Test(groups = "api")
    public void getAuthorTests() {
        String author = bookDataClient.getBook().getAuthor();

        BookAuthorRequest bookAuthorRequest = BookAuthorRequest.newBuilder().setAuthor(author).build();

        BookResponse bookResponse = Client(GrpcClient.class)
                .getBookByAuthor(bookAuthorRequest);

        Assert.assertEquals(bookResponse.getResponseCode(), "200");
    }
}