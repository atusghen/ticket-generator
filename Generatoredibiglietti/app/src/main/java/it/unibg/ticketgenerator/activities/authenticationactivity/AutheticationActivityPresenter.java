package it.unibg.ticketgenerator.activities.authenticationactivity;

import android.content.Context;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import it.unibg.ticketgenerator.activities.mainactivity.MainActivityContract;
import it.unibg.ticketgenerator.data.AllStackCb;
import it.unibg.ticketgenerator.data.LoginCb;
import it.unibg.ticketgenerator.data.SignUpCb;
import it.unibg.ticketgenerator.repositories.SharedPreferenceRepository;
import it.unibg.ticketgenerator.source.RetroFitRepository;

public class AutheticationActivityPresenter implements AutheticationActivityContract.Presenter{

    @Inject
    RetroFitRepository retroFitRepository;

    @Inject
    SharedPreferenceRepository sharedPreferenceRepository;

    private AutheticationActivityContract.View mView;

    private Context context;

    @Inject
    AutheticationActivityPresenter(AutheticationActivityContract.View mView, @ApplicationContext Context context){
        this.mView = mView;
        this.context = context;
    }


    @Override
    public void start() {

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
                            sharedPreferenceRepository.saveString("token", onSuccess.getJwt());
                            sharedPreferenceRepository.saveString("username", username);
                            mView.stopLoading();
                            mView.startMainActivity();
                        },
                        onError -> {
                            mView.showSnackBar(onError.getMessage());
                            mView.stopLoading();
                        }
                );
    }

    @Override
    public void register(String username, String password, String name, String surname, String cf) {
        SignUpCb cb = new SignUpCb();
        cb.getI().setUsername(username);
        cb.getI().setPassword(password);
        cb.getI().setNome(name);
        cb.getI().setCognome(surname);
        cb.getI().setCf(cf);
        retroFitRepository.register(cb)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        onSuccess -> {
                            mView.stopLoading();
                            login(username, password);
                        },
                        onError -> {
                            Toast.makeText(context, onError.getMessage(), Toast.LENGTH_SHORT).show();
                            mView.stopLoading();
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

