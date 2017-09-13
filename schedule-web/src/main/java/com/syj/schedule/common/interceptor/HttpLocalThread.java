package com.syj.schedule.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpLocalThread {

    static ThreadLocal<HttpLocalThread.HttpContext> threadLocal = new ThreadLocal();

    public HttpLocalThread() {
    }

    public static void set(HttpServletRequest req, HttpServletResponse res) {
        threadLocal.set(new HttpLocalThread.HttpContext(req, res));
    }

    public static HttpServletRequest getRequest() {
        return ((HttpLocalThread.HttpContext)threadLocal.get()).getRequest();
    }

    public static HttpServletResponse getResponse() {
        return ((HttpLocalThread.HttpContext)threadLocal.get()).getResponse();
    }

    public static void clean() {
        threadLocal.remove();
    }

    static class HttpContext {
        private HttpServletRequest request;
        private HttpServletResponse response;

        public HttpContext(HttpServletRequest request, HttpServletResponse response) {
            this.request = request;
            this.response = response;
        }

        public HttpServletRequest getRequest() {
            return this.request;
        }

        public HttpServletResponse getResponse() {
            return this.response;
        }
    }
}
