package Tests;

import Database.DB;
import Database.SQL;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class testSQL {
    // insert ოპერაციების სისწორის შემოწმება უკეთ მოხდება, როცა search-ის იმპლემენტაციაც მოხდება. ამჟამად, მთავარი მიზანია,
    // რომ დაინახოთ, როგორ ხდება insert ოპერაციაში პარამეტრების გადაცემა.
    @Test
    public void testInsert(){
        DB db = new SQL();

        // სხვადასხვა ცხრილებზე insert ოპერაციის გაშვების მაგალითები. დააკვირდით, რომ TEXT, CHAR(n), DATE და ა.შ. ტიპის
        // სვეტების შესაბამისი მნიშვნელობები დამატებით მოქცეულია ერთხაზიან ბრჭყალებში ('...'), ხოლო ასეთი ბრჭყალები აღარ
        // სჭირდება, მაგ; INT, DOUBLE და ა.შ. ტიპის სვეტების შესაბამის მნიშვნელობებს. model კლასების ფუნქციების იმპლემენტაციის
        // დროს ყურადღება მიაქციეთ, რომ სწორად გადააწოდოთ SQL-ს პარამეტრები.
        List<String> user = Arrays.asList("'sjanj'", "'Shorena'", "'Janjghava'", "'2001-05-31'", "'RottenPotatoes'");
        List<String> item1 = Arrays.asList("'MO_Cruella_2021'", "'Cruella'", "'MOVIES'", "'sjanj'", "0", "'cruella.jpg'");

        // ასევე: ჯერ items ცხრილში უნდა ჩაამატოთ ნივთი და შემდეგ მისი კონკრეტული კატეგორიის ცხრილში, რადგან კატეგორიების
        // ცხრილები items ცხრილიდან იყენებენ id ველს foreign key-დ.
        List<String> item2 = Arrays.asList("'BO_Cosmos_1980'", "'Cosmos'", "'BOOKS'", "'sjanj'", "0", "'cosmos.jpg'");
        List<String> book = Arrays.asList("'BO_Cosmos_1980'", "'Cosmos'", "1980", "'Carl Sagan'", "'cosmos.jpg'",
                                            "'A must-read.'", "'sjanj'", "0", "0");

        db.insert("USERS", user);
        db.insert("ITEMS", item1);
        db.insert("ITEMS", item2);
        db.insert("BOOKS", book);
    }
}