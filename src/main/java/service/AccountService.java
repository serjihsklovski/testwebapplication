package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public final class AccountService {

    private static AccountService instance = new AccountService();

    private Map<String, HttpSession> sessionIdToSession;

    private AccountService() {
        sessionIdToSession = new HashMap<>();
    }

    public static AccountService getInstance() {
        return instance;
    }

    public boolean isLoggedIn(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);

        return (httpSession != null) &&
                (httpSession.getAttribute("user") != null) &&
                (sessionIdToSession.containsKey(httpSession.getId()));
    }

    public HttpSession getHttpSession(String sessionId) {
        return sessionIdToSession.get(sessionId);
    }

    public void putHttpSession(HttpSession httpSession) {
        sessionIdToSession.put(httpSession.getId(), httpSession);
    }

    public void removeHttpSession(String sessionId) {
        sessionIdToSession.remove(sessionId).invalidate();
    }
}
