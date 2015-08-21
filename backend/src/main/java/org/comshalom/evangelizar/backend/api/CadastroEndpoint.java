package org.comshalom.evangelizar.backend.api;

import com.google.api.server.spi.ServiceException;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import org.comshalom.evangelizar.backend.Constants;
import org.comshalom.evangelizar.backend.model.CadastroVO;

import java.util.UUID;
import java.util.logging.Logger;

import static org.comshalom.evangelizar.backend.OfyService.ofy;

/**
 * Exposes REST API over Recommendation resources.
 */
@Api(name = "cadastroApi", version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = Constants.API_OWNER,
                ownerName = Constants.API_OWNER,
                packagePath = Constants.API_PACKAGE_PATH
        )
)
public class CadastroEndpoint {

    /**
     * Log output.
     */
    private static final Logger LOG = Logger
            .getLogger(CadastroEndpoint.class.getName());

    /**
     * Inserts the entity into App Engine datastore. It uses HTTP POST method.
     * @param cadastro the entity to be inserted.
     * @return The inserted entity.
     * @throws ServiceException if user is not
     * authorized
     */
    @ApiMethod(httpMethod = "POST")
    public final CadastroVO inserirCadastro(final CadastroVO cadastro) {

        cadastro.setIdGoogle(UUID.randomUUID().toString());
        ofy().save().entity(cadastro).now();

        return cadastro;
    }
}
