package view;

import controller.AlunoController;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Aluno;
import net.sf.jasperreports.engine.JRException;

public class FrmRelatorioAluno extends javax.swing.JInternalFrame {
    AlunoController ac = new AlunoController();
    List<Aluno> aluno = new ArrayList<Aluno>();

    public FrmRelatorioAluno() {
        initComponents();
        aluno = ac.getAlunos();
        if (aluno.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ainda não foram cadastrados alunos");
        } else {
            preencheAlunos();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblNomeUsuario = new javax.swing.JLabel();
        cbxAlunos = new javax.swing.JComboBox<>();
        btnGerarRelatorio = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTitulo.setText("Relatório de Aluno");

        lblNomeUsuario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblNomeUsuario.setText("Nome do aluno:");

        btnGerarRelatorio.setText("Gerar Relatório");
        btnGerarRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarRelatorioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(lblTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNomeUsuario)
                            .addComponent(cbxAlunos, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGerarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addGap(38, 38, 38)
                .addComponent(lblNomeUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxAlunos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnGerarRelatorio)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGerarRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarRelatorioActionPerformed
        try {
            String nomeAluno = cbxAlunos.getSelectedItem().toString();
            ac.gerarRelatorioAluno(nomeAluno);
        } catch (JRException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + erro);
        } catch (SQLException ex) {
            Logger.getLogger(FrmDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGerarRelatorioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGerarRelatorio;
    private javax.swing.JComboBox<String> cbxAlunos;
    private javax.swing.JLabel lblNomeUsuario;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables

    public void preencheAlunos() {
        cbxAlunos.addItem("--Selecione--");
        for (int i = 0; i < aluno.size(); i++) {
            cbxAlunos.addItem(aluno.get(i).getNome());
        }
    }
    
    public void centralizarComponente() { 
        Dimension ds = this.getDesktopPane().getSize();
        setLocation((ds.width - this.getSize().width) / 2, (ds.height - this.getSize().height) / 5); 
    }
}
