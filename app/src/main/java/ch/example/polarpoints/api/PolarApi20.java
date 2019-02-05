package ch.example.polarpoints.api;

import com.github.scribejava.core.builder.api.DefaultApi20;

// documentation at: https://www.polar.com/accesslink-api/?java#authentication
public class PolarApi20 extends DefaultApi20 {

    protected PolarApi20() {
    }

    private static class InstanceHolder {
        private static final PolarApi20 INSTANCE = new PolarApi20();
    }

    public static PolarApi20 instance() {
        return PolarApi20.InstanceHolder.INSTANCE;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://polarremote.com/v2/oauth2/token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return "https://flow.polar.com/oauth2/authorization";
    }

    @Override
    public PolarJsonTokenExtractor getAccessTokenExtractor() {
        return PolarJsonTokenExtractor.instance();
    }
}
