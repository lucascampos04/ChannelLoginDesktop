package Controller;

import com.gestao.vendas.gestaovendas.View.form.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
    
    private final Login login;

    public LoginController(Login login) {
        this.login = login;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Cancelar":
                login.dispose();
                break;
            case "Login":
                System.out.println("Botão Login clicado");
                break;
            default:
                System.out.println("Ação não reconhecida");
                break;
        }
    }
}
