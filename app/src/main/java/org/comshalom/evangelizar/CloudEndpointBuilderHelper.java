package org.comshalom.evangelizar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;

import org.comshalom.evangelizar.backend.cadastroApi.CadastroApi;

/**
 * Allows configuring Cloud Endpoint builders to support authenticated calls, as
 * well as calls to
 * CLoud Endpoints exposed from an App Engine backend that run locally during
 * development.
 */
final class CloudEndpointBuilderHelper {


    /**
     * Default constructor, never called.
     */
    private CloudEndpointBuilderHelper() {
    }

    /**
     * *
     *
     * @return ShoppingAssistant endpoints to the GAE backend.
     */
    static CadastroApi getEndpoints() {

        // Create API handler
        CadastroApi.Builder builder = new CadastroApi.Builder(
                AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), getRequestInitializer())
                .setRootUrl(Constants.ROOT_URL);

        return builder.build();
    }

    /**
     * Returns appropriate HttpRequestInitializer depending whether the
     * application is configured to require users to be signed in or not.
     *
     * @return an appropriate HttpRequestInitializer.
     */
    static HttpRequestInitializer getRequestInitializer() {
        return new HttpRequestInitializer() {
            @Override
            public void initialize(final HttpRequest arg0) {
            }
        };
    }
}
