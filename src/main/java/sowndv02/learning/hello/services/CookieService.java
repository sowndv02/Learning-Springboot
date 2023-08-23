package sowndv02.learning.hello.services;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CookieService {

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    public Cookie get(String name) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase(name))
                return cookie;
        }
        return null;
    }

    public String getValue(String name) {
        var cookie = get(name);
        if (cookie == null)
            return null;
        return cookie.getValue();
    }

    public Cookie add(String name, String value, int hours) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(hours * 60 * 60);
        response.addCookie(cookie);
        return cookie;
    }

    public void remove(String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
    }
}