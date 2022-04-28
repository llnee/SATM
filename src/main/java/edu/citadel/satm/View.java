package edu.citadel.satm;

import javax.swing.*;
import java.awt.*;

public class View {

    private JFrame frame;
    private JButton dispenserButton;
    private JButton DPSlotButton;
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
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);

        dispenserButton = new JButton("  Dispenser  ");
        DPSlotButton = new JButton("DP Slot");
        clearButton = new JButton("  Clear  ");
        enterButton = new JButton("  Enter  ");
        cancelButton = new JButton("  Cancel  ");
        currEntryOp = new JLabel(entryOperations[0]);
        entry = new JTextField("");
        entry.setColumns(10);
        topMsg = new JLabel("          Welcome to Yo Bank!     ");
        btmMsg = new JLabel("     Please insert your ATM card below.          ");

        // transaction, yes/no buttons, not added to frame upon init
        viewBalButton = new JButton("View Balance");
        dpButton = new JButton("Make Deposit");
        wdButton = new JButton("Withdrawal funds");
        yes = new JButton("          Yes");
        no = new JButton("          No");

        FlowLayout layout = new FlowLayout();
        layout.setVgap(20); layout.setHgap(10);
        frame.setLayout(layout);

        frame.add(topMsg);
        frame.add(btmMsg);
        frame.add(new JLabel("                                    "));
        frame.add(currEntryOp);
        frame.add(entry);
        frame.add(new JLabel("                                              "));
        frame.add(new JLabel("                         "));
        frame.add(cancelButton);
        frame.add(clearButton);
        frame.add(enterButton);
        frame.add(new JLabel("                                        "));
        frame.add(dispenserButton);
        frame.add(DPSlotButton);

        frame.setVisible(true);
    }

    // GETTERS
    public JFrame getFrame() { return frame; }

    public JButton getDispenserButton() {
        return dispenserButton;
    }

    public JButton getDPSlotButton() {
        return DPSlotButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JButton getEnterButton() {
        return enterButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JLabel getCurrEntryOp() { return currEntryOp; }

    public JTextField getEntry() {
        return entry;
    }

    public String[] getEntryOperations() { return entryOperations; }

    public JLabel getTopMsg() { return topMsg; }

    public JLabel getBtmMsg() { return btmMsg; }

    public JButton getViewBalButton() {
        return viewBalButton;
    }

    public JButton getDpButton() {
        return dpButton;
    }

    public JButton getWdButton() {
        return wdButton;
    }

    public JButton getYesButton() { return yes; }

    public JButton getNoButton() { return no; }

    // SETTERS
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void setDispenserButton(JButton dispenserButton) {
        this.dispenserButton = dispenserButton;
    }

    public void setDPSlotButton(JButton DPSlotButton) {
        this.DPSlotButton = DPSlotButton;
    }

    public void setClearButton(JButton clearButton) {
        this.clearButton = clearButton;
    }

    public void setEnterButton(JButton enterButton) {
        this.enterButton = enterButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    public void setCurrEntryOp(int i) {
        this.currEntryOp.setText(entryOperations[i]);
    }

    public void setEntry(String entry) { this.entry.setText(entry); }

    public void setTopMsg(String msg) {
        this.topMsg.setText(msg);
    }

    public void setBtmMsg(String msg) {
        this.btmMsg.setText(msg);
    }

    public void setViewBalButton(JButton viewBalButton) {
        this.viewBalButton = viewBalButton;
    }

    public void setDpButton(JButton dpButton) {
        this.dpButton = dpButton;
    }

    public void setWdButton(JButton wdButton) {
        this.wdButton = wdButton;
    }

    public void viewTransactionSelection() {
        JFrame frame = getFrame();
        frame.add(new JLabel("                                                       "));
        frame.add(viewBalButton);
        frame.add(dpButton);
        frame.add(wdButton);
        frame.add(new JLabel("                                                       "));
    }

    public void hideTransactionSelection() {
        JFrame frame = getFrame();
        frame.remove(viewBalButton);
        frame.remove(dpButton);
        frame.remove(wdButton);
    }

    public void viewYesNo() {
        JFrame frame = getFrame();
        frame.add(yes);
        frame.add(no);
    }

    public void hideYesNo() {
        JFrame frame = getFrame();
        frame.remove(yes);
        frame.remove(no);
    }

    public void clearView() {
        frame.remove(dispenserButton);
        frame.remove(DPSlotButton);
        frame.remove(clearButton);
        frame.remove(enterButton);
        frame.remove(cancelButton);
        frame.remove(currEntryOp);
        frame.remove(entry);
    }

}
