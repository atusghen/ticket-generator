package it.unibg.ticketgenerator.activities.mainactivity;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import it.unibg.ticketgenerator.data.AllStackCb;
import it.unibg.ticketgenerator.data.IncrementaCb;
import it.unibg.ticketgenerator.data.LoginCb;
import it.unibg.ticketgenerator.repositories.SharedPreferenceRepository;
import it.unibg.ticketgenerator.source.RetroFitRepository;

public class MainActivityPresenter implements MainActivityContract.Presenter{

    @Inject
    RetroFitRepository retroFitRepository;

    @Inject
    SharedPreferenceRepository sharedPreferenceRepository;

    private MainActivityContract.View mView;

    private Context context;

    @Inject
    MainActivityPresenter(MainActivityContract.View mView, @ApplicationContext Context context){
        this.mView = mView;
        this.context = context;
    }


    @Override
    public void start() {

        if (sharedPreferenceRepository.getString("token").equals("")){

            mView.startAuthenticationActivity();

        }
    }

    @Override
    public void login(String username, String password) {
        LoginCb cb = new LoginCb();
        cb.getI().setUsername(username);
        cb.getI().setPassword(password);
        retroFitRepository.login(cb)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        onSuccess -> {
                            Toast.makeText(context, "DIo cane", Toast.LENGTH_SHORT).show();
                            sharedPreferenceRepository.saveString("token", onSuccess.getJwt());
                        },
                        onError -> {
                            Toast.makeText(context, onError.getMessage(), Toast.LENGTH_SHORT).show();
                            onError.printStackTrace();
                        }
                );
    }

    @Override
    public void logout() {
        sharedPreferenceRepository.saveString("token", "");
        mView.startAuthenticationActivity();
    }

    @Override
    public void createTicket(String token) {
        IncrementaCb cb = new IncrementaCb();
        cb.getI().setToken(token);
        cb.getI().setPriority("X");
        retroFitRepository.createTicket(cb)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        onSuccess -> {
                            mView.showTicket(onSuccess.getBiglietto());
                        },
                        onError -> {
                            Toast.makeText(context, onError.getMessage(), Toast.LENGTH_SHORT).show();
                            onError.printStackTrace();
                        }
                );
    }


    @Override
    public void stop() {

    }

    @Override
    public void onDestroyView() {

    }
}
