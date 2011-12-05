import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class MyBean {
    public int addNumbers(int numberA, int numberB) {
        return numberA + numberB;
    }
}