package it.unibg.ticketgenerator.activities.mainactivity;

import it.unibg.ticketgenerator.utils.BasePresenter;
import it.unibg.ticketgenerator.utils.BaseView;

public interface MainActivityContract {
    interface View //extends BaseView
    {

        void startAuthenticationActivity();
    }

    interface Presenter extends BasePresenter {

        void login(String username, String password);

        void logout();
    }
}
