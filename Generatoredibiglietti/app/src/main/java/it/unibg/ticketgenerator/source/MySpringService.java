package it.unibg.ticketgenerator.source;

import it.unibg.ticketgenerator.data.BasicCB;
import it.unibg.ticketgenerator.data.JsonCB;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MySpringService {
    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("{operation}")
    Call<JsonCB> execOPE(@Path("operation") String var2, @Body BasicCB var3);

}
