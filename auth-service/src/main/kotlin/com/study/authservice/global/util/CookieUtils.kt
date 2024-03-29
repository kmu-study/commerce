package com.study.authservice.global.util

import org.springframework.util.SerializationUtils
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

abstract class CookieUtils {

    companion object {
        fun getCookie(request: HttpServletRequest, name: String): Optional<Cookie> {
            val cookies = request.cookies

            if(cookies != null && cookies.isNotEmpty()) {
                for (cookie in cookies) {
                    if(cookie.name.equals(name)) {
                        return Optional.of(cookie)
                    }
                }
            }

            return Optional.empty()
        }

        fun deleteCookie(
            request: HttpServletRequest,
            response: HttpServletResponse,
            name: String
        ) {
            val cookies = request.cookies

            if(cookies != null && cookies.isNotEmpty()) {
                for (cookie in cookies) {
                    if(cookie.name.equals(name)) {
                        cookie.value = "";
                        cookie.path = "/";
                        cookie.maxAge = 0;
                        response.addCookie(cookie);
                    }
                }
            }
        }

        fun addCookie(response: HttpServletResponse, name: String, value: String, maxAge: Int) {
            val cookie = Cookie(name, value)
            cookie.path = "/"
            cookie.isHttpOnly = true
            cookie.maxAge = maxAge
            response.addCookie(cookie)
        }

        fun serialize(obj: Any): String {
            return Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(obj))
        }

        fun <T> deserialize(cookie: Cookie, cls: Class<T>): T {
            return cls.cast(SerializationUtils.deserialize(
                Base64.getUrlDecoder().decode(cookie.value)))
        }


    }
}