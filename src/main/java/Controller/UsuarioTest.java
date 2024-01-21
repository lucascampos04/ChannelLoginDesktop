package Controller;

import com.gestao.vendas.gestaovendas.Model.dao.UsuarioDao;
import com.gestao.vendas.gestaovendas.Model.domain.Perfil;
import com.gestao.vendas.gestaovendas.Model.domain.Usuario;

import java.time.LocalDateTime;

public class UsuarioTest {
    public static void main(String[] args) {
        Usuario usuario = new Usuario(1L, "lucas", "123", "lucas", Perfil.ADMIN, LocalDateTime.now(), LocalDateTime.now());
        UsuarioDao usuarioDao = new UsuarioDao();
        String message = usuarioDao.editar(usuario);
        System.out.println(message);
            
    }
}
