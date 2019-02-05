package ch.example.polarpoints.api;

import com.github.scribejava.core.extractors.OAuth2AccessTokenJsonExtractor;
import com.github.scribejava.core.model.OAuth2AccessTokenErrorResponse;

import java.util.regex.Pattern;

public class PolarJsonTokenExtractor extends OAuth2AccessTokenJsonExtractor {

    private static final Pattern USER_ID_REGEX_PATTERN = Pattern.compile("\"x_user_id\"\\s*:\\s*\"(\\S*?)\"");
    private static final Pattern ERROR_REGEX_PATTERN = Pattern.compile("\"errorType\"\\s*:\\s*\"(\\S*?)\"");
    private static final Pattern ERROR_DESCRIPTION_REGEX_PATTERN = Pattern.compile("\"message\"\\s*:\\s*\"([^\"]*?)\"");

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

    @Override
    public void generateError(String response) {
        final String errorInString = extractParameter(response, ERROR_REGEX_PATTERN, true);
        final String errorDescription = extractParameter(response, ERROR_DESCRIPTION_REGEX_PATTERN, false);

        OAuth2AccessTokenErrorResponse.ErrorCode errorCode;
        try {
            errorCode = OAuth2AccessTokenErrorResponse.ErrorCode.valueOf(errorInString);
        } catch (IllegalArgumentException iaE) {
            //non oauth standard error code
            errorCode = null;
        }

        throw new OAuth2AccessTokenErrorResponse(errorCode, errorDescription, null, response);
    }
}
