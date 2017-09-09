package jp.tokushima.tatsuhama.realmmodellib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import io.realm.Realm;
import jp.tokushima.tatsuhama.realmmodellib.model.Book;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        long currentTime = System.currentTimeMillis();
        String randomString = Long.toString(currentTime);
        int randomInt = (int) currentTime;

        Book book = createBook(randomString, randomInt);
        saveRealm(book);
        boolean isSuccess = checkRealmValue(randomString, randomInt);
        Toast.makeText(this, "isSuccess = " + isSuccess, Toast.LENGTH_LONG).show();
    }

    private Book createBook(String name, int price) {
        Book book = new Book();
        book.name = name;
        book.price = price;
        return book;
    }

    private void saveRealm(Book book) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(book);
        realm.commitTransaction();
        realm.close();
    }

    private boolean checkRealmValue(String name, int price) {
        Realm realm = Realm.getDefaultInstance();
        Book book = realm.where(Book.class).equalTo("name", name).findAll().first();
        boolean result = name.equals(book.name) && price == book.price;
        realm.close();
        return result;
    }
}
