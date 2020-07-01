package controller;

import dao.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.*;
import net.sf.jasperreports.engine.JRException;

public class UsuarioController {
    DaoUsuario usuarioDao = new DaoUsuarioImp();

    public void salvarUsuario(Usuario u) {
        if (u != null && !u.getNome().equals("")
                && !u.getEmail().equals("")
                && !u.getSenha().equals("")) {
            usuarioDao.salvarUsuario(u);
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios!");
        }
    }

    public void alterarUsuario(Usuario u) {
        if (u != null && !u.getNome().equals("")
                && !u.getEmail().equals("")
                && !u.getSenha().equals("")) {
            usuarioDao.editarUsuario(u);
            JOptionPane.showMessageDialog(null, "Editado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios!");
        }
    }

    public void excluirUsuario(int id) {
        int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente exluir o registro?");
        if (resp == 1) {
            JOptionPane.showMessageDialog(null, "Você não excluiu o registro");
        } else {
            if (id != 0) {
                usuarioDao.deletarUsuario(id);
                JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Informe um usuário para ser excluída!");
            }
        }
    }

    public List<Usuario> getUsuarios() {
        List<Usuario> lista = new ArrayList<Usuario>();
        lista = usuarioDao.getUsuarios();
        return lista;
    }

    public boolean login(String login, String senha) {
        boolean acesso = false;
        if (!login.equals("") && !senha.equals("")) {
            acesso = usuarioDao.login(login, senha);
        } else {
            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios!");
        }
        return acesso;
    }

    public void criarUsuarioPadrao() {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        usuarios = getUsuarios();
        if (usuarios.isEmpty()) {
            Usuario novoUsuario = new Usuario();
            novoUsuario.setNome("admin");
            novoUsuario.setEmail("admin@ifms.edu.br");
            novoUsuario.setSenha("admin");

            salvarUsuario(novoUsuario);
        }
    }
    
    public List<UsuarioReport> getListaUsuario() {
        List<Usuario> listaUsuario = new ArrayList<Usuario>();
        List<UsuarioReport> lista = new ArrayList<UsuarioReport>();

        listaUsuario = usuarioDao.getUsuarios();
        lista = construirObjetoUsuarioReports(listaUsuario);

        return lista;
    }
    
     public void gerarRelatorioGeral() throws JRException {
        String CAMINHO_RELATORIO = "C:\\Users\\NathaliaCosim\\Documents\\NetBeansProjects\\ProvaMatisse\\src\\reports\\usuario.jasper";
        List<UsuarioReport> lista = new ArrayList<UsuarioReport>();
        lista = getListaUsuario();
        usuarioDao.gerarRelatorioCollection(lista, CAMINHO_RELATORIO);
    }
    
    public List<UsuarioReport> construirObjetoUsuarioReports(List<Usuario> listaUsuario) {
        List<UsuarioReport> lista = new ArrayList<UsuarioReport>();

        for (int i = 0; i < listaUsuario.size(); i++) {
            UsuarioReport usuarioReport = new UsuarioReport();
            usuarioReport.setId(listaUsuario.get(i).getId());
            usuarioReport.setNome(listaUsuario.get(i).getNome());
            usuarioReport.setEmail(listaUsuario.get(i).getEmail());
            
            lista.add(usuarioReport);
        }
        return lista;
    }
    
    public void gerarRelatorioUsuario(String nomeUsuario) throws JRException, SQLException {
        if (!nomeUsuario.equals("") && !nomeUsuario.equals("--Selecione--")) {
            usuarioDao.getUsuario(nomeUsuario);
        } else {
            JOptionPane.showMessageDialog(null, "Informe um usuário para gerar o relatório!");
        }
    }
}
