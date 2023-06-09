package it.unibg.ticketgenerator.activities.mainactivity;

import it.unibg.ticketgenerator.entities.Ticket;
import it.unibg.ticketgenerator.utils.BasePresenter;

public interface MainActivityContract {
    interface View //extends BaseView
    {

        void startAuthenticationActivity();

        void showTicket(Ticket onSuccess);
    }

    interface Presenter extends BasePresenter {

        void login(String username, String password);

        void logout();

        void createTicket(String token);

        void getTicket(String ticket, long number);
    }
}
