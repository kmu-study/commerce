package com.study.authservice.global.security.oauth2.user

class GoogleOAuth2UserInfo(attributes: Map<String, Any>): OAuth2UserInfo(attributes) {
    override fun getId(): String {
        return attributes["sub"] as String
    }

    override fun getName(): String {
        return attributes["name"] as String
    }

    override fun getEmail(): String {
        return attributes["email"] as String
    }

    override fun getImageUrl(): String {
        return attributes["picture"] as String
    }
}