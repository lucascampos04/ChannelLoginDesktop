package Controller;

import com.gestao.vendas.gestaovendas.Model.dao.UsuarioDao;
import com.gestao.vendas.gestaovendas.Model.domain.Perfil;
import com.gestao.vendas.gestaovendas.Model.domain.Usuario;

import java.time.LocalDateTime;

public class UsuarioTest {
    public static void main(String[] args) {
        Usuario usuario = new Usuario(0L, "Lucas", "123", "Lucascampos1", Perfil.ADMIN, null, null);
        UsuarioDao usuarioDao = new UsuarioDao();
        String message = usuarioDao.salvar(usuario);
        System.out.println(message);


    }
}
