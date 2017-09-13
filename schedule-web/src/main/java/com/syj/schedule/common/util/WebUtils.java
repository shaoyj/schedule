package com.syj.schedule.common.util;

import com.syj.schedule.common.domain.RequestKV;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class WebUtils {

    public WebUtils() {
    }

    public static Integer getPage(HttpServletRequest request) {
        return getPage(request, "page");
    }

    public static Integer getPage(HttpServletRequest request, String pageKey) {
        int page = NumberUtils.toInt(request.getParameter(pageKey));
        return page < 1 ? 1 : page;
    }

    public static String getParameter(HttpServletRequest request, String key) {
        return request.getParameter(key);
    }

    public static String getPageUrl(HttpServletRequest req, Map<String, String> skipParam) {
        return initUrl(req, (Map)null, skipParam);
    }

    public static String getUrl(HttpServletRequest req, boolean skipPageParam, Map<String, String> parameter) {
        Map<String, String> skipParam = new HashMap();
        if (skipPageParam) {
            skipParam.put("page", "page");
            skipParam.put("pageSize", "pageSize");
        }

        return initUrl(req, parameter, skipParam);
    }

    public static String getUrl(HttpServletRequest req, String requestUri, boolean skipPageParam, Map<String, String> parameter) {
        Map<String, String> skipParam = new HashMap();
        if (skipPageParam) {
            skipParam.put("page", "page");
            skipParam.put("pageSize", "pageSize");
        }

        return initUrl(req, requestUri, parameter, skipParam);
    }

    public static String getUrl(HttpServletRequest req, Map<String, String> defaultV) {
        return getUrl(req, true, defaultV);
    }

    private static String initUrl(HttpServletRequest req, Map<String, String> parameter, Map<String, String> skipParam) {
        return initUrl(req, req.getRequestURI(), parameter, skipParam);
    }

    private static String initUrl(HttpServletRequest req, String requestUri, Map<String, String> parameter, Map<String, String> skipParam) {
        StringBuffer sb = new StringBuffer();
        Enumeration<String> it = req.getParameterNames();
        int pos = 0;
        HashSet keys = new HashSet();

        while(true) {
            String key;
            String[] values;
            do {
                do {
                    do {
                        do {
                            if (!it.hasMoreElements()) {
                                String url;
                                if (parameter != null && !parameter.isEmpty()) {
                                    Iterator var16 = parameter.keySet().iterator();

                                    while(var16.hasNext()) {
                                        url = (String)var16.next();
                                        if (pos++ > 0) {
                                            sb.append("&");
                                        }

                                        sb.append(url);
                                        sb.append("=");
                                        sb.append((String)parameter.get(url));
                                    }
                                }

                                if (sb.length() > 0) {
                                    sb.append("&");
                                }

                                sb.append("page=");
                                StringBuffer uri = new StringBuffer();
                                uri.append(requestUri);
                                if (sb.length() > 1) {
                                    uri.append("?");
                                    uri.append(sb.toString());
                                }

                                url = uri.toString().replaceAll("/{1,}", "/");
                                return url;
                            }

                            key = (String)it.nextElement();
                        } while(skipParam != null && skipParam.containsKey(key));
                    } while(parameter != null && parameter.containsKey(key));
                } while(keys.contains(key));

                keys.add(key);
                values = req.getParameterValues(key);
            } while(ArrayUtils.isEmpty(values));

            String[] var10 = values;
            int var11 = values.length;

            for(int var12 = 0; var12 < var11; ++var12) {
                String v = var10[var12];
                if (pos++ > 0) {
                    sb.append("&");
                }

                sb.append(key);
                sb.append("=");

                try {
                    sb.append(URLEncoder.encode(v, "UTF-8"));
                } catch (UnsupportedEncodingException var15) {
                    var15.printStackTrace();
                }
            }
        }
    }

    public static List<RequestKV> getParameterMap(HttpServletRequest req, Map<String, String> skipParam) {
        Enumeration<String> it = req.getParameterNames();
        Set<String> keys = new HashSet();
        ArrayList list = new ArrayList();

        while(true) {
            String key;
            String[] values;
            do {
                do {
                    do {
                        if (!it.hasMoreElements()) {
                            return list;
                        }

                        key = (String)it.nextElement();
                    } while(skipParam != null && skipParam.containsKey(key));
                } while(keys.contains(key));

                keys.add(key);
                values = req.getParameterValues(key);
            } while(ArrayUtils.isEmpty(values));

            String[] var7 = values;
            int var8 = values.length;

            for(int var9 = 0; var9 < var8; ++var9) {
                String v = var7[var9];
                RequestKV kv = new RequestKV(key, v);
                list.add(kv);
            }
        }
    }

    public static String getUrl(HttpServletRequest req) {
        return getUrl(req, true, (Map)null);
    }

    public static String getUrlByParam(String requestURI, boolean skipPageParam, Map<String, Object> param) {
        StringBuffer sb = new StringBuffer();
        int pos = 0;
        Iterator var5 = param.keySet().iterator();

        while(true) {
            String key;
            do {
                if (!var5.hasNext()) {
                    if (sb.length() > 0) {
                        sb.insert(0, "?");
                    }

                    sb.insert(0, requestURI);
                    return sb.toString();
                }

                key = (String)var5.next();
            } while(skipPageParam && ("page".equals(key) || "perPageRecord".equals(key)));

            if (pos++ > 0) {
                sb.append("&");
            }

            sb.append(key);
            sb.append("=");
            sb.append(param.get(key).toString());
        }
    }

    public static String getUrlByKeyValue(HttpServletRequest req, String... fields) {
        if (fields != null && fields.length != 0) {
            Map<String, String> param = new HashMap();

            for(int i = 0; i < fields.length; i += 2) {
                try {
                    param.put(fields[i], fields[i + 1]);
                } catch (ArrayIndexOutOfBoundsException var5) {
                    ;
                }
            }

            return getUrl(req, true, param);
        } else {
            return getUrl(req);
        }
    }

    public static Integer getInt(HttpServletRequest request, String key) {
        String str = getParameter(request, key);
        return StringUtils.isEmpty(str) ? null : NumberUtils.toInt(str);
    }

}
