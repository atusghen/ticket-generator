package it.unibg.ticketgenerator.activities.authenticationactivity;


import it.unibg.ticketgenerator.utils.BasePresenter;

public interface AutheticationActivityContract {
    interface View {
        void startMainActivity();

        void setLoginData(String username, String password);

        void stopLoading();

        void showSnackBar(String message);
    }

    interface Presenter extends BasePresenter {

        void login(String username, String password);

        void register(String username, String password, String name, String surname, String cf);
    }
}
