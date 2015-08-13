/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarefateste;

import entities.Tarefa;
import java.awt.Color;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import model.TarefaModel;

/**
 *
 * @author timi
 */
public class JInternalFrameTarefaExecucao extends javax.swing.JInternalFrame {

    private final TarefaModel tarefaModel = new TarefaModel();
    private JFramePrincipal framePrincipal;
    private Tarefa tarefaExecucao = null;

    /**
     * Creates new form JInternalFrameTarefa
     */
    public JInternalFrameTarefaExecucao(JFramePrincipal framePrincipal) {
        try {
            initComponents();
            BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
            bi.setNorthPane(null);
            this.framePrincipal = framePrincipal;
            this.jButtonSetStatus.setVisible(false);
            this.jButtonFinalizar.setVisible(false);
            preencheTabela();
            this.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }
    

    private void preencheTabela() {
        try {

            DefaultTableModel dtm = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            dtm.addColumn("#");
            dtm.addColumn("Título");
            dtm.addColumn("Criada por");
            dtm.addColumn("Esforco estimado");
            dtm.addColumn("Status");
            dtm.addColumn("Data criação");
            String where = " id_usuario_executor = " + this.framePrincipal.usuarioLogado.getId();
            where += "and status <> '" +Tarefa.FINALIZADA+"' order by created desc";
            for (Tarefa tarefa : this.tarefaModel.findAllWhere(where)) {
                dtm.addRow(new Object[]{tarefa.getId(),
                    tarefa.getTitulo(),
                    tarefa.getUsuarioByIdUsuarioCriador().getNome(),
                    ((tarefa.getEsforcoEstimado() > 1) ? (tarefa.getEsforcoEstimado() + " horas") : (tarefa.getEsforcoEstimado() + " hora")),
                    tarefa.getStatus(),
                    new SimpleDateFormat("dd/MM/yyyy").format(tarefa.getCreated()),});
            }
            this.jTableTarefa.setModel(dtm);
            this.jTableTarefa.getColumnModel().getColumn(0).setPreferredWidth(10);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void atualiza() {
        preencheTabela();
        preencheTarefa();
    }
    
    private void preencheTarefa() {
        try {
            if (this.tarefaExecucao instanceof Tarefa) {
                this.jTextFieldTitulo.setText(this.tarefaExecucao.getTitulo());
                this.jTextFieldCriador.setText(this.tarefaExecucao.getUsuarioByIdUsuarioCriador().getNome());
                int intEsforco = this.tarefaExecucao.getEsforcoEstimado();
                this.jTextFieldEsforco.setText((intEsforco > 1) ? (intEsforco + " horas") : (intEsforco + " hora"));
                this.jTextFieldData.setText(new SimpleDateFormat("dd/MM/yyyy").format(this.tarefaExecucao.getCreated()));
                this.jTextAreaDescricao.setText(this.tarefaExecucao.getDescricao());
                String sts = this.tarefaExecucao.getStatus();
                this.jTextFieldStatus.setText(sts);
                if (sts.equals(Tarefa.EXECUTANDO)) {
                    this.jTextFieldStatus.setBackground(new Color(153, 255, 153));
                    this.jButtonSetStatus.setText("Parar execução");
                } else if (sts.equals(Tarefa.PARADA)) {
                    this.jTextFieldStatus.setBackground(new Color(255, 153, 153));
                    this.jButtonSetStatus.setText("Iniciar execução");
                }
                this.jButtonSetStatus.setVisible(true);
                this.jButtonFinalizar.setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void limpaTarefa() {
        this.tarefaExecucao = null;
        this.jTextFieldTitulo.setText("");
        this.jTextFieldCriador.setText("");
        this.jTextFieldEsforco.setText("");
        this.jTextFieldData.setText("");
        this.jTextAreaDescricao.setText("");
        this.jTextFieldStatus.setText("");
        this.jTextFieldStatus.setBackground(Color.WHITE);
        this.jButtonSetStatus.setVisible(false);
        this.jButtonFinalizar.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTarefa = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldTitulo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaDescricao = new javax.swing.JTextArea();
        jTextFieldCriador = new javax.swing.JTextField();
        jTextFieldEsforco = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldData = new javax.swing.JTextField();
        jTextFieldStatus = new javax.swing.JTextField();
        jButtonSetStatus = new javax.swing.JButton();
        jButtonFinalizar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        jLabel5.setText("Descrição");

        setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Minhas tarefas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        setTitle("Minhas Tarefas");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        jTableTarefa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableTarefa.setName(""); // NOI18N
        jTableTarefa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTarefaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableTarefa);

        jLabel2.setText("*Duplo clique na lista para selecionar uma tarefa para execução");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Execução de tarefa"));

        jLabel1.setText("Título");

        jTextFieldTitulo.setEditable(false);

        jLabel3.setText("Criada por");

        jLabel4.setText("Esforço estimado");

        jLabel6.setText("Descrição");

        jTextAreaDescricao.setEditable(false);
        jTextAreaDescricao.setColumns(20);
        jTextAreaDescricao.setRows(5);
        jScrollPane3.setViewportView(jTextAreaDescricao);

        jTextFieldCriador.setEditable(false);

        jTextFieldEsforco.setEditable(false);

        jLabel7.setText("Data criação");

        jTextFieldData.setEditable(false);

        jTextFieldStatus.setEditable(false);
        jTextFieldStatus.setBackground(new java.awt.Color(255, 255, 255));

        jButtonSetStatus.setText("Parar execução");
        jButtonSetStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSetStatusActionPerformed(evt);
            }
        });

        jButtonFinalizar.setText("Fiinalizar tarefa");
        jButtonFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinalizarActionPerformed(evt);
            }
        });

        jLabel8.setText("Situação");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)))
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldTitulo)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldData)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldEsforco, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldCriador)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSetStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonFinalizar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldCriador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldEsforco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSetStatus)
                    .addComponent(jButtonFinalizar)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Minhas tarefas");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableTarefaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTarefaMouseClicked
        try {
            if (evt.getClickCount() == 2) {
                int indice = this.jTableTarefa.getSelectedRow();
                String id = this.jTableTarefa.getValueAt(indice, 0).toString();
                this.tarefaExecucao = this.tarefaModel.find(Integer.parseInt(id));
                preencheTarefa();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
    }//GEN-LAST:event_jTableTarefaMouseClicked

    private void jButtonSetStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSetStatusActionPerformed
        if (this.tarefaExecucao instanceof Tarefa) {

            switch (this.tarefaExecucao.getStatus()) {
                case Tarefa.EXECUTANDO:
                    this.tarefaExecucao.setStatus(Tarefa.PARADA);
                    break;
                case Tarefa.PARADA:
                    this.tarefaExecucao.setStatus(Tarefa.EXECUTANDO);
                    break;
            }
            this.tarefaModel.update(this.tarefaExecucao);
            preencheTabela();
            preencheTarefa();

        }
    }//GEN-LAST:event_jButtonSetStatusActionPerformed

    private void jButtonFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinalizarActionPerformed
        if (this.tarefaExecucao instanceof Tarefa) {
            int resposta = JOptionPane.showInternalConfirmDialog(this, "Confirma a finalização dessa tarefa?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                if (this.tarefaExecucao.getStatus().equals(Tarefa.EXECUTANDO)) {
                    this.tarefaExecucao.setStatus(Tarefa.PARADA);
                    this.tarefaModel.update(this.tarefaExecucao);
                }
                this.tarefaExecucao.setStatus(Tarefa.FINALIZADA);
                this.tarefaModel.update(this.tarefaExecucao);
                preencheTabela();
                limpaTarefa();
            }
        }
    }//GEN-LAST:event_jButtonFinalizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonFinalizar;
    private javax.swing.JButton jButtonSetStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableTarefa;
    private javax.swing.JTextArea jTextAreaDescricao;
    private javax.swing.JTextField jTextFieldCriador;
    private javax.swing.JTextField jTextFieldData;
    private javax.swing.JTextField jTextFieldEsforco;
    private javax.swing.JTextField jTextFieldStatus;
    private javax.swing.JTextField jTextFieldTitulo;
    // End of variables declaration//GEN-END:variables
}
