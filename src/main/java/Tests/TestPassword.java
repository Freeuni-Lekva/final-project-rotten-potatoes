package Tests;

import Database.DB;
import Database.SQL;
import Models.Password;
import junit.framework.TestCase;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class TestPassword extends TestCase {

    public void testGetHash() throws NoSuchAlgorithmException {
        assertEquals("86f7e437faa5a7fce15d1ddcb9eaeaea377667b8", Password.getHash("a"));
        assertEquals("adeb6f2a18fe33af368d91b09587b68e3abcb9a7", Password.getHash("fm"));
    }

    public void testValidInfo() throws SQLException, NoSuchAlgorithmException {
        DB db = new SQL();
        db.delete("USERS", "username", "nobody", "username", "anybody");
        assertTrue(Password.validInfo(db, "admin", "RottenPotatoes"));
        assertTrue(Password.validInfo(db, "tekla", "teklatekla"));
        assertFalse(Password.validInfo(db, "nobody", "something"));
        assertFalse(Password.validInfo(db, "anybody", "something"));
        assertFalse(Password.validInfo(db, "admin", "privetebi"));
    }
}
