package com.example.base.springmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebContext {

  public static final String ORIGINAL_REQUEST_SERVLET_PATH_KEY = "original_servlet_path";
  private static final ThreadLocal<Object[]> WEBCONTEXT_LOCAL = new ThreadLocal<Object[]>();

  /**
   * 得到当前request
   *
   * @return request
   */
  public static HttpServletRequest currentRequest() {
    Object[] locals = WEBCONTEXT_LOCAL.get();
    return locals == null ? null : (HttpServletRequest) locals[0];
  }

  /**
   * 得到当前response
   *
   * @return response
   */
  public static HttpServletResponse currentResponse() {
    Object[] locals = WEBCONTEXT_LOCAL.get();
    return locals == null ? null : (HttpServletResponse) locals[1];
  }

  /**
   * 保存一个原始request信息，以免forward和include的时候请求信息发生变化
   *
   * @return response
   */
  public static void saveOriginalInfo() {
    currentRequest()
        .setAttribute(ORIGINAL_REQUEST_SERVLET_PATH_KEY, currentRequest().getServletPath());
  }

  /**
   * 在进入WebContextFilter过滤器时，将request和response注册到ThreadLocal中
   *
   * @param request 要注入的request
   * @param response 要注入的response javax.servlet.ServletResponse, javax.servlet.FilterChain)
   */
  public static void registry(HttpServletRequest request, HttpServletResponse response) {
    Object[] locals = new Object[]{request, response};
    WEBCONTEXT_LOCAL.set(locals);
    saveOriginalInfo();
  }

  /**
   * 在WebContextFilter过滤器完成时，将request和response从ThreadLocal中清除
   */
  public static void release() {
    WEBCONTEXT_LOCAL.set(null);
  }
}
