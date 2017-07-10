/**
 * Created by carlo on 03/07/2017.
 */
security {
    oauth2 {
        providers {
            instagram {
                successUri = "/oauth2/linkedin/success"
                failureUri = "/oauth2/linkedin/failure"
                callback = "/oauth2/linkedin/callback"
                api_key = "changeme_apikey"
                api_secret = "changeme_apisecret"
            }
        }
    }
}
