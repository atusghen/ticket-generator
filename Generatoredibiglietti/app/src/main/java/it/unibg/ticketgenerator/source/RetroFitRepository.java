package it.unibg.ticketgenerator.source;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Single;
import it.unibg.ticketgenerator.data.AllStackCb;
import it.unibg.ticketgenerator.data.BasicCB;
import it.unibg.ticketgenerator.data.IncrementaCb;
import it.unibg.ticketgenerator.data.JsonCB;
import it.unibg.ticketgenerator.data.LoginCb;
import it.unibg.ticketgenerator.data.SignUpCb;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Singleton
public class RetroFitRepository {

  private static MySpringService mySpringService;
//    private final SpringHttpClient mySpringService;

    @Inject
    public RetroFitRepository() {
    }

    public void setupClient2(String apiUrl) {
        OkHttpClient okHttpClient = (new OkHttpClient())
                .newBuilder()
                .build();
        Retrofit retrofit = (new Retrofit.Builder())
                .client(okHttpClient)
                .baseUrl(apiUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mySpringService = (MySpringService)retrofit
                .create(MySpringService.class);
    }


    public Single<AllStackCb.O> getAllStack(@NotNull AllStackCb cb) {
        return execOPE(AllStackCb.NAME, cb, AllStackCb.O.class);
    }

    public Single<LoginCb.O> login(@NotNull LoginCb cb) {
        return execOPE(LoginCb.NAME, cb, LoginCb.O.class);
    }

    public Single<SignUpCb.O> register( @NotNull SignUpCb cb) {
        return execOPE(SignUpCb.NAME, cb, SignUpCb.O.class);
    }

    public Single<IncrementaCb.O> createTicket(@NotNull IncrementaCb cb) {
        return execOPE(IncrementaCb.NAME, cb, IncrementaCb.O.class);
    }
//
//    public Single<SaveNotaCB.O> saveNota(@NotNull SaveNotaCB cb){
//        return execOPE(SaveNotaCB.OPE_NAME, cb, SaveNotaCB.O.class);
//    }
//
//    public Single<DeleteNotaCB.O> deleteNota(@NotNull DeleteNotaCB cb){
//        return execOPE(DeleteNotaCB.OPE_NAME, cb, DeleteNotaCB.O.class);
//    }
//
//    public Single<AddNotaCB.O> addNota(@NonNull AddNotaCB cb){
//        return execOPE(AddNotaCB.OPE_NAME, cb, AddNotaCB.O.class);
//    }
//
//    public Single<GetFullNoteListCB.O> getFullNoteList(@NotNull GetFullNoteListCB cb) {
//        return execOPE(GetFullNoteListCB.OPE_NAME, cb, GetFullNoteListCB.O.class);
//    }

    public <I, O, B extends BasicCB<I, O>> Single<O> execOPE(String operation, B cbReq, Class<O> cbClass) throws RuntimeException {
        return Single.create((emitter) -> {
            if (mySpringService == null) {
                emitter.onError(new RuntimeException("Cuba backend address unknown, retry client setup"));
            }
//            if (tokens == null) {
//                emitter.onError(this.getNullTokensException());
//            }

            if (cbReq == null) {
                emitter.onError(new RuntimeException("cb cannot be null"));
            }

//          String bearerToken = "Bearer " + tokens.getAccess_token();
            Call<JsonCB> call = mySpringService.execOPE(/*bearerToken,*/ operation, cbReq);
            call.enqueue(new Callback<JsonCB>() {
                public void onResponse(Call<JsonCB> call, Response<JsonCB> response) {
                    if (response.isSuccessful()) {
                        JsonCB cbResp = (JsonCB) response.body();
//                        if (cbResp.getR().getEc().equals(OPEResult.EXIT_CODE.OK.toString())) {
                        Gson gson = (new GsonBuilder()).create();
                        O o = gson.fromJson((JsonElement)cbResp.getO(), cbClass);
                        emitter.onSuccess(o);
//                        } else {
//                            emitter.onError(new RuntimeException(SpringHttpClient.this.serverErrorHandler(cbResp.getR())));
//                        }
                    } else {
                        try {
                            if (response.errorBody() != null) {
                                emitter.onError(new RuntimeException(response.errorBody().string()));
                            } else {
                                emitter.onError(new RuntimeException("HTTP " + response.code() + ": error uploading images"));
                            }
                        } catch (IOException var6) {
                            var6.printStackTrace();
                        }
                    }

                }

                public void onFailure(Call<JsonCB> call, Throwable t) {
                    emitter.onError(t);
                }
            });
        });
    }

}
