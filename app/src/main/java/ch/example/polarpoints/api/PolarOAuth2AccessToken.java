package ch.example.polarpoints.api;

import com.github.scribejava.core.model.OAuth2AccessToken;

import java.util.Objects;

public class PolarOAuth2AccessToken extends OAuth2AccessToken {

    private static final long serialVersionUID = -1930069873847693076L;

    private final String userId;

    public PolarOAuth2AccessToken(String accessToken, String openIdToken, String rawResponse) {
        this(accessToken, null, null, null, null, openIdToken, rawResponse);
    }

    public PolarOAuth2AccessToken(String accessToken, String tokenType, Integer expiresIn, String refreshToken,
                                   String scope, String userId, String rawResponse) {
        super(accessToken, tokenType, expiresIn, refreshToken, scope, rawResponse);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 37 * hash + Objects.hashCode(userId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }

        return Objects.equals(userId, ((PolarOAuth2AccessToken) obj).getUserId());
    }
}
