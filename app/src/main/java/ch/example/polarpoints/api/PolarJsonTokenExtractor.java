package ch.example.polarpoints.api;

import com.github.scribejava.core.extractors.OAuth2AccessTokenJsonExtractor;

import java.util.regex.Pattern;

public class PolarJsonTokenExtractor extends OAuth2AccessTokenJsonExtractor {

    private static final Pattern USER_ID_REGEX_PATTERN = Pattern.compile("\"x_user_id\"\\s*:(\\d*)");

    protected PolarJsonTokenExtractor() {
    }

    private static class InstanceHolder {

        private static final PolarJsonTokenExtractor INSTANCE = new PolarJsonTokenExtractor();
    }

    public static PolarJsonTokenExtractor instance() {
        return PolarJsonTokenExtractor.InstanceHolder.INSTANCE;
    }

    @Override
    protected PolarOAuth2AccessToken createToken(String accessToken, String tokenType, Integer expiresIn,
                                                  String refreshToken, String scope, String response) {
        return new PolarOAuth2AccessToken(accessToken, tokenType, expiresIn, refreshToken, scope,
                extractParameter(response, USER_ID_REGEX_PATTERN, false), response);
    }

}
