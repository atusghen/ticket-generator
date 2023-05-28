package it.unibg.ticketgenerator.utils;

public interface BaseView {


    // snackbar
    void showErrorOnScreen(int msgID);

    void showSuccessOnScreen(String s);

    // spinner
    void showWaitingAnimation();

    void removeWaitingAnimation();

}