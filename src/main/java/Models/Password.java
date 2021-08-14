package Models;

import Database.DB;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Password {

    public static String hexToString(byte[] bytes) {
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            int val = bytes[i];
            val = val & 0xff;
            if (val < 16) buff.append('0');
            buff.append(Integer.toString(val, 16));
        }
        return buff.toString();
    }

    public static String getHash (String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA");
        return hexToString(md.digest(password.getBytes()));
    }

    public static boolean validInfo (DB db, String username, String password) throws SQLException, NoSuchAlgorithmException {
        User user = User.getUserByUsername(db, username);
        if (user == null) return false;
        String hashPasswordForThisUser = user.getPassword();
        String enteredPasswordHash = getHash(password);
        if (hashPasswordForThisUser.equals(enteredPasswordHash)) return true;
        return false;
    }
}
