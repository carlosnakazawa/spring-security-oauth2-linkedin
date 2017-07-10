package grails.plugin.springsecurity.oauth2.linkedin

import com.github.scribejava.core.builder.api.DefaultApi20

class Linkedin2Api extends DefaultApi20 {

    private static final String AUTHORIZATION_BASE_URL = "https://www.linkedin.com/oauth/v2/authorization"

    protected Linkedin2Api() {
    }

    private static class InstanceHolder {

        private static final Linkedin2Api INSTANCE = new Linkedin2Api();
    }

    public static Linkedin2Api instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    String getAccessTokenEndpoint() {
        return "https://www.linkedin.com/oauth/v2/accessToken";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return AUTHORIZATION_BASE_URL
    }
}
