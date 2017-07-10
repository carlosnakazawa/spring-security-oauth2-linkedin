package grails.plugin.springsecurity.oauth2.linkedin

import com.github.scribejava.core.builder.api.DefaultApi20
import com.github.scribejava.core.model.OAuth2AccessToken
import grails.converters.JSON
import grails.plugin.springsecurity.oauth2.exception.OAuth2Exception
import grails.plugin.springsecurity.oauth2.service.OAuth2AbstractProviderService
import grails.plugin.springsecurity.oauth2.token.OAuth2SpringToken
import grails.transaction.Transactional

@Transactional
class LinkedinOAuth2ProviderService extends OAuth2AbstractProviderService {

    @Override
    String getProviderID() {
        return 'linkedin'
    }

    @Override
    Class<? extends DefaultApi20> getApiClass() {
        Instagram2Api.class
    }

    @Override
    String getProfileScope() {
        return "https://api.instagram.com/v1/users/self"
    }

    @Override
    String getScopes() {
        return "basic"
    }

    @Override
    String getScopeSeparator() {
        return "+"
    }

    @Override
    OAuth2SpringToken createSpringAuthToken(OAuth2AccessToken accessToken) {
        def user
        def response = getResponse(accessToken)
        try {
            println "JSON response body: " + accessToken.rawResponse
            user = JSON.parse(response.body)?.data
            println "Usuário: $user"
            println "Response: $response.body"
        } catch (Exception exception) {
            log.error("Error parsing response from " + getProviderID() + ". Response:\n" + response.body)
            throw new OAuth2Exception("Error parsing response from " + getProviderID(), exception)
        }
        if (!user?.username) {
            log.error("No user email from " + getProviderID() + ". Response was:\n" + response.body)
            throw new OAuth2Exception("No user email from " + getProviderID())
        }
        new InstagramOauth2SpringToken(accessToken, user?.username, providerID)
    }
}
