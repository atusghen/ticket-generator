package it.unibg.ticketgenerator.activities;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import it.unibg.ticketgenerator.data.AllStackCb;
import it.unibg.ticketgenerator.entities.Ticket;
import it.unibg.ticketgenerator.source.RetroFitRepository;

public class MainActivityPresenter implements MainActivityContract.Presenter{

    @Inject
    RetroFitRepository retroFitRepository;

    private MainActivityContract.View mView;

    private Context context;

    @Inject
    MainActivityPresenter(MainActivityContract.View mView, @ApplicationContext Context context){
        this.mView = mView;
        this.context = context;
    }


    @Override
    public void start() {
        AllStackCb cb = new AllStackCb();
        cb.getI().setToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJncmVjbzEiLCJpYXQiOjE2ODUzMDQ4OTUsImV4cCI6MTY4NTM5MTI5NX0.vH3_1I0rBS5zvpDlU5Smel-LiCnWGBWgN95VfYd9sgfY097Xh1vUTuRiQsVO7aI5YEI4mJ3U5VnQMQ-gbD5f_g");
        retroFitRepository.getAllStack(cb)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        onSuccess -> {
                            onSuccess.getOutput().forEach(ticket -> {
                                Toast.makeText(context, ticket.toString2(), Toast.LENGTH_SHORT).show();
                            });
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
