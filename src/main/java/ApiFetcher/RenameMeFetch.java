package ApiFetcher;

import utils.HttpUtils;

import java.io.IOException;

public class RenameMeFetch {

    public static void renameMeFetch() throws IOException {

        String dummyFetch = HttpUtils.fetchData("https://api.chucknorris.io/jokes/random");
        System.out.println("Test fetch");
        System.out.println(dummyFetch);
    }
}
