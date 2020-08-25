
import com.staxter.communication.PlayerCommunication;
import com.staxter.util.constant.Constant;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class PlayerMainTest {

    @Test
    public void testSalutationMessage() {
        try {
            PlayerCommunication playerComm = new PlayerCommunication(Constant.messageCount);
        }catch(Exception e){
            Assert.fail();
        }
    }
}
