package database;

import com.kurachenko.entity.enums.Qualification;
import org.junit.Test;

/**
 * @author Pavel Kurachenko
 * @since 1/21/2017
 */
public class TestQualification {

    @Test
    public void test(){
        Qualification junior = Qualification.JUNIOR;
        Qualification middle = Qualification.MIDDLE;
        Qualification senior = Qualification.SENIOR;
        Qualification techLead = Qualification.TECHLEAD;

        System.out.println(junior.ordinal() > middle.ordinal());
        System.out.println(middle.ordinal());
        System.out.println(senior);
        System.out.println(techLead);



        Qualification junior2 = Qualification.valueOf(junior.toString());

        System.out.println(String.valueOf(junior2));
    }
}
