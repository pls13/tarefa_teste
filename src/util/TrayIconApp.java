/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package util;
/*
 * TrayIconApp.java
 */

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;
import tarefateste.JFramePrincipal;

public class TrayIconApp {
    

    public  void createAndShowGUI(JFramePrincipal framePrincipal) {
        //Check the SystemTray support
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon = new TrayIcon(createImage("/resources/images/icon.png", "tray icon"));
        final SystemTray tray = SystemTray.getSystemTray();

        // Create a popup menu components
        MenuItem minhasTarefas = new MenuItem("Minhas tarefas");
        MenuItem cadastroTarefas = new MenuItem("Cadastro de tarefas");
        MenuItem cadastroUsuarios = new MenuItem("Cadastro de usuários");
        MenuItem trocarUsuario = new MenuItem("Trocar usuário");
        MenuItem sair = new MenuItem("Sair");

        //Add components to popup menu
        popup.add(minhasTarefas);
        popup.addSeparator();
        popup.add(cadastroTarefas);
        popup.add(cadastroUsuarios);
        popup.addSeparator();
        popup.add(trocarUsuario);
        popup.add(sair);

        trayIcon.setPopupMenu(popup);
        trayIcon.setToolTip("Sistema de tarefas");
        trayIcon.setImageAutoSize(true);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }

        
        minhasTarefas.addActionListener(new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent e) {
                framePrincipal.exibeMinhasTarefas();
                framePrincipal.setVisible(true); 
                framePrincipal.setExtendedState(JFrame.NORMAL);
            }
            
        });
        cadastroTarefas.addActionListener(new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent e) {
                framePrincipal.exibeCadastroTarefa();
                framePrincipal.setVisible(true); 
                framePrincipal.setExtendedState(JFrame.NORMAL);
            }
        });
        cadastroUsuarios.addActionListener(new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent e) {
                framePrincipal.exibeCadastroUsuario();
                framePrincipal.setVisible(true); 
                framePrincipal.setExtendedState(JFrame.NORMAL);
            }
        });
        trocarUsuario.addActionListener(new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent e) {
                framePrincipal.trocarUsuario();
            }
        });

        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });
    }

    //Obtain the image URL
    public static Image createImage(String path, String description) {
        URL imageURL = TrayIconApp.class.getResource(path);
        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }
}
