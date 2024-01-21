package Controller;

import com.gestao.vendas.gestaovendas.Model.dao.Autenticacao;
import com.gestao.vendas.gestaovendas.Model.domain.Usuario;
import com.gestao.vendas.gestaovendas.View.LoginDto;
import com.gestao.vendas.gestaovendas.View.form.LoginUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class LoginController implements ActionListener {
    
    private final LoginUI login;
    private Autenticacao autenticacao;

    public LoginController(LoginUI login) {
        this.login = login;
        this.autenticacao = new Autenticacao();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Cancelar":
                cancelar();
                break;
            case "Login":
                login();
                break;
            default:
                System.out.println("Ação não reconhecida");
                break;
        }
    }
    
    private void login(){
        String usuario = this.login.getTxTLoginInput().getText();
        String senha = this.login.getPasswordInput().getText();
        
        if (usuario.equals("")|| senha.equals("")){
            this.login.getjLabel4().setText("Usuário e senha devem ser preenchidos");
            return;
        }
        
        LoginDto loginDto = new LoginDto(usuario, senha);
        Usuario usuarioTem = autenticacao.login(loginDto);
        
        
        
        if (usuarioTem != null){
            JOptionPane.showConfirmDialog(null, usuarioTem.getNome());
            this.login.getjLabel4().setText("Login realizado com sucesso");
            System.out.println("Login realizado com sucesso");
        } else {
            this.login.getjLabel4().setText("Usuario e senha incorreto");
        }
    }
    
    private void cancelar(){
        int confirm = JOptionPane.showConfirmDialog(login, "Tem certeza ?", "Sair do sistema", JOptionPane.YES_NO_CANCEL_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION){
            System.exit(0);
        }             
    }
    
    public void limparCampos(){
        this.login.getTxTLoginInput().setText("");
        this.login.getPasswordInput().setText("");
        this.login.getjLabel4().setText("");
    }
}
