import javax.swing.SwingUtilities;
import com.netbanking.ui.NetBankingFrame;
import com.netbanking.model.DataManager;
import com.netbanking.model.User;

public class NetBankingApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            User savedUser = DataManager.loadUser();
            if (savedUser != null) {
                System.out.println("Loaded saved user data.");
            }
            // If null, NetBankingFrame will create default user
            
            NetBankingFrame frame = new NetBankingFrame(savedUser);
            
            // Save on exit
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                DataManager.saveUser(frame.getCurrentUser());
            }));
        });
    }
}
