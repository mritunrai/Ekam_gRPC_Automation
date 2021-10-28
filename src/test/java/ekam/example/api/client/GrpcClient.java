package ekam.example.api.client;

import com.examples.bookstore.BookAuthorRequest;
import com.examples.bookstore.BookResponse;
import com.examples.bookstore.BookServiceGrpc;
import com.examples.bookstore.GetBookRequest;
import com.google.inject.Inject;
import com.testvagrant.ekam.api.annotations.GrpcRequest;
import com.testvagrant.ekam.api.annotations.GrpcResponse;
import com.testvagrant.ekam.reports.annotations.APIStep;
import io.grpc.ManagedChannel;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;

import javax.inject.Named;
import java.net.URI;
import java.net.URISyntaxException;

public class GrpcClient {
    ManagedChannel channel;
    BookServiceGrpc.BookServiceBlockingStub bookServiceStub;

    @Inject
    public GrpcClient(@Named("host") String host) throws URISyntaxException {
        channel = build(host);
        bookServiceStub = com.examples.bookstore.BookServiceGrpc.newBlockingStub(channel);
    }

    public ManagedChannel build(String host) throws URISyntaxException {
        URI uri = new URI(host);
        return NettyChannelBuilder.forAddress(uri.getHost(), uri.getPort())
                .negotiationType(NegotiationType.PLAINTEXT)
                .build();
    }

    @APIStep(keyword = "When", description = "I invoke getBookRequest call")
    public @GrpcResponse
    BookResponse getBookByISBN(@GrpcRequest GetBookRequest getBookRequest) {

        return bookServiceStub.getBook(getBookRequest);
    }

    @APIStep(keyword = "When", description = "I invoke getBookRequest call")
    public @GrpcResponse
    BookResponse getBookByAuthor(@GrpcRequest BookAuthorRequest bookAuthorRequest) {
        return bookServiceStub.getBooksViaAuthor(bookAuthorRequest);
    }
}