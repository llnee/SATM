package edu.citadel.satm;

import javax.swing.*;
import java.awt.*;

public class View {
    private JFrame frame;

    // panels
    private JPanel topbtm_msgPanel;
    private JPanel entryPanel;
    private JPanel yesnoPanel;
    private JPanel view_dp_wdPanel;
    private JPanel cnl_clr_entPanel;
    private JPanel dispPanel;

    // card control
    private CardLayout cardlayout;
    private JPanel cardPanel;
    private int cardno;

    // components
    private JLabel filler = new JLabel("                                                       ");
    private JButton dispenserButton;
    private JButton clearButton;
    private JButton enterButton;
    private JButton cancelButton;
    private String[] entryOperations = {"Enter PAN:", "Enter PIN:", "Enter withdrawal amount:", "Enter deposit amount:"};
    private JLabel currEntryOp;
    private JTextField entry;
    private JLabel topMsg;
    private JLabel btmMsg;
    private JButton viewBalButton;
    private JButton dpButton;
    private JButton wdButton;
    private JButton yes;
    private JButton no;

    public View(String title) {

        frame = new JFrame(title);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(375, 450);
        frame.setLocationRelativeTo(null);

        // create starter components
        dispenserButton = new JButton("  Dispenser  ");
        clearButton = new JButton("  Clear  ");
        enterButton = new JButton("  Enter  ");
        cancelButton = new JButton("  Cancel  ");
        currEntryOp = new JLabel(entryOperations[0]);
        entry = new JTextField("");
        entry.setColumns(10);
        topMsg = new JLabel("          Welcome to Yo Bank!     ");
//        btmMsg = new JLabel("     Please insert your ATM card below.          ");
        btmMsg = new JLabel("");

        // transaction, yes/no buttons, not added to frame upon init, not starters
        viewBalButton = new JButton("View Balance");
        dpButton = new JButton("Make Deposit");
        wdButton = new JButton("Withdrawal funds");
        yes = new JButton("          Yes");
        no = new JButton("          No");

        // panels
        topbtm_msgPanel = new JPanel();
        topbtm_msgPanel.add(topMsg); topbtm_msgPanel.add(btmMsg);
        entryPanel = new JPanel();
        entryPanel.add(currEntryOp); entryPanel.add(entry);
        yesnoPanel = new JPanel();
        yesnoPanel.add(yes); yesnoPanel.add(no);
        view_dp_wdPanel = new JPanel();
        view_dp_wdPanel.add(viewBalButton); view_dp_wdPanel.add(dpButton); view_dp_wdPanel.add(wdButton);
        cnl_clr_entPanel = new JPanel();
        cnl_clr_entPanel.add(cancelButton); cnl_clr_entPanel.add(clearButton); cnl_clr_entPanel.add(enterButton);
        dispPanel = new JPanel();
        dispPanel.add(dispenserButton);

        // card control
        cardlayout = new CardLayout(10, 10);
        cardPanel = new JPanel();
        cardPanel.setLayout(cardlayout);
        cardno = 1;
        cardPanel.add(entryPanel, "entry");
        cardPanel.add(yesnoPanel, "yesno");
        cardPanel.add(view_dp_wdPanel, "view_dp_wd");

        // add starter panels {top_btm, can_clr, d} and cardPanel
        frame.add(topbtm_msgPanel);
        frame.add(cardPanel);
        frame.add(cnl_clr_entPanel);
        frame.add(dispPanel);

        frame.setVisible(true);
    }

    // GETTERS
    public JFrame getFrame() { return frame; }
    public JButton getDispenserButton() { return dispenserButton; }
    public JButton getClearButton() { return clearButton; }
    public JButton getEnterButton() { return enterButton; }
    public JButton getCancelButton() { return cancelButton; }
    public JLabel getCurrEntryOp() { return currEntryOp; }
    public JTextField getEntry() { return entry; }
    public String[] getEntryOperations() { return entryOperations; }
    public JLabel getTopMsg() { return topMsg; }
    public JLabel getBtmMsg() { return btmMsg; }
    public JButton getViewBalButton() { return viewBalButton; }
    public JButton getDpButton() { return dpButton; }
    public JButton getWdButton() { return wdButton; }
    public JButton getYesButton() { return yes; }
    public JButton getNoButton() { return no; }
    public JPanel getTopbtm_msgPanel() { return topbtm_msgPanel; }
    public JPanel getEntryPanel() { return entryPanel; }
    public JPanel getYesnoPanel() { return yesnoPanel; }
    public JPanel getView_dp_wdPanel() { return view_dp_wdPanel; }
    public JPanel getCnl_clr_entPanel() { return cnl_clr_entPanel; }
    public JPanel getDispPanel() { return dispPanel; }
    public CardLayout getCardlayout() { return cardlayout; }
    public JPanel getCardPanel() { return cardPanel; }
    public int getCardno() { return cardno; }

    // SETTERS
    public void setFrame(JFrame frame) { this.frame = frame; }
    public void setDispenserButton(JButton dispenserButton) { this.dispenserButton = dispenserButton; }
    public void setClearButton(JButton clearButton) { this.clearButton = clearButton; }
    public void setEnterButton(JButton enterButton) { this.enterButton = enterButton; }
    public void setCancelButton(JButton cancelButton) { this.cancelButton = cancelButton; }
    public void setCurrEntryOp(int i) { this.currEntryOp.setText(entryOperations[i]); }
    public void setEntry(String entry) { this.entry.setText(entry); }
    public void setTopMsg(String msg) { this.topMsg.setText(msg); }
    public void setBtmMsg(String msg) { this.btmMsg.setText(msg); }
    public void setViewBalButton(JButton viewBalButton) { this.viewBalButton = viewBalButton; }
    public void setDpButton(JButton dpButton) { this.dpButton = dpButton; }
    public void setWdButton(JButton wdButton) { this.wdButton = wdButton; }
    public void setCardPanel(JPanel cardPanel) { this.cardPanel = cardPanel; }
    public void setCardno(int cardno) { this.cardno = cardno; }

    public void viewTransactionSelection() {
        cardlayout.show(cardPanel, "view_dp_wd");
    }

    public void hideTransactionSelection() {
        JFrame frame = getFrame();
        frame.remove(view_dp_wdPanel);
    }

    public void viewEntry() {
        JFrame frame = getFrame();
        cardlayout.show(cardPanel, "entry");
    }

    public void hideEntry() {
        JFrame frame = getFrame();
        frame.remove(entryPanel);
    }

    public void viewYesNo() {
        JFrame frame = getFrame();
        cardlayout.show(cardPanel, "yesno");
    }

    public void hideYesNo() {
        JFrame frame = getFrame();
        frame.remove(yesnoPanel);
    }

    public void clearView() {
        frame.remove(cardPanel);
        frame.remove(cnl_clr_entPanel);
        for (int i = 0; i < 10; i++) { frame.add(new JPanel()); }
    }

}
