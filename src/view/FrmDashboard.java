/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.AlunoController;
import controller.AulaController;
import controller.MatriculaController;
import controller.UsuarioController;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author NathaliaCosim
 */
public class FrmDashboard extends javax.swing.JFrame {
    AlunoController ac = new AlunoController();
    AulaController tc = new AulaController();
    MatriculaController mc = new MatriculaController();
    UsuarioController uc = new UsuarioController();
    
    public FrmDashboard() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        desktop = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuCadAluno = new javax.swing.JMenuItem();
        menuCadMatric = new javax.swing.JMenuItem();
        menuCadTurma = new javax.swing.JMenuItem();
        jMenuCadUsuario = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuListaAluno = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        menuItemRelatorioAlunos = new javax.swing.JMenuItem();
        menuItemRelatorioTurmas = new javax.swing.JMenuItem();
        menuItemRelatorioMatriculas = new javax.swing.JMenuItem();
        menuItemRelatorioAl = new javax.swing.JMenuItem();
        jSair = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        jMenu1.setText("Cadastro");

        menuCadAluno.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        menuCadAluno.setText("Aluno");
        menuCadAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadAlunoActionPerformed(evt);
            }
        });
        jMenu1.add(menuCadAluno);

        menuCadMatric.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        menuCadMatric.setText("Matricula");
        menuCadMatric.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadMatricActionPerformed(evt);
            }
        });
        jMenu1.add(menuCadMatric);

        menuCadTurma.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        menuCadTurma.setText("Turma");
        menuCadTurma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadTurmaActionPerformed(evt);
            }
        });
        jMenu1.add(menuCadTurma);

        jMenuCadUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jMenuCadUsuario.setText("Usuário");
        jMenuCadUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuCadUsuarioActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuCadUsuario);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Listagem");

        menuListaAluno.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        menuListaAluno.setText("Alunos");
        menuListaAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuListaAlunoActionPerformed(evt);
            }
        });
        jMenu3.add(menuListaAluno);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Relatórios");

        menuItemRelatorioAlunos.setText("Relatório de Alunos");
        menuItemRelatorioAlunos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRelatorioAlunosActionPerformed(evt);
            }
        });
        jMenu4.add(menuItemRelatorioAlunos);

        menuItemRelatorioTurmas.setText("Relatório de Turmas");
        menuItemRelatorioTurmas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRelatorioTurmasActionPerformed(evt);
            }
        });
        jMenu4.add(menuItemRelatorioTurmas);

        menuItemRelatorioMatriculas.setText("Relatório de Matrículas");
        menuItemRelatorioMatriculas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRelatorioMatriculasActionPerformed(evt);
            }
        });
        jMenu4.add(menuItemRelatorioMatriculas);

        menuItemRelatorioAl.setText("Relatório Aluno (Single)");
        menuItemRelatorioAl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRelatorioAlActionPerformed(evt);
            }
        });
        jMenu4.add(menuItemRelatorioAl);

        jMenuBar1.add(jMenu4);

        jSair.setText("Sair");
        jSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSairMouseClicked(evt);
            }
        });
        jMenuBar1.add(jSair);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuCadAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadAlunoActionPerformed
        FrmCadastroAluno a = new FrmCadastroAluno();
        desktop.add(a);
        a.setVisible(true);
        a.centralizarComponente();
    }//GEN-LAST:event_menuCadAlunoActionPerformed

    private void jMenuCadUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuCadUsuarioActionPerformed
        FrmCadastroUsuario a = new FrmCadastroUsuario();
        desktop.add(a);
        a.setVisible(true);
        a.centralizarComponente();
    }//GEN-LAST:event_jMenuCadUsuarioActionPerformed

    private void jSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSairMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jSairMouseClicked

    private void menuCadTurmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadTurmaActionPerformed
        FrmCadastroAula a = new FrmCadastroAula();
        desktop.add(a);
        a.setVisible(true);
        a.centralizarComponente();
    }//GEN-LAST:event_menuCadTurmaActionPerformed

    private void menuCadMatricActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadMatricActionPerformed
        FrmCadastroMatricula a = new FrmCadastroMatricula();
        desktop.add(a);
        a.setVisible(true);
        a.centralizarComponente();
    }//GEN-LAST:event_menuCadMatricActionPerformed

    private void menuItemRelatorioAlunosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRelatorioAlunosActionPerformed
        try {
            ac.gerarRelatorioGeral();
        } catch (JRException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + erro);
        }
    }//GEN-LAST:event_menuItemRelatorioAlunosActionPerformed

    private void menuItemRelatorioTurmasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRelatorioTurmasActionPerformed
        try {
            tc.gerarRelatorioGeral();
        } catch (JRException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + erro);
        }
    }//GEN-LAST:event_menuItemRelatorioTurmasActionPerformed

    private void menuItemRelatorioMatriculasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRelatorioMatriculasActionPerformed
        try {
            mc.gerarRelatorioGeral();
        } catch (JRException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + erro);
        }
    }//GEN-LAST:event_menuItemRelatorioMatriculasActionPerformed

    private void menuItemRelatorioAlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRelatorioAlActionPerformed
        FrmRelatorioAluno ra = new FrmRelatorioAluno();
        desktop.add(ra);
        ra.setVisible(true);
        ra.centralizarComponente();
    }//GEN-LAST:event_menuItemRelatorioAlActionPerformed

    private void menuListaAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuListaAlunoActionPerformed
        FrmListaAluno a = new FrmListaAluno();
        desktop.add(a);
        a.setVisible(true);
        a.centralizarComponente();
    }//GEN-LAST:event_menuListaAlunoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuCadUsuario;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenu jSair;
    private javax.swing.JMenuItem menuCadAluno;
    private javax.swing.JMenuItem menuCadMatric;
    private javax.swing.JMenuItem menuCadTurma;
    private javax.swing.JMenuItem menuItemRelatorioAl;
    private javax.swing.JMenuItem menuItemRelatorioAlunos;
    private javax.swing.JMenuItem menuItemRelatorioMatriculas;
    private javax.swing.JMenuItem menuItemRelatorioTurmas;
    private javax.swing.JMenuItem menuListaAluno;
    // End of variables declaration//GEN-END:variables
}
